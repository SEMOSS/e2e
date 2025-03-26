package aicore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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

	public static String[] splitStringBySpace(String input) {
        if (input != null && !input.isEmpty()) {
            return input.trim().split("\\s+");  // Split by one or more spaces
        }
        return new String[0];  // Return an empty array if the string is null or empty
    }
	public static String splitString(String input) {
        // First, split the string by space
        String[] parts = input.split(" ");

        // Check if there's at least one part (to avoid ArrayIndexOutOfBoundsException)
        if (parts.length > 0) {
            // Trim the first part and then split it by the hyphen '-'
            String firstPart = parts[0].trim();
            String[] hyphenSplit = firstPart.split("-");

            // Return the first part after splitting by the hyphen
            if (hyphenSplit.length > 0) {
                return hyphenSplit[0];  // Return the first element after splitting by '-'
            }
        }

        // If the input doesn't meet expectations, return an empty string or null
        return "";
    }
}
