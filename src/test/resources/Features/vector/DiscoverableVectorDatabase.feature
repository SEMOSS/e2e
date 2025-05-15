
#Feature: View Existing Vectors
#
   #@LoginWithAuthor
   #Scenario Outline: Create Vector Databases
   # adding embedder for use when creating vector DB
    #Given User navigates to Open Model
    #And User clicks on Add Model
    #And User selects 'GPT-3.5'
    #And User enters Catalog name as 'Catalog'
    #And User enters open AI Key as 'Test@1234'
    #And User enters var name as 'Variable1'
    #And User clicks on Create Model button
    #Then User can see a toast message as 'Successfully added LLM to catalog'
    #When User clicks on Edit button
    #And User add tags 'embeddings' and presses Enter
    #And User clicks on Submit button
   #
   # creating vector DB
   #And User navigates to Open Vector Page
   #And User clicks on vector 'Discoverable Vectors' tab
   #And User clicks on Add vector button
   #And User selects 'FAISS' as connection
   #And User enters 'Catalog Name' as 'FAISSCatalogeeVectorr'
   #And User selects 'Catalog' from embedder field
   #And User selects 'Token' from chunking strategy field
   #And User enters 'Content Length' as '512'
   #And User enters 'Content Overlap' as '20'
   #And User clicks on Create Vector Button
   #And User navigates to Open Vector Page
   #Then User should see the 'WeaviateCatalogeeVectorr' vector on the Vector Catalog page
  # TODO add in process to set as discoverable
  #
  # now testing discoverable vector search
  # TODO log in with a different user
   #Scenario Outline: View My Vectors
   #Given User clicks on vector 'Discoverable Vectors' tab
   #Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
#
   #Scenario Outline: Vecter Search
#	 When User clicks on Search box
#	 And User enters the search value as 'FAISSCatalogeeVectorr' and presses Enter
#	 Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
 
