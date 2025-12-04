package aicore.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.microsoft.playwright.JSHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.BoundingBox;

import aicore.framework.ConfigUtils;
import aicore.framework.UrlUtils;

public class CommonUtils {
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);
	private static final String NAME_TIMESTAMP_FORMAT = "ddHHmmss";

	private static final String SEARCH_CATALOG_LABEL = "Search";
	private static final String CLICK_ON_CATALOG_XPATH = "//div[@role='img' and contains(@class,'MuiCardMedia-root')]";
	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	static final String STORAGE_SETTING_XPATH = "//button[text()='Settings']";

	private static final String APP_DELETE_BUTTON_XPATH = "//li[@value='delete']";
	private static final String APP_CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialogActions-root')]//button[normalize-space()='Delete']";
	private static final String APP_DELETE_TOAST_MESSAGE_XPATH = "//div[@role='alert' and //*[name()='svg'][@data-testid='SuccessOutlinedIcon']]//div[contains(@class,'MuiAlert-message') and normalize-space()='Successfully deleted']";

	private static final String THREE_DOT_ICON_DATATESTID = "MoreVertIcon";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
	private static final String DELETE_TOAST_MESSAGE_XPATH = "//div[contains(text(),'Successfully deleted')]";

	public static String getTimeStampName() {
		return new SimpleDateFormat(NAME_TIMESTAMP_FORMAT).format(new Date());
	}

	public static void removeTargetAttribute(Locator anchor) {
		anchor.evaluate("element => element.setAttribute('target', '')");
	}

	public static String splitTrimValue(String keyValueString, String key) {
		String actualName = null;
		if (keyValueString != null && !keyValueString.isEmpty()) {
			keyValueString = keyValueString.replaceAll("\\u00A0", " ");
			// Now split the text on "NAME" and get the second part (after NAME)
			if (keyValueString.contains(key)) {

				String[] parts = keyValueString.split(key + "\\s+");
				if (parts.length > 1) {
					actualName = parts[1].trim();
				}
			}
		}
		return actualName;
	}

	public static String getTodayDateFormatted() {
		// Get today's date
		LocalDate today = LocalDate.now();

		// Define the formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Return formatted date
		return today.format(formatter);
	}

	public static void extractOverviewSectionValues(List<String> keys, List<String> keyText) {
		for (String text : keyText) {
			String[] splitTags = text.split("[," + System.lineSeparator() + "]+");
			for (String tag : splitTags) {
				if (!tag.trim().isEmpty()) {
					keys.add(tag.trim());
				}
			}
		}

	}

	public static int countIdOccurances(String section, String id) {
		String pattern = "\\b" + Pattern.quote(id) + "\\b";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(section);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	public static List<String> getAppliedStyles(Locator element) {
		List<String> appliedStyles = new ArrayList<>();

		// Font Weight - Bold
		String fontWeight = element.evaluate("el => el.style.fontWeight || getComputedStyle(el).fontWeight").toString();
		String numericPart = fontWeight.replaceAll("[^0-9]", "");

		if (!numericPart.isEmpty()) {
			int numericWeight = Integer.parseInt(numericPart);
			if (numericWeight >= 600) {
				appliedStyles.add("Bold");
			}
		} else if (fontWeight.equalsIgnoreCase("bold")) {
			appliedStyles.add("Bold");
		}

		// Font Style - Italic
		String fontStyle = element.evaluate("el => el.style.fontStyle || getComputedStyle(el).fontStyle").toString();
		if (fontStyle.equalsIgnoreCase("italic")) {
			appliedStyles.add("Italic");
		}

		// Text Decoration - Underline
		String textDecoration = element
				.evaluate("el => el.style.textDecorationLine || getComputedStyle(el).textDecorationLine").toString();
		if (textDecoration.toLowerCase().contains("underline")) {
			appliedStyles.add("Underlined");
		}
		return appliedStyles;
	}

	public static String hexToRGBConversion(String hex) {
		hex = hex.replace("#", "");

		int r = Integer.parseInt(hex.substring(0, 2), 16);
		int g = Integer.parseInt(hex.substring(2, 4), 16);
		int b = Integer.parseInt(hex.substring(4, 6), 16);

		return r + ", " + g + ", " + b;
	}

	public static String[] splitStringBySpace(String input, int index) {
		if (input != null && !input.isEmpty()) {
			return input.trim().split("\\s+"); // Split by one or more spaces
		}
		return new String[index]; // Return an empty array if the string is null or empty
	}

	public static boolean deleteCatalog(Page page, String accessControlXpath, String deleteButtonXpath,
			String confirmationPopupXpath, String deleteToastMessageXpath) {
		Locator accessLocator = page.locator(accessControlXpath);
		AICorePageUtils.waitFor(accessLocator);
		accessLocator.click();
		page.locator(deleteButtonXpath).click();
		page.locator(confirmationPopupXpath).click();
		return page.locator(deleteToastMessageXpath).isVisible();
	}

	public static boolean isIconValid(String iconUrl) {
		try {
			if (iconUrl == null || iconUrl.isEmpty()) {
				return false;
			}
			if (iconUrl.startsWith("data:image")) {
				String base64Data = iconUrl.substring(iconUrl.indexOf(",") + 1);
				byte[] imageBytes = null;
				try {
					imageBytes = Base64.getDecoder().decode(base64Data);
				} catch (IllegalArgumentException e) {
					return false; // invalid base64
				}
				if (!iconUrl.startsWith("data:image/svg+xml")) {
					// PNG, JPEG, etc.
					if (imageBytes != null) {
						BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
						return (image != null);
					}
					return false;
				} else {
					// SVG image
					return true;
				}
			}
			// Handle traditional image URLs (http, https, file)
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(iconUrl))
					.method("GET", HttpRequest.BodyPublishers.noBody()).build();
			HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
			return (response.statusCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			logger.error("icon validation failed", e);
			return false;
		}
	}

	public static boolean compareImages(String actualImagePath, String expectedImagePath, String diffImagePath)
			throws Exception {
		File actualFile = new File(actualImagePath);
		File expectedFile = new File(expectedImagePath);
		File diffFile = new File(diffImagePath);
		diffFile.getParentFile().mkdirs();
		if (!expectedFile.exists()) {
			throw new IllegalStateException("Expected image not found at: " + expectedImagePath);
		}
		BufferedImage expected = ImageIO.read(expectedFile);
		BufferedImage actual = ImageIO.read(actualFile);
		if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {
			logger.info("Resizing actual image to match expected dimensions...");
			actual = resizeImage(actual, expected.getWidth(), expected.getHeight());
		}
		expected = applyBlur(expected);
		actual = applyBlur(actual);
		ImageComparison imageComparison = new ImageComparison(expected, actual);
		ImageComparisonResult result = imageComparison.compareImages();
		double diffPercent = result.getDifferencePercent();
		logger.info(String.format("Image comparison completed. Difference: %.3f%%", diffPercent));
		double threshold = 1.5;
		boolean areSimilar = diffPercent <= threshold;
		if (!areSimilar) {
			logger.warn("Images differ beyond acceptable threshold. Creating diff image...");
			imageComparison.setDestination(diffFile);
			imageComparison.compareImages();
		}
		return areSimilar;
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		int imageType = originalImage.getColorModel().hasAlpha() ? BufferedImage.TYPE_INT_ARGB
				: BufferedImage.TYPE_INT_RGB;
		BufferedImage resized = new BufferedImage(targetWidth, targetHeight, imageType);
		Graphics2D g2d = resized.createGraphics();
		// Consistent cross-platform rendering settings
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		g2d.dispose();
		return resized;
	}

	private static BufferedImage applyBlur(BufferedImage image) {
		float[] kernel = { 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f };
		BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, kernel));
		return op.filter(image, null);
	}

	public static void moveMouseToCenter(Page page, Locator locator, int steps) {
		BoundingBox box = locator.boundingBox();
		page.mouse().move(box.x + (box.width / 2), (box.y + box.height / 2), new Mouse.MoveOptions().setSteps(steps));
	}

	public static void moveMouseToCenterWithMargin(Page page, Locator locator, int margin, int steps) {
		BoundingBox box = locator.boundingBox();
		page.mouse().move(box.x + (box.width / 2), (box.y + box.height + margin),
				new Mouse.MoveOptions().setSteps(steps));
	}

	public static String readCopiedTextFromClipboard(Page page) {
		JSHandle handle = page.evaluateHandle("async () => await navigator.clipboard.readText()");
		try {
			return handle.jsonValue().toString();
		} catch (PlaywrightException e) {
			throw new RuntimeException("Failed to read text from clipboard", e);
		}
	}

	public static boolean getVersion(Page page) {
		HomePageUtils.openMainMenu(page);
		page.getByTestId("AccountCircleRoundedIcon").click();
		String version = CaptureScreenShotUtils.versionCapture(page);

		logger.info("Version obtained: {}", version);
		logger.info("Current version: {}", ConfigUtils.getValue("current_version"));
		if (version.equals(ConfigUtils.getValue("current_version"))) {
			logger.info("Version match: {}", version);
			return true;
		} else {
			logger.error("Version mismatch: expected {}, got {}", ConfigUtils.getValue("current_version"), version);
			return false;
		}
	}

	public static boolean navigateAndDeleteCatalog(Page page, String catalogType, String catalogId) {
		try {
			page.navigate(UrlUtils.getUrl("#/"));
			HomePageUtils.openMainMenu(page);
			switch (catalogType) {
			case TestResourceTrackerHelper.CATALOG_TYPE_DATABASE -> HomePageUtils.clickOnOpenDatabase(page);
			case TestResourceTrackerHelper.CATALOG_TYPE_MODEL -> HomePageUtils.clickOnOpenModel(page);
			case TestResourceTrackerHelper.CATALOG_TYPE_VECTOR -> HomePageUtils.clickOnOpenVector(page);
			case TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION -> HomePageUtils.clickOnOpenFunction(page);
			case TestResourceTrackerHelper.CATALOG_TYPE_STORAGE -> HomePageUtils.clickOnOpenStorage(page);
			default -> throw new IllegalArgumentException("Invalid catalog type: " + catalogType);
			}
			page.getByLabel(SEARCH_CATALOG_LABEL).fill(catalogId);
			page.waitForTimeout(500);
			page.locator(CLICK_ON_CATALOG_XPATH).click();

			if (page.locator(ACCESS_CONTROL_XPATH).isVisible()) {
				page.locator(ACCESS_CONTROL_XPATH).click();

			} else if (page.locator(STORAGE_SETTING_XPATH).isVisible()) {
				page.locator(STORAGE_SETTING_XPATH).click();
			}
			page.locator(DELETE_BUTTON_XPATH).click();
			page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
			return page.locator(DELETE_TOAST_MESSAGE_XPATH).isVisible();
		} catch (Exception e) {
			logger.warn("Catalog deletion failed due to an exception", e);
			return false;
		}
	}

	public static boolean navigateAndDeleteApp(Page page, String appName) {
		try {
			page.navigate(UrlUtils.getUrl("#/"));
			HomePageUtils.openMainMenu(page);
			HomePageUtils.clickOnOpenAppLibrary(page);
			page.getByLabel(SEARCH_CATALOG_LABEL).fill(appName);
			page.waitForTimeout(500);
			page.getByTestId(THREE_DOT_ICON_DATATESTID).click();
			page.locator(APP_DELETE_BUTTON_XPATH).click();
			page.locator(APP_CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
			boolean deleted = page.locator(APP_DELETE_TOAST_MESSAGE_XPATH).isVisible();
			return deleted;
		} catch (Exception e) {
			logger.warn("App deletion failed due to an exception", e);
			return false;
		}
	}

	public static String getCurrentUtcTime() {
		return LocalDateTime.now(ZoneOffset.UTC).withNano(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
