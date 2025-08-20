@DeleteCreatedTestApp
Feature: Create App setting for Editor permission
  Adding LLm to the Catlog

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User click on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps

  @LoginWithAdmin
  Scenario: Create App - Editor user - View Member, Pending Request, Data Apps
    Then 'Editor' user Edit option should be 'Enable'
    And User clicks on app Edit button
    And User click on Settings
    And 'Editor' user can 'view' Settings
    And 'Editor' user can 'view' Member
    And 'Editor' user can 'view' Pending Requests
    And 'Editor' user can 'view' Data Apps
    And User opens Main Menu
    And User logs out from the application
    And User login as 'Admin'

  Scenario: Create App - Editor user - Not View Export Icon, Make public, Make Discoverable and Delete toggle button
    And User clicks on app Edit button
    And User click on Settings
    And 'Editor' user Make Public toggle should be 'Disable'
    And 'Editor' user Make Discoverable toggle should be 'Disable'
    And 'Editor' user can 'not view' Delete Model option
    #And 'Editor' user can 'view' Export Icon
    And User opens Main Menu
    And User logs out from the application
    And User login as 'Admin'

  Scenario: Create APP - Editor user - View Member setting and add and delete the Editor and Read User
    Given User clicks on app Edit button
    And User click on Settings
    Then 'Editor' user 'can' see Member Setting
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user
    And User opens Main Menu
    And User logs out from the application
    And User login as 'Admin'

  Scenario: Create App - Editor user - Delete Model
    And User clicks on app Edit button
    And User click on Settings
    Then 'Editor' user can 'not view' Delete Model option
