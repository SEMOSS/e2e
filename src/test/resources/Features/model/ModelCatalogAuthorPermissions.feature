@LoginWithAuthor @Regression
Feature: Model catalog permission - Author
  Adding LLM to the catalog

  Background: Create a Model - GPT-3.5
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog Name as 'Model'
    And User enters Open AI Key as 'Test@1234'
    And User enters Variable Name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks on Copy Catalog ID
    Then User can see the Model title as 'Model'

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - View overview
    Then 'Author' user can 'View' Overview

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - View usage
    Then 'Author' user can 'View' Usage

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - SMSS Details
    Then 'Author' user can 'View' SMSS Details

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - Edit SMSS
    And User clicks on SMSS
    Then 'Author' user can 'View' Edit SMSS

  @DeleteTestCatalog
  Scenario: Model Catalog - Author -  Access Control
    Then 'Author' user can 'View' Access Control

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - Member setting
    And 'Author' user clicks on Settings
    Then 'Author' user 'can' see Member Setting

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - Add Editor Member
    And 'Author' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - Add Read Member
    And 'Author' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - Delete editor Member
    And 'Author' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user

  @DeleteTestCatalog
  Scenario: Model Catalog - Author - Delete read Member
    And 'Author' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  Scenario: Model Catalog - Author - Delete Model
    And 'Author' user clicks on Settings
    Then 'Author' user 'can' Delete Catalog
