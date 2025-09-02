Feature: App Library page Documentation

@LoginWithAdmin @SkipIfVersionMatch
  Scenario: Create Drag and Drop app
    Given User captures documentation screenshot for 'App Library'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Apps'
    And User clicks on Open App Library
    And User captures a 'button' and highlights the 'Discoverable'
    And User captures a 'button' and highlights the 'Create New App'
    And User captures a 'SearchBar' and highlights the 'Search'
    And User clicks on Create New App button
    And User captures a 'Heading' and highlights the 'Start build with a template'
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Travel Itinerary'
    And User enters description as 'Created by automation script'
    And User enters tags 'travel planner, itinerary creator' and presses Enter
    And User captures screenshot for "New App Popup"
    And User clicks on Create button
    And User captures a 'button' and highlights the 'Preview App'
    And User captures a 'button' and highlights the 'Save App'
    And User captures a 'button' and highlights the 'Share App'
    And User clicks on Share App button
    And User captures a 'button' and highlights the 'Copy'
    And User clicks on IFrame button
    And User captures a 'copycta' and highlights the 'CopyURL'
    And User Clicks on close button
    When User opens Main Menu
    And User clicks on Open App Library
		And User searches 'Travel Itinerary' app in the app searchbox
    Then User can see 'Travel Itinerary' app on the page
		And User clicks on View Details button
		And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User clicks on 'Edit' button
    And User captures screenshot for "View Tabs"
    And User enters and selects 'CONFIDENTIAL' under 'Data classification' section
    And User captures a 'button' and highlights the 'Save'
    And User clicks on 'Cancel' button
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures a 'button' and highlights the 'Upload App'
    And User clicks on Upload button
    And User captures screenshot for "Uploading App"
    And User is on Home page
    Then User opens Main Menu
    And User captures a 'button' and highlights the 'Settings'
    And User clicks on Open Settings
    And User captures a 'button' and highlights the 'App Settings'
    And User clicks on 'App Settings' Card
    When User enables admin mode
    And User captures screenshot for "App Settings Page"
    Then User can selects 'Travel Itinerary' on the page
    And User captures a 'button' and highlights the 'Delete'
    And User completes screenshot capture and triggers comparison for 'Settings Overview'






