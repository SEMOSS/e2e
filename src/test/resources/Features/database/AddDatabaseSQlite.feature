@Regression 
Feature: Add SQLITE Database

  Background: Create Sqlite database
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    And User selects database 'SQLITE' from connection types
    And User enters 'SqliteDB' as Catalog Name
    And User Upload 'localhost' as Host Name
    And User add 'sqlite.db' as JDBC URL for 'sqlite' database
    And User clicks on Create Database button
    And User clicks On Copy Catalog ID
    And User clicks on apply button
    And User clicks on apply database button
    Then User can see the database title as 'SqliteDB'

@LoginWithAdmin @DeleteTestCatalog @Smoke
  Scenario: Verify Sqlite database
    Given User opens Main Menu
    When User clicks on Open Database
    And User searches the 'SqliteDB' in the database Catalog searchbox
    Then User sees the database name 'SqliteDB' in database catalog
    And User clicks on the database name 'SqliteDB' in  database catalog
