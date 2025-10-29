package aicore.runners;

import io.cucumber.core.cli.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FeatureFolderParallelExecutor {
    private static final Logger logger = LogManager.getLogger(FeatureFolderParallelExecutor.class);

    // Base feature folder root
    private static final String FEATURE_ROOT = "src/test/resources/features";

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
        
        // Get all feature folders dynamically
        List<String> allFeatureFolders = Files.walk(Paths.get(FEATURE_ROOT), 1)
                .filter(path -> Files.isDirectory(path) && !path.equals(Paths.get(FEATURE_ROOT)))
                .map(path -> path.getFileName().toString())
                .sorted()  // Sort folders for consistent distribution
                .collect(Collectors.toList());
                
        logger.info("Found feature folders: {}", allFeatureFolders);
        
        // Distribute folders across threads evenly
        Map<String, List<String>> threadFolders = distributeFeatureFolders(allFeatureFolders, threadCount);
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Integer>> futures = new ArrayList<>();

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

        // Submit tasks for each thread with its assigned feature folders
        for (Map.Entry<String, List<String>> entry : threadFolders.entrySet()) {
            String threadName = entry.getKey();
            List<String> folders = entry.getValue();
            
            // Collect all feature files from assigned folders
            List<String> features = new ArrayList<>();
            for (String folder : folders) {
                features.addAll(listFeaturesUnderFolder(folder));
            }
            
            if (features.isEmpty()) {
                logger.warn("No features found for thread {} in folders {}", threadName, folders);
                continue;
            }
            
            futures.add(executor.submit(() -> runCucumber(threadName, features)));
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.HOURS);
        if (!finished) {
            logger.warn("Timed out waiting for test execution");
            executor.shutdownNow();
            return 1;
        }

        int result = 0;
        for (Future<Integer> future : futures) {
            try {
                int r = future.get();
                if (r != 0) {
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

        args.add("--monochrome");

        logger.info("Cucumber CLI args: {}", args);

        int exitCode = Main.run(args.toArray(new String[0]), Thread.currentThread().getContextClassLoader());
        logger.info("Thread {} finished with exit code {}", threadName, exitCode);
        return exitCode;
    }
}