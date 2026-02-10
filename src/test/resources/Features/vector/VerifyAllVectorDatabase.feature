Feature: Verify All Vector Database
  I want to use this feature file for all the scenarios related to Verify Vector Database

  #@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
  #Scenario: Create Vector Database using ZIP file
  #Given User is on Home page
  #When User opens Main Menu
  #And User clicks on Open Vector
  #And User clicks on Add Vector button
  #And User clicks on file upload icon
  #And User uploads the file 'VectorDatabase/TestVector.zip'
  #And User clicks on 'Upload' button to create catalog
  ##And User sees success toast message 'Successfully Created Vector Database'
  #And User clicks on Copy Catalog ID
  #And User can see the Catalog title as 'TestVector'
  
  @LoginWithAdmin @Regression @Smoke
  Scenario Outline: Verify existing Vector Databases in the application
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Add Vector button
    And User selects vector database '<VECTORDATABASE>' from connection types
    Then User can see vector Database form sections with fields:
      | SECTION_NAME | FIELDS      |
      | <S1_NAME>    | <S1_FIELDS> |
      | <S2_NAME>    | <S2_FIELDS> |
      | <S3_NAME>    | <S3_FIELDS> |
      | <S4_NAME>    | <S4_FIELDS> |
    And User can see vector database mandatory fields
      | <MANDATORY_FIELDS> |

    Examples: 
      | VECTORDATABASE  | S1_NAME | S1_FIELDS              | S2_NAME  | S2_FIELDS                                                                                | S3_NAME     | S3_FIELDS                       | S4_NAME           | S4_FIELDS                                                                                                                               | MANDATORY_FIELDS                                                                                                                                                                                                                                   |
      | Azure AI Search | General | Catalog Name, Embedder | Settings | Chunking Strategy, Content Length, Content Overlap, Index Name, Embedding Dimension Size | Credentials | Host Name, API Key, API Version | ADVANCED SETTINGS | Method Name, Space Type, Index Engine, EF Construction, M Value, Record Questions and Responses, Distance Method, Retain Extracted Text | Catalog Name, Host Name, Method Name, Space Type, Index Engine, EF Construction, M Value, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Index Name, Embedding Dimension Size, API Key, API Version |
      #| Chroma          | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses                                                                                | Credentials | Host Name, API Key, Collection Name                     | ADVANCED SETTINGS | Distance Method                                                                                                                         | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Collection Name                                                                                                             |
      #| Elastic Search  | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses, Index Name                                                                    | Credentials | Host Name, API Key, API Key ID, Username, Password      | ADVANCED SETTINGS | Distance Method, Method Name, Space Type, Index Engine, EF Construction, M Value, Embedding Dimension Size                              | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Method Name, Space Type, Index Engine, EF Construction, M Value, Embedding Dimension Size                                   |
      #| Chroma          | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses                                                                                | Credentials | Host Name, API Key, Collection Name                     | ADVANCED SETTINGS | Distance Method                                                                                                                         | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Collection Name                                                                                                             |
      #| Milvus          | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Embedding Dimension Size                                                                                      | Credentials | Host Name, API Key, Collection, Database                | ADVANCED SETTINGS | Distance Method, Index Type, EF Construction, M Value, Record Questions and Responses                                                   | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Collection, Database, API Key                                                                                               |
      #| Open Search     | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Embedding Dimension Size, Record Questions and Responses                                                      | Credentials | Host Name, Index Name, Username, Password               | ADVANCED SETTINGS | Distance Method, Index Engine, EF Construction, M Value, Method Name                                                                    | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Username, Password, Index Name, Index Engine, EF Construction, M Value, Method Name                                         |
      #| PGVector        | General | Catalog Name, Description, Tags | Settings | Embedder, Host Name, Port, Database, PGVector Table Name, Schema, Chunking Strategy, Content Length, Content Overlap, Additional Parameters, Record Questions and Responses | Credentials | JDBC Url, Username, Password                            | ADVANCED SETTINGS | Distance Method, Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size                                                           | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Database, PGVector Table Name                                                                                               |
      #| Weaviate        | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses                                                                                | Credentials | Host Name, API Key, Weaviate Classname, Autocut default | ADVANCED SETTINGS | Distance Method                                                                                                                         | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, API Key, Weaviate Classname, Autocut default                                                                                |
#
#@LoginWithAdmin @Regression @Smoke
  #Scenario Outline: Verify '<VECTORDATABASE>' Vector Databases in the application
    #Given User is on Home page
    #When User opens Main Menu
    #And User clicks on Open Vector
    #And User clicks on Add Vector button
    #And User selects vector database '<VECTORDATABASE>' from connection types
    #Then User can see vector Database form sections with fields:
      #| SECTION_NAME | FIELDS      |
      #| <S1_NAME>    | <S1_FIELDS> |
      #| <S2_NAME>    | <S2_FIELDS> |
      #| <S3_NAME>    | <S3_FIELDS> |
    #And User can see vector database mandatory fields
      #| <MANDATORY_FIELDS> |
#
    #Examples:
      #| VECTORDATABASE | S1_NAME | S1_FIELDS                       | S2_NAME  | S2_FIELDS                                                                                    | S3_NAME           | S3_FIELDS                     | MANDATORY_FIELDS                                                                                                                          |
      #| FAISS          | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses | ADVANCED SETTINGS | Distance Method               | Catalog Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap,                               |
      #| Pinecone       | General | Catalog Name, Description, Tags | Settings | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses | Credentials       | Host Name, API Key, Namespace | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Namespace, API Key |
