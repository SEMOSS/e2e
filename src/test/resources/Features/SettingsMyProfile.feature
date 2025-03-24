Feature: Settings 

Scenario: Settings - My Profile Page
Given User clicks on Open Settings icon 
When User clicks on My Profile
Then User can sees 'Privacy Center' link in the top right
And User can sees 'Profile Info' section
And User can sees 'Javascript SDK' section
And User can sees 'Python SDK' section
And User can sees 'Personal Access Tokens' section

