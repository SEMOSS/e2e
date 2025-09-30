@Regression
Feature: Database Catalog permissions for Read user
  Adding LLm to the Catlog

@LoginWithAuthor
  Scenario: Create DataBase Using Zip File
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees success toast message 'ZIP uploaded successfully'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks On Copy Catalog ID
    Then 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User logs out from the application
    And User login as 'Read'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog

  Scenario: Database Catalog - Read - View overview,Usage,Metadat
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'View' Overview
    And 'Read' user can 'View' Usage
    And 'Read' user can 'View' Metadata

  Scenario: Database Catalog - Read - Not View SMSS Details,Access Control
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'Not View' SMSS Details
    And 'Read' user can 'Not View' Access Control

  Scenario: Database Catalog - Read - Not View Edit SMSS
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    And 'Read' user can 'Not View' SMSS Details
    Then 'Read' user can 'Not View' Edit SMSS

  Scenario: Database Catalog - Read - Member setting
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'Not View' Access Control
    Then 'Read' user 'can not' see Member Setting

   @DeleteTestCatalog
  Scenario: Database Catalog - Read -  Delete Model
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'Not View' Access Control
    And User logs out from the application
    And User login as 'Author'
