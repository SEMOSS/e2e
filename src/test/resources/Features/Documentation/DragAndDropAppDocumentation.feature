
Feature: Create Drag and Drop app documentation
@SkipIfVersionMatch @Documentation 
   Scenario: Drag and Drop app - Create New App screenshot
    Given User captures documentation screenshot for 'DragAndDrop'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User captures a 'button' and highlights the "Create New App" with name "CreateNewApp"
    And User completes screenshot capture and triggers comparison for 'DragAndDropApp'
    
@SkipIfVersionMatch @Documentation 
  Scenario: Drag and Drop app - Landing screenshot
    Given User captures documentation screenshot for 'DragAndDrop'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures screenshot for "DragAndDropLanding"
    And User completes screenshot capture and triggers comparison for 'DragAndDropApp'
    