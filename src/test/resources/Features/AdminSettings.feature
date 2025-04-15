Feature: Admin Settings

  @LoginWithAdmin
  Scenario: Check admin have access to each setting
    Given User logs as Admin user in AI CORE application
    And User navigates to the settings
    And User enables admin mode
    Then User can view the following settings tile
      | SETTINGS_TILE     |
      | App Settings      |
      | Database Settings |
      | Function Settings |
      | Model Settings    |
      | Storage Settings  |
      | Vector Settings   |
      | Jobs              |
      | Member Settings   |
      | Team Permissions  |
      | Configuration     |
      | Admin Query       |
      | My Profile        |

  @LoginWithAdmin
  Scenario: Search on Settings Page
    Given User searches 'mod' in Search box
    Then User can view the 'Model Settings' tile
