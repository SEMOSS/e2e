package e2e;

public class MonolithCookieJar {
	
	private static String cookie = null;
	
	public static String getCookie() {
		if (cookie != null) {
			return cookie;
		}
		
		return cookie;
	}
	
	
	public static void setCookie(String c) {
		cookie = c;
	}
	

}
