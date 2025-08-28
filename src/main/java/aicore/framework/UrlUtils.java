package aicore.framework;

public class UrlUtils {
	
	private static String api = ConfigUtils.getValue("baseUrl") + "Monolith/";
	private static String baseUrl = ConfigUtils.getValue("baseUrl") + "SemossWeb/";
	private static String url = baseUrl + "packages/client/dist/";

	public static String getApi(String path) {
		String base = ResourcePool.get().getUrl();
		String x = base + "Monolith/" + path;
		return x;
	}

	public static String getUrl(String path) {
		String base = ResourcePool.get().getUrl();
		String x = base + "SemossWeb/" + path;
		return x;
	}

}
