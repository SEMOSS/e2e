package aicore.unit.home;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.framework.UrlUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;

public class MainMenuTests extends AbstractE2ETest {

	@BeforeAll
	public void setup() throws IOException {
		login(page, UserType.NATIVE);
	}

	@BeforeEach
	public void openMainMenu() throws IOException {
		MainMenuUtils.openMainMenu(page);
	}

	@Test
	public void testAppLibrary() {
		MainMenuUtils.clickOnOpenAppLibrary(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/app");
		assertEquals(expectedUrl, url);
	}

	@Test
	public void testModel() {
		MainMenuUtils.clickOnOpenModel(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/engine/model");
		assertEquals(expectedUrl, url);
	}

	@Test
	public void testDatabaseLibrary() {
		MainMenuUtils.clickOnOpenDatabase(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/engine/database");
		assertEquals(expectedUrl, url);
	}

	@Test
	public void testVectorLibrary() {
		MainMenuUtils.clickOnOpenVector(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/engine/vector");
		assertEquals(expectedUrl, url);
	}

	@Test
	public void testFunction() {
		MainMenuUtils.clickOnOpenFunction(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/engine/function");
		assertEquals(expectedUrl, url);
	}

	@Test
	public void testStorage() {
		MainMenuUtils.clickOnOpenStorage(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/engine/storage");
		assertEquals(expectedUrl, url);
	}

	@Test
	public void testGuardrail() {
		MainMenuUtils.clickOnGuardrail(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/engine/guardrail");
		assertEquals(expectedUrl, url);
	}

	@Test
	public void testSettings() {
		MainMenuUtils.clickOnOpenSettings(page);
		// validate
		String url = AICorePageUtils.getPageUrl(page);
		String expectedUrl = UrlUtils.getUrl("#/settings");
		assertEquals(expectedUrl, url);
	}

}
