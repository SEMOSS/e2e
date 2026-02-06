Feature: Validate Database Query Functionality

  Background: Create database and open Query tab
    Given User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User sees success toast message 'Successfully Created Database'
    And User clicks on Copy Catalog ID
    Then User can see the Catalog title as 'TestDatabase'

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate database query response
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User enters the query 'SELECT AGE, BMI from DIABETES'
    And User clicks on Run Query button
    Then User sees 'AGE, BMI' columns in the query response table

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate database query reset
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User enters the query 'SELECT AGE, BMI from DIABETES'
    And User clicks on Reset button for database
    Then User can see query field is empty

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate collapse data columns functionality
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User clicks on 'Collapse All' button
    Then User can see all data columns are collapsed
    And User can see button name changed to 'Expand All' button
    When User clicks on 'Expand table' arrow
  ## commented out the steps below due to the known bug (SEMOSS issue #557).
  #Then User can see button name changed to 'Collapse All' button
  #And User can see all data columns are collapsed
  
  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate all database columns displayed under data columns
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    Then User can see 'DIABETES_UNIQUE_ROW_ID, AGE, BMI, BLOODPRESSURE, DIABETESPEDIGREEFUNCTION, END_DATE, GLUCOSE, INSULIN, MILESTONE, OUTCOME, PREGNANCIES, SKINTHICKNESS, START_DATE, TASK_GROUP, TASK_NAME, TOOLTIP' columns displayed under data columns section

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate search data columns
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User searches the 'BMI' column in data columns searchbox
    Then User can see only 'BMI' column in the list

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate refresh data columns
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User clicks on Refresh database structure icon
    Then User can see 'Refreshing database structure' tile
