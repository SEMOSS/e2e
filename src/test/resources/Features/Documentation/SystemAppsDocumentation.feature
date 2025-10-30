Feature: System Apps Documentation

  ### Emedded file upload issue Bug- https://github.com/SEMOSS/semoss-ui/issues/1950 ###
  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @BLOCKED_BY_BE @Documentation
  Scenario: Verify user can create database from CSV file in BI system app
    Given User captures documentation screenshot for 'PlatformNavigation/System Apps'
    When User opens Main Menu
    And User clicks on Open Database
    And User clicks on Add Database
    And User selects the 'ZIP' option to upload file
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on Create Database button
    And User clicks on Copy Catalog ID
    ##Steps added for metadata table Refresh to enable the data import until bug is fixed
    And User clicks on MetaData tab
    And User clicks on Refresh button
    And User selects the 'DIABETES' from the dropdown
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    ###
    Then User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    And User captures a 'tile' and highlights the 'BI'
    And User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on New project button
    And User captures a 'button' and highlights the 'New project'
    And User captures screenshot for "New Project form"
    And User click on cancel button
    And User captures a 'button' and highlights the 'Create the first insight'
    And User clicks on Add option
    And User captures screenshot for "Add Data form"
    And User search 'TestDatabase' created database and select
    And User clicks on Add All button from Available Columns section
    And User captures screenshot for "Selected Columns section"
    And User clicks on Import button from Selected Columns section
    And User mouse hover on database frame and click on Visualize this data option
    And User captures a 'button' and highlights the 'VISUAL'
    And User clicks on Save button
    And User captures screenshot for form "Save Insight form"
    And User click on cancel button
    And User selects 'Bar' from the Visualization type options
    And User captures screenshot for "Visualization type options"
    And User drags the 'Age' field to the 'X-Axis'
    And User captures screenshot for "Data selection tab"
    And User drags the 'BloodPressure' field to the 'Y-Axis'
    Then User clicks on the Tools option
    Then User selects 'Color' from the Tools options
    Then User captures screenshot for "Color option selected"
    Then User hovers Add Panel and selects 'Add Chart'
    And User captures a 'button' and highlights the 'Add Panel'
    And User Clicks on Presentation Mode option
    And User captures a 'button' and highlights the 'Presenting'
    And User Clicks on Presentation Mode option
    And User clicks on Export option from the side menu
    And User clicks on 'Export File' button
    And User captures screenshot for "Export Insight options"
