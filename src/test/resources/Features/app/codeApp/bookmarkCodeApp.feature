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

  Scenario: Bookmark the code app and app display in bookmark section
    Given User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Code app' app in the app searchbox
    When User clicks on the Bookmark icon for 'Code app' App
    Then User see the Bookmarked section
    And The app should appear in the bookmarked section

  Scenario: Bookmark an app and verify the toast message and its presence in the Bookmarks section on the App Home page
    Given The Files section should be open by default
    And User click on 'Code app' from breadcrumb link
    When User click on the Bookmark App icon
    Then User sees success toast message 'Project bookmarked'

  Scenario: Unbookmark the app and see the Bookmarks section is display on the App Home page
    Given User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Code app' app in the app searchbox
    And User can see 'Code app' app on the page
    When User clicks on the Bookmark icon for 'Code app' App
    And User clicks on the Unbookmark icon for 'Code app' App
    Then The 'Code app' should be removed from the bookmarked section
    And If no apps remain bookmarked the "Bookmarked" section should not be visible
