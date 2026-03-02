@LoginWithAuthor @Regression
Feature: Database Catalog permissions for Author
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
         
  @DeleteTestCatalog
  Scenario: Database Catalog - Author - View Overview,Metadata,Usage,Access Control,SMSS deatils
    Then 'Author' user can 'View' Overview
    And 'Author' user can 'View' Metadata
    And 'Author' user can 'View' Usage
    And 'Author' user can 'View' Access Control
    And 'Author' user can 'View' SMSS Details

  @DeleteTestCatalog
  Scenario: Database Catalog - Author - View Edit SMSS
    And User clicks on SMSS
    Then 'Author' user can 'View' Edit SMSS

  @DeleteTestCatalog
  Scenario: Database Catalog - Author - View Member setting
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' see Member Setting

  @DeleteTestCatalog
  Scenario: Database Catalog - Author - Add and Delete editor Member
    And 'Author' user clicks on Access Control
    Then User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user

  @DeleteTestCatalog
  Scenario: Database Catalog - Author - Add and Delete read Member
    And 'Author' user clicks on Access Control
    Then User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteTestCatalog
  Scenario: Database Catalog-Autor-View Export button
    Then 'Author' user can 'view' export button

  Scenario: Database Catalog - Author - Delete Database
    And 'Author' user clicks on Access Control
    Then 'Author' user 'can' Delete Catalog 
