Feature: Register new user

  Scenario: Register new user and login with created user
    Given User logs out from the application
    When User clicks on Register Now button
    And User fills below user creation form fields
      | FIELD_NAME       | FIELD_VALUE               |
      | First Name       | John                      |
      | Last Name        | Smith                     |
      | Username         | user<RANDOM_ID>           |
      | Email            | user<RANDOM_ID>@gmail.com |
      | Phone Number     | 12<RANDOM_ID>             |
      | Phone Extention  | ext.                      |
      | Country Code     | +91                       |
      | Password         | Pass@<RANDOM_ID>          |
      | Confirm Password | Pass@<RANDOM_ID>          |
    And User clicks on Register button
    Then User can see 'Account registration successful. Log in below.' message on login page
    When User enters username as 'user<RANDOM_ID>' and password 'Pass@<RANDOM_ID>'
    And User clicks on Login button
    Then User should navigate to home page
