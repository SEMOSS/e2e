@DeleteTestCatalog @DeleteCreatedTestApp @Regression
Feature: Export Function for Drag And Drop App

  Background: Create Database, Drag and Drop App, and Notebook
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    Then User can see the Catalog title as 'TestDatabase'
    When User clicks on MetaData tab
    And User clicks on Refresh button
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    When User clicks on Save button of Metadata tab
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    Then User fetch the app name
    And User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'

  Scenario: Validate Export Data functionality
    Given User clicks on Block Settings option
    When  User clicks on Blocks if it is not selected by default
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'From Data Catalog' from the data import options
    And User selects 'TestDatabase' from the dropdown list
    Then User can see 'AGE, BLOODPRESSURE, BMI, DIABETESPEDIGREEFUNCTION, DIABETES_UNIQUE_ROW_IDFK, END_DATE, GLUCOSE, INSULIN, MILESTONE, OUTCOME, PREGNANCIES, SKINTHICKNESS, START_DATE, TASK_GROUP, TASK_NAME, TOOLTIP' columns under the fields column
    When User selects all columns from database
    And User clicks on data Import button
    And User deletes the previous cell
    And User selects type as 'Python'
    And User enter the data limit as '20'
    And User clicks on Run cell button
    And User fetch the frame id
    Then User can see header names as 'AGE, BLOODPRESSURE, BMI, DIABETES_UNIQUE_ROW_ID, DIABETESPEDIGREEFUNCTION, END_DATE, GLUCOSE, INSULIN, MILESTONE, OUTCOME, PREGNANCIES, SKINTHICKNESS, START_DATE, TASK_GROUP, TASK_NAME, TOOLTIP'
    And User clicks on Export option
    Then User can sees the Export Data section
    And User select the frame for export data
    And User Select the file type as 'csv'
    When User clicks on 'Create Export Button' for Export Data
    Then User can sees the exported file in Notebook section with expected frame and file type as 'csv'
