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
    And User fetch the app name
    And User opens Main Menu
    And User clicks on Open App Library
    And User selects the 'Grid view' view

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Verify app card details
    When User searches 'Test app' app in the app searchbox
    Then User can see 'Test app' app on the page
    And User can see the following details on the app card
      | DETAIL_NAME     | VALUE                        |
      | App Name        | Test app                     |
      | App Description | Created by automation script |
      | Open App button | Open                         |
      | Info button     | Info                         |
      | More Vert Icon  |                              |
      | Bookmark Icon   |                              |

  @DeleteCreatedTestApp @Regression
  Scenario: User copies the App Id successfully
    When User searches 'Test app' app in the app searchbox
    And User selects the 'List view' view
    And User clicks on 'Copy app ID' icon
    Then User can see 'App ID copied to clipboard' toast message after copying the ID.
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches copied id in the app searchbox
    Then User can see 'Test app' app on the page

  @DeleteCreatedTestApp @Regression
  Scenario: User clones app successfully
    When User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Clone App' option
    And User enters cloned app name as 'App clone'
    And User enters cloned app description as 'cloned app'
    And User clicks on 'Next' button of clone app popup
    #And User click on Make Public toggle switch
    And User clicks on 'Clone' button of clone app popup
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'App clone' app in the app searchbox
    Then User can see 'App clone' app on the page

  Scenario: User deletes app successfully
    When User searches 'Test app' app in the app searchbox
    And User clicks on more vertical icon of 'Test app' app
    And User clicks on 'Delete App' option
    And User click on 'Delete' confirmation button
    Then User can not see 'Test app' app on the page

  @DeleteCreatedTestApp @Regression
  Scenario: Filter apps
    When User searches 'Test app' app in the app searchbox
    And User applies each filter and validate 'Test app' app is visible on the page
      | FILTER_CATEGORY | FILTER_VALUE |
      | Tag             | Test1, Test2 |

  @DeleteCreatedTestApp @Regression
  Scenario: Verify bookmark and unbookmark an app
    When User searches 'Test app' app in the app searchbox
    And User clicks on the Bookmark icon for 'Test app' App
    Then User clicks on the Bookmarked Apps tab
    And User can see 'Test app' in the Bookmarked Apps section
    When User clicks on the Unbookmark icon for 'Test app' App
    Then User cannot see 'Test app' in the Bookmarked Apps section
    And User clicks on the My Apps tab

  @DeleteCreatedTestApp @Regression
  Scenario: Created app is displayed in My Apps section
    When User searches 'Test app' app in the app searchbox
    Then User can see 'Test app' app in the My Apps section

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Verify app is display under Discoverable
    When User searches 'Test app' app in the app searchbox
    Then User can see 'Test app' app on the page
    And User clicks on app 'Info' button
    And User clicks on Access Control Tab
    And User turn OFF the Non Discoverable option
    And User logs out from the application
    And User login as 'Editor'
    And User opens Main Menu
    And User clicks on Open App Library
    And User click on Discoverable Apps
    And User searches 'Test app' app in the app searchbox
    Then The newly created 'Test app' should be displayed in the discoverable apps list
    And User logs out from the application
    And User login as 'Admin'
    And User opens Main Menu
