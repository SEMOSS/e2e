@LoginWithAuthor @DeleteCreatedTestApp
Feature: App setting for Read permission

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User click on Settings
    And User Click on Members setting option
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User logs out from the application
    Then User login as 'Read'
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps

  Scenario: Create APP - Read Only user - Edit button is disable
    And 'Read' user Edit option should be 'Disable'
    And User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    Then 'Read' user can 'not view' Settings
    And 'Read' user can 'not view' Members
    And 'Read' user can 'not view' General
    And 'Read' user can 'not view' Apps
    And 'Read' user can 'not see' private toggle button
    And 'Read' user can 'not see' Non-Discoverable toggle button
    And 'Read' user can 'not view' Delete catalog option
    And User opens Main Menu
    And User logs out from the application
    And User login as 'Author'
