package aicore.framework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EnvUtils {

	private static Map<String, String> ENV_VARIABLES;

	/**
	 * Loads environment variables from .env, then overlays any values from .env.local.
	 * Values in .env.local overwrite those from .env; values not present in .env.local are kept unchanged.
	 * Stores the result internally and also returns it.
	 */
	public static Map<String, String> loadEnv() throws IOException {
		Path envFile = Paths.get(".env");
		Map<String, String> environment = new HashMap<>(parseEnvFile(envFile));

		Path localFile = Paths.get(".env.local");
		if (Files.exists(localFile)) {
			environment.putAll(parseEnvFile(localFile));
		}

		ENV_VARIABLES = environment;
		return environment;
	}

	public static String get(String key) {
		if (ENV_VARIABLES == null) {
			try {
				loadEnv();
			} catch (IOException e) {
				throw new RuntimeException("Failed to load .env file", e);
			}
		}
		return ENV_VARIABLES.get(key);
	}

	public static Map<String, String> getAll() {
		return ENV_VARIABLES;
	}

	/**
	 * Parses a .env-style file into a map of key-value pairs.
	 * Skips blank lines and comments (lines starting with #).
	 */
	private static Map<String, String> parseEnvFile(Path file) throws IOException {
		return Files.readAllLines(file).stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.filter(s -> !s.startsWith("#"))
				.filter(s -> s.contains("="))
				.map(s -> s.split("=", 2))
				.collect(Collectors.toMap(
						s -> s[0].trim(),
						s -> s.length > 1 ? s[1].trim() : ""));
	}
}
