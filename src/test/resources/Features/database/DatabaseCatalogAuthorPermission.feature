Feature: Database Catalog permissions for Author
  Adding LLm to the Catlog

  Background: Create DataBase Using Zip File
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    And User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User opens Main Menu
  	And User clicks on Open Database
    Then User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog and Copy ID

  @DeleteCreatedDatabaseCatalog
  Scenario: Database Catalog - Author - View Overview,Metadata,Usage,Access Control,SMSS deatils
    Then 'Author' user can 'View' Overview
    Then 'Author' user can 'View' Metadata
    Then 'Author' user can 'View' Usage
    Then 'Author' user can 'View' Access Control
    Then 'Author' user can 'View' SMSS Details

  @DeleteCreatedDatabaseCatalog
  Scenario: Database Catalog - Author - View Edit SMSS
    And 'Author' user clicks on Access Control
    And User clicks on SMSS
    Then 'Author' user can 'View' Edit SMSS

  @DeleteCreatedDatabaseCatalog
  Scenario: Database Catalog - Author - View Member setting
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' see Member Setting

 @DeleteCreatedDatabaseCatalog
  Scenario: Database Catalog - Author - Add and Delete editor Member
    And 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
   And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user

  @DeleteCreatedDatabaseCatalog
  Scenario: Database Catalog - Author - Add and Delete read Member
    And 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteCreatedDatabaseCatalog
  Scenario: Database Catalog-Autor-View Export button
    Then 'Author' user can 'view' export button

  Scenario: Database Catalog - Author - Delete Model
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' Delete Model
