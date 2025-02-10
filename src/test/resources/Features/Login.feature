Feature: AI Core application
 
  Scenario: Login to the application
    Given User is on application
    When User enters username and password and click on SignIn button
    Then User can navigate to home page