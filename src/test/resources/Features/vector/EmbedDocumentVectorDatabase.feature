######### NEED to set up pinecone in docker

#Feature: Embed file into the Vector Database
#
  #@LoginWithAdmin
  #Scenario Outline: Add Model and Vector database
    #Given User navigates to Open Model
    #When User clicks on Add Model
    #Then User selects 'ZIP'
    #And User uploads a file '<file_Name>'
    #And User clicks on Create Model button
    #And User clicks on Open Vector engine
    #And User clicks on Add Vector button
    #And User selects '<connection>' connection
    #And User enters vector database Catalog name as '<catalog_name>'
    #And User selects '<model_name>' from Embedder field
    #And User selects '<chunking_strategy>' from Chunking Strategy field
    #And User enters value of Content Length as '<content_length>'
    #And User enters value of Content Overlap as '<content_overlap>'
    #And User enters value of Host Name
    #And User enters value of API Key
    #And User enters value of Namespace as '<Namespace>'
    #And User clicks on Create Vector button
    #And User can see vector database created success toast message as 'Successfully added vector database to catalog'
    #And User can see the Vector title as '<catalog_name>'
#
    #Examples: 
      #| connection | catalog_name       | model_name                        | chunking_strategy | content_length | content_overlap | Namespace | file_Name                               |
      #| Pinecone   | Pinecone Vector DB | TextEmbeddings BAAI-Large-En-V1.5 | Token             |            510 |              17 | Default   | VectorDatabase/Text_Embedding_model.zip |
#
  #@LoginWithAdmin
  #Scenario Outline: Embed a document in Vector DB
    #Given User clicks on Open Vector engine
    #When User clicks on the created Vector card name as '<catalog_name>'
    #And User clicks on files
    #And User clicks on Embed New Document
    #And User uploads a file '<file_Name>'
    #And User clicks on Embed button
    #Then User sees file embeded success toast message 'Successfully added document'
    #And User sees file named '<file_Name>' in the file list
    #And User sees date of upload in the file list
    #And User sees file size '<file_Size>' in the file list
    #And User sees delete icon in the file list
    #And User clicks on Open Vector engine
#
    #Examples: 
      #| catalog_name       | file_Name                            | file_Size |
      #| Pinecone Vector DB | VectorDatabase/Vector_Embed_file.pdf | 13 KB     |
#
  #@LoginWithAdmin
  #Scenario Outline: Verifying Document List in Vector Database using Pixel
    #Given User clicks on Open Vector engine
    #When User clicks on the created Vector card name as '<catalog_name>'
    #Then User can see the Vector title as '<catalog_name>'
    #And User copies the vector id
    #And User sees the copied success toast message 'Successfully copied ID'
    #And User navigates to Open App Library
    #And User clicks on '<tab_Name>' app tab
    #And User clicks on Terminal card
    #And User run pixel command '<pixel_Command>'
    #And User sees Filename in the output as '<file_Name>'
    #And User sees Filesize in the output as '<file_Size>'
    #And User sees Last modified date in the output
#
    #Examples: 
      #| catalog_name       | file_Name                            | file_Size | pixel_Command                                           | tab_Name    |
      #| Pinecone Vector DB | VectorDatabase/Vector_Embed_file.pdf |        13 | ListDocumentsInVectorDatabase (engine = "{VECTOR_ID}"); | System Apps |
#
  #@LoginWithAdmin
  #Scenario Outline: Delete created vector and model
    #Given User clicks on Open Vector engine
    #When User clicks on the created Vector card name as '<catalog_name>'
    #Then User clicks on Access Control
    #And User clicks on delete icon
    #And User sees deleted Vector success toast message 'Successfully deleted Vector'
    #And User navigates to Open Model
    #And User clicks on the created Model card name as '<model_name>'
    #And User clicks on Access Control
    #And User clicks on delete icon
    #And User sees deleted Model success toast message 'Successfully deleted Model'
#
    #Examples: 
      #| catalog_name       | model_name                        |
      #| Pinecone Vector DB | TextEmbeddings BAAI-Large-En-V1.5 |