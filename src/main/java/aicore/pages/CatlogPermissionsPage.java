package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AICorePageUtils;
import aicore.utils.CatlogAccessPageUtility;

public class CatlogPermissionsPage {

	private Page page;

	public CatlogPermissionsPage(Page page) {
		this.page = page;
	}

	public boolean canViewOverview() {
		return CatlogAccessPageUtility.canViewOverview(page);
	}

	public boolean canViewUsage() {
		return CatlogAccessPageUtility.canViewUsage(page);
	}

	public boolean canViewSMSSDetails() {
		return CatlogAccessPageUtility.canViewSMSSDetails(page);
	}

	public boolean canViewEditSMSS() {
		return CatlogAccessPageUtility.canViewEditSMSS(page);
	}

	public boolean canViewAccessControl() {
		return CatlogAccessPageUtility.canViewAccessControl(page);
	}

	// new database catalog
	public boolean canViewMetadata() {
		return CatlogAccessPageUtility.canViewMetadata(page);
	}

	public void searchUserBasedOnRole(String role) {
		CatlogAccessPageUtility.searchUserBasedOnRole(page, role);
	}

	public boolean canViewExportOption() {
		return CatlogAccessPageUtility.canViewExportOption(page);

	}

	// create App Class file
	public void clickOnSettings() {
		CatlogAccessPageUtility.clickOnSettings(page);
	}

	public boolean userCanSeeDeleteCatalogOption() {
		return CatlogAccessPageUtility.userCanSeeDeleteCatalog(page);
	}

	public boolean userCanSeeMember() {
		return CatlogAccessPageUtility.userCanSeeMember(page);
	}

	public boolean userCanSeeGeneral() {
		return CatlogAccessPageUtility.UserCanSeeGeneral(page);
	}

	public boolean userCanSeeApps() {
		return CatlogAccessPageUtility.userCanSeeApps(page);
	}

	public boolean userCanSeeExportIcon() {
		return CatlogAccessPageUtility.userCanSeeExportIcon(page);
	}

	public boolean userCanSeeAndEnablePrivateToggle() {
		return CatlogAccessPageUtility.userCanSeeAndEnablePrivateToggle(page);
	}

	public void setToggleStateForPrivate() {
		CatlogAccessPageUtility.setToggleStateForPrivate(page);
	}

	public String getToasterMessage() {
		return CatlogAccessPageUtility.getToasterMessage(page);
	}

	public boolean userCanSeeAndEnableNonDiscovrableToggle() {
		return CatlogAccessPageUtility.userCanSeeAndEnableNonDiscoverableToggle(page);
	}

	public void setToggleStateForNonDiscovrable() {
		CatlogAccessPageUtility.setToggleStateForNonDiscovrable(page);
	}

	public boolean canSeeEditOtion(String action) {
		return CatlogAccessPageUtility.canSeeEditOption(page, action);
	}

	public boolean canSeeSettingOtion() {
		return CatlogAccessPageUtility.canSeeSettingOption(page);
	}

	public String editorUserSeeToastMessageText() {
		return CatlogAccessPageUtility.editorUserSeeToastMessageText(page);
	}

	public void getCatalogAndCopyId() {
		CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}

	public void closeToastMessage() {
		AICorePageUtils.closeToastMessage(page);
	}

	// as per new UI of setting page
	public void clickOnMemberSettingOption() {
		CatlogAccessPageUtility.clickOnMemberSettingOption(page);
	}

	public void clickOnGeneralSettingOption() {
		CatlogAccessPageUtility.clickOnGeneralSettingOption(page);
	}

	public void searchUserBasedOnRole(String role, boolean useDocker) {
		CatlogAccessPageUtility.searchUser(page, role, useDocker);
	}

	public void clickOnPendingRequestTab() {
		CatlogAccessPageUtility.clickOnPendingRequestTab(page);

	}

	public void acceptPendingRequest() {
		CatlogAccessPageUtility.acceptPendingRequest(page);
	}

	public void rejectPendingRequest() {
		CatlogAccessPageUtility.rejectPendingRequest(page);
	}
}
