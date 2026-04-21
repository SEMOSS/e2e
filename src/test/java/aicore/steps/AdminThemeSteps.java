package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.utils.settings.SettingAdminThemeUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class AdminThemeSteps {

	public AdminThemeSteps() {
	}

	@Given("User should see page title {string}")
	public void user_see_page_title(String titleName) {
		boolean isTitleDisplay = SettingAdminThemeUtils.verifyPageTitleIsDisplay(SetupHooks.getPage(), titleName);
		Assertions.assertTrue(isTitleDisplay, "Page title is not displayed as expected.");
	}

	@And("User should see breadcrumb {string}")
	public void user_see_breadcrumb(String breadcrumbName) {
		boolean isBreadcrumbDisplay = SettingAdminThemeUtils.verifyBreadcrumbIsDisplay(SetupHooks.getPage(), breadcrumbName);
		Assertions.assertTrue(isBreadcrumbDisplay, "Breadcrumb is not displayed as expected.");
	}

	@And("User should see subtitle {string}")
	public void user_see_subtitle(String subtitle) {
		boolean isSubtitleDisplay = SettingAdminThemeUtils.verifySubtitleIsDisplay(SetupHooks.getPage(), subtitle);
		Assertions.assertTrue(isSubtitleDisplay, "Subtitle is not displayed as expected.");
	}

	@And("User should see Select Theme dropdown")
	public void user_see_dropdown() {
		boolean isDropdownVisible = SettingAdminThemeUtils.verifyDropdownIsDisplay(SetupHooks.getPage());
		Assertions.assertTrue(isDropdownVisible, "Dropdown '" + "' is not visible.");
	}

	@And("User should see {string} input field")
	public void user_see_input_field(String inputFieldName) {
		boolean isInputFieldVisible = SettingAdminThemeUtils.verifyInputFieldIsDisplay(SetupHooks.getPage(), inputFieldName);
		Assertions.assertTrue(isInputFieldVisible, "Input field '" + inputFieldName + "' is not visible.");
	}

	@And("User should see {string} editor section")
	public void user_see_editor_section(String editorSectionName) {
		boolean isEditorSectionVisible = SettingAdminThemeUtils.verifyJsonEditorSectionIsDisplay(SetupHooks.getPage(), editorSectionName);
		Assertions.assertTrue(isEditorSectionVisible, "Editor section '" + editorSectionName + "' is not visible.");
	}

	@And("User should see {string} button")
	public void user_see_button(String buttonName) {
		boolean isButtonVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(SetupHooks.getPage(), buttonName);
		Assertions.assertTrue(isButtonVisible, "Button '" + buttonName + "' is not visible.");
	}

	@And("User should see {string} link")
	public void user_see_link(String linkName) {
		boolean isLinkVisible = SettingAdminThemeUtils.verifyLinkIsDisplay(SetupHooks.getPage(), linkName);
		Assertions.assertTrue(isLinkVisible, "Link '" + linkName + "' is not visible.");
	}

	@And("User should see {string} option")
	public void user_see_option(String optionName) {
		boolean isOptionVisible = SettingAdminThemeUtils.verifyAdminOptionIsDisplay(SetupHooks.getPage(), optionName);
		Assertions.assertTrue(isOptionVisible, "Option '" + optionName + "' is not visible in the dropdown.");
	}

	@And("User enters {string} name in the input field")
	public void user_enters_name_in_input_field(String themeName) {
		SettingAdminThemeUtils.enterThemeName(SetupHooks.getPage(), themeName);
	}

	@And("User click on {string} button in the Create Theme dialog")
	public void user_click_on_create_button(String buttonName) {
		SettingAdminThemeUtils.clickCreateButton(SetupHooks.getPage(), buttonName);
	}

	@And("User should see the Toast message {string}")
	public void user_should_see_toast_message(String expectedMessage) {
		boolean isToastVisible = SettingAdminThemeUtils.verifyToastMessage(SetupHooks.getPage(), expectedMessage);
		Assertions.assertTrue(isToastVisible, "Expected toast message '" + expectedMessage + "' is not visible.");
	}
}