Feature: View add Database options on catalog page

  #Scenario: Verify the available options on add database page
    #Given User clicks on Open Database
    #When User clicks on Add Database
    #Then User should see Search bar to filter database options
    #And User should see the following database options with icons on the page
      #| GROUP        | DATABASE_OPTIONS                                                                                                                                                                                                                                              |
      #| File Uploads | ZIP, CSV, Excel, TSV, SQLite, H2, Neo4J, Tinker                                                                                                                                                                                                               |
      #| Connections  | Aster, Athena, BigQuery, Cassandra, Clickhouse, DATABRICKS, DataStax, DB2, Derby, Elastic Search, H2, Hive, Impala, MariaDB, MySQL, Open Search, Oracle, Phoenix, Postgres, Redshift, SAP Hana, SEMOSS, Snowflake, SQL Server, SQLITE, Teradata, Tibco, Trino |

  Scenario Outline: Verify the Database Type is search on Connect To Database Page
    Given User opens Main Menu
    And User is on Home page
    And User clicks on Open Database
    And User clicks on Add Database
    When User enters "<DatabaseTypeOption>" in the search box
    Then "<DatabaseTypeOption>" should be visible under "<ExpectedSection>"
   

    Examples: 
      | DatabaseTypeOption | ExpectedSection |
      | ZIP                | File Uploads    |
      | CSV                | File Uploads    |
      #| Excel              | File Uploads    |
      #| TSV                | File Uploads    |
      #| SQLite             | File Uploads    |
      #| H2                 | File Uploads    |
      #| Neo4J              | File Uploads    |
      #| Tinker             | File Uploads    |
       | Aster              | Connections     |
      #| Athena             | Connections     |
      #| BigQuery           | Connections     |
      #| Cassandra          | Connections     |
      #| Clickhouse         | Connections     |
      #| DATABRI..          | Connections     |
      #| DataStax           | Connections     |
      #| DB2                | Connections     |
      #| Derby              | Connections     |
      #| Elastic Search     | Connections     |
