package aicore.documentation.platformNavigation;

import java.nio.file.Path;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import aicore.base.GenericSetupUtils;
import aicore.framework.ConfigUtils;

public class DocumentationUtils {
	private static final Logger logger = LogManager.getLogger(DocumentationUtils.class);

	public static Page setupPlaywright(boolean initialSetup) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());

		Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions();
		BrowserContext context = browser.newContext(newContextOptions);

		context.grantPermissions(Arrays.asList("clipboard-read", "clipboard-write"));

		Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
		context.tracing().start(startOptions);

		Page page = context.newPage();
		page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		GenericSetupUtils.setupLoggers(page);

		if (initialSetup) {
			logger.info("Creating users");
			GenericSetupUtils.createUsers(page);
		}

		return page;
	}

	public static Locator focusOnElement(Page page, String elementePath) {
		Locator element = page.locator(elementePath);
		formatElementFocus(element);
		element.scrollIntoViewIfNeeded();
		return element;
	}

	public static Locator focusOnDataTestId(Page page, String dataTestId) {
		Locator element = page.getByTestId(dataTestId);
		formatElementFocus(element);
		element.scrollIntoViewIfNeeded();
		return element;
	}

	public static void formatElementFocus(Locator element) {
		element.evaluate("element => element.style.border = '3px solid red'");
	}

	public static void removeElementFocus(Locator element) {
		element.evaluate("element => element.style.border = ''");
	}

	public static void screenshot(Page page, Path outputFile) {
		page.screenshot(new Page.ScreenshotOptions().setPath(outputFile));
		logger.info("screenshot captured:::");
		logger.info(outputFile.toString());

	}

	public static void screenshot(Locator element, Path outputFile) {
		element.screenshot(new Locator.ScreenshotOptions().setPath(outputFile));
		logger.info("screenshot captured:::");
		logger.info(outputFile.toString());
	}

	public static void moveElementToTopOfScreen(Locator element) {
		// this is not ideal, but it shows the element
		element.evaluate("el => el.scrollIntoView({behavior: 'smooth', block: 'center'})");
	}

	public static void zoomPage(Page page, int zoom) {
		page.evaluate("document.body.style.zoom='" + zoom + "%'");
	}

}
