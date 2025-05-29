package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AbstractChangeAccessPopUpPageUtils;

public abstract class AbstractChangeAccessPopUpPage {

	protected Page page;

	public String getChangeAccessPopupTitle() {
		return AbstractChangeAccessPopUpPageUtils.isChangeAccessPopupVisible(page);
	}

	public boolean isPopupVisible() {
		return AbstractChangeAccessPopUpPageUtils.isPopupVisible(page);
	}

	public boolean isOptionVisible(String option) {
		return AbstractChangeAccessPopUpPageUtils.isOptionVisible(page, option);
	}

	public void selectAccessType(String accessType) {
		AbstractChangeAccessPopUpPageUtils.selectAccessType(page, accessType);
	}

	public void enterComment(String comment) {
		AbstractChangeAccessPopUpPageUtils.enterComment(page, comment);
	}

	public void clickOnRequestButton() {
		AbstractChangeAccessPopUpPageUtils.clickOnRequestButton(page);
	}

	public boolean isRequestSuccessToastVisible() {
		return AbstractChangeAccessPopUpPageUtils.isRequestSuccessToastVisible(page);
	}

}
