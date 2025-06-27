Feature: Create drag and drop app

  Background: Create Drag and Drop app and navigate to Blocks option
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
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'

  @DeleteCreatedCatalog
  Scenario Outline: Drag and Drop '<BLOCK_NAME>' block
  Given User is on Home page
  When User searches 'Test app' app in the app searchbox
  And User clicks on 'Test app' app from the My Apps
  And User clicks on app Edit button
  And User clicks on Blocks if it is not selected by default
  And User clicks on Notebook
  And User clicks on Create new notebook
  And User enters New Query name as '<NOTEBOOK_NAME>'
  And User clicks on query Submit button
  And User mouse hover below the existing cell
  And User selects '<HIDDEN_OPTION>' from the hidden options
  And User selects '<DATA_IMPORT_OPTION>' from the data import options
  And User selects '<DATABASE_NAME>' from the dropdown list
  Then User can see 'Age, BMI, BloodPressure, DIABETES_1_UNIQUE_ROW_IDFK, DiabetesPedigreeFunction, Glucose, Insulin, Outcome, Pregnancies, SkinThickness' columns under the fields column
  When User selects all columns from database
  And User clicks on data Import button
  And User deletes the previous cell
  And User clicks on Run cell button
  And User fetch the frame id
  And User clicks on Blocks
  And User clicks on 'page-1' page
  And User drags the '<BLOCK_NAME>' block and drops it on the page
  And User clicks on the Block Settings option
  And User clicks on Data tab
  And User selects the frame from the Selected Frame dropdown
  And User drag and drop the '<COLUMN_NAMES>' columns to '<FIELD_NAMES>' fields
  Then User can see '<BLOCK_NAME>' chart same as baseline chart
  When User clicks on the Save App icon
  And User is on Home page
  And User clicks on Open Database
  Then User sees the database name 'TestDatabase' in the database catalog
  When User clicks on the database name 'TestDatabase' in the database catalog
  Examples:
  | NOTEBOOK_NAME | HIDDEN_OPTION | DATA_IMPORT_OPTION | DATABASE_NAME | BLOCK_NAME          | COLUMN_NAMES                     | FIELD_NAMES                                                |
  | Test          | Import Data   | From Data Catalog  | TestDatabase  | Scatter Plot        | Age, BloodPressure, BMI, Glucose | Select Label, Select X Axis, Select Y Axis, Select Tooltip |
  | Test          | Import Data   | From Data Catalog  | TestDatabase  | Line Chart          | Age, BloodPressure, BMI          | Select X Axis, Select Y Axis, Select Tooltip               |
  | Test          | Import Data   | From Data Catalog  | TestDatabase  | Bar Chart           | Age, BloodPressure               | Select X Axis, Select Y Axis                               |
  | Test          | Import Data   | From Data Catalog  | TestDatabase  | Bar Chart - Stacked | Age, BloodPressure, BMI          | Select X Axis, Select Y Axis, Select Tooltip               |
  
  
