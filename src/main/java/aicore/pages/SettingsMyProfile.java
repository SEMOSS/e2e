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

	public String verifyPrivacyCenter() {
		return MyProfilePageUtils.verifyPrivacyCenter(page);
	}

	public boolean isAccessKeyPopupVisible() {
		return MyProfilePageUtils.isAccessKeyPopupVisible(page);
	}

	public void clickOnCancelButton() {
		MyProfilePageUtils.clickOnCancelButton(page);
	}

	public String deleteKeyToastMessage() {
		return MyProfilePageUtils.deleteKeyToastMessage(page);
	}

	public String getExpectedAccessKeyTitle(String keyName) {
		return keyName + timestamp;
	}

	public String getExpectedDescriptionName(String description) {
		return description + timestamp;
	}

	public boolean isFieldEnabled(String fieldName) {
		return MyProfilePageUtils.isFieldEnabled(page, fieldName);
	}

	public void updateField(String fieldName, String fieldValue) {
		MyProfilePageUtils.updateField(page, fieldName, fieldValue);
	}

	public boolean getUpdatedInfo(String name) {
		return MyProfilePageUtils.getUpdatedInfo(page, name);
	}

	public void clickOnChangePasswordLink() {
		MyProfilePageUtils.clickOnChangePasswordLink(page);
	}

	public boolean changePasswordTitle(String title) {
		return MyProfilePageUtils.changePasswordTitle(page, title);
	}

	public void clickOnProfileIcon() {
		MyProfilePageUtils.clickOnProfileIcon(page);
	}

	public void enterThePassword(String fieldName, String fieldValue) {
		MyProfilePageUtils.enterThePassword(page, fieldName, fieldValue);
	}

	public String getErrorMessage(String message) {
		return MyProfilePageUtils.getErrorMessage(page, message);
	}

}
