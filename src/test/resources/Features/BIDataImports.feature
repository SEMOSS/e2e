Feature: Add Database to BI System app
  I want to use this feature file for all the scenarios related to BI System app

  Background: Login to the application
    Given User is on application
    When User enters username and password and click on SignIn button
    Then User can navigate to home page

  Scenario: Importing Data into a New Database for Business Insights -via CSV
    When User clicks on System app
    And User clicks on BI
    And User clicks on Catalog option
    And User clicks on Add Database button
    And User enter the database name as 'DB created by Automation'
    And User upload CSV file and clicks on Next button
    And User clicks on Import button
    And User clicks on Add option
    And User search 'DB created by Automation' database and select
    And User clicks on Add All button from Available Columns section
    And User clicks on Import button from Selected Columns section
    And User mouse hover on database frame and click on Visualize this data option
    And User click on Save button
    And User fill the all information and clicks on Save button
    Then User can see toast message as 'Successfully saved insight(s)'

  Scenario: Importing Data into a New Database for Business Insights -via Excel
    When User clicks on System app
    And User clicks on BI
    And User clicks on Catalog option
    And User clicks on Add Database button
    And User select the Excel option
    And User enter the database name as 'DB created by Automation'
    And User upload Excel file and clicks on Next button
    And User clicks on Import button
    And User clicks on Add option
    And User search 'DB created by Automation' database and select
    And User clicks on Add All button from Available Columns section
    And User clicks on Import button from Selected Columns section
    And User mouse hover on database frame and click on Visualize this data option
    And User click on Save button
    And User fill the all information and clicks on Save button
    Then User can see toast message as 'Successfully saved insight(s)'
