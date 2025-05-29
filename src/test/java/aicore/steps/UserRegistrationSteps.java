package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.HomePage;
import aicore.pages.LoginPage;
import aicore.pages.UserRegistrationPage;
import aicore.utils.CommonUtils;
import aicore.utils.ConfigUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserRegistrationSteps {
	private HomePage homePage;
	private LoginPage loginPage;
	private UserRegistrationPage userRegistration;
	protected String timestamp;

	public UserRegistrationSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		loginPage = new LoginPage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		this.userRegistration = new UserRegistrationPage(SetupHooks.getPage());
	}

	@When("User clicks on Register Now button")
	public void user_clicks_on_register_now_button() {
		userRegistration.clickOnRegisterNowButton();
	}

	@And("User fills below user creation form fields")
	public void user_fills_below_user_creation_form_fields(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD_NAME");
			String fieldValue = row.get("FIELD_VALUE");

			fieldValue = fieldValue.replace("<RANDOM_ID>", timestamp);
			userRegistration.fillUserRegistartionFormField(fieldName, fieldValue);
		}
	}

	@And("User clicks on Register button")
	public void user_clicks_on_register_button() {
		userRegistration.clickOnRegisterButton();
	}

	@Then("User can see {string} message on login page")
	public void user_can_see_message_on_login_page(String expectedMessage) {
		String actualMessage = userRegistration.verifyRegitrationSuccessMessage(expectedMessage);
		Assertions.assertEquals(expectedMessage, actualMessage, "Registration message is incorrect on login page");
	}

	@When("User enters username as {string} and password {string}")
	public void user_enters_username_as_and_password(String username, String password) {
		String uniqueUsername = username.replace("<RANDOM_ID>", timestamp);
		String uniquePassword = password.replace("<RANDOM_ID>", timestamp);
		loginPage.enterUsernameAndPassword(uniqueUsername, uniquePassword);
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.loginWithNative();
	}

	@Then("User should navigate to home page")
	public void user_should_navigate_to_home_page() {
		String actualPageTitle = homePage.getPageTitle();
		String expectedPageTitle = ConfigUtils.getValue("applicationName");
		Assertions.assertEquals(expectedPageTitle, actualPageTitle, "Page title is incorrect");
	}
}
