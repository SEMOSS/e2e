@LoginWithAuthor
Feature: Model Catalog Permission - Read
  Adding LLM to the catalog

  Background: Model Catalog - Read - View overview
    Given User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    And User Can see the Model title as 'Catalog'
		Then 'Author' user clicks on Access Control
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User logs out from the application
    Then User login as 'Read'
    And User opens Main Menu
    And User clicks on Open Model
    And User searches the 'Catalog' in the model catalog searchbox
    And User selects the 'Catalog' from the model catalog
    Given User Can see the Model title as 'Catalog'
    Then 'Read' user can 'View' Overview

  @DeleteTestCatalog
  Scenario: Model Catalog - Read - View usage
    Then 'Read' user can 'View' Usage
     And User logs out from the application
    And User login as 'Author'

  @DeleteTestCatalog
  Scenario: Model Catalog - Read - SMSS Details
    Then 'Read' user can 'Not View' SMSS Details
     And User logs out from the application
    And User login as 'Author'

  @DeleteTestCatalog
  Scenario: Model Catalog - Read - Edit SMSS
    And 'Read' user can 'Not View' SMSS Details
    Then 'Read' user can 'Not View' Edit SMSS
     And User logs out from the application
    And User login as 'Author'

  @DeleteTestCatalog
  Scenario: Model Catalog - Read -  Access Control
    Then 'Read' user can 'Not View' Access Control
     And User logs out from the application
    And User login as 'Author'

  @DeleteTestCatalog
  Scenario: Model Catalog - Read - Member setting
    When 'Read' user can 'Not View' Access Control
    Then 'Read' user 'can not' see Member Setting
     And User logs out from the application
    And User login as 'Author'

  @DeleteTestCatalog
  Scenario: Model Catalog - Read -  Delete Model
    When 'Read' user can 'Not View' Access Control
    And User logs out from the application
    And User login as 'Author'
    
