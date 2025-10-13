
@LoginWithAuthor @Regression
Feature: App setting for Author permission

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
    And User click on Settings

  @DeleteCreatedTestApp
  Scenario: Create App - Author user - View Member, Apps, General option
    Then 'Author' user can 'view' Settings
    And 'Author' user can 'view' Members
    And 'Author' user can 'view' Apps
    And 'Author' user can 'view' General

  @DeleteCreatedTestApp
  Scenario: Create APP - Author user - Click on Member setting and add and delete the Editor and Read User
    When User Click on Members setting option
    Then User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteCreatedTestApp
  Scenario: Create App - Author user - View and perform action on Private toggle button
    When User Click on General setting option
    Then 'Author' user can see private toggle button as 'Enable'
    And User turn OFF the Private option
    And 'Author' user can see toaster message is 'Successfully made Test app .* global'
    And User turn ON the Private option
    And 'Author' user can see toaster message is 'Successfully made Test app .* non-global'

  @DeleteCreatedTestApp
  Scenario: Create App - Author user - View and perform action on Non Discoverable toggle button
    When User Click on General setting option
    Then 'Author' user can see Non-Discoverable toggle button as 'Enable'
    And User turn OFF the Non Discoverable option
    And 'Author' user can see toaster message is 'Successfully made Test app .* discoverable'
    And User turn ON the Non Discoverable option
    And 'Author' user can see toaster message is 'Successfully made Test app .* undiscoverable'

  Scenario: Create App - Author user - Delete App
    When User Click on General setting option
    Then 'Author' user can 'view' Delete catalog option
    And 'Author' user 'can' Delete Catalog
