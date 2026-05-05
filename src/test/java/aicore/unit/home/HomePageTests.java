package aicore.unit.home;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.page.app.DragAndDropBlocksPageUtils;

public class HomePageTests extends AbstractE2ETest {

	@BeforeAll
	public void setup() throws IOException {
		login(page, UserType.NATIVE);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnHome(page);
		HomePageUtils.clickOnBuildButton(page);
	}

	///////////// Loading tests

	Stream<String> pageTitles() {
		return Stream.of(
				"Experiment with AI in the Playground", 
				"Get started with our tool", 
				"Drag and drop blocks",
				"Develop in code", 
				"Construct an agent", 
				"Try these fan favorites", 
				"BI", 
				"Terminal");
	}

	@ParameterizedTest(name = "Verify title is visible: {0}")
	@MethodSource("pageTitles")
	void testPageComponents(String titleName) {
		HomePageUtils.verifyTitleIsVisible(page, titleName);
	}

	@ParameterizedTest(name = "Verify Get Started button for section: {0}")
	@ValueSource(strings = { "Drag and drop blocks", "Develop in code", "Construct an agent" })
	void testGetStartedButtons(String sectionName) {
		assertTrue(HomePageUtils.verifyBuildPageButtons(page, sectionName, "Get Started"));
	}
	
	@Test
	void testBrowseTemplatesBtn() {
		HomePageUtils.verifyBuildPageButton(page, "Browse Templates");
		// TODO validate
	}
	
	@ParameterizedTest(name = "Verify System App: {0}")
	@ValueSource(strings = { "BI", "Terminal" })
	void testSystemApps(String appName) {
		boolean isAppDisplayed = DragAndDropBlocksPageUtils.isAppDisplayedInSystemAppsSection(page, appName);
		Assertions.assertTrue(isAppDisplayed, "Created Application is not displayed in System Apps section"); // TODO
	}

	/////////// BUTTONS

//	@Test
	public void testClickLaunchPlayground() {
		// TODO write test to click on "Launch Playground button"
	}
	


}
