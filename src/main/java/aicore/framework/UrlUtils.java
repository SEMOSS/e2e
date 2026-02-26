package aicore.framework;

import java.net.URI;

public class UrlUtils {

	private static String newWebRoute = "packages/client/dist/";

	public static String getApi(String path) {
		String base = ResourcePool.get().getUrl();
		String x = base + ConfigUtils.getValue("API_ENDPOINT") + "/" + path;
		return x;
	}

	public static String getUrl(String path) {
		String base = ResourcePool.get().getUrl();
		String x = base + ConfigUtils.getValue("FRONTEND") +"/" + newWebRoute + path;
		return x;
	}

	public static String getBaseFrontendUrl(String path) {
		String base = ResourcePool.get().getUrl();
		String x = base + ConfigUtils.getValue("FRONTEND") +"/" + path;
		return x;
	}

	public static String extractRelativePath(String fullUrl) {
		// Extract relative path + fragment (if any)
		String actualRelativeUrl = "";
		if (fullUrl != null && !fullUrl.isEmpty()) {
			try {
				URI uri = new URI(fullUrl);
				String path = uri.getPath(); // e.g., "/SemossWeb/packages/client/dist/"
				String fragment = uri.getFragment(); // e.g., "app/uuid/view/resources"
				String combined = (fragment != null && !fragment.isEmpty()) ? path + "#" + fragment : path;
				if (combined.startsWith("/")) {
					combined = combined.substring(1);
				}
				actualRelativeUrl = combined;
			} catch (Exception e) {
				actualRelativeUrl = fullUrl; // fallback
			}
		}
		// Normalize: remove duplicate slashes, trailing slash
		actualRelativeUrl = actualRelativeUrl.replaceAll("//+", "/");
		if (actualRelativeUrl.endsWith("/")) {
			actualRelativeUrl = actualRelativeUrl.substring(0, actualRelativeUrl.length() - 1);
		}
		// Replace dynamic UUIDs with wildcard for regex matching
		String normalizedActual = actualRelativeUrl
				.replaceAll("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", ".*");
		return normalizedActual;
	}
}
