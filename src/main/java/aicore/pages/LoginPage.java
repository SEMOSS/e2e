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
		// Added for Demo purposes.
		// Thread.sleep(3000);
		page1.fill(USERNAME_XPATH, ConfigUtils.getValue("username"));
		page1.click(NEXT_BUTTON_XPATH);
		page1.fill(PASSWORD_XPATH, ConfigUtils.getValue("password"));
		page1.click(SIGNIN_BUTTON_XPATH);
		// Added for Demo purposes.
		// Thread.sleep(3000);

	}

}
