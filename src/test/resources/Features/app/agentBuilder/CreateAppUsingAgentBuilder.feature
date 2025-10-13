@DeleteTestCatalog @DeleteCreatedTestApp @Regression
Feature: Create Agent builder app

  Background: Create a Model - GPT-3.5
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Model'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    Then User Can see the Model title as 'Model'
    And User clicks On Copy Catalog ID

  Scenario: Create Agent builder app and navigate to Blocks option
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Construct an agent"
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
    And User fetch the app name for agent builder app

  Scenario: Create Agent builder app using global prompt and navigate to Blocks option
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Construct an agent"
    And User enters name as 'Demo app'
    And User selects LLM as 'Model'
    And User fills the prompt as 'I am planning a 90 day project for a client in Federal Health. The project involves software development, team training, and stakeholder presentations. Can you outline a project plan for me?'
    And User clicks on Next button
    And User selects '90 day' to set input in prompt
    And User selects 'Federal Health.' to set input in prompt
    And User selects 'software development,' to set input in prompt
    And User selects 'team training,' to set input in prompt
    And User selects 'stakeholder presentations.' to set input in prompt
    And User clicks on Next button
    And User selects InputType as 'User Text' for '90 day'
    And User selects InputType as 'User Text' for 'Federal Health.'
    And User selects InputType as 'User Text' for 'software development,'
    And User selects InputType as 'User Text' for 'team training,'
    And User selects InputType as 'User Text' for 'stakeholder presentations.'
    And User clicks on Next button
    And User clicks on Preview button
    And User clicks on Create App button
    And User fetch the app name for agent builder app
