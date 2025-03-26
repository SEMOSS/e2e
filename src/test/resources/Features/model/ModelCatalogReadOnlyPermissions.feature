Feature: Model Catalog Permission - Read
  Adding LLM to the catalog
  
  Background: Model Catalog - Read - View overview
    When User navigates to Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User Can see the Model title as 'Catalog'
    Then 'Author' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as "Read"
    And User logs out from the application
    Then User login as "Read"
    Given User Can see the Model title as 'Catalog'  
    Then 'Read' user can 'View' Overview       
  
  Scenario: Model Catalog - Read - View usage
    Then 'Read' user can 'View' Usage    
     
  Scenario: Model Catalog - Read - SMSS Details
    Then 'Read' user can 'Not View' SMSS Details 
    
  Scenario: Model Catalog - Read - Edit SMSS
    And 'Read' user can 'Not View' SMSS Details 
    Then 'Read' user can 'Not View' Edit SMSS 
  
  Scenario: Model Catalog - Read -  Access Control
    Then 'Read' user can 'Not View' Access Control  
    
  Scenario: Model Catalog - Read - Member setting
   When 'Read' user can 'Not View' Access Control 
   Then 'Read' user 'can not' see member setting

  Scenario: Model Catalog - Read -  Delete Model
    When 'Read' user can 'Not View' Access Control
   
   #Scenario: Model Catalog - Read -  Delete Model as Author
   #And User logs out from the application
   #Then User login as "Author"
    #And 'Author' user clicks on Access Control
    #Then 'Author' user 'can' Delete Model

  
   
 