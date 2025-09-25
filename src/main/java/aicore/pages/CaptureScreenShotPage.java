package aicore.pages;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.utils.CaptureElementUtils;
import aicore.utils.CaptureScreenShotUtils;

public class CaptureScreenShotPage {

	private final Page page;

	public CaptureScreenShotPage(Page page) {
		this.page = page;

	}

	public void captureScreenshot(String elementType, String elementName, String folderName) throws IOException {
		Locator locators;
		switch (elementType.toLowerCase()) {
		case "button":
			locators = CaptureElementUtils.captureButtonScreenshot(page, elementName);
			break;
		case "tab":
			locators = CaptureElementUtils.captureTabScreenshot(page, elementName);
			break;
		case "list item":
			locators = CaptureElementUtils.captureListItemScreenshot(page, elementName);
			break;
		case "tile":
			locators = CaptureElementUtils.captureTileScreenshot(page, elementName);
			break;
		case "heading":
			locators = CaptureElementUtils.captureHeadingScreenshot(page, elementName);
			break;
		case "searchbar":
			locators = CaptureElementUtils.captureSearchElementScreenshot(page, elementName);
			break;
		case "copycta":
			String elementName1 = "Copy";
			locators = CaptureElementUtils.captureCopyCTAScreenshot(page, elementName1);
			break;
		default:
			throw new IllegalArgumentException("Unsupported element type: " + elementType);
		}
		Path path = Paths.get(folderName, elementName + ".png");
		CaptureScreenShotUtils.captureScreenshot(page, locators, path);

	}

	public void capturePageScreenshot(String pageName, String folderName) throws IOException {
		Path path = Paths.get(folderName, pageName + ".png");
		CaptureScreenShotUtils.captureScreenshot(page, path);
	}

	public void captureFormScreenshot(String formName, String folderName) throws IOException {
		Path path = Paths.get(folderName, formName + ".png");
		CaptureScreenShotUtils.captureFormScreenshot(page, path);
	}

	public void compareAndStoreResultsIfReady(String catalogName) throws IOException, Exception {
		CaptureScreenShotUtils.compareAndStoreResultsIfReady(page, catalogName);
	}
}
