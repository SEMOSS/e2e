Feature: Jobs

  Background: Add Job
    Given User navigates to the settings
    And User enables admin mode
    And User clicks on Jobs
    And User clicks on Add Job button
    And User fills 'Test Job' in Name field
    And User fills '2+2' in Pixel field
    And User clicks Add button
    Then User can see 'Test Job' in the list

  @LoginWithAdmin
  Scenario: Edit Job
    When User clicks on Edit Icon for added 'Test Job'
    And User edit Tags as 2
    And User edit Pixels as '2+3'
    And User clicks Save button
    Then User can see '2' value as Tag for edited 'Test Job'

  @LoginWithAdmin
  Scenario: Delete Job
    When User clicks on Delete Icon for added 'Test Job'
    Then User sees delete job toast message as 'Successfully deleted UNSCHEDULE_JOB'

  @LoginWithAdmin
  Scenario: Run Job
    When User selects the checkbox next to 'Test Job'
    Then 'Test Job' will start running and Pause button will be enabled
