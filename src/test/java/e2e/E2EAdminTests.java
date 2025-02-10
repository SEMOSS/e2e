package e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;

public class E2EAdminTests extends E2ETests {

	@BeforeEach
	public void loginToAdmin() {
		page.navigate(getUrl("https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login"));
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill("user1");
		page.getByLabel("Username").press("Tab");
		page.locator("input[type=\"password\"]").fill("TestTest8*");
		Response response = page.waitForResponse(getApi("/api/auth/login"), () -> page
				.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
		assertEquals(200, response.status());
	}

	@AfterEach
	public void logout() {
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^SEMOSS$")))
				.getByRole(AriaRole.BUTTON).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();

		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
		assertEquals(getUrl("/packages/client/dist/#/login"), page.url());
	}

}
