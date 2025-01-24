package e2e.admin;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import e2e.E2EAdminTests;

public class UserManagementTests extends E2EAdminTests {

	@Test
	void createAndDeleteUser() {
		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Apps")).click();
		page.getByLabel("Navigate to settings").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Admin Off")).click();
		page.getByText("Add new members, reset").click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add User")).click();
		page.getByLabel("Name").first().click();
		page.getByLabel("Name").first().fill("Joe Shmoe");
		page.locator("input[type=\"email\"]").fill("jshmoe@test.com");
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Phone Number$"))).locator("input")
				.fill("5555555555");
		page.getByLabel("Type").click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Native")).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^User Id$"))).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^User Id$"))).locator("input").fill("jshmoe");
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Username$"))).click();
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Username$"))).locator("input")
				.fill("jshmoe");
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
		assertThat(page.locator("#home__content")).matchesAriaSnapshot(
				"- navigation:\n  - list:\n    - listitem:\n      - link \"Settings\"\n    - listitem:\n      - link \"Member Settings\"\n- heading \"Member Settings\" [level=4]\n- button \"Privacy Center\"\n- button \"Admin On\"\n- paragraph: Add new members, reset passwords, and edit member-based permissions.\n- heading \"Users\" [level=6]\n- text: U 1 Users\n- textbox \"Search Users\"\n- button \"Add User\"\n- table:\n  - rowgroup:\n    - row \"User Role\":\n      - columnheader \"User\"\n      - columnheader \"Role\"\n      - columnheader\n  - rowgroup:\n    - 'row \"u u User ID: user1 Email: user1@example.com Type: Native Publisher Exporter Admin\"':\n      - 'cell \"u u User ID: user1 Email: user1@example.com Type: Native\"':\n        - paragraph: u\n        - paragraph: \"User ID:\"\n        - paragraph: user1\n        - paragraph: \"Email:\"\n        - paragraph: user1@example.com\n        - paragraph: \"Type:\"\n        - paragraph: Native\n      - cell \"Publisher Exporter Admin\":\n        - checkbox \"Publisher\" [checked]\n        - checkbox \"Exporter\" [checked]\n        - checkbox \"Admin\" [checked]\n      - cell:\n        - button\n        - button\n  - rowgroup:\n    - 'row \"Rows per page: 5 1–1 of 1 Go to previous page Go to next page\"':\n      - 'cell \"Rows per page: 5 1–1 of 1 Go to previous page Go to next page\"':\n        - paragraph: \"Rows per page:\"\n        - 'button \"Rows per page: 5\"'\n        - paragraph: 1–1 of 1\n        - button \"Go to previous page\" [disabled]\n        - button \"Go to next page\" [disabled]");
		assertThat(page.locator("tfoot")).matchesAriaSnapshot("- paragraph: 1–1 of 1");
	}

}
