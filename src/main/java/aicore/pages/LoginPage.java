package aicore.pages;

import java.io.IOException;

import com.microsoft.playwright.Page;

import aicore.utils.ConfigUtils;

public class LoginPage {

	private Page page;

	private static final String INFO_POPUP_ACCEPT_BUTTON_XPATH = "//div[@class='MuiStack-root css-bcmwpg']//button";
	private static final String MICROSOFT_LOGIN_XPATH = "(//button[@type='button'])[4]";
	private static final String USERNAME_XPATH = "//input[@type='email']";
	private static final String NEXT_BUTTON_XPATH = "#idSIButton9";
	private static final String PASSWORD_XPATH = "//input[@type='password']";
	private static final String SIGNIN_BUTTON_XPATH = "//input[@data-report-event='Signin_Submit']";
	private static final String NATIVE_USERNAME_XPATH = "//label[text()='Username']/following-sibling::div//input";
	private static final String NATIVE_PASSWORD_XPATH = "//input[contains(@class, 'MuiInputBase-input') and contains(@class, 'MuiOutlinedInput-input') and @type='password']";
	private static final String LOGIN_WITH_NATIVE_XPATH = "//button[@type='submit' and contains(@class, 'MuiButton-containedPrimary')]";

	public LoginPage(Page page) {
		this.page = page;
	}

	public void navigateToLoginPage() throws IOException {
		page.navigate(ConfigUtils.getValue("baseUrl"));
	}

	public void closeCookiesPopup() throws InterruptedException {
		page.locator(INFO_POPUP_ACCEPT_BUTTON_XPATH).click();
	}

	public void loginToApplication() throws InterruptedException, IOException {
		Page page1 = page.waitForPopup(() -> {
			page.locator(MICROSOFT_LOGIN_XPATH).click();
		});
		page1.fill(USERNAME_XPATH, ConfigUtils.getValue("ms_username"));
		page1.click(NEXT_BUTTON_XPATH);
		page1.fill(PASSWORD_XPATH, ConfigUtils.getValue("ms_password"));
		page1.click(SIGNIN_BUTTON_XPATH);
	}

	public void enterNativeUsernamePassword() {
		page.fill(NATIVE_USERNAME_XPATH, ConfigUtils.getValue("native_username"));
		page.fill(NATIVE_PASSWORD_XPATH, ConfigUtils.getValue("native_password"));
	}

	public void loginWithNative() {
		page.click(LOGIN_WITH_NATIVE_XPATH);
	}

	public void loginWithDifferetUsers(String role) throws Exception {
		String username = ConfigUtils.getValue(role.toLowerCase() + "_username");
		String password = ConfigUtils.getValue(role.toLowerCase() + "_password");

		if (username == null || password == null) {
			throw new Exception("Login credentials not found for role: " + role);
		}

		page.fill(NATIVE_USERNAME_XPATH, username);
		page.fill(NATIVE_PASSWORD_XPATH, password);
		page.click(LOGIN_WITH_NATIVE_XPATH);
	}
}
