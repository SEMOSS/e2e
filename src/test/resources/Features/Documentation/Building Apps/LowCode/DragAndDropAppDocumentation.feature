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
    
    @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation 
    Scenario: Documenation- New Page Icon - Drag and Drop app
    Given User captures documentation screenshot for 'DragAndDrop'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
     And User clicks on Get Started button in 'Drag and Drop'
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name 
    And User clicks on Block Settings option
    And User click on the 'Layers' tab in the left panel
    And User captures a 'testidelement' and highlights the 'AddIcon' with name 'NewPage'
    And User completes screenshot capture and triggers comparison for 'DragAndDropApp'
    
    @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Documentation for Drag And Drop - Create App 
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Drag and Drop test'
    And User enters description as 'Test app'
    And User enters tags 'test' and presses Enter
    And User captures screenshot for "CreateApp"
    And User completes screenshot capture and triggers comparison for "DragAndDrop"
    