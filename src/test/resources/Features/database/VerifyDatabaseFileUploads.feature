Feature: verify all file Uploads database types with its fields

  Scenario Outline: Verify <DATABASE> database types with its fields
    Given User opens Main Menu
    When User clicks on Open Database
    And User clicks on Add Database
    And User clicks on the 'file uploads' tab from options
    And User selects database '<DATABASE>' from connection types
    Then User can see form sections with fields:
      | SECTION_NAME | FIELDS      |
      | <S1_NAME>    | <S1_FIELDS> |
      | <S2_NAME>    | <S2_FIELDS> |
    And User can see database mandatory fields
      | <MANDATORY_FIELDS> |

    Examples:
      | DATABASE | S1_NAME | S1_FIELDS                                                          | S2_NAME  | S2_FIELDS                                                  | MANDATORY_FIELDS                                               |
      | CSV      | General | Enter Database Name, Enter Database Description, Enter Database Tag | Database | Enter Database Type, Enter Delimiter, Enter Metamodel Type | Enter Database Name, Enter Database Type, Enter Metamodel Type |
      | TSV      | General | Enter Database Name, Enter Database Description, Enter Database Tag | Database | Enter Database Type, Enter Delimiter, Enter Metamodel Type | Enter Database Name, Enter Database Type, Enter Metamodel Type |
      | Excel    | General | Enter Database Name, Enter Database Description, Enter Database Tag | Database | Enter Database Type, Enter Metamodel Type | Enter Database Name, Enter Database Type, Enter Metamodel Type |
