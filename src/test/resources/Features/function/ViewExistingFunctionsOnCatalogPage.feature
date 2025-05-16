Feature: View existing functions in Function Catalog

  Background: Create Function using ZIP file
    Given User navigates to Open Function
    When User clicks on Add Function
    Then User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    When User clicks on the function name 'WeatherFunctionTest' in the function catalog
    And User clicks on Edit button
    And User add tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'IP, PHI' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'

  @LoginWithAdmin
  Scenario: view and validate filter functionality - My Functions
    Given User navigates to Open Function
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    And User applies each filter and validate 'WeatherFunctionTest' function is visible on the page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |
    When User clicks on the function name 'WeatherFunctionTest' in the function catalog
    And User clicks on Access Control Tab
    And User clicks on Delete button
    Then User sees deleted function success toast message 'Successfully deleted Function'


### TODO restore this test once the auto bookmarking of discoverable functions bug is resolved

  #Scenario: view and validate filter functionality - Discoverable Functions
    #Given User navigates to Open Function
    #Then User sees the function name 'WeatherFunctionTest' in the function catalog
    #When User clicks on the function name 'WeatherFunctionTest' in the function catalog
    #And User clicks on Access Control Tab
    #And User clicks Make Discoverable button
    #And User logs out from the application
    #And User login as 'author'
    #And User navigates to Open Function
    #And User clicks on Discoverable Functions button
    #Then User sees the function name 'WeatherFunctionTest' in the function catalog
    #And User applies each filter and validate 'WeatherFunctionTest' function is visible on the page
      #| FILTER_CATEGORY     | FILTER_VALUE |
      #| Data Classification | IP           |
      #| Data Restrictions   | IP ALLOWED   |
    #When User logs out from the application
    #And User login as 'admin'
    #And User navigates to Open Function
    #When User clicks on the function name 'WeatherFunctionTest' in the function catalog
    #And User clicks on Access Control Tab
    #And User clicks on Delete button
    #Then User sees deleted function success toast message 'Successfully deleted Function'
