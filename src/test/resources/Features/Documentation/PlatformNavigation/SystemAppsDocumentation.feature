Feature: System Apps Documentation

  @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
  Scenario: Capture screenshots of BI system app for Platform Navigation documentation
    Given User captures documentation screenshot for 'PlatformNavigation/System Apps'
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
    And User clicks on apply database button
    Then User sees the table in the metadata tab
    When User clicks on Save button of Metadata tab
    And User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on System app
    Then User captures a 'tile' and highlights the 'BI'
    When User clicks on BI
    And User clicks on Welcome popup close option
    And User clicks on New project button
    Then User captures a 'button' and highlights the 'New project'
    And User captures screenshot for "New Project form"
    When User click on cancel button
    Then User captures a 'button' and highlights the 'Create the first insight'
    When User clicks on Add option
    Then User captures screenshot for "Add Data form"
    When User search 'TestDatabase' created database and select
    And User clicks on Add All button from Available Columns section
    Then User captures screenshot for "Selected Columns section"
    When User clicks on Import button from Selected Columns section
    And User mouse hover on database frame and click on Visualize this data option
    Then User captures a 'button' and highlights the 'VISUAL'
    When User clicks on Save button
    Then User captures screenshot for form "Save Insight form"
    When User click on cancel button
    And User selects 'Bar' from the Visualization type options
    Then User captures screenshot for "Visualization type options"
    When User drags the 'Age' field to the 'X-Axis'
    Then User captures screenshot for "Data selection tab"
    When User drags the 'BloodPressure' field to the 'Y-Axis'
    And User clicks on the Tools option
    And User selects 'Color' from the Tools options
    Then User captures screenshot for "Color option selected"
    When User hovers Add Panel and selects 'Add Chart'
    Then User captures a 'button' and highlights the 'Add Panel'
    When User Clicks on Presentation Mode option
    Then User captures a 'button' and highlights the 'Presenting'
    When User Clicks on Presentation Mode option
    And User clicks on Export option from the side menu
    And User clicks on 'Export File' button
    Then User captures screenshot for "Export Insight options"
