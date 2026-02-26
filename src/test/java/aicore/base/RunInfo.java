package aicore.base;

import aicore.framework.ConfigUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunInfo {

    private static boolean firstRun = true;
    private static boolean needToCreateUser = true;
    private static Boolean accept_cookies_popup = null;

    private static Map<String, String> ENV_VARIABLES = new HashMap<>();
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
            accept_cookies_popup = Boolean.parseBoolean(ConfigUtils.getValue("accept_cookies_popup"));
        }
        return accept_cookies_popup;
    }

    public static String getApiEndpoint() {
        return ENV_VARIABLES.get("API_ENDPOINT");
    }

    public static void setEnvVariables(Map<String, String> envVariables) {
        ENV_VARIABLES = envVariables;
    }

    public static Map<String, String> getEnvVariables() {
        return ENV_VARIABLES;
    }

    public static int getParallelism() {
        return Integer.parseInt(ENV_VARIABLES.get("PARALLEL_COUNT"));
    }

    public static void setURLS(List<String> urls) {
        URLS = urls;
    }

    public static List<String> getUrls() {
        return URLS;
    }

}
