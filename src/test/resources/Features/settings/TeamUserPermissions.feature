Feature: Team Permissions - add User

  Background: Team Permissions - Add team
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User clicks on "Add Team" button
    And User selects type as "Custom" from Type dropdown
    And User fills "Test Team" in Name field of Add Team form
    And User fills Description as "Test Description" in Description field of Add Team form
    And User clicks on "Add" button in Add Team form

  Scenario: User add different user to the team
    Given User clicks on the team name 'Test Team' in the list
    And User clicks on "Add Member" button in Add Team Page
    When User selects "Editor" member from the list
    Then User sees "Editor" card in the Add Member form
    And User clicks on "Save" button in Add Member form
    And User sees the message "Successfully added member permissions" is displayed
    And User can see the new member "editor" added in the team member list
