Feature: Gmail Template
 
@LoginWithAdmin @DeleteCreatedTestApp @Regression
Scenario: Create app using Template -Gmail 
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Gmail" from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name
    And User sees title "Gmail" 
    And User sees a dialog containing text "Logged in by :"
    And User verifies "Refresh" button is enabled 
    And User verifies "Compose Mail" button is enabled
    And User sees "All Mails" tab
    And User verifies "Read" button is enabled
    And User verifies "Delete" button is enabled
    And User sees "Unread Mails" tab
    And User verifies "Read" button is enabled
    And User sees "Sent Mails" tab
    And User clicks on Preview app button
    And User sees title "Gmail" 
    And User sees a dialog containing text "Logged in by :"
    And User verifies "Refresh" button is enabled 
    And User verifies "Compose Mail" button is enabled
    And User sees "All Mails" tab
    And User verifies "Read" button is enabled
    And User verifies "Delete" button is enabled
    And User sees "Unread Mails" tab
    And User verifies "Read" button is enabled
    And User sees "Sent Mails" tab
    And User clicks on Close Preview button