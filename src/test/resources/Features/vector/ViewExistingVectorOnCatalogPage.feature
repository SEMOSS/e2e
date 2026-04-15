Feature: View existing Vectors on Vector Catalog Page

  Background: Create and edit Vector
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT-4.1'
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
    And User enters vector database Catalog name as 'FAISS Vector'
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
    And User searches the 'FAISS Vector' in the Vector Catalog searchbox
    Then User applies each filter and validate 'FAISS Vector' catalog is visible on the 'vector' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | IP                |
      | Data Restrictions   | IP ALLOWED        |
    When User clicks on bookmark button of 'FAISS Vector' catalog
    Then User sees the catalog name 'FAISS Vector' in the Bookmarked section
    When User clicks on bookmark button to unbookmark 'FAISS Vector' catalog

  @DeleteTestCatalog @Regression @LoginWithAdmin
  Scenario: view and validate filter functionality - Discoverable Vectors
    Given User opens Main Menu
    When User clicks on Open Vector
    And User searches the 'FAISS Vector' in the Vector Catalog searchbox
    And User selects the 'FAISS Vector' from the Vector catalog
    And User clicks on Access Control Tab
    And User clicks Make 'Vector' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the 'FAISS Vector' in the Vector Catalog searchbox
    And User clicks on Discoverable Vectors button
    And User applies each filter and validate 'FAISS Vector' catalog is visible on the 'vector' catalog page
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
