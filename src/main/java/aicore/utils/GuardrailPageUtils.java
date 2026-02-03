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
	private static final String CATALOG_NAME_FIELD_XPATH = "//div[@data-testid='guardrail-form-input-MODEL_NAME']//input";
	private static final String NER_LABELS_FIELD_XPATH = "//div[@data-testid='guardrail-form-input-NER_LABELS']//input";
	private static final String DEFAULT_THRESHOLD_FIELD_XPATH = "//div[@data-testid='guardrail-form-input-DEFAULT_THRESHOLD']//input";
	private static final String EXPORT_BUTTON_DATA_TESTID = "engineHeader-Guardrail-export-btn";
	private static final String TOAST_MESSAGE_DATA_TESTID = "notification-success-message";

	public static void clickOnAddGuardrailButton(Page page) {
		page.getByTestId(ADD_GUARDRAIL_BUTTON_DATA_TESTID).click();
	}

	public static void enterCatalogName(Page page, String catalogName) {
		page.locator(CATALOG_NAME_FIELD_XPATH).fill(catalogName);
	}

	public static void enterNerLabels(Page page, String label) {
		page.locator(NER_LABELS_FIELD_XPATH).fill(label);
		page.locator(NER_LABELS_FIELD_XPATH).press("Enter");
	}

	public static void enterDefaultThreshold(Page page, String threshold) {
		page.locator(DEFAULT_THRESHOLD_FIELD_XPATH).fill(threshold);
	}

	public static String verifyGuardrailTitle(Page page, String guardrailTitle) {
		Locator actualGuardrailTitle = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(guardrailTitle));
		AICorePageUtils.waitFor(actualGuardrailTitle);
		return actualGuardrailTitle.textContent().trim();
	}

	public static String verifyToastMessage(Page page) {
		Locator toast = page.getByTestId(TOAST_MESSAGE_DATA_TESTID);
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

}
