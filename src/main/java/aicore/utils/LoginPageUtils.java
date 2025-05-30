package aicore.utils;

import java.io.IOException;

import com.microsoft.playwright.Page;

public class LoginPageUtils {
	
	private static final String INFO_POPUP_ACCEPT_BUTTON_XPATH = "//div[@class='MuiStack-root css-bcmwpg']//button";
	private static final String MICROSOFT_LOGIN_XPATH = "(//button[@type='button'])[4]";
	private static final String USERNAME_XPATH = "//input[@type='email']";
	private static final String NEXT_BUTTON_XPATH = "#idSIButton9";
	private static final String PASSWORD_XPATH = "//input[@type='password']";
	private static final String SIGNIN_BUTTON_XPATH = "//input[@data-report-event='Signin_Submit']";
	private static final String USERNAME_DATA_TEST_ID = "loginPage-textField-username";
	private static final String PASSWORD_DATA_TEST_ID = "loginPage-textField-password";
	private static final String LOGIN_BUTTON_DATA_TEST_ID = "loginPage-button-login";

	
	public static void navigateToLoginPage(Page page) throws IOException {
		page.navigate(UrlUtils.getUrl("#/login"));
	}

	public static void closeCookiesPopup(Page page) throws InterruptedException {
		if (Boolean.parseBoolean(ConfigUtils.getValue("accept_cookies_popup"))) {
			page.locator(INFO_POPUP_ACCEPT_BUTTON_XPATH).click();
		}
	}

	public static void loginToApplication(Page page) throws InterruptedException, IOException {
		Page page1 = page.waitForPopup(() -> {
			page.locator(MICROSOFT_LOGIN_XPATH).click();
		});
		// optional test if the user adds in credentials
		String userName = ConfigUtils.getValue("ms_username");
		if (userName != null && !userName.isEmpty()) {
			page1.fill(USERNAME_XPATH, userName);
			page1.click(NEXT_BUTTON_XPATH);
			page1.fill(PASSWORD_XPATH, ConfigUtils.getValue("ms_password"));
			page1.click(SIGNIN_BUTTON_XPATH);
		}
	}

	public static void clickLogin(Page page) {
		page.getByTestId(LOGIN_BUTTON_DATA_TEST_ID).click();
	}

	public static void enterUsernameAndPassword(Page page, String username, String Password) {
		page.getByTestId(USERNAME_DATA_TEST_ID).fill(username);
		page.getByTestId(PASSWORD_DATA_TEST_ID).fill(Password);
	}
}
