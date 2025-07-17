Feature: View existing models in model Catalog

  Background: Create a Model - GPT-3.5
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Model'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    Then User Can see the Model title as 'Model'
    And User clicks on Edit button
    And User add tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'IP, PHI' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'

  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: view and validate filter functionality - My Functions
    Given User opens Main Menu
    When User clicks on Open Model
    Then User should see the 'Model' on the model catalog page
    And User applies each filter and validate 'Model' catalog is visible on the 'model' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |
    When User searches the 'Model' in the model catalog searchbox
    And User selects the 'Model' from the model catalog
