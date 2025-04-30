Feature: Model Catalog Permission - Editor
  Adding LLM to the catalog
   
  Background: Model Catalog - Editor - View overview
    When User navigates to Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User Can see the Model title as 'Catalog'
    Then 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as "Editor"
    And User logs out from the application
    Then User login as "editor"
    Given User Can see the Model title as 'Catalog'  
    Then 'Editor' user can 'View' Overview       
  
  Scenario: Model Catalog - Editor - View usage
    Then 'Editor' user can 'View' Usage
    And User logs out from the application
    Then User login as "author"    
     
  Scenario: Model Catalog - Editor - SMSS Details
    Then 'Editor' user can 'Not View' SMSS Details 
    And User logs out from the application
    Then User login as "author"    
    
  Scenario: Model Catalog - Editor - Edit SMSS
    And 'Editor' user can 'Not View' SMSS Details
    Then 'Editor' user can 'Not View' Edit SMSS
    And User logs out from the application
    Then User login as "author"      
  
  Scenario: Model Catalog - Editor -  Access Control
    Then 'Editor' user can 'View' Access Control 
    And User logs out from the application
    Then User login as "author"    
    
  Scenario: Model Catalog - Editor - Member setting
    And 'Editor' user clicks on Access Control
    Then 'Editor' user 'can' see member setting
    And User logs out from the application
    Then User login as "author"    

  #Scenario: Model Catalog - Editor -  Delete Model
    #Then 'Editor' user 'can not' Delete Model
    #And User logs out from the application
    #Then User login as "author"    
   
  Scenario: Model Catalog - Editor - Add Read
  And 'Editor' user clicks on Access Control
   And User clicks on Add Member button
   And User adds one user and assigns them as "Read"
   And User logs out from the application
    Then User login as "author"    
   
  Scenario: Model Catalog - Editor - Delete Read Member
   And 'Editor' user clicks on Access Control
   And User clicks on Add Member button
   And User adds one user and assigns them as "Read"
   And User deletes the 'Read' user
   And User logs out from the application
    Then User login as "author"    
   
  Scenario: Model Catalog - Editor -  Delete Model as Author
   And User logs out from the application
   Then User login as "Author"
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' Delete Model
    
   
 