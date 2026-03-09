@LoginWithAdmin @Regression @DeleteTestCatalog
Feature: Validate Guardrail catalog Search functionality

  Scenario Outline: Create Guardrail catalog
    Given User opens Main Menu
    When User clicks on Guardrail
    And User clicks on Add Guardrail button
    And User selects '<GUARDRAIL_TYPE>'
    And User enters guardrail Catalog Name as '<CATALOG_NAME>'
    And User enters NER Labels as '<NER_LABELS>' and presses Enter
    And User enters Default Threshold as '<DEFAULT_THRESHOLD>'
    And User clicks on 'Connect' button
    And User clicks on Copy Catalog ID
    Then User can see a toast message as '<TOAST_MESSAGE>'
    And User can see the Guardrail Catalog title as '<CATALOG_NAME>'
    When User enters '<CATALOG_NAME>' in the search box
    Then User should see the Guardrail Catalog title as '<CATALOG_NAME>' in search results

    Examples:
      | GUARDRAIL_TYPE | CATALOG_NAME       | NER_LABELS | DEFAULT_THRESHOLD | TOAST_MESSAGE                               |
      | Gliner         | Gliner guardrail   | label      |                 1 | Successfully added new guardrail to catalog |
      | Gliner         | Gliner guardrail 2 | label      |                 1 | Successfully added new guardrail to catalog |
