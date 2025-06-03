Feature: Terminal

  @LoginWithAdmin
  Scenario: Verify Pixel command output
    Given User clicks on Open App Library
    When User clicks on System app
    Then User clicks on Terminal card
    And User run pixel command '1+1'
    And User sees '2' in the output
