package aicore.utils;

public class LastCreatedUser {
	private static String userId;
	private static String password;
	private static String name;
	private static String email;

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String id) {
		userId = id;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String pw) {
		password = pw;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String nm) {
		name = nm;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String em) {
		email = em;
	}
}
