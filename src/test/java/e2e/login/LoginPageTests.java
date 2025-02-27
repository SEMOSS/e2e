package e2e.login;

import e2e.E2ETests;

public class LoginPageTests extends E2ETests {

//	//@BeforeEach
//	void goToLoginPage() {
//		page.navigate(getUrl("/packages/client/dist/#/login"));
//	}
//
//	//@Test
//	void testLoginIncorrect() {
//		page.getByLabel("Username").click();
//		page.getByLabel("Username").fill("wronguser");
//		page.getByLabel("Username").press("Tab");
//		page.locator("input[type=\"password\"]").fill("wrongpassword");
//		Response response = page.waitForResponse(getApi("/api/auth/login"), () -> page
//				.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
//		assertEquals(401, response.status());
//		JSONObject json = new JSONObject(response.text());
//		assertEquals("The user name or password are invalid.", json.get("errorMessage"));
//
//		assertTrue(page.getByRole(AriaRole.ALERT).isVisible());
//		assertTrue(page.getByText("The user name or password are invalid.").isVisible());
//	}
//
//	//@Test
//	void loginAndLogout() {
//		page.getByLabel("Username").click();
//		page.getByLabel("Username").fill("user1");
//		page.getByLabel("Username").press("Tab");
//		page.locator("input[type=\"password\"]").fill("TestTest8*");
//		Response response = page.waitForResponse(getApi("/api/auth/login"), () -> page
//				.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
//		assertEquals(200, response.status());
//		page.waitForLoadState(LoadState.NETWORKIDLE);
//		page.waitForLoadState(LoadState.LOAD);
//		page.navigate(getUrl("/packages/client/dist/#"));
//		page.waitForLoadState(LoadState.NETWORKIDLE);
//		page.waitForLoadState(LoadState.LOAD);
//
//		// click has auto wait. so click and then check for URL
//		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^SEMOSS$")))
//				.getByRole(AriaRole.BUTTON).click();
//
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
//
//		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
//		assertEquals(getUrl("/packages/client/dist/#/login"), page.url());
//	}

}
