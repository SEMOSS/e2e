@LoginWithAdmin @DeleteTestCatalog @Regression
Feature: Team Permissions

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
    Then User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    Then User can see team name as "Test Team" in the list
    Then User can see description as "Test Description" in the list

  Scenario: Database Catalog - Verify team is displayed in Catalog team section after adding engine to team
    Given User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully Created Database'
    And User can see the Catalog title as 'TestDatabase'
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    When User clicks on 'Add Engine' button in Team Permission page
    And User select the 'TestDatabase' in the 'Select Engine' field of Add Engine form the 'Database'
    And User select the engine access as 'Editor'
    And User clicks on save button
    And User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'TestDatabase' in the engine list with access as 'Editor'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog
    And 'Admin' user clicks on Access Control
    Then User sees the team 'Test Team' with 'Editor' in Team section on the the Access Settings page
		        
