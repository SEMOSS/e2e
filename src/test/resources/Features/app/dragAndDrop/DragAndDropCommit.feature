@LoginWithAdmin @Regression @DeleteCreatedTestApp
Feature: Drag and Drop App Setting - Commit tab

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User opens Main Menu
    And User is on Home page
    And User clicks on Open App Library
    When User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Block Settings option

  Scenario: Setting page - Commit Tab - validate commit tab functionality
    When User click on Settings
    Then User can sees the Commits tab
    And User clicks on the Commits tab
    And User can sees the 'Commit History' as title
    And User can sees the Commits section with message 'Initial creation of project'
    When User clicks on Blocks if it is not selected by default
    And User clicks on the 'page-1' for app
    And User drags the 'Area Chart' block and drops it on the page
    And User clicks on the Save App icon
    And User refresh the page
    And User clicks on the 'AppSettings' for app
    And User clicks on the Commits tab
    Then User can sees the Commits section with message 'Commited on'
