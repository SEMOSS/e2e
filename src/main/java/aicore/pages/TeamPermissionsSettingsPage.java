package aicore.pages;

import com.microsoft.playwright.Page;

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

	public boolean checkMemberInList(String member) {
		return TeamPermissionsSettingsUtils.checkMemberInList(page, member);
	}
	
	public String verifyName(String name) {
		return TeamPermissionsSettingsUtils.verifyName(page, name + " " + timestamp);
	}

	public String validateDescription(String description) {
		return TeamPermissionsSettingsUtils.validateDescription(page, description + " " + timestamp);
	}

	// add engine to all catalog with different roles
	public void userClickOnCreatedTeamName(String teamName, String timestamp) {
		TeamPermissionsSettingsUtils.userClickOnCreatedTeamName(page, teamName, timestamp);
	}

	public void userClickOnAddEngineButton(String addCatalogName) {
		TeamPermissionsSettingsUtils.userClickOnAddEngineButton(page, addCatalogName);
	}

	public void userSelectEngineFromList(String catalogName, String timestamp, String selectCatalog,
			String catlogType) {
		TeamPermissionsSettingsUtils.userSelectEngineFromList(page, catalogName, timestamp, selectCatalog, catlogType);
	}
	public void userSelectAppFromList(String catalogType, String selectCatalog) {
		TeamPermissionsSettingsUtils.userSelectAppFromList(page, catalogType, selectCatalog, catalogType, timestamp);
	}
	

	public boolean userSeeAddedEngineInTheList(String catalogName, String role) {
		return TeamPermissionsSettingsUtils.userSeeAddedEngineInTheList(page, catalogName, role);
	}

	public void userSelectEngineAccessRole(String role) {
		TeamPermissionsSettingsUtils.userSelectEngineAccessRole(page, role);
	}

	//delete team member
	public void userClickOnDeleteIcon(String icon, String member) {
		TeamPermissionsSettingsUtils.userClickOnDeleteIcon(page, icon, member);
	}
	public void userClickOnDeleteConfirmButton(String button) {
		TeamPermissionsSettingsUtils.userClickOnDeleteConfirmButton(page, button);
	}

	public void selectMultipleMembersFromList(String member1, String member2) {
		TeamPermissionsSettingsUtils.selectMultipleMembersFromList(page, member1, member2);
	}
	public void userSelectAllMember() {
		TeamPermissionsSettingsUtils.userSelectAllMember(page);
	}
	public void userClickOnOption(String option) {
		TeamPermissionsSettingsUtils.userClickOnOption(page, option);
	}
	public void userSearchMemberName(String member) {
		TeamPermissionsSettingsUtils.userSearchMemberName(page, member);
	}

}
