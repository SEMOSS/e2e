Feature: View existing databases on database catalog page

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate access status of created Database catalog
    Given User opens Main Menu
    When User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User sees the database name 'TestDatabase' in the database catalog
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Private' on the tooltip
    When User clicks on the database name 'TestDatabase' in the database catalog
    And User clicks on Access Control Tab
    And User clicks on make 'Database' public button
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User sees the database name 'TestDatabase' in the database catalog
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Global' on the tooltip

  @LoginWithAdmin @Regression
  Scenario: Delete database catalog from dashboard and validate delete confirmation pop-up
    When User get the catalog ID
    And User opens Main Menu
    And User clicks on Open Database
    And User searches the 'TestDatabase' in the database Catalog searchbox
    And User sees the database name 'TestDatabase' in the database catalog
    When User clicks on 'Delete Engine' option from catalog card options
    Then User should see a delete confirmation pop-up with message 'Are you sure you want to delete this engine?'
    And User should see the Engine name as 'TestDatabase' on the delete confirmation pop-up for 'Database' catalog
    And User should see the Engine ID on the delete confirmation pop-up
    And User sees the 'Cancel' button on the delete confirmation pop-up
    And User sees the 'Delete' button on the delete confirmation pop-up
    When User clicks on 'Delete' button
    Then User can see a toast message as 'Successfully deleted TestDatabase' engine for 'Database' catalog
