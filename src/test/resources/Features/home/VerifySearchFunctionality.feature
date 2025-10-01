Feature: Search app and catalogs

  @LoginWithAdmin
  Scenario: Search App
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters 'Test apps' as app name
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User opens Main Menu
    And User clicks on Home
    And User search the 'Test apps' in the home search box
    And User clicks on the 'Apps' option to filter the results
    Then User can see 'Test apps' in the 'Apps' filter results

  @DeleteTestCatalog
  Scenario: Search model
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters 'Test Model' as Catalog name
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User clicks On Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'Test Model' in the home search box
    And User clicks on the 'Model' option to filter the results
    Then User can see 'Test Model' in the 'Model' filter results

  Scenario: Search Function
    Given User opens Main Menu
    When User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button
    And User clicks On Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'weatherFunctionTest' in the home search box
    And User clicks on the 'Function' option to filter the results
    Then User can see 'weatherFunctionTest' in the 'Function' filter results

  Scenario: Search Vector
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters 'Test Model' as Catalog name
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User clicks On Copy Catalog ID
    When User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button
    When User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters 'Test Vector' as Catalog name
    And User selects 'Test Model' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '512'
    And User enters value of Content Overlap as '20'
    And User clicks on Create Vector button
    And User clicks On Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'Test Vector' in the home search box
    And User clicks on the 'Vector' option to filter the results
    Then User can see 'Test Vector' in the 'Vector' filter results

  Scenario: Search Database
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User clicks On Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'TestDatabase' in the home search box
    And User clicks on the 'Database' option to filter the results
    Then User can see 'TestDatabase' in the 'Database' filter results

  Scenario: Search Storage
    Given User opens Main Menu
    When User clicks on Open Storage
    And User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters 'Test Storage' as Catalog name
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User clicks on Create Storage button
    And User clicks On Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'Test Storage' in the home search box
    And User clicks on the 'Storage' option to filter the results
    Then User can see 'Test Storage' in the 'Storage' filter results

  Scenario Outline: Search All
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Home
    And User search the '<CATALOG NAME>' in the home search box
    And User clicks on the '<OPTION>' option to filter the results
    Then User can see '<CATALOG NAME>' in the '<OPTION>' filter results

    Examples: 
      | CATALOG NAME        | OPTION |
      | Test apps           | All    |
      | Test Model          | All    |
      | weatherFunctionTest | All    |
      | Test Vector         | All    |
      | TestDatabase        | All    |
      | Test Storage        | All    |

  @DeleteTestCatalog @DeleteCreatedTestApp
  Scenario: Delete created resources
    Given User opens Main Menu
    When User clicks on Home
