@DeleteTestCatalog @DeleteCreatedTestApp @Regression
Feature: Notebook Validate Database Operations

  Background: Create Database, Drag and Drop App, and Notebook
    Given User is on Home page
    When User opens Main Menu
    #### Add Database
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'TestDatabase'
    When User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    ### Create Drag and Drop App
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'

  Scenario: Validate import db query functionality
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Blocks if it is not selected by default
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'Custom Import (SQL)' from the data import options
    And User deletes the previous cell
    And User selects 'TestDatabase' database from the dropdown
    And User writes the query 'SELECT * FROM DIABETES where AGE = 50 AND BLOODPRESSURE = 90'
    And User clicks on Run cell button
    Then User sees the output of the executed query where 'AGE' is '50'
    And User sees the output of the executed query where 'BLOODPRESSURE' is '90'
    
    
  Scenario: Import Data
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Blocks if it is not selected by default
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
    And User selects type as 'Python'
    And User enter the data limit as '20'
    And User clicks on Run cell button
    And User fetch the frame id
    Then User can see header names as 'Age, BloodPressure, BMI, DIABETES_UNIQUE_ROW_ID, DiabetesPedigreeFunction, End_Date, Glucose, Insulin, Milestone, Outcome, Pregnancies, SkinThickness, Start_Date, Task_Group, Task_Name, Tooltip'
    And User can see total '20' rows
    And User can see the 'DIABETES_UNIQUE_ROW_ID' column have unique values
    And User can see name as frame id in JSON
    And User can see type as 'PY' for 'Python' in JSON
    When User clicks on the Save App icon
