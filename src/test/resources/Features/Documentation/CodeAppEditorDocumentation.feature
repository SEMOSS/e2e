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

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture the screenshot of Code Editor - Member Permission for code app
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'MyApp2' app in the app searchbox
    And User clicks on 'MyApp2' app from the My Apps
    And User clicks on app Edit button
    And User click on Settings
    And User clicks on Access Control Tab
    And User captures a 'text' and highlights the "Member Permissions" with name "MemberPermissions"
    And User completes screenshot capture and triggers comparison for 'AccessControlMemberPermission'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture the screenshot of Code Editor - Upload File for code app
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'MyApp2' app in the app searchbox
    And User clicks on 'MyApp2' app from the My Apps
    And User clicks on app Edit button
    And User click on Settings
    And User clicks on 'Settings' tab for Apps
    And User captures a 'card' and highlights the "Update Project" with name "UploadFiles"
    And User completes screenshot capture and triggers comparison for 'SettingUpdateProject'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture the screenshot of Code Editor - Upload File for code app
    When User clicks on Settings tab
    And User clicks on Access Control Tab
    Then User captures a "testidelement" and highlights the "workspace-Settings-image" with name "Settings"
    And User completes screenshot capture and triggers comparison for 'CodeAppSetting'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture the screenshot of Code Editor - Upload File for code app
    When User clicks on the 'portals' folder in the Files section
    And User click on the created 'index.html' file
    Then User captures a "class" and highlights the "upload" with name "PublishFiles"
    And User completes screenshot capture and triggers comparison for 'CodeAppSetting'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture the screenshot of Code Editor - Upload File for code app
    When User clicks on the 'portals' folder in the Files section
    And User click on the created 'index.html' file
    Then User captures a "role" and highlights the "group" with name "AppDirectory"
    And User completes screenshot capture and triggers comparison for 'CodeAppSetting'