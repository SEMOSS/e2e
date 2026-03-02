Feature: Validate Pagination in Team Permission Settings

  @LoginWithAdmin @Regression
  Scenario: Validate Pagination in Team Permission Settings page for add members
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Member Settings' Card
    And User sees the Add User button
    And User adds 6 members with name "PaginationUser", userId "PaginationUserId", password "Test@123", and email domain "testautomation.com" and can see toast message as 'Successfully added user' for all added members
    And User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User clicks on "Add Team" button
    And User selects type as "Custom" from Type dropdown
    And User fills "Test Team1" in Name field of Add Team form
    And User fills Description as "Test Description" in Description field of Add Team form
    And User clicks on "Add" button in Add Team form
    And User clicks on "Add Member" button in Add Team Page
    And User adds "PaginationUser" from the member list
    And User clicks on save button
    Then User verifies pagination is working correctly
    And User opens Main Menu
    And User clicks on Open Settings
    And User enables admin mode
    And User clicks on 'Member Settings' Card
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 1 times

  @LoginWithAdmin @Regression
  Scenario: Validate Pagination in Team Permission Settings page for add engines
    Given User created '6' models with the 'OpenAI' model 'GPT 3.5 Turbo', catalog name 'Model 1', OpenAI key 'Test123'
    When User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User clicks on "Add Team" button
    And User selects type as "Custom" from Type dropdown
    And User fills "Test Team2" in Name field of Add Team form
    And User fills Description as "Test Description" in Description field of Add Team form
    And User clicks on "Add" button in Add Team form
    #And User clicks on the team name 'Test Team2' in the list
    And User clicks on 'Add Engine' button in Team Permission page
    And User adds multiple engines to the team
    And User select the engine access as 'Editor'
    And User clicks on save button
    And User verifies pagination is working correctly
    And User Delete the created Model

  @LoginWithAdmin @Regression
  Scenario: Validate Pagination in Team Permission Settings page for add Project
    Given User creates '6' 'drag and drop' apps with app name 'Pagination Test App', description 'Pagination Test Description', and tags 'Pagination, Test'
    When User opens Main Menu
    And User clicks on Open Settings
    And User enable admin mode
    And User clicks on 'Team Permissions' Card
    And User clicks on "Add Team" button
    And User selects type as "Custom" from Type dropdown
    And User fills "Test Team3" in Name field of Add Team form
    And User fills Description as "Test Description" in Description field of Add Team form
    And User clicks on "Add" button in Add Team form
    #And User clicks on the team name 'Test Team3' in the list
    And User clicks on 'Add Apps' button in Team Permission page
    And User adds multiple projects to the team
    And User select the engine access as 'Editor'
    And User clicks on save button
    And User verifies pagination is working correctly
    And User Delete the created Apps
