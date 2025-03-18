package aicore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class ConfigUtils {

	private static final Logger LOGGER = LogManager.getLogger(ConfigUtils.class);

	private static Properties p;

	static {
		p = new Properties();
		FileInputStream f;
		try {
			//TODO: To be fixed for Continuous test Pipeline!
			LOGGER.info("Loading properties from file");
			Path pathToProps = Paths.get("src", "main", "resources", "config.properties");
			f = new FileInputStream(pathToProps.toFile());
			p.load(f);
			LOGGER.info("Loaded properties from file");
		} catch (Exception e) {
			LOGGER.error("Could not load properties", e);
			throw new RuntimeException(e);
		}
	}

	private ConfigUtils() {
	}

	public static String getValue(String key) {
		LOGGER.info("Getting value for key: {}", key);
		String value = p.getProperty(key);
		LOGGER.info("KEY: {} VALUE: {}", key, value);
		if(value==null || value.trim().length()==0) {
			LOGGER.error("Value of key is empty: {}", key);
			System.err.println("Property '"+key+"' is undefined in config.properties!");
		}
		return value;
	}

}