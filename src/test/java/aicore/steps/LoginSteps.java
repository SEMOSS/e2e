package aicore.steps;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import aicore.utils.ConfigUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private static final Logger logger = LogManager.getLogger(LoginSteps.class);
	private LoginPage loginPage;
	private HomePage homePage;

	public LoginSteps() {
		this.loginPage = new LoginPage(SetupHooks.getPage());
		this.homePage = new HomePage(SetupHooks.getPage());
	}

	@Given("User is on login page")
	public void user_is_on_application() throws IOException {
		loginPage.navigateToLoginPage();
	}

	@When("User enters username and password and click on SignIn button")
	public void user_enters_username_and_password_and_click_on_sign_in_button()
			throws InterruptedException, IOException {
		loginPage.closeCookiesPopup();
		loginPage.loginToApplication();
	}

	@When("User enters nativeUsername and nativePassword")
	public void user_enters_native_username_and_native_password()
			throws InterruptedException {
		loginPage.closeCookiesPopup();
		loginPage.enterUsernameAndPassword(ConfigUtils.getValue("native_username"), ConfigUtils.getValue("native_password"));
	}

	@And("User clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.clickOnLoginButton();
	}
	
	@Then("User can navigate to home page")
	public void userCanNavigateToHomePage() {
		homePage.navigateToHomePage();
	}

}
