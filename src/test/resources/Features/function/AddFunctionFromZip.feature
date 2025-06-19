Feature: Add Function From Zip

  Background: Create Function using ZIP file
    Given User clicks on Open Function
    When User clicks on Add Function
    Then User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button
    And User sees the function name 'WeatherFunctionTest' in the function catalog

  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: Validate Change access popup
    Given User sees the function name 'WeatherFunctionTest' in the function catalog
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog
    When 'Admin' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as "Editor"
    And User clicks on Open Function
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog
    And User click on the Change Access button
    And User should see the "Change Access" popup with following options:
      | Author         |
      | Editor         |
      | Read-Only      |
      | Comment Box    |
      | Cancel Button  |
      | Request Button |
    And User click on cancel button
    And User logs out from the application
    Then User login as "Admin"
    And User clicks on Open Function
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog

  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: Validate change access request
    Given User sees the function name 'WeatherFunctionTest' in the function catalog
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog
    When 'Admin' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as "Editor"
    And User clicks on Open Function
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog
    And User click on the Change Access button
    And User selects 'author' access
    And User types a comment as 'Access Request'
    And User clicks on Request button
    Then User should successfully request access given the Vector is requestable with a toast message as 'Successfully requested access to engine'
    And User logs out from the application
    And User login as "Admin"
    And User clicks on Open Function
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog

	@LoginWithAdmin
  Scenario Outline: Delete Function
    Given User clicks on Open Function
    When User sees the function name '<function_name>' in the function catalog
    Then User clicks on the function name '<function_name>' in the function catalog
    And User clicks on Access Control Tab
    And User clicks on Delete button
    And User sees deleted function success toast message '<Toast_message>'

    Examples: 
      | function_name           | Toast_message                 |
      | WeatherFunctionTest     | Successfully deleted Function |
