package aicore.utils.page.app;

import com.microsoft.playwright.Page;

public class LayersPageUtils {
	private static final String LEFT_PANEL_TAB_DATATESTID = "workspace-{tabName}";
	private static final String ADD_NEW_PAGE_ICON_XPATH = "//button/*[@data-testid='AddIcon']";
	private static final String LEFT_PANE_PAGE_XPATH = "//h6[text()='{pageName}']";
	private static final String SELECTED_PAGE_XPATH = "//div[@id='{pageName}']";

	public static void clickOnTabInLeftPanel(Page page, String tabName) {
		page.getByTestId(LEFT_PANEL_TAB_DATATESTID.replace("{tabName}", tabName)).first().click();
	}

	public static void clickOnAddNewPageIcon(Page page) {
		page.locator(ADD_NEW_PAGE_ICON_XPATH).click();
	}

	public static boolean isPagePresent(Page page, String pageName) {
		return page.locator(LEFT_PANE_PAGE_XPATH.replace("{pageName}", pageName)).isVisible();
	}

	public static boolean isUserOnPage(Page page, String pageName) {
		return page.locator(SELECTED_PAGE_XPATH.replace("{pageName}", pageName)).isVisible();
	}

	public static void clickOnPageInLeftPane(Page page, String pageName) {
		page.locator(LEFT_PANE_PAGE_XPATH.replace("{pageName}", pageName)).click();
	}
}
