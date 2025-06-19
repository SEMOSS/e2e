Feature: Job Management

  Background: Add Job
    Given User clicks on Open Settings
    And User clicks on Jobs
    And User clicks on Add Job button
    And User fills 'Test Job' in Name field
    And User fills '2+2' in Pixel field
    And User clicks Add button
    Then User can see 'Test Job' in the list

  Scenario: Edit Job
    When User clicks on Edit Icon for added 'Test Job'
    And User edit Tags as 2
    And User edit Pixels as '2+3'
    And User clicks Save button
    Then User can see '2' value as Tag for edited 'Test Job'

  Scenario: Delete Job
    When User clicks on Delete Icon for added 'Test Job'
    Then User sees delete job toast message as 'Successfully deleted all selected jobs'

  Scenario: Run Job
    When User selects the checkbox next to 'Test Job'
    Then 'Test Job' will start running and Pause button will be enabled
    
  Scenario: Pause a Running Job
    When User clicks the checkbox of "Test Job"
    And User clicks the green Pause button
    Then the "Test Job" should stop running
    And the checkbox of "Test Job" should become unselected
    And the green Pause button should revert to its default state

  Scenario: Resume a Paused Job
    When User clicks the checkbox of "Test Job"
    And User clicks the green Pause button
    And the "Test Job" should stop running
    And User clicks the checkbox of "Test Job"
    And User clicks the Resume button
    Then 'Test Job' will start running and Pause button will be enabled
    And the checkbox of "Test Job" should become unselected
    And the Resume button should revert to its default state