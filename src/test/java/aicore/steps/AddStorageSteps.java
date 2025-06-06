package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.CatalogPage;
import aicore.pages.HomePage;
import aicore.pages.OpenStoragePage;
import aicore.pages.ViewUsagePage;
import aicore.utils.CommonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddStorageSteps extends AbstractAddCatalogBase {

	private HomePage homePage;
	private OpenStoragePage storagePage;
	protected static String timestamp;
	private ViewUsagePage viewUsagePage;
	private CatalogPage catalogPage;

	public AddStorageSteps() {
		homePage = new HomePage(SetupHooks.getPage());
		timestamp = CommonUtils.getTimeStampName();
		storagePage = new OpenStoragePage(SetupHooks.getPage(), timestamp);
		viewUsagePage = new ViewUsagePage(SetupHooks.getPage());
		catalogPage = new CatalogPage(SetupHooks.getPage());
	}

	@Given("User clicks on Open Storage engine")
	public void user_clicks_on_open_storage_engine() {
		homePage.clickOnOpenStorage();
	}

	@When("User clicks on Add Storage button")
	public void user_clicks_on_add_storage_button() {
		storagePage.clickOnAddStorageButton();
	}

	@Then("User should see Search bar to filter storage options")
	public void user_should_see_search_bar_to_filter_options() {
		validateSearchBar(storagePage);
	}

	@Then("User should see the following storage options with valid icons on the page")
	public void user_should_see_the_following_storage_options_with_valid_icon_on_the_page(DataTable dataTable) {
		final String GROUP_NAME = "GROUP";
		final String STORAGE_OPTION_NAMES = "STORAGE_OPTIONS";
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		validateOptionsWithIcon(GROUP_NAME, STORAGE_OPTION_NAMES, rows, storagePage);
	}

	@And("User selects {string} storage")
	public void user_selects_storage(String storageName) {
		storagePage.selectStorage(storageName);
	}

	@And("User enters storage Catalog name as {string}")
	public void user_enters_storage_catalog_name_as(String catalogName) {
		catalogName = catalogName + timestamp;
		storagePage.enterCatalogName(catalogName);
	}

	@And("User enters Region as {string}")
	public void user_enters_region_as(String regionName) {
		storagePage.enterRegionName(regionName);
	}

	@And("User enters Bucket as {string}")
	public void user_enters_bucket_as(String regionName) {
		storagePage.enterBucket(regionName);
	}

	@And("User enters Access Key as {string}")
	public void user_enters_access_key_as(String accessKey) {
		storagePage.enterAccessKey(accessKey);
	}

	@And("User enters Secret Key as {string}")
	public void user_enters_secret_key_as(String secretKey) {
		storagePage.enterSecretKey(secretKey);
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
		String actualTitle = storagePage.verifyStorageTitle(storageTitle + timestamp);
		String expectedTitle = storageTitle + timestamp;
		Assertions.assertEquals(actualTitle, expectedTitle, "Storage title is not matching");
	}

	@Then("User can see storage name in {string} field as {string} in SMSS properties")
	public void user_can_see_storage_name_in_field_as_in_smss_properties(String field, String name) {
		String fullText = storagePage.verifyNameFiledInSMSS();
		String actualName = CommonUtils.splitTrimValue(fullText, field);
		String expectedName = name + timestamp;
		Assertions.assertEquals(actualName, expectedName, "Storage title is not matching");
	}

	@Then("User can see storage bucket in {string} field as {string} in SMSS properties")
	public void user_can_see_storage_bucket_in_field_as_in_smss_properties(String field, String name) {
		String fullText = storagePage.verifyS3BucketFiledInSMSS();
		String actualName = CommonUtils.splitTrimValue(fullText, field);
		String expectedName = name;
		Assertions.assertEquals(actualName, expectedName, "Storage bucket is not matching");
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

	@Then("User can see {string} fields on the form")
	public void user_can_see_fields_on_the_form(String fields) {
		String[] fieldNames = fields.split(", ");
		for (String field : fieldNames) {
			boolean isFieldVisible = storagePage.verifyFieldIsVisible(field);
			Assertions.assertTrue(isFieldVisible, "Field is not visible on form: " + field);
		}
	}

	@And("User sees astrisk mark on the {string} fields of storage creation form")
	public void user_sees_astrisk_mark_on_the_fields_of_storage_creation_form(String requiredFields) {
		storagePage.verifyAsteriskMarkOnFields(requiredFields);
	}

	@Then("User redirects to the missing {string} input field")
	public void user_redirects_to_the_missing_input_field(String fieldName) {
		boolean missingFieldFlag = storagePage.verifyMissingInputField(fieldName);
		Assertions.assertTrue(missingFieldFlag, "missing input field is not highlighted/redirected " + fieldName);
	}

	@When("User enters value in below fields")
	public void user_enters_value_in_below_fields(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String fieldName = row.get("FIELD_NAME");
			String fieldValue = row.get("FIELD_VALUE");
			fieldValue = fieldValue != null ? fieldValue.trim() : "";
			storagePage.enterValuesInField(fieldName, fieldValue);
		}
	}

	@And("User clicks on Usage tab for storage")
	public void user_clicks_on_usage() {
		viewUsagePage.clickOnUsageTab();
	}

	@Then("User sees an example of {string} with example code for storage")
	public void user_sees_an_example_of_with_example_code(String example) {
		viewUsagePage.verifyExample(example);
	}

	@Then("User click on cancel button")
	public void user_click_on_cancel_button() {
		storagePage.clickOnCancelButton();
	}

	@Given("{string} user clicks on Settings of Storage")
	public void user_clicks_on_settings_of_storage(String string) {
		storagePage.clickOnSettingsTab();
	}

	@Then("User searches the {string} in the storage Catalog searchbox")
	public void user_searches_the_in_the_storage_catalog_searchbox(String catalogName) {
		catalogPage.searchCatalog(catalogName, timestamp);
	}

	@Then("User selects the {string} from the storage catalog")
	public void user_selects_the_from_the_storage_catalog(String catalogName) {
		catalogPage.selectCatalogFromSearchOptions(catalogName, timestamp);
	}
}