package aicore.base;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DockerUtils {
	private static final Logger logger = LogManager.getLogger(DockerUtils.class);

	public static void startup() {
		pingAllServers();
	}
	
	private static void pingServer(String baseUrl, String endpoint, int retryCount, int timeoutInMilliseconds)  {
		int i = 0;
		boolean successful = false;
		String apiStringEndpoint = baseUrl + endpoint + "/api/config";
		logger.warn("attempting to ping: {}", apiStringEndpoint);
		while (i < retryCount) {
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
					logger.warn("Unsuccessful ping for {}, sleeping", apiStringEndpoint);
					Thread.sleep(timeoutInMilliseconds);
				}
			} catch (Exception e) {
				logger.error("Could not ping api or sleep: {}", apiStringEndpoint, e);
                try {
                    Thread.sleep(timeoutInMilliseconds);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
			i++;
		}
		if (!successful) {
			logger.error("Could not connect to api endpoint at: {}", apiStringEndpoint);
			throw new RuntimeException("Breaking, cannot connect to server at: " + apiStringEndpoint);
		}
	}


	public static void pingAllServers() {
		List<String> urls = RunInfo.getUrls();
		String first = urls.getFirst();
		String endpoint = RunInfo.getApiEndpoint();
		pingServer(first, endpoint, 120, 5000);
		if (urls.size() > 1) {
			// skip the first server, ping the rest of the servers.
			for (int i = 1; i < urls.size(); i++) {
				// set a more aggressive timeout since the other containers should be built
				// at the same time
				pingServer(urls.get(i), endpoint, 100, 1);
			}
		}
	}
}
