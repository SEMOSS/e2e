package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.function.AddFunctionFormUtils;
import aicore.pages.function.FunctionAccessSettingsUtils;
import aicore.pages.model.EditModelPageUtils;

public class FunctionTestUtils {
	public static void verifyFunctionCreationFormWithSelectFields(Page page, List<Map<String, String>> formFields){
		for (Map<String, String> row : formFields) {
			String sectionName = row.get("SECTION_NAME");
			String fieldsValue = row.get("FIELDS");
			if (sectionName == null || sectionName.trim().isEmpty() || fieldsValue == null
					|| fieldsValue.trim().isEmpty()) {
				continue;
			}
			String[] fields = fieldsValue.split(", ");
			for (String field : fields) {
				boolean isFieldVisible = AddFunctionPageUtils.fieldUnderSection(page, sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section");
			}
		}
	}
	
	
	public static void verifyFunctionCreationFormWithMandatoryFields(Page page, String mandatoryFields){
		String[] fields = mandatoryFields.split(", ");
		for (String field : fields) {
			boolean isFieldMandatory = AddFunctionPageUtils.isFieldMandatory(page, field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}
	
	public static void verifyUserFilledFunctionCreationFormFields(Page page, String formFields, String timestamp){
		String[] fields = formFields.split(", ");
		for (String field : fields) {
			if (!field.contains("=")) {
				continue;
			}
			String[] keyValue = field.split("=", 2);
			String fieldName = keyValue[0].trim();
			String fieldValue = keyValue[1].trim();
			AddFunctionPageUtils.fillFunctionCreationForm(page, fieldName, fieldValue, timestamp);
		}
	}
	
	public static void verifyUploadedFunctionFile(Page page, String fileName){
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "file is not uploaded successfully");
		}
	}
	
	public static void verifyAsteriskOnRequiredFields(Page page, String requiredFields){
		String[] fields = requiredFields.split(",");
		for (String field : fields) {
			boolean isFieldMandatory = AddFunctionFormUtils.isFieldMandatory(page, field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}
	
	public static void verifyUserSeesSuccessToastMessage(Page page, String toastMessage) {
		String actualMessage = AddFunctionPageUtils.verifySuccessToastMessage(page, toastMessage);
		Assertions.assertEquals(toastMessage, actualMessage, "Toaster is not matching with expected");
		AddFunctionPageUtils.closeToastMessage(page);
	}
	
	public static void verifyUserSeesDeletedToastMessage(Page page, String toastMessage) {
		String expectedMessage = FunctionAccessSettingsUtils.verifyDeleteToastMessage(page, toastMessage);
		String actualMessage = toastMessage;
		Assertions.assertEquals(actualMessage, expectedMessage, "Delete Message is not matching with expected");
	}
	
	public static void verifyUserSeesSuccessfulRequestToastMessage(Page page, String toastMessage) {
		String toastText = RequestAccessPopupUtils.verifyRequestSuccessToastVisible(page, toastMessage);
		Assertions.assertTrue(toastText != null && toastText.contains(toastMessage),
				"Expected toast message to contain: '" + toastMessage + "' but got: '" + toastText + "'");
	}
	
	public static void userUploadsFile(Page page, String fileName) {
		String uploadedFileName = CatalogCreationFromZipUtil.uploadFile(page, fileName);
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], uploadedFileName,
					"file is not uploaded successfully");
		} else {
			Assertions.assertEquals(fileName, uploadedFileName, "file is not uploaded successfully");
		}
	}
	
	public static void userCanSeeCatalogTitle(Page page, String functionName) {
		boolean isTitleVisible = AddDatabasePageUtils.verifyDatabaseTitle(page, functionName);
		Assertions.assertTrue(isTitleVisible, "Database title is not visible");
	}
	
	public static void verifyUserSeesFunctionInCatalog(Page page, String functionName, String timestamp) {
		String expectedFunctionName = AddFunctionPageUtils.verifyFunctionNameInCatalog(page, functionName, timestamp);
		functionName = functionName.replace("{Timestamp}", " " + timestamp);
		Assertions.assertEquals(functionName, expectedFunctionName, "Function is not present in the function catalog");
	}
	
	public static void userSearchesForAndLocatesFunction(Page page, String catalogName) {
		Locator catalogLocator = AddFunctionPageUtils.searchForAndLocateCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION, catalogName);
		assertTrue( catalogLocator.isVisible() );
	}
	
	
	public static void verifyPopupWithSelectOptions(Page page, String expectedTitle, List<String> options){//user_should_see_the_popup_with_following_options(String expectedTitle,
//			io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		Assertions.assertTrue(RequestAccessPopupUtils.isPopupVisible(SetupHooks.getPage()), expectedTitle + " popup is not visible");
		for (String option : options) {
			Assertions.assertTrue(RequestAccessPopupUtils.isOptionVisible(SetupHooks.getPage(), option),
					option + " is not visible in Change Access popup");
		}
	}
	
	public static void verifyUserCanSeeFolder(Page page, String folderName) {
		boolean canSee = CatalogCreationFromZipUtil.userSeesItemInFilesList(page, folderName);
		assertTrue(canSee, "The folder " + folderName + " was expected to be visible but was not");
	}
	
//	public static void verifyUserCanSeeFile(Page page, String fileName) {
//		CodeAppPageUtils.userCanSeeFile(page, fileName);
//		boolean isFileVisible = CodeAppPageUtils.userCanSeeFile(page, fileName);
//		Assertions.assertTrue(isFileVisible, "Default file is not visible");
//	}
	
	public static void validateSearchBar(Page page) {
		boolean isSearchBarVisible = AddCatalogPageBaseUtils.isSearchBarPresent(page);
		Assertions.assertTrue(isSearchBarVisible, "Search bar not visible");
	}

	public static void verifyOptionsWithIconsOnConnectToFunctionPage(Page page, String optionsStr) {
		List<String> optionsList = List.of(optionsStr.split(", "));
		final String sectionName = "Functions";

		boolean isSectionVisible = AddCatalogPageBaseUtils.verifySectionIsVisible(page, "function", sectionName);
		Assertions.assertTrue(isSectionVisible, sectionName + " section not visible");
		for (String option : optionsList) {
			// Verify option is visible
			boolean isOptionVisible = AddCatalogPageBaseUtils.verifyOptionIsVisible(page, "function", sectionName, option);
			Assertions.assertTrue(isOptionVisible, option + " option not visible");
			// Verify icon is visible
			Locator icon = AddCatalogPageBaseUtils.getIconByLabel(page, "function", sectionName, option);
			icon.waitFor();
			boolean isIconVisible = AddCatalogPageBaseUtils.isIconVisible(page, "function", sectionName, option);
			Assertions.assertTrue(isIconVisible, option + " icon is not visible");
			// verify icon is not broken
			String iconUrl = icon.getAttribute("src");
			// for 'Local File System' storage & 'FAISS' vector options getting broken image
			if (isIconVisible && !option.matches(".*(Local File System||FAISS).*")) {
				boolean isIconValid = CommonUtils.isIconValid(iconUrl);
				Assertions.assertTrue(isIconValid, option + " icon src is broken: " + iconUrl);
			}
		}
	}
	
	public static void validateFunctionFilters(Page page, String catalogName, String filterCategoryName, String filterValues) {
//		for (Map<String, String> row : rows) {
//			row.get(filterCategoryName);
//			String filterValues = row.get(filterValueName);

			String[] filterValuesArray = filterValues.split(", ");
			for (String filterValue : filterValuesArray) {
				CatalogFilterPageUtils.selectFilterValue(page , filterValue);
				CatalogFilterPageUtils.selectFilterValue(page , filterValue);
				boolean isCatalogVisible = CatalogFilterPageUtils.verifyCatalogIsVisibleOnCatalogPage(page, catalogName);
				Assertions.assertTrue(isCatalogVisible, "Catalog is not present for " + "'" + filterValue + "'" + " filter value");
				// To de-select selected filter we again call this method
				CatalogFilterPageUtils.selectFilterValue(page , filterValue);

			}
//		}
	}
	
	public static void userSeesFunctionStatusOnTooltip(Page page, String expectedStatus) {
		String actualStatus = EditModelPageUtils.getEngineAccessStatusTooltipText(page, expectedStatus);
		Assertions.assertEquals(expectedStatus, actualStatus, "Incorrect status");
	}
	
	public static void userGetsCatalogID(Page page) {
		String id = EditModelPageUtils.getCatalogID(page);
		Assertions.assertNotNull(id, "Catalog ID should not be null");
	}
	
	public static void userShouldSeeCatalogID(Page page) {
		boolean isIdVisible = EditModelPageUtils.validateIDisDisplayedOnCatalogCard(page);
		Assertions.assertTrue(isIdVisible, "Catalog ID is not visible on the catalog card");
	}
	
	public static void verifyUserSeesTagsOnCard(Page page, String expectedTags, String catalogName) {
		String[] tagArray = expectedTags.split(", ");
		List<String> actualTagList = EditModelPageUtils.verifyTagNamesDisplayedOnCard(page, catalogName);//openModelPage.verifyTagNamesDisplayedOnCard(catalogName);
		List<String> expectedTagList = Arrays.asList(tagArray);
		Assertions.assertEquals(expectedTagList, actualTagList);
	}
	
	public static void verifyUserSeesCreatedDateOnCatalogCard(Page page) {
		boolean isCreatedDateVisible = EditModelPageUtils.isCreatedDateVisibleOnCard(page);
		Assertions.assertTrue(isCreatedDateVisible, "Catalog created date is not visible on the catalog card");
	}
	
	public static void verifyUsersSeesIconsOnContentCard(Page page, String iconsStr) {
		String[] icons = iconsStr.split(",");
		for (String icon : icons) {
			boolean isIconVisible = EditModelPageUtils.isIconVisibleOnCatalogCard(page, icon.trim());//openModelPage.isIconVisibleOnCatalogCard(icon.trim());
			Assertions.assertTrue(isIconVisible, "Icon '" + icon + "' is not visible on the catalog card");
		}
	}
	
	public static void verifyUserSeesDeleteConfirmationPopupWithMessage(Page page, String expectedMessage) {
		String actualMessage = EditModelPageUtils.getDeleteConfirmationMessage(page);
		Assertions.assertEquals(expectedMessage, actualMessage, "Incorrect confirmation message");
	}
	
	public static void verifyFunctionNameIsOnDeletionConfirmationPopup(Page page, String expectedEngineName) {
		String actualEngineName = EditModelPageUtils.getDeleteConfirmationEngineName(page);
		Assertions.assertEquals(expectedEngineName, actualEngineName, "Incorrect engine name");
	}
	
	public static void verifyUsersSeesEngineIdOnDeleteConfirmationPopup(Page page) {
		Boolean isEngineIdVisible = EditModelPageUtils.isEngineIdVisibleOnDeleteConfirmation(page);
		Assertions.assertTrue(isEngineIdVisible, "Engine ID is not visible on the delete confirmation pop-up");
	}
	
	public static void verifUserSeesButtonOnDeleteConfirmationPopup(Page page, String expectedButton) {
		boolean isButtonVisible = EditModelPageUtils.isButtonVisibleOnDeleteConfirmation(page, expectedButton);
		Assertions.assertTrue(isButtonVisible, "Button '" + expectedButton + "' is not visible on the delete confirmation pop-up");
	}
}
