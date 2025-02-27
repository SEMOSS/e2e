
Feature: Add Model to catalog
Adding LLM to the catalog

Background: Create a Model - GPT-3.5
  Given User navigated to Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5' 
    And User enters Catalog name as 'Catalog'   
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'  
    Then User Can see the Model title as 'Catalog'
 
  Scenario: Validate SMSS properties of a Model to catalog - GPT-3.5
    Given User Can see the Model title as 'Catalog'
    When User clicks on SMSS 
    And User can see name in 'NAME' field as 'Catalog' in SMSS properties
    And User can see var name in 'VAR_NAME' field as 'Variable1' in SMSS properties
    And User navigated to Open Model
         
  Scenario: Adding tag to Model to catalog - GPT-3.5 - embeddings
    Given User Can see the Model title as 'Catalog'  
    When User clicks on Edit button
    And User enters tag as 'embeddings' in Edit Model Details and press enter
    And User clicks on Submit button
    Then User can see 'embeddings' tag added
    And User navigated to Open Model
 
 
 