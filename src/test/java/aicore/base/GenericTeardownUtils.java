package aicore.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Tracing.StopOptions;

import aicore.utils.ConfigUtils;
import io.cucumber.java.Scenario;

public class GenericTeardownUtils {

	private static final Logger LOGGER = LogManager.getLogger(GenericTeardownUtils.class);

	public static void saveVideo(Scenario scenario, Path p) throws IOException {
		// TODO Auto-generated method stub
		if (Boolean.parseBoolean(ConfigUtils.getValue("use_video"))) {
			String name = scenario.getName();
			String videoName = name + ".webm";
			Path path = Paths.get("videos", videoName);
			Files.move(p, path);
		}

	}

	public static void saveTrace(Scenario scenario, BrowserContext context) {
		if (Boolean.parseBoolean(ConfigUtils.getValue("use_trace"))) {
			StopOptions so = new Tracing.StopOptions();
			String name = scenario.getName();
			String traceName = name + ".zip";
			so.setPath(Paths.get("traces", traceName));
			context.tracing().stop(so);
		}
	}

}
