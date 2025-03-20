package aicore.base;

import aicore.utils.ConfigUtils;

public class RunInfo {

    private static boolean firstRun = true;
    private static boolean needToCreateUser = true;
    private static Boolean accept_cookies_popup = null;

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

}
