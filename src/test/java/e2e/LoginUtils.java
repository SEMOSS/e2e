package e2e;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;

public class LoginUtils {
	private static final Logger LOGGER = LogManager.getLogger(LoginUtils.class);

	public static void login(Page page, BrowserContext context, String username, String password) {
		page.navigate(Utility.getUrl("/packages/client/dist/#/login"));
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill(username);
		page.getByLabel("Username").press("Tab");
		page.locator("input[type=\"password\"]").fill(password);
		Response response = page.waitForResponse(Utility.getApi("/api/auth/login"), () -> page
				.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
		String cookie = response.allHeaders().get("set-cookie").split("; ")[0];
		Map<String, String> newMap = new HashMap<>();
		newMap.put("cookie", cookie);
		page.setExtraHTTPHeaders(newMap);
		LOGGER.info("storage state: {}", context.storageState());
		page.reload();
	}

}
