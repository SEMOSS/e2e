package aicore.base;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import aicore.utils.ConfigUtils;
import aicore.utils.UrlUtils;

public class DockerUtils {
	private static final Logger logger = LogManager.getLogger(DockerUtils.class);

	public static void startup() {
		pingServer();
	}
	
	private static void pingServer()  {
		int i = 0;
		boolean successful = false;
		String apiStringEndpoint = UrlUtils.getApi("api/config");
		logger.info("attempting to ping: {}", apiStringEndpoint);
		while (i < 10) {
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(apiStringEndpoint).openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(1000);
				int code = conn.getResponseCode();
				if (code == 200) {
					logger.info("Successful ping");
					successful = true;
					break;
				} else {
					logger.warn("Unsuccessful ping, sleeping");
					Thread.sleep(500);
				}
			} catch (Exception e) {
				logger.error("Could not ping api or sleep", e);
			}
			i++;
		}
		if (!successful) {
			logger.error("Could not connect to api endpoint at: {}", apiStringEndpoint);
			throw new RuntimeException("Breaking, cannot connect to server at: " + apiStringEndpoint);
		}
	}
}
