Feature: Playground Home model to verify configuration tab

  Background: Playground Configuration tab for Model - add model
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User add "2" models with details "OpenAI" "GPT 3.5 Turbo" "Model" "Test@1234" "text-generation"
    And User clicks on Copy Catalog ID
    
  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate Playground Configuration tab for Model - add/search Model
    Given User is on Home page
    When User clicks on Build button
    And User clicks on Try it out hyperlink for Experiment in our Playground
    And User clicks on the "Open settings" button
    Then User verify the model catalog dropdown is present with default model with 'Model' name
    When User clicks on the Model dropdown
    Then User verify "default" model should be checked in the model catalog dropdown
    When User clicks on the Model dropdown
    And User searches the 'Model2' configuration tab in the model catalog searchbox
    Then User should see the 'Model2' in the model catalog dropdown
    When User selects the 'Model2' from the model catalog dropdown
    And User clicks on the Model dropdown
    Then User verify "Model2" model should be checked in the model catalog dropdown
    
