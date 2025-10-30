@DeleteTestCatalog @DeleteCreatedTestApp @Regression
Feature: Create Notebook and execute it

  Background: Create database and notebook
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    #And User selects database 'ZIP'
    #And User uploads database file 'Database/TestDatabase.zip'
    And User selects the 'ZIP' option to upload file
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees success toast message 'ZIP uploaded successfully'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    And User clicks On Copy Catalog ID
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app

  Scenario: Execute Import Data query
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
