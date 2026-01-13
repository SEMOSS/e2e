@DeleteCreatedTestApp @Regression
Feature: Code app files

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
    And User fetch the app name for drag and drop app

    Scenario: Bookmark the code app and veriify the toast message
      Given The Files section should be open by default
      When User click on the Bookmark App icon
      Then User sees success toast message 'Project bookmarked'
