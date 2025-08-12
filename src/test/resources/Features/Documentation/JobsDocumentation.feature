Feature: Member Setting documentation

@LoginWithAdmin @SkipIfVersionMatch
  Scenario: Settings - My Profile Page
   Given User captures documentation screenshot for 'Jobs Management'
    Given User opens Main Menu
    And User captures a 'button' and highlights the 'Settings'
    And User clicks on Open Settings
    When User enable admin mode
    And User captures a 'button' and highlights the 'Admin On'
    And User captures a 'button' and highlights the 'Jobs'
    And User clicks on 'Jobs' Card
    And User clicks on Add Job button
    And User captures screenshot for "AddJobForm"
    And User completes screenshot capture and triggers comparison for 'Jobs Management'

