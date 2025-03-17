package aicore.pages;

import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;

import aicore.utils.ConfigUtils;

public class OpenAppLibraryPage {

	private Page page;
	private String timestamp;

	private static final String CREATE_NEW_APP_BUTTON_XPATH = "//button[span[text()='Create New App']]";
	private static final String GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH = "//div[h6[text()='Drag and Drop']]/following-sibling::div/button[span[text()='Get Started']]";
	private static final String NAME_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiFormControl-fullWidth')]//label[text()='Name']";
	private static final String DESCRIPTION_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiTextField-root')]//label[text()='Description']";
	private static final String TAG_TEXTBOX_XPATH = "//input[contains(@placeholder,'to add tag') and @role='combobox']";
	private static final String CREATE_BUTTON_XPATH = "//button[span[text()='Create']]";
	private static final String PAGE1_XPATH = "//div[contains(@class,'flexlayout__tab_button flexlayout')]//div[@class='flexlayout__tab_button_content' and text()='page-1']";
	private static final String WELCOME_TEXT_BLOCK_XPATH = "//div[@id='page-1']//p[@data-block='welcome-text-block']";
	private static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	private static final String SEARCHED_APP_XPATH = "//a[contains(@class,'MuiTypography-root MuiTypography-inherit')]//p[text()='{appName}']";
	private static final String EDIT_BUTTON_XPATH = "//a[span[text()='Edit']]";
	private static final String BLOCKS_OPTION_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Blocks']";
	private static final String HEADING_1_BLOCK_XPATH = "//div[@aria-label='Display Text in header 1']";
	private static final String HEADING_BLOCK_HELLO_WORLD_XPATH = "//h1[text()='Hello world']";
	private static final String MENU_OPTION_XPATH = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart')]";
	private static final String APP_LOGO_ON_EDIT_PAGE_XPATH = "//h6[text()='{appName}']";

	public OpenAppLibraryPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnCreateNewAppButton() {
		page.click(CREATE_NEW_APP_BUTTON_XPATH);
	}

	public void clickOnGetStartedButtonInDragAndDrop() {
		page.click(GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH);
	}

	public void enterAppName(String appName) {
		page.fill(NAME_TEXTBOX_XPATH, appName + " " + timestamp);
	}

	public void enterAppDescription(String appDescription) {
		page.fill(DESCRIPTION_TEXTBOX_XPATH, appDescription);
	}

	public void enterTags(String tagName) {
		page.fill(TAG_TEXTBOX_XPATH, tagName);
		page.keyboard().press("Enter");
	}

	public void clickOnCreateButton() {
		page.click(CREATE_BUTTON_XPATH);
	}

	public boolean verifyPage1IsVisible() {
		page.waitForSelector(PAGE1_XPATH, new Page.WaitForSelectorOptions().setTimeout(20000));
		boolean isPage1Visible = page.isVisible(PAGE1_XPATH);
		return isPage1Visible;
	}

	public boolean verifyWelcomeTextboxIsVisible() {
		boolean isWelcomeTextboxVisible = page.isVisible(WELCOME_TEXT_BLOCK_XPATH);
		return isWelcomeTextboxVisible;
	}

	public String verifyWelcomeText() {
		String actualWelcomeText = page.textContent(WELCOME_TEXT_BLOCK_XPATH).trim();
		return actualWelcomeText;
	}

	public void navigateToPreviousPage() {
		String appNameWithLogo = ConfigUtils.getValue("applicationName");
		page.click(MENU_OPTION_XPATH);
		page.click(APP_LOGO_ON_EDIT_PAGE_XPATH.replace("{appName}", appNameWithLogo));
	}

	public void searchApp(String appName) {
		page.fill(APP_SEARCH_TEXTBOX_XPATH, appName + " " + timestamp);
	}

	public void clickOnSearchedApp(String appName) {
		page.click(SEARCHED_APP_XPATH.replace("{appName}", appName + " " + timestamp));
	}

	public void clickOnEditButton() {
		page.click(EDIT_BUTTON_XPATH);
	}

	public void clickOnBlocksOption() {
		page.click(BLOCKS_OPTION_XPATH);
	}

	public void dragHeading1BlockAndDropOnPage() {
		page.hover(HEADING_1_BLOCK_XPATH);
		page.mouse().down();
		BoundingBox targetBox = page.locator(WELCOME_TEXT_BLOCK_XPATH).boundingBox();
		double dropX = targetBox.x + (targetBox.width / 2); // Center X position
		double dropY = targetBox.y + targetBox.height + 10; // Below target (+10 for margin)
		page.mouse().move(dropX, dropY, new Mouse.MoveOptions().setSteps(30)); // Slow movement
		page.mouse().up();
	}

	public String verifyHeadingBlockTextMessage() {
		String headingBlockTextMessage = page.textContent(HEADING_BLOCK_HELLO_WORLD_XPATH).trim();
		return headingBlockTextMessage;
	}
}
