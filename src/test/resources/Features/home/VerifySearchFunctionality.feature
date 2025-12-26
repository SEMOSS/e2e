Feature: Search app and catalogs

  @LoginWithAdmin @Regression
  Scenario: Search App
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters 'App' as app name
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User opens Main Menu
    And User clicks on Home
    And User search the 'App' in the home search box
    And User clicks on the 'Apps' option to filter the results
    Then User can see 'App' in the 'Apps' filter results
    And User clicks on the 'Apps' option to unfilter the results
    And User close the search popup

  @DeleteTestCatalog @Regression
  Scenario: Search model
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters 'Model' as Catalog name
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'Model' in the home search box
    And User clicks on the 'Model' option to filter the results
    Then User can see 'Model' in the 'Model' filter results
    And User clicks on the 'Model' option to unfilter the results
    And User close the search popup

  @Regression
  Scenario: Search Function
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Open Function
    And User checks if 'Function' catalog created and Deletes the 'WeatherFunctionTest'
    And User clicks on Add Function
    And User selects the 'ZIP' option to upload file
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'weatherFunctionTest' in the home search box
    And User clicks on the 'Function' option to filter the results
    Then User can see 'WeatherFunctionTest' in the 'Function' filter results
    And User clicks on the 'Function' option to unfilter the results
    And User close the search popup

  @Regression
  Scenario: Search Vector
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters 'Model' as Catalog name
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    When User clicks on Edit button
    And User add Tags 'embeddings' and presses Enter
    And User clicks on Submit button
    When User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters 'Vector' as Catalog name
    And User selects 'Model' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '512'
    And User enters value of Content Overlap as '20'
    And User clicks on Create Vector button
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'Vector' in the home search box
    And User clicks on the 'Vector' option to filter the results
    Then User can see 'Vector' in the 'Vector' filter results
    And User clicks on the 'Vector' option to unfilter the results
    And User close the search popup

  @Regression
  Scenario: Search Database
    Given User is on Home page
    And User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    When User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'TestDatabase' in the home search box
    And User clicks on the 'Database' option to filter the results
    Then User can see 'TestDatabase' in the 'Database' filter results
    And User clicks on the 'Database' option to unfilter the results
    And User close the search popup

  @Regression
  Scenario: Search Storage
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Open Storage
    And User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters 'Storage' as Catalog name
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User clicks on Create Storage button
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Home
    And User search the 'Storage' in the home search box
    And User clicks on the 'Storage' option to filter the results
    Then User can see 'Storage' in the 'Storage' filter results
    And User clicks on the 'Storage' option to unfilter the results
    And User close the search popup

  @Regression
  Scenario Outline: Search All
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Home
    And User search the '<CATALOG NAME>' in the home search box
    #And User clicks on the '<OPTION>' option to filter the results
    Then User can see '<CATALOG NAME>' in the '<OPTION>' filter results
    #And User clicks on the '<OPTION>' option to unfilter the results
    And User close the search popup

    Examples: 
      | CATALOG NAME        | OPTION |
      | App                 | All    |
      | Model               | All    |
      | WeatherFunctionTest | All    |
      | Vector              | All    |
      | TestDatabase        | All    |
      | Storage             | All    |

  @DeleteTestCatalog @DeleteCreatedTestApp @Regression
  Scenario: Delete created resources
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Home
