@LoginWithAuthor @DeleteCreatedTestApp @Regression
Feature: App setting for Editor permission

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User click on Settings
    And User clicks on Access Control Tab
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps

  Scenario: Create App - Editor user - Not View Private, Non Discoverable and Delete toggle button
    When User clicks on app Edit button
    And User click on Settings
    And User clicks on Access Control Tab
    And 'Editor' user can see private toggle button as 'Disable'
    And 'Editor' user can see Non-Discoverable toggle button as 'Disable'
    And 'Editor' user can 'not view' Delete catalog option
    And User logs out from the application
    And User login as 'Author'

  Scenario: Create APP - Editor user - View Member setting and add and delete the Editor and Read User
    Given User clicks on app Edit button
    When User click on Settings
    And User clicks on Access Control Tab
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user
    And User logs out from the application
    And User login as 'Author'
    And User opens Main Menu
    And User clicks on Open App Library

  Scenario: Create App - Editor user - Delete Model
    When User clicks on app Edit button
    And User click on Settings
    And User clicks on Access Control Tab
    Then 'Editor' user can 'not view' Delete catalog option
    And User logs out from the application
    And User login as 'Author'
    And User opens Main Menu
