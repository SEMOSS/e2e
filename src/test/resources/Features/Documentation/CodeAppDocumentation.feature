Feature: Documentation for Code App

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Code App - Documentation for Custom Reactor App Settings
    Given User captures documentation screenshot for 'Building Apps/CustomReactor'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Develop in code"
    And User enters app name as 'Code app'
    And User enters description as 'Created by automation script'
    And User clicks on Create button
    And User fetch the app name 
    And User clicks on Settings tab
    And User clicks on General tab
    And User clicks on Apps tab
    Then User captures a "testidelement , label" and highlights the "workspace-Settings-image , Apps" with name "AppSettings"
    And User completes screenshot capture and triggers comparison for 'CodeAppSetting'

    @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Code App - Documentation for Custom Reactor App Settings
    Given User captures documentation screenshot for 'CodeEditor'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Develop in code"
    And User enters app name as 'Code app'
    And User enters description as 'Created by automation script'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on Settings tab
    And User clicks on Access Control Tab
    Then User captures a "testidelement" and highlights the "workspace-Settings-image" with name "Settings"
    And User completes screenshot capture and triggers comparison for 'CodeAppSetting'

 @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Code App - Documentation for Publish App Settings
    Given User captures documentation screenshot for 'CodeEditor'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Develop in code"
    And User enters app name as 'Code app'
    And User enters description as 'Created by automation script'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on the 'portals' folder in the Files section
    And User click on the created 'index.html' file
    Then User captures a "class" and highlights the "upload" with name "PublishFiles"
    And User completes screenshot capture and triggers comparison for 'CodeAppSetting'

 @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Code App - Documentation for AppDirectory 
    Given User captures documentation screenshot for 'CodeEditor'
    When User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Develop in code"
    And User enters app name as 'Code app'
    And User enters description as 'Created by automation script'
    And User clicks on Create button
    And User fetch the app name
    And User clicks on the 'portals' folder in the Files section
    And User click on the created 'index.html' file
    Then User captures a "role" and highlights the "group" with name "AppDirectory"
    And User completes screenshot capture and triggers comparison for 'CodeAppSetting'