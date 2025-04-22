Feature: Run Pixel Command

  @LoginWithAdmin
  Scenario: Verify Pixel command screen output
    Given User clicks on Open App Library
    When User clicks on System Apps tab
    Then User clicks on Terminal card
    And User run pixel command '1+1'
    And User sees '2' in the output