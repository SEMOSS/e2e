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

  Scenario: Drag and Drop Heading 1 block component
    When User drags the 'Heading 1' block and drops it on the page
    Then User can see 'Hello world' on the page
    When User navigate to Home page

  Scenario: Drag and Drop Builder - Add Link
    When User drags the 'Link' block and drops it on the page
    And User clicks on the Block Settings option
    And User enters 'https://playwright.dev/' as the destination
    And User enters 'Test App Link' as the text
    And User selects the 'Bold, Italic, Underlined' styles
    And User selects 'Times New Roman' from the font list
    And User selects '#ff5733' as the HEX color value
    And User selects 'Center' as the text alignment
    And User clicks on the Save App icon
    Then User should see 'Test App Link' as the link name
    And User should see the link name displayed in 'Bold, Italic, Underlined' styles
    And User should see the link name displayed in 'Times New Roman' font
    And User should see the link name displayed in '#ff5733' HEX color value
    And User should see the link name aligned to the 'Center'
    When User clicks on the link
    Then User should be navigated to 'https://playwright.dev/'
