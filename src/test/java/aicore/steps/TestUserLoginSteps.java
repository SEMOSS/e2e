package aicore.steps;

import aicore.base.AICoreTestBase;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class TestUserLoginSteps {

	private LoginPage loginpage;
	private HomePage homePage;

	public TestUserLoginSteps() {
		this.loginpage = new LoginPage(AICoreTestBase.page);
		this.homePage = new HomePage(AICoreTestBase.page);
	}

	@When("User enters nativeUsername and nativePassword")
	public void user_enters_native_username_and_native_password_and_click_on_sign_in_button()
			throws InterruptedException {
		loginpage.closeCookiesPopup();
		loginpage.enterNativeUsernamePassword();

	}

	@And("User clicks on Login with native button")
	public void click_on_sign_in_button() {
		loginpage.loginWithNative();
	}

}
