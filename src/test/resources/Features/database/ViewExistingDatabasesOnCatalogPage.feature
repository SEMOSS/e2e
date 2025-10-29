Feature: View existing databases on database catalog page

  Background: Create and edit database
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User clicks On Copy Catalog ID
    And User sees success toast message 'ZIP uploaded successfully'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on Edit button
    And User add tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'IP, PHI' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: view and validate filter functionality - My Functions
    Given User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    Then User sees the database name 'TestDatabase' in the database catalog
    When User clicks on Copy ID option of 'TestDatabase' database
    Then User can see a copy success toast message as 'Successfully copied to clipboard'
    And User applies each filter and validate 'TestDatabase' catalog is visible on the 'database' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |
    #When User clicks on bookmark button of 'TestDatabase' catalog
    #Then User sees the catalog name 'TestDatabase' in the Bookmarked section
    #When User clicks on bookmark button to unbookmark 'TestDatabase' catalog
