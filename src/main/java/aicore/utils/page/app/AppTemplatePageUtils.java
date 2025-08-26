package aicore.utils.page.app;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import aicore.utils.CommonUtils;

public class AppTemplatePageUtils {

	private static final String DESCRIPTION_XPATH = "//p[@data-block='description']";
	private static final String SUBMIT_BUTTON_XPATH = "//div[@data-block='submit']";
	private static final String TITLE_XPATH = "//p[@data-block='title']";
	private static final String INPUT_BOX_XPATH = "//div[@data-block='question']";
	private static final String INPUT_BOX_LABEL_XPATH = "//div[@data-block='question']//label";
	private static final String PREVIEW_APP_DESCRIPTION_XPATH = "//h2[text()='Preview']/parent::div//p[text()='Ask an LLM a question']";
	private static final String PREVIEW_APP_INPUT_BOX_XPATH = "//div[@role='dialog']//div[@data-block='question']";
	private static final String PREVIEW_APP_INPUT_BOX_LABEL_XPATH = "//div[@role='dialog']//div[@data-block='question']//label";
	private static final String PREVIEW_APP_TITLE_XPATH = "//div[@role='dialog']//p[@data-block='title']";
	private static final String PREVIEW_APP_SUBMIT_BUTTON_XPATH = "//div[@role='dialog']//div[@data-block='submit']";
	private static final String SELECT_TEMPLATE_XPATH = "//p[text()='{templateName}']/../../../../../following-sibling::div//button";
	private static final String APP_TITLE_XPATH = "#page-1>h1";
	private static final String APP_BLOCK_TITLE_XPATH = "input[value='{text}']";
	private static final String APP_SUB_TITLE_XPATH = "#page-1>h5";

	private static final String MULI_PAGE_APP_PAGE1_XAPTH = "//div[@style='overflow: auto hidden;']//div[@class='flexlayout__tab_button_content' and normalize-space(text())='page-1']";
	private static final String MULI_PAGE_APP_LANDING_BLOCK_XPATH = "//div[@id='page-1']//h1";
	private static final String MULI_PAGE_APP_HYPERLINK_XAPTH = "//a[normalize-space(text())='%s']";
	private static final String TEXT_XPATH = "//a[text()='{text}']";
	private static final String LANDING_PAGE_TEXT = "Landing Page";
	private static final String AREA_CHART_SEE_ON_LANDING_PAGE_XPATH = "//div[@class='vega-embed']";
	private static final String RESOURCE_TITLE_TEXT = "Resources";
	private static final String ABOUT_TITLE_TEXT = "About";

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

	public static void clickClosePreviewButton(Page page) {
		Locator cancelButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel"));
		cancelButton.isVisible();
		cancelButton.click();
	}

	public static void verifyAppPageTitle(String title, Page page) {
		String pageTitle = page.locator(APP_TITLE_XPATH).textContent();
		if (!pageTitle.equals(title)) {
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

	public static String userSeePage1(Page page) {
		return page.locator(MULI_PAGE_APP_PAGE1_XAPTH).textContent();
	}

	public static String userSeeLandingPageBlock(Page page) {
		return page.locator(MULI_PAGE_APP_LANDING_BLOCK_XPATH).textContent();
	}

	public static boolean userSeeTheHyperlink(Page page, String hrefValue) {
		String locator = String.format(MULI_PAGE_APP_HYPERLINK_XAPTH, hrefValue);
		return page.isVisible(locator);
	}

	public static void verifyHyperlink(String text, String link, Page page) {
		Locator textLocator = page.locator(TEXT_XPATH.replace("{text}", text));
		textLocator.dblclick();
	}

	public static boolean dropChartOnLandingPage(Page page) {
		Locator targetBox = page.getByText(LANDING_PAGE_TEXT);
		CommonUtils.moveMouseToCenterWithMargin(page, targetBox, 0, 10);
		page.mouse().up();
		return page.isVisible(AREA_CHART_SEE_ON_LANDING_PAGE_XPATH);

	}

	public static void getBackPage(Page page) {
		page.goBack();
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
	}

	public static String getCurrentUrl(Page page) {
		return page.url();
	}

	public static String userSeeResourceTitle(Page page) {
		String actualTitle = page.getByText(RESOURCE_TITLE_TEXT).textContent();
		return actualTitle;
	}

	public static String userSeeAboutTitle(Page page) {
		String actualTitle = page.getByText(ABOUT_TITLE_TEXT).textContent();
		return actualTitle;
	}
}
