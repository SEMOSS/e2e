package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.CatalogCreationFromZipPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class CatalogCreationFromZipSteps {
	
	private CatalogCreationFromZipPage fileUploadPage;
	
	public CatalogCreationFromZipSteps()
	{
		fileUploadPage=new CatalogCreationFromZipPage(SetupHooks.getPage());
	}
	@When("User opens {string}")
	public void user_opens(String catalogName) {
		fileUploadPage.openCatalog(catalogName);
	}

	@And("User clicks on Add {string} button")
	public void user_clicks_on_add_button(String catalogName) {
		fileUploadPage.clickOnAddCatalogButton(catalogName);
	}

	@And("User selects the {string} option to upload file")
	public void user_selects_the_option_to_upload_file(String option) {
		fileUploadPage.selectAddCatalogOption(option);
	}

	@And("User uploads the file {string}")
	public void user_uploads_the_file(String fileName) {
		String uploadedFileName = fileUploadPage.uploadFile(fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "file is not uploaded successfully");
		}
	}

	@And("User clicks on Create {string} button to create catalog")
	public void user_clicks_on_create_button_to_create_catalog(String catalogName) {
		fileUploadPage.clickOnCreateCatalogButton();
	}

}
