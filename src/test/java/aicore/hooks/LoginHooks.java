
package aicore.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Page;

import aicore.base.AICoreTestManager;
import aicore.base.GenericSetupUtils;
import aicore.base.RunInfo;
import aicore.utils.ConfigUtils;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class LoginHooks {

	private static final Logger LOGGER = LogManager.getLogger(LoginHooks.class);
// Microsoft
	private static final String INFO_POPUP_ACCEPT_BUTTON_XPATH = "//div[@class='MuiStack-root css-bcmwpg']//button";
	private static final String MICROSOFT_LOGIN_XPATH = "(//button[@type='button'])[4]";
	private static final String USERNAME_XPATH = "//input[@type='email']";
	private static final String NEXT_BUTTON_XPATH = "#idSIButton9";
	private static final String PASSWORD_XPATH = "//input[@type='password']";
	private static final String SIGNIN_BUTTON_XPATH = "//input[@data-report-event='Signin_Submit']";

//	Native user
	private static final String NATIVE_USERNAME_XPATH = "//label[text()='Username']/following-sibling::div//input";
	private static final String NATIVE_PASSWORD_XPATH = "//input[contains(@class, 'MuiInputBase-input') and contains(@class, 'MuiOutlinedInput-input') and @type='password']";
	private static final String LOGIN_WITH_NATIVE_XPATH = "//button[@type='submit' and contains(@class, 'MuiButton-containedPrimary')]";
	private static Page page;
	private static boolean loggedIn = false;

	@Before
	public void logIn(Scenario scenario) throws Exception {
		page = AICoreTestManager.getPage();
		if (!loggedIn) {
			AICoreTestManager.launchApp();
			if (scenario.getSourceTagNames().contains("@MSUser")) {
				page.locator(INFO_POPUP_ACCEPT_BUTTON_XPATH).click();
				Page page1 = page.waitForPopup(() -> {
					page.locator(MICROSOFT_LOGIN_XPATH).click();
				});
				page1.fill(USERNAME_XPATH, ConfigUtils.getValue("ms_username"));
				page1.click(NEXT_BUTTON_XPATH);
				page1.fill(PASSWORD_XPATH, ConfigUtils.getValue("ms_password"));
				page1.click(SIGNIN_BUTTON_XPATH);
			} else {
				if (RunInfo.isAcceptCookiesPopup()) {
					page.locator(INFO_POPUP_ACCEPT_BUTTON_XPATH).click();
				}
				
				LOGGER.info("login page attempt: {}", page.url());
				
//				String adminUser = ConfigUtils.getValue("native_username");
//				String adminPassword = ConfigUtils.getValue("native_password");
//				GenericSetupUtils.login(page, adminUser, adminPassword);

				page.fill(NATIVE_USERNAME_XPATH, ConfigUtils.getValue("native_username"));
				page.fill(NATIVE_PASSWORD_XPATH, ConfigUtils.getValue("native_password"));
				page.click(LOGIN_WITH_NATIVE_XPATH);
			}
			loggedIn = true;
		} else {
			LOGGER.info("Already Logged in");
		}
	}

	@AfterAll
	public static void tearDown() {

		AICoreTestManager.close();
	}
}
