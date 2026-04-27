package aicore.runners;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aicore.framework.ResourcePool;

public class FeatureFolderParallelRunner {
    @Test
    public void runParallelFeatures() throws Exception {
        int result = 1;
        try {
            result = FeatureFolderParallelExecutor.runAll();
            // Fail the test if any thread returned non-zero exit code
            assertEquals(0, result, "Parallel feature execution had failures. Check logs for details.");
        } finally {
            // Global shutdown: close all browsers and Playwright once all workers finished
            try {
                ResourcePool.closeAllResources();
            } catch (Exception e) {
                // Log but don't rethrow as the assert above indicates test failure
                System.err.println("Error during global shutdown: " + e.getMessage());
            }
        }
    }
}