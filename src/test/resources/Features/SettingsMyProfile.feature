Feature: Settings

  Scenario: Settings - My Profile Page
    Given User clicks on Open Settings icon
    When User clicks on My Profile
    Then User can see 'Privacy Center' link in the top right
    And User can see 'Edit profile information' section on profile page
    And User can see 'Javascript SDK' section on profile page
    And User can see 'Python SDK' section on profile page
    And User can see 'Personal Access Tokens' section on profile page

  Scenario: Generate a new access key and verify the details
    Given User clicks on Open Settings icon
    When User clicks on My Profile
    And User clicks on New Key button
    Then User fills Name as 'New Key' in Name field
    And User fills Description as 'New Description' in Description field
    And User clicks on Generate button
    When User copies the 'Access Key' using copy icon and validate its alphanumeric
    And User copies the 'Secret Key' using copy icon and validate its alphanumeric
    Then User copies contents using copy icon from example section and validate count of Access Key and Secret Key occurences in sections:
      | SECTIONS           | ACCESS KEY COUNT | SECRET KEY COUNT |
      | Javascript Example |                1 |                1 |
      | Python Example     |                1 |                1 |

  Scenario: Generated key - Delete
    Given User clicks on Open Settings icon
    When User clicks on My Profile
    And User clicks on New Key button
    Then User fills Name as 'New Key' in Name field
    And User fills Description as 'New Description' in Description field
    And User clicks on Generate button
    Then User clicks on Close button
    And User clicks on delete icon of the generated 'New Key'
    And User can sees the Toastmessage as "Successfully deleted key"
