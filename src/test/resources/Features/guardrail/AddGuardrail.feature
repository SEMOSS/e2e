@LoginWithAdmin @Regression @DeleteTestCatalog
Feature: Add Guardrail catalog

  Scenario: Create Guardrail catalog by filling the form- Gliner
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

  Scenario: Create Guardrail catalog by uploading ZIP file
    Given User opens Main Menu
    When User clicks on Guardrail
    And User clicks on Add Guardrail button
    And User clicks on file upload icon
    And User uploads the file 'Guardrail/Gliner.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    Then User can see a toast message as 'Successfully Created Guardrail Database'
