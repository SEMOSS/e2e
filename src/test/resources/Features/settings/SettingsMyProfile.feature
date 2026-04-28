@Regression
Feature: Settings My Profile

  #Scenario: Settings -My Profile - Edit profile information
    #Given User opens Main Menu
    #When User clicks on Open Settings
    #And User clicks on My Profile
    #Then User can see 'Edit profile information' section on profile page
    #And User can see the following field with their state:
      #| FIELD NAME | FIELD STATE |
      #| Name       | Enable      |
      #| User Id    | disable     |
      #| Username   | disable     |
      #| Email      | Enable      |
      #| Save       | disable     |
      #| Reset      | disable     |
    #When User update 'Name' field with 'Update Test Name'
    #And User update 'Email' field with 'update@test.com'
    #And User can see the following field with their state:
      #| FIELD NAME | FIELD STATE |
      #| Save       | Enable      |
      #| Reset      | Enable      |
    #And User clicks on 'Save' button
    #Then User can see success toast message "Successfully edited profile information"
    #When User opens Main Menu
    #And User clicks on Open Settings
    #And User enable admin mode
    #And User clicks on 'Member Settings' Card
    #Then User can see the the updated name as 'Update Test Name'
    #When User opens Main Menu
    #And User clicks on Profile Icon
    #Then User can see the the updated name as 'Update Test Name'

  Scenario: Settings -My Profile - Edit profile information - Veruify Change Password Option
    Given User opens Main Menu
    When User clicks on Open Settings
    And User clicks on My Profile
    And User clicks on Change Password link
    Then User can sees the 'Change Password' title
    When User enter the 'Current Password' as 'Abc@123'
    And User enter the 'New Password' as 'Abcd@12345'
    And User enter the 'Confirm New Password' as 'Abcd@12345'
    And User can see the following field with their state:
      | FIELD NAME      | FIELD STATE |
      | Change Password | Enable      |
      | Cancel          | Enable      |

  Scenario: Settings -My Profile - Edit profile information - Verify entering mismatched passwords for Change Password
    Given User opens Main Menu
    When User clicks on Open Settings
    And User clicks on My Profile
    And User clicks on Change Password link
    When User enter the 'New Password' as 'Abcd@12345'
    And User enter the 'Confirm New Password' as 'Abcd@123456'
    Then User can see the following field with their state:
      | FIELD NAME      | FIELD STATE |
      | Change Password | disable     |
      | Cancel          | Enable      |
    And User can see the error as 'The passwords do not match'
