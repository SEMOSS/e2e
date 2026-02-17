Feature: Add Zip Database

  Background: Create Database using ZIP file
    Given User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    When User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User sees success toast message 'Successfully Created Database'
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    And User clicks on Save button of Metadata tab

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture a screenshot of the notebook query entry for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test App'
    And User clicks on Create button
    And User fetch the app name
    And User closes the Block Settings button
    And User clicks on Notebook
    And User deletes the notebook named 'mcp_driver'
    And User clicks on Create new notebook
    And User enters New Query name as "prompt output"
    And User clicks on query Submit button
    And User clicks on Create new notebook
    And User enters New Query name as "adding nums"
    And User clicks on query Submit button
    And User clicks on the Notebook 'prompt output'
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User selects 'From Data Catalog' from the data import options
    And User selects 'TestDatabase' from the dropdown list
    And User click on Import data menu
    And User captures screenshot for app screens "NB4"
    And User completes screenshot capture and triggers comparison for 'Notebook'
