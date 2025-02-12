package e2e;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserContext.StorageStateOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.Cookie;

public class CookieSetter {

	private static final Logger LOGGER = LogManager.getLogger(E2ETests.class);

	private static final String SET_COOKIE = "set-cookie";

	public static void doSet(Response s, BrowserContext context, Page page) {
		try {
			Map<String, String> headers = s.allHeaders();
			if (headers.containsKey(SET_COOKIE)) {
				LOGGER.info("Set cookie found");
				String stringcookie = headers.get(SET_COOKIE);
				if (stringcookie != null) {
					parseAndSetCookie(context, stringcookie, page);
				} else {
					LOGGER.warn("String cookie is null {}", headers);
				}
			} else {
				LOGGER.info("No set cookie found");
			}
		} catch (Exception | Error e) {
			LOGGER.error("Messed up cookie setting", e);
		}
	}

	private static void parseAndSetCookie(BrowserContext context, String stringcookie, Page page) {
		Map<String, String> cookieMap = new HashMap<>();
		cookieMap.put("cookie", stringcookie);
		page.setExtraHTTPHeaders(cookieMap);
		MonolithCookieJar.setCookie(stringcookie);
		String[] split = stringcookie.split("; ");
		String name = split[0].split("=")[0];
		String value = split[0].split("=")[1];
		String path = split[1].split("=")[1];
		List<Cookie> cookies = context.cookies();
		List<Cookie> toAdd = new ArrayList<>();
		Cookie newCookie = createCookie(name, value, path);
		for (Cookie cookie : cookies) {
			LOGGER.info("Looking for cookie: {}", cookie);
			if (cookie.name != name) {
				toAdd.add(cookie);
			}
		}
		toAdd.add(newCookie);
		LOGGER.info("adding cookies: {}", toAdd);
		context.addCookies(toAdd);
		StorageStateOptions sso = new StorageStateOptions();
		sso.setPath(Paths.get("state.json"));
		String storage = context.storageState(sso);
		LOGGER.info("Storage: {}", storage);
		
	}

	private static Cookie createCookie(String name, String value, String path) {
		Cookie newCookie = new Cookie(name, value);
		newCookie.setPath(path);
		newCookie.setSecure(true);
		newCookie.setHttpOnly(true);
		newCookie.setDomain("semoss");
		return newCookie;
	}

}
