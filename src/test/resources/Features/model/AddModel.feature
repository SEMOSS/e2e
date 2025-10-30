@Regression
Feature: Add Model
  Adding LLM to the catalog

  Background: Create a Model - GPT-3.5
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog Name as 'Model'
    And User enters Open AI Key as 'Test@1234'
    And User enters Variable Name as 'Variable1'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks on Copy Catalog ID
    Then User can see the Model title as 'Model'

  @DeleteTestCatalog
  Scenario: Validate SMSS properties of a Model to catalog - GPT-3.5
    Given User can see the Model title as 'Model'
    When User clicks on SMSS
    And User can see name in 'NAME' field as 'Model' in SMSS properties
    And User can see var name in 'VAR_NAME' field as 'Variable1' in SMSS properties

  @DeleteTestCatalog
  Scenario: Edit SMSS properties of Model - GPT-3.5
    Given User can see the Model title as 'Model'
    When User clicks on SMSS
    And User clicks on Edit SMSS button
    And User can edit the value of 'KEEP_CONVERSATION_HISTORY' field as 'True'
    And User can edit the value of 'VAR_NAME' field as 'New_Name'
    And User clicks on Update SMSS button
    And User refresh the page
    And User can see updated value in 'KEEP_CONVERSATION_HISTORY' field as 'True'
    Then User can see updated value in 'VAR_NAME' field as 'New_Name'

  @DeleteTestCatalog
  Scenario: Adding tag to Model to catalog - GPT-3.5 - embeddings
    Given User can see the Model title as 'Model'
    When User clicks on Edit button
    And User add tags 'embeddings' and presses Enter
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'
    And User should see 'embeddings' on the page

  @DeleteTestCatalog
  Scenario: View Existing Models in Model Catalog Page
    Given User opens Main Menu
    When User clicks on Open Model
    And User searches the 'Model' in the model catalog searchbox
    Then User should see the 'Model' on the model catalog page

  @DeleteTestCatalog
  Scenario Outline: Edit Model Details
    Given User opens Main Menu
    When User clicks on Open Model
    When User searches the '<MODEL_NAME>' in the model catalog searchbox
    And User selects the '<MODEL_NAME>' from the model catalog
    And User clicks on Edit button
    And User enters the details as '<DETAILS>'
    And User enters the description as '<DESCRIPTION>'
    And User add tags '<TAGS>' and presses Enter
    And User enters the Domains as '<DOMAINS>'
    And User selects '<DATA_CLASSIFICATION>' from the Data Classification dropdown
    And User selects '<DATA_RESTRICTIONS>' from the Data Restrictions dropdown
    And User clicks on Submit button
    Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'
    And User should see description as '<DESCRIPTION>' on the page
    And User should see '<TAGS>' on the page
    And User should see '<DETAILS>' in the overview Details section
    And User should see '<TAGS>' in the overview Tag section
    And User should see '<DOMAINS>' in the overview Domain section
    And User should see '<DATA_CLASSIFICATION>' in the overview Data classification section
    And User should see '<DATA_RESTRICTIONS>' in the overview Data restrictions section

    Examples: 
      | MODEL_NAME | DETAILS       | DESCRIPTION                | TAGS                            | DOMAINS          | DATA_CLASSIFICATION  | DATA_RESTRICTIONS                     |
      | Model      | GPT-3.5 model | This is GPT-3.5 test model | embeddings, Test1, Test2, Test3 | SAP, AI, Finance | IP, PHI, PII, PUBLIC | IP ALLOWED, PHI ALLOWED, FOUO ALLOWED |

  @DeleteTestCatalog
  Scenario: Validate Model Catalog ID in Usage commands
    Given User can see the Model title as 'Model'
    When User copies the model catalog ID below the title using copy icon
    And User clicks on Usage tab
    When User copies code contents and validate model catalog Id occurences in sections:
      | SECTIONS                                                 | EXPECTED_MODEL_ID_COUNT |
      | How to use in Javascript                                 |                       5 |
      | How to use in Python                                     |                       1 |
      | How to use with Langchain API                            |                       1 |
      | How to use externally with OpenAI API and our Python SDK |                       3 |
      | How to use in Java                                       |                       1 |
