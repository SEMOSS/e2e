Feature: Settings 

Scenario: Settings - My Profile Page
  Given User clicks on Open Settings icon 
  When User clicks on My Profile
  Then User can see 'Privacy Center' link in the top right
  And User can see 'Edit profile information' section on profile page
  And User can see 'Javascript SDK' section on profile page
  And User can see 'Python SDK' section on profile page
  And User can see 'Personal Access Tokens' section on profile page
  
Scenario: Settings - Personal Access Token
  Given User clicks on Open Settings icon 
  When User clicks on My Profile
  And User clicks on New Key button
  Then User fills Name as 'New Key' in Name field
  And User fills Description as 'New Description' in Description field
  And User clicks on Generate button
  Then User clicks on Close button
  Then User can see 'Personal Access Tokens' section on profile page
  And User can see generated key name as 'New Key'
  And User can see generated key description as 'New Description'