Feature: Embed file into the Vector

  @LoginWithAdmin
  Scenario Outline: Add Model and Vector database
    Given User navigates to Open Model
    And User clicks on Add Model
    And User selects 'ZIP'
    And User uploads a file 'pinecone_model.zip'
    And User clicks on Create Model button
    Then User clicks on Open Vector engine
    When User clicks on Add Vector button
    And User selects '<connection>' connection
    And User enters vector database Catalog name as '<catalog_name>'
    And User selects '<model_name>' from Embedder field
    And User selects '<chunking_strategy>' from Chunking Strategy field
    And User enters value of Content Length as '<content_length>'
    And User enters value of Content Overlap as '<content_overlap>'
    And User enters value of Host Name
    And User enters value of API Key
    And User enters value of Namespace as '<Namespace>'
    And User clicks on Create Vector button
    Then User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User can see the Vector title as '<catalog_name>'

    Examples: 
      | connection | catalog_name       | model_name                        | chunking_strategy | content_length | content_overlap | Namespace |
      | Pinecone   | Pinecone Vector DB | TextEmbeddings BAAI-Large-En-V1.5 | Token             |            510 |              17 | Default   |

  @LoginWithAdmin
  Scenario Outline: Embed a document
    Given User clicks on Open Vector engine
    When User clicks on the created Vector card name as '<catalog_name>'
    Then User clicks on files
    Then User clicks on Embed New Document
    And User uploads a file 'test1.pdf'
    Then User clicks on Embed button
    And User sees file embeded success toast message 'Successfully added document'
    And User sees file named 'test1.pdf' in the file list
    And User sees date of uploaded in the file list
    And User sees file size '13 KB' in the file list
    And User sees delete icon in the file list
    And User clicks on Open Vector engine

    Examples: 
      | catalog_name       |
      | Pinecone Vector DB |

  @LoginWithAdmin
  Scenario Outline: Delete vector and model
    Given User clicks on Open Vector engine
    When User clicks on the created Vector card name as '<catalog_name>'
    And User clicks on Access Control
    And User clicks on delete icon
    And User sees deleted Vector success toast message 'Successfully deleted Vector'
    Then User navigates to Open Model
    And User clicks on the created Model card name as '<model_name>'
    And User clicks on Access Control
    And User clicks on delete icon
    And User sees deleted Model success toast message 'Successfully deleted Model'

    Examples: 
      | catalog_name       | model_name                        |
      | Pinecone Vector DB | TextEmbeddings BAAI-Large-En-V1.5 |
