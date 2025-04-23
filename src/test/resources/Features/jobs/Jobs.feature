Feature: Job Management
  
  Background: Open Job Tab in Settings
    Given User clicks on Open Settings Engine
    And User clicks on Jobs Icon

  Scenario: Pause a Running Job
    When User clicks the checkbox of "Test Job"
    And User clicks the green Pause button
    Then the "Test Job" should stop running
    And the checkbox of "Test Job" should become unselected
    And the green Pause button should revert to its default state

  Scenario: Resume a Paused Job
    When User clicks the checkbox of Paused "New Job"
    And User clicks the Resume button
    Then the "New Job" should start running
    And the checkbox of "New Job" should become unselected
    And the Resume button should revert to its default state