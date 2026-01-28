Feature: Validate layers

  Background: 
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app

  @LoginWithAdmin @Regression @DeleteCreatedTestApp
  Scenario: Create and validate multiple pages
    When User click on the 'Layers' tab in the left panel
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

  @LoginWithAdmin @Regression @DeleteCreatedTestApp
  Scenario: Reorder the layers
    When User clicks on Blocks if it is not selected by default
    And User drags the 'Scatter Plot' block and drops it on the page
    And User drags the 'Logs' block and drops it on the page
    And User drags the 'Link' block and drops it on the page
    And User click on the 'Layers' tab in the left panel
    And User moves the 'E-chart' block 'above' the 'Link' block
    Then User should see 'E-chart' block appear 'above' the 'Link' block
    And User moves the 'Link' block 'inside' the 'Container' block
    Then User should see 'Link' block appear 'inside' the 'Container' block
    And User moves the 'Text' block 'outside' the 'Container' block
    Then User should see 'Text' block appear 'outside' the 'Container' block
    And User clicks on the Save App icon

  @LoginWithAdmin @Regression @DeleteCreatedTestApp
  Scenario: Delete and duplicate the layers
    When User clicks on Blocks if it is not selected by default
    And User drags the 'Scatter Plot' block and drops it on the page
    And User click on the 'Layers' tab in the left panel
    And User duplicate the 'E-chart' layer
    Then User should see another 'E-chart' block appear on the page
    When User delete the 'Container' layer
    Then User should not see 'Container' block on the page
    And User clicks on the Save App icon
