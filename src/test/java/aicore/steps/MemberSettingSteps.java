package aicore.steps;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MemberSettingSteps {

	private HomePage homePage;
	private SettingPage settingPage;
	private static final Logger logger = LogManager.getLogger(MemberSettingSteps.class);
	int NumberOfUser = 0;

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

	@Then("User select {string} in Rows per page filter")
	public void user_select_in_Rows_per_page_filter(String RowsNumber) {
		settingPage.clickNumberOfRows(RowsNumber);
	}

	@Then("User sees the {string} rows in the page")
	public void user_sees_the_rows_in_the_page(String totalRecords) {
		int totalRecordOnPage = settingPage.checkRecordsOnPage();
		int countMember = Integer.parseInt(totalRecords);
		Assertions.assertTrue(totalRecordOnPage == countMember);
	}

	@Then("User clicks on the Right pagination arrow to navigate to next page")
	public void user_clicks_on_the_right_button_to_navigate_to_next_page() {
		settingPage.checkForwardButton();
	}

	@Then("User clicks on the Left pagination arrow to navigate to previous page")
	public void user_clicks_on_the_left_button_to_navigate_to_previous_page() {
		settingPage.checkBackwardButton();
	}

	@When("User clicks on search button")
	public void user_clicks_on_search_button() {
		settingPage.clickOnSearchButton();
		settingPage.clickOnSearchBox();
	}

	@Then("User searchs for user having username {string}")
	public void user_searchs_for_user_having_username(String username) {
		settingPage.enterUsername(username);

	}

	@Then("User sees the {string} in the searched user list")
	public void user_sees_the_username_in_the_searched_user_list(String username) {
		String expectedUserName = settingPage.checkUsername(username);
		Assertions.assertEquals(expectedUserName, username);

	}

	@Then("User sees the count of user as {string} in searched result")
	public void user_sees_the_count_of_user_as_in_searched_result(String count) {
		int countOfUser = settingPage.checkCountOfUsers();
		int countOfMember = Integer.parseInt(count);
		Assertions.assertTrue(countOfUser == countOfMember, "The number should be 1, but was " + countOfUser);

	}

	@And("User sees a count of member")
	public void User_sees_a_count_of_member() {
		int initialCount = settingPage.checkCountOfUsers();
		this.NumberOfUser = initialCount;
	}

	@And("User sees the updated count of members increase by {int}")
	public void user_sees_the_updated_count_of_members_increase_by(int addedNumberOfUser) {
		int updatedCountOfUser = settingPage.checkCountOfUsers();
		Assertions.assertTrue(updatedCountOfUser - this.NumberOfUser == addedNumberOfUser);
	}

}