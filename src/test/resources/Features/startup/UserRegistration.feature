Feature: Register new user

  Scenario: Register user and login
    Given User logs out from the application
    When User clicks on Register Now button
    And User fills below user creation form fields
      | FIELD_NAME       | FIELD_VALUE    |
      | First Name       | John           |
      | Last Name        | John           |
      | Username         | user           |
      | Email            | user@gmail.com |
      | Phone Number     |     1234567890 |
      | Phone Extention  | ext.           |
      | Country Code     | +91            |
      | Password         | Pass@          |
      | Confirm Password | Pass@          |
    And User clicks on Register button
    Then User can see 'Account registration successful. Log in below.' message on login page
    When User enters username as 'user' and password 'Pass@'
    And User clicks on Login button
    Then User should navigate to home page
