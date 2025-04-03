Feature:  User Management
 
@LoginWithAdmin
   	Scenario: Add New Native User
    Given User logs as Admin user in AI CORE application
    And User navigates to the settings
  	And User enables admin mode
    And User clicks Member Settings
    Then User sees the Add User button
   	And User adds 10 members and can see toast message as 'Successfully added user' for all added members
