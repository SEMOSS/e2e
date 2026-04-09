package aicore.base;

import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;

import java.util.ArrayList;
import java.util.List;

public class RunInfo {

    private static boolean firstRun = true;
    private static boolean needToCreateUser = true;
    private static Boolean accept_cookies_popup = null;

    private static List<String> URLS = new ArrayList<>();

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
            accept_cookies_popup = Boolean.parseBoolean(ConfigUtils.getValue(AICoreTestConstants.ACCEPT_COOKIES_POPUP));
        }
        return accept_cookies_popup;
    }

    public static String getApiEndpoint() {
        return ConfigUtils.getValue(AICoreTestConstants.API_ENDPOINT);
    }

    public static int getParallelism() {
        return Integer.parseInt(ConfigUtils.getValue(AICoreTestConstants.PARALLEL_COUNT));
    }

    public static void setURLS(List<String> urls) {
        URLS = urls;
    }

    public static List<String> getUrls() {
        return URLS;
    }

}
