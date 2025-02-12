package e2e.admin;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import e2e.E2EAdminTests;

public class UserManagementTests extends E2EAdminTests {

	private static final Logger LOGGER = LogManager.getLogger(UserManagementTests.class);

	@Test
	void createAndDeleteUser() {
		page.getByLabel("Navigate to app library").click();
		page.getByTestId("Function-icon").click();
		page.getByTestId("Model-icon").click();
		page.getByTestId("Database-icon").click();
		page.getByTestId("Vector-icon").click();
		page.getByTestId("Storage-icon").click();
		page.getByLabel("Navigate to settings").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Admin Off")).click();
		page.getByText("Add new members, reset").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add User")).click();
		page.getByLabel("Name").first().click();
		page.getByLabel("Name").first().fill("Joe Shmoe");
		page.locator("input[type=\"email\"]").fill("jshmoe@test.com");
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Phone Number$")))
				.locator("input").fill("5555555555");
		page.getByLabel("Type").click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Native")).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^User Id$"))).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^User Id$")))
				.locator("input").fill("jshmoe");
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Username$"))).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Username$")))
				.locator("input").fill("jshmoe");
		page.getByLabel("Password").click();
		page.getByLabel("Password").fill("TestTest9*");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
		page.getByText("Joe Shmoe").click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^SEMOSS$")))
				.getByRole(AriaRole.BUTTON).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
		page.getByLabel("Username").click();
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill("jshmoe");
		page.locator("input[type=\"password\"]").click();
		page.locator("input[type=\"password\"]").fill("TestTest9*");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SEMOSS")).click();
		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Apps")).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^SEMOSS$")))
				.getByRole(AriaRole.BUTTON).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
		page.getByLabel("Username").click();
		page.getByLabel("Username").fill("user1");
		page.getByLabel("Username").press("Tab");
		page.locator("input[type=\"password\"]").fill("TestTest8*");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click();
		page.getByLabel("Navigate to settings").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Admin Off")).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Member Settings$"))).first()
				.click();
		page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("JS Joe Shmoe User ID: jshmoe"))
				.getByRole(AriaRole.BUTTON).nth(1).click();
		assertThat(page.locator("tfoot")).matchesAriaSnapshot("- paragraph: 1–1 of 1");
	}

}
