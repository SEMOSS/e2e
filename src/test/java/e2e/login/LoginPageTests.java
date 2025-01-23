package e2e.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;

public class LoginPageTests {

	// Shared between all tests in this class.
	static Playwright playwright;
	static Browser browser;

	// New instance for each test method.
	BrowserContext context;
	Page page;

	@BeforeAll
	static void launchBrowser() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch();
		//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	}

	@AfterAll
	static void closeBrowser() {
		playwright.close();
	}

	@BeforeEach
	void createContextAndPage() {
		context = browser.newContext();
		page = context.newPage();
	}

	@AfterEach
	void closeContext() {
		context.close();
	}

	@Test
	void testLoginIncorrect() {
		page.navigate("https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login");
		page.locator("div")
				.filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Here's how we use cookies$")))
				.getByRole(AriaRole.BUTTON).click();
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill("wronguser");
		page.getByLabel("Username").press("Tab");
		page.locator("input[type=\"password\"]").fill("wrongpassword");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click();
		Response response = page
				.waitForResponse("https://workshop.cfg.deloitte.com/cfg-ai-demo/Monolith/api/auth/login", () -> page
						.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
		assertEquals(401, response.status());
		JSONObject json = new JSONObject(response.text());
		assertEquals("The user name or password are invalid.", json.get("errorMessage"));

		assertTrue(page.getByRole(AriaRole.ALERT).isVisible());
		assertTrue(page.getByText("The user name or password are invalid.").isVisible());
		System.out.println();
	}

}
