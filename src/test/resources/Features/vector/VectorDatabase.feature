Feature: View Existing Vectors
  
   Background: View Open Vectors
   Given User navigates to Open Vector Page
   And User clicks on 'My Vectors' tab

   Scenario Outline: View My Vectors
   Given User navigates to Open Vector Page
   And User clicks on 'My Vectors' tab
   Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
  
   Scenario Outline: Add Tag
   When User clciks on 'WeaviateCatalogee'
   And User clicks on 'Edit' button
   And User enters the filters 'TestTag' in 'Tag' box and clicks enter
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Add Domain
   When User clciks on 'WeaviateCatalogee'
   And User clicks on 'Edit' button
   And User enters the filters 'TestDomain' in 'Domain' box and clicks enter
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Add Data Classification
   When User clciks on 'WeaviateCatalogee'
   And User clicks on 'Edit' button
   And User enters the filters 'PUBLIC' in 'Data classification' box
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Add Data Restrictions
   When User clciks on 'WeaviateCatalogee'
   And User clicks on 'Edit' button
   And User enters the filters 'IP ALLOWED' in 'Data restrictions' box
   Then User clicks on the 'Submit' button
  
   Scenario Outline: Filter by Tags
   When User clicks on search by under Filter By Section
   And User enters 'TestTag' in the search box and clicks on it under 'Tag'
   Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
   
   Scenario Outline: Filter by Domain
   When User clicks on search by under Filter By Section
   And User enters 'TestDomain' in the search box and clicks on it under 'Domain'
   Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
   
   Scenario Outline: Filter by Data Classification
   When User clicks on search by under Filter By Section
   And User enters 'PUBLIC' in the search box and clicks on it under 'Data Classification'
   Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
   
   Scenario Outline: Filter by Data Restrictions
   When User clicks on search by under Filter By Section
   And User enters 'IP ALLOWED' in the search box and clicks on it under 'Data Restrictions'
   Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
  
   Scenario Outline: Vecter Search
	 When User clicks on Search box
	 And User enters the search value as 'WeaviateCatalogee' and presses Enter
	 Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
 
   Scenario Outline: Bookmark Vector
   When User clicks on Bookmark  icon of 'WeaviateCatalogee' then the vector is bookmarked
