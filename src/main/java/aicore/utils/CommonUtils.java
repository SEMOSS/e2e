package aicore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public static String[] splitStringBySpace(String input) {
		if (input != null && !input.isEmpty()) {
			return input.trim().split("\\s+"); // Split by one or more spaces
		}
		return new String[0]; // Return an empty array if the string is null or empty
	}
}
