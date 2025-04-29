#Feature: Discoverable Vector Database
  #
   #Background: View Open Vectors
   #Given User navigates to Open Vector Page
   #And User clicks on 'Discoverable Vectors' tab
  #
   #@LoginWithAuthor
   #Scenario Outline: View My Vectors
   #Given User clicks on 'Discoverable Vectors' tab
   #Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
   #
   #Scenario Outline: Filter by Data Classification
   #When User clicks on search by under Filter By Section 
   #And User enters 'PUBLIC' in the search box and clicks on it under 'Data Classification' 
   #Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
   #
   #Scenario Outline: Filter by Data Restrictions
   #When User clicks on search by under Filter By Section 
   #And User enters 'IP ALLOWED' in the search box and clicks on it under 'Data Restrictions' 
   #Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
  #
   #Scenario Outline: Vecter Search
#	 When User clicks on Search box
#	 And User enters the search value as 'WeaviateCatalogee' and presses Enter
#	 Then User should see the 'WeaviateCatalogee' vector on the Vector Catalog page
 #
  #
