Feature: Add Function

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario Outline: Create function with all the required fields
    Given User opens Main Menu
    When User clicks on Open Function
    And User clicks on Add Function
    And User selects function '<functionType>'
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
    And User sees Connect button
    And User clicks on Connect button to create function
    And User clicks on Copy Catalog ID
    And User sees success toast message '<Toast_message>'

    Examples: 
      | functionType | catalogName             | url                                   | httpMethod | postBodyMessage | headers                | functionParameters                                                                                                                                                                                | functionRequiredParameters | functionName    | functionDescription                              | functionTitle | Create_Funtion  | Toast_message                                   | required_fields                                                                                                                                                             |
      | REST         | TestFunction{Timestamp} | https://api.api-ninjas.com/v1/weather | GET        | json            | {"X-Api-Key": "myKey"} | [{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}] | ["lat", "lon"]             | WeatherFunction | a function to call weather based on lat and long | catalog_name  | Create function | Successfully added function database to catalog | Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata) |

  @LoginWithAdmin @Regression
  Scenario Outline: Add Function with missing Form fields
    Given User opens Main Menu
    And User clicks on Open Function
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
    Then User sees the Create function button is disabled

    Examples: 
      | functionType | catalogName             | httpMethod | postBodyMessage | headers                | functionParameters                                                                                                                                                                                | functionRequiredParameters | functionName    | functionDescription                              | functionTitle | Create_Funtion  | Toast_message                          | required_fields                                                                                                                                                             |
      | REST         | TestFunction{Timestamp} | GET        | json            | {"X-Api-Key": "myKey"} | [{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}] | ["lat", "lon"]             | WeatherFunction | a function to call weather based on lat and long | catalog_name  | Create function | Successfully added function to catalog | Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata) |
