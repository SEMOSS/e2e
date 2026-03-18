@LoginWithAdmin @DeleteTestCatalog @Regression
Feature: Team Permissions

  Background: Team Permissions - Add team
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User clicks on "Add Team" button
    And User selects type as "Custom" from Type dropdown
    And User fills "Test Team" in Name field of Add Team form
    And User fills Description as "Test Description" in Description field of Add Team form
    And User clicks on "Add" button in Add Team form
    Then User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    Then User can see team name as "Test Team" in the list
    Then User can see description as "Test Description" in the list

  Scenario: Database Catalog - Verify team is displayed in Catalog team section after adding engine to team
    Given User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    Then User sees success toast message 'Successfully Created Database'
    And User can see the Catalog title as 'TestDatabase'
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Engine' button in Team Permission page
    And User select the 'TestDatabase' in the 'Select Engine' field of Add Engine form the 'Database'
    And User select the engine access as 'Editor'
    And User clicks on save button
    And User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'TestDatabase' in the engine list with access as 'Editor'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog
    And 'Admin' user clicks on Access Control
    Then User sees the team 'Test Team' with 'Editor' in Team section on the the Access Settings page
    And  User verifies the engine added time matches catalog time for 'Test Team'

  Scenario: Model Catalog - Verify team is displayed in Catalog team section after adding engine to team
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters Catalog Name as 'ModelTest'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    Then User can see the Model title as 'ModelTest'
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Engine' button in Team Permission page
    And User select the 'ModelTest' in the 'Select Engine' field of Add Engine form the 'Model'
    And User select the engine access as 'Editor'
    And User clicks on save button
    And User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'ModelTest' in the engine list with access as 'Editor'
    And User opens Main Menu
    And User clicks on Open Model
    And User searches the 'ModelTest' in the model catalog searchbox
    And User selects the 'ModelTest' from the model catalog
    And 'Admin' user clicks on Access Control
    Then User sees the team 'Test Team' with 'Editor' in Team section on the the Access Settings page
		And  User verifies the engine added time matches catalog time for 'Test Team'
		
  Scenario: Vector Catalog - Verify team is displayed in Catalog team section after adding engine to team
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters Catalog Name as 'Catalog'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    And User clicks on Edit button
    And User add Tags 'embeddings' and presses Enter
    And User clicks on Submit button
    And User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISS Vector'
    And User selects 'Catalog' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User clicks on Copy Catalog ID
    And User can see the Vector title as 'FAISS Vector'
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Engine' button in Team Permission page
    And User select the 'FAISS Vector' in the 'Select Engine' field of Add Engine form the 'Vector'
    And User select the engine access as 'Editor'
    And User clicks on save button
    And User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'FAISS Vector' in the engine list with access as 'Editor'
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the 'FAISS Vector' in the Vector Catalog searchbox
    And User clicks on the created Vector card name as 'FAISS Vector'
    And 'Admin' user clicks on Access Control
    Then User sees the team 'Test Team' with 'Editor' in Team section on the the Access Settings page
    And  User verifies the engine added time matches catalog time for 'Test Team'

  Scenario: Storage Catalog - Verify team is displayed in Catalog team section after adding engine to team
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
    And User clicks on Connect button to create storage
    And User clicks on Copy Catalog ID
    Then User can see create storage success toast message as 'Successfully added new storage to catalog'
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Engine' button in Team Permission page
    And User select the 'Amazon S3 Storage' in the 'Select Engine' field of Add Engine form the 'Storage'
    And User select the engine access as 'Author'
    And User clicks on save button
    And User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'Amazon S3 Storage' in the engine list with access as 'Author'
    And User opens Main Menu
    And User clicks on Open Storage
    And User searches the 'Amazon S3 Storage' in the storage Catalog searchbox
    And User selects the 'Amazon S3 Storage' from the storage catalog
    And 'Admin' user clicks on Access Control
    Then User sees the team 'Test Team' with 'Author' in Team section on the the Access Settings page
		And  User verifies the engine added time matches catalog time for 'Test Team'
		
  Scenario: Function Catalog - Verify team is displayed in Catalog team section after adding engine to team
    Given User opens Main Menu
    When User clicks on Open Function
    And User checks if 'Function' catalog created and Deletes the 'WeatherFunctionTest'
    And User clicks on Add Function
    And User clicks on file upload icon
    And User uploads the file 'Function/weatherFunctionTest.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully Created Function Database'
    And User can see the Catalog title as 'WeatherFunctionTest'
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User can see team name as "Test Team" in the list
    And User clicks on the team name 'Test Team' in the list
    And User clicks on 'Add Engine' button in Team Permission page
    And User select the 'weatherFunctionTest' in the 'Select Engine' field of Add Engine form the 'Function'
    And User select the engine access as 'Author'
    And User clicks on save button
    And User sees the message 'Successfully added engine permission' is displayed
    And User see the added 'WeatherFunctionTest' in the engine list with access as 'Author'
    And User opens Main Menu
    And User clicks on Open Function
    And User searches the 'WeatherFunctionTest' in the function Catalog searchbox
    And User selects the 'WeatherFunctionTest' from the function catalog
    And 'Admin' user clicks on Access Control
    Then User sees the team 'Test Team' with 'Author' in Team section on the the Access Settings page
    And  User verifies the engine added time matches catalog time for 'Test Team'
