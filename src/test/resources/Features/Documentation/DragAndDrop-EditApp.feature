Feature: Documentation for Drag and Drop App  Block & windows block
@LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion Dimensions
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Drag and Drop Test'
    And User clicks on Create button
    And User fetch the app name 
    And User captures screenshot for "EditApp"
    And User completes screenshot capture and triggers comparison for 'EditApp Block'
    
    @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion Color
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Drag and Drop Test'
    And User clicks on Create button
    And User fetch the app name 
    And User clicks on Blocks
    And User clicks on the Block Settings option 
    And User captures a 'blocktitle, blocktitle' and highlights the "Blocks, Block Settings" with name "BlockSettings" 
    And User completes screenshot capture and triggers comparison for 'BlockSettings Settings' 
