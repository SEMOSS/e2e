package aicore.pages.model;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

import aicore.utils.AICorePageUtils;

public class ModelViewLogsDashboardPageUtils {
	private static final String DASHBOARD_HEADING_XPATH = "//h1[contains(text(), 'Model Insight Dashboard')]";
	private static final String REFRESH_BUTTON_XPATH = "//button[contains(@aria-label, 'Refresh') or contains(text(), 'Refresh')]";
	private static final String EVENT_HISTORY_SECTION_XPATH = "//div//span[contains(text(), 'Event History')] | //h2[contains(text(), 'Event History')]";
	private static final String PROMPT_RESPONSE_TIMELINE_SECTION_XPATH = "//div//span[contains(text(), 'Prompt & Response Timeline')] | //h2[contains(text(), 'Prompt & Response Timeline')]";
	private static final String DASHBOARD_TABLE_XPATH = "//table | //div[@role='grid']";
	private static final String TABLE_HEADER_XPATH = "//th[contains(text(), '{columnName}')] | //div[@role='columnheader' and contains(text(), '{columnName}')]";

	/**
	 * Navigate to the Model Insight Dashboard
	 */
	public static void navigateToModelInsightDashboard(Page page) {
		try {
			// Look for a link or button that navigates to the dashboard
			Locator dashboardLink = page.locator("//a[contains(text(), 'Model Insight')] | //button[contains(text(), 'Model Insight')] | //a[@href*='insight']");
			if (dashboardLink.isVisible()) {
				dashboardLink.click();
				page.waitForTimeout(2000);
			} else {
				// If no direct link, try clicking on menu and finding the dashboard option
				Locator logsMenuLink = page.locator("//a[contains(text(), 'Logs')] | //button[contains(text(), 'Logs')]");
				if (logsMenuLink.isVisible()) {
					logsMenuLink.click();
					page.waitForTimeout(1000);
					dashboardLink = page.locator("//a[contains(text(), 'Model Insight')] | //button[contains(text(), 'Model Insight')]");
					if (dashboardLink.isVisible()) {
						dashboardLink.click();
						page.waitForTimeout(2000);
					}
				}
			}
		} catch (PlaywrightException e) {
			throw new RuntimeException("Failed to navigate to Model Insight Dashboard: " + e.getMessage());
		}
	}

	/**
	 * Verify the heading 'Model Insight Dashboard' is visible
	 */
	public static boolean verifyDashboardHeading(Page page) {
		Locator heading = page.locator(DASHBOARD_HEADING_XPATH);
		AICorePageUtils.waitFor(heading);
		return heading.isVisible();
	}

	/**
	 * Verify the Refresh button is visible
	 */
	public static boolean verifyRefreshButton(Page page) {
		Locator refreshBtn = page.locator(REFRESH_BUTTON_XPATH);
		try {
			AICorePageUtils.waitFor(refreshBtn);
			return refreshBtn.isVisible();
		} catch (PlaywrightException e) {
			return false;
		}
	}

	/**
	 * Verify the Event History section is visible
	 */
	public static boolean verifyEventHistorySection(Page page) {
		Locator eventHistorySection = page.locator(EVENT_HISTORY_SECTION_XPATH);
		try {
			AICorePageUtils.waitFor(eventHistorySection);
			return eventHistorySection.isVisible();
		} catch (PlaywrightException e) {
			return false;
		}
	}

	/**
	 * Verify the Prompt & Response Timeline section is visible
	 */
	public static boolean verifyPromptResponseTimelineSection(Page page) {
		Locator timelineSection = page.locator(PROMPT_RESPONSE_TIMELINE_SECTION_XPATH);
		try {
			AICorePageUtils.waitFor(timelineSection);
			return timelineSection.isVisible();
		} catch (PlaywrightException e) {
			return false;
		}
	}

	/**
	 * Verify the table exists on the dashboard
	 */
	public static boolean verifyTableExists(Page page) {
		Locator table = page.locator(DASHBOARD_TABLE_XPATH);
		try {
			AICorePageUtils.waitFor(table);
			return table.isVisible();
		} catch (PlaywrightException e) {
			return false;
		}
	}

	/**
	 * Verify that the table contains all the required columns
	 */
	public static boolean verifyTableColumnsExist(Page page, List<String> columnNames) {
		for (String columnName : columnNames) {
			String xpath = TABLE_HEADER_XPATH.replace("{columnName}", columnName);
			Locator columnHeader = page.locator(xpath);
			try {
				AICorePageUtils.waitFor(columnHeader);
				if (!columnHeader.isVisible()) {
					System.out.println("Column '" + columnName + "' is not visible");
					return false;
				}
			} catch (PlaywrightException e) {
				System.out.println("Column '" + columnName + "' not found: " + e.getMessage());
				return false;
			}
		}
		return true;
	}

	/**
	 * Get all column names from the table
	 */
	public static List<String> getTableColumnNames(Page page) {
		List<String> columns = new ArrayList<>();
		try {
			Locator columnHeaders = page.locator("//th | //div[@role='columnheader']");
			int count = columnHeaders.count();
			for (int i = 0; i < count; i++) {
				String columnName = columnHeaders.nth(i).textContent().trim();
				if (!columnName.isEmpty()) {
					columns.add(columnName);
				}
			}
		} catch (PlaywrightException e) {
			System.out.println("Error retrieving column names: " + e.getMessage());
		}
		return columns;
	}

	/**
	 * Click on the Refresh button
	 */
	public static void clickRefreshButton(Page page) {
		Locator refreshBtn = page.locator(REFRESH_BUTTON_XPATH);
		AICorePageUtils.waitFor(refreshBtn);
		refreshBtn.click();
		page.waitForTimeout(1500);
	}
}
