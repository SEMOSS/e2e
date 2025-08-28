Feature: User Management

  @LoginWithAdmin
  Scenario: Add New Native User
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Member Settings' Card
    Then User sees the Add User button
    And User adds 11 members and can see toast message as 'Successfully added user' for all added members
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 2 times

  @LoginWithAdmin
  Scenario: Edit Native User - Change Model Limit Restriction
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
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
  Scenario: Update Configuration Settings - access_keys_allowed - true - Adfs
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Configuration' Card
    And User clicks on 'access_keys_allowed' value
    And User change value of the key to 'true'
    And User clicks on Save button of the configuration
    Then User can see a toast message after updating values of 'Adfs' as "Successfully modified adfs properties"

  @LoginWithAdmin
  Scenario: Update Configuration Settings - access_keys_allowed - true - native
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Configuration' Card
    And User clicks on Authentication dropdown
    And User search 'Native' and select
    And User clicks on 'access_keys_allowed' value
    And User change value of the key to 'true'
    And User clicks on Save button of the configuration
    And User can see a toast message after updating values of 'Native' as "Successfully modified native properties"

  @LoginWithAdmin
  Scenario: Add New Native User with Unique Incremental Details - Validate profile info
    Given User opens Main Menu
    When User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Member Settings' Card
    And User sees the Add User button
    And User adds 1 members with name "TestUser", userId "TestUserId", password "Test@123", and email domain "testautomation.com" and can see toast message as 'Successfully added user' for all added members
    And User logs out from the application
    And User logs in with the last generated userId and password
    And User opens Main Menu
    And User clicks on Open Settings
    When User clicks on My Profile
    And User can see 'Edit profile information' section on profile page
    Then User can see that the displayed User ID matches the generated userId
    And User can see that the displayed Name matches the generated name
    And User can see that the displayed Email matches the generated email
    And User logs out from the application
    And User login as "Admin"
    And User opens Main Menu
    And User clicks on Open Settings
    And User enables admin mode
    And User clicks on 'Member Settings' Card
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times
