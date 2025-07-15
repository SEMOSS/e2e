Feature: View add Database options on catalog page

  Scenario: Verify the available options on add database page
   Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    Then User should see Search bar to filter database options
    And User should see the following database options with icons on the page
      | GROUP        | DATABASE_OPTIONS                                                                                                                                                                                                                                               |
      | File Uploads | ZIP, CSV, Excel, TSV, SQLite, H2, Neo4J, Tinker                                                                                                                                                                                                               |
      | Connections  | Aster, Athena, BigQuery, Cassandra, Clickhouse, DATABRICKS, DataStax, DB2, Derby, Elastic Search, H2, Hive, Impala, MariaDB, MySQL, Open Search, Oracle, Phoenix, Postgres, Redshift, SAP Hana, SEMOSS, Snowflake, SQL Server, SQLITE, Teradata, Tibco, Trino |
