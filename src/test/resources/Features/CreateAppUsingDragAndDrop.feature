Feature: Create new app

  Background: Create Drag and Drop app
    Given User navigates to Open App Library
    When User clicks on Create New App button
    And User clicks on Get Started button in Drag and Drop
    And User enters app name as 'Test app'
    #And User enters description as 'Created by automation script'
    #And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User navigate back to the Home page

    Scenario: Drag and Drop block component
    Given User navigates to Open App Library
    When User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    And User clicks on Blocks
    And User drags Heading 1 block and drop on the page
    Then User can see 'Hello world' on the page