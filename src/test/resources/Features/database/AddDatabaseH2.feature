Feature: Add H2 Database

  Background: Create H2 database
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    And User selects database 'H2' from connection types
    And User enters 'H2' as Catalog Name
    And User Upload 'localhost' as Host Name
    And User clear the Port Number
    And User Upload 'PUBLIC' as Schema Name
    And User add 'sa' as Username
    And User add 'H2' as JDBC URL for 'h2' database
    And User click on Connect button
    And User clicks on Apply button
    And User clicks on Import database button
    And User clicks on Copy Catalog ID
    Then User can see the database title as 'H2'

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Verify H2 database
    Given User opens Main Menu
    And User clicks on Open Database
    And User searches the 'H2' in the database Catalog searchbox
    And User clicks on the database name 'H2' in  database catalog
