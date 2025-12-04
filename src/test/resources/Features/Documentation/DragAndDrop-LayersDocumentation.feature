Feature: DragAndDrop - Documentation for App Landing

   @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
   Scenario: DragAndDrop - Documentation for App Landing - Layers
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User captures a 'testidelement' and highlights the "workspace-Layers" with name "AppLanding" 
    And User completes screenshot capture and triggers comparison for 'App Landing'