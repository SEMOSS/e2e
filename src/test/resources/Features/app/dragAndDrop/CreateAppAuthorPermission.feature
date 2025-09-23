@LoginWithAuthor
Feature: Create App setting for Author permission
  Adding LLm to the Catlog

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User opens Main Menu
    When User is on Home page
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User click on Settings
    
  @DeleteCreatedTestApp
  Scenario: Create App - Author user - View Member, Pending Request, Data Apps, Export option
    Then 'Author' user can 'view' Settings
    And 'Author' user can 'view' Member
   	And 'Author' user can 'view' Pending Requests
    And 'Author' user can 'view' Data Apps
    And 'Author' user can 'view' Export Icon

  @DeleteCreatedTestApp
  Scenario: Create App - Author user - View and perform action on Make public toggle button
    Then 'Author' user Private toggle should be 'Enable'
    And User turn OFF the Private option
    And 'Author' user can see toaster message is 'Successfully made Test app .* global'
    And User turn ON the Private option
    And 'Author' user can see toaster message is 'Successfully made Test app .* non-global'

  @DeleteCreatedTestApp
  Scenario: Create App - Author user - View and perform action on Make Discoverable toggle buttonUser turn OFF the Non Discoverable option
    Then 'Author' user Non-Discoverable toggle should be 'Enable'
    And User turn OFF the Non Discoverable option
    And 'Author' user can see toaster message is 'Successfully made Test app .* discoverable'
    And User turn ON the Non Discoverable option
    And 'Author' user can see toaster message is 'Successfully made Test app .* undiscoverable'

  @DeleteCreatedTestApp
  Scenario: Create APP - Author user - View Member setting and add and delete the Editor and Read User
    Then 'Author' user 'can' see Member Setting
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  Scenario: Create App - Author user - Delete App
    Then 'Author' user can 'view' Delete Model option
    And 'Author' user 'can' Delete Model
