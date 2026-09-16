package aicore.unit.DragAndDrop.permissions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import com.microsoft.playwright.Page;
import aicore.pages.app.settings.AppAccessControlPageUtils;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.CatlogAccessPageUtility;
import aicore.utils.CommonUtils;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.app.AppPageUtils;
import aicore.utils.page.app.CreateAppPopupUtils;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;
import aicore.utils.page.app.TemplateCreationUtils;

public class Appfilter extends AbstractPlaywrightTestBase{
	
	private String appName;
	private List<String> multiApps = new ArrayList<>();
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}
	
	

	@AfterEach
	void tearDown(@PWPage Page page) {
		for (String createdAppName : multiApps) {
			CommonUtils.navigateAndDeleteApp(page, createdAppName);
		}
	    logout(page);
	}
	
	private void verifyAppDisplayed(Page page, String appName) {
	    Assertions.assertTrue(
	            AppPageUtils.isAppDisplayedOnPage(page, appName, ""),
	            "Application '" + appName + "' is not displayed on the page."
	    );
	}
	
	private void verifyWelcomePage(Page page) {
	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyPage1IsVisible(page),
	            "Page is not visible");

	    Assertions.assertTrue(
	            DragAndDropBlocksPageUtils.verifyWelcomeTextboxIsVisible(page),
	            "Welcome text box is not visible");

	    Assertions.assertEquals(
	            "Welcome to the UI Builder! Drag and drop blocks to use in your app.",
	            DragAndDropBlocksPageUtils.verifyWelcomeText(page),
	            "Mismatch between the expected and actual welcome message");
	}
	
	private void verifyAppCreated(Page page) {
	    String appName = CreateAppPopupUtils.userFetchAppName(page);
	    Assertions.assertFalse(appName.isEmpty(), "Fetched App Name is Empty");
	}
	
	private void verifySortedInAscendingOrder(Page page, String catalogName) {
	    Assertions.assertTrue(
	            AppPageUtils.verifySortedInAscendingOrder(page),
	            catalogName + " are not sorted in ascending order."
	    );
	}
	
	private void openAppLibrary(Page page) {
	    HomePageUtils.navigateToHomePage(page);
	    MainMenuUtils.openMainMenu(page);
	    MainMenuUtils.clickOnOpenAppLibrary(page);
	}
	
	private void verifySortedInDescendingOrder(Page page, String catalogName) {
	    Assertions.assertTrue(
	            AppPageUtils.verifySortedInDescendingOrder(page),
	            catalogName + " are not sorted in descending order."
	    );
	}
	
	private void verifyAppsSortedByDateLastEdited(Page page) {
	    Assertions.assertTrue(
	            AppPageUtils.verifyAppsSortedByDateLastEdited(page),
	            "Apps are not sorted by date last edited."
	    );
	}
	
	private void verifyAppsInListView(Page page) {
	    Assertions.assertTrue(
	            AppPageUtils.verifyAppsInTheListView(page),
	            "Apps are not visible in list view."
	    );
	}
	
	private void verifyAppsInGridView(Page page) {
	    Assertions.assertTrue(
	            AppPageUtils.verifyAppsInTheGridView(page),
	            "Apps are not visible in grid view."
	    );
	}
	

	@Test
    public void ValidateDiscoverableOption_test(@PWPage Page page) {
		
		appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		verifyWelcomePage(page);		
		CatlogAccessPageUtility.clickOnSettings(page);
		AddFunctionPageUtils.clickOnAccessControl(page);
		AppAccessControlPageUtils.clickOnMakeDiscoverableButtoninSettings(page, appName);
	    logout(page);
		loginEditor(page);
		openAppLibrary(page);
		AppPageUtils.clickOnDiscoverableAppsButton(page);
		AppPageUtils.searchApp(page, appName, "");
		verifyAppDisplayed(page, appName);
	    logout(page);
		loginNativeAdmin(page);
		CommonUtils.navigateAndDeleteApp(page, appName);

	}
	
	@Test
    public void validateAscendingAndDescendingFilter_test(@PWPage Page page) {
		
		multiApps.addAll(TemplateCreationUtils.createMultipleDragAndDropApps(
		        page,
		        1,
		        "Drag and Drop",
		        "Descending filter test App",
		        "Pagination Test Description",
		        "Pagination, Test"
		));
		
		openAppLibrary(page);
		multiApps.addAll(TemplateCreationUtils.createMultipleDragAndDropApps(
		        page,
		        1,
		        "Drag and Drop",
		        "Descending filter test App",
		        "Pagination Test Description",
		        "Pagination, Test"
		));
        
		openAppLibrary(page);
		AppPageUtils.clickOnFilterButton(page, "Ascending");
		verifySortedInAscendingOrder(page, "Ascending");
		AppPageUtils.clickOnFilterButton(page, "Descending");
		verifySortedInDescendingOrder(page, "Descending");

	}
	
	@Test
    public void validateDateModifiedFilter_test(@PWPage Page page) {
		
		multiApps.addAll(TemplateCreationUtils.createMultipleDragAndDropApps(
		        page,
		        1,
		        "Drag and Drop",
		        "Descending filter test App",
		        "Pagination Test Description",
		        "Pagination, Test"
		));
		
		openAppLibrary(page);
		AppPageUtils.selectSortByOption(page, "Date Last Edited");
		verifyAppsSortedByDateLastEdited(page);
	}

	@Test
    public void validateViewFilter_test(@PWPage Page page) {
		  
		openAppLibrary(page);
		String appName = TemplateCreationUtils.createDragAndDropApp(page, "Drag and Drop");
		verifyAppCreated(page);
		openAppLibrary(page);
		AppPageUtils.clickOnViewFilterButton(page, "List view");
		verifyAppsInListView(page);
		AppPageUtils.clickOnViewFilterButton(page, "Grid View");
		verifyAppsInGridView(page);
		CommonUtils.navigateAndDeleteApp(page, appName);

	}


}
