Feature: View Existing Vectors
  
   Background: View Open Vectors
   Given User navigates to Open Vector Page
   And User clicks on 'My Vectors' tab
  
   @LoginWithAuthor
   Scenario Outline: Create Vector Databases
   # adding embedder for use when creating vector DB
    Given User navigates to Open Model
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
   
   # creating vector DB
   And User navigates to Open Vector Page
   And User clicks on 'Discoverable Vectors' tab
   And User clicks on Add vector button
   And User selects 'FAISS' as connection
   And User enters 'Catalog Name' as 'FAISSCatalogeeVectorr'
   And User selects 'Catalog' from embedder field
   And User selects 'Token' from chunking strategy field
   And User enters 'Content Length' as '512'
   And User enters 'Content Overlap' as '20'
   And User clicks on Create Vector Button
   And User navigates to Open Vector Page
   Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
  

   Scenario Outline: View My Vectors
   Given User navigates to Open Vector Page
   And User clicks on 'My Vectors' tab
   Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
  
   Scenario Outline: Add Tag
   When User clicks on the vector 'FAISSCatalogeeVectorr'
   And User clicks on vector Edit button
   And User enters the filters 'TestTag' in 'Tag' box and clicks enter
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Add Domain
   When User clicks on the vector 'FAISSCatalogeeVectorr'
   And User clicks on vector Edit button
   And User enters the filters 'TestDomain' in 'Domain' box and clicks enter
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Add Data Classification
   When User clicks on the vector 'FAISSCatalogeeVectorr'
   And User clicks on vector Edit button
   And User enters the filters 'PUBLIC' in 'Data classification' box
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Add Data Restrictions
   When User clicks on the vector 'FAISSCatalogeeVectorr'
   And User clicks on vector Edit button
   And User enters the filters 'IP ALLOWED' in 'Data restrictions' box
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Filter by Tags
   When User clicks on search by under Filter By Section
   And User enters 'TestTag' in the search box and clicks on it under 'Tag'
   Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
   
   Scenario Outline: Filter by Domain
   When User clicks on search by under Filter By Section
   And User enters 'TestDomain' in the search box and clicks on it under 'Domain'
   Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
   
   Scenario Outline: Filter by Data Classification
   When User clicks on search by under Filter By Section
   And User enters 'PUBLIC' in the search box and clicks on it under 'Data Classification'
   Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
   
   Scenario Outline: Filter by Data Restrictions
   When User clicks on search by under Filter By Section
   And User enters 'IP ALLOWED' in the search box and clicks on it under 'Data Restrictions'
   Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
  
   Scenario Outline: Vector Search
	 When User clicks on Search box
	 And User enters the search value as 'FAISSCatalogeeVectorr' and presses Enter
	 Then User should see the 'FAISSCatalogeeVectorr' vector on the Vector Catalog page
 
   Scenario Outline: Bookmark Vector
   When User clicks on Bookmark  icon of 'FAISSCatalogeeVectorr' then the vector is bookmarked
