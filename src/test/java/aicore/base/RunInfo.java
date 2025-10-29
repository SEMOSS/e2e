package aicore.base;

import aicore.framework.ConfigUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunInfo {
    private static final Logger logger = LogManager.getLogger(RunInfo.class);
    private static final String ENV_LOCAL_PATH = ".env.local";  // Local environment override
    private static final String ENV_DEFAULT_PATH = ".env";      // Default environment file

    private static boolean firstRun = true;
    private static boolean needToCreateUser = true;
    private static Boolean accept_cookies_popup = null;

    private static Map<String, String> ENV_VARIABLES = new HashMap<>();
    private static List<String> URLS = new ArrayList<>();
    
    static {
        loadEnvironmentVariables();
    }
    
    private static void loadEnvironmentVariables() {
        String projectRoot = System.getProperty("user.dir");
        logger.info("Project root directory: {}", projectRoot);
        
        // Try .env.local first
        Path localEnvPath = Paths.get(projectRoot, ENV_LOCAL_PATH);
        Path defaultEnvPath = Paths.get(projectRoot, ENV_DEFAULT_PATH);
        
        Path envPath;
        if (Files.exists(localEnvPath)) {
            logger.info("Using local environment file: {}", localEnvPath.toAbsolutePath());
            envPath = localEnvPath;
        } else if (Files.exists(defaultEnvPath)) {
            logger.info("Using default environment file: {}", defaultEnvPath.toAbsolutePath());
            envPath = defaultEnvPath;
        } else {
            logger.warn("No environment files found. Checked:\n\t{}\n\t{}", 
                localEnvPath.toAbsolutePath(),
                defaultEnvPath.toAbsolutePath());
            return;
        }
        
        try {
            List<String> lines = Files.readAllLines(envPath);
            for (String line : lines) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        ENV_VARIABLES.put(key, value);
                        logger.debug("Loaded environment variable: {} = {}", key, value);
                    }
                }
            }
            logger.info("Successfully loaded {} environment variables from {}", 
                ENV_VARIABLES.size(), 
                envPath.getFileName());
        } catch (IOException e) {
            logger.error("Failed to load environment file {}: {}", 
                envPath.getFileName(), 
                e.getMessage(), 
                e);
        }
        }

    public static boolean isFirstRun() {
        if (firstRun) {
            firstRun = false;
            return true;
        }
        return false;
    }

    public static boolean isNeedToCreateUser() {
        if (needToCreateUser) {
            needToCreateUser = false;
            return true;
        }
        return false;
    }

    public static boolean isAcceptCookiesPopup() {
        if (accept_cookies_popup == null) {
            accept_cookies_popup = Boolean.parseBoolean(ConfigUtils.getValue("accept_cookies_popup"));
        }
        return accept_cookies_popup;
    }

    public static void setEnvVariables(Map<String, String> envVariables) {
        ENV_VARIABLES = envVariables;
    }

    public static Map<String, String> getEnvVariables() {
        return ENV_VARIABLES;
    }

    /**
     * Get the number of parallel threads to use for test execution.
     * This value is read from the PARALLEL_COUNT environment variable.
     * 
     * @return The number of threads to use, minimum 1
     */
    public static int getParallelism() {
        try {
            String parallelCount = ENV_VARIABLES.getOrDefault("PARALLEL_COUNT", "1").trim();
            int count = Integer.parseInt(parallelCount);
            if (count < 1) {
                logger.warn("PARALLEL_COUNT must be at least 1, got: {}. Using 1 thread.", count);
                return 1;
            }
            return count;
        } catch (NumberFormatException e) {
            String value = ENV_VARIABLES.get("PARALLEL_COUNT");
            logger.error("Invalid PARALLEL_COUNT value: '{}', defaulting to 1", value);
            return 1;
        }
    }

    public static void setURLS(List<String> urls) {
        URLS = urls;
    }

    public static List<String> getUrls() {
        return URLS;
    }

}
