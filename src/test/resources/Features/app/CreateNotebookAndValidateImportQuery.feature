Feature: Create Notebook

  Background: Create Drag and Drop app and Notebook
    Given User is on Home page
    When User clicks on Open Database
    And User clicks on Add Database
    And User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    Then User sees the database name 'TestDatabase' in the database catalog
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'

  @DeleteCreatedCatalog
  Scenario: Validate import query functionality
    Given User is on Home page
    When User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Blocks if it is not selected by default
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User selects 'Query Import' from the hidden options
    And User deletes the previous cell
    And User writes the query 'SELECT * FROM Diabetes_1 where AGE = 50 AND BLOODPRESSURE = 90'
    And User clicks on Run cell button
    Then User sees the output of the executed query where Age is '50' and Bloodpressure is '90'
    And User is on Home page
    And User clicks on Open Database
    Then User sees the database name 'TestDatabase' in the database catalog
    When User clicks on the database name 'TestDatabase' in the database catalog
