@DeleteTestCatalog @DeleteCreatedTestApp
Feature: Create app using Read diabetes Template

  Background: User create the Diabetes database using zip file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
     And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID

  @LoginWithAdmin @Regression
  Scenario: Create app using Read Diabetes Record Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Read Diabetes Record" from Template List
    And User enters app name as 'Diabetes app'
    And User enters description as 'Diabetes app created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name 
    And User clicks on Notebook
    And User clicks on Query name as 'on-page-load'
     And User selects 'TestDatabase' database from the dropdown
    And User clicks on Run cell button of database cell
    Then User can see the output for database cell

  @LoginWithAdmin @Regression @ApplicationBugFailure
  Scenario: Create app using Read Diabetes Record Template with updating existing data
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Read Diabetes Record" from Template List
    And User enters app name as 'Diabetes app'
    And User enters description as 'Diabetes app created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name 
    And User clicks on Preview app button
    And user selects "4" from "Select Unique ID" Read App dropdown
    Then user sees the record with Unique ID "4"
