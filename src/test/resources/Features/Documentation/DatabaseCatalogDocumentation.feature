Feature: Database Catalog Documentation

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Database Catalog Documentation
    Given User captures documentation screenshot for 'PlatformNavigation/Database Catalog'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Database'
    And User clicks on Open Database
    And User captures a 'button' and highlights the 'Add Database'
    And User clicks on Add Database
    And User captures screenshot for "Database Options"
    And User captures a 'List Item' and highlights the 'Postgres'
    Then User selects database 'Postgres'
    And User captures screenshot for "Postgres Database"
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    And User selects the 'ZIP' option to upload file
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees success toast message 'ZIP uploaded successfully'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on Copy Catalog ID
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User captures a 'button' and highlights the 'Access Control'
    And User clicks on Access Control button
    And User captures a 'Heading' and highlights the 'Pending Requests'
    And User clicks on Usage tab
    And User captures a 'tab' and highlights the 'Usage'
    And User clicks on 'Edit' button
    And User captures screenshot for form "Database Edit Options"
    And User clicks on 'Close' button
    And User clicks on Access Control Tab
    And User clicks Make 'Database' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Database
    And User captures a 'button' and highlights the 'Discoverable Databases'
    And User click on 'Discoverable Databases' tab
    And User clicks on the database name 'TestDatabase' in the database catalog
   	And User captures screenshot for "Database Resource"
    And User captures screenshot for "Request Access"
    And User click on the Request Access button
    And User selects 'author' access
    And User captures screenshot for "Access Request"
    And User clicks on Request button
    And User completes screenshot capture and triggers comparison for 'Database Catalog'
    And User logs out from the application
    Then User login as "admin"
    When User opens Main Menu
    And User clicks on Open Database
    And User completes screenshot capture and triggers comparison for 'Database Catalog'
    
  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Database Catalog Connection Documentation
  Given User captures documentation screenshot for 'RAG'
    When User opens Main Menu
    And User clicks on Open Database
    And User clicks on Add Database
    And User captures screenshot for form "SelectingDBConnection"
    And User completes screenshot capture and triggers comparison for 'Database Catalog'