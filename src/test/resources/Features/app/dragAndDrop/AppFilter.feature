@Regression @DeleteCreatedTestApp
Feature: validate the filter and discoverable option for Drag and Drop app

  Scenario: Create Drag and Drop app and Validate the discoverable option for app
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name 
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User click on Settings
    And User clicks on Access Control Tab
    And User clicks on Make 'Test app' Discoverable button in settings page
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Discoverable Apps button
    When User searches 'Test app' app in the app searchbox
    Then User can see 'Test app' app on the page

Scenario: Validate ascending and descending filter on app page
    Given User creates '1' 'drag and drop' apps with app name 'Descending filter test App', description 'Pagination Test Description', and tags 'Pagination, Test'
    And User opens Main Menu
    And User clicks on Open App Library
    Given User creates '1' 'drag and drop' apps with app name 'Descending filter test App', description 'Pagination Test Description', and tags 'Pagination, Test'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on the 'Ascending' Filter button
    Then User can see the apps are sorted in ascending order
    When User clicks on the 'Descending' Filter button
    Then User can see the apps are sorted in descending order

Scenario: Validate the date modified filter on app page
    Given User creates '1' 'drag and drop' apps with app name 'Date Filter Test App', description 'Date Filter Test Description', and tags 'Date Filter, Test'
    And User opens Main Menu
    And User clicks on Open App Library
    When User selects 'Date Last Edited' from the Sort By dropdown
    Then User can see the apps are sorted by date last Edited
    