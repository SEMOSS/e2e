Feature: Create Database to BI System app
  I want to use this feature file for all the scenarios related to Create Database

  Background: Login to the application
    Given User is on application
    When User enters username and password and click on SignIn button
    Then User can navigate to home page
    
  Scenario: Create database by importing data from CSV file
    When User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on Catalog option
    And User clicks on Add Database button
    And User enter the database name as 'DB created from CSV'
    And User upload CSV file and clicks on Next button
    And User clicks on Import button
    Then User can see database created success toast message as 'Success'
    
    Scenario: Create database by importing data from Excel file
    When User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on Catalog option
    And User clicks on Add Database button
    And User select the Excel option
    And User enter the database name as 'DB created from Excel'
    And User upload Excel file and clicks on Next button
    And User clicks on Import button
    Then User can see database created success toast message as 'Success'