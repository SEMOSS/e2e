package aicore.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.microsoft.playwright.Page;

import aicore.hooks.SetupHooks;
import aicore.pages.home.HomePageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.AddModelFormUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.pages.model.ModelSMSSPageUtils;
import aicore.utils.page.model.ModelPageUtils;

public class ModelTestUtils {
	
	public static String addModel(Page page, String modelCatalogName) {
		String modelType = "OpenAI";
		String modelTypeName = "GPT-4.1";
		String openAIKey = "Test@1234";
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);

		// model form options
		ModelPageUtils.clickAddModelButton(page);
		AddModelFormUtils.selectModelType(page, modelType);
		AddModelFormUtils.selectModel(page, modelTypeName);
		AddModelFormUtils.enterCatalogName(page, modelCatalogName);
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
		
		// get the name of the model
		// TODO there might be an easier way to do this
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(SetupHooks.getPage());
		MainMenuUtils.clickOnOpenModel(SetupHooks.getPage());
		EditModelPageUtils.searchModelCatalog(page, modelCatalogName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelCatalogName);
		String modelId = CatlogAccessPageUtility.getCatalogAndCopyId(page);
		return modelId;
	}

	public static void addModel(Page page, String modelType, String modelName, String catalogName,
			String openAIKey) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		ModelPageUtils.clickAddModelButton(page);
		AddModelFormUtils.selectModelType(page, modelType);
		AddModelFormUtils.selectModel(page, modelName);
		AddModelFormUtils.enterCatalogName(page, catalogName);
		AddModelFormUtils.enterOpenAIKey(page, openAIKey);
		AddModelFormUtils.clickOnCreateModelButton(page);
	}
	
    public static String uploadModelZip(Page page, String modelName, String fileName) {
		// delete model before
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		AddFunctionPageUtils.deleteCatalog(page, "Model", modelName);

		// click on add model
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		ModelPageUtils.clickAddModelButton(page);
		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));

		CatalogCreationFromZipUtil.clickOnFileUploadIcon(page);
		CatalogCreationFromZipUtil.uploadFile(page, fileName);
		CatalogCreationFromZipUtil.clickOnUploadButton(page, "Upload");

        // Wait for page to stabilize
		page.waitForLoadState();
		page.waitForTimeout(500); // Small buffer for any animations

		//return null;
		return getModelID(page, modelName);
	}

    public static String getModelID(Page page, String modelName) {
		HomePageUtils.navigateToHomePage(page);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		EditModelPageUtils.searchModelCatalog(page, modelName);
		EditModelPageUtils.selectModelFromSearchOptions(page, modelName);
		String modelId = CatlogAccessPageUtility.getCatalogAndCopyId(page);
		return modelId;
	}

	public static List<Map<String, String>> buildSections(String s1Name, String s1Fields, String s2Name,
			String s2Fields, String s3Name, String s3Fields) {
		List<Map<String, String>> sections = new ArrayList<>();
		addSection(sections, s1Name, s1Fields);
		addSection(sections, s2Name, s2Fields);
		addSection(sections, s3Name, s3Fields);
		return sections;
	}

	public static void verifyFormSections(Page page, List<Map<String, String>> sections) {
		for (Map<String, String> section : sections) {
			String[] fields = section.get("FIELDS").split(", ");
			for (String field : fields) {
				Assertions.assertTrue(AddModelFormUtils.fieldUnderSection(page, section.get("SECTION_NAME"), field),
						field + " is not visible under " + section.get("SECTION_NAME") + " section");
			}
		}
	}

	public static void verifyMandatoryFields(Page page, String mandatoryFields) {
		for (String field : mandatoryFields.split(", ")) {
			Assertions.assertTrue(AddModelFormUtils.isFieldMandatory(page, field), field + " is not a mandatory field");
		}
	}

	public static void fillModelCreationForm(Page page, String formFields, String timestamp) {
		for (String fieldAssignment : formFields.split(", ")) {
			if (!fieldAssignment.contains("=")) {
				continue;
			}
			String[] keyValue = fieldAssignment.split("=", 2);
			AddModelFormUtils.fillCatalogCreationForm(page, keyValue[0].trim(), keyValue[1].trim(), timestamp);
		}
	}

	public static void verifySmssFields(Page page, String smssFields) {
		for (String fieldAssignment : smssFields.split(", ")) {
			if (!fieldAssignment.contains("=")) {
				continue;
			}
			String[] keyValue = fieldAssignment.split("=", 2);
			String fieldName = keyValue[0].trim();
			String expectedValue = keyValue[1].trim();
			String fullText = ModelSMSSPageUtils.getAllFieldsInSMSSProperties(page, fieldName);
			Assertions.assertNotNull(fullText, "No SMSS value found for field: " + fieldName);
			String normalizedText = fullText.replace("\u00A0", " ").trim().replaceAll("\\s+", " ");
			String actualValue = normalizedText;
			if (normalizedText.toUpperCase().startsWith(fieldName.toUpperCase())) {
				int firstSpaceIndex = normalizedText.indexOf(' ');
				actualValue = firstSpaceIndex >= 0 ? normalizedText.substring(firstSpaceIndex + 1).trim() : "";
			}
			if ("NAME".equalsIgnoreCase(fieldName)) {
				actualValue = actualValue.replaceAll("\\d+$", "");
			}
			if ("INIT_MODEL_ENGINE".equalsIgnoreCase(fieldName)) {
				Assertions.assertTrue(actualValue.contains(expectedValue),
						"Field validation failed for '" + fieldName + "' ==> expected partial text: <" + expectedValue
								+ "> but was: <" + actualValue + ">");
			} else {
				Assertions.assertEquals(expectedValue, actualValue,
						"Field validation failed for '" + fieldName + "'");
			}
		}
	}

	private static void addSection(List<Map<String, String>> sections, String sectionName, String fields) {
		if (sectionName != null && !sectionName.isBlank() && fields != null && !fields.isBlank()) {
			sections.add(Map.of("SECTION_NAME", sectionName, "FIELDS", fields));
		}
	}
}
