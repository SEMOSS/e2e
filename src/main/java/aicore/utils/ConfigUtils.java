package aicore.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ConfigUtils {

	private static Properties p;

	static {
		p = new Properties();
		FileInputStream f;
		try {
			//TODO: To be fixed for Continuous test Pipeline!
			f = new FileInputStream("./src/main/resources/config.properties");
			p.load(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ConfigUtils() {
	}

	public static String getValue(String key) {
		String value = p.getProperty(key);
		if(value==null || value.trim().length()==0) {
			System.err.println("Property '"+key+"' is undefined in config.properties!");
		}
		return value;
	}

}