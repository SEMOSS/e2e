Feature: Add Model
  Adding LLM to the catalog

  Background: Create a Model - GPT-3.5
    Given User navigates to Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Model'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    Then User Can see the Model title as 'Model'

  Scenario: Validate SMSS properties of a Model to catalog - GPT-3.5
    Given User Can see the Model title as 'Model'
    When User clicks on SMSS
    And User can see name in 'NAME' field as 'Model' in SMSS properties
    And User can see var name in 'VAR_NAME' field as 'Variable1' in SMSS properties
    And User navigates to Open Model

  Scenario: Edit SMSS properties of Model - GPT-3.5
    Given User Can see the Model title as 'Model'
    When User clicks on SMSS
    And User clicks on Edit SMSS button
    And User can edit the value of 'KEEP_CONVERSATION_HISTORY' field as 'True'
    And User can edit the value of 'VAR_NAME' field as 'New_Name'
    And User clicks on Update SMSS button
    And User refresh the page
    And User can see updated value in 'KEEP_CONVERSATION_HISTORY' field as 'True'
    Then User can see updated value in 'VAR_NAME' field as 'New_Name'
    And User navigates to Open Model

  Scenario: Adding tag to Model to catalog - GPT-3.5 - embeddings
    Given User Can see the Model title as 'Model'
    When User clicks on Edit button
    And User enters tag as 'embeddings' in Edit Model Details and press enter
    And User clicks on Submit button
    Then User can see 'embeddings' tag added
    And User navigates to Open Model
