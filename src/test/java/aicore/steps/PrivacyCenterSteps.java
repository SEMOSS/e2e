package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.PrivacyCenterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrivacyCenterSteps {

	private PrivacyCenterPage privacyCenterPage;
	private Page newTab;

	public PrivacyCenterSteps() {
		privacyCenterPage = new PrivacyCenterPage(SetupHooks.getPage());
	}

	@When("User clicks on Privacy Center button")
	public void user_clicks_on_privacy_center_button() {
		privacyCenterPage.clickOnPrivacyCenterButton();
	}

	@Then("Use can see Privacy popup with following elements")
	public void use_can_see_privacy_popup_with_following_elements(DataTable dataTable) {
		List<Map<String, String>> elements = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : elements) {
			String element = row.get("ELEMENT");
			String name = row.get("EXPECTED_NAME");
			if (name == null) {
				name = "";
			}
			boolean isElementVisible = privacyCenterPage.isElemnetVisible(element, name);
			Assertions.assertTrue(isElementVisible, element + " is not visible");
		}
	}

	@Then("User should see the following cookie details")
	public void user_should_see_the_following_cookie_details(DataTable dataTable) {
		List<Map<String, String>> cookies = dataTable.asMaps(String.class, String.class);
		String[] fields = { "NAME", "HOST", "DURATION", "TYPE", "CATEGORY", "DESCRIPTION" };
		for (Map<String, String> cookie : cookies) {
			String cookieName = cookie.get("NAME");
			for (String field : fields) {
				String expectedValue = cookie.get(field);
				String actualValue = privacyCenterPage.getCookieFieldValue(cookieName,
						field.charAt(0) + field.substring(1).toLowerCase());
				Assertions.assertEquals(expectedValue, actualValue,
						"Mismatch in " + field + " for cookie: " + cookieName);
			}
		}
	}

	@When("User clicks on Close icon")
	public void user_clicks_on_close_icon() {
		privacyCenterPage.clickOnCloseIcon();
	}

	@Then("Privacy popup should close")
	public void privacy_popup_should_close() {
		privacyCenterPage.waitForPopupClose();
		boolean isPrivacyPopupVisible = privacyCenterPage.isPrivacyPopupVisible();
		Assertions.assertFalse(isPrivacyPopupVisible, "Privacy popup is still visible");
	}

	@When("User clicks on Cookie policy link")
	public void user_clicks_on_cookie_policy_link() {
		newTab = privacyCenterPage.clickOnCookiePolicyLink();
		newTab.waitForLoadState();
	}

	@Then("User navigates to {string} page")
	public void user_navigates_to_page(String destination) {
		String actualUrl = newTab.url();
		Assertions.assertEquals(destination, actualUrl, "Mismatch between the expected and actual link destination");
		newTab.close();
	}
}
