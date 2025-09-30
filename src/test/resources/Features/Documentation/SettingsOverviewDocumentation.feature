Feature: Settings Overview Documentation

@LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: View Settings Overview
    Given User captures documentation screenshot for 'Settings Overview'
   Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User opens Main Menu
    And User captures a 'button' and highlights the 'Settings'
    And User clicks on Open Settings
    And User captures a 'button' and highlights the 'App Settings'
    And User clicks on 'App Settings' Card
    When User enables admin mode
    And User captures screenshot for "App Settings Page"
    Then User can selects 'Test app' on the page
    And User captures a 'button' and highlights the 'Delete'
    And User captures screenshot for "App Details"
    And User click on 'Pending Requests' tab
    And User captures screenshot for "Pending Requests"
    And User click on 'Data Apps' tab
    And User captures screenshot for "Data Apps"
    And User captures a 'heading' and highlights the 'Update Project'
    And User completes screenshot capture and triggers comparison for 'Settings Overview'


