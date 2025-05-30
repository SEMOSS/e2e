package aicore.pages;

import java.io.IOException;

import com.microsoft.playwright.Page;

import aicore.utils.ConfigUtils;
import aicore.utils.LoginPageUtils;

public class LoginPage {

	private Page page;

	public LoginPage(Page page) {
		this.page = page;
	}

	public void navigateToLoginPage() throws IOException {
		LoginPageUtils.navigateToLoginPage(page);
	}

	public void closeCookiesPopup() throws InterruptedException {
		LoginPageUtils.closeCookiesPopup(page);
	}

	public void loginToApplication() throws InterruptedException, IOException {
		LoginPageUtils.loginToApplication(page);
	}

	public void enterNativeUsernamePassword() {
		LoginPageUtils.enterUsernameAndPassword(page, ConfigUtils.getValue("native_username"), ConfigUtils.getValue("native_password"));
	}

	public void loginWithNative() {
		LoginPageUtils.clickLogin(page);
	}

	public void enterUsernameAndPassword(String username, String password) {
		LoginPageUtils.enterUsernameAndPassword(page, username, password);
	}
}