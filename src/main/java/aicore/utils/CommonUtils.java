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
}
