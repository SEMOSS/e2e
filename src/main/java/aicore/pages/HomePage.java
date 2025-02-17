package aicore.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page;
	private static final String ACCEPT_BUTTON_XPATH = "//span[text()='Accept']";
	private static final String CLOSE_POPUP_BUTTON_XPATH = "//div[@class='css-1bvc4cc']//button";
//	private static final String PAGE_TITLE_XPATH = "//a[text()='AI Core']";
	private static final String PAGE_TITLE_XPATH = "//a[@class='css-jnxb8i']";
	private static final String SYSTEM_APP_BUTTON_XPATH = "//button[text()='System Apps']";
	private static final String BI_APP_XPATH = "(//div[@class='css-uncsel']//div//a)[1]";
	private static final String OPEN_MODEL_XPATH = "(//a[@class=' css-drmees'])[2]";

	public HomePage(Page page) {
		this.page = page;
	}

	public void closeInfoPopup() {
		page.click(ACCEPT_BUTTON_XPATH);
		page.click(CLOSE_POPUP_BUTTON_XPATH);
	}

	public String getPageTitle() {
		String actTitle = page.textContent(PAGE_TITLE_XPATH).trim();
		return actTitle;
	}

	public void clickOnSystemApp() {
		page.click(SYSTEM_APP_BUTTON_XPATH);
	}

	public void clickOnBIApp() {
		page.click(BI_APP_XPATH);
	}

	public void clickOnOpenModel() {
		page.click(OPEN_MODEL_XPATH);
	}

}