Feature: Create App and validate Variables

  Background: Create Drag and Drop app to validate the variables
    Given User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'

  @LoginWithAdmin @DeleteTestCatalog
  Scenario: Validate Block Variables in Drag and Drop App.
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the 'Input' block and drops it on the page
    And User clicks on the Save App icon
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'BlockVariable'
    And User selects variable type as 'block'
    And User enters variable value as 'input--1'
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation 'BlockVariable'
    And User sees the variable with name 'BlockVariable' and type 'block' in the variable list

  @LoginWithAdmin @DeleteTestCatalog
  Scenario Outline: Validate Query & Cell Variables in Drag and Drop App.
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test query'
    And User clicks on query Submit button
    Then User Sees Python as the default language
    And User hovers and clicks on the cell
    And User deletes the previous cell
    And User enters code as '1+1'
    And User clicks on Run this cell and below icon
    Then User can see Python output as '2'
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as '<variable_name>'
    And User selects variable type as '<variable_type>'
    And User enters variable value as '<variable_value>'
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation '<variable_name>'

    Examples:
      | variable_name | variable_type | variable_value |
      | QueryVariable | query         | Test query     |
      | CellVariable  | cell          | Test query.2   |

  @LoginWithAdmin @DeleteTestCatalog
  Scenario: Validate Database Variables in Drag and Drop App.
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Database
    And User clicks on Add Database
    And User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees success toast message 'ZIP uploaded successfully'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    And User clicks On Copy Catalog ID
    And User get the CatalogName for variable
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'TestDatabase'
    And User selects variable type as 'database'
    And User enters variable value
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation 'TestDatabase'
    And User sees the variable with name 'TestDatabase' and type 'database' in the variable list

  @LoginWithAdmin @DeleteTestCatalog
  Scenario: Validate function Variables in Drag and Drop App.
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog
    And User clicks On Copy Catalog ID
    And User get the CatalogName for variable
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'WeatherFunctionTest'
    And User selects variable type as 'function'
    And User enters variable value
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation 'WeatherFunctionTest'
    And User sees the variable with name 'WeatherFunctionTest' and type 'function' in the variable list

  @LoginWithAdmin @DeleteTestCatalog
  Scenario: Validate Model Variables in Drag and Drop App.
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'ModelVariableTest'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    And User get the CatalogName for variable
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'ModelVariable'
    And User selects variable type as 'model'
    And User enters variable value
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation 'ModelVariable'
    And User sees the variable with name 'ModelVariable' and type 'model' in the variable list
  # @LoginWithAdmin @DeleteTestCatalog
  # Scenario: Validate Vector Variables in Drag and Drop App.
  #  # adding embedder for use when creating vector DB
  #   Given User opens Main Menu
  #   When User clicks on Open Model
  #   And User clicks on Add Model
  #   And User selects 'GPT-3.5'
  #   And User enters Catalog name as 'Catalog'
  #   And User enters open AI Key as 'Test@1234'
  #   And User enters var name as 'Variable1'
  #   And User clicks on Create Model button
  #   Then User can see a toast message as 'Successfully added LLM to catalog'
  #   When User clicks on Edit button
  #   And User add tags 'embeddings' and presses Enter
  #   And User clicks on Submit button
  #   When User opens Main Menu
  #   And User clicks on Open Vector
  #   And User clicks on vector 'Discoverable Vectors' tab
  #   And User clicks on Add vector button
  #   And User selects 'FAISS' as connection
  #   And User enters 'Catalog Name' as 'FAISSCatalogeeVectorr'
  #   And User selects 'Catalog' from embedder field
  #   And User selects 'Token' from chunking strategy field
  #   And User enters 'Content Length' as '512'
  #   And User enters 'Content Overlap' as '20'
  #   When User clicks on Create Vector Button
  #   And User get the CatalogName for variable
  #   And User opens Main Menu
  #   And User clicks on Open Vector
  #   Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
  #   When User opens Main Menu
  #   And User clicks on Open App Library
  #   And User searches 'Test app' app in the app searchbox
  #   And User clicks on 'Test app' app from the My Apps
  #   And User clicks on app Edit button
  #   And User clicks on Variable
  #   And User clicks on Add Variable button
  #   And User enters variable name as 'Vector'
  #   And User selects variable type as 'Vector'
  #   And User enters variable value
  #   And User clicks on Create Variable button
  #   Then User sees Toast message of variable creation 'FAISSCatalogeeVectorr'
  #   And User sees the variable with name 'FAISSCatalogeeVectorr' and type 'Vector' in the variable list

  @LoginWithAdmin @DeleteTestCatalog
  Scenario: Verify Local File System Storage Variable using Drag and Drop App.
    Given User is on Home page
    When User opens Main Menu
    When User clicks on Open Storage
    And User clicks on Add Storage button
    And User selects 'Local File System' storage
    And User enters storage Catalog name as 'Local File System Storage'
    And User enters Path Prefix as 'local_storage'
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User clicks On Copy Catalog ID
    And User get the CatalogName for variable
    And User can see the Storage title as 'Local File System Storage'
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'Storage'
    And User selects variable type as 'storage'
    And User enters variable value
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation 'Storage'
    And User sees the variable with name 'Storage' and type 'storage' in the variable list

  @LoginWithAdmin @DeleteTestCatalog
  Scenario Outline: Verify String, date, number Variable for Drag and Drop App.
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as '<variable_name>'
    And User selects variable type as '<variable_type>'
    And User enters variable value as '<variable_value>'
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation '<variable_name>'
    And User sees the variable with name '<variable_name>' and type '<variable_type>' in the variable list

    Examples:
      | variable_name  | variable_type | variable_value |
      | StringVariable | string        | Sample String  |
      | DateVariable   | date          |     2023-01-01 |
      | NumberVariable | number        |            123 |

  @LoginWithAdmin @DeleteTestCatalog
  Scenario Outline: Verify array and JSON Variable for Drag and Drop App.
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as '<variable_name>'
    And User selects variable type as '<variable_type>'
    And User enter variable value as '<variable_value>'
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation '<variable_name>'
    And User sees the variable with name '<variable_name>' and type '<variable_type>' in the variable list

    Examples:
      | variable_name  | variable_type | variable_value   |
      | StringVariable | array         | [2]              |
      | JsonVariable   | JSON          | {"key": "value"} |
