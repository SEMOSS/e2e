Feature: Function documentation

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create a Function
    Given User captures documentation screenshot for 'PlatformNavigation/Function Catalog'
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
    And User clicks on Connect button to create function
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully added function database to catalog'
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
    And User clicks on file upload icon
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User captures screenshot for "Function ZIP"
    And User clicks on 'Close' button
    And User opens Main Menu
    And User completes screenshot capture and triggers comparison for 'Function Catalog'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create a Function document for the Edit button
    Given User captures documentation screenshot for 'FunctionEngines'
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'REST'
    And User enters Catalog name 'TestFunctionExport'
    And User enters Url as 'https://api.api-ninjas.com/v1/weather'
    And User selects HTTP method as 'GET'
    And User selects Post body message as 'json'
    And User enters Function parameters as '[{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}]'
    And User enters Function required parameters as '["lat", "lon"]'
    And User enters Function name as 'WeatherFunctionExport'
    And User enters Function description as 'a function to call weather based on lat and long'
    And User clicks on Connect button to create function
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully added function database to catalog'
    And User captures a "button" and highlights the "Edit" with name "Edit"
    And User completes screenshot capture and triggers comparison for 'Function Catalog'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create a Function document for the Export button
    Given User captures documentation screenshot for 'FunctionEngines'
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'REST'
    And User enters Catalog name 'TestFunctionExport'
    And User enters Url as 'https://api.api-ninjas.com/v1/weather'
    And User selects HTTP method as 'GET'
    And User selects Post body message as 'json'
    And User enters Function parameters as '[{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}]'
    And User enters Function required parameters as '["lat", "lon"]'
    And User enters Function name as 'WeatherFunctionExport'
    And User enters Function description as 'a function to call weather based on lat and long'
    And User clicks on Connect button to create function
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully added function database to catalog'
    And User captures a 'buttonType' and highlights the "Export" with name "Export"
    And User completes screenshot capture and triggers comparison for 'Function Catalog Export Button'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create Function - Capture function AccessControl Screenshot
    Given User captures documentation screenshot for 'FunctionEngines'
    When User opens Main Menu
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
    And User clicks on Access Control button
    And User captures screenshot for "functionAccessControl"
    And User completes screenshot capture and triggers comparison for 'Function Engines'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create Function - Capture function RequestAccess Screenshot
    Given User captures documentation screenshot for 'FunctionEngines'
    When User opens Main Menu
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
    And User clicks on Access Control button
    And User clicks Make 'Function' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Discoverable Functions button
    Then User sees the function name 'TestFunction' in the function catalog
    And User clicks on the function name 'TestFunction' in the function catalog
    And User click on the Request Access button
    And User captures screenshot for "functionRequestAccess"
    And User clicks on 'Cancel' button
    And User logs out from the application
    And User login as 'Admin'
    And User completes screenshot capture and triggers comparison for 'Function Engines'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
    Scenario: Create a Function Form Document 
    Given User captures documentation screenshot for 'FunctionEngines'
    And User opens Main Menu
    When User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'Azure Document Intelligence'
    Then User captures screenshot for "create_func"
    And User completes screenshot capture and triggers comparison for 'Function Engines'

    
    
