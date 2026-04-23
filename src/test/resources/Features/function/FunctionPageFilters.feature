 Feature: Function Page Filters
 
  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: Validate the Function filter on app page
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User clicks on file upload icon
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'REST'
    And User enters Catalog name 'TestFunction'
    And User enters Url as 'https://api.api-ninjas.com/v1/weather'
    And User selects HTTP method as 'GET'
    And User selects Post body message as 'json'
    And User enters Function parameters as '[{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}]'
    And User enters Function required parameters as '["lat", "lon"]'
    And User enters Function name as 'WeatherFunction'
    And User enters Function description as 'a function to call weather based on lat and long'
    And User clicks on Connect button to create function
    And User clicks on Copy Catalog ID
    When User opens Main Menu
    And User clicks on Open Function
    And User clicks on the 'Ascending' Filter button
    Then User can see the "Function" are sorted in ascending order
    When User clicks on the 'Descending' Filter button
    Then User can see the "Function" are sorted in descending order
    And User opens Main Menu
    And User clicks on Open Function
    When User selects 'Date Created' from the Sort By dropdown
    And User clicks on the 'Ascending' Filter button
    Then User can see the 'Function' are sorted by date created in 'ascending' order
    When User clicks on the 'Descending' Filter button
    Then User can see the 'Function' are sorted by date created in 'descending' order