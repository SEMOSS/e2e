Feature: View add Database options on catalog page

  #Scenario: Verify the available options on add database page
  #Given User opens Main Menu
  #And User clicks on Open Database
  #When User clicks on Add Database
  #Then User should see Search bar to filter database options
  #And User should see the following database options with icons on the page
  #| GROUP        | DATABASE_OPTIONS                                                                                                                                                                                                                                              |
  #| File Uploads | ZIP, CSV, Excel, TSV, SQLite, H2, Neo4J, Tinker                                                                                                                                                                                                               |
  #| Connections  | Aster, Athena, BigQuery, Cassandra, Clickhouse, DATABRICKS, DataStax, DB2, Derby, Elastic Search, H2, Hive, Impala, MariaDB, MySQL, Open Search, Oracle, Phoenix, Postgres, Redshift, SAP Hana, SEMOSS, Snowflake, SQL Server, SQLITE, Teradata, Tibco, Trino |
  
 Scenario: Verify the Database Type is search on Connect To Database Page
    Given User opens Main Menu
    And User is on Home page
    And User clicks on Open Database
    And User clicks on Add Database
    When User searches database types and verifies visibility under respective sections
    | ExpectedSection   | DatabaseTypeOption                                                                                                                                                                                                                                              |
  	| File Uploads 			| ZIP, CSV, Excel, TSV, SQLite, H2, Neo4J, Tinker                                                                                                                                                                                                               |
  	| Connections 		 | Aster, Athena, BigQuery, Cassandra, Clickhouse, DATABRICKS, DataStax, DB2, Derby, Elastic Search, H2, Hive, Impala, MariaDB, MySQL, Open Search, Oracle, Phoenix, Postgres, Redshift, SAP Hana, SEMOSS, Snowflake, SQL Server, SQLITE, Teradata, Tibco, Trino |
   
  
  
      #| DatabaseTypeOption | ExpectedSection |
      #| ZIP             	 | File Uploads    |
      #| CSV                | File Uploads    |
      #| Excel              | File Uploads    |
      #| TSV                | File Uploads    |
      #| SQLite             | File Uploads    |
      #| H2                 | File Uploads    |
      #| Neo4J              | File Uploads    |
      #| Tinker             | File Uploads    |
      #| Aster              | Connections     |
      #| Athena             | Connections     |
      #| BigQuery           | Connections     |
      #| Cassandra          | Connections     |
      #| Clickhouse         | Connections     |
      #| DATABRICKS         | Connections     |
      #| DataStax           | Connections     |
      #| DB2                | Connections     |
      #| Derby              | Connections     |
      #| Elastic Search     | Connections     |
      #| H2                 | Connections     |
      #| Hive               | Connections     |
      #| Impala             | Connections     |
      #| MariaDB            | Connections     |
      #| MySQL              | Connections     |
      #| Open Search        | Connections     |
      #| Oracle             | Connections     |
      #| Phoenix            | Connections     |
      #| Postgres           | Connections     |
      #| Redshift           | Connections     |
      #| SAP Hana           | Connections     |
      #| SEMOSS             | Connections     |
      #| Snowflake          | Connections     |
      #| SQL Server         | Connections     |
      #| SQLITE             | Connections     |
      #| Teradata           | Connections     |
      #| Tibco              | Connections     |
      #| Trino              | Connections     |

 