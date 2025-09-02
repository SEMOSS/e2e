package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.settings.MyProfilePageUtils;
import aicore.utils.settings.TeamPermissionsSettingsUtils;

public class TeamPermissionsSettingsPage {

    private Page page;
    private String timestamp;

    public TeamPermissionsSettingsPage(Page page, String timestamp) {
        this.page = page;
        this.timestamp = timestamp;
    }

    public void selectTypeFromDropdown(String type) {
        TeamPermissionsSettingsUtils.selectTypeFromDropdown(page, type);
    }

    public void fillTeamName(String value) {
        TeamPermissionsSettingsUtils.fillTeamName(page, value);
    }

    public void enterDescription(String description) {
        TeamPermissionsSettingsUtils.enterDescription(page, description);
    }

    public void clickOnAddButton(String button) {
        TeamPermissionsSettingsUtils.clickOnAddButton(page, button);
    }

    public String verifyName(String name) {
        return TeamPermissionsSettingsUtils.verifyName(page, name + " " + timestamp);
    }


	public String validateDescription(String description) {
		return TeamPermissionsSettingsUtils.validateDescription(page, description + " " + timestamp);
	}	

}
