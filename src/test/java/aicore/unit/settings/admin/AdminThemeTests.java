package aicore.unit.settings.admin;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.settings.SettingAdminThemeUtils;
import aicore.utils.settings.SettingsPageUtils;

public class AdminThemeTests extends AbstractPlaywrightTestBase {

	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
		SettingsPageUtils.clickOnCard(page, "Admin Theme");
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@Test
	void testPageLoad(@PWPage Page page) {
		boolean isTitleDisplay = SettingAdminThemeUtils.verifyPageTitleIsDisplay(page, "Admin Theme");
		Assertions.assertTrue(isTitleDisplay, "Page title is not displayed as expected.");

		boolean isBreadcrumbDisplay = SettingAdminThemeUtils.verifyBreadcrumbIsDisplay(page, "Admin Theme");
		Assertions.assertTrue(isBreadcrumbDisplay, "Breadcrumb is not displayed as expected.");

		String optionName = "Admin On";
		boolean isOptionVisible = SettingAdminThemeUtils.verifyAdminOptionIsDisplay(page, optionName);
		Assertions.assertTrue(isOptionVisible, "Option '" + optionName + "' is not visible in the dropdown.");

		boolean isSubtitleDisplay = SettingAdminThemeUtils.verifySubtitleIsDisplay(page, "Update theming for the instance");
		Assertions.assertTrue(isSubtitleDisplay, "Subtitle is not displayed as expected.");

		boolean isDropdownVisible = SettingAdminThemeUtils.verifyDropdownIsDisplay(page);
		Assertions.assertTrue(isDropdownVisible, "Dropdown '" + "' is not visible.");

		String inputFieldName = "Name";
		boolean isInputFieldVisible = SettingAdminThemeUtils.verifyInputFieldIsDisplay(page, inputFieldName);
		Assertions.assertTrue(isInputFieldVisible, "Input field '" + inputFieldName + "' is not visible.");

		String editorSectionName = "JSON";
		boolean isEditorSectionVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, editorSectionName);
		Assertions.assertTrue(isEditorSectionVisible, "Editor section '" + editorSectionName + "' is not visible.");

		editorSectionName = "Client";
		isEditorSectionVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, editorSectionName);
		Assertions.assertTrue(isEditorSectionVisible, "Editor section '" + editorSectionName + "' is not visible.");

		editorSectionName = "Playground";
		isEditorSectionVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, editorSectionName);
		Assertions.assertTrue(isEditorSectionVisible, "Editor section '" + editorSectionName + "' is not visible.");

		editorSectionName = "BI";
		isEditorSectionVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, editorSectionName);
		Assertions.assertTrue(isEditorSectionVisible, "Editor section '" + editorSectionName + "' is not visible.");

		String buttonName = "Save";
		boolean isButtonVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, buttonName);
		Assertions.assertTrue(isButtonVisible, "Button '" + buttonName + "' is not visible.");

		buttonName = "Activate";
		isButtonVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, buttonName);
		Assertions.assertTrue(isButtonVisible, "Button '" + buttonName + "' is not visible.");

		buttonName = "Delete";
		isButtonVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, buttonName);
		Assertions.assertTrue(isButtonVisible, "Button '" + buttonName + "' is not visible.");

		buttonName = "New Theme";
		isButtonVisible = SettingAdminThemeUtils.verifyButtonIsDisplay(page, buttonName);
		Assertions.assertTrue(isButtonVisible, "Button '" + buttonName + "' is not visible.");
	}
	
	@Test
	void testCreateAdminTheme(@PWPage Page page) {
		String buttonName = "New Theme";
		StoragePageUtils.clickOnButton(page, buttonName);

		String themeName = "Test Theme " + CommonUtils.getTimeStampName();
		SettingAdminThemeUtils.enterThemeName(page, themeName);

		buttonName = "Create";
		SettingAdminThemeUtils.clickCreateButton(page, buttonName);

		String expectedMessage = "Theme created successfully";
		boolean isToastVisible = SettingAdminThemeUtils.verifyToastMessage(page, expectedMessage);
		Assertions.assertTrue(isToastVisible, "Expected toast message '" + expectedMessage + "' is not visible.");

		// delete theme
		buttonName = "Delete";
		StoragePageUtils.clickOnButton(page, buttonName);

		expectedMessage = "Theme deleted";
		isToastVisible = SettingAdminThemeUtils.verifyToastMessage(page, expectedMessage);
		Assertions.assertTrue(isToastVisible, "Expected toast message '" + expectedMessage + "' is not visible.");

	}

}
