Feature: Add Database
  Background: Create Database using ZIP file
    Given User opens Main Menu
   	And User clicks on Open Database
    When User clicks on Add Database
    Then User selects database 'ZIP'
    And User uploads database file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User sees the database name 'TestDatabase' in the database catalog

  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: Verify Database Usage Examples
    Given User opens Main Menu
   	And User clicks on Open Database
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog
    When User clicks on Usage tab for Database
    Then User sees an example of "How to use in Javascript" with example code for Database
    And User sees an example of "How to use in Python" with example code for Database
    And User sees an example of "How to use with Langchain API" with example code for Database
    And User sees an example of "How to use in Java" with example code for Database

  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: View Database Tags
    Given User sees the database name 'TestDatabase' in the database catalog
    When User clicks on 'TestDatabase' in the database catalog
    And User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'
    And User should see 'embeddings' on the page

	@LoginWithAdmin @DeleteCreatedCatalog
	Scenario: View Database Overview
   And User clicks on 'TestDatabase' in the database catalog
    And User sees the database name as 'TestDatabase'
    #And User can see 'copy Database ID' Database ID
    And User clicks on copy icon of Database ID
    When User can see toast message as 'Successfully copied ID'
    #And User can see 'Please use the Edit button to provide a description for this Database. A description will help others find the Database and understand how to use it. To include more details associated with the Database, edit the markdown located in the Overview section.' as database description
    And User can see 'No Markdown available' as database description
    #When User clicks on Edit button
    And User clicks on Access Control Tab
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User selects the 'TestDatabase' from the database catalog
    #And User can see last updated info
    And User clicks on Export button that creates a Zip of DB when clicked
    And User sees an Edit button that opens a pop-up to edit
    # used to close edit modal
    And User clicks on Close button
    
  @LoginWithAdmin @DeleteCreatedCatalog
  Scenario: Verify MetaData for Database
    Given User opens Main Menu
   	And User clicks on Open Database    
    And User sees the database name 'TestDatabase' in the database catalog
    And User clicks on the database name 'TestDatabase' in the database catalog
    When User clicks on MetaData tab
    Then User sees the table in the metadata tab
