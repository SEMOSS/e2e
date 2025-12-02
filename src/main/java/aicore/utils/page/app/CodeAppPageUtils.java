package aicore.utils.page.app;

import com.microsoft.playwright.Page;

public class CodeAppPageUtils {
    
	public static final String FILE_UPLOAD_TESTID = "FileUploadIcon";
    public static final String UNZIP_CHECKBOX_TESTID = "CheckBoxOutlineBlankIcon";
    public static final String PUBLISH_BUTTON_TESTID = "PublishedWithChangesOutlinedIcon";

    public static void clickOnFileUploadButton(Page page) {
		page.getByTestId(FILE_UPLOAD_TESTID).click();
	}

    public static void clickOnUnzipCheckbox(Page page) {
        page.getByTestId(UNZIP_CHECKBOX_TESTID).first().click(new com.microsoft.playwright.Locator.ClickOptions().setForce(true));
    }

    public static void clickOnPublishButton(Page page) {
        page.getByTestId(PUBLISH_BUTTON_TESTID).first().click();
    }
}
