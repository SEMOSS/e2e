@Regression
Feature: Model settings
  I want to use this feature file for all the scenarios related to Model settings

  Background: Create a model
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters Catalog Name as 'Catalog'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    #Then User can see a toast message as 'Successfully added LLM to catalog'
 
  @DeleteCreatedDatabaseCatalog
  Scenario: Validate Model Settings page
    When User clicks on Model Setting tab
    Then User can see 'Private' section
    And User can see text message in 'Private' section as 'No one outside of the specified member group can access'
    And User can see toggle button in 'Private' section
    And User can see 'Non Discoverable' section
    And User can see text message in 'Non Discoverable' section as 'Users cannot discover Model, view its details, or request access when it is non-discoverable.'
    And User can see toggle button in 'Non Discoverable' section
    And User can see 'Delete Model' section
    And User can see text message in 'Delete Model' section as 'Delete Model from catalog.'
    And User can see Delete button in 'Delete Model' section
    And User can see 'Pending Requests' section
    And User can see 'Pending Requests' section contains 'pending request' or 'pending requests' text with count
    And User can see 'Permissions' section
    And User can see 'Search Members' textbox
    And User can see 'Add Members' button
    And User can see 'Rows per page' selection dropdown
