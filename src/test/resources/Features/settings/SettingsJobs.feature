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

  Scenario: Validate elements present on jobs page
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enables admin mode
    And User clicks on Jobs
    And User clicks on Add Job button
    And User fills 'Test Job' in Name field
    And User fills '2+2' in Pixel field
    And User clicks Add button
    Then User can see 'Test Job' in the 'All' section list
    When User clicks on 'Active' tab
    Then User can see 'Test Job' in the 'Active' section list
    When User clicks on 'Inactive' tab
    Then User cannot see 'Test Job' in the 'Inactive' section list
    And User can see 'Active Jobs' status tile count is '1'
    When User clicks on 'Active' tab
    And User clicks the checkbox of "Test Job"
    And User clicks the green Pause button
    And User clicks on 'Inactive' tab
    Then User can see 'Test Job' in the 'Inactive' section list
    And User can see 'Active Jobs' status tile count is '0'
    And User can see 'Inactive Jobs' status tile count is '1'
    When User clicks the checkbox of "Test Job"
    And User clicks the Resume button
    And User clicks on 'Active' tab
    Then User can see 'Test Job' in the 'Active' section list
    And User can see 'Active Jobs' status tile count is '1'
    And User can see 'Inactive Jobs' status tile count is '0'
    When User clicks on Play icon for added 'Test Job'
    Then User can see 'Failed Jobs' status tile count is '1'
    When User clicks on Delete Icon for added 'Test Job'
    Then User sees delete job toast message as 'Successfully deleted all selected jobs'
