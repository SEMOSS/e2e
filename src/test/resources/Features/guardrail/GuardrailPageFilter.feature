Feature: Guardrail Page Filters

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: Validate the Guardrail filter on app
    Given User created '1' guardrail with the 'Gliner' guardrail, catalog name 'Ascending Gliner guardrail', NER Labels 'label', Default Threshold '1'
    Given User created '1' guardrail with the 'Gliner' guardrail, catalog name 'Descending Gliner guardrail', NER Labels 'label', Default Threshold '1'
    When User opens Main Menu
    And User clicks on Guardrail
    And User clicks on the 'Ascending' Filter button
    Then User can see the "Guardrail" are sorted in ascending order
    When User clicks on the 'Descending' Filter button
    Then User can see the "Guardrail" are sorted in descending order
    And User opens Main Menu
    When User clicks on Guardrail
    When User selects 'Date Created' from the Sort By dropdown
    And User clicks on the 'Ascending' Filter button
    Then User can see the "Guardrail" are sorted by date created in 'ascending' order
    When User clicks on the 'Descending' Filter button
    Then User can see the "Guardrail" are sorted by date created in 'descending' order