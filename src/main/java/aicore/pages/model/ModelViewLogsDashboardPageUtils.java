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
    private static final String DASHBOARD_LINK_XPATH = "View Logs Dashboard";
    private static final String TABLE_COLUMN_HEADERS_XPATH = "//th | //div[@role='columnheader']";

    public static void navigateToModelInsightDashboard(Page page) {
        Locator dashboardLink = page.getByTitle(DASHBOARD_LINK_XPATH);
        AICorePageUtils.waitFor(dashboardLink);
        dashboardLink.click();
    }

    public static boolean verifyDashboardHeading(Page page) {
        Locator heading = page.locator(DASHBOARD_HEADING_XPATH);
        AICorePageUtils.waitFor(heading);
        return heading.isVisible();
    }

    public static boolean verifyRefreshButton(Page page) {
        Locator refreshBtn = page.locator(REFRESH_BUTTON_XPATH);
        try {
            AICorePageUtils.waitFor(refreshBtn);
            return refreshBtn.isVisible();
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public static boolean verifyEventHistorySection(Page page) {
        Locator eventHistorySection = page.locator(EVENT_HISTORY_SECTION_XPATH);
        try {
            AICorePageUtils.waitFor(eventHistorySection);
            return eventHistorySection.isVisible();
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public static boolean verifyPromptResponseTimelineSection(Page page) {
        Locator timelineSection = page.locator(PROMPT_RESPONSE_TIMELINE_SECTION_XPATH);
        try {
            AICorePageUtils.waitFor(timelineSection);
            return timelineSection.isVisible();
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public static boolean verifyTableExists(Page page) {
        Locator table = page.locator(DASHBOARD_TABLE_XPATH);
        try {
            AICorePageUtils.waitFor(table);
            return table.isVisible();
        } catch (PlaywrightException e) {
            return false;
        }
    }

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

    public static List<String> getTableColumnNames(Page page) {
        List<String> columns = new ArrayList<>();
        try {
            Locator columnHeaders = page.locator(TABLE_COLUMN_HEADERS_XPATH);
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

    public static void clickRefreshButton(Page page) {
        Locator refreshBtn = page.locator(REFRESH_BUTTON_XPATH);
        AICorePageUtils.waitFor(refreshBtn);
        refreshBtn.click();
        page.waitForTimeout(1500);
    }
}
