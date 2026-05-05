Feature: Settings- Jobs

 # broken scenario need to move this validation code to JobPageTests 
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
    Then User can see 'Failed' status tile count is '1'
    When User clicks on Delete Icon for added 'Test Job'
    # Then User sees delete job toast message as 'Successfully deleted all selected jobs'
