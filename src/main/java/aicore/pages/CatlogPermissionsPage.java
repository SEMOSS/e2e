package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.CatlogAccessPage;

public class CatlogPermissionsPage {

	private Page page;

	public CatlogPermissionsPage(Page page) {
		this.page = page;
	}

	public boolean canViewOverview() {
		return CatlogAccessPage.canViewOverview(page);
	}

	public boolean canViewUsage() {
		return CatlogAccessPage.canViewUsage(page);
	}

	public boolean canViewSMSSDetails() {
		return CatlogAccessPage.canViewSMSSDetails(page);
	}

	public boolean canViewEditSMSS() {
		return CatlogAccessPage.canViewEditSMSS(page);
	}

	public boolean canViewAccessControl() {
		return CatlogAccessPage.canViewAccessControl(page);
	}

	// new
	public boolean canViewMetadata() {
		return CatlogAccessPage.canViewMetadata(page);
	}

	public void searchUserBasedOnRole(String role) {
		CatlogAccessPage.searchUserBasedOnRole(page, role);
	}

	public boolean canViewExportOption() {
		return CatlogAccessPage.canViewExportOption(page);

	}

}
