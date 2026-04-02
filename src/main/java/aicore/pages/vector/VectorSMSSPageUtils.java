package aicore.pages.vector;
import com.microsoft.playwright.Page;

public class VectorSMSSPageUtils {
	private static final String NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'NAME')]";
	private static final String EMBEDDER_ENGINE_NAME_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'EMBEDDER_ENGINE_NAME')]";
	private static final String CONTENT_LENGTH_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CONTENT_LENGTH')]";
	private static final String CONTENT_OVERLAP_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CONTENT_OVERLAP')]";
	private static final String CHUNKING_STRATEGY_SMSS_PROPERTIES_XPATH = "//div[@class='view-line']//span[@class='mtk1'][starts-with(text(), 'CHUNKING_STRATEGY')]";
	
	public static String verifyNameFiledInSMSS(Page page) {
		String name = page.textContent(NAME_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public static String verifyEmbedderEngineNameInSMSS(Page page) {
		String name = page.textContent(EMBEDDER_ENGINE_NAME_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public static String verifyContentLengthInSMSS(Page page) {
		String name = page.textContent(CONTENT_LENGTH_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public static String verifyContentOverlapInSMSS(Page page) {
		String name = page.textContent(CONTENT_OVERLAP_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}

	public static String verifyChunkingStrategyInSMSS(Page page) {
		String name = page.textContent(CHUNKING_STRATEGY_SMSS_PROPERTIES_XPATH).trim();
		return name;
	}
}
