package e2e;

public class Utility {
	
	
	private static boolean registered = false;
	
	public static boolean getRegistered() {
		if (!registered) {
			registered = true;
			return false;
		}
		return registered;
	}

}
