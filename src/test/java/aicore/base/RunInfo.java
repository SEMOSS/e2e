package aicore.base;

import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunInfo {

    private static final AtomicBoolean firstRun = new AtomicBoolean(true);
    private static final AtomicBoolean needToCreateUser = new AtomicBoolean(true);
    private static Boolean accept_cookies_popup = null;

    private static List<String> URLS = new ArrayList<>();

    public static boolean isFirstRun() {
        return firstRun.getAndSet(false);
    }

    public static boolean isNeedToCreateUser() {
        return needToCreateUser.getAndSet(false);
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
