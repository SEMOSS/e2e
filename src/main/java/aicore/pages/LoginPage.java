package aicore.pages;

import java.io.IOException;

import com.microsoft.playwright.Page;

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

	public void clickOnLoginButton() {
		LoginPageUtils.clickLoginButton(page);
	}

	public void enterUsernameAndPassword(String username, String password) {
		LoginPageUtils.enterUsernameAndPassword(page, username, password);
	}
}