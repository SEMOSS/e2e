package aicore.utils;

public class UrlUtils {
	
	public static String api = ConfigUtils.getValue("baseUrl") + "Monolith/";
	public static String baseUrl = ConfigUtils.getValue("baseUrl") + "SemossWeb/";
	public static String url = baseUrl + "packages/client/dist/";

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
