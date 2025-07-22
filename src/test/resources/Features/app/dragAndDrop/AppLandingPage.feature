Feature: App landing page

  Background: Create Drag and Drop app
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button

  Scenario: Copy App Id
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Copy App ID' option
    #Then User can see 'Successfully copied to clipboard' toast message after copying the ID.
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches copied id in the app searchbox
    Then User can see 'Test app' app on the page

  Scenario: Clone app
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Clone This App' option
    And User enters cloned app name as 'App clone'
    And User enters cloned app description as 'cloned app'
    And User clicks on 'Next' button
    And User click on Make Public button
    And User clicks on 'Upload' button
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'App clone' app in the app searchbox
    Then User can see 'App clone' app on the page

  Scenario: Delete app
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Delete App' option
    And User click on 'Delete' confirmation button
    Then User can not see 'Test app' app on the page
