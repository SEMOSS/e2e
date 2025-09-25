Feature: Database Catalog permissions for Editor user
  Adding LLm to the Catlog
@LoginWithAuthor
  Scenario: Create DataBase Using Zip File
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    And User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees success toast message 'ZIP uploaded successfully'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks On Copy Catalog ID
    Then 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog

  Scenario: Database Catalog - Editor - View Overview,Usage,Metadat,Access Control
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Editor' user can 'View' Overview
    And 'Editor' user can 'View' Usage
    And 'Editor' user can 'View' Metadata
    And 'Editor' user can 'View' Access Control

  Scenario: Database Catalog - Editor - Not View SMSS Deatils,Edit SMSS
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Editor' user can 'Not View' SMSS Details
    And 'Editor' user can 'Not View' Edit SMSS

  Scenario: Database Catalog - Editor - Add and Delete read Member
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    And 'Editor' user clicks on Access Control
    Then User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteTestCatalog
  Scenario: Database Catalog - Editor - Delete Model
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    And 'Editor' user clicks on Access Control
    Then 'Editor' user clicks on delete button and see the permission error toast message
    And User opens Main Menu
    And User clicks on Open Database
    Then User searches the 'TestDatabase' in the database Catalog searchbox
    And User sees the database name 'TestDatabase' in the database catalog
    And User logs out from the application
    And User login as 'Author'
