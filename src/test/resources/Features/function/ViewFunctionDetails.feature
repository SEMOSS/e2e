@DeleteCreatedCatalog
Feature: View Function Details

  Background: Create Function using ZIP file
    Given User opens Main Menu
    When User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button 
    Then User sees the function name 'WeatherFunctionTest' in the function catalog

  Scenario: View overview details in "Overview" tab for selected Function
    When User clicks on the function name 'WeatherFunctionTest' in the function catalog
    Then User can see 'WeatherFunctionTest' as function name
    And User can see function ID
    And User can see 'Please use the Edit button to provide a description for this Function. A description will help others find the Function and understand how to use it. To include more details associated with the Function, edit the markdown located in the Overview section.' as function description
    And User can see 'N/A' as Date last updated
    And User can see ' No Markdown available' Markup with Function overview in Overview tab at the bottom of the page.
    And User clicks on Access Control Tab
    And User clicks on Add Member button
    And User adds one user and assigns them as 'read'
    And User logs out from the application
    Then User login as "read"
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on the function name 'WeatherFunctionTest' in the function catalog
    Then User sees 'Change Access' button
    And User logs out from the application
    Then User login as "native"
    And User opens Main Menu
    And User clicks on Open Function
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    Then User clicks on the function name 'WeatherFunctionTest' in the function catalog

  Scenario: View usage details in "Usage" tab for selected Function
    When User clicks on the function name 'WeatherFunctionTest' in the function catalog
    When User selects 'Usage' tab
    And User can see 'How to use in Javascript' usage instructions section
    And User can see 'How to use in Python' usage instructions section
    And User can see 'How to use in Java' usage instructions section
 
