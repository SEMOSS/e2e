Feature: Add SQLITE Database

  Background: Create Sqlite database
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    And User selects database 'SQLITE' from connection types
    And User enters 'SqliteDB' as Catalog Name
    And User Upload 'sqlite.db' as Host Name
    And User clicks on Create Database button
    And User clicks on apply button
    And User clicks on apply database button
    Then User can see the database title as 'SqliteDB'

  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: Verify Sqlite database
    Given User opens Main Menu
    And User clicks on Open Database
    And User searches the 'SqliteDB' in the database Catalog searchbox
    And User sees the database name 'SqliteDB' in database catalog
    And User clicks on the database name 'SqliteDB' in  database catalog
