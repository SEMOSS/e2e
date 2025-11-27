Feature: Documentation for Accordion Block Settings
@LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Documentation for Accordion Dimensions
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User drags the 'Accordion' block and drops it on the page
    And User clicks on the 'Accordion' block to select it
    And User clicks on the Block Settings option
    And User selects the Appearance tab
    And User captures a 'blocksettingelement' and highlights the "Dimensions" with name "AccordionDimensions" 
    And User completes screenshot capture and triggers comparison for 'Accordion Block Settings'

    @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Documentation for Accordion Dimensions
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User drags the 'Accordion' block and drops it on the page
    And User clicks on the 'Accordion' block to select it
    And User clicks on the Block Settings option
    And User selects the Appearance tab
    And User captures a 'blocksettingelement' and highlights the "Color" with name "AccordionColor" 
    And User completes screenshot capture and triggers comparison for 'Accordion Block Settings'