Feature: App Library Documentation

  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
  Scenario: Create Drag and Drop app
    Given User captures documentation screenshot for 'PlatformNavigation/App Library'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Apps'
    And User clicks on Open App Library
    And User captures a 'button' and highlights the 'Discoverable'
    And User captures a 'button' and highlights the 'Create New App'
    And User captures a 'SearchBar' and highlights the 'Search'
    And User clicks on Create New App button
    And User captures a 'Heading' and highlights the 'Start build with a template'
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Travel Itinerary'
    And User enters description as 'Created by automation script'
    And User enters tags 'travel planner, itinerary creator' and presses Enter
    And User captures screenshot for "New App Popup"
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User captures a 'button' and highlights the 'Preview App'
    And User captures a 'button' and highlights the 'Save App'
    And User captures a 'button' and highlights the 'Share App'
    And User clicks on Share App button
    And User captures a 'button' and highlights the 'Copy'
    And User clicks on IFrame button
    And User captures a 'copycta' and highlights the 'CopyURL'
    And User Clicks on close button
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Travel Itinerary' app in the app searchbox
    Then User can see 'Travel Itinerary' app on the page
    And User clicks on app 'View Details' button
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User captures a 'button' and highlights the 'Access Control'
    And User clicks on Access Control button
    And User captures a 'Heading' and highlights the 'Pending Requests'
    And User clicks on 'Edit' button
    And User captures screenshot for form "App Edit Options"
    And User clicks on 'Cancel' button
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures a 'button' and highlights the 'Upload App'
    And User clicks on Upload button
    And User captures screenshot for "Uploading App"
    And User is on Home page
    Then User opens Main Menu
    And User captures a 'button' and highlights the 'Settings'
    And User clicks on Open Settings
    And User captures a 'button' and highlights the 'App Settings'
    And User clicks on 'App Settings' Card
    When User enables admin mode
    And User captures screenshot for "App Settings Page"
    Then User can selects 'Travel Itinerary' on the page
    And User captures a 'button' and highlights the 'Delete'
    And User completes screenshot capture and triggers comparison for 'Settings Overview'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
  Scenario: Documentation for Visualize CSV template
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures a 'button' and highlights the "Visualize CSV" with name "AskCSV1"
    And User selects "Visualize CSV" from Template List
    And User enters app name as 'Test app'
    And User captures a 'buttonType' and highlights the "submit" with name "AskCSV2"
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User captures a 'Block' and highlights the "upload" with name "AskCSV3"
    And User completes screenshot capture and triggers comparison for 'Visualize CSV Overview'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation @DeleteTestCatalog
  Scenario: Documentation for Ask LLM template
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open Model
    When User clicks on Add Model
    And User clicks on file upload icon
    And User uploads the file 'Model/Llama_model.zip'
    And User clicks on 'Upload' button to create catalog
    And User get the CatalogName for variable
    And User clicks on Copy Catalog ID
    When User opens Main Menu
    And User clicks on Open App Library
    And User captures a 'button' and highlights the "Create New App" with name "LandingPage1"
    And User clicks on Create New App button
    And User captures a "appTypeTile , useTemplateButton" and highlights the "Ask LLM , Ask LLM" with name "askLLMclick"
    And User selects "Ask LLM" from Template List
    And User captures screenshot for "AskLLMcreation"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on description block
    And User clicks on the Block Settings option
    And User adds description as "When did covid start"
    And User captures screenshot for "llmone"
    And User clicks on the Submit Block
    Then User captures screenshot for "llmtwo"
    And User clicks on Variable
    And User clicks on "model" open menu
    And User clicks on edit variable option
    And User enters variable value
    And User clicks on Save variable button
    And User clicks on Notebook
    And User clicks on Query name as 'ask-llm'
    And User clicks on Notebook
    And User click on Run All cell button
    And User clicks on 'page-1' page
    And User clicks on the Response Block
    And User captures screenshot for "llmthree"
    And User completes screenshot capture and triggers comparison for 'Ask LLM'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Documentation for App Homepage
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    Then User captures a 'button' and highlights the "Create New App" with name "AppHomepage"
    And User completes screenshot capture and triggers comparison for 'App Homepage'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Documentation for Landing Page template
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures a "appTypeTile , useTemplateButton" and highlights the "Landing Page , Landing Page" with name "landingpagecreation"
    And User selects "Landing Page" from Template List
    And User enters app name as 'Demo landing page'
    Then User captures a "buttontype" and highlights the "submit" with name "landingone"
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User completes screenshot capture and triggers comparison for 'Landing Page Template'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Documentation for Variable Guide Template Creation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    Then User captures a "appTypeTile , useTemplateButton" and highlights the "Variables Guide , Variables Guide" with name "varGuideCreation"
    And User completes screenshot capture and triggers comparison for 'Variables Guide Template Creation'

    @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
    Scenario: Documentation for Create New App - Home Page 
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    Then User captures a "button" and highlights the "Create New App" with name "AppHomepage"
    And User completes screenshot capture and triggers comparison for 'AppHomePage'
   

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Documentation for Create App Page
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures screenshot for "AB14"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Documentation for Create App Page
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Construct an agent"
    And User captures screenshot for "AB15"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Documentation for ASK LLM Template - model screenshot
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Ask LLM" from Template List
    And User enters app name as 'Test app'
    When User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Variable
    Then User captures a "testidelement , blocksettingelement" and highlights the "workspace-Variables , model" with name "modelchange"
    And User completes screenshot capture and triggers comparison for 'Variables Guide Template Creation'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Documentation for Create New App - Home Page
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    Then User captures a "button" and highlights the "Create New App" with name "AppHomepage"
    And User completes screenshot capture and triggers comparison for 'AppHomePage'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation @DeleteCreatedTestApp
  Scenario: Documentation for Create new App - NB5 screenshot
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'diabetes'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/diabetes.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'diabetes'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Block Settings option
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'adding_numbs'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'From Data Catalog' from the data import options
    And User selects 'diabetes' from the dropdown list
    When User selects all columns from database
    And User clicks on data Import button
    And User deletes the previous cell
    And User selects type as 'Python'
    And User enter the data limit as '20'
    And User clicks on Run cell button
    And User captures screenshot for "NB5"
    And User completes screenshot capture and triggers comparison for "CreateNewAppNB5"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp @DeleteTestCatalog
  Scenario: Documentation for App Library - Create new App - NB6 screenshot
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User is on Home page
    And User opens Main Menu
    And User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'diabetes'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/diabetes.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'diabetes'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Block Settings option
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'adding_numbs'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User enters code as "from ai_server import ModelEngine\nmodel = ModelEngine(engine_id =\n\"4acbe913-df40-4ac0-b23a-da5ad91b172\")\n\n# Generation\n5 question = \"{prompt}\"\n6 output = model.ask(question = question, param_dict="
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'From Data Catalog' from the data import options
    And User selects 'diabetes' from the dropdown list
    When User selects all columns from database
    And User clicks on data Import button
    And User selects type as 'Python'
    And User enter the data limit as '20'
    And User clicks on Run cell button
    And User mouse hover above the existing cell
    And User selects 'Transformation' from the hidden options
    And User captures screenshot for "NB6"
    And User completes screenshot capture and triggers comparison for "CreateNewAppNB6"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp @DeleteTestCatalog
  Scenario: Documentation for App Library - Create new App - NB7 screenshot
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User is on Home page
    And User opens Main Menu
    And User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'diabetes'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/diabetes.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'diabetes'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Block Settings option
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'prompt_output'
    And User clicks on query Submit button
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'From Data Catalog' from the data import options
    And User selects 'diabetes' from the dropdown list
    When User selects all columns from database
    And User clicks on data Import button
    And User selects type as 'Python'
    And User enter the data limit as '20'
    And User clicks on Run cell button
    And User mouse hover on the blank cell
    And User selects 'Transformation' from the hidden options
    And User selects 'Uppercase' from the Transformation options
    And User deletes the previous cell
    And User captures screenshot for "NB7"
    And User completes screenshot capture and triggers comparison for "CreateNewAppNB7"

