package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.settings.MyProfilePageUtils;

public class SettingsMyProfile {
	private Page page;
	private String timestamp;

	public SettingsMyProfile(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnMyProfileCard() {
		MyProfilePageUtils.clickOnMyProfileCard(page);
	}

	public boolean isSectionVisible(String sectionText) {
		return MyProfilePageUtils.isSectionVisible(page, sectionText);
	}

	public String verifyPrivacyCenter() {
		return MyProfilePageUtils.verifyPrivacyCenter(page);
	}

	public void clickNewKeyButton() {
		MyProfilePageUtils.clickNewKeyButton(page);
	}

	public void enterKeyName(String keyName) {
		MyProfilePageUtils.enterKeyName(page, keyName, timestamp);
	}

	public void enterDescription(String description) {
		MyProfilePageUtils.enterDescription(page, description, timestamp);
	}

	public void clickGenerateButton() {
		MyProfilePageUtils.clickGenerateButton(page);
	}

	public boolean isAccessKeyPopupVisible() {
		return MyProfilePageUtils.isAccessKeyPopupVisible(page);
	}

	public String copyAccessKey(String KeyName) {
		return MyProfilePageUtils.copyAccessKey(page, KeyName);
	}

	public String extractExampleSectionContent(String sectionName) {
		return MyProfilePageUtils.extractExampleSectionContent(page, sectionName);
	}

	public void clickOnCancelButton() {
		MyProfilePageUtils.clickOnCancelButton(page);
	}

	public void clickOnDeleteIcon(String KeyName) {
		MyProfilePageUtils.clickOnDeleteIcon(page, KeyName, timestamp);
	}

	public String deleteKeyToastMessage() {
		return MyProfilePageUtils.deleteKeyToastMessage(page);
	}

	public String getExpectedAccessKeyTitle(String keyName) {
		return MyProfilePageUtils.getExpectedAccessKeyTitle(page, keyName, timestamp);
	}

	public String validateGeneratedKey(String keyName) {
		return MyProfilePageUtils.validateGeneratedKey(page, keyName, timestamp);
	}

	public String validateDescriptionName(String description) {
		return MyProfilePageUtils.validateDescriptionName(page, description, timestamp);
	}

	public String getExpectedDescriptionName(String description) {
		return MyProfilePageUtils.getExpectedDescriptionName(page, description, timestamp);
	}

}
