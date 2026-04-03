Feature: Documentation for Drag and Drop app sharing

  Background: Create Drag and Drop app
    Given User captures documentation screenshot for 'AppSharing'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Share App'
    And User clicks on Create button
    And User fetch the app name

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture screenshot of URL sharing for documentation
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Share App' app in the app searchbox
    And User clicks on 'Share App' app from the My Apps
    And User click on Share App link
    Then User captures a 'button' and highlights the 'Copy' with name 'UrlSharing'
    And User completes screenshot capture and triggers comparison for 'AppSharing'

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture screenshot of IFrame sharing for documentation
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Share App' app in the app searchbox
    And User clicks on 'Share App' app from the My Apps
    And User click on Share App link
    And User clicks on 'IFrame' button
    Then User captures a 'button' and highlights the 'Copy' with name 'IframeSharing'
    And User completes screenshot capture and triggers comparison for 'AppSharing'
