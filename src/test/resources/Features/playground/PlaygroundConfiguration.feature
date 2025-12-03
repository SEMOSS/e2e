Feature: Playground Home model to verify configuration tab

  @LoginWithAdmin @Regression
  Scenario Outline: Validate Playground Configuration tab for Model - add model
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects '<MODEL_TYPE>' type
    And User selects '<MODEL_NAME>'
    And User enters Catalog Name as '<CATLOG_NAME>'
    And User enters Open AI Key as '<KEY>'
    And User clicks on Create Model button
    And User can see a toast message as '<TOAST_MESSAGE>'
    And User clicks on Edit button
    And User add tags '<TAGS>' and presses Enter
    And User clicks on Submit button
    And User clicks on Copy Catalog ID
    Then User can see the Model title as '<CATLOG_NAME>'
    Examples: 
      | MODEL_TYPE | MODEL_NAME    | CATLOG_NAME | KEY       | TOAST_MESSAGE                     | TAGS            |
      | OpenAI     | GPT 3.5 Turbo | Model1      | Test@1234 | Successfully added LLM to catalog | text-generation |
      | OpenAI     | GPT 3.5 Turbo | Model2      | Test@1234 | Successfully added LLM to catalog | text-generation |

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate Playground Configuration tab for Model - add/search Model
    Given User is on Home page
    When User clicks on Build button
    And User clicks on Try it out hyperlink for Experiment in our Playground
    And User clicks on the "Open Configuration Menu" button
    Then User verify the model catalog dropdown is present with default model with 'Model' name
    When User clicks on the Model dropdown
    Then User verify "default" model should be checked in the model catalog dropdown
    And User searches the 'Model2' configuration tab in the model catalog searchbox
    Then User should see the 'Model2' in the model catalog dropdown
    When User selects the 'Model2' from the model catalog dropdown
    And User clicks on the Model dropdown
    Then User verify "Model2" model should be checked in the model catalog dropdown
    
