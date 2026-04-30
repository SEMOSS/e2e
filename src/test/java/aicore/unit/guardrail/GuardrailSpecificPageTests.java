package aicore.unit.guardrail;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.pages.base.EditMetadataPageUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.EditModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.AddDatabasePageUtils;
import aicore.utils.CommonUtils;
import aicore.utils.GuardrailPageUtils;
import aicore.utils.GuardrailTestUtils;
import aicore.utils.TestResourceTrackerHelper;
import aicore.utils.ViewUsagePageUtils;

public class GuardrailSpecificPageTests extends AbstractE2ETest {

	private String GUARDRAIL_ID = null;
	private String GUARDRAIL_NAME = null;


	@BeforeAll
	public void setupBeforeAll() throws IOException {
		login(page, UserType.NATIVE);
		String timestamp = CommonUtils.getTimeStampName();
		GUARDRAIL_NAME = "guardrail" + timestamp;
		GUARDRAIL_ID = GuardrailTestUtils.createGlinerGuardrail(page, GUARDRAIL_NAME);
	}
	
	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnGuardrail(page);
		// test search
		GuardrailPageUtils.searchGuardrailCatalog(page, GUARDRAIL_NAME);
		GuardrailPageUtils.selectTheGuardrailCatalog(page, GUARDRAIL_NAME);
	}
	
	@Test
	public void testOverview() {
		AddDatabasePageUtils.clickOnOverview(page);
		String catalogDescription = "No description available";
		assertTrue(AddCatalogPageBaseUtils.verifyCatalogDescription(page, catalogDescription));
	}
	
	@Test
	public void testUsage() {
		ViewUsagePageUtils.clickOnUsageTab(page);
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Pixel"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Python"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Java"));
	}
	
	/////////////////// EDIT
	
	@Test
	public void testEdit() throws IOException, InterruptedException {
		EditMetadataPageUtils.clickEditIcon(page);
		EditMetadataPageUtils.clickOnClose(page);
	}
	
	@Test
	public void testViewMetadataTags() throws IOException {
		EditMetadataPageUtils.clickEditIcon(page);
		String tagName = "embeddings";
		EditMetadataPageUtils.enterTagName(page, tagName);
		EditMetadataPageUtils.clickOnSubmit(page);
		//TODO fix the toast message check
//		AddCatalogPageBaseUtils.verifyEditSuccessfullToastMessage(page);
		List<String> tags = EditModelPageUtils.verifyTagNames(page);
		assertTrue(tags.contains(tagName));
	}
	
	@Test
	public void testExport() {
		Path path = GuardrailPageUtils.downloadCatalog(page);
		assertTrue(path.toFile().exists());
		assertTrue( path.toAbsolutePath().getFileName().toString().contains(GUARDRAIL_ID));
	}

	@AfterAll
	void deleteGuardrail() {
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, GUARDRAIL_ID);
	}
}
