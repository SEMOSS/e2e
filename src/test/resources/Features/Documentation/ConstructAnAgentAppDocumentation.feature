Feature: Construct an Agent App Documentation

@LoginWithAdmin @SkipIfVersionMatch @DeleteCreatedTestApp @Documentation
Scenario: Construct an Agent App Documentation
    Given User captures documentation screenshot for 'RAG'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User captures a "appTypeTile , testidelement" and highlights the "Construct an agent , createAppSection-new-app-agent-btn-btn" with name "ConstructAnAgent"
    And User completes screenshot capture and triggers comparison for 'ConstructAnAgent Overview'