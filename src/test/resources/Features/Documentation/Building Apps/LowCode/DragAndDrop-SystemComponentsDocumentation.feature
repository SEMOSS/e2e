@LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
Feature: Documentation for System components

  Background: Create Drag and Drop app
    Given User captures documentation screenshot for 'Systemcomponents'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'DND Text Test App'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on Blocks if it is not selected by default

  Scenario: Documentation - DND Chip block
    When User drags the 'HTML' block and drops it on the page
    And User drags the 'Text (h1)' block and drops it on the page
    And User drags the 'Chip' block and drops it on the page
    And User clicks on the 'Chip' block to select it
    And User Clicks 'Type' option in the Block Settings
    Then User captures screenshot for "Chip1"
    And User completes screenshot capture and triggers comparison for 'Drag and Drop Chip Block Overview'

  Scenario: Documentation - DND Chip2 block
    When User drags the 'HTML' block and drops it on the page
    And User drags the 'Text (h1)' block and drops it on the page
    And User drags the 'Chip' block and drops it on the page
    And User clicks on the 'Chip' block to select it
    Then User captures screenshot for "Chip2"
    And User completes screenshot capture and triggers comparison for 'Drag and Drop Chip Block Overview'

  Scenario: Documentation - DND Chip3 block
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as "default"
    And User clicks on query Submit button
    And User clicks on Create new notebook
    And User enters New Query name as "notebook 2"
    And User clicks on query Submit button
    And User clicks on Create new notebook
    And User enters New Query name as "options"
    And User clicks on query Submit button
    And User clicks on Blocks if it is not selected by default
    And User clicks on 'page-1' page
    And User drags the 'HTML' block and drops it on the page
    When User drags the 'Text (h1)' block and drops it on the page
    And User drags the 'Chip' block and drops it on the page
    And User clicks on the 'Chip' block to select it
    And User clicks on "Pre Process" New action button
    And User selects "Query" from the action options
    Then User captures screenshot for "Chip3"
    And User completes screenshot capture and triggers comparison for 'Drag and Drop Chip Block Overview'

  Scenario: Capture screenshot of Iframe for documentation
    When User drags the 'Iframe' block and drops it on the page
    And User drags the 'HTML' block and drops it on the page
    And User clicks on the 'Iframe' block to select it
    Then User captures screenshot for 'Iframe'
    And User completes screenshot capture and triggers comparison for 'Drag and Drop Iframe Block Overview'

  Scenario: Capture screenshot of Image for documentation
    When User drags the 'Image' block and drops it on the page
    And User drags the 'HTML' block and drops it on the page
    And User clicks on the 'Image' block to select it
    Then User captures screenshot for 'Image'
    And User completes screenshot capture and triggers comparison for 'Drag and Drop Image Block Overview'

  Scenario: Capture screenshot of Image for documentation
    When User drags the 'Progress' block and drops it on the page
    And User drags the 'HTML' block and drops it on the page
    And User clicks on the 'Progress' block to select it
    Then User captures screenshot for 'Progress'
    And User completes screenshot capture and triggers comparison for 'Drag and Drop Progress Block Overview'
