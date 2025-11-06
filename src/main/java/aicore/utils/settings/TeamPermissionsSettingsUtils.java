package aicore.utils.settings;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.utils.AICorePageUtils;
import aicore.utils.LastCreatedUser;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.model.ModelPageUtils;

public class TeamPermissionsSettingsUtils {

	private static final String SELECT_TYPE_DROPDOWN_XPATH = "//legend/span[text()='Type*']/ancestor::div[contains(@class,'MuiInputBase-root')]//div[@role='combobox']";
	private static final String TEAM_NAME_XPATH = "//legend/span[text()='Name*']/ancestor::div[contains(@class,'MuiInputBase-root')]//input";
	private static final String DESCRIPTION_XPATH = "//label[text()='Description']/parent::div/child::div//textarea";
	private static final String ADD_BUTTON_XPATH = "//button[.//span[normalize-space()='{buttonName}']]";
	private static final String TEAM_BUTTON_XPATH = "//button//span[contains(text(), 'Add Members')]";
	private static final String LIST_MEMBER_XPATH = "//*[text()='{Member}']"; //// *[contains(text(), '{Member}')]";
	private static final String MEMBER_CARD_XPATH = "//span[contains(text(),'User ID:')]/div//span";
	private static final String LIST_DROPDOWN = "ArrowDropDownIcon";
	private static final String TOAST_MESSAGE_XPATH = "//div[contains(@class,'MuiAlert-message')]";
	private static final String MEMBER_XPATH = "//div//p[contains(text(),'NATIVE ID: {member}')]";
	private static final String NAME_XPATH = "//p[normalize-space()='{Name}']";
	private static final String GENERATED_DESCRIPTION_XPATH = "//p[normalize-space()='{description}']";
	private static final String SELECT_ENGINE_ROLE_XPATH = "//input[@value='{role}']";
	private static final String SELELCT_THE_ENGINE_DROPDOWN_XPATH = "//label[text()='{selectCatalog}']";
	private static final String CLICK_ON_ADD_CATALOG_TEXT = "{addCatalogName}";
	private static final String ENGINE_ID_XPATH = "//p[contains(text(),'{EngineId}')]";
	private static final String RADIO_XPATH = "//input[@type='radio' and @value='{radioIndex}']";
	private static final String CLICK_ON_DELETE_ICON_DATATESTID = "DeleteRoundedIcon";
	private static final String CLICK_ON_CONFIRM_BUTTON_XPATH = "//span[text()='{confirm}']";
	private static final String CHECK_THE_CHECKBOX_TO_SELECT_ALL_MEMBER_XPATH = "//th//input[@type='checkbox']";
	private static final String DELETE_ICON_DATATESTID = "DeleteIcon";
	private static final String PAGE_NUMBER_XPATH = "//button[@title='Go to previous page']/../..//p";
	private static final String PREV_BUTTON_XPATH = "//button[@title='Go to previous page']";
	private static final String NEXT_BUTTON_XPATH = "//button[@title='Go to next page']";
	private static final String HEADINGS_XPATH = "//h2[text()='Add Members']";

	final static int ROWS_PER_PAGE = 5;
	
	public static void selectTypeFromDropdown(Page page, String type) {
		Locator selectTypeFromDropdown = page.locator(SELECT_TYPE_DROPDOWN_XPATH);
		AICorePageUtils.waitFor(selectTypeFromDropdown);
		selectTypeFromDropdown.click();
		page.waitForTimeout(300);
		page.getByText(type).click();
	}

	public static void fillTeamName(Page page, String value) {
		page.locator(TEAM_NAME_XPATH).isVisible();
		page.locator(TEAM_NAME_XPATH).fill(value);
	}

	public static void enterDescription(Page page, String description) {
		page.locator(DESCRIPTION_XPATH).first().isVisible();
		page.locator(DESCRIPTION_XPATH).first().fill(description);
	}

	public static void clickOnAddButton(Page page, String button) {
		page.click(ADD_BUTTON_XPATH.replace("{buttonName}", button));
		page.reload(); // added because of stale element
	}

	public static void clickOnAddMemberButton(Page page, String button) {
		page.click(ADD_BUTTON_XPATH.replace("{buttonName}", button));
	}

	public static void clickOnAddTeamButton(Page page, String button) {
		page.click(TEAM_BUTTON_XPATH.replace("{buttonName}", button));
	}

	public static void selectMemberFromList(Page page, String role) {
		String username = LastCreatedUser.getName();
		// ConfigUtils.getValue(role.toLowerCase() + "_username").split("@")[0];
		Locator dropdownLocator = page.getByTestId(LIST_DROPDOWN);
		AICorePageUtils.waitFor(dropdownLocator);
		dropdownLocator.click();
		Locator listMember = page.locator(LIST_MEMBER_XPATH.replace("{Member}", username));
		AICorePageUtils.waitFor(listMember);
		listMember.click();
	}

	public static void validateToastMessage(Page page, String expectedMessage) {
		Locator toastMessage = page.locator(TOAST_MESSAGE_XPATH);
		AICorePageUtils.waitFor(toastMessage);
		String actualMessage = toastMessage.textContent().trim();
		if (!actualMessage.equals(expectedMessage)) {
			throw new AssertionError("Expected toast message: " + expectedMessage + ", but got: " + actualMessage);
		}
	}

	public static void checkMemberCard(Page page, String role) {
		String username = LastCreatedUser.getUserId();
		// ConfigUtils.getValue(role.toLowerCase() + "_username").split("@")[0];
		Locator memberCard = page.locator(MEMBER_CARD_XPATH);
		AICorePageUtils.waitFor(memberCard);
		String actualMember = memberCard.textContent().trim();
		if (!actualMember.equals(username)) {
			throw new AssertionError("Expected member card: " + username + ", but got: " + actualMember);
		}
	}

	public static boolean checkMemberInList(Page page, String member) {
		Locator memberCard = page.locator(MEMBER_XPATH.replace("{member}", member));
		return memberCard.isVisible();
	}

	public static String verifyName(Page page, String name) {
		Locator actualName = page.locator(NAME_XPATH.replace("{Name}", name));
		AICorePageUtils.waitFor(actualName);
		actualName.scrollIntoViewIfNeeded();
		return actualName.textContent().trim();
	}

	public static String validateDescription(Page page, String description) {
		Locator actualDescription = page.locator(GENERATED_DESCRIPTION_XPATH.replace("{description}", description));
		AICorePageUtils.waitFor(actualDescription);
		actualDescription.scrollIntoViewIfNeeded();
		return actualDescription.textContent().trim();
	}

	// add engine to all catalog with different
	public static void userClickOnCreatedTeamName(Page page, String teamName, String timestamp) {
		page.getByText(teamName + " " + timestamp).click();
	}

	public static void userClickOnAddEngineButton(Page page, String addCatalogName) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
				.setName(CLICK_ON_ADD_CATALOG_TEXT.replace("{addCatalogName}", addCatalogName))).click();
	}

	public static void userSelectEngineFromList(Page page, String catalogName, String timestamp, String selectCatalog, String catalogType) {
		String catalogId = TestResourceTrackerHelper.getInstance().getCatalogId(catalogType);
		Locator dropdownLocator = page.locator(SELELCT_THE_ENGINE_DROPDOWN_XPATH.replace("{selectCatalog}", selectCatalog));
		dropdownLocator.press("Enter");
		page.keyboard().type(catalogId);
		AICorePageUtils.waitFor(dropdownLocator);
		page.keyboard().press("ArrowDown");
		page.keyboard().press("Enter");

	}

	public static void userSelectAppFromList(Page page, String catalogName, String selectCatalog) {
		Locator dropdownLocator = page
				.locator(SELELCT_THE_ENGINE_DROPDOWN_XPATH.replace("{selectCatalog}", selectCatalog));
		dropdownLocator.press("Enter");
		dropdownLocator.fill(catalogName);
		AICorePageUtils.waitFor(dropdownLocator);
		page.getByText(catalogName).click();
	}

	public static void userSelectEngineAccessRole(Page page, String role) {
		page.locator(SELECT_ENGINE_ROLE_XPATH.replace("{role}", role)).check();
	}

	public static boolean userSeeAddedEngineInTheList(Page page, String catalogName, String role) {
		//Locator EngineSearchBar = page.locator(ENGINE_SEARCH_XPATH);
		// EngineSearchBar.click();
		// page.keyboard().press("Control+V");		
		String copiedId = (String) page.evaluate("() => navigator.clipboard.readText()");
		page.locator(ENGINE_ID_XPATH.replace("{EngineId}", catalogName)).isVisible();
		boolean EnginePresent = false;
		//check role should be checked 
		switch (role) {
			case "Author":
				EnginePresent = page.locator(RADIO_XPATH.replace("{radioIndex}", "1")).isChecked();
				break;
			case "Editor":
				EnginePresent = page.locator(RADIO_XPATH.replace("{radioIndex}", "2")).isChecked();
				break;
			case "Read-Only":
				EnginePresent = page.locator(RADIO_XPATH.replace("{radioIndex}", "3")).isChecked();
				break;
			default:
				EnginePresent = false;
				break;
		}
		return EnginePresent;
	}

	// delete team member
	public static void userClickOnDeleteIcon(Page page, String icon, String member) {
		Locator searchmember = page.getByPlaceholder("Search Members");
		searchmember.fill(member);
		AICorePageUtils.waitFor(searchmember);
		page.getByTestId(CLICK_ON_DELETE_ICON_DATATESTID).click();

	}

	public static void userClickOnDeleteConfirmButton(Page page, String button) {
		page.locator(CLICK_ON_CONFIRM_BUTTON_XPATH.replace("{confirm}", button)).click();
	}

	public static void selectMultipleMembersFromList(Page page, String member1, String member2) {
		Locator dropdownLocator = page.getByTestId(LIST_DROPDOWN);
		AICorePageUtils.waitFor(dropdownLocator);
		dropdownLocator.click();
		Locator listMember1 = page.locator(LIST_MEMBER_XPATH.replace("{Member}", member1));
		AICorePageUtils.waitFor(listMember1);
		listMember1.click();
		dropdownLocator.click();
		Locator listMember2 = page.locator(LIST_MEMBER_XPATH.replace("{Member}", member2));
		AICorePageUtils.waitFor(listMember2);
		listMember2.click();

	}

	public static void userSelectAllMember(Page page) {
		Locator selelctAllMember = page.locator(CHECK_THE_CHECKBOX_TO_SELECT_ALL_MEMBER_XPATH);
		AICorePageUtils.waitFor(selelctAllMember);
		selelctAllMember.check();
	}

	public static void userClickOnOption(Page page, String option) {
		page.getByText("Delete Selected").click();
	}

	public static void userSearchMemberName(Page page, String member) {
		Locator searchmember = page.getByPlaceholder("Search Members");
		AICorePageUtils.waitFor(searchmember);
		searchmember.fill(member);
	}

	
	public static void clickOnDeleteButton(Page page) {
		page.getByTestId(DELETE_ICON_DATATESTID).isVisible();
		page.getByTestId(DELETE_ICON_DATATESTID).click();
		page.getByText("Confirm").isVisible();
		page.getByText("Confirm").click();

	}

	public static void addmultipleMembers(Page page, String members) {
		Locator dropdownLocator = page.getByTestId(LIST_DROPDOWN);
		AICorePageUtils.waitFor(dropdownLocator);
		dropdownLocator.click();
		Locator Memberlist = page.getByText(members);
		int count = Memberlist.count();
		for (int i = 1; i <= count; i++) {
			Locator listMember = page.locator(LIST_MEMBER_XPATH.replace("{Member}", members+i));
			AICorePageUtils.waitFor(listMember);
			listMember.click();
			dropdownLocator.click();
		}
		dropdownLocator.click();
		page.locator(HEADINGS_XPATH).click();
	}
	
	public static int calculateTotalPages(Page page) {
    // Get the pagination text (e.g., "1-5 of 13")
    Locator paginationText = page.locator(PAGE_NUMBER_XPATH);
    String text = paginationText.textContent().trim();
    
    // Split the text to get "13" (total members)
    String[] parts = text.split(" ");
    String totalText = parts[2]; // "13" from "1-5 of 13"
    int totalMembers = Integer.parseInt(totalText);
    
    // Maximum rows per page is 5
    
    
    // Calculate total pages (ceil division to round up)
    int totalPages = (int) Math.ceil((double) totalMembers / ROWS_PER_PAGE);
    
    return totalPages;
}

public static boolean verifyPagination(Page page) {
    int totalPages = calculateTotalPages(page);
  	Locator nextButton = page.locator(NEXT_BUTTON_XPATH);
    Locator prevButton = page.locator(PREV_BUTTON_XPATH);
	Locator paginationText = page.locator(PAGE_NUMBER_XPATH);
	String paginationInfo = paginationText.textContent().trim();
	String[] parts = paginationInfo.split(" of ");
		
   
    // Check forward navigation
    int forwardCount = 0;
	String start = "1";
    String end = "5";
    String filter = start + "-" + end;
    while (nextButton.isEnabled()) {
        if (Integer.parseInt(start) > Integer.parseInt(parts[1]) 
            && Integer.parseInt(end) > Integer.parseInt(parts[1])) {

            start = String.valueOf(Integer.parseInt(start) + ROWS_PER_PAGE);
            end = String.valueOf(Integer.parseInt(end) + ROWS_PER_PAGE);
            filter = start + "-" + end;

            if (!filter.equals(parts[0])) {
                throw new AssertionError("Pagination forward navigation failed at page: " + parts[0]);
            }

        } else if (Integer.parseInt(start) < Integer.parseInt(parts[1]) 
                   && Integer.parseInt(end) >= Integer.parseInt(parts[1])) {

            start = String.valueOf(Integer.parseInt(start) + ROWS_PER_PAGE);
            end = String.valueOf(Integer.parseInt(end) + ROWS_PER_PAGE);
            filter = start + "-" + end;

            if (!filter.equals(parts[0])) {
                throw new AssertionError("Pagination forward navigation failed at page: " + parts[0]);
            }
        }

        nextButton.click();
        forwardCount++;
    }
    // Check backward navigation
    int backwardCount = 0;
    while (prevButton.isEnabled()) {
		  if (Integer.parseInt(start) > Integer.parseInt(parts[1]) 
            && Integer.parseInt(end) > Integer.parseInt(parts[1])) {

            start = String.valueOf(Integer.parseInt(start) + ROWS_PER_PAGE);
            end = String.valueOf(Integer.parseInt(end) + ROWS_PER_PAGE);
            filter = start + "-" + end;

            if (!filter.equals(parts[0])) {
                throw new AssertionError("Pagination forward navigation failed at page: " + parts[0]);
            }

        } else if (Integer.parseInt(start) < Integer.parseInt(parts[1]) 
                   && Integer.parseInt(end) >= Integer.parseInt(parts[1])) {

            start = String.valueOf(Integer.parseInt(start) + ROWS_PER_PAGE);
            end = String.valueOf(Integer.parseInt(end) + ROWS_PER_PAGE);
            filter = start + "-" + end;

            if (!filter.equals(parts[0])) {
                throw new AssertionError("Pagination forward navigation failed at page: " + parts[0]);
            }
        }

        prevButton.click();
		
        backwardCount++;
    }
    
    // Verify that we navigated through all pages in both directions
    return (forwardCount == totalPages - 1) && (backwardCount == totalPages - 1);
}

	public static List<String> ids = ModelPageUtils.createdModelIds;
	public static void addmultipleEngines(Page page) {
				for (String engineId : ids) {
					Locator dropdownLocator = page.getByTestId(LIST_DROPDOWN);
					dropdownLocator.click();
					page.keyboard().type(engineId.trim());
					AICorePageUtils.waitFor(dropdownLocator);
					page.keyboard().press("ArrowDown");
					page.keyboard().press("Enter");
				}
		}
		public static List<String> projectName = CreateAppPopupUtils.createdAppNames;
		public static void addMultipleProjects(Page page) {
			for (String projectId : projectName) {
				Locator dropdownLocator = page.getByTestId(LIST_DROPDOWN);
				dropdownLocator.click();
				page.keyboard().type(projectId.trim());
				AICorePageUtils.waitFor(dropdownLocator);
				page.keyboard().press("ArrowDown");
				page.keyboard().press("Enter");
			}
		}
	
}
