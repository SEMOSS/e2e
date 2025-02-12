package e2e;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Request;
import com.microsoft.playwright.Response;

public class HttpLogger {

	private static final Logger LOGGER = LogManager.getLogger(HttpLogger.class);

	private static final StringBuilder REQUEST_SB = new StringBuilder()
			.append(" -------------------REQUEST--------------------").append("\n").append("Sent: {}").append("\n")
			.append("URL: {}").append("\n").append("Method: {}").append("\n").append("Headers: {}").append("\n")
			.append("Body: {}").append("\n").append("-------------------END REQUEST--------------------").append("\n")
			.append("\n");

	private static final String REQUEST_LOG = REQUEST_SB.toString();

	private static final StringBuilder RESPONSE_SB = new StringBuilder()
			.append(" -------------------RESPONSE--------------------").append("\n").append("Received: {}").append("\n")
			.append("URL: {}").append("\n").append("Status: {}").append("\n").append("Headers: {}").append("\n")
			.append("Text: {}").append("\n").append("-------------------- END RESPONSE--------------------")
			.append("\n").append("\n");

	private static final String RESPONSE_LOG = RESPONSE_SB.toString();

	public static void logRequest(Request s) {
		if (s.url().contains("/Monolith/")) {
			try {
				LOGGER.info(REQUEST_LOG, s, s.url(), s.method(), s.allHeaders(), s.postData());
			} catch (Exception e) {
				LOGGER.error("Couldn't log request {}\n{}", s.url(), e.getMessage());
			}
		}
	}

	public static void logResponse(Response s) {
		if (s.url().contains("/Monolith/")) {
			if (s.url().contains("milkyway.jpg")) {
				LOGGER.info("Milkway.jpg {}", s.status());
			} else {
				logReasonableResponse(s);
			}
		}
	}

	private static void logReasonableResponse(Response s) {
		try {
			if (s.ok()) {
				LOGGER.info(RESPONSE_LOG, s, s.url(), s.status(), s.allHeaders(), s.text());
			} else if (s.status() == 302) {
				LOGGER.warn(RESPONSE_LOG, s, s.url(), s.status(), s.allHeaders(), s.statusText());
			} else {
				LOGGER.error(RESPONSE_LOG, s, s.url(), s.status(), s.allHeaders(), s.statusText());
			}
		} catch (Exception e) {
			LOGGER.error("Couldn't log response {}\n{}", s.url(), e.getMessage());
		}
	}

}
