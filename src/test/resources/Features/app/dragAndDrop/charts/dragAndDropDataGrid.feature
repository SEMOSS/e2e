@DeleteTestCatalog @DeleteCreatedTestApp
Feature: Drag and Drop Data Grid

  Background: Create Drag and Drop app and navigate to Blocks option
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
     And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    Then User can see the Catalog title as 'TestDatabase'
    When User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name 
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User clicks on Blocks if it is not selected by default
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'From Data Catalog' from the data import options
    And User selects 'TestDatabase' from the dropdown list
    Then User can see 'Age, BMI, BloodPressure, DIABETES_UNIQUE_ROW_IDFK, DiabetesPedigreeFunction, End_Date, Glucose, Insulin, Milestone, Outcome, Pregnancies, SkinThickness, Start_Date, Task_Group, Task_Name, Tooltip' columns under the fields column
    When User selects all columns from database
    And User clicks on data Import button
    And User deletes the previous cell
    And User clicks on Run cell button
    And User fetch the frame id

  @LoginWithAdmin @Regression
  Scenario: Drag and Drop Data Grid block and validate the column names with removed column
    When User clicks on 'page-1' page
    And User clicks on Blocks
    And User drags the 'Data Grid' block and drops it on the page
    And User clicks on the 'Data Grid' block to select it
    And User clicks on the Block Settings option
    And User clicks on Data tab
    And User selects the frame from the selected frame dropdown
    And User clicks on the Sync icon
    And User can see the Data Grid column names as 'Age, BloodPressure, BMI, DIABETES_UNIQUE_ROW_ID, DiabetesPedigreeFunction, End_Date, Glucose, Insulin, Milestone, Outcome, Pregnancies, SkinThickness, Start_Date, Task_Group, Task_Name, Tooltip'
    And User remove the 'Age' column from the Data Grid
    And User clicks on the Sync icon
    And User should not see the 'Age' column in the Data Grid

  @LoginWithAdmin @DeleteTestCatalog @DeleteCreatedTestApp @Regression
  Scenario: validate the pagination on the Data Grid
    When User clicks on 'page-1' page
    And User clicks on Blocks
    And User drags the 'Data Grid' block and drops it on the page
    And User clicks on the 'Data Grid' block to select it
    And User clicks on the Block Settings option
    And User clicks on Data tab
    And User selects the frame from the selected frame dropdown
    And User clicks on the Sync icon
    Then User validates pagination for the following rows per page options
      |  10 |
      |  50 |
      | 100 |
