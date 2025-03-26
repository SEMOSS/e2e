package aicore.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import aicore.base.AICoreTestManager;
import aicore.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MemberSettingSteps {

	private HomePage homePage;
	private SettingPage settingPage;

	public MemberSettingSteps() {
		this.homePage = new HomePage(AICoreTestManager.getPage());
		this.settingPage = new SettingPage(AICoreTestManager.getPage());
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
			System.out.println("Admin button is not visible");
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

	@Then("User sees a count of users on Member setting page")
	public void user_sees_a_count_of_users_on_Member_setting_page() {
		int countOfUser = settingPage.checkCountOfUsers();
		assertTrue(countOfUser > 0);
	}

}