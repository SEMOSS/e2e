Feature: View existing Guardrail on Guardrail Catalog Page

  Background: Create Guardrail catalog
    Given User opens Main Menu
    When User clicks on Guardrail
    And User clicks on Add Guardrail button
    And User selects 'Gliner'
    And User enters guardrail Catalog Name as 'Gliner guardrail'
    And User enters NER Labels as 'label' and presses Enter
    And User enters Default Threshold as '1'
    And User clicks on 'Connect' button
    And User clicks on Copy Catalog ID
    Then User can see a toast message as 'Successfully added new guardrail to catalog'
    And User can see the Guardrail Catalog title as 'Gliner guardrail'

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: view and validate filter functionality - Guardrails
    When User clicks on Edit button
    And User enters the details as 'Gliner guardrail'
    And User enters the description as 'Test Gliner guardrail catalog'
    And User add Tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI, Finance'
    And User selects 'IP, PHI, PII, PUBLIC' from the Data Classification dropdown
    And User selects 'IP ALLOWED, PHI ALLOWED, FOUO ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'
    And User opens Main Menu
    And User clicks on Guardrail
    And User applies each filter and validate 'Gliner guardrail' catalog is visible on the 'Guardrail' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE                          |
      | Tag                 | embeddings, Test1                     |
      | Domain              | SAP, AI                               |
      | Data Classification | IP, PHI, PII, PUBLIC                  |
      | Data Restrictions   | IP ALLOWED, PHI ALLOWED, FOUO ALLOWED |

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate access status of created Guardrail catalog
    Given User opens Main Menu
    When User clicks on Guardrail
    And User searches the 'Gliner guardrail' in the guardrail Catalog searchbox
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Private' on the tooltip
    When User selects the 'Gliner guardrail' from the guardrail catalog
    And User clicks on Access Control Tab
    And User clicks on make 'Guardrail' public button
    And User opens Main Menu
    And User clicks on Guardrail
    And User searches the 'Gliner guardrail' in the guardrail Catalog searchbox
    When User mouse hover on Lock icon displayed on catalog card
    Then User can see engine access status as 'Global' on the tooltip

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate content of created Guardrail catalog card
    When User get the catalog ID
    And User clicks on Edit button
    And User add Tags 'embeddings, Test1' and presses Enter
    And User clicks on Submit button
    And User opens Main Menu
    And User clicks on Guardrail
    And User searches the 'Gliner guardrail' in the guardrail Catalog searchbox
    And User should see the catalog ID on the catalog card
    And User should see the tags 'embeddings, Test1' on the 'Guardrail' catalog card
    And User should see the catalog created date on the catalog card
    And User should see the following icons on the catalog card
      | lock                |
      | bookmark            |
      | view logs dashboard |
      | delete              |

  @LoginWithAdmin @Regression
  Scenario: Delete guardrail catalog from dashboard and validate delete confirmation pop-up
    When User get the catalog ID
    And User opens Main Menu
    And User clicks on Guardrail
    And User searches the 'Gliner guardrail' in the guardrail Catalog searchbox
    When User clicks on 'Delete Engine' option from catalog card options
    Then User should see a delete confirmation pop-up with message 'Are you sure you want to delete this engine?'
    And User should see the Engine name as 'Gliner guardrail' on the delete confirmation pop-up for 'Guardrail' catalog
    And User should see the Engine ID on the delete confirmation pop-up
    And User sees the 'Cancel' button on the delete confirmation pop-up
    And User sees the 'Delete' button on the delete confirmation pop-up
    When User clicks on 'Delete' button
    Then User can see a toast message as 'Successfully deleted Gliner guardrail' engine for 'Guardrail' catalog
