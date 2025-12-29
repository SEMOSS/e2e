Feature: Documentation for Accordion Block Settings

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion Dimensions
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
  Scenario: DragAndDrop - Documentation for Accordion Color
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

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion block
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Accordion Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User searches 'Accordion' block in the block searchbox
    And User captures screenshot for "Accordion"
    And User completes screenshot capture and triggers comparison for 'Accordion Block'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion block Settings
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User searches 'Accordion' block in the block searchbox
    And User drags the 'Accordion' block and drops it on the page
    And User clicks on the 'Accordion' block to select it
    And User captures screenshot for "AccordionBlockSettings"
    And User completes screenshot capture and triggers comparison for 'Accordion Block Settings'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion Content
    Given User captures documentation screenshot for 'DragAndDrop'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Accordion Settings Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User searches 'Accordion' block in the block searchbox
    And User drags the 'Accordion' block and drops it on the page
    And User clicks on the 'Accordion' block to select it
    And User clicks on the Block Settings option
    And User captures screenshot for "AccordionContent"
    And User completes screenshot capture and triggers comparison for 'Accordion Content'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion Spacing
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
    And User captures a 'blocksettingelement' and highlights the "Spacing" with name "AccordionSpacing"
    And User completes screenshot capture and triggers comparison for 'Accordion Block Settings'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Accordion Border
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
    And User captures a 'blocksettingelement' and highlights the "Border" with name "AccordionBorder"
    And User completes screenshot capture and triggers comparison for 'Accordion Block Settings'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: DragAndDrop - Documentation for Divider Block
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Divider Block Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User searches 'Divider' block in the block searchbox
    And User captures a 'blocksection' and highlights the "Divider" with name "DNDdivider"
    And User completes screenshot capture and triggers comparison for 'Divider Block'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
  Scenario: Documentation - Input block for Drag and Drop App
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Input Block Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User search the 'Input' block from blocks section
    And User drags the 'Input' block and drops it on the page
    And User captures screenshot for "DNDinput"
    And User completes screenshot capture and triggers comparison for 'Input Block Overview'

  @LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
  Scenario: Documentation - DND layout screenshot for Drag and Drop App
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'DND Layout Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User clicks on Blocks
    And User search the 'Container' block from blocks section
    And User drags the 'Container' block and drops it on the page
    And User search the 'Input' block from blocks section
    And User drags the 'Input' block and drops it on the page
    And User search the 'HTML' block from blocks section
    And User drags the 'HTML' block and drops it on the page
    And User clicks on the 'Add Content' block on page
    And User captures screenshot for "DNDlayout"
    And User completes screenshot capture and triggers comparison for 'Drag and Drop Layout Overview'
