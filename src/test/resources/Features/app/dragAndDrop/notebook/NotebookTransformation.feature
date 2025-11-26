Feature: Validate Transformation

  Background: Create database, drag and drop app, and notebook
    Given User is on Home page
    When User opens Main Menu
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
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'Custom Import (SQL)' from the data import options
    And User deletes the previous cell
    And User selects 'TestDatabase' database from the dropdown

  @Regression @LoginWithAdmin @DeleteCreatedTestApp @DeleteTestCatalog
  Scenario: Validate Timestamp function in Transformation
    And User writes the query 'SELECT BMI FROM DIABETES LIMIT 20'
    And User clicks on Run cell button
    And User fetch the frame id
    And User mouse hover below the existing cell
    And User selects 'Transformation' from the hidden options
    And User selects 'Timestamp' from the Transformation options
    And User selects the frame from the selected frame dropdown
    And User enters column name as 'BMI_IncludeTime'
    And User clicks on Include time checkbox
    And User click on Run All cell button
    Then User can see header names as 'BMI, BMI_IncludeTime'
    And User can see 'BMI_IncludeTime' column values as todays date along with current time
    When User clicks on Include time checkbox
    And User enters column name as 'BMI_ExcludeTime'
    And User click on Run All cell button    
    Then User can see header names as 'BMI, BMI_ExcludeTime'
    And User can see 'BMI_ExcludeTime' column values as todays date along with '00:00:00' as timestamp
