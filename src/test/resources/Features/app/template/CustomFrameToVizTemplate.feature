Feature: Create Custome Frame to Visualization App using Template

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Custome Frame to Visualization App using Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects 'Custom Frame to Visualization' from Template List
    And User enters app name as "Custome Frame to Visualization App using Template"
    And User enters description as "Test to add description"
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User see the 'page-1'
    And User see the 'Create Pandas Frame Help Guide' block
    And User views description as 'This is simply an app that shows you how to create a custom pandas frame in notebook.  Use this as inspiration for the cool visualizations you can build off of this.  Ask the LLM to create JSON out of data, manually import database engine data and construct a custom pandas frame off of that data (use imagination on how to interact that pulled data with the LLM).'
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Area Chart' block and drops it on the 'Create Pandas Frame Help Guide'
    And User clicks on the Save App icon
