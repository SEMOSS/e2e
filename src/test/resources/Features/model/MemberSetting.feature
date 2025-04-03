Feature: View Member Settings for Admin User

Background: User is on member setting page 
    Given User navigates to Open Setting page
    When User enable admin mode
    And  User clicks on 'Member Settings' Card

@LoginWithAdmin
 Scenario: View Member Settings
    Then User sees the Add User button
    And User sees Admin mode on
    And User sees atleast one count of users on Member setting page
    
   
 @LoginWithAdmin
 Scenario: Validate Pagination in member setting page
   Given User sees atleast one count of users on Member setting page
   When User sees Member count equal or greater than '28'
   Then User select '5' in Rows per page filter
   And User sees the '5' rows in the page
   Then User clicks on the Right pagination arrow to navigate to next page
   Then User clicks on the Left pagination arrow to navigate to previous page 
   Then User select '10' in Rows per page filter
   Then User sees the '10' rows in the page
   Then User clicks on the Right pagination arrow to navigate to next page
   Then User clicks on the Left pagination arrow to navigate to previous page
   Then User select '20' in Rows per page filter
   Then User sees the '20' rows in the page
   Then User clicks on the Right pagination arrow to navigate to next page
   Then User clicks on the Left pagination arrow to navigate to previous page  
   
@LoginWithAdmin   
 Scenario: Validate Search Functionality 
  Given User sees atleast one count of users on Member setting page
  When User clicks on search button 
  Then User clicks on search input bar 
  And User enters the 'testuser'
  And User sees the 'testuser' in the searched user list 
  And User sees the count of user as '1' in searched result 