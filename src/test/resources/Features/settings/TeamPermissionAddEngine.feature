Feature: Add Engine for Team Permission

  Background: Team Permissions - Add team
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User clicks on 'Add Team' button
    And User selects type as 'Custom' from Type dropdown
    And User fills 'Test Team' in Name field of Add Team form
    And User fills Description as 'Test Description' in Description field of Add Team form
    And User clicks on 'Add' button in Add Team form

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario Outline: Add Engine for Database Users role
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees success toast message 'ZIP uploaded successfully'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks On Copy Catalog ID
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    Then User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    When User clicks on 'Add Engine' button in Team Permission page
    And User select the 'TestDatabase' in the 'Select Engine' field of Add Engine form the 'Database'
    And User select the engine access as '<Role>'
    And User clicks on save button
    Then User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'TestDatabase' in the engine list with access as '<Role>'

    Examples: 
      | Role      |
      | Author    |
      | Editor    |
      | Read-Only |

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario Outline: Add Engine for Function Users role
    Given User opens Main Menu
    When User clicks on Open Function
    And User clicks on Add Function
    And User selects function 'ZIP'
    And User uploads function file 'Function/weatherFunctionTest.zip'
    And User clicks on Create Function button
    And User clicks On Copy Catalog ID
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    Then User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    When User clicks on 'Add Engine' button in Team Permission page
    And User select the 'WeatherFunctionTest' in the 'Select Engine' field of Add Engine form the 'Function'
    And User select the engine access as '<Role>'
    And User clicks on save button
    Then User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'WeatherFunctionTest' in the engine list with access as '<Role>'

    Examples: 
      | Role      |
      | Author    |
      | Editor    |
      | Read-Only |

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario Outline: Add Engine for Storage Users role
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Storage
    When User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters storage Catalog name as 'Amazon S3 Storage'
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User clicks On Copy Catalog ID
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    Then User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    When User clicks on 'Add Engine' button in Team Permission page
    And User select the 'Amazon S3 Storage' in the 'Select Engine' field of Add Engine form the 'Storage'
    And User select the engine access as '<Role>'
    And User clicks on save button
    Then User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'Amazon S3 Storage' in the engine list with access as '<Role>'

    Examples: 
      | Role      |
      | Author    |
      | Editor    |
      | Read-Only |

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario Outline: Add Engine for Vector Users role
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    And User clicks On Copy Catalog ID
    When User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISS Vector DB01'
    And User selects 'Catalog' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User can see the Vector title as 'FAISS Vector DB01'
    And User clicks On Copy Catalog ID
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    Then User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    When User clicks on 'Add Engine' button in Team Permission page
    And User select the 'FAISS Vector DB01' in the 'Select Engine' field of Add Engine form the 'Vector'
    And User select the engine access as '<Role>'
    And User clicks on save button
    Then User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'FAISS Vector DB01' in the engine list with access as '<Role>'

    Examples: 
      | Role      |
      | Author    |
      | Editor    |
      | Read-Only |
