#Feature: Row to Notebook Template
#
#  @LoginWithAdmin @DeleteCreatedTestApp @Regression
#  Scenario: Create app using Row to Notebook Template
#    Given User created '5' models with the 'OpenAI' model 'GPT 3.5 Turbo', catalog name 'Model 1', OpenAI key 'Test123'
#    When User is on Home page
#    And User opens Main Menu
#    And User clicks on Open App Library
#    And User clicks on Create New App button
#    And User selects "Row To Notebook" from Template List
#    And User enters app name as 'Test app'
#    And User enters description as 'Created by automation script'
#    And User enters tags 'Test1, Test2' and presses Enter
#    And User clicks on Create button
#    Then User sees the title as 'Interact with Row Data'
#    And User sees the description as 'This app is meant to show you how to interact with row data via the iterator and the notebook.'
#    And User clicks on Notebook
#    And User clicks on Query name as 'data'
#    And User clicks on Notebook
#    And User click on Run All cell button
#    And User clicks on 'page-1' page
#    And User checks 'Show Row Data' button is enabled
#    And User checks 'Delete Row' button is enabled
#    And User clicks on Preview app button
#    And User checks the created models are visible in the list
#    And User clicks on Close Preview button
#    And User Delete the created Model
