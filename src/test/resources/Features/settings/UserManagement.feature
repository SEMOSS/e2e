Feature: User Management

  @LoginWithAdmin
  Scenario: Add New Native User
    Given User navigates to Open Setting page
    When User enable admin mode
    And User clicks on 'Member Settings' Card
    Then User sees the Add User button
    And User adds 30 members and can see toast message as 'Successfully added user' for all added members
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times

  @LoginWithAdmin
  Scenario: Edit Native User - Change Model Limit Restriction
    Given User navigates to Open Setting page
    When User enable admin mode
    And User clicks on 'Member Settings' Card
    Then User sees the Add User button
    And User adds 1 members and can see toast message as 'Successfully added user' for all added members
    And User clicks on Edit icon
    And User clicks on Model dropdown
    And User selects 'Token' value in Model dropdown
    And User fills '2' value in Max Tokens field
    And User clicks on Frequency dropdown
    And User selects 'Weekly' value in Frequency dropdown
    And User clicks on save button
    Then User sees the new model limit value as '2' on Member Settings page
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times
    
    
  @LoginWithAdmin
  Scenario: Add New Native User with Unique Incremental Details
    And User navigates to the settings
    And User enables admin mode
    And User clicks on 'Member Settings' Card
    And User sees the Add User button
    And User adds 1 members with name "PranaleeChaudhari", userId "UserId", password "Test@123", and email domain "testautomation.com" and can see toast message as 'Successfully added user' for all added members
    And User logs out from the application
    And User logs in with the last generated userId and password
    And User clicks on Open Settings icon
    When User clicks on My Profile
    And User can see 'Edit profile information' section on profile page
    Then User can see that the displayed User ID matches the generated userId
    And User can see that the displayed Name matches the generated name
    And User can see that the displayed Email matches the generated email
 