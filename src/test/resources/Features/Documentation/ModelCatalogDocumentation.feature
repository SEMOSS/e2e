Feature: capture screenshot and highlight button

  @LoginWithAdmin @SkipIfVersionMatch
  Scenario: Create a Model
    Given User captures documentation screenshot for 'Model Catalog'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Model'
    When User clicks on Open Model
    And User captures a 'button' and highlights the 'Add Model'
    When User clicks on Add Model
    And User captures a 'list item' and highlights the 'GPT-3.5'
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Model'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    Then User can see a toast message as 'Successfully added LLM to catalog'
    Then User Can see the Model title as 'Model'
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User clicks on 'Edit' button
    And User captures screenshot for "View Tabs"
    And User enters and selects 'CONFIDENTIAL' under 'Data classification' section
    And User captures a 'button' and highlights the 'Submit'
    And User clicks on 'Submit' button
    And User clicks on Access Control Tab
    And User clicks Make 'Model' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu    
    When User clicks on Open Model
    And User clicks on Discoverable Models button
    And User searches the 'Model' in the model catalog searchbox
    And User selects the 'Model' from the model catalog
    And User Can see the Model title as 'Model'
    And User captures a 'button' and highlights the 'Request Access'
    And User click on the Request Access button
    And User selects 'author' access
    And User captures screenshot for "Access Request"
    And User clicks on Request button
    And User completes screenshot capture and triggers comparison for 'Model Catalog'
