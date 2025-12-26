Feature: Documentation for Layers Panel

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

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - One Conatainer Documentation for App Landing
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in 'Drag and Drop'
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Block Settings option
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Markdown' block and drops it on the page
    And User click on the 'Layers' tab in the left panel
    And User delete 'Welcome to the UI Builder! Drag and drop blocks to use in your app.' on page
    Then User captures screenshot for 'OneContainer'
    And User completes screenshot capture and triggers comparison for 'One Container'

  @LoginWithAdmin @Documentation @DeleteCreatedTestApp @SkipIfVersionMatch
  Scenario: DragAndDrop - Two Conatainer Documentation for App Landing
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in 'Drag and Drop'
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Block Settings option
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Container' block and drops it on the page
    And User drags the 'Markdown' block and drops it on the page
    And User drags the 'Markdown' block and drops it on the page
    And User delete 'Welcome to the UI Builder! Drag and drop blocks to use in your app.' on page
    And User click on the 'Layers' tab in the left panel
    Then User captures screenshot for 'TwoContainer'
    And User completes screenshot capture and triggers comparison for 'Two Container'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Drag and drop two Markdown blocks and highlight layers panel for documentation
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in 'Drag and Drop'
    And User enters app name as 'Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Block Settings option
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Markdown' block and drops it on the page
    And User drags the 'Markdown' block and drops it on the page
    And User click on the 'Layers' tab in the left panel
    And User delete 'Welcome to the UI Builder! Drag and drop blocks to use in your app.' on page
    And User captures a 'section' and highlights the "Layers" with name "NestBlock"
    And User completes screenshot capture and triggers comparison for 'NestBlock'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Unnest in Layers Panel
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Unnest Layer Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Block Settings option
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Markdown' block and drops it on the page
    And User drags the 'Markdown' block and drops it on the page
    And User delete 'Welcome to the UI Builder! Drag and drop blocks to use in your app.' on page
    And User click on the 'Layers' tab in the left panel
    And User drags the 'markdown--2' block and drops it 'outside' the 'container--1' block
    And User captures a 'layerblock' and highlights the "container" with name "UnnestBlock"
    And User completes screenshot capture and triggers comparison for 'DragAndDropApp'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Reorder Blocks in Layers Panel
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Reorder Layer Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Block Settings option
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Markdown' block and drops it on the page
    And User drags the 'Markdown' block and drops it on the page
    And User click on the 'Layers' tab in the left panel
    And User drags the 'markdown--1' block and drops it 'inside' the 'container--1' block
    And User delete 'Welcome to the UI Builder! Drag and drop blocks to use in your app.' on page
    Then User captures screenshot for 'ReOrderBlocks'
    And User completes screenshot capture and triggers comparison for 'ReOrderBlocks'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for ReorderBlock2 in Layers Panel
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Reorder Layer Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Block Settings option
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Markdown' block and drops it on the page
    And User drags the 'Markdown' block and drops it on the page
    And User delete 'Welcome to the UI Builder! Drag and drop blocks to use in your app.' on page
    And User click on the 'Layers' tab in the left panel
    Then User captures screenshot for 'ReorderBlock2'
    And User completes screenshot capture and triggers comparison for 'ReorderBlock2'
