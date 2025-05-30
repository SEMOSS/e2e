Feature: Add Database Using ZIP

  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: Create Database using ZIP file
    Given User navigates to Open Database
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees the database name 'TestDatabase' in the database catalog
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog
