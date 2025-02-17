Feature: Create Insights to BI System app
  I want to use this feature file for all the scenarios related to Create Insights

  Background: Login to the application
    Given User is on application
    When User enters username and password and click on SignIn button
    Then User can navigate to home page
    
  Scenario: Create an Insight from a database created by importing a CSV file
    When User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on Catalog option
    And User clicks on Add Database button
    And User enter the database name as 'DB created from CSV'
    And User upload CSV file and clicks on Next button
    And User clicks on Import button
    Then User can see database created success toast message as 'Success'
    And User clicks on Add option
    And User search 'DB created from CSV' database and select
    And User clicks on Add All button from Available Columns section
    And User clicks on Import button from Selected Columns section
    And User mouse hover on database frame and click on Visualize this data option
    And User clicks on Save button
    And User fill the all information and clicks on Save button
    Then User can see Insight created toast message as 'Successfully saved insight(s)'
    
  Scenario: Create an Insight from a database created by importing a Excel file
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
    And User clicks on Add option
    And User search 'DB created from Excel' database and select
    And User clicks on Add All button from Available Columns section
    And User clicks on Import button from Selected Columns section
    And User mouse hover on database frame and click on Visualize this data option
    And User clicks on Save button
    And User fill the all information and clicks on Save button
    Then User can see Insight created toast message as 'Successfully saved insight(s)'