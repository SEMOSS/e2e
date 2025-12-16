package aicore.pages;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
		List<Locator> locators = getLocatorsForTypeAndName(elementType, elementName);
		Path path = Paths.get(folderName, elementName + ".png");
		CaptureScreenShotUtils.captureScreenshot(page, locators, path);
	}

	// Generic method for fetching locator lists
	public List<Locator> getLocatorsForTypeAndName(String elementType, String elementName) {
		switch (elementType.toLowerCase()) {
		case "button":
			return CaptureElementUtils.captureButtonScreenshot(page, elementName);
		case "tab":
			return CaptureElementUtils.captureTabScreenshot(page, elementName);
		case "list item":
			return CaptureElementUtils.captureListItemScreenshot(page, elementName);
		case "tile":
			return CaptureElementUtils.captureTileScreenshot(page, elementName);
		case "heading":
			return CaptureElementUtils.captureHeadingScreenshot(page, elementName);
		case "searchbar":
			return CaptureElementUtils.captureSearchElementScreenshot(page, elementName);
		case "copycta":
			return CaptureElementUtils.captureCopyCTAScreenshot(page, "Copy");
		case "copyid":
			return CaptureElementUtils.captureCopyIDScreenshot(page, elementName);
		case "block":
			return CaptureElementUtils.captureBlockScreenshot(page, elementName);
		case "buttontype":
			return CaptureElementUtils.captureButtonTypeScreenshot(page, elementName);
		case "apptypetile":
			return CaptureElementUtils.captureAppTypeTab(page, elementName);
		case "usetemplatebutton":
			return CaptureElementUtils.captureUseTemplate(page, elementName);
		case "testidelement":
			return CaptureElementUtils.captureElementThroughtDataTestId(page, elementName);
		case "blocksettingelement":
			return CaptureElementUtils.captureBlockSettingElementScreenshot(page, elementName);
		case "section":
			return CaptureElementUtils.captureSectionScreenshot(page, elementName);
		default:
			throw new IllegalArgumentException("Unsupported element type: " + elementType);
		}
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
