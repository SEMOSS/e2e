Feature: AI Core application login with test user
 
  Scenario: Login with different credentials
    Given User is on application
    When User enters nativeUsername and nativePassword
    And User clicks on Login with native button
    Then User can navigate to home page
 
    