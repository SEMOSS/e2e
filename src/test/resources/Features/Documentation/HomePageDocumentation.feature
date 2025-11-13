Feature: Home Page Documentation

  @LoginWithAdmin @SkipIfVersionMatch @Documentation
  Scenario: Home Page Documentation
    Given User captures documentation screenshot for "RAG"
    When User opens Main Menu
    Then User captures a "testidelement, button" and highlights the "MenuOpenRoundedIcon, Vector" with name "HomeMenu"
    And User completes screenshot capture and triggers comparison for "Home Page"