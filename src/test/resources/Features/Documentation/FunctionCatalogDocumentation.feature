Feature: Function documentation

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create a Function
    Given User captures documentation screenshot for 'Function Catalog'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Function'
    When User clicks on Open Function
    And User captures a 'button' and highlights the 'Add Function'
    When User clicks on Add Function
    And User captures screenshot for "Function Options"
    And User selects function 'REST'
    And User enters Catalog name 'TestFunction'
    And User enters Url as 'https://api.api-ninjas.com/v1/weather'
    And User selects HTTP method as 'GET'
    And User selects Post body message as 'json'
    And User enters Function parameters as '[{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}]'
    And User enters Function required parameters as '["lat", "lon"]'
    And User enters Function name as 'WeatherFunction'
    And User enters Function description as 'a function to call weather based on lat and long'
    And User captures a 'button' and highlights the 'Create function'
    And User clicks on Create Function button
    And User sees success toast message 'Successfully added function to catalog'
    And User clicks On Copy Catalog ID
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User captures a 'button' and highlights the 'Access Control'
    And User clicks on Access Control button
    And User captures a 'Heading' and highlights the 'Pending Requests'
    And User clicks on Usage tab
    And User captures a 'tab' and highlights the 'Usage'
    And User clicks on 'Edit' button
    And User captures screenshot for form "Edit Function Options"
    And User clicks on 'Close' button
    And User clicks on Access Control Tab
    And User clicks Make 'Function' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Discoverable Functions button
    And User captures a 'button' and highlights the 'Discoverable Functions'
    Then User sees the function name 'TestFunction' in the function catalog
    And User clicks on the function name 'TestFunction' in the function catalog
    And User captures screenshot for "Request Access"
    And User click on the Request Access button
    And User selects 'author' access
    And User captures screenshot for "Access Request"
    And User clicks on Request button
    And User logs out from the application
    And User login as 'admin'
    And User opens Main Menu
    And User clicks on Open Function
    When User clicks on Add Function
    #And User selects function 'ZIP'
    #And User uploads function file 'Function/weatherFunctionTest.zip'
    And User selects the 'ZIP' option to upload file
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User captures screenshot for "Function ZIP"
    And User opens Main Menu
    And User completes screenshot capture and triggers comparison for 'Function Catalog'
