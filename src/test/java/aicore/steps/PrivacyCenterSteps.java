package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.PrivacyCenterPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrivacyCenterSteps {

	private PrivacyCenterPage privacyCenterPage;

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
			String result = row.get("EXPECTED_RESULT");
			if (result == null) {
				result = "";
			}
			boolean isElementVisible = privacyCenterPage.isElemnetVisible(element, result);
			Assertions.assertTrue(isElementVisible, element + " is not visible");
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

}
