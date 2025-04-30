Feature: Add Function

  @LoginWithAdmin
  Scenario Outline: Create Function using ZIP file
    Given User navigates to Open Function
    When User clicks on Add Function
    Then User selects function '<functionType>'
    And User uploads function file '<filePath>'
    And User clicks on Create Funtion button
    And User sees the function name '<funtion_name>' in the function catalog

    Examples:
      | functionType | filePath                         | funtion_name        |
      | ZIP          | Function/weatherFunctionTest.zip | WeatherFunctionTest |

  Scenario Outline: Delete Function
    Given User navigates to Open Function
    When User sees the function name '<funtion_name>' in the function catalog
    Then User clicks on the function name '<funtion_name>' in the function catalog
    And User clicks on Access Control Tab
    And User clicks on Delete button
    And User sees deleted function success toast message '<Toast_message>'

    Examples:
      | funtion_name        | Toast_message                 |
      | WeatherFunctionTest | Successfully deleted Function |

#   Scenario Outline: Fill all the fields in the Add Function form
#     Given User navigates to Open Function
#     When User clicks on Add Function
#     Then User selects function '<functionType>'
#     And User sees astrisk mark on the required fields '<required_fields>'
#     And User enters Catalog name '<catalogName>'
#     And User enters Url as '<url>'
#     And User selects HTTP method as '<httpMethod>'
#     And User selects Post body message as '<postBodyMessage>'
#     And User enters Headers as '<headers>'
#     And User enters Function parameters as '<functionParameters>'
#     And User enters Function required parameters as '<functionRequiredParameters>'
#     And User enters Function name as '<functionName>'
#     And User enters Function description as '<functionDescription>'
#     And User clicks on Create Funtion button
#     And User sees Toast message '<Toast_message>'
#     Examples:
#       | functionType | catalogName | url | httpMethod | postBodyMessage | headers | functionParameters | functionRequiredParameters | functionName | functionDescription | functionTitle | Create_Funtion  | Toast_message                                                                                  | required_fields                                                                                                                                                             |
#       | REST         | test1       | url | GET        | json            | header1 | param1             | requireAParam1             | Functionname | Functiondescription | catalog_name  | Create function | java.lang.IllegalStateException: Expected BEGIN_ARRAY but was STRING at line 1 column 1 path $ | Function Type,Catalog Name,URL,Http Method,POST Message Body Type,Function Parameters,Function Required Parameters,Function Name (metadata),Function Description (metadata) |
