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
    
public void clickOnAddTeamButton(String button) {
        TeamPermissionsSettingsUtils.clickOnAddTeamButton(page, button);
    }

    public void selectMemberFromList(String member) {
        TeamPermissionsSettingsUtils.selectMemberFromList(page, member);
    }

    public void clickOnSaveMemberButton(String button) {
        TeamPermissionsSettingsUtils.clickOnAddMemberButton(page, button);
    }

    public void validateToastMessage(String expectedMessage) {
        TeamPermissionsSettingsUtils.validateToastMessage(page, expectedMessage);
    }

    public void checkMemberCard(String member) {
        TeamPermissionsSettingsUtils.checkMemberCard(page, member);
    }

    public void checkMemberInList(String member) {
        TeamPermissionsSettingsUtils.checkMemberInList(page, member);
    }

    public String verifyName(String name) {
        return TeamPermissionsSettingsUtils.verifyName(page, name + " " + timestamp);
    }


	public String validateDescription(String description) {
		return TeamPermissionsSettingsUtils.validateDescription(page, description + " " + timestamp);
	}	

}
