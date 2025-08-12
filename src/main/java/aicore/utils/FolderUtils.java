package aicore.utils;

import java.io.File;


public class FolderUtils {

    private static final String BENCHMARK = "img/PlatformNavigation/"+ "Version" + CaptureScreenShotUtils.version ;

    public static String getCurrentBaselineFolder(String subFolder) {

        File benchmarkSub = new File(BENCHMARK, subFolder);
            if (!benchmarkSub.exists()) {
                benchmarkSub.mkdirs();
            }
            return benchmarkSub.getAbsolutePath();

    }
}

