Feature: Playground Home knowledge, Max Token, Temperature and Instructions to verify configuration tab

  Background: Create and edit Vector
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters Catalog Name as 'ModelCatalog'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    And User clicks on Edit button
    And User add Tags 'embeddings' and presses Enter
    And User clicks on Submit button
    When User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Add Vector button
    And User add "2" vectors with details "FAISS" "FAISSVector" "MCP" "ModelCatalog"
    And User clicks on Copy Catalog ID

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate Playground Configuration tab for knowledge - add/search/delete knowledge , Max token, Temperature and Instructions section
    Given User is on Home page
    When User clicks on Build button
    And User clicks on Try it out hyperlink for Experiment in our Playground
    And User clicks on the "Open Configuration Menu" button
    Then User Verify the Instructions section is displayed
    And User Verify the Max Token section is displayed with default value & stepper control
    And User Verify the Temperature section is displayed with default value '.30'
    When User clicks on the Knowledge dropdown
    Then User should see and select the 'FAISSVector11' in the Knowledge available tools
    And User verify "FAISSVector11" knowledge should be checked in the knowledge catalog dropdown
    When User saves the added Knowledge list
    Then User verify the added 'FAISSVector11' is displayed in Knowledge section
    When User deletes the added 'FAISSVector11' from Knowledge section
    Then User verify the 'FAISSVector11' is removed from Knowledge section
    When User clicks on the Knowledge dropdown
    And User searches the 'FAISSVector11' configuration tab in the Knowledge catalog searchbox
