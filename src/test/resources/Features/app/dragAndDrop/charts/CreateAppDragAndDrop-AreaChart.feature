@DeleteCreatedTestApp @Regression
Feature: Create App Drag and Drop - Area chart

  Background: Create Drag and Drop app and navigate to Blocks option
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test App'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name 
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Blocks if it is not selected by default

  Scenario: Drag and Drop - Area Chart - Duplicate Icon - Tooltip appears on hover over Duplicate and Delete icons
    Given User drags the 'Area Chart' block and drops it on the page
    And User clicks on the 'Area Chart' block to select it
    When User hovers over the Duplicate icon
    Then Tooltip with text "Duplicate" should appear
    And User clicks on the 'Area Chart' block to select it
    When User hovers over the Delete icon
    Then Tooltip with text "Delete" should display

  Scenario: Drag and Drop - Area Chart - Duplicate Icon
    Given User drags the 'Area Chart' block and drops it on the page
    And User clicks on the 'Area Chart' block to select it
    When User can "view" Duplicate icon on area chart
    And User click on 'Area Chart' chart duplicate icon
    Then Duplicate 'Area Chart' chart should appear on the page

  Scenario: Drag and Drop - Area Chart - Duplicate Icon-Duplicate the Area Chart multiple times
    Given User drags the 'Area Chart' block and drops it on the page
    And User clicks on the 'Area Chart' block to select it
    When User clicks on Duplicate Icon 3 times
    Then Total 4 Area Chart blocks should be present on the page

  Scenario: Drag and Drop - Area Chart - Delete Icon
    Given User drags the 'Area Chart' block and drops it on the page
    And User clicks on the 'Area Chart' block to select it
    When User can "view" delete icon on area chart
    And User click on 'Area Chart' chart delete icon
    And Duplicate 'Area Chart' chart should be remove from the page
