package aicore.steps;

import java.io.IOException;

import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.utils.LoginPageUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private HomePage homePage;

	public LoginSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
	}

	@Given("User is on login page")
	public void user_is_on_application() throws IOException {
		LoginPageUtils.navigateToLoginPage(SetupHooks.getPage());
	}

	@When("User enters username and password and click on SignIn button")
	public void user_enters_username_and_password_and_click_on_sign_in_button()
			throws InterruptedException, IOException {
		LoginPageUtils.closeCookiesPopup(SetupHooks.getPage());
		LoginPageUtils.loginToApplication(SetupHooks.getPage());
	}

	@When("User enters nativeUsername and nativePassword")
	public void user_enters_native_username_and_native_password()
			throws InterruptedException {
		LoginPageUtils.closeCookiesPopup(SetupHooks.getPage());
		LoginPageUtils.enterUsernameAndPassword(SetupHooks.getPage(), ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME),
				ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD));
	}

	@And("User clicks on Login button")
	public void user_clicks_on_login_button() {
		LoginPageUtils.clickLoginButton(SetupHooks.getPage());
	}
	
	@Then("User can navigate to home page")
	public void userCanNavigateToHomePage() {
		homePage.navigateToHomePage();
	}

}
