Feature: Vector Overview

  Background: Validate usage of storage
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    #And User can see a toast message as 'Successfully added LLM to catalog'
     And User clicks On Copy Catalog ID
    And User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button
    And User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISS Vector DB00'
    And User selects 'Catalog' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    #Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User can see the Vector title as 'FAISS Vector DB00'

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: Validate vector overview page
    Given User can see the Vector title as 'FAISS Vector DB00'
    When User clicks on Edit button
    And User add tags 'TestTag' and presses Enter
    And User clicks on Submit button
    And 'Admin' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    And User login as "Editor"
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the 'FAISS Vector DB00' in the Vector Catalog searchbox
    And User clicks on the created Vector card name as 'FAISS Vector DB00'
    Then User can see the Vector title as 'FAISS Vector DB00'
    And User sees and copies the vector id
    #And User sees the copied success toast message 'Successfully copied ID'
    # And User sees a description for this Vector
    # Note: Description is not visible in the UI, so this step is commented out
    And User sees Tags 'TestTag' that have been added to the Vector
    And User sees the Change Access button
    # And User sees Updated By as 'Admin' and Updated At as current date
    # Note: Updated By and Updated At are not visible in the UI, so this step is commented out
