@LoginWithAuthor @Regression  @DeleteTestCatalog
Feature: Database Catalog permissions for Editor user
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
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
   

  Scenario: Database Catalog - Editor - View Overview,Usage,Metadat,Access Control
  Given User selects the 'TestDatabase' from the database catalog
    Then 'Editor' user can 'View' Overview
    And 'Editor' user can 'View' Usage
    And 'Editor' user can 'View' Metadata
    And 'Editor' user can 'not View' Access Control
   	And User logs out from the application
    And User login as 'Author'

  Scenario: Database Catalog - Editor - Not View SMSS Deatils,Edit SMSS
    Given User selects the 'TestDatabase' from the database catalog
    Then 'Editor' user can 'Not View' SMSS Details
    And 'Editor' user can 'Not View' Edit SMSS
    And User logs out from the application
    And User login as 'Author'

	##In latest UI change Editor User not able to see the Access Control Option we are commenting below Step
  #Scenario: Database Catalog - Editor - Add and Delete read Member
    #Given User selects the 'TestDatabase' from the database catalog
    #And 'Editor' user clicks on Access Control
    #Then User clicks on Add Member button
    #And User adds one user and assigns them as 'Read'
    #And User Search 'Read' user from Access Control
    #And User deletes the 'Read' user
    #And User logs out from the application
    #And User login as 'Author'
#
 #@DeleteTestCatalog
  #Scenario: Database Catalog - Editor - Delete Database
   #Given User selects the 'TestDatabase' from the database catalog
    #And 'Editor' user clicks on Access Control
    #Then 'Editor' user clicks on delete button and see the permission error toast message
    #And User opens Main Menu
    #And User clicks on Open Database
    #Then User searches the 'TestDatabase' in the database Catalog searchbox
    #And User sees the database name 'TestDatabase' in the database catalog
    #And User logs out from the application
    #And User login as 'Author'
