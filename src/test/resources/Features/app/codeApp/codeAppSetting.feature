@Regression @LoginWithAdmin
Feature: Code App - Setting page

  Background: Create the code app
    Given User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Develop in code"
    And User enters app name as 'Code app'
    And User enters description as 'Created by automation script'
    And User enters tags 'MCP' and presses Enter
    And User clicks on Create button
    And User fetch the app name

  @DeleteCreatedTestApp
  Scenario: Setting page - View Title, Member, Apps, General option
    Given User click on Settings
    Then 'Admin' user can 'view' Settings
    And User see the 'Settings' as title of the 'Settings' option
    And 'Admin' user can 'view' Members
    And 'Admin' user can 'view' Apps
    And 'Admin' user can 'view' General

  @DeleteCreatedTestApp
  Scenario: Setting page - validate the Member option for code app
    Given User click on Settings
    When User clicks on 'Members' option under Settings
    Then User can see the 'Members' page open on right side panel
    And 'Admin' user can 'view' Export Icon
    And User can see the 'Pending Requests' section on setting page
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteCreatedTestApp
  Scenario: Setting page - validate the Apps option for code app
    Given User click on Settings
    When User clicks on 'Apps' option under Settings
    Then User can see the 'Apps' page open on right side panel
    And 'Admin' user can 'view' Export Icon
    And User can see the 'Portals' section on setting page
    And User can 'enable' the portal
    And User click on Publish Portal button
    And User sees success toast message 'Successfully published'
    And User can see the 'Reactors' section on setting page
    And User click on 'Compile Changes on This Instance' button on setting page
    And User sees success toast message 'Successfully recompiled'
    And User click on 'Deploy and Persist Changes' button on setting page
    And User sees success toast message 'Successfully redeployed'
    And User can see the 'Update Project' section on setting page
    And User uploads the file 'dummy-pdf.pdf'
    And User click on 'Update' button on setting page

  Scenario: Setting page - validate the General option for code app
    Given User click on Settings
    When User clicks on 'General' option under Settings
    Then User can see the 'General' page open on right side panel
    And User can see the 'Private' section on General setting page
    And User turn OFF the Private option
    #And 'Admin' user can see toaster message is 'Successfully made .* global'
    And User turn ON the Private option
    #And 'Admin' user can see toaster message is 'Successfully made .* non-global'
    And User can see the 'Non Discoverable' section on General setting page
    And User turn OFF the Non Discoverable option
    #And 'Admin' user can see toaster message is 'Successfully made .* discoverable'
    And User turn ON the Non Discoverable option
    #And 'Admin' user can see toaster message is 'Successfully made .* undiscoverable'
    And User can see the 'Delete Project' section on General setting page
    And 'Admin' user 'can' Delete Catalog
