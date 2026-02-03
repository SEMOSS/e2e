@DeleteCreatedTestApp
Feature: Verify Notebook Operations - creation, deletion, searching and duplication

  Background: Create Drag and Drop App
    Given User is on Home page
    When User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as "Test app"
    And User clicks on Create button
    And User fetch the app name 

  @LoginWithAdmin @Regression
  Scenario: Verify creation and duplication of new Notebook
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches "Test app" app in the app searchbox
    And User clicks on "Test app" app from the My Apps
    And User clicks on app Edit button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as "Test query"
    And User clicks on query Submit button
    Then User see "Test query" notebook present in the notebook list
    When User duplicates the notebook named "Test query"
    Then User see "Test query (1)" notebook present in the notebook list

  @LoginWithAdmin @Regression
  Scenario: Verify deletion of new Notebook
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches "Test app" app in the app searchbox
    And User clicks on "Test app" app from the My Apps
    And User clicks on app Edit button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as "Test query"
    And User clicks on query Submit button
    Then User see "Test query" notebook present in the notebook list
    And User deletes the notebook named "Test query"

  @LoginWithAdmin @Regression
  Scenario: Verify Search functionality of new Notebook
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches "Test app" app in the app searchbox
    And User clicks on "Test app" app from the My Apps
    And User clicks on app Edit button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as "Test query"
    And User clicks on query Submit button
    Then User see "Test query" notebook present in the notebook list
    When User duplicates the notebook named "Test query"
    Then User see "Test query (1)" notebook present in the notebook list
    When User searches for notebook named "Test query (1)"
    Then User see "Test query (1)" notebook present in the notebook list
