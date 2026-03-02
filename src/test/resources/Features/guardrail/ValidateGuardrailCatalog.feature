@LoginWithAdmin @Regression @DeleteTestCatalog
Feature: Validate Guardrail catalog

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

  Scenario Outline: Edit guardrail catalog
    Given User can see the Guardrail Catalog title as 'Gliner guardrail'
    When User clicks on Edit button
    And User enters the details as '<DETAILS>'
    And User enters the description as '<DESCRIPTION>'
    And User add Tags '<TAGS>' and presses Enter
    And User enters the Domains as '<DOMAINS>'
    And User selects '<DATA_CLASSIFICATION>' from the Data Classification dropdown
    And User selects '<DATA_RESTRICTIONS>' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'
    And User should see description as '<DESCRIPTION>' on the page
    #And User should see '<TAGS>' on the page
    And User should see '<DETAILS>' in the overview Details section
    And User should see '<TAGS>' in the overview Tag section
    And User should see '<DOMAINS>' in the overview Domain section
    And User should see '<DATA_CLASSIFICATION>' in the overview Data classification section
    And User should see '<DATA_RESTRICTIONS>' in the overview Data restrictions section

    Examples: 
      | DETAILS          | DESCRIPTION                   | TAGS                            | DOMAINS          | DATA_CLASSIFICATION  | DATA_RESTRICTIONS                     |
      | Gliner guardrail | Test Gliner guardrail catalog | embeddings, Test1, Test2, Test3 | SAP, AI, Finance | IP, PHI, PII, PUBLIC | IP ALLOWED, PHI ALLOWED, FOUO ALLOWED |

  Scenario: Export created guardrail catalog
    Given User can see the Guardrail Catalog title as 'Gliner guardrail'
    When User clicks on Export button
    Then User sees export success toast message as 'Guardrail engine download started'
    And User sees catalog zip file downloaded
    And User sees downloaded zip file name contains 'Gliner guardrail'
