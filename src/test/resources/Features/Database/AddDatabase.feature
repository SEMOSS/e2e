Feature: Add Database Using ZIP
    
Background: Create Database using ZIP file
    Given User navigates to Open Database  
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees the database name 'TestDatabase' in the database catalog

@LoginWithAdmin
@DeleteCreatedCatalog
Scenario:Verify Database Usage Examples
 Given User navigates to Open Database
  And User sees the database name 'TestDatabase' in the database catalog  
  And User clicks the database name 'TestDatabase' in the database catalog  
  When User clicks on Usage tab for Database
  Then User sees an example of "How to use in Javascript" with example code for Database
  And User sees an example of "How to use in Python" with example code for Database
  And User sees an example of "How to use with Langchain API" with example code for Database
  And User sees an example of "How to use in Java" with example code for Database
  
