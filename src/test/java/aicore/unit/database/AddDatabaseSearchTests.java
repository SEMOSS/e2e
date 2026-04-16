package aicore.unit.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.pages.database.AddDatabaseFormUtils;
import aicore.pages.home.MainMenuUtils;
import aicore.steps.AbstractAddCatalogBase;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;

public class AddDatabaseSearchTests extends AbstractE2ETest {

	private static final String GROUP_NAME = "GROUP";
	private static final String DATABASE_OPTION_NAMES = "DATABASE_OPTIONS";

	@BeforeEach
	void setUp() {
		login(page, UserType.NATIVE);
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenDatabase(page);
		AddDatabaseFormUtils.clickAddDatabaseButton(page);

		assertTrue(AddCatalogPageBaseUtils.isSearchBarPresent(page));
	}

	@ParameterizedTest(name = "{index} => group={0}")
	@MethodSource("databaseSelectionData")
	void testDatabaseSelectionIsViewable(String group, String databaseOptions) {
		List<Map<String, String>> mapList = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put(GROUP_NAME, group);
		map.put(DATABASE_OPTION_NAMES, databaseOptions);
		mapList.add(map);

		AbstractAddCatalogBase.validateOptionsWithIcon("database", GROUP_NAME, DATABASE_OPTION_NAMES, mapList, page);
	}
	
	@ParameterizedTest(name = "{index} => group={0}")
	@MethodSource("databaseSelectionData")
	void testSearchDatabaseOption(String group, String databaseOptions) {
		List<Map<String, String>> mapList = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put(GROUP_NAME, group);
		map.put(DATABASE_OPTION_NAMES, databaseOptions);
		mapList.add(map);

		AbstractAddCatalogBase.validateSearchOptions("database", GROUP_NAME, DATABASE_OPTION_NAMES, mapList, page);
	}

	private static Stream<Arguments> databaseSelectionData() {
		return Stream.of(Arguments.of("File Uploads", "CSV, Excel, TSV, SQLite, H2, Neo4J, Tinker"),
				Arguments.of("Connections",
						"Aster, Athena, BigQuery, Cassandra, Clickhouse, "
								+ "Databricks, DataStax, DB2, Derby, Elastic Search, "
								+ "H2, Hive, Impala, MariaDB, MySQL, Open Search, "
								+ "Oracle, Phoenix, Postgres, Redshift, SAP Hana, "
								+ "SEMOSS, Snowflake, SQL Server, SQLITE, Teradata, Tibco, Trino"));
	}

}
