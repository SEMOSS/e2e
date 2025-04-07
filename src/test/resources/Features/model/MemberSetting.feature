Feature: View Member Settings for Admin User
@LoginWithAdmin
 Scenario: View Member Settings
    Given User navigates to Open Setting page
    When User enable admin mode
    And  User clicks on 'Member Settings' Card
    Then User sees the Add User button
    And User sees Admin mode on
    And User sees atleast one count of users on Member setting page