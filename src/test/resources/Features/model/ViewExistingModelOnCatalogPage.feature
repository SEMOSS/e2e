Feature: View existing models in model Catalog

  Background: Create a Model - GPT 4.1
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT-4.1'
    And User enters Catalog Name as 'Model'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    Then User can see the Model title as 'Model'

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: view and validate filter functionality - My Functions
    When User clicks on Edit button
    And User add Tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'IP, PHI' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'
    When User opens Main Menu
    And User clicks on Open Model
    And User searches the 'Model' in the model catalog searchbox
    Then User should see the 'Model' on the model catalog page
    And User applies each filter and validate 'Model' catalog is visible on the 'model' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |
    When User clicks on bookmark button of 'Model' catalog
    Then User sees the catalog name 'Model' in the Bookmarked section
    When User clicks on bookmark button to unbookmark 'Model' catalog

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate access status of created model
    When User opens Main Menu
    And User clicks on Open Model
    And User searches the 'Model' in the model catalog searchbox
    Then User should see the 'Model' on the model catalog page
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Private' on the tooltip
    When User selects the 'Model' from the model catalog
    And User clicks on Access Control Tab
    And User clicks on make 'Model' public button
    And User opens Main Menu
    And User clicks on Open Model
    And User searches the 'Model' in the model catalog searchbox
    Then User should see the 'Model' on the model catalog page
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Global' on the tooltip
