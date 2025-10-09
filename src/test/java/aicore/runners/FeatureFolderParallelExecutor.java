package aicore.runners;

import io.cucumber.core.cli.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

public class FeatureFolderParallelExecutor {
    private static final Logger logger = LogManager.getLogger(FeatureFolderParallelExecutor.class);

    // Base feature folder root
    private static final String FEATURE_ROOT = "src/test/resources/features";

    private static final Map<String, List<String>> THREAD_FOLDERS = Map.of(
            "thread-1", List.of("database","app","startup","function","home"),
            "thread-2", List.of("model","vector","storage","settings","systemApps","Documentation")   
    );

    public static int runAll() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_FOLDERS.size());
        List<Future<Integer>> futures = new ArrayList<>();

        Path extentReportPath = Paths.get("target", "extent-report");
        if (!Files.exists(extentReportPath)) {
            Files.createDirectories(extentReportPath);
        }
        Files.walk(extentReportPath)
                .filter(Files::isRegularFile)
                .forEach(f -> {
                    try { Files.delete(f); } catch (Exception ignore) {}
                });

        for (Map.Entry<String, List<String>> entry : THREAD_FOLDERS.entrySet()) {
            String threadName = entry.getKey();
            List<String> folders = entry.getValue();
            List<String> features = new ArrayList<>();
            for (String folder : folders) {
                features.addAll(listFeaturesUnderFolder(folder));
            }
            if (features.isEmpty()) {
                logger.warn("No features found for thread {} in folders {}", threadName, folders);
                continue;
            }
            final String tn = threadName;
            final List<String> featureList = features;

            futures.add(executor.submit(() -> runCucumber(tn, featureList)));
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.HOURS);
        if (!finished) {
            logger.warn("Timed out waiting for test execution");
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