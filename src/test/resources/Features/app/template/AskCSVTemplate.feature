Feature: Create app using csv Template

  @LoginWithAdmin @Regression
  Scenario: Create app using Template Visualize CSV
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Ask CSV" from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User sees the title 'CSV Query'
    And User clicks on "title" block
    And User change title 'CSV Query' with 'CSV Query - Edited'
    And User clicks on "question" block
    And User adds description as 'What is the total sales?'
    And User sees description as 'Upload a csv file and ask a question'
    And User clicks on "description" block
    And User change title 'Upload a csv file and ask a question' with 'Upload a csv file and ask a question - Edited'
    And User sees submit button
    And User clicks on the Save App icon
    And User clicks on Preview app button
    Then User sees the title 'CSV Query - Edited' in Preview App
