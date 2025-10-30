Feature: Validate catalog user permissions for all catalog types

  @LoginWithAuthor @DeleteTestCatalog @Regression
  Scenario Outline: validate user access permissions of '<CATALOG>'
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
    And User can search '<CATALOG_NAME>' in search box
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
    And user can see permission date along with user added time
      | Author    |
      | Editor    |
      | Read-Only |
    When User logs out from the application
    And User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the '<CARD>' card
    And User can search '<CATALOG_NAME>' in search box
    And User clicks on the '<CATALOG_NAME>'
    And User search for 'Read' user in members search box
    And 'Editor' user changes 'Read-Only' role to 'Editor'
    Then User should see role changed to 'Editor' in members list
    When 'Editor' user deletes 'Editor' role user from members list
    Then User should see 'Editor' role user is removed from members list
    When User search for 'Author' user in members search box
    And 'Editor' user changes 'Author' role to 'Read-Only'
    Then 'Editor' user cannot change the 'Author' user role and sees 'Author' user in members list
    When 'Editor' user deletes 'Author' role user from members list
    Then 'Editor' user cannot delete the 'Author' user and sees 'Author' user in members list
    When User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    When User logs out from the application
    And User login as 'Read'
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the '<CARD>' card
    And User can search '<CATALOG_NAME>' in search box
    And User clicks on the '<CATALOG_NAME>'
    Then 'Read-Only' user can see 'delete' icon is disabled
    And 'Read-Only' user can see 'edit' icon is disabled
    Then 'Read-Only' user can see 'delete' icon is disabled
    And 'Read-Only' user can see 'edit' icon is disabled
    When User logs out from the application
    And User login as 'Author'
    And User opens Main Menu
    And User clicks on Open Settings
    And User selects the '<CARD>' card
    And User can search '<CATALOG_NAME>' in search box
    And User clicks on the '<CATALOG_NAME>'
    And User search for 'Editor' user in members search box
    When 'Author' user changes 'Editor' role to 'Read-Only'
    Then User should see role changed to 'Read-Only' in members list
    When 'Author' user deletes 'Read-Only' role user from members list
    Then User should see 'Read-Only' role user is removed from members list

    Examples: 
      | CATALOG  | BUTTON_NAME  | FILE_NAME                        | CARD              | CATALOG_NAME        |
      | Model    | Add Model    | Model/Llama3-70B-Instruct.zip    | Model Settings    | Llama3-70B-Instruct |
      | Database | Add Database | Database/TestDatabase.zip        | Database Settings | TestDatabase        |
      | Vector   | Add Vector   | VectorDatabase/TestVector.zip    | Vector Settings   | TestVector          |
      | Function | Add Function | Function/weatherFunctionTest.zip | Function Settings | WeatherFunctionTest |
      | Storage  | Add Storage  | Storage/Localminio.zip           | Storage Settings  | Localminio          |

  @LoginWithAuthor @DeleteCreatedTestApp @Regression
  Scenario: validate user access permissions of Apps
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in 'Drag and Drop'
    And User enters 'Test App permissions' as app name
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the 'App Settings' card
    And User can search 'Test App permissions' in search box
    And User clicks on the 'Test App permissions'
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    Then User should see users with following permissions
      | USER_TYPE | ROLE      |
      | Author    | Author    |
      | Editor    | Editor    |
      | Read      | Read-Only |
    And user can see permission date along with user added time
      | Author    |
      | Editor    |
      | Read-Only |
    When User logs out from the application
    And User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the 'App Settings' card
    And User can search 'Test App permissions' in search box
    And User clicks on the 'Test App permissions'
    And User search for 'Read' user in members search box
    And 'Editor' user changes 'Read-Only' role to 'Editor'
    Then User should see role changed to 'Editor' in members list
    When 'Editor' user deletes 'Editor' role user from members list
    Then User should see 'Editor' role user is removed from members list
    When User search for 'Author' user in members search box
    And 'Editor' user changes 'Author' role to 'Read-Only'
    Then 'Editor' user cannot change the 'Author' user role and sees 'Author' user in members list
    When 'Editor' user deletes 'Author' role user from members list
    Then 'Editor' user cannot delete the 'Author' user and sees 'Author' user in members list
    When User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    When User logs out from the application
    And User login as 'Read'
    And User opens Main Menu
    And User clicks on Open Settings
    When User selects the 'App Settings' card
    And User can search 'Test App permissions' in search box
    And User clicks on the 'Test App permissions'
    Then 'Read-Only' user can see 'delete' icon is disabled
    And 'Read-Only' user can see 'edit' icon is disabled
    Then 'Read-Only' user can see 'delete' icon is disabled
    And 'Read-Only' user can see 'edit' icon is disabled
    When User logs out from the application
    And User login as 'Author'
    And User opens Main Menu
    And User clicks on Open Settings
    And User selects the 'App Settings' card
    And User can search 'Test App permissions' in search box
    And User clicks on the 'Test App permissions'
    And User search for 'Editor' user in members search box
    When 'Author' user changes 'Editor' role to 'Read-Only'
    Then User should see role changed to 'Read-Only' in members list
    When 'Author' user deletes 'Read-Only' role user from members list
    Then User should see 'Read-Only' role user is removed from members list
