@SkipIfVersionMatch @Documentation 
Feature: Create Drag and Drop app documentation

   Scenario: Drag and Drop app - Create New App screenshot
    Given User captures documentation screenshot for 'DragAndDrop'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User captures a 'button' and highlights the "Create New App" with name "CreateNewApp"
    And User completes screenshot capture and triggers comparison for 'DragAndDropApp'


  