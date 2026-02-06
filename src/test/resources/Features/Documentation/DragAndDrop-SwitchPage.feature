Feature: Documentation for Drag and Drop App - Switch Page
    @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for layer switch page
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Drag and Drop Test'
    And User clicks on Create button
    And User fetch the app name 
    And User clicks on Block Settings option
    And User click on the 'Layers' tab in the left panel
    And User click on the Add new page icon to add a new page
    And User captures a 'text, text' and highlights the "page-1, /page-1" with name "SwitchPage" 
    And User completes screenshot capture and triggers comparison for 'SwitchPage' 