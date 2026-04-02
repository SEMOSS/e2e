Feature: App Templates
  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
  Scenario: Documentation for Visualize CSV template
    Given User captures documentation screenshot for 'Building Apps/App Templates'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures a 'button' and highlights the "Visualize CSV" with name "AskCSV1"
    And User selects "Visualize CSV" from Template List
    And User enters app name as 'Test app'
    And User captures a 'buttonType' and highlights the "submit" with name "AskCSV2"
    And User clicks on Create button
    And User fetch the app name
    And User captures a 'Block' and highlights the "upload" with name "AskCSV3"
    And User completes screenshot capture and triggers comparison for 'Visualize CSV Overview'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
  Scenario: Documentation for Ask CSV template
    Given User captures documentation screenshot for 'Building Apps/App Templates'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Ask CSV" from Template List
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on description block
    And User clicks on the Block Settings option
    And User adds description as "Give me list of people over the age of 50"
    Then User captures a "promptcontext" and highlights the "Value" with name "AskCSV4"
    And User completes screenshot capture and triggers comparison for 'Visualize CSV Overview'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
  Scenario: Documentation for Ask CSV template -onclick action
    Given User captures documentation screenshot for 'Building Apps/App Templates'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Ask CSV" from Template List
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on description block
    And User clicks on the Block Settings option
    And User adds description as "Give me list of people over the age of 50"
    And User clicks on the Submit Block
    And User clicks on "On Click" New action button 
    And User selects "Query" from the action options
    And User selects "ask-model" from the list of queries
    And User clicks on Save query button
    Then User captures a "blocksettingelement" and highlights the "Run Query" with name "AskCSV5"
    And User completes screenshot capture and triggers comparison for 'Visualize CSV Overview'
  

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Documentation for ASK LLM Template - model screenshot
    Given User captures documentation screenshot for 'Building Apps/App Templates'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Ask LLM" from Template List
    And User enters app name as 'Test app'
    When User clicks on Create button
    And User fetch the app name
    And User clicks on Variable
    Then User captures a "testidelement , blocksettingelement" and highlights the "workspace-Variables , model" with name "modelchange"
    And User completes screenshot capture and triggers comparison for 'Variables Guide Template Creation'


  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation @DeleteTestCatalog
  Scenario: Documentation for Ask LLM template
    Given User captures documentation screenshot for 'Building Apps/App Templates'
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
    And User fetch the app name
    And User clicks on description block
    And User clicks on the Block Settings option
    And User adds description as "When did covid start"
    And User captures screenshot for "llmone"
    And User clicks on the Submit Block
    Then User captures screenshot for "llmtwo"
    And User clicks on Variable
    And User clicks on "Model" open menu
    And User clicks on 'model' edit variable option
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
  Scenario: Documentation for Landing Page template
    Given User captures documentation screenshot for 'Building Apps/App Templates'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures a "appTypeTile , useTemplateButton" and highlights the "Landing Page , Landing Page" with name "landingpagecreation"
    And User selects "Landing Page" from Template List
    And User enters app name as 'Demo landing page'
    Then User captures a "buttontype" and highlights the "submit" with name "landingone"
    And User clicks on Create button
    And User fetch the app name
    And User completes screenshot capture and triggers comparison for 'Landing Page Template'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Documentation for Variable Guide Template Creation
    Given User captures documentation screenshot for 'Building Apps/App Templates'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    Then User captures a "appTypeTile , useTemplateButton" and highlights the "Variables Guide , Variables Guide" with name "varGuideCreation"
    And User completes screenshot capture and triggers comparison for 'Variables Guide Template Creation'
    
