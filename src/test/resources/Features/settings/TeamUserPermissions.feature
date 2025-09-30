@LoginWithAdmin
Feature: Team Permissions - add User

  Background: Team Permissions - Add team
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Member Settings' Card
    And User sees the Add User button
    And User adds 1 members with name "userId", userId "userId123", password "Test@123", and email domain "testautomation.com" and can see toast message as 'Successfully added user' for all added members
    And User opens Main Menu
    And User clicks on Open Settings
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
    When User selects "userId1" member from the list
    Then User sees "userId1231" card in the Add Member form
    And User clicks on "Save" button in Add Member form
    And User sees the message "Successfully added member permissions" is displayed
    And User can see the new member "userId1" added in the team member list
    And User opens Main Menu
    And User clicks on Open Settings
    And User enables admin mode
    And User clicks on 'Member Settings' Card
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times

  Scenario: Delete a single member from the team
    371 defect are resolved we change steps
    Given User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as 'Test Team' in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Member' button in Add Team Page
    When User selects "userId1" member from the list
    And User clicks on 'Save' button in Add Member form
    Then User sees the message "Successfully added member permissions" is displayed
    And User can see the new member "userId1" added in the team member list
    When User clicks on 'Delete' icon on the 'userId1' card
    And User clicks on 'Confirm' button in the confirmation modal
    Then User sees the message 'Successfully removed user' is displayed
    And User should not see the "userId1231" card in the team member list
    And User opens Main Menu
    And User clicks on Open Settings
    And User enables admin mode
    And User clicks on 'Member Settings' Card
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times

  Scenario: Delete multiple members from the Add team member
    371 defect are resolved we change steps
    Given User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Member Settings' Card
    And User sees the Add User button
    And User adds 1 members with name "TestSecond", userId "TestSecond2", password "Test@12345", and email domain "testautomation.com" and can see toast message as 'Successfully added user' for all added members
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as 'Test Team' in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Member' button in Add Team Page
    When User selects multiple members "userId1" and "TestSecond1" from the team list
    And User clicks on 'Save' button in Add Member form
    And User click on checkbox to select all member
    And User Click on 'Delete Selected' Option
    And User clicks on 'Confirm' button in the confirmation modal
    Then User sees the message 'Successfully removed users' is displayed
    And User opens Main Menu
    And User clicks on Open Settings
    And User enables admin mode
    And User clicks on 'Member Settings' Card
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times

  Scenario: Search for a member by name and delete selected user from add member
    371 defect are resolved we change steps
    Given User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Member Settings' Card
    And User sees the Add User button
    And User adds 1 members with name "secondId", userId "seconduser", password "Test@123465", and email domain "testautomation.com" and can see toast message as 'Successfully added user' for all added members
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as 'Test Team' in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Member' button in Add Team Page
    When User selects multiple members "userId1" and "secondId1" from the team list
    And User clicks on 'Save' button in Add Member form
    And User Search the member name as 'Id'
    And User click on checkbox to select all member
    And User Click on 'Delete Selected' Option
    And User clicks on 'Confirm' button in the confirmation modal
    Then User sees the message 'Successfully removed users' is displayed
    And User opens Main Menu
    And User clicks on Open Settings
    And User enables admin mode
    And User clicks on 'Member Settings' Card
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times
