package aicore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	
	private static final String NAME_TIMESTAMP_FORMAT = "ddHHmmss";
	 
	public static String getTimeStampName() {
		return new SimpleDateFormat(NAME_TIMESTAMP_FORMAT).format(new Date());
	}

}
