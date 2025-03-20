package aicore.pages;

import com.microsoft.playwright.Page;

public class ModelPermissionsAuthor {

	private Page page;

	private static final String VIEW_OVERVIEW_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Overview']";
	private static final String VIEW_USAGE_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Usage']";
	private static final String VIEW_SMSS_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='SMSS']";
	private static final String VIEW_EDIT_SMSS_BUTTON_XPATH = "//span[text()='Edit SMSS']";
	private static final String VIEW_ACCESSCONTROL_TAB_XPATH = "//button[contains(@class, 'MuiTab-root') and text()='Access Control']";

	public ModelPermissionsAuthor(Page page) {
		this.page = page;
	}

	public boolean canViewOverview() {
		return page.isVisible(VIEW_OVERVIEW_TAB_XPATH);
	}

	public boolean canViewUsage() {
		return page.isVisible(VIEW_USAGE_TAB_XPATH);
	}

	public boolean canViewSMSSDetails() {
		return page.isVisible(VIEW_SMSS_TAB_XPATH);
	}

	public boolean canViewEditSMSS() {
		return page.isVisible(VIEW_EDIT_SMSS_BUTTON_XPATH);
	}

	public boolean canViewAccessControl() {
		return page.isVisible(VIEW_ACCESSCONTROL_TAB_XPATH);
	}
}