package aicore.utils;

import java.io.File;


public class FolderUtils {

    private static final String BENCHMARK = "Version" + CaptureScreenShotUtils.version + "/static/img/";
    
    public static String getCurrentBaselineFolder(String subFolder) {

        File benchmarkSub = new File(BENCHMARK, subFolder);
            if (!benchmarkSub.exists()) {
                benchmarkSub.mkdirs();
            }
            return benchmarkSub.getAbsolutePath();

    }
}

