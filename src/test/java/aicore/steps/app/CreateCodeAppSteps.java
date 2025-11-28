package aicore.steps.app;

import aicore.hooks.SetupHooks;
import aicore.pages.app.CodeAppPage;
import io.cucumber.java.en.And;

public class CreateCodeAppSteps {

	private CodeAppPage codeAppPage;

	public CreateCodeAppSteps() {
		this.codeAppPage = new CodeAppPage(SetupHooks.getPage());
	}

    @And("User clicks on the file icon in the left panel")
    public void user_Clicks_On_The_File_Icon_In_The_Left_Panel() {
        codeAppPage.clickOnFileUploadButton();
    }

    @And("User selects the unzip checkbox")
    public void user_Selects_The_Unzip_Checkbox() {
        codeAppPage.clickOnUnzipCheckbox();
    }

    @And("User clicks on the publish icon to publish the code app")
    public void user_Clicks_On_The_Publish_Icon_To_Publish_The_Code_App() {
        codeAppPage.clickOnPublishButton();
    }
}
