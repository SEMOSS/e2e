Feature: Database Catalog permissions for Read user
  Adding LLm to the Catlog

  Scenario: Create DataBase Using Zip File
    Given User opens Main Menu
  	 When User clicks on Open Database
    And User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User opens Main Menu
  	And User clicks on Open Database
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog
    Then 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User logs out from the application
    Then User login as 'Read'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog

  Scenario: Database Catalog - Read - View overview,Usage,Metadat
  #	And User opens Main Menu
    #And User clicks on Open Database
    #And User searches the 'TestDatabase' in the database Catalog searchbox
    #And User selects the 'TestDatabase' from the database catalog
    Then 'Read' user can 'View' Overview
    Then 'Read' user can 'View' Usage
    Then 'Read' user can 'View' Metadata

  #Scenario: Database Catalog - Read - Not View SMSS Details,Access Control
    #And User clicks on Open Database
    #And User searches the 'TestDatabase' in the database Catalog searchbox
    #And User selects the 'TestDatabase' from the database catalog
    #Then 'Read' user can 'Not View' SMSS Details
    #Then 'Read' user can 'Not View' Access Control
#
  #Scenario: Database Catalog - Read - Not View Edit SMSS
    #And User clicks on Open Database
    #And User searches the 'TestDatabase' in the database Catalog searchbox
    #And User selects the 'TestDatabase' from the database catalog
    #And 'Read' user can 'Not View' SMSS Details
    #Then 'Read' user can 'Not View' Edit SMSS
#
  #Scenario: Database Catalog - Read - Member setting
    #And User clicks on Open Database
    #And User searches the 'TestDatabase' in the database Catalog searchbox
    #And User selects the 'TestDatabase' from the database catalog
    #When 'Read' user can 'Not View' Access Control
    #Then 'Read' user 'can not' see Member Setting
#
  #Scenario: Database Catalog - Read -  Delete Model
    #And User clicks on Open Database
    #And User searches the 'TestDatabase' in the database Catalog searchbox
    #And User selects the 'TestDatabase' from the database catalog
    #When 'Read' user can 'Not View' Access Control
