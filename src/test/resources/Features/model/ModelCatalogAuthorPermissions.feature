Feature: Model catalog permission - Author
Adding LLM to the catalog
 
Scenario: Create a Model - GPT-3.5
    Given User navigates to Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5' 
    And User enters Catalog name as 'Catalog'   
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'  
    Then User Can see the Model title as 'Catalog'
          
Scenario: Model Catalog - Author - View overview 
    Then 'Author' user can 'View' Overview   
  
Scenario: Model Catalog - Author - View usage
    Then 'Author' user can 'View' Usage
    
  Scenario: Model Catalog - Author - SMSS Details
    Then 'Author' user can 'View' SMSS Details
    
  Scenario: Model Catalog - Author - Edit SMSS
    And User clicks on SMSS 
    Then 'Author' user can 'View' Edit SMSS 
    
  Scenario: Model Catalog - Author -  Access Control
    Then 'Author' user can 'View' Access Control
    
 Scenario: Model Catalog - Author - Member setting
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' see member setting
    
 Scenario: Model Catalog - Author - Add Editor Member
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    
 Scenario: Model Catalog - Author - Add Read Member
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    
  Scenario: Model Catalog - Author - Delete editor Member
    And User deletes the 'Editor' user
    
  Scenario: Model Catalog - Author - Delete read Member
    And User deletes the 'Read' user
    
  Scenario: Model Catalog - Author - Delete Model
    Then 'Author' user 'can' Delete Model