package aicore.runners;

import org.junit.jupiter.api.Test;

public class FeatureFolderParallelRunner {
    @Test
    public void runParallelFeatures() throws Exception {
         FeatureFolderParallelExecutor.runAll();
       
    }
}