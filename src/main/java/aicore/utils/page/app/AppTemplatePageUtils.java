package aicore.utils.page.app;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.page.model.ModelPageUtils;

public class AppTemplatePageUtils {

	private static final String DESCRIPTION_XPATH = "//p[@data-block='description']";
	private static final String SUBMIT_BUTTON_XPATH = "//div[@data-block='submit']";
	private static final String TITLE_XPATH = "//p[@data-block='title']";
	private static final String INPUT_BOX_XPATH = "//div[@data-block='question']";
	private static final String RESPONSE_BOX_XPATH = "//p[@data-block='response']";
	private static final String ASK_LOADER_XPATH = "//div[@data-block=\"submit\"]//span[@role=\"progressbar\"]";
	private static final String DESCRIPTION_BOX_XPATH = "//p[text()='Value']/../..//div//div//input";
	private static final String PREVIEW_APP_CANCEL_XPATH = "(//button//span[contains(text(),'Cancel')])[2]";
	private static final String INPUT_BOX_LABEL_XPATH = "//div[@data-block='question']//label";
	private static final String PREVIEW_APP_DESCRIPTION_XPATH = "//h2[text()='Preview']/parent::div//p[text()='Ask an LLM a question']";
	private static final String PREVIEW_APP_INPUT_BOX_XPATH = "//div[@role='dialog']//div[@data-block='question']";
	private static final String PREVIEW_APP_INPUT_BOX_LABEL_XPATH = "//div[@role='dialog']//div[@data-block='question']//label";
	private static final String PREVIEW_APP_TITLE_XPATH = "//div[@role='dialog']//p[@data-block='title']";
	private static final String PREVIEW_APP_SUBMIT_BUTTON_XPATH = "//div[@role='dialog']//div[@data-block='submit']";
	private static final String LANDING_PAGE_TITLE_TEXT_XPATH = "//p[text()='{titleText}']";
	private static final String DESCRIPTION_BELOW_TITLE_XPATH = "//p[text()='{descriptionText}']";
	private static final String SELECT_TEMPLATE_XPATH = "//p[text()='{templateName}']/../../../../../following-sibling::div//button";
	private static final String TEXT_XPATH = "//a[text()='{text}']";
	private static final String BLOCK_DESCRIPTION_XPATH = "//div[p[text()='{blockTitle}']]//p[text()='{description}']";
	private static final String HYPERLINK_TEXT_FOR_BLOCK_XPATH = "//div[p[text()='{title}']]//a[text()='{hyperlinkText}']";
	private static final String DESTINATION_URL_INPUT_FIELD_XPATH = "//p[text()='Destination']/ancestor::div[contains(@class,'base-setting-section')]//input[@type='text']";
	private static final String APP_TITLE_XPATH = "//*[@id='page-1']//h1";
	private static final String APP_BLOCK_TITLE_XPATH = "input[value='{text}']";
	private static final String APP_SUB_TITLE_XPATH = "//*[@id='page-1']//h5";

	private static final String MULI_PAGE_APP_PAGE1_XAPTH = "//div[@style='overflow: auto hidden;']//div[@class='flexlayout__tab_button_content workspace_layout' and normalize-space(text())='page-1']";
	private static final String TEAMPLATE_APP_PAGE_TITLE_XPATH = "//div[@id='page-1']/h1[contains(@data-block,'text')]";
	private static final String MULI_PAGE_APP_HYPERLINK_XAPTH = "//a[normalize-space(text())='%s']";
	private static final String AREA_CHART_SEE_ON_LANDING_PAGE_XPATH = "//div[@class='vega-embed']";
	private static final String RESOURCE_TITLE_TEXT = "Resources";
	private static final String ABOUT_TITLE_TEXT = "About";

	private static final String VARIABLE_GUIDE_BLOCKS_TITLE_XAPTH = "//h1[text()='{blockTitle}']";
	private static final String FONT_STYLE_SIZE_BLOCK_XAPTH = "//div[@id='delete-duplicate-mask'][.//div[contains(@class,'MuiAutocomplete')]]";
	private static final String VARIABLE_GUIDE_BLOCK_FONT_SIZE_XPATH = "//input[@type='number']";
	private static final String VARIABLE_GUIDE_BLOCK_FONT_STYLE_XPATH = "//label[text()='Fonts Style']/following::input[@role='combobox']";
	private static final String TEAMPLATE_APP_TITLE_TEXT = "{title}";
	private static final String TEMPLATE_APP_DESCRIPTION = "//*[@id='page-1']//p[text()='{description}']";

	public static void verifyDescription(String description, Page page) {
		Locator descriptionLocator = page.locator(DESCRIPTION_XPATH);
		String actualDescription = descriptionLocator.textContent();

		if (!actualDescription.equals(description)) {
			throw new AssertionError("Description does not match");
		}
	}

	public static void verifySubmitButton(Page page) {
		page.locator(SUBMIT_BUTTON_XPATH).isVisible();
		if (!page.locator(SUBMIT_BUTTON_XPATH).isVisible()) {
			throw new AssertionError("Submit button is not visible");
		}
	}

	public static void clickOnQuestionBlock(Page page) {
		page.locator(INPUT_BOX_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(INPUT_BOX_XPATH).isVisible();
		page.locator(INPUT_BOX_XPATH).click();
	}

	public static void addDescription(String description, Page page) {
		page.locator(DESCRIPTION_BOX_XPATH).fill(description);
	}

	public static void clickPreviewButton(Page page) {
		boolean isPreviewButtonVisible = page.getByTestId("PreviewRoundedIcon").isVisible();
		if (!isPreviewButtonVisible) {
			throw new AssertionError("Preview button is not visible");
		}
		page.getByTestId("PreviewRoundedIcon").click();
	}

	public static void selectTemplateFromList(String templateName, Page page) {
		boolean isTemplateVisible = page.locator(SELECT_TEMPLATE_XPATH.replace("{templateName}", templateName))
				.isVisible();
		if (!isTemplateVisible) {
			throw new AssertionError("Template " + templateName + " is not visible in the list");
		}
		page.locator(SELECT_TEMPLATE_XPATH.replace("{templateName}", templateName)).click();
	}

	public static void verifyInputFieldWithLabel(String label, Page page) {
		boolean inputBoxVisible = page.locator(INPUT_BOX_XPATH).isVisible();
		if (!inputBoxVisible) {
			throw new AssertionError("Input field with label '" + label + "' is not visible");
		}
		String inputBoxLabel = page.locator(INPUT_BOX_LABEL_XPATH).textContent();
		if (!inputBoxLabel.contains(label)) {
			throw new AssertionError("Input field label " + label + " does not match with expected label ");
		}
	}

	public static void verifyPageWithTitle(String title, Page page) {
		String pageTitle = page.locator(TITLE_XPATH).textContent();
		if (!pageTitle.equals(title)) {
			throw new AssertionError("Page title '" + title + "' is not visible");
		}
	}

	public static void verifyPageWithTitleInPreview(String title, Page page) {
		Locator titleLocator = page.getByRole(AriaRole.DIALOG).getByText("Ask LLM");
		titleLocator.isVisible();
		String pageTitle = titleLocator.textContent();
		if (!pageTitle.equals(title)) {
			throw new AssertionError("Page title in preview '" + title + "' is not visible");
		}
	}

	public static void verifySubmitButtonInPreview(Page page) {
		page.locator(PREVIEW_APP_SUBMIT_BUTTON_XPATH).first().isVisible();
		if (!page.locator(PREVIEW_APP_SUBMIT_BUTTON_XPATH).first().isVisible()) {
			throw new AssertionError("Ask button in preview is not visible");
		}
	}

	public static void closePreviewWindow(Page page) {
		boolean isPreviewButtonVisible = page.locator(PREVIEW_APP_CANCEL_XPATH).isVisible();
		if (!isPreviewButtonVisible) {
			throw new AssertionError("Preview button is not visible");
		}
		page.locator(PREVIEW_APP_CANCEL_XPATH).click();
	}

	public static void verifyInputFieldWithLabelInPreview(String label, Page page) {
		boolean inputBoxVisible = page.locator(PREVIEW_APP_INPUT_BOX_XPATH).first().isVisible();
		if (!inputBoxVisible) {
			throw new AssertionError("Input field in preview with label '" + label + "' is not visible");
		}
		String inputBoxLabel = page.locator(PREVIEW_APP_INPUT_BOX_LABEL_XPATH).first().textContent();
		if (!inputBoxLabel.contains(label)) {
			throw new AssertionError("Input field label in preview '" + label + "' does not match");
		}
	}

	public static void verifyDescriptionInPreview(String description, Page page) {
		page.locator(PREVIEW_APP_DESCRIPTION_XPATH).first().isVisible();
		String actualDescription = page.locator(PREVIEW_APP_DESCRIPTION_XPATH).first().textContent();

		if (!actualDescription.equals(description)) {
			throw new AssertionError("Description in preview does not match");
		}
	}

	public static void verifyPageWithtitleText(String titleText, Page page) {
		String pageTitle = page.locator(LANDING_PAGE_TITLE_TEXT_XPATH.replace("{titleText}", titleText)).textContent();
		if (!pageTitle.equals(titleText)) {
			throw new AssertionError("Page title '" + titleText + "' is not visible");
		}
	}

	public static void verifyDescriptionBelowTitle(String description, Page page) {
		String pageDespriptionBelowTitle = page
				.locator(DESCRIPTION_BELOW_TITLE_XPATH.replace("{descriptionText}", description)).textContent();
		if (!pageDespriptionBelowTitle.equals(description)) {
			throw new AssertionError("Description below title does not match");
		}
	}

	public static void verifyHyperlink(String text, String link, Page page) {
		Locator textLocator = page.locator(TEXT_XPATH.replace("{text}", text));
		AICorePageUtils.waitFor(textLocator);
		textLocator.dblclick();
		page.waitForTimeout(1000);
	}

	public static String getCurrentUrl(Page page) {
		page.waitForLoadState(LoadState.LOAD);
		return page.url();
	}

	public static void clickClosePreviewButton(Page page) {
		Locator cancelButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel"));
		cancelButton.isVisible();
		cancelButton.click();
	}

	public static void getBackPage(Page page) {
		page.goBack();
		page.waitForLoadState(LoadState.LOAD);
	}

	public static void verifyDescriptionBelowTitleOfBlock(String blockTitle, String description, Page page) {
		Locator blockDescriptionXPath = page.locator(
				BLOCK_DESCRIPTION_XPATH.replace("{blockTitle}", blockTitle).replace("{description}", description));
		if (!blockDescriptionXPath.isVisible()) {
			throw new AssertionError("Description for the block with title '" + blockTitle + "' does not match");
		}
	}

	public static void verifyHyperlinkText(String text, String blockTitle, String url, Page page) {
		Locator hyperlinkLocator = page.locator(
				HYPERLINK_TEXT_FOR_BLOCK_XPATH.replace("{title}", blockTitle).replace("{hyperlinkText}", text));
		hyperlinkLocator.scrollIntoViewIfNeeded();
		hyperlinkLocator.dblclick();
		page.waitForTimeout(1000);
	}

	public static void clickOnHyperlinkText(String text, Page page) {
		Locator textLocator = page.locator(TEXT_XPATH.replace("{text}", text));
		textLocator.click();
		page.waitForTimeout(1000);
	}

	public static void fillDestinationUrl(String url, Page page) {
		Locator urlInputField = page.locator(DESTINATION_URL_INPUT_FIELD_XPATH);
		urlInputField.fill(url);
		page.waitForTimeout(1000);
	}

	public static void clickSaveButtonOfTheApp(Page page) {
		Locator saveButton = page.getByTestId("SaveRoundedIcon");
		saveButton.isVisible();
		saveButton.click();
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
	}

	public static void verifyAppPageTitle(String title, Page page) {
		Locator pageTitle = page.locator(APP_TITLE_XPATH);
		AICorePageUtils.waitFor(pageTitle);
		String titleText = pageTitle.textContent();
		if (!titleText.equals(title)) {
			throw new AssertionError("App page title '" + title + "' is not visible");
		}
	}

	public static void clickOnAppPageTitle(String title, Page page) {
		String pageTitle = page.locator(APP_TITLE_XPATH).textContent();
		if (!pageTitle.equals(title)) {
			throw new AssertionError("App page title '" + title + "' is not visible");
		}
		page.locator(APP_TITLE_XPATH).click();
	}

	public static void changeAppPageTitle(String oldTitle, String newTitle, Page page) {
		Locator pageTitle = page.locator(APP_BLOCK_TITLE_XPATH.replace("{text}", oldTitle));
		if (!pageTitle.isVisible()) {
			throw new AssertionError("App page title '" + oldTitle + "' is not visible");
		}
		pageTitle.click();
		page.locator(APP_BLOCK_TITLE_XPATH.replace("{text}", oldTitle)).fill(newTitle);
		page.locator(APP_BLOCK_TITLE_XPATH.replace("{text}", newTitle)).press("Enter");
	}

	public static void verifyAppPageSubTitle(String title, Page page) {
		String pageTitle = page.locator(APP_SUB_TITLE_XPATH).textContent();
		if (!pageTitle.equals(title)) {
			throw new AssertionError("App page sub title '" + title + "' is not visible");
		}
	}

	// MultiPage App
	public static String userSeePage1(Page page) {
		return page.locator(MULI_PAGE_APP_PAGE1_XAPTH).textContent();
	}

	public static String userSeeTeamplatePageTitle(Page page) {
		return page.locator(TEAMPLATE_APP_PAGE_TITLE_XPATH).textContent();
	}

	public static boolean userSeeTheHyperlink(Page page, String hrefValue) {
		String locator = String.format(MULI_PAGE_APP_HYPERLINK_XAPTH, hrefValue);
		return page.isVisible(locator);
	}

	public static boolean dropChartOnPage(Page page, String titleOfPage) {
		Locator targetBox = page.getByText(TEAMPLATE_APP_TITLE_TEXT.replace("{title}", titleOfPage));
		targetBox.scrollIntoViewIfNeeded();
		CommonUtils.moveMouseToCenterWithMargin(page, targetBox, 0, 10);
		page.mouse().up();
		return page.isVisible(AREA_CHART_SEE_ON_LANDING_PAGE_XPATH);
	}

	public static String userSeeResourceTitle(Page page) {
		String actualTitle = page.getByText(RESOURCE_TITLE_TEXT).textContent();
		return actualTitle;
	}

	public static String userSeeAboutTitle(Page page) {
		String actualTitle = page.getByText(ABOUT_TITLE_TEXT).textContent();
		return actualTitle;
	}

	// Variable Guide App
	public static String userSeeVariableGuideBlocksTitle(Page page, String blockTitle) {
		Locator actualBlockTitles = page.locator(VARIABLE_GUIDE_BLOCKS_TITLE_XAPTH.replace("{blockTitle}", blockTitle));
		return actualBlockTitles.textContent();
	}

	public static boolean userSeeTheFontStyleAndSizeBlock(Page page, String block) {
		Locator blockLocator = page.locator(VARIABLE_GUIDE_BLOCKS_TITLE_XAPTH.replace("{blockTitle}", block));
		AICorePageUtils.waitFor(blockLocator);
		blockLocator.hover();
		blockLocator.click();
		Locator style = page.locator(FONT_STYLE_SIZE_BLOCK_XAPTH);
		AICorePageUtils.waitFor(blockLocator);
		return style.isVisible();
	}

	public static void selectFontStyle(Page page, String fontName) {
		page.locator(VARIABLE_GUIDE_BLOCK_FONT_STYLE_XPATH).click();
		page.getByText(fontName).click();
	}

	public static String getSelectedFont(Page page) {
		return page.locator(VARIABLE_GUIDE_BLOCK_FONT_STYLE_XPATH).inputValue();
	}

	public static void changeFontSize(Page page, String size) {
		Locator size1 = page.locator(VARIABLE_GUIDE_BLOCK_FONT_SIZE_XPATH);
		size1.fill("");
		size1.fill(size);
	}

	public static String getFontSize(Page page) {
		return page.locator(VARIABLE_GUIDE_BLOCK_FONT_SIZE_XPATH).inputValue();
	}

	public static void clickOnResponseBlock(Page page) {
		page.waitForTimeout(5000);
		if (page.locator(ASK_LOADER_XPATH).isVisible()) {
			page.locator(ASK_LOADER_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		}
		Locator responseBlock = page.locator(RESPONSE_BOX_XPATH);
		AICorePageUtils.waitFor(responseBlock);
		responseBlock.isVisible();
		responseBlock.click();
		page.locator(TITLE_XPATH).scrollIntoViewIfNeeded();
	}

	public static void clickOSubmitBlock(Page page) {
		page.locator(SUBMIT_BUTTON_XPATH).isVisible();
		page.locator(SUBMIT_BUTTON_XPATH).click();
	}

	public static void verifyAppPageDescription(String descriptionText, Page page) {
		Locator descriptionLocator = page.locator(TEMPLATE_APP_DESCRIPTION.replace("{description}", descriptionText));
		AICorePageUtils.waitFor(descriptionLocator);
		String actualDescription = descriptionLocator.textContent();

		if (!actualDescription.equals(descriptionText)) {
			throw new AssertionError("Description does not match");
		}
	}

	public static boolean isButtonEnabled(String buttonText, Page page) {
		Locator buttonLocator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(buttonText));
		AICorePageUtils.waitFor(buttonLocator);
		return buttonLocator.isEnabled();
	}

	public static List<String> ids = ModelPageUtils.createdModelIds;
		public static boolean verifyCreatedModelsInList(Page page) {
		if (ids == null || ids.isEmpty()) {
			throw new AssertionError("No created model ids provided");
		}

		int foundCount = 0;
		for (String id : ids) {
			Locator locator = page.getByText(id);
			if (locator.count() == 0) {
				throw new AssertionError("Model id '" + id + "' is not present on the page");
			}
			foundCount++;
		}
		
		if (foundCount > ids.size()) {
			throw new AssertionError("More models are present on the page than were created");
		}

		return foundCount == ids.size();
	}
}
