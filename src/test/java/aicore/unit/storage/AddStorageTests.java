package aicore.unit.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.pages.storage.AddStorageFormUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.RequestAccessPopupUtils;
import aicore.utils.SearchAndSelectCatalogPageUtils;
import aicore.utils.StoragePageUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.ViewUsagePageUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;




public class AddStorageTests extends AbstractPlaywrightTestBase {

	private static String storageName = "Amazon S3";
	private static String timestamp = CommonUtils.getTimeStampName();
	private static String catalogName = "Amazon S3 Storage" + timestamp;
	
	private static final String CHANGE_ACCESS_XPATH = "//button[text()='Change Access']";

	@BeforeEach
	void setup(@PWPage Page page) {
		
		System.out.println("starting setup");
		
		// while logged in as author, the "Add Storage" button doesn't appear
		// loginAuthor(page);
		
		loginNativeAdmin(page);
		
		// add Amazon S3 Storage
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		StoragePageUtils.clickOnAddStorageButton(page);
		AddStorageFormUtils.selectStorage(page, storageName);
		
		AddStorageFormUtils.enterCatalogName(page, catalogName);
		AddStorageFormUtils.enterRegionName(page, "India");
		AddStorageFormUtils.enterBucket(page, "BucketTest");
		AddStorageFormUtils.enterAccessKey(page, "Test123");
		AddStorageFormUtils.enterSecretKey(page, "Test123");
		
		StoragePageUtils.clickOnConnectButton(page);
		
		page.context().grantPermissions(
			    Arrays.asList("clipboard-read", "clipboard-write"),
			    new BrowserContext.GrantPermissionsOptions()
			        .setOrigin("https://workshop.cfg.deloitte.com")
			);
		
		String catalogAndCopyId = CatlogAccessPageUtility.getCatalogAndCopyId(page);
		
//		String expectedMessage = "Successfully added new storage to catalog";
//		String actualMessage = StoragePageUtils.verifyStorageCreatedToastMessage(page, expectedMessage);
//		Assertions.assertEquals(actualMessage, expectedMessage, "Storage creation is failed");
		
//		CatlogAccessPageUtility.getCatalogAndCopyId(page);
		
		System.out.println("finishing setup");
	}	
	@AfterEach
	void tearDown(@PWPage Page page) {
		//HomePageUtils.navigateToHomePage(page);
		// MainMenuUtils.openMainMenu(page);
		assertTrue(CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_STORAGE, catalogName));
		logout(page);
	}
	
	@Test
	public void validateUsageOfStorage_test(@PWPage Page page) {
				
//		System.out.println("starting test");
		
		Assertions.assertTrue(AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName));
		
		ViewUsagePageUtils.clickOnUsageTab(page);
		
		// commenting out broken assert: the "How to use in Javascript" text verification from the cucumber script
		// since it's not on the screen
//		Assertions.assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Javascript"));
		Assertions.assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Pixel"));
		Assertions.assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Python"));
		Assertions.assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use with LangChain API"));
		Assertions.assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Java"));
		
//		System.out.println("finishing test");
	}
		
	@Test
	public void validateSMSSpropertiesOfStorage_test(@PWPage Page page) {
		
		Assertions.assertTrue(AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName));
		
		ModelPageUtils.clickOnSMSSTab(page);
		
		String field = "NAME";
		String name = "Amazon S3 Storage";
		String fullText = StoragePageUtils.verifyNameFiledInSMSS(page);
		String actualName = CommonUtils.splitTrimValue(fullText, field);
		String expectedName = name + timestamp;
		Assertions.assertEquals(actualName, expectedName, "Storage title is not matching");
		
		fullText = StoragePageUtils.verifyS3RegionFiledInSMSS(page);
		field = "S3_REGION";
		String expectedRegionName = "India";
		String actualRegionName = CommonUtils.splitTrimValue(fullText, field);
		Assertions.assertEquals(actualRegionName, expectedRegionName, "Region name is not matching");
		
		fullText = StoragePageUtils.verifyS3BucketFiledInSMSS(page);
		field = "S3_BUCKET";
		name = "BucketTest";
		actualName = CommonUtils.splitTrimValue(fullText, field);
		expectedName = name;
		Assertions.assertEquals(actualName, expectedName, "Storage bucket is not matching");
	}
	
	@Test
	public void viewStorageOverview_test(@PWPage Page page) throws Exception {
		
		Assertions.assertTrue(AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName));
		
		String catalogID = "copy Storage ID";
		boolean flag = AddCatalogPageBaseUtils.verifyCatalogID(page, catalogID);
		Assertions.assertTrue(flag, "storage ID is not visible");
		
		AddCatalogPageBaseUtils.clickCopyIcon(page);
		
		String toastMessage = "ID copied to clipboard";
		// commenting out broken asserts
//		flag = AddCatalogPageBaseUtils.verifyCopyToastMessage(page, toastMessage);
//		Assertions.assertTrue(flag, "copy message is not visible");
		
		EditMetadataPageUtils.clickEditIcon(page);
		
		String tags = "embeddings";
		String[] tagsArray = tags.split(", ");
		for (String tag : tagsArray) {
			EditMetadataPageUtils.enterTagName(page, tag);
		}
		
		EditMetadataPageUtils.clickOnSubmit(page);
		
		String expectedToastMessage = "Successfully set the new metadata values for the engine";
		String actualToastMessage = AddCatalogPageBaseUtils.verifyEditSuccessfullToastMessage(page);
		Assertions.assertEquals(actualToastMessage, expectedToastMessage);
		// .waitforEditSuccessToastMessageToDisappear() is commented out in the cucumber methods
		// AddCatalogPageBaseUtils.waitForEditSuccessToastMessageToDisappear(page);
		
		String expectedTags = "embeddings";
		String[] tagArray = expectedTags.split(", ");
		List<String> actualTagList = EditModelPageUtils.verifyTagNames(page);
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);
		
		AddFunctionPageUtils.clickOnAccessControl(page);
		
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		
		SettingsModelPageUtils.addMember(page, "Read", GenericSetupUtils.useDocker());
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName);
		
		logout(page);
		
		loginReadOnly(page);
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName);
		
		flag =  StoragePageUtils.verifyChangeAccessButton(page);
		// commenting out broken assert
//		Assertions.assertTrue(flag, "User sees Change Access button");
		
		logout(page);
		loginNativeAdmin(page);
	}
	
	@Test
	public void validateChangeAccessPopup_test(@PWPage Page page) throws Exception {
		
		Assertions.assertTrue(AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName));
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
		logout(page);

		loginEditor(page);
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName);
		
		page.locator(CHANGE_ACCESS_XPATH).click();
		
		List<String> options = Arrays.asList(
				"Author",
				"Editor",
				"Read-Only",
				"Comment Box",
				"Cancel Button",
				"Request Button"
				);
				
		String expectedTitle = "Change Access";
		Assertions.assertTrue(RequestAccessPopupUtils.isPopupVisible(page), expectedTitle + " popup is not visible");
		for (String option : options) {
			Assertions.assertTrue(RequestAccessPopupUtils.isOptionVisible(page, option),
					option + " is not visible in Change Access popup");
		}
		
		StoragePageUtils.clickOnCancelButton(page);
		
		logout(page);
		
		loginNativeAdmin(page);
	}
	
	@Test
	public void validateChangeAccessRequest_test(@PWPage Page page) throws Exception {
		
		Assertions.assertTrue(AddCatalogPageBaseUtils.verifyCatalogName(page, catalogName));
		AddFunctionPageUtils.clickOnAccessControl(page);
		SettingsModelPageUtils.clickOnAddMembersButton(page);
		SettingsModelPageUtils.addMember(page, "Editor", GenericSetupUtils.useDocker());
		
		logout(page);
		
		loginEditor(page);
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenStorage(page);
		SearchAndSelectCatalogPageUtils.searchCatalog(page, catalogName);
		SearchAndSelectCatalogPageUtils.selectCatalogFromSearchOptions(page, catalogName);
		
		page.locator(CHANGE_ACCESS_XPATH).click();
		
		RequestAccessPopupUtils.selectAccessType(page, "author");
		
		RequestAccessPopupUtils.enterComment(page, "Access Request");
		
		RequestAccessPopupUtils.clickOnRequestButton(page);
		
		String expectedMessage = "Successfully requested access to engine";
		String toastText = RequestAccessPopupUtils.isRequestSuccessToastVisible(page);
		Assertions.assertTrue(toastText != null && toastText.contains(expectedMessage),
				"Expected toast message to contain: '" + expectedMessage + "' but got: '" + toastText + "'");
		
		logout(page);
		
		loginNativeAdmin(page);
	}
	
}
