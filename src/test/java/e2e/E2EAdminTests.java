package e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class E2EAdminTests extends E2ETests {
	
	private static final Logger LOGGER = LogManager.getLogger(E2EAdminTests.class);

	@BeforeEach
	public void loginToAdmin() {
		String username = "user1";
		String password = "TestTest8*";
		LoginUtils.login(page, context, username, password);
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
