# Database BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/database/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Database Types Available](#database-types-available)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the database-related feature files located under `src/test/resources/Features/database/`. It covers database creation (ZIP, CSV, TSV, Excel, H2, SQLite, connection types), database form field validation, metadata management, query functionality, usage examples, overview, tags, catalog filtering, bookmarking, and available database options verification.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | All database features | Opens the main navigation menu |
| 2 | `Given User clicks on Open Database` | All database features | Opens the Database catalog page |
| 3 | `Given User can see the Catalog title as '{string}'` | Add Zip DB, DB Query, DB Overview | Validates the catalog title is displayed |
| 4 | `Given User can see the database title as '{string}'` | Add H2 DB, Add SQLite DB | Validates the database title is displayed |
| 5 | `Given User sees the database name as '{string}'` | Add Zip DB | Validates database name displayed |

---

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All database features | Opens the main navigation menu |
| 2 | `When User clicks on Open Database` | All database features | Opens the Database catalog page |
| 3 | `When User clicks on Add Database` | All database features | Clicks Add Database button |

### Database Creation - ZIP Upload

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `When User clicks on file upload icon` | Add Zip DB, View DB Catalog, DB Query | Clicks the file upload icon |
| 5 | `When User uploads the file '{string}'` | Add Zip/CSV/TSV/Excel DB, View DB Catalog, DB Query | Uploads a database file (e.g., 'Database/TestDatabase.zip') |
| 6 | `When User clicks on '{string}' button to create catalog` | Add Zip DB, View DB Catalog, DB Query | Clicks Upload button to create catalog |
| 7 | `When User clicks on Copy Catalog ID` | All creation features | Copies the database catalog ID |
| 8 | `When User sees success toast message '{string}'` | Add Zip DB, DB Query, View DB Catalog | Validates success toast message |

### Database Creation - File Uploads (CSV/TSV/Excel)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 9 | `When User clicks on the 'file uploads' tab from options` | Add TSV/CSV/Excel DB, File Upload Types | Clicks file uploads tab |
| 10 | `When User selects '{string}' file type` | Add TSV/CSV/Excel DB | Selects file type (CSV, TSV, Excel) |
| 11 | `When User enters '{string}' as Database Name` | Add TSV/CSV/Excel DB | Enters database name |
| 12 | `When User selects '{string}' as database type` | Add TSV/CSV/Excel DB | Selects database type (e.g., h2) |
| 13 | `When User selects '{string}' as Metamodel type` | Add TSV/CSV/Excel DB | Selects metamodel type (asFlatTable, fromScratch, asSuggestedMetaModel) |
| 14 | `When User click on Connect button` | Add TSV/CSV/Excel DB, Add H2 DB, Add SQLite DB | Clicks Connect button |
| 15 | `When User sees the colunm names with edit button and delete button` | Add TSV/CSV/Excel DB | Validates column names with edit/delete buttons |
| 16 | `When User clicks on Import button` | Add TSV/CSV/Excel DB | Clicks Import button |
| 17 | `When User clicks on Save button for database` | Add TSV DB (Suggested MetaModel) | Clicks Save button for database |

### Database Creation - File Uploads (Multiple Files)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 18 | `When User uploads the file '{string}'` (second file) | Add TSV/CSV Multiple Files | Uploads second file for multi-file import |

### Database Creation - Suggested MetaModel Relationship

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `When User verifies the '{string}' table name is displayed` | Add TSV DB (Suggested MetaModel) | Validates table name is displayed |
| 20 | `When User verifies the full screen button is enabled` | Add TSV DB (Suggested MetaModel) | Validates full screen button |
| 21 | `When User clicks on the select table button and verifies the table is selected` | Add TSV DB (Suggested MetaModel) | Clicks select table button |
| 22 | `When User reset button is enabled` | Add TSV DB (Suggested MetaModel) | Validates reset button is enabled |
| 23 | `When User clicks on create relationship button and creates relationship between '{string}' and '{string}' tables` | Add TSV DB (Suggested MetaModel) | Creates relationship between parent and child tables |
| 24 | `When User verifies save button is enabled` | Add TSV DB (Suggested MetaModel) | Validates save button enabled |
| 25 | `When User verifies cancel button is enabled` | Add TSV DB (Suggested MetaModel) | Validates cancel button enabled |

### Database Creation - Connection Types (H2)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 26 | `When User selects database '{string}' from connection types` | All DB Types, Add H2 DB, Add SQLite DB | Selects database type from connections |
| 27 | `When User enters '{string}' as Catalog Name` | Add H2 DB, Add SQLite DB | Enters catalog name |
| 28 | `When User Upload '{string}' as Host Name` | Add H2 DB, Add SQLite DB | Enters host name |
| 29 | `When User clear the Port Number` | Add H2 DB | Clears the port number field |
| 30 | `When User Upload '{string}' as Schema Name` | Add H2 DB | Enters schema name |
| 31 | `When User add '{string}' as Username` | Add H2 DB | Enters username |
| 32 | `When User add '{string}' as JDBC URL for '{string}' database` | Add H2 DB, Add SQLite DB | Enters JDBC URL for specific database |
| 33 | `When User clicks on Apply button` | Add H2 DB, Add SQLite DB | Clicks Apply button |
| 34 | `When User clicks on Import database button` | Add H2 DB, Add SQLite DB | Clicks Import database button |

### Database Catalog Search & Selection

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 35 | `When User searches the '{string}' in the database Catalog searchbox` | Add Zip DB, Add H2 DB, Add SQLite DB, View DB Catalog, DB Metadata | Searches database in catalog searchbox |
| 36 | `When User sees the database name '{string}' in the database catalog` | Add Zip DB, Add SQLite DB, View DB Catalog, DB Metadata | Validates database visible in catalog |
| 37 | `When User sees the database name '{string}' in database catalog` | Add SQLite DB | Validates database visible in catalog |
| 38 | `When User clicks on the database name '{string}' in database catalog` | Add Zip DB, Add H2 DB, Add SQLite DB, DB Metadata | Clicks on database in catalog |
| 39 | `When User clicks on the database name '{string}' in the database catalog` | Add Zip DB | Clicks on database in catalog |
| 40 | `When User selects the '{string}' from the database catalog` | Add Zip DB | Selects database from catalog |
| 41 | `When User checks if 'Database' catalog created and Deletes the '{string}'` | Add Zip DB, View DB Catalog, DB Query | Checks and deletes existing catalog |

### Database Edit & Metadata

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 42 | `When User clicks on Edit button` | Add Zip DB, View DB Catalog | Clicks Edit button |
| 43 | `When User add Tags '{string}' and presses Enter` | Add Zip DB, View DB Catalog | Adds tags to the database |
| 44 | `When User enters the Domains as '{string}'` | View DB Catalog | Enters domain values |
| 45 | `When User selects '{string}' from the Data Classification dropdown` | View DB Catalog | Selects data classification |
| 46 | `When User selects '{string}' from the Data Restrictions dropdown` | View DB Catalog | Selects data restrictions |
| 47 | `When User clicks on Submit button` | Add Zip DB, View DB Catalog | Clicks submit to save changes |

### Database MetaData Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 48 | `When User clicks on MetaData tab` | Add Zip DB, DB Metadata | Clicks MetaData tab |
| 49 | `When User clicks on Refresh button` | Add Zip DB, DB Metadata | Clicks Refresh button on MetaData |
| 50 | `When User selects the '{string}' from the dropdown` | Add Zip DB, DB Metadata | Selects table from dropdown (e.g., 'DIABETES') |
| 51 | `When User clicks on apply database button` | Add Zip DB, DB Metadata | Clicks apply database button |
| 52 | `When User sees the Save button is '{string}'` | DB Metadata | Validates Save button state (enabled/disabled) |
| 53 | `When User clicks on Save button of Metadata tab` | DB Metadata | Clicks Save button on Metadata tab |

### Database Usage Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 54 | `When User clicks on Usage tab for Database` | Add Zip DB | Clicks Usage tab for Database |

### Database Overview

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 55 | `When User can see 'copy Database ID' Database ID` | Add Zip DB | Validates Database ID is visible |
| 56 | `When User clicks on copy icon of Database ID` | Add Zip DB | Clicks copy icon for Database ID |
| 57 | `When User can see toast message as '{string}'` | Add Zip DB | Validates toast message |
| 58 | `When User clicks on Access Control Tab` | Add Zip DB | Clicks Access Control tab |
| 59 | `When User clicks on Add Member button` | Add Zip DB | Clicks Add Member button |
| 60 | `When User adds one user and assigns them as '{string}'` | Add Zip DB | Adds user with role |
| 61 | `When User clicks on Export button that creates a Zip of DB when clicked` | Add Zip DB | Clicks Export button to download ZIP |
| 62 | `When User sees an Edit button that opens a pop-up to edit` | Add Zip DB | Validates Edit button opens popup |
| 63 | `When User clicks on Close button` | Add Zip DB | Clicks Close button |

### Database Query Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 64 | `When User clicks on Query tab` | DB Query | Clicks Query tab |
| 65 | `When User enters the query '{string}'` | DB Query | Enters SQL query |
| 66 | `When User clicks on Run Query button` | DB Query | Clicks Run Query button |
| 67 | `When User clicks on Reset button for database` | DB Query | Clicks Reset button |
| 68 | `When User clicks on '{string}' button` | DB Query | Clicks Collapse All / Expand All button |
| 69 | `When User clicks on 'Expand table' arrow` | DB Query | Clicks expand table arrow |
| 70 | `When User searches the '{string}' column in data columns searchbox` | DB Query | Searches column in data columns searchbox |
| 71 | `When User clicks on Refresh database structure icon` | DB Query | Clicks Refresh database structure icon |

### Database Catalog Filter & Bookmark

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 72 | `When User clicks on bookmark button of '{string}' catalog` | View DB Catalog | Clicks bookmark button for catalog |
| 73 | `When User clicks on bookmark button to unbookmark '{string}' catalog` | View DB Catalog | Clicks bookmark button to unbookmark |

### View Add Database Options

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 74 | `When User should see Search bar to filter database options` | View Add DB Options | Validates search bar on add database page |

---

## Then Steps

### Database Creation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see the Catalog title as '{string}'` | Add Zip/TSV/CSV/Excel DB, View DB Catalog, DB Query | Validates catalog title displayed |
| 2 | `Then User can see the database title as '{string}'` | Add H2 DB, Add SQLite DB | Validates database title displayed |
| 3 | `Then User sees success toast message '{string}'` | Add Zip DB, DB Query | Validates success toast message |

### Database Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `Then User can see form sections with fields:` | All DB Types, File Upload Types | Validates form sections and fields with data table |
| 5 | `Then User can see database mandatory fields` | All DB Types, File Upload Types | Validates mandatory fields with data table |

### Database MetaData Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 6 | `Then User sees the table in the metadata tab` | Add Zip DB, DB Metadata | Validates table in metadata tab |

### Database Edit Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 7 | `Then User can see a edit success toast message as '{string}'` | Add Zip DB, View DB Catalog | Validates edit success toast |
| 8 | `Then User should see '{string}' on the page` | Add Zip DB | Validates text/tag visible on page |

### Database Usage Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 9 | `Then User sees an example of "{string}" with example code for Database` | Add Zip DB | Validates usage example with code (Javascript, Python, Langchain API, Java) |

### Database Query Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `Then User sees '{string}' columns in the query response table` | DB Query | Validates columns in query response |
| 11 | `Then User can see query field is empty` | DB Query | Validates query field is cleared |
| 12 | `Then User can see all data columns are collapsed` | DB Query | Validates all columns are collapsed |
| 13 | `Then User can see button name changed to '{string}' button` | DB Query | Validates button name changed (Expand All / Collapse All) |
| 14 | `Then User can see '{string}' columns displayed under data columns section` | DB Query | Validates all columns displayed |
| 15 | `Then User can see only '{string}' column in the list` | DB Query | Validates single column in filtered list |
| 16 | `Then User can see 'Refreshing database structure' tile` | DB Query | Validates refresh tile message |

### Database Catalog Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `Then User sees the database name '{string}' in the database catalog` | Add Zip DB, View DB Catalog, DB Metadata | Validates database visible in catalog |
| 18 | `Then User sees the database name '{string}' in database catalog` | Add SQLite DB | Validates database visible in catalog |

### Database Catalog Filter Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `Then User applies each filter and validate '{string}' catalog is visible on the '{string}' catalog page` | View DB Catalog | Validates filter functionality with data table |
| 20 | `Then User sees the catalog name '{string}' in the Bookmarked section` | View DB Catalog | Validates catalog in Bookmarked section |

### View Add Database Options Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `Then User should see Search bar to filter database options` | View Add DB Options | Validates search bar visible |
| 22 | `Then User should see the following '{string}' options with icons on the page` | View Add DB Options | Validates database options with groups using data table |
| 23 | `Then User searches '{string}' types and verifies visibility under respective sections` | View Add DB Options | Validates search filter for database types with data table |

### File Upload Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 24 | `Then User can see form sections with fields:` | File Upload Types | Validates file upload form sections (General, Database) |
| 25 | `Then User can see database mandatory fields` | File Upload Types | Validates file upload mandatory fields |

---

## Database Types Available

### Connection Types

| # | Database | Settings Fields | Credentials Fields | Advanced Settings Fields | Mandatory Fields |
|---|----------|----------------|-------------------|------------------------|-----------------|
| 1 | Aster | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 2 | Athena | Region, Output, Schema, Additional Parameters, JDBC Url | Access Key, Secret Key | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Output, Access Key, Secret Key |
| 3 | BigQuery | Host Name, Port, Project, Schema, Additional Parameters, JDBC Url | OAuth Type, OAuth Service Account, OAuth Service Account Key | Not Available | Catalog Name, Project, OAuth Type |
| 4 | Cassandra | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 5 | ClickHouse | Host Name, Port, Database, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 6 | DATABRICKS | Host Name, Port, HTTP Path, Database, Schema, Additional Parameters, JDBC Url | UID, Personal Access Token | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, HTTP Path, UID |
| 7 | DataStax | Host Name, Port, GRAPH | Username, Password | Not Available | Catalog Name, Host Name, GRAPH |
| 8 | DB2 | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 9 | Derby | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 10 | Elastic Search | Host Name, Port, HTTP Type, Additional Parameters, JDBC Url | Username, Password | Not Available | Catalog Name, Host Name |
| 11 | H2 | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Schema, JDBC Url |
| 12 | Hive | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 13 | Impala | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 14 | MariaDB | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 15 | MySQL | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 16 | Open Search | Host Name, Port, HTTP Path, Additional Parameters, JDBC Url | Username, Password | Not Available | Catalog Name, Host Name |
| 17 | Oracle | Host Name, Port, SID Service, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 18 | Phoenix | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 19 | Postgres | Host Name, Port, Database, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Database |
| 20 | Redshift | Host Name, Port, Database, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Database |
| 21 | SAP Hana | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Schema |
| 22 | SEMOSS | Host Name, Port, Project Id, Insight Id, Endpoint, Protocol, Sub URL, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Project Id, Insight Id, Endpoint, Protocol |
| 23 | Snowflake | Host Name, Port, Warehouse, Role, Database, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Warehouse, Role, Database, Schema |
| 24 | SQL Server | Host Name, Port, Database, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 25 | SQLITE | Host Name, Port, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name |
| 26 | Teradata | Host Name, Database, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Database |
| 27 | Tibco | Host Name, Port, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Port, Schema |
| 28 | Trino | Host Name, Port, Catalog, Schema, Additional Parameters, JDBC Url | Username, Password | Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Port, Catalog, Schema |

### File Upload Types

| # | File Type | General Fields | Database Fields | Mandatory Fields |
|---|-----------|---------------|----------------|-----------------|
| 1 | CSV | Enter Database Name, Enter Database Description, Enter Database Tag | Enter Database Type, Enter Delimiter, Enter Metamodel Type | Enter Database Name, Enter Database Type, Enter Metamodel Type |
| 2 | TSV | Enter Database Name, Enter Database Description, Enter Database Tag | Enter Database Type, Enter Delimiter, Enter Metamodel Type | Enter Database Name, Enter Database Type, Enter Metamodel Type |
| 3 | Excel | Enter Database Name, Enter Database Description, Enter Database Tag | Enter Database Type, Enter Metamodel Type | Enter Database Name, Enter Database Type, Enter Metamodel Type |

### File Upload - Available Options

| Group | Database Options |
|-------|-----------------|
| File Uploads | CSV, Excel, TSV, SQLite, H2, Neo4J, Tinker |
| Connections | Aster, Athena, BigQuery, Cassandra, Clickhouse, DATABRICKS, DataStax, DB2, Derby, Elastic Search, H2, Hive, Impala, MariaDB, MySQL, Open Search, Oracle, Phoenix, Postgres, Redshift, SAP Hana, SEMOSS, Snowflake, SQL Server, SQLITE, Teradata, Tibco, Trino |

---

## Feature Files Summary

| # | Feature File | Scenarios | Description |
|---|-------------|-----------|-------------|
| 1 | Verify All Database Types with Fields | 1 Scenario Outline (28 examples) | Validate form sections, fields, and mandatory fields for all 28 connection types |
| 2 | Add TSV/CSV/Excel Database | 4 Scenario Outlines (8 examples) | Create databases using file uploads with asFlatTable, fromScratch, asSuggestedMetaModel metamodels; multi-file; relationship creation |
| 3 | Add H2 Database | 1 Scenario | Create H2 database with connection parameters and verify |
| 4 | Add SQLite Database | 1 Scenario | Create SQLite database with connection parameters and verify |
| 5 | Add Zip Database | 6 Scenarios | Create ZIP database, verify usage examples, tags, overview, metadata, save metadata |
| 6 | Validate Database Query Functionality | 6 Scenarios | Query response, reset, collapse/expand, data columns display, search columns, refresh structure |
| 7 | Verify File Upload Database Types with Fields | 1 Scenario Outline (3 examples) | Validate file upload form sections and mandatory fields for CSV, TSV, Excel |
| 8 | View Add Database Options | 2 Scenarios | Verify available database options with icons; search/filter database types |
| 9 | View Existing Databases on Catalog Page | 1 Scenario | Create, edit, filter by tags/domains/classification/restrictions, bookmark/unbookmark |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 5 |
| When Steps | 74 |
| Then Steps | 25 |
| **Total** | **104** |

---