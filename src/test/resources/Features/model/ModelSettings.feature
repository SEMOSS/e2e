@Regression
Feature: Model settings
  I want to use this feature file for all the scenarios related to Model settings

  Background: Create a model
  Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    Then User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID

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
    #And User can see 'Rows per page' dropdown has options "5, 10, 20"
