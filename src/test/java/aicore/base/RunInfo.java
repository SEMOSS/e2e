package aicore.base;

public class RunInfo {

    private static boolean firstRun = true;

    public static boolean isFirstRun() {
        if (firstRun) {
            firstRun = false;
            return true;
        }
        return false;
    }

}
