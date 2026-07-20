package aicore.utils.page.app;

import com.microsoft.playwright.Page;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.CommonUtils;

public class TemplateCreationUtils {

    public static String createAppFromTemplate(
            Page page,
            String templateName) {

        String appName = "Test app " + CommonUtils.getTimeStampName();

        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);

        AppPageUtils.clickOnCreateNewAppButton(page);

        AppTemplatePageUtils.selectTemplateFromList(templateName, page);

        completeAppCreation(page, appName);

        return appName;
    }

    public static String createDragAndDropApp(
            Page page,
            String appType) {

        String appName = "Test app " + CommonUtils.getTimeStampName();

        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);

        AppPageUtils.clickOnCreateNewAppButton(page);

        CreateAppPopupUtils.clickOnGetStartedButton(page, appType);

        completeAppCreation(page, appName);

        return appName;
    }

    private static void completeAppCreation(Page page, String appName) {

        CreateAppPopupUtils.enterAppName(page, appName);
        CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
        CreateAppPopupUtils.enterTags(page, "Test1, Test2");
        CreateAppPopupUtils.clickOnCreateButton(page);
    }
}