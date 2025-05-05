Feature: Add Function

  @LoginWithAdmin
  Scenario: Create Function using ZIP file
    Given User navigates to Open Function
    When User clicks on Add Function
    Then User selects function 'ZIP'
    And User uploads function file ' Function/weatherFunctionTest.zip'
    And User clicks on Create Funtion button
    And User sees the function name 'WeatherFunctionTest' in the function catalog

  Scenario: Delete Function
    Given User navigates to Open Function
    When User sees the function name 'WeatherFunctionTest' in the function catalog
    Then User clicks on the function name 'WeatherFunctionTest' in the function catalog
    And User clicks on Access Control Tab
    And User clicks on Delete button
    And User sees deleted function success toast message 'Successfully deleted Function'
