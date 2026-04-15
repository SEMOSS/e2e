@DeleteCreatedTestApp @Regression
Feature: Bookmark app - Code app files

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

  Scenario: Verify Bookmark and unbookmark the code app
    Given User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Code app' app in the app searchbox
    When User clicks on the Bookmark icon for 'Code app' App
    Then User sees success toast message 'App bookmarked'
    Then User clicks on the Bookmarked Apps tab
    And User can see 'Code app' in the Bookmarked Apps section
    When User clicks on the Unbookmark icon for 'Test app' App
    Then User sees success toast message 'App unbookmarked'
    Then User cannot see 'Test app' in the Bookmarked Apps section
    And User clicks on the My Apps tab
    
  Scenario: Bookmark an app and verify the toast message and its presence in the Bookmarks section on the App Home page
    Given The Files section should be open by default
    And User click on 'Code app' from breadcrumb link
    When User click on the Bookmark App icon
    Then User can see success toast message 'Project bookmarked'