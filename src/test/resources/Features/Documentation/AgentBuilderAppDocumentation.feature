@SkipIfVersionMatch @Documentation @DeleteTestCatalog
Feature: Create Agent builder app documentation

  Background: Create a Model - GPT-3.5
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog Name as 'Model'
    And User enters Open AI Key as 'Test@1234'
    And User enters Variable Name as 'Variable1'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    And User can see a toast message as 'Successfully added LLM to catalog'
    Then User can see the Model title as 'Model'

  Scenario: Agent builder app Setting Prompt screenshot
    Given User captures documentation screenshot for 'RAG'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Construct an agent"
    And User enters name as 'Demo app1'
    And User selects LLM as 'Model'
    And User fills the prompt as 'Prompt'
    And User clicks on Next button
    And User clicks on 'Prompt'
    And User captures screenshot for "SettingPrompt"
