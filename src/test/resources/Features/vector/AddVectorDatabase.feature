@LoginWithAuthor
Feature: Add Vector Database
  I want to use this feature file for all the scenarios related to Create Vector Database

  Background: Login to the application and Create model tagged with embeddings
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT 3.5 Turbo'
    And User enters Catalog Name as 'Catalog'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    #Then User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks on Copy Catalog ID
    When User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button

  @DeleteTestCatalog @Regression
  Scenario Outline: Add and validate FAISS Vector database '<chunking_strategy>'
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects '<connection>' connection
    And User enters vector database Catalog name as '<catalog_name>'
    And User selects '<model_name>' from Embedder field
    And User selects '<chunking_strategy>' from Chunking Strategy field
    And User enters value of Content Length as '<content_length>'
    And User enters value of Content Overlap as '<content_overlap>'
    And User clicks on Create Vector button
    And User clicks on Copy Catalog ID
    #Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User can see the Vector title as '<catalog_name>'
    When User clicks on SMSS
    Then User can see vector catalog name in 'NAME' field as '<catalog_name>' in SMSS properties
    And User can see embedder engine name in 'EMBEDDER_ENGINE_NAME' field as '<model_name>' in SMSS properties
    And User can see content length in 'CONTENT_LENGTH' field as '<content_length>' in SMSS properties
    And User can see content overlap in 'CONTENT_OVERLAP' field as '<content_overlap>' in SMSS properties
    And User can see chunking strategy in 'CHUNKING_STRATEGY' field as '<chunking_strategy>' in SMSS properties

    Examples: 
      | connection | catalog_name      | model_name | chunking_strategy | content_length | content_overlap |
      | FAISS      | FAISS Vector DB01 | Catalog    | Token             |            510 |              17 |
      | FAISS      | FAISS Vector DB02 | Catalog    | Page by page      |            512 |              19 |
      | FAISS      | FAISS Vector DB03 | Catalog    | Markdown          |            512 |              15 |

  #Note: For 'Page by page' and 'Markdown' chunking strategies, the Content Length defaults to '512' as the field is not present
  @DeleteTestCatalog @Regression
  Scenario: Validate usage of vector
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISS Vector DB00'
    And User selects 'Catalog' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    And User clicks on Copy Catalog ID
    #Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User can see the Vector title as 'FAISS Vector DB00'
    When User clicks on Usage tab for Vector DB
    Then User sees an example of "How to use in Javascript" with example code for Vector DB
    And User sees an example of "How to use in Python" with example code for Vector DB
    And User sees an example of "How to use with Langchain API" with example code for Vector DB
    And User sees an example of "How to use in Java" with example code for Vector DB

  @DeleteTestCatalog @Regression
  Scenario Outline: Validate Change access popup
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects '<connection>' connection
    And User enters vector database Catalog name as '<catalog_name>'
    And User selects '<model_name>' from Embedder field
    And User selects '<chunking_strategy>' from Chunking Strategy field
    And User enters value of Content Length as '<content_length>'
    And User enters value of Content Overlap as '<content_overlap>'
    And User clicks on Create Vector button
    #Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User clicks on Copy Catalog ID
    And 'Admin' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as "Editor"
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the '<catalog_name>' in the Vector Catalog searchbox
    And User selects the '<catalog_name>' from the Vector catalog
    And User click on the Change Access button
    And User should see the "Change Access" popup with following options:
      | Author         |
      | Editor         |
      | Read-Only      |
      | Comment Box    |
      | Cancel Button  |
      | Request Button |
    And User click on cancel button
    And User logs out from the application
    Then User login as "Author"

    Examples: 
      | connection | catalog_name      | model_name | chunking_strategy | content_length | content_overlap |
      | FAISS      | FAISS Vector DB01 | Catalog    | Token             |            510 |              17 |

  @DeleteTestCatalog @Regression
  Scenario Outline: Validate change access request
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects '<connection>' connection
    And User enters vector database Catalog name as '<catalog_name>'
    And User selects '<model_name>' from Embedder field
    And User selects '<chunking_strategy>' from Chunking Strategy field
    And User enters value of Content Length as '<content_length>'
    And User enters value of Content Overlap as '<content_overlap>'
    And User clicks on Create Vector button
    #Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User clicks on Copy Catalog ID
    And 'Author' user clicks on Settings
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as "Editor"
    When User opens Main Menu
    And User clicks on Open Vector
    And User searches the '<catalog_name>' in the Vector Catalog searchbox
    And User selects the '<catalog_name>' from the Vector catalog
    And User click on the Change Access button
    And User selects 'author' access
    And User types a comment as 'Access Request'
    And User clicks on Request button

    #Then User should successfully request access given the Vector is requestable with a toast message as 'Successfully requested access to engine'
    Examples: 
      | connection | catalog_name      | model_name | chunking_strategy | content_length | content_overlap |
      | FAISS      | FAISS Vector DB01 | Catalog    | Token             |            510 |              17 |
