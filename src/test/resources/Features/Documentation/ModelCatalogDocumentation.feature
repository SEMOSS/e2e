Feature: Model Documentation

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create a Model
    Given User captures documentation screenshot for 'Model Catalog'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Model'
    When User clicks on Open Model
    And User captures a 'button' and highlights the 'Add Model'
    When User clicks on Add Model
    And User captures screenshot for "Model Options"
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Model'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    Then User can see a toast message as 'Successfully added LLM to catalog'
    Then User can see the Model title as 'Model'
    And User clicks On Copy Catalog ID
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User captures a 'button' and highlights the 'Access Control'
    And User clicks on Access Control button
    And User captures a 'Heading' and highlights the 'Pending Requests'
    And User clicks on Usage tab
    And User captures a 'tab' and highlights the 'Usage'
    And User clicks on Usage tab
    And User captures a 'tab' and highlights the 'Usage'
    And User clicks on 'Edit' button
    And User captures screenshot for form "Edit Model Options"
    And User clicks on 'Close' button
    And User clicks on Access Control Tab
    And User clicks Make 'Model' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    When User clicks on Open Model
    And User clicks on Discoverable Models button
    And User captures a 'button' and highlights the 'Discoverable Models'
    And User searches the 'Model' in the model catalog searchbox
    And User selects the 'Model' from the model catalog
    And User can see the Model title as 'Model'
    And User captures a 'button' and highlights the 'Request Access'
    And User click on the Request Access button
    And User selects 'author' access
    And User captures screenshot for "Access Request"
    And User clicks on Request button
    And User logs out from the application
    And User login as 'admin'
    And User completes screenshot capture and triggers comparison for 'Model Catalog'

  @LoginWithAdmin @SkipIfVersionMatch
  Scenario: Create Simple One or More Model and capture the screenshot for the AI CORE platform
    Given User captures documentation screenshot for 'Model Catalog'
    And User created '2' models with the model 'GPT-3.5', catalog name 'Model 1', OpenAI key 'Test123', and variable name 'Var123'
    And User created '2' models with the model 'GPT-4', catalog name 'Model 2', OpenAI key 'Test123', and variable name 'Var123'
    When User opens Main Menu
    And User clicks on Open Model
    And User captures screenshot for "Model Catalog"
    And User click on Created Model
    And User click on 'Usage' tab
    And User captures a 'tab' and highlights the 'Usage'
    And User captures a 'copyid' and highlights the 'copy Model ID'
    And User Delete the created Model
    And User completes screenshot capture and triggers comparison for 'Model Catalog'
