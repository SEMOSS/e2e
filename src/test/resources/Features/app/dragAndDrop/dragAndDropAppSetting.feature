@LoginWithAdmin @Regression
Feature: Drag and Drop App setting Page

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User opens Main Menu
    And User is on Home page
    And User clicks on Open App Library
    When User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Block Settings option

  @DeleteCreatedTestApp
  Scenario: Setting page - View Title, Member, Apps, General option
    Given User click on Settings
    Then 'Admin' user can 'view' Settings
    And User see the 'Settings' as title of the 'Settings' option
    And 'Admin' user can 'view' Members
    And 'Admin' user can 'view' Apps
    And 'Admin' user can 'view' General

  @DeleteCreatedTestApp
  Scenario: Setting page - validate the Member option for drag and drop app
    Given User click on Settings
    When User clicks on 'Members' option under Settings
    Then User see the 'Members' page open on right side panel
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
  Scenario: Setting page - validate the Apps option for drag and drop app
    Given User click on Settings
    When User clicks on 'Apps' option under Settings
    Then User see the 'Apps' page open on right side panel
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

  Scenario: Setting page - validate the General option for drag and drop app
    Given User click on Settings
    When User clicks on 'General' option under Settings
    Then User see the 'General' page open on right side panel
    And User can see the 'Private' section on General setting page
    And User turn OFF the Private option
    And 'Admin' user can see toaster message is 'Successfully made Test app .* global'
    And User turn ON the Private option
    And 'Admin' user can see toaster message is 'Successfully made Test app .* non-global'
    And User can see the 'Non Discoverable' section on General setting page
    And User turn OFF the Non Discoverable option
    And 'Author' user can see toaster message is 'Successfully made Test app .* discoverable'
    And User turn ON the Non Discoverable option
    And 'Author' user can see toaster message is 'Successfully made Test app .* undiscoverable'
    And User can see the 'Delete Project' section on General setting page
    And 'Author' user 'can' Delete Catalog
