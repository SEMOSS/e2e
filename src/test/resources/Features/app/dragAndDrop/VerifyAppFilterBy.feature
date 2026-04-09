@DeleteCreatedTestApp @LoginWithAdmin @Regression
Feature: Filter By option in the app
    
    Scenario: Create Drag and Drop app and verify the app is visible on applying filters in the app library
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
    And User selects 'IP, PHI, PII, PUBLIC' from the Data Classification in the app settings
    And User selects 'IP ALLOWED, PHI ALLOWED, FOUO ALLOWED' from the Data Restrictions in the app settings
    And User clicks on Submit button in the app settings
    And User opens Main Menu
    And User clicks on Open App Library
    Then User applies each filter and validate 'Test app' app is visible on the page
      | FILTER_CATEGORY     | FILTER_VALUE                          |
      | Tag                 | embeddings, Test1                     |
      | Domain              | SAP, AI                               |
      | Data Classification | IP, PHI, PII, PUBLIC                  |
      | Data Restrictions   | IP ALLOWED, PHI ALLOWED, FOUO ALLOWED |

