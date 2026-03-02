Feature: Add Model Chat Functionality

  Background: Create a Model - GPT 3.5 Turbo
    Given User opens Main Menu
    And User clicks on Open Model
    When User clicks on Add Model
    And User clicks on file upload icon
    And User uploads the file 'Model/Llama_model.zip'
    And User clicks on 'Upload' button to create catalog
    And User get the CatalogName for variable
    And User clicks on Copy Catalog ID

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate Chat section of Model
    When User clicks on Chat button
    Then User should see the Chat section for Model with title 'Chat with the Model'
    And User should see the Model ID and Model Name displayed in Model information section
    And User should see the Temperature value displayed as '0.1' in Model information section by default
    And User should see the Max Tokens value displayed as '2000' in Model information section by default
    And User should see the input textbox with placeholder as 'Ask a question...'
    And User should see the send button get active on entering text 'Tell me a joke' in the input textbox
    When User click on send button to submit the query
    Then User should see the loader indicating that the response is being generated for the query
    And User should see the response generated for the query in the chat window
    When User click on clear all button
    Then User should see the chat window is cleared of previous conversation
