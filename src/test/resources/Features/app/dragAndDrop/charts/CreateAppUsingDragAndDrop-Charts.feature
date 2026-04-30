Feature: Create drag and drop app

  Background: Create Drag and Drop app
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name

  @DeleteTestCatalog @DeleteCreatedTestApp @Smoke @Regression @ApplicationBugFailure
  Scenario Outline: Drag and Drop '<BLOCK_NAME>' block
    When User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    When User clicks on Save button of Metadata tab
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
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
    Then User can see 'AGE, BLOODPRESSURE, BMI, DIABETESPEDIGREEFUNCTION, DIABETES_UNIQUE_ROW_IDFK, END_DATE, GLUCOSE, INSULIN, MILESTONE, OUTCOME, PREGNANCIES, SKINTHICKNESS, START_DATE, TASK_GROUP, TASK_NAME, TOOLTIP' columns under the fields column
    When User selects all columns from database
    And User clicks on data Import button
    And User deletes the previous cell
    And User enter the data limit as '20'
    And User clicks on Run cell button
    And User fetch the frame id
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the '<BLOCK_NAME>' block and drops it on the page
    And User clicks on the '<BLOCK_NAME>' block to select it
    And User clicks on the Block Settings option
    And User clicks on Data tab
    And User selects the frame from the selected frame dropdown
    And User drag and drop the '<COLUMN_NAMES>' columns to '<FIELD_NAMES>' fields
    Then User can see '<BLOCK_NAME>' chart same as baseline chart
    And User clicks on the '<BLOCK_NAME>' block to select it
    When User click on '<BLOCK_NAME>' chart duplicate icon
    Then Duplicate '<BLOCK_NAME>' chart should appear on the page
    When User click on '<BLOCK_NAME>' chart delete icon
    Then Duplicate '<BLOCK_NAME>' chart should be remove from the page
    When User clicks on the Save App icon

    Examples:
      | NOTEBOOK_NAME | HIDDEN_OPTION | DATA_IMPORT_OPTION | DATABASE_NAME | BLOCK_NAME          | COLUMN_NAMES                                                    | FIELD_NAMES                                                                                          |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | Scatter Plot        | AGE, BLOODPRESSURE, BMI, GLUCOSE                                | Select Label, Select X Axis, Select Y Axis, Select Tooltip                                           |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | Line Chart          | AGE, BLOODPRESSURE, BMI                                         | Select X Axis, Select Y Axis, Select Tooltip                                                         |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | Bar Chart           | AGE, BLOODPRESSURE                                              | Select X Axis, Select Y Axis                                                                         |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | Bar Chart - Stacked | AGE, BMI, GLUCOSE, INSULIN                                      | Select X Axis, Select Y Axis, Select Category, Select Tooltip                                        |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | Pie Chart           | AGE, BLOODPRESSURE                                              | Select Label, Select Value                                                                           |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | Gantt Chart         | TASK_NAME, START_DATE, END_DATE, TASK_GROUP, MILESTONE, TOOLTIP | Select Task, Select Start Date, Select End Date, Select Task Group, Select MileStone, Select Tooltip |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | Dendrogram Chart    | AGE, BLOODPRESSURE                                              | Select Dimensions, Select Facet                                                                      |
      | Test          | Import Data   | From Data Catalog  | TestDatabase  | World Map Chart     | DIABETES_UNIQUE_ROW_ID, AGE, BMI, SKINTHICKNESS, TOOLTIP        | Select Label, Select Latitude, Select Longitude, Select Size, Select Tooltip                         |

  @DeleteCreatedTestApp @Regression
  Scenario: Drag and Drop Mermaid Chart block
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Mermaid Chart' block and drops it on the page
    And User clicks on the 'Mermaid Chart' block to select it
    And User clicks on the Block Settings option
    And User enters 'A-->D' in graph TD section
    And User clicks on the Save App icon
    Then User can see 'Mermaid Chart' chart same as baseline chart
