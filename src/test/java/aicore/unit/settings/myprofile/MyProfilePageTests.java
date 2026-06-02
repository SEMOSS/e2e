package aicore.unit.settings.myprofile;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.CommonUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;
import aicore.utils.settings.MyProfilePageUtils;
import aicore.utils.settings.SettingsPageUtils;

public class MyProfilePageTests extends AbstractPlaywrightTestBase {

	private String modelCatalogName = null;
	
	@BeforeEach
	public void setup(@PWPage Page page) throws IOException {
		loginNativeAdmin(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		MyProfilePageUtils.clickOnMyProfileCard(page);
	}
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

//	@BeforeAll
//	public void login() throws IOException {
//		login(page, UserType.NATIVE);
//	}
//
//	@BeforeEach
//	public void setup() throws IOException {
//		MainMenuUtils.openMainMenu(page);
//		MainMenuUtils.clickOnOpenSettings(page);
//		MyProfilePageUtils.clickOnMyProfileCard(page);
//	}

	@ParameterizedTest
	@ValueSource(strings = { "Edit profile information", "Javascript SDK", "Python SDK", "Personal Access Tokens" })
	void testSectionLoad(String sectionName, @PWPage Page page) {
		boolean isVisible = MyProfilePageUtils.isSectionVisible(page, sectionName);
		assertTrue(isVisible, "Expected section not found: " + sectionName);

	}

	private void createModelCatalog(Page page) throws IOException {
		String timestamp = CommonUtils.getTimeStampName();
		modelCatalogName = "ProfileModel" + timestamp;
		String modelType = "OpenAI";
		String modelName = "GPT-4.1";
		String openAIKey = "Test@1234";
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		ModelPageUtils.clickAddModelButton(page);
		AddModelFormUtils.selectModelType(page, modelType);
		AddModelFormUtils.selectModel(page, modelName);
		AddModelFormUtils.enterCatalogName(page, modelCatalogName);
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
	}

	@Test
	@DisplayName("Select default AI model for your requests")
	void testSelectModelInDropdown(@PWPage Page page) throws IOException {
		// Create model catalog
		createModelCatalog(page);
		// Perform validation on my profile card
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		MyProfilePageUtils.clickOnMyProfileCard(page);
		MyProfilePageUtils.selectModelFromTextGenerationModelDropdown(page, modelCatalogName);
		String actualToastMessage = MyProfilePageUtils.getToastMessage(page);
		String expectedMessage = "Default text-generation-model saved successfully";
		Assertions.assertEquals(actualToastMessage, expectedMessage,
				"Toast message mismatch after selecting text generation model from dropdown");
		MyProfilePageUtils.selectModelFromCodeGenerationModelDropdown(page, modelCatalogName);
		String actualCodeGenToastMessage = MyProfilePageUtils.getToastMessage(page);
		String expectedCodeGenMessage = "Default code-generation-model saved successfully";
		Assertions.assertEquals(actualCodeGenToastMessage, expectedCodeGenMessage,
				"Toast message mismatch after selecting code generation model from dropdown");
		// delete created catalog
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_MODEL, modelCatalogName);
	}

}
