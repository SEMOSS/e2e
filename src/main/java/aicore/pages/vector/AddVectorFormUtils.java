package aicore.pages.vector;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import aicore.framework.AICoreTestConstants;
import aicore.framework.ConfigUtils;

public class AddVectorFormUtils {
	private static final String CATALOG_NAME_TEXTBOX_ID = "//input[@data-testid='vector-form-input-NAME']";
	private static final String VECTOR_TAGS_FIELD_DATA_TESTID = "vector-form-input-TAGS";

	// credentials
	private static final String HOST_NAME_ID = "//*[@data-testid='vector-form-input-HOSTNAME']//input";
	private static final String API_KEY_ID = "//*[@data-testid='vector-form-input-API_KEY']//input";
	private static final String NAME_SPACE_ID = "//*[@data-testid='vector-form-input-NAMESPACE']//input";

	// setting options
	private static final String EMBEDDER_DROPDOWN_DATATESTID = "vector-form-input-EMBEDDER_ENGINE_ID";
	private static final String CHUNKING_STRATEGY_DROPDOWN_DATATESTID = "vector-form-input-CHUNKING_STRATEGY";
	private static final String CONTENT_LENGTH_ID = "//input[@data-testid='vector-form-input-CONTENT_LENGTH']";
	private static final String CONTENT_OVERLAP_ID = "//input[@data-testid='vector-form-input-CONTENT_OVERLAP']";

	private static final String CREATE_VECTOR_BUTTON_XPATH = "//button[@type='submit']";

	public static void selectConnections(Page page, String connectionName) {
		Locator locator = page.locator("p", new Page.LocatorOptions().setHasText(connectionName));
		locator.click();
	}

	public static void enterVectorCatalogName(Page page, String vCatalogName) {
		page.fill(CATALOG_NAME_TEXTBOX_ID, vCatalogName);
	}

	public static void enterVectorTag(Page page, String vTag) {
		Locator tagInput = page.getByTestId(VECTOR_TAGS_FIELD_DATA_TESTID);
		tagInput.fill(vTag);
		tagInput.press("Enter");
	}

	///////////////////////// Credentials
	public static void enterHostName(Page page) {
		String hostName = ConfigUtils.getValue(AICoreTestConstants.PINECONE_HOST_NAME);
		page.locator(HOST_NAME_ID).isVisible();
		page.locator(HOST_NAME_ID).fill(hostName);
	}

	public static void enterApiKey(Page page) {
		String apiKey = ConfigUtils.getValue(AICoreTestConstants.PINECONE_API_KEY);
		page.locator(API_KEY_ID).isVisible();
		page.locator(API_KEY_ID).fill(apiKey);
	}

	public static void enterNameSpace(Page page, String namespace) {
		page.locator(NAME_SPACE_ID).isVisible();
		page.locator(NAME_SPACE_ID).fill(namespace);
	}

	/////////////////////////// Settings
	public static void selectModelfromEmbedderDropdown(Page page, String modelName) {
		page.getByTestId(EMBEDDER_DROPDOWN_DATATESTID).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(modelName)).click();
	}

	public static void selectStrategyfromChunkingStrategyDropdown(Page page, String strategyName) {
		page.getByTestId(CHUNKING_STRATEGY_DROPDOWN_DATATESTID).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(strategyName)).click();
	}

	public static void enterContentLength(Page page, String contentLengthValue) {
		Locator contentLength = page.locator(CONTENT_LENGTH_ID);
		if (contentLength.isVisible()) {
			contentLength.fill("");
			contentLength.fill(contentLengthValue);
		}
	}

	public static void enterContentOverlap(Page page, String contentOverlap) {
		page.fill(CONTENT_OVERLAP_ID, contentOverlap);
	}

	public static void clickOnCreateVectorButton(Page page) {
		page.locator(CREATE_VECTOR_BUTTON_XPATH).scrollIntoViewIfNeeded();
		page.locator(CREATE_VECTOR_BUTTON_XPATH).click();
	}
}
