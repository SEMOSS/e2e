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

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Verify app card details
    Given User opens Main Menu
    And User clicks on Open App Library
    When User searches 'Test app' app in the app searchbox
    Then User can see 'Test app' app on the page
    And User can see the following details on the app card
      | DETAIL_NAME         | VALUE                        |
      | App Name            | Test app                     |
      | App Description     | Created by automation script |
      | Published date      | Published {date}             |
      | Last Edited date    | Last Edited {date}           |
      | Open App button     | Open App                     |
      | View Details button | View Details                 |
      | More Vert Icon      |                              |
      | Bookmark Icon       |                              |

  @DeleteCreatedTestApp @Regression
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

  @DeleteCreatedTestApp @Regression
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

  @DeleteCreatedTestApp @Regression
  Scenario: Filter apps
    Given User opens Main Menu
    When User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User applies each filter and validate 'Test app' app is visible on the page
      | FILTER_CATEGORY | FILTER_VALUE |
      | Tag             | Test1, Test2 |

  @DeleteCreatedTestApp @Regression
  Scenario: Bookmark an app successfully
    Given User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    When User clicks on the Bookmark icon for 'Test app' App
    Then User see the Bookmarked section
    And The app should appear in the bookmarked section

  @DeleteCreatedTestApp @Regression
  Scenario: Remove app from bookmarks
    Given User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User can see 'Test app' app on the page
    When User clicks on the Bookmark icon for 'Test app' App
    And User clicks on the Unbookmark icon for 'Test app' App
    Then The 'Test app' should be removed from the bookmarked section
    And If no apps remain bookmarked the "Bookmarked" section should not be visible

  @DeleteCreatedTestApp @Regression
  Scenario: Created app is displayed in All Apps section
    Given User opens Main Menu
    And User clicks on Open App Library
    When User searches 'Test app' app in the app searchbox
    Then User can see 'Test app' app in the All Apps section

  @DeleteCreatedTestApp @Regression
  Scenario: Verify BI and Terminal apps are displayed under System apps
    Given User opens Main Menu
    And User clicks on Open App Library
    When User click on System Apps
    Then User can see 'BI' app in the System Apps section
    And User can see 'Terminal' app in the System Apps section
    
  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Verify app is display under Discoverable
    Given User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    Then User can see 'Test app' app on the page
    And User clicks on app 'View Details' button
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

