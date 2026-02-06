Feature: Settings- Jobs

  Scenario: Validate elements present on jobs page
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enables admin mode
    And User clicks on Jobs
    Then User sees Jobs page title as 'Jobs'
    And User sees Jobs page subtitle as 'Search by job name or filter using job tags'
    And User sees below status tiles on Jobs page
      | Active Jobs   |
      | Inactive Jobs |
      | Failed Jobs   |
    And User sees below tabs on Jobs page
      | All      |
      | Active   |
      | Inactive |
    And User sees below buttons on Jobs page
      | Pause  |
      | Resume |
      | Add    |
    And User sees below buttons are disabled on Jobs page
      | Pause  |
      | Resume |
    And User sees Search box on Jobs page
    And User sees Jobs table with below columns
      | Name        |
      | Frequency   |
      | Time Zone   |
      | Tags        |
      | Last Run    |
      | Status      |
      | Modified By |
      | Actions     |
    And User sees 'No jobs found' message when there are no jobs present
    And User sees History table is by default collapsed
    When User expands History table
    Then User sees History table with below columns
      | Name     |
      | Run Date |
      | Time     |
      | Status   |
    And User sees search box on History table
    And User sees 'No job history, please try again.' message when there is no job history present
