Feature: Create new app

  Background: Create Drag and Drop app and navigate to Blocks option
    Given User navigates to Open App Library
    When User clicks on Create New App button
    And User clicks on Get Started button in Drag and Drop
    And User enters app name as 'Test app'
    #And User enters description as 'Created by automation script'
    #And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User navigate to Home page
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Blocks if it is not selected by default

  #
  #Scenario: Drag and Drop Heading 1 block
  #When User drags the 'Heading 1' block and drops it on the page
  #Then User can see 'Hello world' on the page
  #When User navigate to Home page
  Scenario Outline: Drag and Drop Builder - '<SCENARIO_NAME>'
    When User drags the '<BLOCK_NAME>' block and drops it on the page
    And User clicks on the Block Settings option
    And User enters '<DESTINATION>' as the destination
    And User enters '<TEXT>' as the text
    And User selects the '<STYLES>' styles
    And User selects '<FONT>' from the font list
    And User selects '<HEX_COLOR>' as the HEX color value
    And User selects '<TEXT_ALIGNMENT>' as the text alignment
    And User clicks on the Save App icon
    Then User should see the '<BLOCK_NAME>' text as '<TEXT>'
    And User should see the '<BLOCK_NAME>' text displayed in '<STYLES>' styles
    And User should see the '<BLOCK_NAME>' text displayed in '<FONT>' font
    And User should see the '<BLOCK_NAME>' text displayed in '<HEX_COLOR>' HEX color value
    And User should see the '<BLOCK_NAME>' text aligned to the '<TEXT_ALIGNMENT>'
    And User should be navigated to '<DESTINATION>' by clicking on link
    When User navigate to Home page

    Examples: 
      | SCENARIO_NAME       | BLOCK_NAME | DESTINATION             | TEXT            | STYLES                   | FONT            | HEX_COLOR | TEXT_ALIGNMENT |
      | Add link block      | Link       | https://playwright.dev/ | Test App Link   | Bold, Italic, Underlined | Times New Roman | #ff5733   | Center         |
      | Add Heading 1 blobk | Text (h1)  |                         | Heading 1 block | Bold, Italic, Underlined | Times New Roman | #ff5733   | Center         |
