Feature: Database Catlog permissions for Author
  Adding LLm to the Catlog

  Background: Create DataBase Using Zip File
    Given User clicks on Open Database
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog

  @DeleteCreatedCatalog
  Scenario: Database Catalog - Author - View Overview,Metadata,Usage,Access Control,SMSS deatils
    Then 'Author' user can 'View' Overview
    Then 'Author' user can 'View' Metadata
    Then 'Author' user can 'View' Usage
    Then 'Author' user can 'View' Access Control
    Then 'Author' user can 'View' SMSS Details

  @DeleteCreatedCatalog
  Scenario: Database Catalog - Author - View Edit SMSS
    And User clicks on SMSS
    Then 'Author' user can 'View' Edit SMSS

  @DeleteCreatedCatalog
  Scenario: Database Catalog - Author - View Member setting
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' see Member Setting

  @DeleteCreatedCatalog
  Scenario: Database Catalog - Author - Add and Delete editor Member
    And 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user

  @DeleteCreatedCatalog
  Scenario: Database Catalog - Author - Add and Delete read Member
    And 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteCreatedCatalog
  Scenario: Database Catalog-Autor-View Export button
    Then 'Author' user can 'view' export button

  Scenario: Database Catalog - Author - Delete Model
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' Delete Model
