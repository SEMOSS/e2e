package aicore.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonUtils {
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);
	private static final String NAME_TIMESTAMP_FORMAT = "ddHHmmss";

	public static String getTimeStampName() {
		return new SimpleDateFormat(NAME_TIMESTAMP_FORMAT).format(new Date());
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
		page.locator(accessControlXpath).click();
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

		BufferedImage expected = ImageIO.read(new File(expectedImagePath));
		BufferedImage actual = ImageIO.read(new File(actualImagePath));

		ImageComparison imageComparison = new ImageComparison(expected, actual);
		imageComparison.setDestination(new File(diffImagePath));
		ImageComparisonResult result = imageComparison.compareImages();

		return result.getDifferencePercent() == 0.0;
	}
}
