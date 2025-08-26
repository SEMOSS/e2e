Feature: Vector documentation

  @LoginWithAdmin @SkipIfVersionMatch
  Scenario: Create a Vector 
    Given User captures documentation screenshot for 'Vector Catalog'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'Catalog'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable1'
    And User clicks on Create Model button
    Then User can see a toast message as 'Successfully added LLM to catalog'
    When User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Vector' 
    And User clicks on Open Vector
    And User captures a 'button' and highlights the 'Add Vector'
    When User clicks on Add Vector button
    And User captures a 'List Item' and highlights the 'FAISS'
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISS Vector DB01'
    And User selects 'Catalog' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User captures a 'button' and highlights the 'Create vector' 
    And User clicks on Create Vector button
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    When User clicks on Edit button 
    And User captures screenshot for "View Tabs"
    And User enters and selects 'CONFIDENTIAL' under 'Data classification' section
    And User captures a 'button' and highlights the 'Submit'
    And User clicks on 'Submit' button
    And User clicks on Access Control Tab
    And User clicks Make 'Vector' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu    
    And User clicks on Open Vector
    And User clicks on Discoverable vectors button
    And User searches the 'FAISS Vector DB01' in the Vector Catalog searchbox
    And User selects the 'FAISS Vector DB01' from the Vector catalog
    And User captures a 'button' and highlights the 'Request Access'
    And User click on the Request Access button
    And User selects 'author' access
    And User captures screenshot for "Access Request"
    And User clicks on Request button
    And User completes screenshot capture and triggers comparison for 'Vector Catalog'