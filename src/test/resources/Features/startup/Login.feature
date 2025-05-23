# need to rework this as it uses AICoreTestManager and we log in the test user
Feature: AI Core application
 #Note: This commented 1st scenario is for MS user which we dont have in local host
  #Scenario: Login to the application
    #Given User is on application
    #When User enters username and password and click on SignIn button
    #Then User can navigate to home page
    #
  Scenario: Login with native credentials
    Given User logs out from the application
    And User is on application
    When User enters nativeUsername and nativePassword
    And User clicks on Login with native button
    Then User can navigate to home page