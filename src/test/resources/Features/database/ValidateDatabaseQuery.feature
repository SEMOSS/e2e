Feature: Validate Database Query Functionality

  Background: Create database and open Query tab
    Given User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    Then User can see the Catalog title as 'TestDatabase'

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate database query response
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User enters the query 'SELECT AGE, BMI from DIABETES'
    And User clicks on 'Run Query' button
    Then User sees 'AGE, BMI' columns in the query response table

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate reset query
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User enters the query 'SELECT AGE, BMI from DIABETES'
    And User clicks on 'Reset' button
    Then User can see query field is empty

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate collapse data columns
    Given User can see the Catalog title as 'TestDatabase'
    When User clicks on Query tab
    And User clicks on 'Collapse All' button
    Then User can see all data columns are collapsed
