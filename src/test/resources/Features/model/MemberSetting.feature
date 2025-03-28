Feature: View Member Settings for Admin User
@NativeAdmin
 Scenario: View Member Settings
    Given User navigates to Open Setting page
    When User enable admin mode
    And  User clicks on 'Member Settings' Card
    Then User sees the Add User button
    And User sees Admin mode on
    And User sees a count of users on Member setting page