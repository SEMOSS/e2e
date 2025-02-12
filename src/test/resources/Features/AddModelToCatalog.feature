
Feature: Add Model to catalog
Adding LLM to the catlog

  Background: Login to the application
    Given User is on application
    When User enters username and password and click on SignIn button
    Then User can navigate to home page
    

  Scenario: Add Model to catalog - GPT-3.5
    Given User navigated to Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5' 
    And User enters Catlog name as 'Catlog'   
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see toast message as 'Successfully added LLM to catalog'  
    Then User Can see the Model title as 'Catlog'
    And User clicks on SMSS 
    And User can see NAME as 'Catlog' in SMSS properties
    And User can see VAR_NAME as 'Variable1' in SMSS properties
    
