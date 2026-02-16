Feature: Vector Catalog documentation

  ## Embede file upload fails bug- https://github.com/SEMOSS/semoss-ui/issues/1950 ###
  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @BLOCKED_BY_BE @Documentation 
  Scenario: Create a Vector
    Given User captures documentation screenshot for 'PlatformNavigation/Vector Catalog'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User clicks on file upload icon
    And User uploads the file 'VectorDatabase/Text_Embedding_model.zip'
    And User clicks on 'Upload' button to create catalog
    And User can see a toast message as 'Model uploaded successfully!'
    And User can see the Catalog title as 'TextEmbeddings BAAI-Large-En-V1.5'
    And User clicks on Copy Catalog ID
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Vector'
    And User clicks on Open Vector
    And User captures a 'button' and highlights the 'Add Vector'
    When User clicks on Add Vector button
    And User captures a 'List Item' and highlights the 'FAISS'
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISS Vector DB01'
    And User selects 'TextEmbeddings BAAI-Large-En-V1.5' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User captures a 'button' and highlights the 'Create vector'
    And User clicks on Create Vector button
    And User can see vector database created success toast message as 'Successfully added vector database to catalog'
    And User clicks on Copy Catalog ID
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User clicks on Usage tab
    And User captures a 'tab' and highlights the 'Usage'
    When User clicks on 'Edit' button
    And User captures screenshot for form "Edit Options"
    And User clicks on 'Close' button
    And User clicks on Access Control Tab
    And User captures a 'button' and highlights the 'Access Control'
    And User captures a 'Heading' and highlights the 'Pending Requests'
    And User clicks Make 'Vector' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Discoverable Vectors button
    And User searches the 'FAISS Vector DB01' in the Vector Catalog searchbox
    And User selects the 'FAISS Vector DB01' from the Vector catalog
    And User captures a 'button' and highlights the 'Request Access'
    And User click on the Request Access button
    And User selects 'author' access
    And User captures screenshot for "Access Request"
    And User clicks on Request button
    And User logs out from the application
    And User login as 'admin'
    And User opens Main Menu
    And User clicks on Open Vector
    And User searches the 'FAISS Vector DB01' in the Vector Catalog searchbox
    And User selects the 'FAISS Vector DB01' from the Vector catalog
    And User clicks on Documents tab
    And User clicks on Embed New Document
    And User captures screenshot for "Embed New Document"
    And User uploads the file 'Vector_Embed_file.pdf'
    And User clicks on Embed button
    Then User sees file embeded success toast message 'Successfully added document'
    And User captures screenshot for "File List"
    And User clicks on Q&A button
    And User completes screenshot capture and triggers comparison for 'Vector Catalog'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Take the screenshot for Add Vector Button
    Given User captures documentation screenshot for 'RAG'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open Vector
    And User captures a 'button' and highlights the 'Add Vector' with name 'AddVectorDB.png'
    Then User completes screenshot capture and triggers comparison for 'Add Vector Button'
