Feature: View Member Settings

  Scenario: View Member Settings
    Given I am logged as Admin user in AI CORE application
    When I go to the settings
    And I enable admin mode
    And I select Member Settings
    Then I should see the Add User button
    And I should see Admin on
    And I should see a count of users