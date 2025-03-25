package aicore.utils;

public class UrlUtils {
	
	private static String api = ConfigUtils.getValue("baseUrl") + "Monolith/";
	private static String baseUrl = ConfigUtils.getValue("baseUrl") + "SemossWeb/";
	private static String url = baseUrl + "packages/client/dist/";

	public static String getApi(String path) {
		String x = api + path;
		return x;
	}

	public static String getUrl(String path) {
		String x = url + path;
		return x;
	}

	public static String getBaseUrl(String path) {
		String x = baseUrl + path;
		return x;
	}

}
