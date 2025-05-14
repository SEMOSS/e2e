Feature: Add Function

  @LoginWithAdmin
  Scenario: Create Function using ZIP file
    Given User navigates to Open Function
    When User clicks on Add Function
    Then User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Funtion button
    And User sees the function name 'WeatherFunctionTest' in the function catalog

  @LoginWithAdmin
  Scenario Outline: add function with all the required fields
    Given User navigates to Open Function
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
    And User clicks on Create Funtion button
    And User sees success toast message '<Toast_message>'
    And User sees the function name '<catalogName>' in the function catalog

    Examples:
      | functionType | catalogName             | url                                   | httpMethod | postBodyMessage | headers                | functionParameters                                                                                                                                                                                | functionRequiredParameters | functionName    | functionDescription                              | functionTitle | Create_Funtion  | Toast_message                          | required_fields                                                                                                                                                             |
      | REST         | TestFunction{Timestamp} | https://api.api-ninjas.com/v1/weather | GET        | json            | {"X-Api-Key": "myKey"} | [{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}] | ["lat", "lon"]             | WeatherFunction | a function to call weather based on lat and long | catalog_name  | Create function | Successfully added function to catalog | Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata) |

  @LoginWithAdmin
  Scenario Outline: Add Function with missing Form fields
    Given User navigates to Open Function
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
    Then User clicks on Create Funtion button
    And User redirects to the missing input field

    Examples:
      | functionType | catalogName             | httpMethod | postBodyMessage | headers                | functionParameters                                                                                                                                                                                | functionRequiredParameters | functionName    | functionDescription                              | functionTitle | Create_Funtion  | Toast_message                          | required_fields                                                                                                                                                             |
      | REST         | TestFunction{Timestamp} | GET        | json            | {"X-Api-Key": "myKey"} | [{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}] | ["lat", "lon"]             | WeatherFunction | a function to call weather based on lat and long | catalog_name  | Create function | Successfully added function to catalog | Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata) |

  @LoginWithAdmin
  Scenario: Delete Function
    Given User navigates to Open Function
    When User sees the function name '<function_name>' in the function catalog
    Then User clicks on the function name '<function_name>' in the function catalog
    And User clicks on Access Control Tab
    And User clicks on Delete button
    And User sees deleted function success toast message '<Toast_message>'

    Examples:
      | function_name           | Toast_message                 |
      | WeatherFunctionTest     | Successfully deleted Function |
      | TestFunction{Timestamp} | Successfully deleted Function |
