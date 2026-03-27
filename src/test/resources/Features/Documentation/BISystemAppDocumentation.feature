Feature: BI System App Documentation

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Capture screenshots of BI app card for documentation
    Given User captures documentation screenshot for 'Navigating'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    Then User captures a 'tile' and highlights the 'BI' with name 'AppLibrary_BI'
    And User completes screenshot capture and triggers comparison for 'BI App'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Capture screenshots of Create Project button for documentation
    Given User captures documentation screenshot for 'Navigating'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on New project button
    Then User captures a 'button' and highlights the 'New project' with name 'CreateProject'
    And User completes screenshot capture and triggers comparison for 'BI App'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Capture screenshots of Create the first Insight button for documentation
    Given User captures documentation screenshot for 'Navigating'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    Then User captures a 'text' and highlights the 'Create the first insight' with name 'CreateInsight'
    And User completes screenshot capture and triggers comparison for 'BI App'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Capture screenshots of Create the first Insight button for documentation
    Given User captures documentation screenshot for 'Navigating'
    When User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    When User clicks on Save button of Metadata tab
    And User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on Add option
    And User captures screenshot for 'ImportData'
    And User completes screenshot capture and triggers comparison for 'BI App'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Capture screenshots of Edit Database screen for documentation
    Given User captures documentation screenshot for 'Navigating'
    When User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    When User clicks on Save button of Metadata tab
    And User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on Add option
    And User search 'TestDatabase' created database and select
    And User clicks on Add All button from Available Columns section
    Then User captures screenshot for 'Insight2'
    And User completes screenshot capture and triggers comparison for 'BI App'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Capture screenshots of Edit Database screen for documentation
    Given User captures documentation screenshot for 'Navigating'
    When User opens Main Menu
    And User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    When User clicks on Save button of Metadata tab
    And User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on Add option
    And User search 'TestDatabase' created database and select
    And User clicks on Add All button from Available Columns section
    And User clicks on Import button from Selected Columns section
    And User clicks on Save button
    Then User captures screenshot for 'Insight4'
    And User completes screenshot capture and triggers comparison for 'BI App'
