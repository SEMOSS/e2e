Feature: Create App and validate Variables and take screenshot for documentation

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Validate Block Variables in Drag and Drop App with screenshot for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the 'Input' block and drops it on the page
    And User clicks on the Save App icon
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'prompt'
    And User selects variable type as 'block'
    And User enters variable value as 'input--1'
    And User clicks on Create Variable button
    Then User sees Toast message of variable creation 'prompt'
    And User clicks on Add Variable button
    And User enters variable name as 'demo variable'
    And User clicks on variable type
    And User captures screenshot for app screens "VAR1"
    And User completes screenshot capture and triggers comparison for 'App variable'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Validate  Variables in Drag and Drop App with screenshot for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the 'Input' block and drops it on the page
    And User clicks on the Save App icon
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as "adding nums"
    And User clicks on query Submit button
    And User hovers and clicks on the cell
    And User deletes the previous cell
    And User enters code as 'x={{num one}}+{{num two}}+{{num three}}'
    And User clicks on Variable
    And User clicks on Add Variable button
    And User enters variable name as 'num one'
    And User selects variable type as 'block'
    And User enters variable value as 'input--1'
    And User clicks on Create Variable button
    And User clicks on Add Variable button
    And User enters variable name as 'num three'
    And User selects variable type as 'block'
    And User enters variable value as 'input--1'
    And User clicks on Create Variable button
    And User clicks on Add Variable button
    And User enters variable name as 'num two'
    And User selects variable type as 'block'
    And User enters variable value as 'input--1'
    And User clicks on Create Variable button
    And User opens the 'Block' Variables tab
    And User opens the 'Query' Variables tab
    And User captures screenshot for "VAR2"
    And User completes screenshot capture and triggers comparison for 'App variable'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Validate  Variables in Drag and Drop App with screenshot for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the 'Text' block and drops it on the page
    And User drags the 'Input' block and drops it on the page
    And User clicks on the Save App icon
    And User clicks on the 'Text' block to select it
    And User enters '{{text-generation}}' in the Text field
    And User captures screenshot for "VAR3"
    And User completes screenshot capture and triggers comparison for 'App variable'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Validate Variables in Drag and Drop App with screenshot for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as "text generation"
    And User clicks on query Submit button
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the 'Button' block and drops it on the page
    And User clicks on "On Click" New action button
    And User selects "Query" from the action options
    Then User captures a "blocksettingelement,button" and highlights the "On Click,New Action" with name "VAR4"
    And User completes screenshot capture and triggers comparison for 'App variable'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Validate Variables in Drag and Drop App with screenshot for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as "text generation"
    And User clicks on query Submit button
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User clicks on "on Page Load" New action button
    And User selects "Query" from the action options
    Then User captures a "button" and highlights the "New Action" with name "VAR5"
    And User completes screenshot capture and triggers comparison for 'App variable'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Validate Access Control in Drag and Drop App with screenshot for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User click on Settings
    And User clicks on Access Control Tab
    And User captures screenshot for app screens "VAR6"
    And User completes screenshot capture and triggers comparison for 'App variable'
