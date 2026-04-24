Feature: Validate Create by me filter

  @DeleteCreatedTestApp @LoginWithAdmin @Regression
  Scenario: Validate Created by me filter on app landing page
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'App created by editor'
    And User clicks on Create button
    And User click on Settings
    And User clicks on Access Control Tab
    And User clicks on make 'App created by editor' app public toggle switch
    And User logs out from the application
    And User login as 'admin'
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'App created by editor' app in the app searchbox
    Then User can see 'App created by editor' app on the page
    When User clicks on Created by me toggle switch
    Then User can not see 'App created by editor' app on the page
