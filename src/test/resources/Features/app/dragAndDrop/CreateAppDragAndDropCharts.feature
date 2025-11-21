@DeleteCreatedTestApp @DeleteTestCatalog @Regression
Feature: Create drag and drop  for charts validation

  Background: Create Drag and Drop app and navigate to Blocks option
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    Given User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User selects the 'ZIP' option to upload file
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    And User opens Main Menu
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
    And User enter the data limit as '20'
    And User clicks on Run cell button
    And User fetch the frame id
    And User clicks on 'page-1' page
    And User clicks on Blocks if it is not selected by default

  Scenario Outline: Drag and Drop Charts section '<BLOCK_NAME>' block
    When User drags the '<BLOCK_NAME>' block and drops it on the page
    And User clicks on the '<BLOCK_NAME>' block to select it
    And User clicks on the Block Settings option
    And User clicks on Data tab
    And User selects the frame from the selected frame dropdown
    And User drag and drop the 'Age, Glucose' columns to 'Select X Axis, Select Y Axis' fields
    And User click on the Tools tab
    And User click on Conditional toole option
    And User validates Conditional using '<CONDITIONAL>'
    And User click on Color Palette toole option
    And User validates Color Palette using '<COLOR_PALETTE>'
    Then User can see '<COLOR_PALLETTE_CHART_NAME>' chart same as baseline chart
    And User click on Legend Option and turn on the toggle
    Then User can see '<LEGEND_CHART>' chart same as baseline chart
    #And User validates Colour By Value using '<COLOUR_BY_VALUE>'
    #And User validates Edit X Axis using '<EDIT_X_AXIS>'
    #And User validates Edit Y Axis using '<EDIT_Y_AXIS>'
    #And User validates Value Label using '<VALUE_LABEL>'
    #And User validates Bar Style using '<BAR_STYLE>'
    #And User validates Chart Title using '<CHART_TITLE>'
    #And User validates Resizing using '<RESIZING>'
    #And User validates Trendlines using '<TRENDLINES>'
   
    Examples:
    | BLOCK_NAME | CONDITIONAL | COLOR_PALETTE		| COLOR_PALLETTE_CHART_NAME 	| LEGEND_CHART |
    | Bar Chart  | false, true | Add Color, Change Color			| Bar Chart Tool | Legend Chart Tool |