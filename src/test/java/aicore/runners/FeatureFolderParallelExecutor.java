package aicore.runners;

import io.cucumber.core.cli.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FeatureFolderParallelExecutor {
    private static final Logger logger = LogManager.getLogger(FeatureFolderParallelExecutor.class);

    // Base feature folder root
    private static final String pathSeparator = FileSystems.getDefault().getSeparator();
    private static final String FEATURE_ROOT = "src" + pathSeparator + "test" + pathSeparator + "resources" +
            pathSeparator + "Features";

    private static Map<String, List<String>> distributeFeatureFolders(List<String> folders, int threadCount) {
        Map<String, List<String>> distribution = new HashMap<>();
        int totalFolders = folders.size();
        
        // Ensure we don't create more threads than folders
        threadCount = Math.min(threadCount, totalFolders);
        if (threadCount == 0) {
            logger.warn("No feature folders found to distribute");
            return distribution;
        }
        
        int baseFoldersPerThread = totalFolders / threadCount;
        int extraFolders = totalFolders % threadCount;
        
        int currentIndex = 0;
        
        for (int i = 0; i < threadCount; i++) {
            String threadName = "thread-" + (i + 1);
            int foldersForThisThread = baseFoldersPerThread + (i < extraFolders ? 1 : 0);
            
            if (foldersForThisThread > 0 && currentIndex < folders.size()) {
                List<String> threadFolders = new ArrayList<>(
                    folders.subList(currentIndex, Math.min(currentIndex + foldersForThisThread, folders.size()))
                );
                distribution.put(threadName, threadFolders);
                currentIndex += foldersForThisThread;
            } else {
                distribution.put(threadName, new ArrayList<>());
            }
        }
        
        logger.info("Feature folder distribution: {}", distribution);
        return distribution;
    }

    public static int runAll() throws Exception {
        // Get thread count from RunInfo
        int threadCount = aicore.base.RunInfo.getParallelism();
        logger.info("Using {} threads for parallel execution", threadCount);
        
        // Collect all feature files dynamically (per-feature granularity for better load balancing)
        List<String> allFeaturePaths = Files.walk(Paths.get(FEATURE_ROOT))
                .filter(p -> p.toString().endsWith(".feature"))
                .map(p -> p.toAbsolutePath().toString())
                .sorted()
                .collect(Collectors.toList());

        logger.info("Found feature files: {}", allFeaturePaths.size());

        // If none found, warn and exit
        if (allFeaturePaths.isEmpty()) {
            logger.warn("No feature files found under {}", FEATURE_ROOT);
            return 0;
        }


        // Create and clean extent report directory
        Path extentReportPath = Paths.get("target", "extent-report");
        if (!Files.exists(extentReportPath)) {
            Files.createDirectories(extentReportPath);
        }
        Files.walk(extentReportPath)
                .filter(Files::isRegularFile)
                .forEach(f -> {
                    try {
                        Files.delete(f);
                    } catch (Exception e) {
                        logger.warn("Failed to delete old report file: {}", f, e);
                    }
                });

        // Use a shared concurrent queue of feature paths so idle threads can pick up remaining work dynamically
        java.util.concurrent.ConcurrentLinkedQueue<String> featureQueue = new java.util.concurrent.ConcurrentLinkedQueue<>(allFeaturePaths);

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            final String workerName = "worker-" + (i + 1);
            tasks.add(() -> {
                long startMillis = System.currentTimeMillis();
                int threadResult = 0;
                int processedCount = 0;
                String featurePath;
                while ((featurePath = featureQueue.poll()) != null) {
                    logger.info("{} picked up feature {}", workerName, featurePath);
                    try {
                        int r = runCucumber(workerName + "-" + Paths.get(featurePath).getFileName().toString(), Collections.singletonList(featurePath));
                        processedCount++;
                        if (r != 0) {
                            logger.warn("Worker {}: feature {} returned non-zero exit code {}", workerName, featurePath, r);
                            threadResult = r; // record the non-zero code
                        }
                    } catch (Exception e) {
                        logger.error("Worker {}: error processing feature {}", workerName, featurePath, e);
                        threadResult = 1;
                    }
                }
                long elapsed = System.currentTimeMillis() - startMillis;
                if (processedCount > 0) {
                    logger.info("{} finished (no more features). Processed {} features in {} ms (avg {} ms/feature)", workerName, processedCount, elapsed, elapsed / processedCount);
                } else {
                    logger.info("{} finished (no more features). Processed 0 features in {} ms", workerName, elapsed);
                }
                return threadResult;
            });
        }

        List<Future<Integer>> futures;
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            futures = executor.invokeAll(tasks);

            executor.shutdown();
            try {
                if (!executor.awaitTermination(2, TimeUnit.HOURS)) {
                    logger.warn("Executor did not terminate in set time limit");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                logger.warn("Forcing shutdown");
                executor.shutdownNow();
                return 1;
            }
        }

        int result = 0;
        for (Future<Integer> future : futures) {
            try {
                int r = future.get();
                if (r != 0) {
                    logger.warn("Non 0 result. Thread had failure");
                    result = r;
                }
            } catch (ExecutionException e) {
                logger.error("Exception during cucumber execution", e);
                result = 1;
            }
        }

        logger.info("Extent report generated at target/extent-report/");
        return result;
    }

    private static List<String> listFeaturesUnderFolder(String folder) throws Exception {
        List<String> featurePaths = new ArrayList<>();
        Files.walk(Paths.get(FEATURE_ROOT, folder))
                .filter(p -> p.toString().endsWith(".feature"))
                .forEach(p -> featurePaths.add(p.toAbsolutePath().toString()));
        return featurePaths;
    }

    private static int runCucumber(String threadName, List<String> featurePaths) {
        logger.info("Thread {} running {} features", threadName, featurePaths.size());

        List<String> validFeaturePaths = new ArrayList<>();
        for (String featurePath : featurePaths) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(featurePath));
                boolean hasScenarios = lines.stream().anyMatch(line -> line.trim().startsWith("Scenario"));
                if (hasScenarios) {
                    validFeaturePaths.add(featurePath);
                } else {
                    logger.warn("Skipping feature file {} as it contains no scenarios", featurePath);
                }
            } catch (Exception e) {
                logger.error("Error reading feature file {}", featurePath, e);
            }
        }

        if (validFeaturePaths.isEmpty()) {
            logger.warn("No valid feature files with scenarios found for thread {}", threadName);
            return 0;
        }

        List<String> args = new ArrayList<>();
        args.addAll(validFeaturePaths);

        args.add("--glue");
        args.add("aicore.steps");
        args.add("--glue");
        args.add("aicore.hooks");
        args.add("--plugin");
        args.add("pretty");
        args.add("--plugin");
        args.add("summary");
        
        args.add("--plugin");
        args.add("com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:");

        // Exclude scenarios marked as ApplicationBugFailure
        args.add("--tags");
        args.add("not @ApplicationBugFailure");

        args.add("--monochrome");

        logger.info("Cucumber CLI args: {}", args);

        int exitCode = Main.run(args.toArray(new String[0]), Thread.currentThread().getContextClassLoader());
        logger.info("Thread {} finished with exit code {}", threadName, exitCode);
        return exitCode;
    }
}
