package aicore.steps;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.microsoft.playwright.Page;

import aicore.base.AICoreTestBase;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import aicore.utils.ConfigUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private static final Logger LOGGER = LogManager.getLogger(LoginSteps.class);
	private LoginPage loginpage;
	private HomePage homePage;

	public LoginSteps() throws IOException {
		Page page = null;
		try {
			page = new AICoreTestBase().page; 
		} catch(Exception e) {
			LOGGER.error("Could not init page", e);
		}
		this.loginpage = new LoginPage(AICoreTestBase.page);
		this.homePage = new HomePage(AICoreTestBase.page);
	}

	@Given("User is on application")
	public void user_is_on_application() throws IOException {

		loginpage.navigateToLoginPage();

	}

	@When("User enters username and password and click on SignIn button")
	public void user_enters_username_and_password_and_click_on_sign_in_button()
			throws InterruptedException, IOException {

		loginpage.closeCookiesPopup();
		loginpage.loginToApplication();
	}

	@Then("User can navigate to home page")
	public void user_can_navigate_to_home_page() throws InterruptedException {
//		homePage.closeInfoPopup();
		String actTitle = homePage.getPageTitle();
		Assert.assertEquals(ConfigUtils.getValue("applicationName"), actTitle);

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
