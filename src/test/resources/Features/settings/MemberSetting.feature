Feature: View Member Settings for Admin User

  Background: View Member Settings
    Given User navigates to Open Setting page
    When User enable admin mode
    And User clicks on 'Member Settings' Card
    Then User sees the Add User button
    And User sees Admin mode on
    And User sees atleast one count of users on Member setting page

  @LoginWithAdmin
  Scenario: Validate Pagination in member setting page
    Given User sees atleast one count of users on Member setting page
    When User adds 10 members and can see toast message as 'Successfully added user' for all added members
    #Then User select '25' in Rows per page filter
    #And User sees the '25' rows in the page
    #Then User clicks on the Right pagination arrow to navigate to next page
    #Then User clicks on the Left pagination arrow to navigate to previous page
    #Then User select '50' in Rows per page filter
    #Then User sees the '50' rows in the page
    #Then User clicks on the Right pagination arrow to navigate to next page
    #Then User clicks on the Left pagination arrow to navigate to previous page
    #Then User select '100' in Rows per page filter
    #Then User sees the '100' rows in the page
    #Then User clicks on the Right pagination arrow to navigate to next page
    #Then User clicks on the Left pagination arrow to navigate to previous page
    And User sees the search button
    And User searches for the created user
    And User clicks on Delete Selected button 10 times

  Scenario: Validate Search Functionality
    Given User sees atleast one count of users on Member setting page
    When User adds 1 members and can see toast message as 'Successfully added user' for all added members
    Then User clicks on search button
    Then User searchs for user having username 'Name1'
    And User sees the 'Name1' in the searched user list
    And User sees the count of user as '1' in searched result
    And User clicks on Delete Selected button 1 times

  Scenario: Add Native User
    Given User sees a count of member
    When User adds 1 members and can see toast message as 'Successfully added user' for all added members
    Then User sees the updated count of members increase by 1
    And User clicks on search button
    And User searchs for user having username 'Name1'
    And User sees the 'Name1' in the searched user list
    And User sees the count of user as '1' in searched result
    And User clicks on Delete Selected button 1 times