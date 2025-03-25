package e2e;

public class LoginUtils {
//	private static final Logger logger = LogManager.getLogger(LoginUtils.class);
//
//	public static void login(Page page, BrowserContext context, String username, String password) {
//		page.navigate(Utility.getUrl("/packages/client/dist/#/login"));
//		page.getByLabel("Username").click();
//		page.getByLabel("Username").fill(username);
//		page.getByLabel("Username").press("Tab");
//		page.locator("input[type=\"password\"]").fill(password);
//		Response response = page.waitForResponse(Utility.getApi("/api/auth/login"), () -> page
//				.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
//		String cookie = response.allHeaders().get("set-cookie").split("; ")[0];
//		Map<String, String> newMap = new HashMap<>();
//		newMap.put("cookie", cookie);
//		page.setExtraHTTPHeaders(newMap);
//		logger.info("storage state: {}", context.storageState());
//		page.reload();
//	}

}
