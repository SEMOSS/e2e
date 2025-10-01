Feature: Job Settings documentation

@LoginWithAdmin @SkipIfVersionMatch
  Scenario: Settings - Jobs Page
   Given User captures documentation screenshot for 'Jobs Management'
    Given User opens Main Menu
    And User captures a 'button' and highlights the 'Settings'
    And User clicks on Open Settings
    When User enables admin mode
    And User captures screenshot for form "Settings Page"
    And User captures a 'button' and highlights the 'Jobs'
    And User clicks on 'Jobs' Card
    And User captures screenshot for "Jobs Page"
    And User clicks on Add Job button
    And User captures screenshot for form "AddJobForm"
    And User completes screenshot capture and triggers comparison for 'Jobs Management'