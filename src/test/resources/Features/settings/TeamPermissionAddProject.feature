Feature: Add Project for Team Permission

  Background: Team Permissions - Add team
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User clicks on 'Add Team' button
    And User selects type as 'Custom' from Type dropdown
    And User fills 'Test Team' in Name field of Add Team form
    And User fills Description as 'Test Description' in Description field of Add Team form
    And User clicks on 'Add' button in Add Team form

  @LoginWithAdmin @DeleteCreatedTestApp
  Scenario Outline: Add Project for different Users role
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters 'Test for add project' as app name
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as 'Test Team' in the list
    And User clicks on the team name 'Test Team' in the list
    When User clicks on 'Add Apps' button in Team Permission page
    And User select the 'Test for add project' in the 'Select App' field of Add App form
    And User select the engine access as '<Role>'
    And User clicks on save button
    Then User sees the message 'Successfully added app permission' is displayed
    And User see the added 'Test for add project' in the engine list with access as '<Role>'

    Examples: 
      | Role      |
      | Author    |
      | Editor    |
      | Read-Only |
