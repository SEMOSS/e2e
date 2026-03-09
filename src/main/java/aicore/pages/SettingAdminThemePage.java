package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.settings.SettingAdminThemeUtils;

public class SettingAdminThemePage {
	private Page page;

	public SettingAdminThemePage(Page page) {
		this.page = page;
	}

	public boolean VerifyPageTitleIsDisplay(String titleName) {
		return SettingAdminThemeUtils.VerifyPageTitleIsDisplay(page, titleName);
	}

	public boolean VerifyBreadcrumbIsDisplay(String breadcrumbName) {
		return SettingAdminThemeUtils.VerifyBreadcrumbIsDisplay(page, breadcrumbName);
	}

	public boolean VerifySubtitleIsDisplay(String subtitle) {
		return SettingAdminThemeUtils.VerifySubtitleIsDisplay(page, subtitle);
	}

	public boolean VerifyDropdownIsDisplay() {
		return SettingAdminThemeUtils.VerifyDropdownIsDisplay(page);
	}

	public boolean VerifyInputFieldIsDisplay(String inputFieldName) {
		return SettingAdminThemeUtils.VerifyInputFieldIsDisplay(page, inputFieldName);
	}

	public boolean VerifyJsonEditorSectionIsDisplay(String editorSectionName) {
		return SettingAdminThemeUtils.VerifyJsonEditorSectionIsDisplay(page, editorSectionName);
	}

	public boolean VerifyButtonIsDisplay(String buttonName) {
		return SettingAdminThemeUtils.VerifyButtonIsDisplay(page, buttonName);
	}

	public boolean VerifyLinkIsDisplay(String linkName) {
		return SettingAdminThemeUtils.VerifyLinkIsDisplay(page, linkName);
	}

	public boolean VerifyAdminOptionIsDisplay(String optionName) {
		return SettingAdminThemeUtils.VerifyAdminOptionIsDisplay(page, optionName);
	}

	public void EnterThemeName(String themeName) {
		SettingAdminThemeUtils.EnterThemeName(page, themeName);
	}

	public void ClickCreateButton(String buttonName) {
		SettingAdminThemeUtils.ClickCreateButton(page, buttonName);
	}

	public boolean VerifyToastMessage(String expectedMessage) {
		return SettingAdminThemeUtils.VerifyToastMessage(page, expectedMessage);
	}
}