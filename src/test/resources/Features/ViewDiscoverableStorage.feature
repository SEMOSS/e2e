Feature: View Existing Discoverable Storage
  I want to use this feature file for all scenarios related to View Existing Storage
  
  Background: View Discoverable Storages
    Given User clicks on Open Storage Engine
    And User clicks on 'Discoverable Storages' tab
    And User has added storages in the system
   
    @LoginWithAuthor
    Scenario: View discoverable storages under 'Discoverable Storages' tab
    When User clicks on 'Discoverable Storages' tab
    Then User should see the Storage title as 'Amazon S3 Test Storage'
      And User should see the 'No description available' in the description
		
    Scenario: Filter discoverable storage by 'Data Classification'
    When User clicks on 'Discoverable Storages' tab
    And User selects 'CONFIDENTIAL' under 'Data Classification' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'
   
    Scenario: Filter discoverable storage by 'Data Restrictions'
    When User unselects 'CONFIDENTIAL' under 'Data Classification' section
    And User selects 'CONFIDENTIAL ALLOWED' under 'Data Restrictions' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'
    