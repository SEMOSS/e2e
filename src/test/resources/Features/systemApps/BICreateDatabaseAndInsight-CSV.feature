Feature: Create Database and Insight to BI System app- CSV
  I want to use this feature file for all the scenarios related to Create Database and Insight via CSV

  Background: Create database by importing data from CSV file
    Given User can navigate to home page
    And User navigates to Open App Library
    When User clicks on Create New App button
    And User clicks on Get Started button in Drag and Drop
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User navigate to Home page
    And User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on Catalog option
    And User clicks on Add Database button
    And User enter the database name as 'DB created from CSV'
    And User uploads CSV file 
    And User clicks on Next button
    And User clicks on Import button
    Then User can see database created success toast message as 'Success'

  Scenario: Create an Insight from a database created by importing a CSV file
    Given User can see database created success toast message as 'Success'
    And User clicks on Add option
    And User search 'DB created from CSV' database and select
    And User clicks on Add All button from Available Columns section
    And User clicks on Import button from Selected Columns section
    And User mouse hover on database frame and click on Visualize this data option
    And User clicks on Save button
    And User enters 'Test Automation' as the insight name, selects the 'Test App' project, and clicks the Save button
   # Then User can see Insight created toast message as 'Successfully saved insight(s)'
