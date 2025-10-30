Feature: Configuration documentation

@LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Settings - Configuration documentation
   Given User captures documentation screenshot for 'PlatformNavigation/Configuration'
    Given User opens Main Menu
    And User captures a 'button' and highlights the 'Settings'
    And User clicks on Open Settings
    When User enable admin mode
    And User captures a 'button' and highlights the 'Admin On'
    And User captures a 'button' and highlights the 'Configuration'
    And User clicks on 'Configuration' Card
    And User captures screenshot for "ConfigurationPage"
    And User completes screenshot capture and triggers comparison for 'ConfigurationPage'
    
