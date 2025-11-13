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

//	public static String extractRelativePath(String fullUrl) {
//		if (fullUrl == null || fullUrl.isEmpty()) {
//			return "";
//		}
//		try {
//			URI uri = new URI(fullUrl);
//			StringBuilder relativePath = new StringBuilder();
//			// Extract path
//			String path = uri.getPath(); // e.g. "/SemossWeb/packages/client/dist/"
//			if (path != null && !path.isEmpty()) {
//				if (path.startsWith("/")) {
//					path = path.substring(1); // remove leading slash
//				}
//				relativePath.append(path);
//			}
//			// Extract fragment (handles hash routes like #/app/...)
//			String fragment = uri.getFragment();
//			if (fragment != null && !fragment.isEmpty()) {
//				if (!fragment.startsWith("/")) {
//					relativePath.append("/");
//				}
//				relativePath.append(fragment);
//			}
//			return relativePath.toString();
//		} catch (Exception e) {
//			return "";
//		}
//	}
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
