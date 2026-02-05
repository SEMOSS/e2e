Feature: Create App and validate Variables and take screenshot for documentation

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Validate Block Variables in Drag and Drop App with screenshot for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name 
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the 'Input' block and drops it on the page
    And User clicks on the Save App icon
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'prompt'
    And User selects variable type as 'block'
    And User enters variable value as 'input--1'
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation 'prompt'
    And User clicks on Add Variable button
    And User enters variable name as 'demo variable'
    And User clicks on variable type
    And User captures screenshot for app screens "VAR1"
    And User completes screenshot capture and triggers comparison for 'App variable'
