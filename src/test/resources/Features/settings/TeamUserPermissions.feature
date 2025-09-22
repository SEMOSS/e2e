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
     Given User clicks on "Add Member" button in Add Team Page
     When User selects "editor" member from the list
     Then User sees "editor" card in the Add Member form
     And User clicks on "Save" button in Add Member form
     And User sees the message "Successfully added member permissions" is displayed
     And User can see the new member "editor" added in the team member list

    Scenario: Delete a single member from the team
    #When 371 defect are resolved we can remove the first six steps
     Given User opens Main Menu
     And User clicks on Open Settings
     And User enable admin mode
     And User clicks on 'Team Permissions' Card
     And User can see team name as 'Test Team' in the list
     And User clicks on the team name 'Test Team' in the list
     And User clicks on 'Add Member' button in Add Team Page
     When User selects 'editor new' member from the list
     And User clicks on 'Save' button in Add Member form
     Then User sees the message "Successfully added member permissions" is displayed
     And User can see the new member "EditorNew" added in the team member list
     When User clicks on 'Delete' icon on the 'editor' card
     And User clicks on 'Confirm' button in the confirmation modal
     Then User sees the message 'Successfully removed user' is displayed
     And User should not see the "EditorNew" card in the team member list

   
   Scenario: Delete multiple members from the team
    #When 371 defect are resolved we can remove the first six steps
     Given User opens Main Menu
     And User clicks on Open Settings
     And User enable admin mode
     And User clicks on 'Team Permissions' Card
     And User can see team name as 'Test Team' in the list
     And User clicks on the team name 'Test Team' in the list
     And User clicks on 'Add Member' button in Add Team Page
     When User selects multiple members "editor new" and "Read New" from the team list
     And User clicks on 'Save' button in Add Member form
     And User click on checkbox to select all member 
     And User Click on 'Delete Selected' Option
     And User clicks on 'Confirm' button in the confirmation modal
     Then User sees the message 'Successfully removed users' is displayed
    
    Scenario: Search for a member by name and delete
    #When 371 defect are resolved we can remove the first six steps
    Given User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as 'Test Team' in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Member' button in Add Team Page
    When User selects multiple members "editor new" and "Read New" from the team list
    And User clicks on 'Save' button in Add Member form
    And User Search the member name as 'editor'
    And User click on checkbox to select all member 
    And User Click on 'Delete Selected' Option
    And User clicks on 'Confirm' button in the confirmation modal
    Then User sees the message 'Successfully removed users' is displayed
   
    
    
    
   
   
