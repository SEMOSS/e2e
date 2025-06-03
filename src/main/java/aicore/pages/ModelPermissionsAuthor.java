package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.page.model.ModelPageUtils;

public class ModelPermissionsAuthor {

	private Page page;
	
	public ModelPermissionsAuthor(Page page) {
		this.page = page;
	}

	public boolean canViewOverview() {
		return ModelPageUtils.canViewOverview(page);
	}

	public boolean canViewUsage() {
		return ModelPageUtils.canViewUsage(page);
	}

	public boolean canViewSMSSDetails() {
		return ModelPageUtils.canViewSMSSDetails(page);
	}

	public boolean canViewEditSMSS() {
		return ModelPageUtils.canViewEditSMSS(page);
	}

	public boolean canViewAccessControl() {
		return ModelPageUtils.canViewAccessControl(page);
	}
}