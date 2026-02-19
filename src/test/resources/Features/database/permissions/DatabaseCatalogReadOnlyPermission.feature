@LoginWithAuthor @Regression @DeleteTestCatalog
Feature: Database Catalog permissions for Read user
  Adding LLm to the Catlog

  Background: Create DataBase Using Zip File
    Given User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully Created Database'
    And User can see the Catalog title as 'TestDatabase'
    Then 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User logs out from the application
    And User login as 'Read'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    
  Scenario: Database Catalog - Read - View overview,Usage,Metadat
    Given User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'View' Overview
    And 'Read' user can 'View' Usage
    And 'Read' user can 'View' Metadata
    And User logs out from the application
    And User login as 'Author'

  Scenario: Database Catalog - Read - Not View SMSS Details,Access Control
    Given User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'Not View' SMSS Details
    And 'Read' user can 'Not View' Access Control
    And User logs out from the application
    And User login as 'Author'

  Scenario: Database Catalog - Read - Not View Edit SMSS
    Given User selects the 'TestDatabase' from the database catalog
    And 'Read' user can 'Not View' SMSS Details
    Then 'Read' user can 'Not View' Edit SMSS
    And User logs out from the application
    And User login as 'Author'

  Scenario: Database Catalog - Read - Member setting
    Given User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'Not View' Access Control
    Then 'Read' user 'can not' see Member Setting
    And User logs out from the application
    And User login as 'Author'

  Scenario: Database Catalog - Read -  Delete Database
    Given User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'Not View' Access Control
    And User logs out from the application
    And User login as 'Author'
