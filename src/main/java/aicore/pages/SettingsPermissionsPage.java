package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.settings.SettingsPermissionsPageUtils;

public class SettingsPermissionsPage {
	
    private Page page;
    
	public SettingsPermissionsPage(Page page)
	{
		this.page=page;
	}
	public void selectCard(String cardName)
	{
		SettingsPermissionsPageUtils.selectCard(page, cardName);
	}
	public void searchCatalog(String catalogName)
	{
		SettingsPermissionsPageUtils.searchCatalog(page, catalogName);
	}
	public void clickOnCatalogCard(String catalogName )
	{
		SettingsPermissionsPageUtils.clickOnCatalogCard(page, catalogName);
	}
	public boolean validateUserPermissions(String expectedRole)
	{
		return SettingsPermissionsPageUtils.validateUserPermissions(page, expectedRole);
	}
	public void selectUserRole(String role)
	{
		SettingsPermissionsPageUtils.selectUserRole(page, role);
	}
	public void searchUserInUserSearchbar(String userType)
	{
		SettingsPermissionsPageUtils.searchUserInUserSearchbar(page, userType);	
	}
	public void deleteUserFromMembersList(String role)
	{
		SettingsPermissionsPageUtils.deleteUserFromMembersList(page, role);	
	}
	public boolean verifyUserIsExists(String role)
	{
		return SettingsPermissionsPageUtils.verifyUserIsExists(page, role);	
	}
}
