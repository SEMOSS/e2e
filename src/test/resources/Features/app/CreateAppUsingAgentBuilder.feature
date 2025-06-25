Feature: Create Agent builder app

  Background: Create a Model - GPT-3.5
    Given User clicks on Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Model'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    Then User Can see the Model title as 'Model'

  Scenario: Create Agent builder app and navigate to Blocks option
    Given User navigates to Home page
    When User clicks on Create New App button
    And User clicks on Get Started button in "Agent Builder"
    And User enters name as 'Demo app'
    And User selects LLM as 'Model'
    And User fills the prompt as 'Create Test cases for the Userstory'
    And User clicks on Next button
    And User selects 'Userstory' to set input
    And User clicks on Next button
    And User selects InputType as 'User Text'
    And User clicks on Next button
    And User clicks on Preview button
    And User clicks on Create App button
