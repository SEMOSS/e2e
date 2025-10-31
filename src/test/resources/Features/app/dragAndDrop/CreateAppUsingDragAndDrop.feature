@DeleteCreatedTestApp @Regression
Feature: Create drag and drop app

  Background: Create Drag and Drop app and navigate to Blocks option
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    #And User enters description as 'Created by automation script'
    #And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Blocks if it is not selected by default

  Scenario: Drag and Drop Heading 1 block
    When User drags the 'Text (h1)' block and drops it on the page
    Then User can see 'Hello world' on the page

  Scenario Outline: Drag and Drop Text section '<BLOCK_NAME>' block
    When User drags the '<BLOCK_NAME>' block and drops it on the page
    And User clicks on the '<BLOCK_NAME>' block to select it
    And User clicks on the Block Settings option
    And User enters '<DESTINATION>' as the destination
    And User enters '<BLOCK_NAME>' text as '<TEXT>'
    And User selects the Appearance tab
    And User selects the '<STYLES>' styles
    And User selects '<FONT>' from the font list
    And User selects '<HEX_COLOR>' as the HEX color value
    And User selects '<TEXT_ALIGNMENT>' as the text alignment
    And User clicks on the Save App icon
    Then User should see the '<BLOCK_NAME>' text as '<TEXT>'
    And User should see the '<BLOCK_NAME>' text displayed in '<EXPECTED_STYLES>' styles
    And User should see the '<BLOCK_NAME>' text displayed in '<FONT>' font
    And User should see the '<BLOCK_NAME>' text displayed in '<HEX_COLOR>' HEX color value
    And User should see the '<BLOCK_NAME>' text aligned to the '<TEXT_ALIGNMENT>'
    And User should be navigated to '<DESTINATION>' by clicking on link

    Examples: 
      | BLOCK_NAME | DESTINATION             | TEXT               | STYLES                   | EXPECTED_STYLES          | FONT            | HEX_COLOR | TEXT_ALIGNMENT |
      | Link       | https://playwright.dev/ | Test App Link      | Bold, Italic             | Bold, Italic, Underlined | Times New Roman | #ff5733   | Center         |
      | Text (h1)  |                         | Heading 1 block    | Italic, Underlined       | Bold, Italic, Underlined | Times New Roman | #ff6666   | Center         |
      | Text (h2)  |                         | Heading 2 block    | Italic, Underlined       | Bold, Italic, Underlined | Arial           | #ff9900   | Left           |
      | Text (h3)  |                         | Heading 3 block    | Italic, Underlined       | Bold, Italic, Underlined | Roboto          | #ffcc00   | Right          |
      | Text (h4)  |                         | Heading 4 block    | Italic, Underlined       | Bold, Italic, Underlined | Times New Roman | #ff5733   | Justify        |
      | Text (h5)  |                         | Heading 5 block    | Italic, Underlined       | Bold, Italic, Underlined | Helvetica       | #ff007f   | Center         |
      | Text (h6)  |                         | Heading 6 block    | Italic, Underlined       | Bold, Italic, Underlined | Georgia         | #ff5733   | Center         |
      | Text       |                         | Text block         | Bold, Italic, Underlined | Bold, Italic, Underlined | Arial           | #ffcc00   | Right          |
      | Markdown   |                         | **Markdown** block | Bold, Italic             | Bold, Italic             | Times New Roman | #ffcc00   | Left           |

  @ApplicationBugFailure
  Scenario: Drag and Drop Text section Logs block
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test query'
    And User clicks on query Submit button
    And User enters code as 'print("Hello word")'
    And User clicks on Run cell button
    Then User can see code output as 'Hello word'
    And User clicks on Blocks
    And User clicks on 'page-1' page
    And User drags the 'Logs' block and drops it on the page
    And User clicks on the 'Logs' block to select it
    And User clicks on the Block Settings option
    And User selects 'Test query' from the Query dropdown
    And User clicks on the Save App icon
    Then User should see the 'Logs' text as 'Hello word'
