# @LoginWithAdmin @SkipIfVersionMatch @DeleteTestCatalog @Documentation
# Feature: BI System App Documentation

#   Background: Create Database and open System app
#     Given User captures documentation screenshot for 'Navigating'
#     When User opens Main Menu
#     And User clicks on Open Database
#     And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
#     And User clicks on Add Database
#     And User clicks on file upload icon
#     And User uploads the file 'Database/TestDatabase.zip'
#     And User clicks on 'Upload' button to create catalog
#     And User clicks on Copy Catalog ID
#     And User clicks on MetaData tab
#     And User clicks on Refresh button
#     And User selects the 'DIABETES' from the dropdown
#     And User clicks on apply database button
#     Then User sees the table in the metadata tab
#     When User clicks on Save button of Metadata tab
#     Then User sees success toast message 'Successfully saved changes.'
#     When User opens Main Menu
#     And User clicks on Open App Library
#     And User clicks on System app

#   Scenario: Capture screenshots of BI app card, Create Project, and Create Insight buttons for documentation
#     When User clicks on System app
#     Then User captures a 'tile' and highlights the 'BI' with name 'AppLibrary_BI'
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on New project button
#     Then User captures a 'button' and highlights the 'New project' with name 'CreateProject'
#     When User click on cancel button
#     Then User captures a 'text' and highlights the 'Create the first insight' with name 'CreateInsight'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Import Data screen for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User captures screenshot for 'ImportData'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Edit Database screen for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User search 'TestDatabase' created database and select
#     And User clicks on Add All button from Available Columns section
#     Then User captures screenshot for 'Insight2'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Edit Database screen for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User search 'TestDatabase' created database and select
#     And User clicks on Add All button from Available Columns section
#     And User clicks on Import button from Selected Columns section
#     And User clicks on Save button
#     Then User captures screenshot for 'Insight4'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Import Database screen for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User search 'TestDatabase' created database and select
#     And User clicks on Add All button from Available Columns section
#     And User clicks on Import button from Selected Columns section
#     And User mouse hover on database frame
#     Then User captures screenshot for 'Insight3'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Visual options for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User search 'TestDatabase' created database and select
#     And User clicks on Add All button from Available Columns section
#     And User clicks on Import button from Selected Columns section
#     And User mouse hover on database frame and click on Visualize this data option
#     Then User captures a 'fullsection' and highlights the 'view__menu' with name 'Insight5'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Add Panel button for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User search 'TestDatabase' created database and select
#     And User clicks on Add All button from Available Columns section
#     And User clicks on Import button from Selected Columns section
#     And User mouse hover on database frame and click on Visualize this data option
#     And User selects 'Bar' from the Visualization type options
#     When User drags the 'Age' field to the 'X-Axis'
#     When User drags the 'BloodPressure' field to the 'Y-Axis'
#     When User hovers Add Panel and selects 'Add Chart'
#     Then User captures a 'button' and highlights the 'Add Panel' with name 'panel'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Presenting button for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User search 'TestDatabase' created database and select
#     And User clicks on Add All button from Available Columns section
#     And User clicks on Import button from Selected Columns section
#     And User mouse hover on database frame and click on Visualize this data option
#     And User selects 'Bar' from the Visualization type options
#     When User drags the 'Age' field to the 'X-Axis'
#     When User drags the 'BloodPressure' field to the 'Y-Axis'
#     When User hovers Add Panel and selects 'Add Chart'
#     When User Clicks on Presentation Mode option
#     Then User captures a 'button' and highlights the 'Presenting' with name 'Presentation'
#     And User completes screenshot capture and triggers comparison for 'BI App'

#   Scenario: Capture screenshots of Export file button for documentation
#     When User clicks on BI
#     And User clicks on Welcome popup close option
#     And User clicks on Add option
#     And User search 'TestDatabase' created database and select
#     And User clicks on Add All button from Available Columns section
#     And User clicks on Import button from Selected Columns section
#     And User mouse hover on database frame and click on Visualize this data option
#     And User selects 'Bar' from the Visualization type options
#     When User drags the 'Age' field to the 'X-Axis'
#     When User drags the 'BloodPressure' field to the 'Y-Axis'
#     When User hovers Add Panel and selects 'Add Chart'
#     And User clicks on Export option from the side menu
#     And User clicks on 'Export File' button
#     Then User captures a 'buttontext' and highlights the 'Export' with name 'exporting'
#     And User completes screenshot capture and triggers comparison for 'BI App'
