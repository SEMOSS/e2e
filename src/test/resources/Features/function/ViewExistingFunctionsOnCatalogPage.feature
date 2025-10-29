Feature: View existing functions on Function Catalog Page

  Background: Create Function using ZIP file
    Given User opens Main Menu
    When User clicks on Open Function
    And User clicks on Add Function
    Then User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button
    And User clicks On Copy Catalog ID
    And User can see the Catalog title as 'WeatherFunctionTest'
    And User clicks on Edit button
    And User add tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'IP, PHI' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: view and validate filter functionality - My Functions
    Given User opens Main Menu
    When User clicks on Open Function
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    And User applies each filter and validate 'WeatherFunctionTest' catalog is visible on the 'function' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |

 @DeleteTestCatalog @Regression
  Scenario: view and validate filter functionality - Discoverable Functions
    Given User opens Main Menu
    When User clicks on Open Function
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    When User clicks on the function name 'WeatherFunctionTest' in the function catalog
    And User clicks on Access Control Tab
    And User clicks Make 'Function' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Discoverable Functions button
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    And User applies each filter and validate 'WeatherFunctionTest' catalog is visible on the 'function' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE |
      | Data Classification | IP           |
      | Data Restrictions   | IP ALLOWED   |
    When User logs out from the application
    And User login as 'admin'
   
