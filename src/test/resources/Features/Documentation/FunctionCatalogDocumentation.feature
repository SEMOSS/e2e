Feature: Function documentation

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create a Function
    Given User captures documentation screenshot for 'PlatformNavigation/Function Catalog'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Function'
    And User clicks on Open Function
    And User captures a 'button' and highlights the 'Add Function'
    And User clicks on Add Function
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
    And User clicks on 'Cancel' button
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
    Given User captures documentation screenshot for 'PlatformNavigation/Function Catalog'
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
    Given User captures documentation screenshot for 'PlatformNavigation/Function Catalog'
    When User opens Main Menu
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
    Then User captures a 'buttonType' and highlights the "Export" with name "Export"
    And User completes screenshot capture and triggers comparison for 'Function Catalog Export Button'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create Function - Capture function AccessControl Screenshot
    Given User captures documentation screenshot for 'PlatformNavigation/Function Catalog'
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
    Then User captures screenshot for "functionAccessControl"
    And User completes screenshot capture and triggers comparison for 'Function Engines'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Create Function - Capture function RequestAccess Screenshot
    Given User captures documentation screenshot for 'PlatformNavigation/Function Catalog'
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
    Then User captures screenshot for "functionRequestAccess"
    And User clicks on 'Cancel' button
    And User logs out from the application
    And User login as 'Admin'
    And User completes screenshot capture and triggers comparison for 'Function Engines'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Create a Function Form Document
    Given User captures documentation screenshot for 'PlatformNavigation/Function Catalog'
    When User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'Azure Document Intelligence'
    Then User captures screenshot for "create_func"
    And User completes screenshot capture and triggers comparison for 'Function Engines'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteTestCatalog
  Scenario: Function Engine Edit Interface Document
    Given User captures documentation screenshot for 'FunctionEngines'
    When User opens Main Menu
    And User clicks on Open Function
    And User checks if 'Function' catalog created and Deletes the 'WeatherFunctionTest'
    And User clicks on Add Function
    And User clicks on file upload icon
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully Created Function Database'
    And User clicks on Edit button
    And User resize the browser window size to '1200,1100'
    Then User captures screenshot for "EditInterface"
    And User resize the browser window size to '1280,720'
    And User completes screenshot capture and triggers comparison for "EditInterface"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteTestCatalog
  Scenario: Function Engine - Capture Azure Document Intelligence form
    Given User captures documentation screenshot for 'FunctionEngines'
    When User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    Then User captures a 'testidelement, testidelement' and highlights the "importPageContent-connect-to-Azure-Document-Intelligence-img,tabs" with name "azure"
    And User completes screenshot capture and triggers comparison for "FunctionEngines"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteTestCatalog
  Scenario: Function Engine - properties
    Given User captures documentation screenshot for 'FunctionEngines'
    When User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'Azure Document Intelligence'
    Then User captures a 'catalogformpage' and highlights the "function-form-box" with name "func-prop"
    And User completes screenshot capture and triggers comparison for "FunctionEngines"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteTestCatalog
  Scenario: Function Engine add zip Document
    Given User captures documentation screenshot for 'FunctionEngines'
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User clicks on file upload icon
    And User uploads the file 'Function/weatherFunctionTest.zip'
    Then User captures screenshot for "add_zip"
    And User completes screenshot capture and triggers comparison for "EditInterface"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteTestCatalog
  Scenario: Function Engine - properties
    Given User captures documentation screenshot for 'FunctionEngines'
    When User opens Main Menu
    And User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'Azure Document Intelligence'
    Then User captures a 'testidelement' and highlights the "function-form-submit" with name "create_func"
    And User completes screenshot capture and triggers comparison for "FunctionEngines"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteTestCatalog
  Scenario: Function Engines - Function Access Control Documentation
    Given User captures documentation screenshot for 'FunctionEngines'
    And User opens Main Menu
    When User clicks on Open Function
    And User checks if 'Function' catalog created and Deletes the 'WeatherFunctionTest'
    And User clicks on Add Function
    And User clicks on file upload icon
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    Then User sees success toast message 'Successfully Created Function Database'
    When User clicks on Access Control Tab
    Then User captures screenshot for "functionAccessControl"
    And User completes screenshot capture and triggers comparison for "Function Access Control Page"

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteTestCatalog
  Scenario: Function Engines - Discoverable Function Documenatation
    Given User captures documentation screenshot for 'FunctionEngines'
    And User opens Main Menu
    When User clicks on Open Function
    And User checks if 'Function' catalog created and Deletes the 'WeatherFunctionTest'
    And User clicks on Add Function
    And User clicks on file upload icon
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User clicks on Access Control Tab
    And User clicks Make 'Function' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Function
    And User clicks on Discoverable Functions button
    Then User sees the function name 'WeatherFunctionTest' in the function catalog
    And User captures a 'testidelement' and highlights the "engineIndexPage-Functions-discoverable-switch" with name "discoverableFunction"
    And User completes screenshot capture and triggers comparison for "Discoverable Function page"
   
