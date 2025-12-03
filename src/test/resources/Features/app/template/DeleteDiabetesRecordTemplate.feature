@DeleteTestCatalog @DeleteCreatedTestApp
Feature: Create app using Delete diabetesTemplate

  Background: User create the Diabetes database using zip file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID

  @LoginWithAdmin @Regression
  Scenario: Create app using delete Diabetes Record Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Delete Diabetes Record" from Template List
    And User enters app name as 'Diabetes app'
    And User enters description as 'Diabetes app created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Notebook
    And User clicks on Query name as 'on-page-load'
    And User selects 'TestDatabase' database from the dropdown
    And User clicks on Run cell button of database cell
    Then User can see the output for database cell

  @LoginWithAdmin @Regression @ApplicationBugFailure
  Scenario: Create app using delete Diabetes Record Template in existing data
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Delete Diabetes Record" from Template List
    And User enters app name as 'Diabetes app'
    And User enters description as 'Diabetes app created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Preview app button
    And user selects "4" from "Select Unique ID" dropdown
    And User click on 'Delete' Record button
    Then User sees the success message "true"
    When User close the Preview app window
    And User clicks on Notebook
    And User clicks on Query name as 'on-page-load'
    And User selects 'TestDatabase' database from the dropdown
    And User clicks on Run cell button of database cell
    Then User can see the output for database cell
    When User modify the Sql query "SELECT * from diabetes WHERE DIABETES_UNIQUE_ROW_ID = 4"
    And User clicks on Run cell button of database cell
    Then User sees the output of the executed query with empty result
