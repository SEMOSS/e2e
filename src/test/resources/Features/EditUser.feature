Feature:  Edit User
 
@LoginWithAdmin
   	Scenario: Edit Native User
    Given User logs as Admin user in AI CORE application
    When User navigates to the settings
    And User enables admin mode
    And User clicks Member Settings
    Then User sees the Add User button
    And User adds 1 members and can see toast message as 'Successfully added user' for all added members
		And User clicks on Edit icon
		And User clicks on Model dropdown
		And User selects 'Token' value in Model dropdown
		And User fills '2' value in Max Tokens field
		And User clicks on Frequency dropdown