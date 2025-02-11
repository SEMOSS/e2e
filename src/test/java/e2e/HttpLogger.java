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
			.append("-------------------END REQUEST--------------------").append("\n").append("\n");

	private static final String REQUEST_LOG = REQUEST_SB.toString();

	private static final StringBuilder RESPONSE_SB = new StringBuilder()
			.append(" -------------------RESPONSE--------------------").append("\n").append("Received: {}").append("\n")
			.append("URL: {}").append("\n").append("Status: {}").append("\n").append("Text: {}").append("\n")
			.append("-------------------- END RESPONSE--------------------").append("\n").append("\n");

	private static final String RESPONSE_LOG = RESPONSE_SB.toString();

	public static void logRequest(Request s) {
		if (s.url().contains("/Monolith/")) {
			LOGGER.info(REQUEST_LOG, s, s.url(), s.method(), s.headers());
		}
	}

	public static void logResponse(Response s) {
		if (s.url().contains("/Monolith/")) {
			LOGGER.info(RESPONSE_LOG, s, s.url(), s.status(), "placeholder");
		}
	}

}
