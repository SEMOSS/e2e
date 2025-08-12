package aicore.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TestResourceTrackerHelper {

	private static final ThreadLocal<TestResourceTrackerHelper> instance = ThreadLocal
			.withInitial(TestResourceTrackerHelper::new);

	public static TestResourceTrackerHelper getInstance() {
		return instance.get();
	}

	private final Map<String, String> testCatalogResources = new HashMap<>();

	public static final String CATALOG_TYPE_DATABASE = "Database";
	public static final String CATALOG_TYPE_MODEL = "Model";
	public static final String CATALOG_TYPE_VECTOR = "Vector";
	public static final String CATALOG_TYPE_FUNCTION = "Function";
	public static final String CATALOG_TYPE_STORAGE = "Storage";

	public static final List<String> CATALOG_TYPES = List.of(CATALOG_TYPE_DATABASE, CATALOG_TYPE_MODEL,
			CATALOG_TYPE_VECTOR, CATALOG_TYPE_FUNCTION, CATALOG_TYPE_STORAGE);

	public void setCatalogId(String type, String id) {
		testCatalogResources.put(type, id);
	}

	public String getCatalogId(String type) {
		return testCatalogResources.get(type);
	}

	public Map<String, String> getCatalogType() {
		return new HashMap<>(testCatalogResources);
	}

	public void clearCatalogResources() {
		testCatalogResources.clear();
	}

	public Map<String, String> getCatalogType() {
		return new HashMap<>(testCatalogResources);
	}

}
