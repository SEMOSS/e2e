package aicore.utils.settings;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;

public class SettingsPermissionsPageUtils {
	private static final String CATALOG_SETTINGS_CARD_DATA_TESTID = "settingsIndexPage-{cardName}-card";
	private static final String CATALOG_SEARCHBOX_PLACEHOLDER="Search";
	private static final String CATALOG_CARD_XPATH="//p[text()='{catalogName}']";
	private static final String MEMBER_SEARCH_ICON_XPATH = "//h6[text()='Permissions']/parent::div/following-sibling::div//*[@data-testid='SearchIcon']";
	private static final String SEARCH_MEMBER_SEARCHBOX_XPATH = "//div[@data-testid='membersTables-searchMembers-searchBar}']//input";
	private static final String ACCESS_RADIO_BUTTON_XPATH="//input[@type='radio' and @value='{role}']";
	private static final String USER_DELETE_ICON_DATA_TESTID = "//tr[.//input[@type='radio' and @value='{role}' and @checked]]//*[@data-testid='DeleteIcon']";
    private static final String DELETE_USER_CONFIRM_BUTTON_XPATH="//span[text()='Confirm']";
    
	public static void selectCard(Page page, String cardName)
	{
		String card = cardName.replace(" ", "-");
		Locator cardLocator = page.getByTestId(CATALOG_SETTINGS_CARD_DATA_TESTID.replace("{cardName}", card));
		cardLocator.isVisible();
		cardLocator.click();
	}
	public static void searchCatalog(Page page, String catalogName)
	{
		page.getByPlaceholder(CATALOG_SEARCHBOX_PLACEHOLDER).nth(1).fill(catalogName);
	}
	public static void clickOnCatalogCard(Page page, String catalogName)
	{
		Locator catalogCardLocator = page.locator(CATALOG_CARD_XPATH.replace("{catalogName}", catalogName));
		AICorePageUtils.waitFor(catalogCardLocator);
		catalogCardLocator.click();
	}
	public static String getUserNameFromConfig(Page page, String userType)
	{
		String userName = "";
        switch (userType.toLowerCase()) {
            case "author":
                userName = ConfigUtils.getValue("author_username");
                break;
            case "editor":
                userName = ConfigUtils.getValue("editor_username");
                break;
            case "read":
                userName=ConfigUtils.getValue("read_username");
                break;
            default:
            	throw new IllegalArgumentException("Unknown user type: " + userType);
        }
        return userName;
	}
	public static boolean validateUserPermissions(Page page, String expectedRole ) {
        Locator accessRadioButton= page.locator(ACCESS_RADIO_BUTTON_XPATH.replace("{role}", expectedRole));
        boolean isSelected = accessRadioButton.isChecked()  ;
	        return isSelected;
	    }
	public static void searchUserInUserSearchbar(Page page, String userType)
	{    String userName = getUserNameFromConfig(page, userType);
		 Locator searchIcon = page.locator(MEMBER_SEARCH_ICON_XPATH);
	        if(searchIcon.isVisible())
	        {
	        	searchIcon.click();
	        }
	        Locator searchBox=page.locator(SEARCH_MEMBER_SEARCHBOX_XPATH);
	        searchBox.clear();
	        searchBox.fill(userName);
	        page.waitForTimeout(1000);
	}
    public static void selectUserRole(Page page, String role)
    {
    	Locator accessRadioButton= page.locator(ACCESS_RADIO_BUTTON_XPATH.replace("{role}", role));
    	accessRadioButton.click();
    	page.waitForTimeout(500);
    }
    public static void deleteUserFromMembersList(Page page, String role)
    {
    	Locator deleteIcon= page.locator(USER_DELETE_ICON_DATA_TESTID.replace("{role}", role));
    	deleteIcon.click();
    	Locator confirmDeleteButton=page.locator(DELETE_USER_CONFIRM_BUTTON_XPATH);
    	confirmDeleteButton.click();
    }
    public static boolean verifyUserIsExists(Page page, String role)
    {
    	Locator accessRadioButton= page.locator(ACCESS_RADIO_BUTTON_XPATH.replace("{role}", role));
    	return accessRadioButton.isVisible();
    }
}
