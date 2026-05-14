@DeleteCreatedTestApp @LoginWithAdmin @Regression
Feature: Filter By option in the app

  Background: Create Drag and Drop app and edit the app
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User click on Settings
    And User clicks on Edit button in the setting page
    And User enters the details as 'Test app'
    And User enters the description as 'Test app'
    And User add Tags 'embeddings, Test1, Test2, Test3' in app settings and presses Enter
    And User enters the Domains as 'SAP, AI, Finance' in the app settings and presses Enter
    And User selects 'IP, PHI, PII, Public' from the Data Classification in the app settings
    And User selects 'IP Allowed, PHI Allowed, FOUO Allowed' from the Data Restrictions in the app settings
    And User clicks on Submit button in the app settings

  Scenario: Verify the app is visible while applying filters in the app library
    When User opens Main Menu
    And User clicks on Open App Library
    Then User applies each filter and validate 'Test app' app is visible on the page
      | FILTER_CATEGORY     | FILTER_VALUE                          |
      | Tag                 | embeddings, Test1                     |
      | Domain              | SAP, AI                               |
      | Data Classification | IP, PHI, PII, Public                  |
      | Data Restrictions   | IP Allowed, PHI Allowed, FOUO Allowed |

  Scenario: Verify the discoverable  app is visible while applying filters in the app library
    When User clicks on Access Control Tab
    And User clicks on Make 'Test app' Discoverable button in settings page
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Discoverable Apps button
    Then User applies each filter and validate 'Test app' app is visible on the page
      | FILTER_CATEGORY     | FILTER_VALUE                          |
      | Tag                 | embeddings, Test1                     |
      | Domain              | SAP, AI                               |
      | Data Classification | IP, PHI, PII, Public                  |
      | Data Restrictions   | IP Allowed, PHI Allowed, FOUO Allowed |

  Scenario: Verify the Bookmarked app is visible while applying filters in the app library
    When User opens Main Menu
    And User clicks on Open App Library
    When User searches 'Test app' app in the app searchbox
    And User clicks on the Bookmark icon for 'Test app' App
    And User clicks on the Bookmarked Apps tab
    Then User applies each filter and validate 'Test app' app is visible on the page
      | FILTER_CATEGORY     | FILTER_VALUE                          |
      | Tag                 | embeddings, Test1                     |
      | Domain              | SAP, AI                               |
      | Data Classification | IP, PHI, PII, Public                  |
      | Data Restrictions   | IP Allowed, PHI Allowed, FOUO Allowed |
