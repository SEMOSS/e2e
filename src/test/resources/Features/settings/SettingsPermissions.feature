Feature: Validate catalog user permissions for all catalog types

  @LoginWithAuthor @DeleteTestCatalog
  Scenario Outline: 
    Given User opens Main Menu
    When User opens '<CATALOG>'
    And User clicks on Add '<CATALOG>' button
    And User selects the 'ZIP' option to upload file
    And User uploads the file '<FILE_NAME>'
    And User clicks on Create '<CATALOG>' button to create catalog
    And User clicks On Copy Catalog ID
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the '<CARD>' card
    And User clicks on the '<CATALOG_NAME>'
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    Then User should see users with following permissions
      | USER_TYPE | ROLE      |
      | Author    | Author    |
      | Editor    | Editor    |
      | Read      | Read-Only |
    #And user can see permission date as current date
    When User logs out from the application
    And User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the '<CARD>' card
    And User can serach '<CATALOG_NAME>' in search box
    And User clicks on the '<CATALOG_NAME>'
    And User search for 'Read' user in members search box
    And 'Editor' user changes 'Read' user role to 'Editor'
    Then User should see role changed to 'Editor' in members list
    When 'Editor' user deletes 'Editor' role user from members list
    Then User should see 'Editor' role user is removed from members list
    When User search for 'Author' user in members search box
    And 'Editor' user changes 'Author' user role to 'Read-Only'
    Then 'Editor' user cannot change the 'Author' user role and sees 'Author' user in members list
    When 'Editor' user deletes 'Author' role user from members list
    Then 'Editor' user cannot delete the 'Author' user and sees 'Author' user in members list
    When User logs out from the application
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read-Only'
    When User logs out from the application
    And User login as 'Read-Only'
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the '<CARD>' card
    And User can serach '<CATALOG_NAME>' in search box
    And User clicks on the '<CATALOG_NAME>'
    And User search for 'Author' user in members search box
    Then 'Read-Only' user can see 'Delete' button is disabled
    And 'Read-Only' user can see 'Edit' button is disabled
    And User search for 'Editor' user in members search box
    Then 'Read-Only' user can see 'Delete' button is disabled
    And 'Read-Only' user can see 'Edit' button is disabled
    And User search for 'Read-Only' user in members search box
    Then 'Read-Only' user can see 'Delete' button is disabled
    And 'Read-Only' user can see 'Edit' button is disabled
    When User logs out from the application
    And User login as 'Author'
    And User opens Main Menu
    And User clicks on Open Settings
    And User selects the '<CARD>' card
    And User can serach '<CATALOG_NAME>' in search box
    And User clicks on the '<CATALOG_NAME>'
    And User search for 'Editor' user in members search box
    Then User can see 'Editor' user with 'Editor' role in members list
    When 'Author' user changes 'Editor' user role to 'Read-Only'
    Then User should see role changed to 'Read-Only' in members list
    When 'Author' user deletes 'Read-Only' role user from members list
    Then User should see 'Read-Only' role user is removed from members list

    Examples: 
      | CATALOG  | BUTTON_NAME  | FILE_NAME                        | CARD              | CATALOG_NAME        |
      | Model    | Add Model    | Model/Llama3-70B-Instruct.zip    | Model Settings    | Llama3-70B-Instruct |
      | Database | Add Database | Database/TestDatabase.zip        | Database Settings | TestDatabase        |
      | Vector   | Add Vector   | VectorDatabase/TestVector.zip    | Vector Settings   | TestVector          |
      | Function | Add Function | Function/weatherFunctionTest.zip | Function Settings | weatherFunctionTest |
      | Storage  | Add Storage  | Storage/Localminio.zip           | Storage Settings  | Localminio          |
