Feature: Add Database Using ZIP
    
 @LoginWithAdmin   
Scenario: Create Function using ZIP file
    Given User navigates to Open Database  
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees the database name 'TestDatabase' in the database catalog