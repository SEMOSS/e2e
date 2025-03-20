package aicore.base;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import aicore.utils.ConfigUtils;

public class DockerUtils {
	private static final Logger LOGGER = LogManager.getLogger(DockerUtils.class);

	public static void startup() {
		pingServer();
	}
	
	private static void pingServer()  {
		int i = 0;
		boolean successful = false;
		String apiStringEndpoint = getApi("/api/config");
		LOGGER.info("attempting to ping: {}", apiStringEndpoint);
		while (i < 10) {
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(apiStringEndpoint).openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(1000);
				int code = conn.getResponseCode();
				if (code == 200) {
					LOGGER.info("Successful ping");
					successful = true;
					break;
				} else {
					LOGGER.warn("Unsuccessful ping, sleeping");
					Thread.sleep(500);
				}
			} catch (Exception e) {
				LOGGER.error("Could not ping api or sleep", e);
			}
			i++;
		}
		if (!successful) {
			LOGGER.error("Could not connect to api endpoint at: {}", apiStringEndpoint);
			throw new RuntimeException("Breaking, cannot connect to server at: " + apiStringEndpoint);
		}
	}

	public static String getApi(String string) {
		String api = ConfigUtils.getValue("api");
		if (api == null) {
			throw new RuntimeException("Cannot run if api not set. Breaking");
		}
		return api + string;
	}

	public static String getUrl(String string) {
		String url = ConfigUtils.getValue("url");
		if (url == null) {
			throw new RuntimeException("Cannot run if url not set. Breaking");
		}
		return url + string;
	}
}
