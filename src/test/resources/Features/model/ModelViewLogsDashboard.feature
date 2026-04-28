@LoginWithAdmin @Regression @DeleteTestCatalog
Feature: Model view logs dashboard Functionality

  Background: Create a Model -Llama_model
    Given User opens Main Menu
    And User clicks on Open Model
    And User checks if 'Model' catalog created and Deletes the 'Llama3-70B-Instruct'
    When User clicks on Add Model
    And User clicks on file upload icon
    And User uploads the file 'Model/Llama3-70B-Instruct.zip'
    And User clicks on 'Upload' button to create catalog
    And User get the CatalogName for variable
    And User clicks on Copy Catalog ID
    When User clicks on Chat button
    And User should see the send button get active on entering text 'Tell me a joke' in the input textbox
    When User click on send button to submit the query
    Then User should see the loader indicating that the response is being generated for the query
    And User should see the response generated for the query in the chat window

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate the Model Insight Dashboard UI
    Given User opens Main Menu
    When User clicks on Open Model
    And User navigates to the Model Insight Dashboard
    Then User should see the heading 'Model Insight Dashboard'
    And User should see the 'Refresh' button
    And User should see the 'Event History' section
    And User should see the 'Prompt & Response Timeline' section
    And User should see the table with columns 'User Id', 'Session Id', 'Request', 'Response', 'Engine Type', 'Engine Name', 'Latency', 'Tokens', 'Timestamp', and 'Status'
