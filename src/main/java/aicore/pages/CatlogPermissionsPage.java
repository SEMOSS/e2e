package aicore.pages;

import com.microsoft.playwright.Page;

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

	public boolean userCanSeeDeleteModelOption() {
		return CatlogAccessPageUtility.userCanSeeDeleteModel(page);
	}

	public boolean userCanSeeMember() {
		return CatlogAccessPageUtility.userCanSeeMember(page);
	}

	public boolean userCanSeePendingRequests() {
		return CatlogAccessPageUtility.UserCanSeePendingRequests(page);
	}

	public boolean userCanSeeDataApps() {
		return CatlogAccessPageUtility.userCanSeeDataApps(page);
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

	public boolean canSeeEditOtion() {
		return CatlogAccessPageUtility.canSeeEditOption(page);
	}

	public boolean canSeeSettingOtion() {
		return CatlogAccessPageUtility.canSeeSettingOption(page);
	}

	public String editorUserSeeToastMessageText() {
		return CatlogAccessPageUtility.editorUserSeeToastMessageText(page);
	}

	public boolean getCatalogAndCopyId() {
		return CatlogAccessPageUtility.getCatalogAndCopyId(page);
	}
}
