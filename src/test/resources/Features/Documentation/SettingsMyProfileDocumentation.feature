Feature: Settings My Profile

@LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Settings - My Profile Page
   Given User captures documentation screenshot for 'Settings-My Profile'
    Given User opens Main Menu
    And User clicks on Open Settings
    When User clicks on My Profile
    And User captures screenshot for "My Profile Page"
    And User clicks on New Key button
    And User captures screenshot for "Generate New Key"
    Then User fills Name as 'New Key' in Name field
    And User fills Description as 'New Description' in Description field
    And User captures a 'button' and highlights the 'Generate'
    And User completes screenshot capture and triggers comparison for 'Settings-My Profile'

