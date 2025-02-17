package aicore.steps;

import java.io.IOException;

import org.junit.Assert;

import aicore.base.AICoreTestBase;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import aicore.utils.ConfigUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private LoginPage loginpage;
	private HomePage homePage;

	public LoginSteps() {
//		Page page = new AICoreTestBase().page; 
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
}
