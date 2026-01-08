Feature: Validate layers

  @LoginWithAdmin @Regression @DeleteCreatedTestApp
  Scenario: Create and validate multiple pages
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User click on the 'Layers' tab in the left panel
    Then User should see '/page-1' in the Pages section of the left pane
    When User click on the Add new page icon to add a new page
    Then User should see '/page-2' in the Pages section of the left pane
    When User click on the Add new page icon to add a new page
    Then User should see '/page-3' in the Pages section of the left pane
    When User clicks on 'page-2' page
    Then User should be on 'page-2' page
    When User clicks on 'page-3' page
    Then User should be on 'page-3' page
    When User clicks on '/page-1' in the left pane
    Then User should be on 'page-1' page
