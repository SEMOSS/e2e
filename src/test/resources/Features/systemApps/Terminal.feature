Feature: Terminal

  ## Terminal system app is not accessible in the local environment ###
  Background: Navigate to Terminal
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    When User clicks on System app
    And User clicks on Terminal card

  @LoginWithAdmin @Regression @BLOCKED 
  Scenario: Run Pixel command in Terminal
    Given User is on Terminal page
    When User run pixel command 'Hello'
    Then User sees 'Pixel' output 'Hello'

  @LoginWithAdmin @Regression @BLOCKED 
  Scenario: Run Python command in Terminal
    Given User is on Terminal page
    When User change the language to 'Python'
    And User run python command '1+1'
    Then User sees 'Python' output '2'
