Feature: Settings 

Scenario: Settings - My Profile Page
  Given User clicks on Open Settings icon 
  When User clicks on My Profile
  Then User can see 'Privacy Center' link in the top right
  And User can see 'Edit profile information' section on profile page
  And User can see 'Javascript SDK' section on profile page
  And User can see 'Python SDK' section on profile page
  And User can see 'Personal Access Tokens' section on profile page