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
    And User fetch the app name for drag and drop app

  @DeleteCreatedTestApp
  Scenario: User copies the App Id successfully
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Copy App ID' option
    Then User can see 'Successfully copied to clipboard' toast message after copying the ID.
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches copied id in the app searchbox
    Then User can see 'Test app' app on the page

  @DeleteCreatedTestApp
  Scenario: User clones app successfully
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Clone This App' option
    And User enters cloned app name as 'App clone'
    And User enters cloned app description as 'cloned app'
    And User clicks on 'Next' button of clone app popup
    And User click on Make Public toggle switch
    And User clicks on 'Clone' button of clone app popup
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'App clone' app in the app searchbox
    Then User can see 'App clone' app on the page

  Scenario: User deletes app successfully
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Delete App' option
    And User click on 'Delete' confirmation button
    Then User can not see 'Test app' app on the page

  @DeleteCreatedTestApp
  Scenario: Filter apps
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User applies each filter and validate 'Test app' app is visible on the page
      | FILTER_CATEGORY | FILTER_VALUE |
      | Tag             | Test1, Test2 |
