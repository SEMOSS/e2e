package aicore.unit.guardrail;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

	private static String GUARDRAIL_ID = null;

	@BeforeAll
	public static void setupBeforeAll() throws IOException {
		login(page, UserType.NATIVE);
		String timestamp = CommonUtils.getTimeStampName();
		String catalogName = "guardrail" + timestamp;
		GUARDRAIL_ID = GuardrailTestUtils.createGlinerGuardrail(page, catalogName);
	}
	
	@Test
	public void testOverview() throws IOException {
		AddDatabasePageUtils.clickOnOverview(page);
		String catalogDescription = "No description available";
		assertTrue(AddCatalogPageBaseUtils.verifyCatalogDescription(page, catalogDescription));
	}
	
	@Test
	public void testUsage() throws IOException {
		ViewUsagePageUtils.clickOnUsageTab(page);
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Pixel"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Python"));
		assertTrue(ViewUsagePageUtils.verifyExample(page, "How to use in Java"));
	}
	
	/////////////////// EDIT
	
	@Test
	public void testEdit() throws IOException, InterruptedException {
		AddCatalogPageBaseUtils.clickEditIcon(page);
		AddCatalogPageBaseUtils.clickOnClose(page);
	}
	
	@Test
	public void testViewMetadataTags() throws IOException {
		AddCatalogPageBaseUtils.clickEditIcon(page);
		String tagName = "embeddings";
		AddCatalogPageBaseUtils.enterTagName(page, tagName);
		AddCatalogPageBaseUtils.clickOnSubmit(page);
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
	static void deleteGuardrail() {
		CommonUtils.navigateAndDeleteCatalog(page, TestResourceTrackerHelper.CATALOG_TYPE_GUARDRAIL, GUARDRAIL_ID);
	}
}
