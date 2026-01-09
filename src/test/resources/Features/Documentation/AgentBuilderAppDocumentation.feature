@SkipIfVersionMatch @Documentation @DeleteTestCatalog
Feature: Create Agent builder app documentation

  Background: Create a Model - GPT 3.5 Turbo
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters Catalog Name as 'Model'
    And User enters Open AI Key as 'Test@1234'
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

  Scenario: Agent builder app Setting Prompt screenshot
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Construct an agent"
    And User enters name as 'new demo'
    And User selects LLM as 'Model'
    And User fills the prompt as 'Suppose you are a travel agent. The user will provide the destination number Of days of travel and their Travel place preference (slow or fast). Prepare an itinerary for them.'
    And User clicks on Next button
    And User selects 'destination' to set input in prompt
    And User selects 'number Of days' to set input in prompt
    And User selects 'Travel place' to set input in prompt
    And User captures screenshot for 'AB16'
    When User clicks on Next button
    And User selects InputType as 'User Text' for 'number Of days'
    And User selects InputType as 'User Text' for 'Travel place'
    And User selects InputType as 'User Text' for 'destination'
    And User captures screenshot for 'AB17'
    When User clicks on Next button
    And User captures screenshot for 'AB18'
    When User clicks on Preview button
    And User captures screenshot for 'AB19'
    And User clicks on Create App button
    And User captures screenshot for 'AB20'
