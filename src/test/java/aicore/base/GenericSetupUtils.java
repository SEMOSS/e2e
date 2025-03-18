package aicore.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Tracing.StartOptions;

import aicore.utils.ConfigUtils;
import e2e.HttpLogger;

public class GenericSetupUtils {

	private static final Logger LOGGER = LogManager.getLogger(GenericSetupUtils.class);

	public static void initialize() throws IOException {
		logCheck();

		boolean useDocker = Boolean.parseBoolean(ConfigUtils.getValue("use_docker"));
		boolean useVideo = Boolean.parseBoolean(ConfigUtils.getValue("use_video"));
		boolean useTrace = Boolean.parseBoolean(ConfigUtils.getValue("use_trace"));
		LOGGER.info("docker: {}, videos: {}, traces: {}", useDocker, useVideo, useTrace);

		if (useDocker) {
			DockerUtils.startup();
		}

		if (useVideo) {
			Path p = Paths.get("videos");
			LOGGER.info("Videos will be saved to: {}", p.toString());
			if (Files.isDirectory(p)) {
				LOGGER.info("Cleaning directory: {}", p.toString());
				FileUtils.cleanDirectory(p.toFile());
			}
		}

		if (useTrace) {
			Path trace = Paths.get("traces");
			LOGGER.info("Traces will be saved to: {}", trace);
			if (Files.isDirectory(trace)) {
				LOGGER.info("Cleaning directory: {}", trace.toString());
				FileUtils.cleanDirectory(trace.toFile());
			}
		}
	}

	public static void logCheck() {
		LOGGER.info("Log check");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.warn("WARN");
		LOGGER.error("ERROR");
		LOGGER.fatal("FATAL");
		LOGGER.info("Log check end");
	}

	public static LaunchOptions getLaunchOptions() {
		LaunchOptions lp = new LaunchOptions();
		lp.setChannel(ConfigUtils.getValue("browserType"));
		lp.setHeadless(Boolean.parseBoolean(ConfigUtils.getValue("headless")));
		lp.setSlowMo(Double.parseDouble(ConfigUtils.getValue("slowmo")));
		lp.setTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

		return lp;
	}

	public static NewContextOptions getContextOptions() {
		NewContextOptions co = new Browser.NewContextOptions();
		if (Boolean.parseBoolean(ConfigUtils.getValue("use_video"))) {
			co.setRecordVideoDir(Paths.get("videos"));
			co.setRecordVideoSize(1920, 1080);
			co.setViewportSize(1920, 1080);
		}
		
		if (Boolean.parseBoolean(ConfigUtils.getValue("use_state"))) {
			co.setStorageStatePath(Paths.get("state.json"));
		}
		return co;
	}
	
	
	public static StartOptions getStartOptions() {
		StartOptions so = new Tracing.StartOptions();
		so.setScreenshots(true);
		so.setSnapshots(true);
		so.setSources(true);
		return so;
	}

	public static void setupLoggers(Page page) {
		// request handling
		page.onRequest(HttpLogger::logRequest);
		
		// response handling
		page.onResponse(HttpLogger::logResponse);
	}

}
