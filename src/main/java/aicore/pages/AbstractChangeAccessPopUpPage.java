package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.ChangeAccessPopUpPageUtils;

public abstract class AbstractChangeAccessPopUpPage {
	protected Page page;

	public String getChangeAccessPopupTitle() {
		return ChangeAccessPopUpPageUtils.isChangeAccessPopupVisible(page);
	}

	public boolean isPopupVisible() throws InterruptedException {
		return ChangeAccessPopUpPageUtils.isPopupVisible(page);
	}

	public boolean isOptionVisible(String option) {
		return ChangeAccessPopUpPageUtils.isOptionVisible(page, option);
	}

	public void selectAccessType(String accessType) {
		ChangeAccessPopUpPageUtils.selectAccessType(page, accessType);
	}

	public void enterComment(String comment) {
		ChangeAccessPopUpPageUtils.enterComment(page, comment);
	}

	public void clickOnRequestButton() {
		ChangeAccessPopUpPageUtils.clickOnRequestButton(page);
	}

	public boolean isRequestSuccessToastVisible() {
		return ChangeAccessPopUpPageUtils.isRequestSuccessToastVisible(page);
	}

}
