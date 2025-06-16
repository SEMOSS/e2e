Feature: Add Function

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

  @LoginWithAdmin
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

  @LoginWithAdmin
  Scenario Outline: Create function with all the required fields
    Given User clicks on Open Function
    When User clicks on Add Function
    Then User selects function '<functionType>'
    And User sees astrisk mark on the required fields '<required_fields>'
    And User enters Catalog name '<catalogName>'
    And User enters Url as '<url>'
    And User selects HTTP method as '<httpMethod>'
    And User selects Post body message as '<postBodyMessage>'
    And User enters Headers as '<headers>'
    And User enters Function parameters as '<functionParameters>'
    And User enters Function required parameters as '<functionRequiredParameters>'
    And User enters Function name as '<functionName>'
    And User enters Function description as '<functionDescription>'
    And User sees Create Function button
    And User clicks on Create Function button
    And User sees success toast message '<Toast_message>'
    And User sees the function name '<catalogName>' in the function catalog

    Examples: 
      | functionType | catalogName             | url                                   | httpMethod | postBodyMessage | headers                | functionParameters                                                                                                                                                                                | functionRequiredParameters | functionName    | functionDescription                              | functionTitle | Create_Funtion  | Toast_message                          | required_fields                                                                                                                                                             |
      | REST         | TestFunction{Timestamp} | https://api.api-ninjas.com/v1/weather | GET        | json            | {"X-Api-Key": "myKey"} | [{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}] | ["lat", "lon"]             | WeatherFunction | a function to call weather based on lat and long | catalog_name  | Create function | Successfully added function to catalog | Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata) |

  @LoginWithAdmin
  Scenario Outline: Add Function with missing Form fields
    Given User clicks on Open Function
    When User clicks on Add Function
    And User selects function '<functionType>'
    And User sees astrisk mark on the required fields '<required_fields>'
    And User enters Catalog name '<catalogName>'
    And User selects HTTP method as '<httpMethod>'
    And User selects Post body message as '<postBodyMessage>'
    And User enters Headers as '<headers>'
    And User enters Function parameters as '<functionParameters>'
    And User enters Function required parameters as '<functionRequiredParameters>'
    And User enters Function name as '<functionName>'
    And User enters Function description as '<functionDescription>'
    Then User clicks on Create Function button
    And User redirects to the missing input field

    Examples: 
      | functionType | catalogName             | httpMethod | postBodyMessage | headers                | functionParameters                                                                                                                                                                                | functionRequiredParameters | functionName    | functionDescription                              | functionTitle | Create_Funtion  | Toast_message                          | required_fields                                                                                                                                                             |
      | REST         | TestFunction{Timestamp} | GET        | json            | {"X-Api-Key": "myKey"} | [{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}] | ["lat", "lon"]             | WeatherFunction | a function to call weather based on lat and long | catalog_name  | Create function | Successfully added function to catalog | Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata) |

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
      | TestFunction{Timestamp} | Successfully deleted Function |
