 Feature: Vector Page Filters

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: Validate the Vector filter on app
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT-4.1'
    And User enters Catalog Name as 'Ascending Model'
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
    And User enters vector database Catalog name as 'Ascending FAISS Vector DB00'
    And User selects 'Ascending Model' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'Descending FAISS Vector DB00'
    And User selects 'Ascending Model' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    And User clicks on Open Vector
    And User clicks on the 'Ascending' Filter button
    Then User can see the "Vector" are sorted in ascending order
    When User clicks on the 'Descending' Filter button
    Then User can see the "Vector" are sorted in descending order
    And User opens Main Menu
    And User clicks on Open Vector
    When User selects 'Date Created' from the Sort By dropdown
    And User clicks on the 'Ascending' Filter button
    Then User can see the 'Vector' are sorted by date created in 'ascending' order
    When User clicks on the 'Descending' Filter button
    Then User can see the 'Vector' are sorted by date created in 'descending' order