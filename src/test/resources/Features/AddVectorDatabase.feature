Feature: Create Vector Database
  I want to use this feature file for all the scenarios related to Create Vector Database

  Background: Login to the application and Create model tagged with embeddings
    Given User navigates to Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    Then User can see a toast message as 'Successfully added LLM to catalog'
    When User clicks on Edit button
    And User enters tag as 'embeddings' in Edit Model Details and press enter
    And User clicks on Submit button
@LoginWithAdmin
  Scenario Outline: Add and validate FAISS Vector database '<chunking_strategy>'
    Given User clicks on Open Vector engine
    When User clicks on Add Vector button
    And User selects '<connection>' connection
    And User enters vector database Catalog name as '<catalog_name>'
    And User selects '<model_name>' from Embedder field
    And User selects '<chunking_strategy>' from Chunking Strategy field
    And User enters value of Content Length as '<content_length>'
    And User enters value of Content Overlap as '<content_overlap>'
    And User clicks on Create Vector button
    Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User can see the Vector title as '<catalog_name>'
    When User clicks on SMSS
    Then User can see vector catalog name in 'NAME' field as '<catalog_name>' in SMSS properties
    And User can see embedder engine name in 'EMBEDDER_ENGINE_NAME' field as '<model_name>' in SMSS properties
    And User can see content length in 'CONTENT_LENGTH' field as '<content_length>' in SMSS properties
    And User can see content overlap in 'CONTENT_OVERLAP' field as '<content_overlap>' in SMSS properties
    And User can see chunking strategy in 'CHUNKING_STRATEGY' field as '<chunking_strategy>' in SMSS properties
    And User navigates to Open Model

    Examples: 
      | connection | catalog_name    | model_name | chunking_strategy | content_length | content_overlap |
      | FAISS      | FAISS Vector DB | Catalog    | Token             |            510 |              17 |
      #| FAISS      | FAISS Vector DB | Catalog    | Page by page      |            512 |              19 |
      #| FAISS      | FAISS Vector DB | Catalog    | Markdown          |            512 |              15 |
#Note: For 'Page by page' and 'Markdown' chunking strategies, the Content Length defaults to '512' as the field is not present




