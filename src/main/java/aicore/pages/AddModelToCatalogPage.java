package aicore.pages;

import java.util.List;

import com.microsoft.playwright.Page;

public class AddModelToCatalogPage {

	private Page page;
	private String timestamp;

	private static final String ADD_MODEL_BUTTON_XPATH = "//button[@aria-label='Navigate to import Model']";
	private static final String SELECT_OPENAI_XPATH = "//p[text()='{OpenAIModelName}']";
	private static final String CATALOG_NAME_XPATH = "//label[@id='NAME-label']";
	private static final String OPEN_AI_KEY_XPATH = "//input[@id='OPEN_AI_KEY']";
	private static final String VARIABLE_NAME_ID = "#VAR_NAME";
	private static final String CREATE_MODEL_BUTTON_XPATH = "//button[@type='submit']";
	private static final String MODEL_TOAST_MESSAGE_XPATH = "//div[@class='MuiAlert-message css-1xsto0d']";
	private static final String CRAETED_MODEL_XPATH = "//h4[@class='MuiTypography-root MuiTypography-h4 css-grm9aw']";
	// SMSS field
	private static final String SMSS_TAB_XPATH = "//button[text()='SMSS']";
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String VAR_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'VAR_NAME')]";
	// Settings field
	private static final String SETTINGS_TAB_XPATH = "//button[text()='Settings']";
	private static final String MAKE_PUBLIC_SECTION_TITLE_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[1]//p[text()='{title}']";
	private static final String MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH = "(//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')])[1]//p[contains(@class,'MuiTypography-root MuiTypography-body2')]";
	private static final String MAKE_PUBLIC_TOGGLE_BUTTON_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[1]//span[@class='MuiSwitch-track css-1ju1kxc']";
	private static final String MAKE_DISCOVERABLE_SECTION_TITLE_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[2]//p[text()='{title}']";
	private static final String MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH = "(//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')])[2]//p[contains(@class,'MuiTypography-root MuiTypography-body2')]";
	private static final String MAKE_DISCOVERABLE_TOGGLE_BUTTON_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[2]//span[@class='MuiSwitch-track css-1ju1kxc']";
	private static final String DELETE_SECTION_TITLE_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[3]//p[text()='{title}']";
	private static final String DELETE_SECTION_TEXT_MESSAGE_XPATH = "(//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')])[3]//p[contains(@class,'MuiTypography-root MuiTypography-body2')]";
	private static final String DELETE_BUTTON_XPATH = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4 css-1udb513'])[3]//div[@class='MuiAlert-action css-1mzcepu']";
	private static final String PENDING_REQUESTS_SECTION_TITLE_XPATH = "//div[@class='css-aplv3o']//div[@class='css-163ryps']";
	private static final String PENDING_REQUESTS_SECTION_TEXT_MESSAGE_XPATH = "//div[@class='css-1lxwves']//p[contains(@class,'MuiTypography-root MuiTypography')]";
	private static final String MEMBER_SECTION_TITLE_XPATH = "//div[@class='css-1hk1ec8']//div[@class='css-163ryps']";
	private static final String SEARCH_MEMBERS_SEARCHBOX_XPATH = "//div[contains(@class,'MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary')]";
	private static final String ADD_MEMBERS_BUTTON_XPATH = "//div[@class='css-gm8qym']";
	private static final String ROWS_PER_PAGE_DROPDOWN_XPATH = "//div[@class='MuiInputBase-root MuiInputBase-colorPrimary css-1tsucmk']";
	private static final String ROWS_PER_PAGE_DROPDOWN_OPTIONS_LIST_XPATH = "//ul[contains(@class,'MuiList-root MuiList-padding MuiMenu-list')]//li";
	// Edit model
	private static final String EDIT_BUTTON_XPATH = "//button[contains(@class, 'MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium ')]";
	private static final String TAG_NAME_AFTER_ADDING_XPATH = "//span[@class='MuiChip-label MuiChip-labelMedium css-9iedg7'][1]";
	private static final String TAG_TEXTBOX = "Tag";
	private static final String SUBMIT_BUTTON_XPATH = "//span[text()='Submit']";

	public AddModelToCatalogPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}

	public void addModelButton() {
		page.click(ADD_MODEL_BUTTON_XPATH);
	}

	public void selectOpenAi(String aiModelName) {
		page.click(SELECT_OPENAI_XPATH.replace("{OpenAIModelName}", aiModelName));
	}

	public void enterCatalogName(String CatalogName) {
		String uniqueCatalogName = CatalogName + timestamp;
		page.fill(CATALOG_NAME_XPATH, uniqueCatalogName);
	}

	public void enterOpenAIKey(String openAIKey) {
		page.fill(OPEN_AI_KEY_XPATH, openAIKey);
	}

	public void enterVariableName(String varName) {
		page.fill(VARIABLE_NAME_ID, varName);
	}

	public void createModel() {
		page.click(CREATE_MODEL_BUTTON_XPATH);
	}

	public String modelCreationToastMessage() {
		String toastMessage = page.textContent(MODEL_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public String verifyModelTitle() {
		String modelTitle = page.textContent(CRAETED_MODEL_XPATH).trim();
		return modelTitle;
	}

	public void clickOnSMSSTab() {
		page.click(SMSS_TAB_XPATH);
	}

	public String getExpectedCatalogTitle(String modelTitle) {
		String expTitle = modelTitle + timestamp;
		return expTitle;
	}

	public String verifyNameInSMSS() {
		String nameInSMSSProperties = page.textContent(NAME_SMSS_PROPERTIES_XPATH);
		return nameInSMSSProperties;
	}

	public String verifyVarNameInSMSS() {
		String varNameInSMSSProperties = page.textContent(VAR_NAME_SMSS_PROPERTIES_XPATH);
		return varNameInSMSSProperties;
	}

	public void clickOnEditButton() {
		page.click(EDIT_BUTTON_XPATH);
		page.waitForTimeout(1000);
	}

	public void enterTagName(String tagName) {
		page.getByLabel(TAG_TEXTBOX).click();
		page.getByLabel(TAG_TEXTBOX).fill("embeddings");
		page.getByLabel(TAG_TEXTBOX).press("Enter");
	}

	public void clickOnSubmit() {
		page.click(SUBMIT_BUTTON_XPATH);
	}

	public String verifyTagNameafteradding() {
		String modelTagName = page.textContent(TAG_NAME_AFTER_ADDING_XPATH);
		return modelTagName;
	}

	// Methods used for settings

	public void clickOnSettingsTab() {
		page.click(SETTINGS_TAB_XPATH);
	}

	public boolean verifyMakePublicSectionIsVisible(String title) {
		boolean isMakePublicSectionVisible = page.isVisible(MAKE_PUBLIC_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakePublicSectionVisible;
	}

	public String verifyMakePublicSectionTextMessage() {
		String actualTextMessage = page.textContent(MAKE_PUBLIC_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public boolean verifyMakePublicToggleButtonIsVisible() {
		boolean isMakePublicToggleButtonVisible = page.isVisible(MAKE_PUBLIC_TOGGLE_BUTTON_XPATH);
		return isMakePublicToggleButtonVisible;
	}

	public boolean verifyMakeDiscoverableSectionIsVisible(String title) {
		boolean isMakeDiscoverableSectionVisible = page
				.isVisible(MAKE_DISCOVERABLE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isMakeDiscoverableSectionVisible;
	}

	public String verifyMakeDiscoverableSectionTextMessage() {
		String actualTextMessage = page.textContent(MAKE_DISCOVERABLE_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public boolean verifyMakeDiscoverableToggleButtonIsVisible() {
		boolean isMakeDiscoverableToggleButtonVisible = page.isVisible(MAKE_DISCOVERABLE_TOGGLE_BUTTON_XPATH);
		return isMakeDiscoverableToggleButtonVisible;
	}

	public boolean verifyDeleteSectionIsVisible(String title) {
		boolean isDeleteSectionVisible = page.isVisible(DELETE_SECTION_TITLE_XPATH.replace("{title}", title));
		return isDeleteSectionVisible;
	}

	public String verifyDeleteSectionTextMessage() {
		String actualTextMessage = page.textContent(DELETE_SECTION_TEXT_MESSAGE_XPATH);
		return actualTextMessage;
	}

	public boolean verifyDeleteButtonIsVisible() {
		boolean isDeleteButtonVisible = page.isVisible(DELETE_BUTTON_XPATH);
		return isDeleteButtonVisible;
	}

	public boolean verifyPendingRequestsSectionIsVisible() {
		boolean isPendingRequestsSectionVisible = page.isVisible(PENDING_REQUESTS_SECTION_TITLE_XPATH);
		return isPendingRequestsSectionVisible;
	}

	public String verifyPendingRequestsSectionTextMessage() {
		String actualTextMessage = page.textContent(PENDING_REQUESTS_SECTION_TEXT_MESSAGE_XPATH).trim();
		return actualTextMessage;
	}

	public boolean verifyMembersSectionIsVisible() {
		boolean isMembersSectionVisible = page.isVisible(MEMBER_SECTION_TITLE_XPATH);
		return isMembersSectionVisible;
	}

	public boolean verifySearchMembersSearchBoxIsVisible() {
		boolean isSearchMembersTextBoxVisible = page.isVisible(SEARCH_MEMBERS_SEARCHBOX_XPATH);
		return isSearchMembersTextBoxVisible;
	}

	public boolean verifyAddMembersButtonIsVisible() {
		boolean isAddMembersButtonVisible = page.isVisible(ADD_MEMBERS_BUTTON_XPATH);
		return isAddMembersButtonVisible;
	}

	public boolean verifyRowsPerPageDropdownIsVisible() {
		page.locator(ROWS_PER_PAGE_DROPDOWN_XPATH).scrollIntoViewIfNeeded();
		boolean isRowsPerPageDropdownVisible = page.isVisible(ROWS_PER_PAGE_DROPDOWN_XPATH);
		return isRowsPerPageDropdownVisible;
	}

	public List<String> verifyRowsPerPageDropdownOptions() {
		page.click(ROWS_PER_PAGE_DROPDOWN_XPATH);
		List<String> actualOptions = page.locator(ROWS_PER_PAGE_DROPDOWN_OPTIONS_LIST_XPATH).allTextContents();
		return actualOptions;
	}
}
