package aicore.unit.DragAndDrop;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.pages.app.settings.AppAccessControlPageUtils;
import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class VerifyAppFilterByTests extends AbstractPlaywrightTestBase {

	private static final String APP_NAME = "Test app";

	private String appName = "";

	@BeforeEach
	void setup(@PWPage Page page) {
		loginAdmin(page);
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");

		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyPage1IsVisible(page), "Page is not visible");
		Assertions.assertTrue(DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
				"Welcome text box not visible");
		Assertions.assertEquals("Welcome to the UI Builder! Drag and drop blocks to use in your app.",
				DragAndDropBlocksPageUtils.verifyWelcomeText(page), "Mismatch between the expected and actual message");

		CatlogAccessPageUtility.clickOnSettings(page);
		AppPageUtils.clickOnEditButtoninSettings(page);
		EditMetadataPageUtils.enterDetails(page, APP_NAME);
		EditMetadataPageUtils.enterDescription(page, APP_NAME);

		for (String tag : "embeddings, Test1, Test2, Test3".split(", ")) {
			AppPageUtils.enterTagNameinAppSettings(page, tag);
		}
		for (String domain : "SAP, AI, Finance".split(", ")) {
			AppPageUtils.enterDomainNameinAppSettings(page, domain);
		}
		for (String classification : "IP, PHI, PII, Public".split(", ")) {
			AppPageUtils.selectDataClassificationOptioninAppSettings(page, classification);
		}
		for (String restriction : "IP Allowed, PHI Allowed, FOUO Allowed".split(", ")) {
			AppPageUtils.selectDataRestrictionsOptioninAppSettings(page, restriction);
		}

		AppPageUtils.clickOnSubmitButtoninAppSettings(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {

		CommonUtils.navigateAndDeleteApp(page, appName);
		logout(page);
	}

	private void applyEachFilterAndValidateAppIsVisible(Page page, String appName) {
		Map<String, String> filters = new LinkedHashMap<>();
		filters.put("Tag", "embeddings, Test1");
		filters.put("Domain", "SAP, AI");
		filters.put("Data Classification", "IP, PHI, PII, PUBLIC");
		filters.put("Data Restrictions", "IP ALLOWED, PHI ALLOWED, FOUO ALLOWED");

		for (Map.Entry<String, String> row : filters.entrySet()) {
			String filterCategory = row.getKey();
			String[] filterValues = row.getValue().split(", ");
			for (String filterValue : filterValues) {
				AppPageUtils.searchFilterValueOnAppPage(page, filterValue);
				AppPageUtils.selectFilterValueOnAppPage(page, filterCategory, filterValue);

				Assertions.assertTrue(AppPageUtils.isAppDisplayedOnPage(page, appName, ""),
						"App is not present in the list for ' " + filterValue + " ' filter value");

				AppPageUtils.selectFilterValueOnAppPage(page, filterCategory, filterValue);
			}
		}
	}

	@Test
	@DisplayName("Verify the app is visible while applying filters in the app library")
	void testAppVisibleWhileApplyingFiltersInAppLibrary(@PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		applyEachFilterAndValidateAppIsVisible(page, appName);
	}

	@Test
	@DisplayName("Verify the discoverable app is visible while applying filters in the app library")
	void testDiscoverableAppVisibleWhileApplyingFiltersInAppLibrary(@PWPage Page page) {
		AddFunctionPageUtils.clickOnAccessControl(page);
		AppAccessControlPageUtils.clickOnMakeDiscoverableButtoninSettings(page, appName);
		logout(page);

		try {
			loginEditor(page);
			MainMenuUtils.openMainMenu(page);
			MainMenuUtils.clickOnOpenAppLibrary(page);
			AppPageUtils.clickOnDiscoverableAppsButton(page);

			applyEachFilterAndValidateAppIsVisible(page, appName);
		} finally {
			// delete the app it created as ADMIN 
			// Bug : reviewer steps 
			logout(page);
			loginAdmin(page);
		}
	}

	@Test
	@DisplayName("Verify the Bookmarked app is visible while applying filters in the app library")
	void testBookmarkedAppVisibleWhileApplyingFiltersInAppLibrary(@PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenAppLibrary(page);
		AppPageUtils.searchApp(page, appName, "");
		DragAndDropBlocksPageUtils.clickBookmarkIcon(page, appName);
		DragAndDropBlocksPageUtils.clickOnBookmarkedAppTab(page);
		applyEachFilterAndValidateAppIsVisible(page, appName);
	}
}