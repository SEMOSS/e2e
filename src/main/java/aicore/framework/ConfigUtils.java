package aicore.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConfigUtils {

	private static final Logger logger = LogManager.getLogger(ConfigUtils.class);

	private static Properties p;

	static {
		p = new Properties();
		FileInputStream f;
		try {
			logger.info("Loading properties from file");
			Path pathToProps = Paths.get("src", "main", "resources", "local.properties");
			if (!Files.exists(pathToProps)) {
				pathToProps = Paths.get("src", "main", "resources", "config.properties");
			}
			logger.info("Loading properties from file: {}", pathToProps);
			f = new FileInputStream(pathToProps.toFile());
			p.load(f);
			logger.info("Loaded properties from file");
			p.entrySet().stream().forEach(entry -> {
				logger.info("Key: " + entry.getKey() + " Value: " + entry.getValue());
			});
		} catch (Exception e) {
			logger.error("Could not load properties", e);
			throw new RuntimeException(e);
		}
	}

	private ConfigUtils() {
	}

	public static String getValue(String key) {
		logger.info("Getting value for key: {}", key);
		String value = p.getProperty(key);
		logger.info("KEY: {} VALUE: {}", key, value);
		if (value == null || value.trim().length() == 0) {
			logger.error("Value of key is empty: {}", key);
			System.err.println("Property '" + key + "' is undefined in config.properties!");
		}
		return value;
	}
	public static void setValue(String key, String value) {
    Path pathToProps = Paths.get("src", "main", "resources", "local.properties");
    if (!Files.exists(pathToProps)) {
        pathToProps = Paths.get("src", "main", "resources", "config.properties");
    }

    try {
        java.util.List<String> lines = Files.readAllLines(pathToProps);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String trimmedLine = line.trim();
            if (!trimmedLine.startsWith("#") && !trimmedLine.startsWith("##") && !trimmedLine.isEmpty() && trimmedLine.contains("=")) {
                String[] parts = trimmedLine.split("=", 2);
                String currentKey = parts[0].trim();
                if (currentKey.equals(key)) {
                    String leading = line.substring(0, line.indexOf(parts[0]));
                    lines.set(i, leading + key + "=" + value);
                    Files.write(pathToProps, lines);
                    logger.info("Updated key: {} with value: {}", key, value);
                    break;
                }
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

}