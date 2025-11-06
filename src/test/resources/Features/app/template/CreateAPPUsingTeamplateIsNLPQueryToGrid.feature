Feature: Create app using NLP Query to Grid Template

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Create app using NLP Query to Grid Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects 'NLP Query To Grid' from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User see the 'page-1'
    And User see the 'Natural Language Query to Grid' block

    