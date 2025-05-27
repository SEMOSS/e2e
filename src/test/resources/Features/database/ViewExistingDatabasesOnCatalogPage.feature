Feature: View existing databases on database Catalog page

  @LoginWithAdmin
  Scenario: Create Function using ZIP file
    Given User navigates to Open Database
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    Then User sees the database name 'TestDatabase' in the database catalog
    When User clicks on the database name 'TestDatabase' in the database catalog
    And User clicks on Edit button
    And User add tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'IP, PHI' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'

  @LoginWithAdmin
  Scenario: view and validate filter functionality - My Functions
    Given User navigates to Open Database
    Then User sees the database name 'TestDatabase' in the database catalog
    When User clicks on Copy ID option of database
    Then User can see a copy success toast message as 'Succesfully copied to clipboard'
    And User applies each filter and validate 'TestDatabase' database is visible on the page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |
    When User clicks on bookmark button of database
    Then User sees the database name 'TestDatabase' in the Bookmarked section
    When User clicks on the database name 'TestDatabase' in the database catalog
