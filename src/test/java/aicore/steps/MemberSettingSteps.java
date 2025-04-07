package aicore.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MemberSettingSteps {

	private HomePage homePage;
	private SettingPage settingPage;
	private static final Logger logger = LogManager.getLogger(MemberSettingSteps.class);


	public MemberSettingSteps() {
		this.homePage = new HomePage(SetupHooks.getPage());
		this.settingPage = new SettingPage(SetupHooks.getPage());
	}

	@Given("User navigates to Open Setting page")
	public void user_navigates_to_open_setting_page() {
		homePage.checkOnOpenSetting();
		homePage.clickOnOpenSetting();
	}

	@When("User enable admin mode")
	public void user_enable_admin_mode() {
		if (settingPage.checkAdminButton()) {
			settingPage.clickOnAdminButton();
		} else {
			logger.info("Admin button is not visible");
		}

	}

	@When("User clicks on {string} Card")
	public void user_clicks_on_card(String cardName) {
		settingPage.checkCardVisible(cardName);
		settingPage.clickOnCard(cardName);

	}

	@Then("User sees the Add User button")
	public void user_sees_the_add_user_button() {
		settingPage.checkAddUserButton();

	}

	@Then("User sees Admin mode on")
	public void user_sees_admin_mode_on() {
		settingPage.checkAdminOnButton();

	}

	@Then("User sees atleast one count of users on Member setting page")
	public void user_sees_atleast_one_count_of_users_on_Member_setting_page() {
		int countOfUser = settingPage.checkCountOfUsers();
		Assertions.assertTrue(countOfUser > 0);
	}

}