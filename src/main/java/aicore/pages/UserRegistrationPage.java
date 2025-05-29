package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.UserRegistrationPageUtils;

public class UserRegistrationPage {
	private Page page;

	public UserRegistrationPage(Page page) {
		this.page = page;
	}

	public void clickOnRegisterNowButton() {
		UserRegistrationPageUtils.clickOnRegisterNowButton(page);
	}

	public void fillUserRegistartionFormField(String fieldName, String fieldValue) {
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, fieldName, fieldValue);
	}

	public void clickOnRegisterButton() {
		UserRegistrationPageUtils.clickOnRegisterButton(page);
	}

	public String verifyRegitrationSuccessMessage(String messageText) {
		return UserRegistrationPageUtils.verifyRegitrationSuccessMessage(page, messageText);
	}

}
