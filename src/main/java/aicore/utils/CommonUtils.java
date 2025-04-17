package aicore.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;

public class CommonUtils {

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


	public static String[] splitStringBySpace(String input) {
		if (input != null && !input.isEmpty()) {
			return input.trim().split("\\s+"); // Split by one or more spaces
		}
		return new String[0]; // Return an empty array if the string is null or empty
	}

}
