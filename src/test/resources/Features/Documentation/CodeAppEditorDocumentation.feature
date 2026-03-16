Feature: Documentation for Code App

  Background: Create code app
    Given User captures documentation screenshot for 'CodeEditor'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in 'Develop in code'
    And User enters app name as 'MyApp2'
    And User enters description as 'Created by automation script'
    And User clicks on Create button
    And User fetch the app name

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture a screenshot of the OpenCodeEditor for documentation.
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'MyApp2' app in the app searchbox
    And User clicks on 'MyApp2' app from the My Apps
    Then User captures a 'testidelement' and highlights the 'viewAppPage-edit-btn' with name 'OpenCodeEditor'
    And User completes screenshot capture and triggers comparison for 'CodeEditor'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture a screenshot of the OpenCodeEditor for documentation.
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'MyApp2' app in the app searchbox
    And User clicks on 'MyApp2' app from the My Apps
    And User clicks on app Edit button
    And User captures screenshot for "CodeEditorHomeScreen"
    And User completes screenshot capture and triggers comparison for 'CodeEditor'
