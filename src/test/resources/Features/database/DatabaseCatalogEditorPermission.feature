Feature: Database Catalog permissions for Editor user
  Adding LLm to the Catlog

	Background: Create Database using Zip and Add the Editor User
 		Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User opens Main Menu
  	And User clicks on Open Database
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog and Copy ID
    Then 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
   	Then User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog

  @DeleteCreatedDatabaseCatalog
  Scenario: Editor User - View Overview, Usage, Metadata, Access Control
    Then 'Editor' user can 'View' Overview
    Then 'Editor' user can 'View' Usage
    Then 'Editor' user can 'View' Metadata
    Then 'Editor' user can 'View' Access Control
    And User logs out from the application
    And User login as 'Author'
        
  @DeleteCreatedDatabaseCatalog  
  Scenario: Editor User - Not View SMSS Deatils,Edit SMSS
   	Then 'Editor' user can 'Not View' SMSS Details
    Then 'Editor' user can 'Not View' Edit SMSS
    And User logs out from the application
    And User login as 'Author'
    
 @DeleteCreatedDatabaseCatalog 
  Scenario: Database Catalog - Editor - Add and Delete read Member
    And 'Editor' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
   	And User deletes the 'Read' user
   	And User logs out from the application
    And User login as 'Author'

#doubt
  Scenario: Database Catalog - Editor - Delete Model
    And 'Editor' user clicks on Access Control
    Then 'Editor' user 'can' Delete Model
