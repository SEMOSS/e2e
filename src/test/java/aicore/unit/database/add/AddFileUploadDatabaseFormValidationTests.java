package aicore.unit.database.add;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.microsoft.playwright.Page;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractDatabaseTestBase;
import aicore.utils.AddDatabaseFileUploadUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.annotations.PWPage;

public class AddFileUploadDatabaseFormValidationTests extends AbstractDatabaseTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	
	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	@ParameterizedTest(name = "Validate file upload database form for {0}")
	@MethodSource("fileUploadDatabaseFormData")
	void testFileUploadDatabaseForm(String dbType, Map<String, String[]> sections, String[] mandatoryFields, @PWPage Page page) {

		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);

		AddDatabaseFileUploadUtils.selectTab(page, "file uploads");
		AddDatabaseFormUtils.selectDatabaseFromConnectionTypes(page, dbType);

		// validate sections
		for (Map.Entry<String, String[]> entry : sections.entrySet()) {
			String sectionName = entry.getKey();
			String[] fields = entry.getValue();

			for (String field : fields) {
				boolean isFieldVisible = AddDatabasePageUtils.verifyFieldUnderSection(page, sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section");
			}
		}

		// validate mandatory fields
		for (String fieldName : mandatoryFields) {
			boolean isFieldMandatory = AddDatabaseFormUtils.isDBFieldMandatory(page, fieldName);
			Assertions.assertTrue(isFieldMandatory, fieldName + " is not mandatory field");
		}
	}

	@SuppressWarnings("unchecked")
	private Stream<Arguments> fileUploadDatabaseFormData() {
		return Stream.of(
				Arguments.of("CSV",
						sections(
								section("General", "Enter Database Name", "Enter Database Description",
										"Enter Database Tag"),
								section("Database", "Enter Database Type",/* "Enter Delimiter",*/ "Enter Metamodel Type")),
						new String[] { "Enter Database Name", "Enter Database Type", "Enter Metamodel Type" }),
				Arguments.of("TSV",
						sections(
								section("General", "Enter Database Name", "Enter Database Description",
										"Enter Database Tag"),
								section("Database", "Enter Database Type",/* "Enter Delimiter",*/ "Enter Metamodel Type")),
						new String[] { "Enter Database Name", "Enter Database Type", "Enter Metamodel Type" }),
				Arguments.of("Excel",
						sections(
								section("General", "Enter Database Name", "Enter Database Description",
										"Enter Database Tag"),
								section("Database", "Enter Database Type", "Enter Metamodel Type")),
						new String[] { "Enter Database Name", "Enter Database Type", "Enter Metamodel Type" }));
		
		// TODO SQLite, H2, Neo4j, Tinker
	}

	private static Map<String, String[]> sections(@SuppressWarnings("unchecked") Map.Entry<String, String[]>... entries) {
		Map<String, String[]> map = new LinkedHashMap<>();
		for (Map.Entry<String, String[]> entry : entries) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	private static Map.Entry<String, String[]> section(String sectionName, String... fields) {
		return Map.entry(sectionName, fields);
	}

}
