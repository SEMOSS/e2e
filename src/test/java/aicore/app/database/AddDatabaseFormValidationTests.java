package aicore.app.database;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;

import aicore.base.GenericSetupUtils;
import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;
import aicore.hooks.SetupHooks;
import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;

public class AddDatabaseFormValidationTests {
	private static Page page = null;
	
	@BeforeAll
	public static void setup() throws IOException {
		GenericSetupUtils.initialize();
		page = GenericSetupUtils.setupPlaywright();
		String nativeUser = ConfigUtils.getValue(AICoreTestConstants.NATIVE_USERNAME);
		String nativePassword = ConfigUtils.getValue(AICoreTestConstants.NATIVE_PASSWORD);
		GenericSetupUtils.login(page, nativeUser, nativePassword);
		
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenDatabase(SetupHooks.getPage());
		
	}
	

	@Test
	public void testAster() {
		AddDatabaseFormUtils.clickAddDatabaseButton(page);

	}
	
	
}
