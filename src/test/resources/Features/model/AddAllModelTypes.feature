@LoginWithAuthor @Regression
Feature: Add all model types

  Scenario Outline: Create '<MODEL>' model and validate its SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User clicks on '<GROUP>' tab
    And User clicks on '<MODEL>' model
    Then User can see following form sections with fields:
      | SECTION_NAME | FIELDS      |
      | <S1_NAME>    | <S1_FIELDS> |
      | <S2_NAME>    | <S2_FIELDS> |
      | <S3_NAME>    | <S3_FIELDS> |
    And User can see following fields are mandatory fields
      | <MANDATORY_FIELDS> |
    When User fills the model creation form with:
      | <FORM_FIELDS> |
    Then User can see 'Connect' button becomes enabled
    When User clicks on 'Connect' button
    Then User can see a toast message as 'Successfully added LLM to catalog'
    When User clicks on Copy Catalog ID
    Then User can see the Model title as '<CATALOG_NAME>'
    When User clicks on SMSS
    Then User can see following fields in SMSS Properties
      | <SMSS_FIELDS> |

    Examples: 
      | GROUP  | MODEL               | S1_NAME | S1_FIELDS                      | S2_NAME     | S2_FIELDS      | S3_NAME  | S3_FIELDS                                                                                                                       | MANDATORY_FIELDS                                                                                                       | FORM_FIELDS                                                                                                                                                                                                                               | SMSS_FIELDS                                                                                                                                                                                                                              | CATALOG_NAME  |
      | OpenAI | Other OpenAI models | General | Catalog Name, Model, Chat Type | Credentials | OpenAI API Key | Settings | Max Completion Tokens, Max Input Tokens, Context Window, Record Questions and Responses, Keep Conversation History, Init Script | Catalog Name, Model, Chat Type, Open AI Key, Context Window, Record Questions and Responses, Keep Conversation History | Catalog Name=My Test Model, Model=gpt-3.5-turbo, Chat Type=chat-completion, OpenAI API Key=ABC, Max Completion Tokens=10, Max Input Tokens=20, Context Window=5000, Record Questions and Responses=false, Keep Conversation History=false | NAME=My Test Model, MODEL=gpt-3.5-turbo, CHAT_TYPE=chat-completion, INIT_MODEL_ENGINE=import genai_client;${VAR_NAME}, KEEP_CONVERSATION_HISTORY=false, KEEP_INPUT_OUTPUT=false, CONTEXT_WINDOW=5000, MAX_TOKENS=10, MAX_INPUT_TOKENS=20 | My Test Model |
