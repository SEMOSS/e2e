Feature: View existing Vectors on Vector Catalog Page

  Background: Create and edit Vector
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters Catalog Name as 'ModelCatalog'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    And User clicks on Edit button
    And User add Tags 'embeddings' and presses Enter
    And User clicks on Submit button
    When User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISSVector'
    And User selects 'ModelCatalog' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    And User clicks on Copy Catalog ID
    And User clicks on Edit button
    And User add Tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'IP, PHI' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: view and validate filter functionality - My Vectors
    Given User opens Main Menu
    When User clicks on Open Vector
    And User searches the 'FAISSVector' in the Vector Catalog searchbox
    Then User applies each filter and validate 'FAISSVector' catalog is visible on the 'vector' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |
    #When User clicks on bookmark button of 'FAISSVector' catalog
    #Then User sees the catalog name 'FAISSVector' in the Bookmarked section
    #When User clicks on bookmark button to unbookmark 'FAISSVector' catalog

  @DeleteTestCatalog @Regression  @LoginWithAdmin
  Scenario: view and validate filter functionality - Discoverable Vectors
    Given User opens Main Menu
    When User clicks on Open Vector
    And User searches the 'FAISSVector' in the Vector Catalog searchbox
    And User selects the 'FAISSVector' from the Vector catalog
    And User clicks on Access Control Tab
    And User clicks Make 'Vector' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the 'FAISSVector' in the Vector Catalog searchbox
    And User clicks on Discoverable Vectors button
    And User applies each filter and validate 'FAISSVector' catalog is visible on the 'vector' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE |
      | Data Classification | IP           |
      | Data Restrictions   | IP ALLOWED   |
    And User logs out from the application
    And User login as 'Admin'

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate access status of created Vector catalog
    Given User opens Main Menu
    When User clicks on Open Vector
    And User searches the 'FAISS Vector' in the Vector Catalog searchbox
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Private' on the tooltip
    When User selects the 'FAISS Vector' from the Vector catalog
    And User clicks on Access Control Tab
    And User clicks on make 'Vector' public button
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the 'FAISS Vector' in the Vector Catalog searchbox
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Global' on the tooltip

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate content of created Vector catalog card
    When User get the catalog ID
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the 'FAISS Vector' in the Vector Catalog searchbox
    And User should see the catalog ID on the catalog card
    And User should see the tags 'embeddings, Test1' on the 'Vector' catalog card
    And User should see the catalog created date on the catalog card
    And User should see the following icons on the catalog card
      | lock                |
      | bookmark            |
      | view logs dashboard |
      | delete              |
