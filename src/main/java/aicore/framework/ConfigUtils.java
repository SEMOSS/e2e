package aicore.framework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConfigUtils {

	private static final Logger logger = LogManager.getLogger(ConfigUtils.class);

	private static final Map<String, String> config = new LinkedHashMap<>();

	static {
		try {
			// Load chain: .env.defaults → .env → .env.local → System.getenv()
			loadFile(Paths.get(".env.defaults"));
			loadFile(Paths.get(".env"));
			loadFile(Paths.get(".env.local"));

			// System env vars override everything
			for (String key : config.keySet()) {
				String sysVal = System.getenv(key);
				if (sysVal != null) {
					config.put(key, sysVal);
				}
			}
		} catch (Exception e) {
			logger.error("Could not load configuration", e);
			throw new RuntimeException(e);
		}
	}

	private ConfigUtils() {
	}

	public static String getValue(String key) {
		// Also check system env for keys not in files
		String value = config.get(key);
		if (value == null) {
			value = System.getenv(key);
		}
		if (value == null || value.trim().isEmpty()) {
			logger.error("Value of key is empty: {}", key);
		}
		return value;
	}

	public static void setValue(String key, String value) {
		config.put(key, value);

		Path localFile = Paths.get(".env.local");
		try {
			Map<String, String> localEntries = new LinkedHashMap<>();
			if (Files.exists(localFile)) {
				List<String> lines = Files.readAllLines(localFile);
				for (String line : lines) {
					String trimmed = line.trim();
					if (!trimmed.isEmpty() && !trimmed.startsWith("#") && trimmed.contains("=")) {
						String[] parts = trimmed.split("=", 2);
						localEntries.put(parts[0].trim(), parts.length > 1 ? parts[1].trim() : "");
					}
				}
			}
			localEntries.put(key, value);

			List<String> output = localEntries.entrySet().stream()
					.map(e -> e.getKey() + "=" + e.getValue())
					.collect(Collectors.toList());
			Files.write(localFile, output);
			logger.info("Updated key: {} with value: {} in .env.local", key, value);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void loadFile(Path file) throws IOException {
		if (!Files.exists(file)) {
			return;
		}
		for (String line : Files.readAllLines(file)) {
			String trimmed = line.trim();
			if (trimmed.isEmpty() || trimmed.startsWith("#")) {
				continue;
			}
			if (!trimmed.contains("=")) {
				continue;
			}
			String[] parts = trimmed.split("=", 2);
			String k = parts[0].trim();
			String v = parts.length > 1 ? parts[1].trim() : "";
			config.put(k, v);
		}
	}
}
