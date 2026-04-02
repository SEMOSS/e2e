@LoginWithAdmin @Regression @DeleteTestCatalog
Feature: Add all model types

  Scenario Outline: Create '<MODEL>' model and validate its SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    And User clicks on Add Model
    And User clicks on the '<GROUP>' tab
    And User selects '<MODEL>'
    Then User can see following form sections with fields:
      | SECTION_NAME | FIELDS      |
      | <S1_NAME>    | <S1_FIELDS> |
      | <S2_NAME>    | <S2_FIELDS> |
      | <S3_NAME>    | <S3_FIELDS> |
    And User can see following fields are mandatory fields
      | <MANDATORY_FIELDS> |
    When User fills the model creation form with:
      | <FORM_FIELDS> |
    Then User can see 'Create Model' button becomes enabled
    When User clicks on model 'Create Model' button
    #Then User can see a toast message as 'Successfully added LLM to catalog'
    When User clicks on Copy Catalog ID
    Then User can see the Model title as '<CATALOG_NAME>'
    When User clicks on SMSS
    Then User can see following fields in SMSS Properties
      | <SMSS_FIELDS> |

    Examples: 
      | GROUP        | MODEL         | S1_NAME | S1_FIELDS                                        | S2_NAME     | S2_FIELDS                                      | S3_NAME  | S3_FIELDS                                                                                                                  | MANDATORY_FIELDS                                                                                                                                                                                                            | FORM_FIELDS                                                                                                                                                                         | CATALOG_NAME       | SMSS_FIELDS                                                                                                                                                                                                                   |
      | Azure OpenAI | Azure Open AI | General | Catalog Name, Model (Deployment Name), Chat Type | Credentials | Azure Endpoint, Azure Open AI Key, API Version | Settings | Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History, Init Script | Catalog Name, Model (Deployment Name), Chat Type, Azure Endpoint, Azure Open AI Key, API Version, Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History,Init Script | Catalog Name=Azure Open AI, Model (Deployment Name)=Azure Ai, Azure Endpoint=https://www.google.com, Azure Open AI Key=a23, API Version=3, Max Tokens (Max Completion Tokens)=10, Context Window=5000, Record Questions and Responses=false, Keep Conversation History=false | Azure Open AI | NAME=Azure Open AI, MODEL=claude-haiku-4-5-20251001, PROVIDER=anthropic, INIT_MODEL_ENGINE=import genai_client;${VAR_NAME}, KEEP_CONVERSATION_HISTORY=false, KEEP_INPUT_OUTPUT=false, CONTEXT_WINDOW=5000, MAX_TOKENS=10 |
     
     
     
      #| Azure OpenAI | Claude Opus 4.6       | General | Catalog Name, Model ID, Provider | Credentials | API Key   | Settings | Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History, Init Script | Catalog Name, Model ID, Provider, API Key, Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History | Catalog Name=Claude Opus Model, API Key=Test123, Max Tokens (Max Completion Tokens)=10, Context Window=5000, Record Questions and Responses=false, Keep Conversation History=false                                     | Claude Opus Model     | NAME=Claude Opus Model, MODEL=claude-opus-4-6, PROVIDER=anthropic, INIT_MODEL_ENGINE=import genai_client;${VAR_NAME}, KEEP_CONVERSATION_HISTORY=false, KEEP_INPUT_OUTPUT=false, CONTEXT_WINDOW=5000, MAX_TOKENS=10            |
      #| Azure OpenAI | Claude Sonnet 4.6     | General | Catalog Name, Model ID, Provider | Credentials | API Key   | Settings | Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History, Init Script | Catalog Name, Model ID, Provider, API Key, Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History | Catalog Name=Claude Sonnet Model, API Key=Test123, Max Tokens (Max Completion Tokens)=10, Context Window=5000, Record Questions and Responses=false, Keep Conversation History=false                                   | Claude Sonnet Model   | NAME=Claude Sonnet Model, MODEL=claude-sonnet-4-6, PROVIDER=anthropic, INIT_MODEL_ENGINE=import genai_client;${VAR_NAME}, KEEP_CONVERSATION_HISTORY=false, KEEP_INPUT_OUTPUT=false, CONTEXT_WINDOW=5000, MAX_TOKENS=10        |
      #| Azure OpenAI | Other Anthropic Model | General | Catalog Name, Model ID, Provider | Credentials | API Key   | Settings | Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History, Init Script | Catalog Name, Model ID, Provider, API Key, Max Tokens (Max Completion Tokens), Context Window, Record Questions and Responses, Keep Conversation History | Catalog Name=Other Anthropic Model, Model ID=Other Anthropic Model, API Key=Test123, Max Tokens (Max Completion Tokens)=10, Context Window=5000, Record Questions and Responses=false, Keep Conversation History=false | Other Anthropic Model | NAME=Other Anthropic Model, MODEL=Other Anthropic Model, PROVIDER=anthropic, INIT_MODEL_ENGINE=import genai_client;${VAR_NAME}, KEEP_CONVERSATION_HISTORY=false, KEEP_INPUT_OUTPUT=false, CONTEXT_WINDOW=5000, MAX_TOKENS=10  |
