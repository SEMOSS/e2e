package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.SettingAdminThemePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class AdminThemeSteps {
	private SettingAdminThemePage themePage;

	public AdminThemeSteps() {
		themePage = new SettingAdminThemePage(SetupHooks.getPage());
	}

	@Given("User should see page title {string}")
	public void user_see_page_title(String titleName) {
		boolean isTitleDisplay = themePage.VerifyPageTitleIsDisplay(titleName);
		Assertions.assertTrue(isTitleDisplay, "Page title is not displayed as expected.");
	}

	@And("User should see breadcrumb {string}")
	public void user_see_breadcrumb(String breadcrumbName) {
		boolean isBreadcrumbDisplay = themePage.VerifyBreadcrumbIsDisplay(breadcrumbName);
		Assertions.assertTrue(isBreadcrumbDisplay, "Breadcrumb is not displayed as expected.");
	}

	@And("User should see subtitle {string}")
	public void user_see_subtitle(String subtitle) {
		boolean isSubtitleDisplay = themePage.VerifySubtitleIsDisplay(subtitle);
		Assertions.assertTrue(isSubtitleDisplay, "Subtitle is not displayed as expected.");
	}

	@And("User should see Select Theme dropdown")
	public void user_see_dropdown() {
		boolean isDropdownVisible = themePage.VerifyDropdownIsDisplay();
		Assertions.assertTrue(isDropdownVisible, "Dropdown '" + "' is not visible.");
	}

	@And("User should see {string} input field")
	public void user_see_input_field(String inputFieldName) {
		boolean isInputFieldVisible = themePage.VerifyInputFieldIsDisplay(inputFieldName);
		Assertions.assertTrue(isInputFieldVisible, "Input field '" + inputFieldName + "' is not visible.");
	}

	@And("User should see {string} editor section")
	public void user_see_editor_section(String editorSectionName) {
		boolean isEditorSectionVisible = themePage.VerifyJsonEditorSectionIsDisplay(editorSectionName);
		Assertions.assertTrue(isEditorSectionVisible, "Editor section '" + editorSectionName + "' is not visible.");
	}

	@And("User should see {string} button")
	public void user_see_button(String buttonName) {
		boolean isButtonVisible = themePage.VerifyButtonIsDisplay(buttonName);
		Assertions.assertTrue(isButtonVisible, "Button '" + buttonName + "' is not visible.");
	}

	@And("User should see {string} link")
	public void user_see_link(String linkName) {
		boolean isLinkVisible = themePage.VerifyLinkIsDisplay(linkName);
		Assertions.assertTrue(isLinkVisible, "Link '" + linkName + "' is not visible.");
	}

	@And("User should see {string} option")
	public void user_see_option(String optionName) {
		boolean isOptionVisible = themePage.VerifyAdminOptionIsDisplay(optionName);
		Assertions.assertTrue(isOptionVisible, "Option '" + optionName + "' is not visible in the dropdown.");
	}

	@And("User enters {string} name in the input field")
	public void user_enters_name_in_input_field(String themeName) {
		themePage.EnterThemeName(themeName);
	}

	@And("User click on {string} button in the Create Theme dialog")
	public void user_click_on_create_button(String buttonName) {
		themePage.ClickCreateButton(buttonName);
	}

	@And("User should see the Toast message {string}")
	public void user_should_see_toast_message(String expectedMessage) {
		boolean isToastVisible = themePage.VerifyToastMessage(expectedMessage);
		Assertions.assertTrue(isToastVisible, "Expected toast message '" + expectedMessage + "' is not visible.");
	}
}