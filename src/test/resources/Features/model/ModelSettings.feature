Feature: Model settings
  I want to use this feature file for all the scenarios related to Model settings

  Background: Create a model
    Given User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    Then User can see a toast message as 'Successfully added LLM to catalog'

  Scenario: Validate Model Settings page
    When User clicks on Open Settings
    Then User can see 'Make Public' section
    And User can see text message in 'Make Public' section as 'Show Model to all users and automatically give them read-only access. Users can request elevated access.'
    And User can see toggle button in 'Make Public' section
    And User can see 'Make Discoverable' section
    And User can see text message in 'Make Discoverable' section as 'Allow users that do not currently have access to the Model to discover the Model, view Model details, and request access.'
    And User can see toggle button in 'Make Discoverable' section
    And User can see 'Delete' section
    And User can see text message in 'Delete' section as 'Delete Model from catalog.'
    And User can see Delete button in 'Delete' section
    And User can see 'Pending Requests' section
    And User can see 'Pending Requests' section contains 'pending request' or 'pending requests' text with count
    And User can see 'Members' section
    And User can see 'Search Members' textbox
    And User can see 'Add Members' button
    And User can see 'Rows per page' selection dropdown
    And User can see 'Rows per page' dropdown has options "5, 10, 20"
