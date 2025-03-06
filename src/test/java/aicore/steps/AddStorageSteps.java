package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.base.AICoreTestManager;
import aicore.pages.HomePage;
import aicore.pages.OpenStoragePage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddStorageSteps {

	private HomePage homePage;
	private OpenStoragePage storagePage;
	private String timestamp;

	public AddStorageSteps() {
		homePage = new HomePage(AICoreTestManager.getPage());
		timestamp = CommonUtils.getTimeStampName();
		storagePage = new OpenStoragePage(AICoreTestManager.getPage(), timestamp);
	}

	@Given("User clicks on Open Storage engine")
	public void user_clicks_on_open_storage_engine() {
		homePage.clickOnOpenStorage();
	}

	@When("User clicks on Add Storage button")
	public void user_clicks_on_add_storage_button() {
		storagePage.clickOnAddStorageButton();
	}

	@And("User selects {string} storage")
	public void user_selects_storage(String storageName) {
		storagePage.selectStorage(storageName);
	}

	@And("User enters storage Catalog name as {string}")
	public void user_enters_storage_catalog_name_as(String catalogName) {
		storagePage.enterCatalogName(catalogName);
	}

	@And("User enters Region as {string}")
	public void user_enters_region_as(String regionName) {
		storagePage.enterRegionName(regionName);
	}

	@And("User enters Access Key as {string}")
	public void user_enters_access_key_as(String accessKey) {
		storagePage.enterAccessKey(accessKey);
	}

	@And("User enters Secret Key")
	public void user_enters_secret_key() {
		storagePage.enterSecretKey();
	}

	@And("User clicks on Create Storage button")
	public void user_clicks_on_create_storage_button() {
		storagePage.clickOnCreateStorageButton();
	}

	@Then("User can see create storage success toast message as {string}")
	public void user_can_see_create_storage_success_toast_message_as(String expectedMessage) {
		String actualMessage = storagePage.verifyStorageCreatedToastMessage();
		Assertions.assertEquals(actualMessage, expectedMessage, "Storage creation is failed");
	}

	@Then("User can see the Storage title as {string}")
	public void user_can_see_the_storage_title_as(String storageTitle) {
		String actualTitle = storagePage.verifyStorageTitle(storageTitle);
		String expectedTitle = storageTitle + " " + timestamp;
		Assertions.assertEquals(actualTitle, expectedTitle, "Storage title is not matching");
	}

	@Then("User can see storage name in {string} field as {string} in SMSS properties")
	public void user_can_see_storage_name_in_field_as_in_smss_properties(String field, String name) {
		String fullText = storagePage.verifyNameFiledInSMSS();
		String actualName = CommonUtils.splitTrimValue(fullText, field);
		String expectedName = name + " " + timestamp;
		Assertions.assertEquals(actualName, expectedName, "Storage title is not matching");
	}

	@Then("User can see storage region in {string} field as {string} in SMSS properties")
	public void user_can_see_storage_region_in_field_as_in_smss_properties(String field, String expectedRegionName) {
		String fullText = storagePage.verifyS3RegionFiledInSMSS();
		String actualRegionName = CommonUtils.splitTrimValue(fullText, field);
		Assertions.assertEquals(actualRegionName, expectedRegionName, "Region name is not matching");
	}

	@Then("User can see storage access key in {string} field as {string} in SMSS properties")
	public void user_can_see_storage_access_key_in_field_as_in_smss_properties(String field, String expectedAccessKey) {
		String fullText = storagePage.verifyS3AccessKeyFiledInSMSS();
		String actualAccessKey = CommonUtils.splitTrimValue(fullText, field);
		Assertions.assertEquals(actualAccessKey, expectedAccessKey, "Storage title is not matching");
	}

}
