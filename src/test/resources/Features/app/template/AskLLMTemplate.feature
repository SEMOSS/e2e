Feature: Ask LLM Template
 
@LoginWithAdmin @DeleteCreatedTestApp @Regression
Scenario: Create app using Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Ask LLM" from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name 
    Then User sees the title 'Ask LLM'
    And User sees description as 'Ask an LLM a question'
    And User sees input field with With label 'Question'
    And User sees submit button
    When User clicks on Preview app button
    Then User sees the title 'Ask LLM' in Preview App
    And User sees description as 'Ask an LLM a question' in Preview App
    And User sees input field with With label 'Question' in Preview App
    And User sees submit button in Preview App
    When User clicks on Close Preview button
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps