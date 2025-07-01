package aicore.steps;

import aicore.hooks.SetupHooks;
import aicore.pages.AddModelPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.When;

public class ModelCatalogAuthorPermissionsSteps {

	private AddModelPage openModelPage;

	protected static String timestamp;

	public ModelCatalogAuthorPermissionsSteps() {

		timestamp = CommonUtils.getTimeStampName();
		this.openModelPage = new AddModelPage(SetupHooks.getPage(), timestamp);

	}

	@When("{string} user login to the appication")
	public void user_login_to_the_appication(String string) {
		openModelPage.clickOnAccessControl();
	}

}
