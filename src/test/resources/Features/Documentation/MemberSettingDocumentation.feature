Feature: Member Settings documentation

@LoginWithAdmin @SkipIfVersionMatch
  Scenario: Settings - Member Settings
   Given User captures documentation screenshot for 'Member Settings'
    Given User opens Main Menu
    And User captures a 'button' and highlights the 'Settings'
    And User clicks on Open Settings
    When User enable admin mode
    And User captures a 'button' and highlights the 'Admin On'
    And User captures a 'button' and highlights the 'Member Settings'
    And User clicks on 'Member Settings' Card 
    And User captures a 'button' and highlights the 'Add Member'
    And User captures screenshot for "MemberSettingsPage"
    And User Clicks on Add User button
    And User captures screenshot for form "AddMemberform"
    And User completes screenshot capture and triggers comparison for 'Member Settings'