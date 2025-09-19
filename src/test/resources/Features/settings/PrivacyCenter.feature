Feature: Privacy Center Popup Validation

  Scenario: Validate Privacy Center popup and its elements
    Given User opens Main Menu
    When User clicks on Open Settings
    And User clicks on Privacy Center button
    Then Use can see Privacy popup with following elements
      | ELEMENT      | EXPECTED_RESULT           |
      | Popup        |                           |
      | Title        | Privacy Preference Center |
      | Close Icon   |                           |
      | Close button |                           |
    When User clicks on Close icon
   Then Privacy popup should close
    And User clicks on Privacy Center button
    When User clicks on 'Close' button
    Then Privacy popup should close
