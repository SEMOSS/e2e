package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.SettingsPageUtils;

public class SettingsMyProfile {
	private Page page;
	private String timestamp;

	public SettingsMyProfile(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void openSettingsIcon() {
		SettingsPageUtils.openSettingsIcon(page);
	}

	public void clickOnMyProfileCard() {
		SettingsPageUtils.clickOnMyProfileCard(page);
	}

	public boolean isSectionVisible(String sectionText) {
		return SettingsPageUtils.isSectionVisible(page, sectionText);
	}

	public String verifyPrivacyCenter() {
		return SettingsPageUtils.verifyPrivacyCenter(page);
	}

	public void clickNewKeyButton() {
		SettingsPageUtils.clickNewKeyButton(page);
	}

	public void enterKeyName(String keyName) {
		SettingsPageUtils.enterKeyName(page, keyName, timestamp);
	}

	public void enterDescription(String description) {
		SettingsPageUtils.enterDescription(page, description, timestamp);
	}

	public void clickGenerateButton() {
		SettingsPageUtils.clickGenerateButton(page);
	}

	public boolean isAccessKeyPopupVisible() {
		return SettingsPageUtils.isAccessKeyPopupVisible(page);
	}

	public String copyAccessKey(String KeyName) {
		return SettingsPageUtils.copyAccessKey(page, KeyName);
	}

	public String extractExampleSectionContent(String sectionName) {
		return SettingsPageUtils.extractExampleSectionContent(page, sectionName);
	}

	public void clickOnCancelButton() {
		SettingsPageUtils.clickOnCancelButton(page);
	}

	public void clickOnDeleteIcon(String KeyName) {
		SettingsPageUtils.clickOnDeleteIcon(page, KeyName, timestamp);
	}

	public String deleteKeyToastMessage() {
		return SettingsPageUtils.deleteKeyToastMessage(page);
	}

	public String getExpectedAccessKeyTitle(String keyName) {
		return SettingsPageUtils.getExpectedAccessKeyTitle(page, keyName, timestamp);
	}

	public String validateGeneratedKey(String keyName) {
		return SettingsPageUtils.validateGeneratedKey(page, keyName, timestamp);
	}

	public String validateDescriptionName(String description) {
		return SettingsPageUtils.validateDescriptionName(page, description, timestamp);
	}

	public String getExpectedDescriptionName(String description) {
		return SettingsPageUtils.getExpectedDescriptionName(page, description, timestamp);
	}

}
