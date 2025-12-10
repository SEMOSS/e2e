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

  @LoginWithAdmin @Documentation @DeleteCreatedTestApp @SkipIfVersionMatchs
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
