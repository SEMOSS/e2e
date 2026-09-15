package aicore.utils.reporting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ArtifactCleanup {
	private static final Logger logger = LogManager.getLogger(ArtifactCleanup.class);

	private ArtifactCleanup() {
	}

	public static void cleanOlderThan(Path root, int retentionDays) {
		if (root == null || !Files.isDirectory(root)) {
			return;
		}
		Instant cutoff = Instant.now().minus(retentionDays, ChronoUnit.DAYS);
		try (Stream<Path> walk = Files.walk(root)) {
			walk.sorted(Comparator.reverseOrder()).forEach(path -> {
				try {
					if (path.equals(root) || !Files.exists(path)) {
						return;
					}
					if (Files.isDirectory(path)) {
						// remove directory only if it ended up empty after file cleanup
						try (Stream<Path> children = Files.list(path)) {
							if (children.findAny().isEmpty()) {
								Files.deleteIfExists(path);
							}
						}
						return;
					}
					Instant modified = Files.getLastModifiedTime(path).toInstant();
					if (modified.isBefore(cutoff)) {
						Files.deleteIfExists(path);
					}
				} catch (IOException e) {
					logger.warn("Failed to evaluate/delete artifact {}: {}", path, e.getMessage());
				}
			});
		} catch (IOException e) {
			logger.warn("Failed to walk artifact directory {}: {}", root, e.getMessage());
		}
	}
}
