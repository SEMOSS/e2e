Feature: Database Catalog permissions for Editor user
  Adding LLm to the Catlog

  Scenario: Create DataBase Using Zip File
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

  Scenario: Database Catalog - Editor - View Overview,Usage,Metadat,Access Control
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Editor' user can 'View' Overview
    Then 'Editor' user can 'View' Usage
    Then 'Editor' user can 'View' Metadata
    Then 'Editor' user can 'View' Access Control

  Scenario: Database Catalog - Editor - Not View SMSS Deatils,Edit SMSS
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Editor' user can 'Not View' SMSS Details
    Then 'Editor' user can 'Not View' Edit SMSS

  Scenario: Database Catalog - Editor - Add and Delete read Member
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    And 'Editor' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  Scenario: Database Catalog - Editor - Delete Model
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    And 'Editor' user clicks on Access Control
    Then 'Editor' user 'can' Delete Model
