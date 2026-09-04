package aicore.utils.page.app;

import java.util.ArrayList;
import java.util.List;

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

    public static String createCodeApp(Page page) {

        String appName = "Code app " + CommonUtils.getTimeStampName();

        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);

        AppPageUtils.clickOnCreateNewAppButton(page);
        CreateAppPopupUtils.clickOnGetStartedButton(page, "Develop in code");

        CreateAppPopupUtils.enterAppName(page, appName);
        CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
        CreateAppPopupUtils.enterTags(page, "MCP");
        CreateAppPopupUtils.clickOnCreateButton(page);

        return appName;
    }

    
    public static List<String> createMultipleDragAndDropApps(
            Page page,
            int appCount,
            String appType,
            String appName,
            String appDescription,
            String appTags) {

        HomePageUtils.navigateToHomePage(page);
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnOpenAppLibrary(page);

        List<String> createdAppNames = new ArrayList<>();

        for (int i = 0; i < appCount; i++) {

            AppPageUtils.clickOnCreateNewAppButton(page);

            CreateAppPopupUtils.clickOnGetStartedButton(page, appType);

            String appNameWithTimestamp =
                    appName + " " + CommonUtils.getTimeStampName();

            CreateAppPopupUtils.enterAppName(page, appNameWithTimestamp);
            CreateAppPopupUtils.enterAppDescription(page, appDescription);
            CreateAppPopupUtils.enterTags(page, appTags);
            CreateAppPopupUtils.clickOnCreateButton(page);

            createdAppNames.add(appNameWithTimestamp);
        }

        return createdAppNames;
    }
    
    private static void completeAppCreation(Page page, String appName) {

        CreateAppPopupUtils.enterAppName(page, appName);
        CreateAppPopupUtils.enterAppDescription(page, "Created by automation script");
        CreateAppPopupUtils.enterTags(page, "Test1, Test2");
        CreateAppPopupUtils.clickOnCreateButton(page);
    }
}