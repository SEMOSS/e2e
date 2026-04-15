package aicore.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GuardrailPageUtils {
	private static final String ADD_GUARDRAIL_BUTTON_DATA_TESTID = "engineIndex-add-Guardrail-btn";
	private static final String EXPORT_BUTTON_DATA_TESTID = "engineHeader-Guardrail-export-btn";
	private static final String TOAST_MESSAGE_DATA_TESTID = "notification-success-message";
	private static final String GUARDRAIL_TOAST_MESSAGE_XPATH = "//div[text()='{toastMessage}']";
	private static final String SELECT_GUARDRAIL_CATALOG_XPATH = "//p[text()='{catalogName}']";

	public static void clickOnAddGuardrailButton(Page page) {
		page.getByTestId(ADD_GUARDRAIL_BUTTON_DATA_TESTID).click();
	}

	public static String verifyGuardrailTitle(Page page, String guardrailTitle) {
		Locator actualGuardrailTitle = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(guardrailTitle));
		AICorePageUtils.waitFor(actualGuardrailTitle);
		return actualGuardrailTitle.textContent().trim();
	}

	public static String verifyToastMessage(Page page, String toastMessage) {
		Locator toast = page.locator(GUARDRAIL_TOAST_MESSAGE_XPATH.replace("{toastMessage}", toastMessage));
		return AICorePageUtils.verifySuccessToastMessage(page, toast);
	}

	public static Path downloadCatalog(Page page) {
		Download download = page.waitForDownload(() -> {
			page.getByTestId(EXPORT_BUTTON_DATA_TESTID).click();
		});
		Path downloadPath = Paths.get("downloads", download.suggestedFilename());
		try {
			Files.createDirectories(downloadPath.getParent());
			download.saveAs(downloadPath);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save downloaded file", e);
		}
		return downloadPath;
	}

	public static void searchGuardrailCatalog(Page page, String searchText) {
		Locator searchBox = page.getByTestId("search-bar");
		searchBox.fill(searchText);
		page.waitForTimeout(300);
	}

	public static boolean verifySearchedGuardrailCatalogTitle(Page page, String guardrailTitle) {
		Locator searchBox = page.locator(SELECT_GUARDRAIL_CATALOG_XPATH.replace("{catalogName}", guardrailTitle));
		return searchBox.isVisible();
	}

	public static void selectTheGuardrailCatalog(Page page, String catalogName) {
		Locator searchBox = page.locator(SELECT_GUARDRAIL_CATALOG_XPATH.replace("{catalogName}", catalogName));
		searchBox.click();
	}

}
