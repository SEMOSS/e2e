package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.ConfigUtils;
import aicore.utils.UrlUtils;

public class OpenAppLibraryPage {

	private Page page;
	private String timestamp;<<<<<<<HEAD

	=======>>>>>>>origin/main
	private static final String OPEN_APP_LIBRARY_XPATH = "//a[@data-tour='nav-app-library']";
	private static final String CREATE_NEW_APP_BUTTON_XPATH = "//button[span[text()='Create New App']]";
	private static final String GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH = "//div[h6[text()='Drag and Drop']]/following-sibling::div/button[span[text()='Get Started']]";
	private static final String NAME_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiFormControl-fullWidth')]//label[text()='Name']";
	private static final String DESCRIPTION_TEXTBOX_XPATH = "//div[contains(@class,'MuiFormControl-root MuiTextField-root')]//label[text()='Description']";
	private static final String TAG_TEXTBOX_XPATH = "//input[contains(@placeholder,'to add tag') and @role='combobox']";
	private static final String CREATE_BUTTON_XPATH = "//button[span[text()='Create']]";
	private static final String PAGE_1_ID = "#page-1";
	private static final String PAGE_SELECTION_XPATH = "//div[@class='flexlayout__tab_button_content' and text()='{pageName}']";
	private static final String WELCOME_TEXT_BLOCK_XPATH = "//div[@id='page-1']//p[@data-block='welcome-text-block']";
	private static final String APP_SEARCH_TEXTBOX_XPATH = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input ') and @placeholder='Search']";
	private static final String SEARCHED_APP_XPATH = "//a[contains(@class,'MuiTypography-root MuiTypography-inherit')]//p[text()='{appName}']";
	private static final String EDIT_BUTTON_XPATH = "//a[span[text()='Edit']]";
	// Blocks section
	private static final String BLOCKS_OPTION_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Blocks']/parent::div";
	private static final String LINK_BLOCK_XPATH = "//div[@aria-label='Access a webpage through a clickable URL']";
	private static final String HEADING_1_BLOCK_XPATH = "//div[@aria-label='Display Text in header 1']";
	private static final String HEADING_2_BLOCK_XPATH = "//div[@aria-label='Display Text in header 2']";
	private static final String HEADING_3_BLOCK_XPATH = "//div[@aria-label='Display Text in header 3']";
	private static final String HEADING_4_BLOCK_XPATH = "//div[@aria-label='Display Text in header 4']";
	private static final String HEADING_5_BLOCK_XPATH = "//div[@aria-label='Display Text in header 5']";
	private static final String HEADING_6_BLOCK_XPATH = "//div[@aria-label='Display Text in header 6']";
	private static final String TEXT_BLOCK_XPATH = "//div[@aria-label='Show text in a regular paragraph style']";<<<<<<<HEAD
	private static final String LOGS_BLOCK_XPATH = "//div[@aria-label='Show logs from the notebook']";=======>>>>>>>origin/main
	private static final String MARKDOWN_BLOCK_XPATH = "//div[@aria-label='Show text in markdown format']";
	private static final String HEADING_BLOCK_HELLO_WORLD_XPATH = "//h1[text()='Hello world']";

	private static final String MENU_OPTION_XPATH = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart')]";
	private static final String MENU_CLOSED_ICON_XPATH = "//button[@aria-label='menu']//*[local-name()='svg' and @data-testid='MenuIcon']";
	private static final String APP_LOGO_ON_EDIT_PAGE_XPATH = "//h6[text()='{appName}']";<<<<<<<HEAD

	=======
	private static final String LINK_BLOCK_XPATH = "//div[@aria-label='Access a webpage through a clickable URL']";>>>>>>>origin/main
	// Block settings for Text elements
	private static final String BLOCK_SETTINGS_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Block Settings']/parent::div";
	private static final String DESTINATION_TEXTBOX_XPATH = "//p[text()='Destination']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String TEXT_TEXTBOX_XPATH = "//p[text()='Text']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String FONT_LIST_XPATH = "//p[text()='Font']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String COLOR_BOX_XPATH = "//input[@type='color']";
	private static final String MARKDOWN_TEXTBOX_XPATH = "//p[text()='Markdown']/parent::div/following-sibling::div//div[contains(@class,'MuiInputBase-root')]//input[@type='text']";
	private static final String SAVE_APP_BUTTON_NAME = "Save App (ctrl + s)";
//Notebook section
	private static final String NOTEBOOK_OPTION_XPATH = "//div[@class='flexlayout__border_button_content' and text()='Notebooks']";
	private static final String CREATE_NEW_NOTEBOOK_DATA_TESTID = "NoteAddOutlinedIcon";
	private static final String QUERY_SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";
	private static final String NOTEBOOK_QUERY_ID_LABEL = "Id";

	public OpenAppLibraryPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void clickOnCreateNewAppButton() {
		page.locator(CREATE_NEW_APP_BUTTON_XPATH).click();
	}

	public void clickOnGetStartedButtonInDragAndDrop() {
		page.locator(GET_STARTED_BUTTON_IN_DRAG_AND_DROP_XPATH).click();
	}

	public void enterAppName(String appName) {
		page.locator(NAME_TEXTBOX_XPATH).fill(appName + " " + timestamp);
	}

	public void enterAppDescription(String appDescription) {
		page.locator(DESCRIPTION_TEXTBOX_XPATH).fill(appDescription);
	}

	public void enterTags(String tagName) {
		page.locator(TAG_TEXTBOX_XPATH).fill(tagName);
		page.keyboard().press("Enter");
	}

	public void clickOnCreateButton() {
		page.locator(CREATE_BUTTON_XPATH).click();
	}

	public boolean verifyPage1IsVisible() {
		boolean isPage1Visible = page.locator(PAGE_1_ID).isVisible();
		return isPage1Visible;
	}

	public boolean verifyWelcomeTextboxIsVisible() {
		boolean isWelcomeTextboxVisible = page.locator(WELCOME_TEXT_BLOCK_XPATH).isVisible();
		return isWelcomeTextboxVisible;
	}

	public String verifyWelcomeText() {
		String actualWelcomeText = page.locator(WELCOME_TEXT_BLOCK_XPATH).textContent().trim();
		return actualWelcomeText;
	}

	public void navigateToHomePageFromAppEditPage() {
		String appNameWithLogo = ConfigUtils.getValue("applicationName");
		if (page.locator(MENU_CLOSED_ICON_XPATH).isVisible()) {
			page.locator(MENU_OPTION_XPATH).click();
		}
		page.locator(APP_LOGO_ON_EDIT_PAGE_XPATH.replace("{appName}", appNameWithLogo)).click();
	}

	public void navigatesToHomePage() {
		Locator openAppLibrary = page.locator(OPEN_APP_LIBRARY_XPATH);
		if (!openAppLibrary.isVisible()) {
			page.navigate(UrlUtils.getUrl("#/"));
			openAppLibrary.waitFor();
		}
	}

	public void searchApp(String appName) {
		page.locator(APP_SEARCH_TEXTBOX_XPATH).fill(appName + " " + timestamp);
	}

	public void clickOnSearchedApp(String appName) {
		page.locator(SEARCHED_APP_XPATH.replace("{appName}", appName + " " + timestamp)).click();
	}

	public void clickOnEditButton() {
		page.locator(EDIT_BUTTON_XPATH).click();
	}

	public void clickOnBlocksOption() {
		Locator blocksOption = page.locator(BLOCKS_OPTION_XPATH);
		if (!blocksOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blocksOption.click();
		}
	}

	public void blockDropPosition() {
		BoundingBox targetBox = page.locator(WELCOME_TEXT_BLOCK_XPATH).boundingBox();
		double dropX = targetBox.x + (targetBox.width / 2); // Center X position
		double dropY = targetBox.y + targetBox.height + 10; // Below target (+10 for margin)
		page.mouse().move(dropX, dropY, new Mouse.MoveOptions().setSteps(10)); // Slow movement
		page.mouse().up();
	}

	public void mouseHoverOnTextSectionBlock(String blockName) {
		boolean isValidBlock = true;
		switch (blockName) {
		case "Text (h1)":
			page.locator(HEADING_1_BLOCK_XPATH).isVisible();
			page.locator(HEADING_1_BLOCK_XPATH).hover();
			break;
		case "Text (h2)":
			page.locator(HEADING_2_BLOCK_XPATH).isVisible();
			page.locator(HEADING_2_BLOCK_XPATH).hover();
			break;
		case "Text (h3)":
			page.locator(HEADING_3_BLOCK_XPATH).isVisible();
			page.locator(HEADING_3_BLOCK_XPATH).hover();
			break;
		case "Text (h4)":
			page.locator(HEADING_4_BLOCK_XPATH).isVisible();
			page.locator(HEADING_4_BLOCK_XPATH).hover();
			break;
		case "Text (h5)":
			page.locator(HEADING_5_BLOCK_XPATH).isVisible();
			page.locator(HEADING_5_BLOCK_XPATH).hover();
			break;
		case "Text (h6)":
			page.locator(HEADING_6_BLOCK_XPATH).isVisible();
			page.locator(HEADING_6_BLOCK_XPATH).hover();
			break;
		case "Text":
			page.locator(TEXT_BLOCK_XPATH).isVisible();
			page.locator(TEXT_BLOCK_XPATH).hover();
			break;
		case "Link":
			page.locator(LINK_BLOCK_XPATH).isVisible();
			page.locator(LINK_BLOCK_XPATH).hover();
			break;
		case "Markdown":
			page.locator(MARKDOWN_BLOCK_XPATH).isVisible();
			page.locator(MARKDOWN_BLOCK_XPATH).hover();
			break;
<<<<<<< HEAD
		case "Logs":
			page.locator(LOGS_BLOCK_XPATH).isVisible();
			page.locator(LOGS_BLOCK_XPATH).hover();
			break;
=======
>>>>>>> origin/main
		default:
			isValidBlock = false;
			System.out.println("Invalid block name: " + blockName);
		}
		if (isValidBlock) {
			page.mouse().down();
		}
	}

	public String verifyHeadingBlockTextMessage() {
		String headingBlockTextMessage = page.locator(HEADING_BLOCK_HELLO_WORLD_XPATH).textContent().trim();
		return headingBlockTextMessage;
	}

	public void clickOnBlockSettingsOption() {
		Locator blockSettingsOption = page.locator(BLOCK_SETTINGS_XPATH);
		if (!blockSettingsOption.getAttribute("class").contains("flexlayout__border_button--selected")) {
			blockSettingsOption.click();
		}
	}

	public void enterDestination(String destination) {
		page.locator(DESTINATION_TEXTBOX_XPATH).fill(destination);
	}

	public void enterText(String text) {
		page.locator(TEXT_TEXTBOX_XPATH).fill(text);
	}

	public void enterMarkdown(String markdown) {
		page.locator(MARKDOWN_TEXTBOX_XPATH).fill(markdown);
	}

	public void selectTextStyle(String textStyles) {
		String[] textStyle = textStyles.split(", ");
		for (String style : textStyle) {
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(style.trim())).click();
		}
	}

	public void selectTextFont(String fontName) {
		page.locator(FONT_LIST_XPATH).click();
		page.locator(FONT_LIST_XPATH).fill(fontName);
		page.locator(FONT_LIST_XPATH).press("ArrowDown");
		page.locator(FONT_LIST_XPATH).press("Enter");
	}

	public void selectTextColor(String hexColor) {
		page.locator(COLOR_BOX_XPATH).fill(hexColor);
	}

	public void selectTextAlign(String textAlign) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(textAlign)).click();
	}

	public void clickOnSaveAppButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(SAVE_APP_BUTTON_NAME)).click();
	}

	public Locator textSectionDragAndDroppedBlockLocator(String blockName, String blockText) {
		Locator textBlockLocator = null;
		switch (blockName) {
		case "Link":
			textBlockLocator = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(blockText));
			break;
		case "Text":
			textBlockLocator = page.locator("p", new Page.LocatorOptions().setHasText(blockText));
			break;
		case "Markdown":
			textBlockLocator = page.locator("p", new Page.LocatorOptions().setHasText(blockText));
			break;
		default:
			textBlockLocator = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(blockText));
			break;
		}
		return textBlockLocator;
	}

	public String getBlockText(String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(blockName, blockText).textContent().trim();
	}

	public String getBlockTextFont(String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(blockName, blockText)
				.evaluate("el => el.style.fontFamily || getComputedStyle(el).fontFamily").toString()
				.replaceAll("^\"|\"$", "");
	}

	public String getBlockTextStyle(String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(blockName, blockText).evaluate("el => el.style.fontWeight")
				.toString();
	}

	public String getBlockTextColor(String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(blockName, blockText).evaluate("el => getComputedStyle(el).color")
				.toString().trim();
	}

	public String getBlockTextAlign(String blockName, String blockText) {
		return textSectionDragAndDroppedBlockLocator(blockName, blockText)
				.evaluate("el => getComputedStyle(el).textAlign").toString();
	}

	public void clickOnLink(String blockText) {
		Locator link = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(blockText));
		link.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		link.click();
	}

	public String getDestinationUrl(String url) {
		page.waitForURL(url);
		return page.url();
	}

	public void navigateToPreviosPage() {
		page.goBack(new Page.GoBackOptions().setTimeout(5000));
	}

	public void clickOnNotebooksOption() {
		page.locator(NOTEBOOK_OPTION_XPATH).click();
	}

	public void clickOnCreateNewNotebook() {
		page.getByTestId(CREATE_NEW_NOTEBOOK_DATA_TESTID).click();
	}

	public void enterQueryID(String queryId) {
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(NOTEBOOK_QUERY_ID_LABEL)).fill(queryId);
	}

	public void clickOnQuerySubmitButton() {
		page.locator(QUERY_SUBMIT_BUTTON_XPATH).click();
	}

	public void selectPage(String pageName) {
		Locator pageSelect = page.locator(PAGE_SELECTION_XPATH.replace("{pageName}", pageName));
		// pageSelect.waitFor(new
		// Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		pageSelect.first().click();
	}

}