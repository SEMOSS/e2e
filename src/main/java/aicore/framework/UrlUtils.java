package aicore.framework;

import java.net.URI;

public class UrlUtils {

	private static String api = ConfigUtils.getValue("baseUrl") + "Monolith/";
	private static String baseUrl = ConfigUtils.getValue("baseUrl") + "SemossWeb/";
	private static String newWebRoute = "packages/client/dist/";

	public static String getApi(String path) {
		String base = ResourcePool.get().getUrl();
		String x = base + "Monolith/" + path;
		return x;
	}

	public static String getUrl(String path) {
		String base = ResourcePool.get().getUrl();
		String x = base + "SemossWeb/" + newWebRoute + path;
		return x;
	}

	public static String extractRelativePath(String fullUrl) {
		if (fullUrl == null || fullUrl.isEmpty()) {
			return "";
		}
		try {
			URI uri = new URI(fullUrl);
			String path = uri.getPath(); // e.g., "/SemossWeb/packages/client/dist/"
			if (path.startsWith("/")) {
				path = path.substring(1); // remove leading slash
			}
			return path;
		} catch (Exception e) {
			return "";
		}
	}

}
