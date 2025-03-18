package aicore.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {

	private Page page;
	private static final String ACCEPT_BUTTON_XPATH = "//span[text()='Accept']";
	private static final String CLOSE_POPUP_BUTTON_XPATH = "//div[@class='css-1bvc4cc']//button";
	private static final String PAGE_TITLE_XPATH = "//a[@class='css-jnxb8i']";
	private static final String SYSTEM_APP_BUTTON_XPATH = "//button[text()='System Apps']";
	private static final String BI_APP_XPATH = "(//div[@class='css-uncsel']//div//a)[1]";
	private static final String OPEN_MODEL_XPATH = "(//a[@data-testid='Model-icon'])";
	private static final String OPEN_STORAGE_XPATH = "//a[@data-testid='Storage-icon']";
	private static final String OPEN_VECTOR_XPATH = "//a[@data-testid='Vector-icon']";
	private static final String USER_PROFILE_ICON_XPATH = "//div[normalize-space()='GovConnect.AI']//button";

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

	public void clickOnOpenStorage() {
		page.click(OPEN_STORAGE_XPATH);
	}

	public void clickOnOpenVector() {
		page.click(OPEN_VECTOR_XPATH);
	}

	public void logOutAsCurrentUser() {
		page.click(USER_PROFILE_ICON_XPATH);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
	}
}