Feature: Playground Home Chat

  Background: Validate Playground Home page chat - check prompt response
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User clicks on file upload icon
    And User uploads the file 'Model/Llama3-70B-Instruct.zip'
    And User clicks on 'Upload' button to create catalog
    When User clicks on Edit button
    And User add Tags 'text-generation' and presses Enter
    And User clicks on Submit button
    And User clicks on Copy Catalog ID
    And User is on Home page
    And User clicks on Build button
    And User clicks on Try it out hyperlink for Experiment in our Playground

  @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp.
  Scenario: Validate Playground chat validatinos - add/remove histroy prompt
    Given User sees the Prompt textarea with placeholder 'What do you want to do today?'
    And User verifies that the 'Prompt the Model' button is 'disabled'
    When User enters prompt in the Prompt textarea 'tell me a joke'
    Then User verifies that the 'Prompt the Model' button is 'enabled'
    When User clicks on the "Prompt the Model" button
    And User waits for the response from the model
    Then User verifies that the response from the model is displayed as Prompt
    When User clicks on sidbar toggle button
    Then User verifies that "tell me a joke" prompt is present in the sidebar history
    When User hovers over the sidebar history item with prompt "tell me a joke"
    And User clicks on the delete icon for the sidebar history item with prompt "tell me a joke"
    Then User verifies that "tell me a joke" prompt is no longer present in the sidebar history
