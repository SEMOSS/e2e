package aicore.unit.home;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Page;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;

public class HomePageSearchLoadTests  extends AbstractPlaywrightTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}
	
    @ParameterizedTest(name = "Search catalog: {0}")
    @ValueSource(strings = {
        "App",
        "Model",
        "WeatherFunctionTest",
        "Vector",
        "TestDatabase",
        "Storage"
    })
    void testSearch(String searchData, @PWPage Page page) {
        MainMenuUtils.openMainMenu(page);
        MainMenuUtils.clickOnHome(page);
        HomePageUtils.searchCatalog(page, searchData);
        HomePageUtils.closeSearchPopup(page);
    }
}
