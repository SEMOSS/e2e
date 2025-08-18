package aicore.utils.page.app;

import com.microsoft.playwright.Page;

public class AppTemplatePageUtils {

    private static final String DESCRIPTION_XPATH = "//p[@data-block='description']";
    private static final String SUBMIT_BUTTON_XPATH = "//div[@data-block='submit']";
    private static final String TITLE_XPATH = "//p[@data-block='title']";
    private static final String LANDING_PAGE_TITLE_TEXT_XPATH = "//p[@data-block='text--5552']";
    private static final String DESCRIPTION_BELOW_TITLE_XPATH = "//p[@data-block='text--6096']";
    private static final String INPUT_BOX_XPATH = "//div[@data-block='question']";
    private static final String INPUT_BOX_LABEL_XPATH = "//div[@data-block='question']//label";
    private static final String PREVIEW_APP_DESCRIPTION_XPATH = "//div[@role='dialog']//p[@data-block='description']";
    private static final String PREVIEW_APP_INPUT_BOX_XPATH = "//div[@role='dialog']//div[@data-block='question']";
    private static final String PREVIEW_APP_INPUT_BOX_LABEL_XPATH = "//div[@role='dialog']//div[@data-block='question']//label";
    private static final String PREVIEW_APP_TITLE_XPATH = "//div[@role='dialog']//p[@data-block='title']";
    private static final String PREVIEW_APP_SUBMIT_BUTTON_XPATH = "//div[@role='dialog']//div[@data-block='submit']";
    private static final String SELECT_TEMPLATE_XPATH = "//p[text()='{templateName}']/../../../../../following-sibling::div//button";

    public static void verifyDescription(String description, Page page) {
        page.locator(DESCRIPTION_XPATH).isVisible();
        String actualDescription = page.locator(DESCRIPTION_XPATH).textContent();

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
        boolean isPreviewButtonVisible = page.getByTestId("PlayArrowIcon").isVisible();
        if(!isPreviewButtonVisible) {
            throw new AssertionError("Preview button is not visible");
        }
         page.getByTestId("PlayArrowIcon").click();
    }

    public static void selectTemplateFromList(String templateName, Page page) {
        boolean isTemplateVisible = page.locator(SELECT_TEMPLATE_XPATH.replace("{templateName}", templateName)).isVisible();
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
        page.locator(PREVIEW_APP_TITLE_XPATH).isVisible();
        String pageTitle = page.locator(PREVIEW_APP_TITLE_XPATH).textContent();
        if (!pageTitle.equals(title)) {
            throw new AssertionError("Page title in preview '" + title + "' is not visible");
        }
    }

    public static void verifySubmitButtonInPreview(Page page) {
        page.locator(PREVIEW_APP_SUBMIT_BUTTON_XPATH).isVisible();
        if (!page.locator(PREVIEW_APP_SUBMIT_BUTTON_XPATH).isVisible()) {
            throw new AssertionError("Submit button in preview is not visible");
        }

    }

    public static void verifyInputFieldWithLabelInPreview(String label, Page page) {
        boolean inputBoxVisible = page.locator(PREVIEW_APP_INPUT_BOX_XPATH).isVisible();
        if (!inputBoxVisible) {
            throw new AssertionError("Input field in preview with label '" + label + "' is not visible");
        }
        String inputBoxLabel = page.locator(PREVIEW_APP_INPUT_BOX_LABEL_XPATH).textContent();
        if (!inputBoxLabel.contains(label)) {
            throw new AssertionError("Input field label in preview '" + label + "' does not match");
        }
    }

    public static void verifyDescriptionInPreview(String description, Page page) {
        page.locator(PREVIEW_APP_DESCRIPTION_XPATH).isVisible();
        String actualDescription = page.locator(PREVIEW_APP_DESCRIPTION_XPATH).textContent();

        if (!actualDescription.equals(description)) {
            throw new AssertionError("Description in preview does not match");
        }
    }

    public static void clickClosePreviewButton(Page page) {
        page.getByText("Cancel").isVisible();
        page.getByText("Cancel").click();

    }

    public static void verifyPageWithtitleText(String titleText, Page page) {
        String pageTitle = page.locator(LANDING_PAGE_TITLE_TEXT_XPATH).textContent();
        if (!pageTitle.equals(titleText)) {
            throw new AssertionError("Page title '" + titleText + "' is not visible");
        }
    }
    public static void verifyDescriptionBelowTitle(String description, Page page) {
       String pageDespriptionBelowTitle = page.locator(DESCRIPTION_BELOW_TITLE_XPATH).textContent();  
        if (!pageDespriptionBelowTitle.equals(description)) {
            throw new AssertionError("Description below title does not match");
        }
    }
    public static void verifyHyperlink(String text, String url, Page page) {
        page.getByText(text).isVisible();
        String href = page.getByText(text).getAttribute("href");
        if (!href.equals(url)) {
            throw new AssertionError("Hyperlink with text '" + text + "' does not point to '" + url + "'");
        }
    }
}
