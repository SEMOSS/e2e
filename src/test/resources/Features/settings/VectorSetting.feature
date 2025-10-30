
Feature: Search Vector Settings

  Background: Login to the application and Create model tagged with embeddings
    Given User opens Main Menu
    Given User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog Name as 'Catalog'
    And User enters Open AI Key as 'Test@1234'
    And User enters Variable Name as 'Variable1'
    And User clicks on Create Model button
    Then User can see a toast message as 'Successfully added LLM to catalog'
    When User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button

  @LoginWithAdmin @DeleteCreatedCatalog @Regression @Smoke
  Scenario Outline: Create vector
    Given User opens Main Menu
    And User clicks on Open Vector
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

    Examples: 
      | connection | catalog_name      | model_name | chunking_strategy | content_length | content_overlap |
      | FAISS      | FAISS Vector DB01 | Catalog    | Token             |            510 |              17 |
      | FAISS      | FAISS Vector DB02 | Catalog    | Page by page      |            512 |              19 |
      | FAISS      | FAISS Vector DB03 | Catalog    | Markdown          |            512 |              15 |

  @DeleteCreatedCatalog @Regression @Smoke
  Scenario Outline: Validate Search Functionality
  Given User opens Main Menu
    And User clicks on Open Vector
    When User clicks on Add Vector button
    And User selects '<connection>' connection
    And User enters vector database Catalog name as '<catalog_name>'
    And User selects '<model_name>' from Embedder field
    And User selects '<chunking_strategy>' from Chunking Strategy field
    And User enters value of Content Length as '<content_length>'
    And User enters value of Content Overlap as '<content_overlap>'
    And User clicks on Create Vector button
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Vector Settings' Card
    Then User sees title as 'Vector Settings'
    And User sees the vector cards
    And User sees the search bar
    And User searches for the vector '<catalog_name>'
    And User sees the '<catalog_name>' in the searched vector list
    And User opens Main Menu
    And User clicks on Open Vector
     And User searches the '<catalog_name>' in the Vector Catalog searchbox
    And User selects the '<catalog_name>' from the Vector catalog

    Examples: 
      | connection | catalog_name      | model_name | chunking_strategy | content_length | content_overlap |
      | FAISS      | FAISS Vector DB01 | Catalog    | Token             |            510 |              17 |
      | FAISS      | FAISS Vector DB02 | Catalog    | Page by page      |            512 |              19 |
      | FAISS      | FAISS Vector DB03 | Catalog    | Markdown          |            512 |              15 |
