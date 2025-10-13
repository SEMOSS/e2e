Feature: Add all model types

  @DeleteTestCatalog
  Scenario: Create Model of GPT-3.5 and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'GPT-3.5'
    And User enters Catalog name as 'GPT3_Model'
    And User select type as 'Open AI'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable'
    And User select chat type as 'chat-completion'
    And User enter Init Script as 'InitScript_GPT3.5'
    And User select the keep conversation history as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "GPT 3.5" model
      | NAME                      |
      | MODEL_TYPE                |
      | OPEN_AI_KEY               |
      | VAR_NAME                  |
      | CHAT_TYPE                 |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'GPT3 Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName   | fieldValue |
      | NAME        | GPT3_Model |
      | VAR_NAME    | Variable   |
      | CHAT_TYPE   | chat-completion |
      | INIT_MODEL_ENGINE | import genai_client;${VAR_NAME} |
      | KEEP_CONVERSATION_HISTORY | false|
      |KEEP_INPUT_OUTPUT|false|
      |KEEP_INPUT_OUTPUT | false |
      

  @DeleteTestCatalog
  Scenario: Create Model of GPT-4 and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'GPT-4'
    And User enters Catalog name as 'GPT4_Model'
    And User select type as 'Open AI'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'GPT4_Variable'
    And User select chat type as 'chat-completion'
    And User enter Init Script as 'InitScript_GPT4'
    And User select the keep conversation history as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "GPT 4" model
      | NAME                      |
      | MODEL_TYPE                |
      | OPEN_AI_KEY               |
      | VAR_NAME                  |
      | CHAT_TYPE                 |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'GPT4 Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName   | fieldValue    |
      | NAME        | GPT4_Model    |
      | VAR_NAME    | GPT4_Variable |
      | CHAT_TYPE   | chat-completion |
      | INIT_MODEL_ENGINE | import genai_client;${VAR_NAME} |
      | KEEP_CONVERSATION_HISTORY | false|
      |KEEP_INPUT_OUTPUT|false|
      |KEEP_INPUT_OUTPUT | false |

  @DeleteTestCatalog
  Scenario: Create Model of Text-Davinci and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Text-Davinci'
    And User enters Catalog name as 'Text_Davinci_Model'
    And User select type as 'Open AI'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable_Davinci'
    And User enter Init Script as 'InitScript_Davinci'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "Text Davinci" model
      | NAME              |
      | MODEL_TYPE        |
      | OPEN_AI_KEY       |
      | VAR_NAME          |
      | INIT_MODEL_ENGINE |
      | KEEP_INPUT_OUTPUT |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'Text Davinci Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName   | fieldValue         |
      | NAME        | Text_Davinci_Model |
      | VAR_NAME    | Variable_Davinci   |
      | INIT_MODEL_ENGINE | from genai_client import OpenAiE |
      | KEEP_INPUT_OUTPUT | false           |
      |KEEP_INPUT_OUTPUT | false |


  @DeleteTestCatalog
  Scenario: Create Model of DALL E and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'DALL E'
    And User select type as 'Open AI'
    And User enters Catalog name as 'DALL_E_Model'
    And User enters open AI Key as 'Test@1234'
    And User enters var name as 'Variable_DALLE'
    And User enter Init Script as 'InitScript_DALL E'
    And User select the keep conversation history as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Input Tokens "18"
    And User enter the Max Input Tokens "18"
    Then User can enable Submit button after filling mandatory fields for "DALL E" model
      | NAME                      |
      | MODEL_TYPE                |
      | OPEN_AI_KEY               |
      | VAR_NAME                  |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'DALL E Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName         | fieldValue        |
      | NAME              | DALL_E_Model      |
      | VAR_NAME          | Variable_DALLE    |
      | INIT_MODEL_ENGINE | InitScript_DALL E |
      | KEEP_CONVERSATION_HISTORY | false|
      |KEEP_INPUT_OUTPUT | false|
      |KEEP_INPUT_OUTPUT | false|
      

  @DeleteTestCatalog
  Scenario: Create Model of Palm Chat Bison and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Palm Chat Bison'
    And User enters Catalog name as 'Palm_Chat_Bison_Model'
   #type not able to edit
  #   And User select the Type as 'Vertex'
    And User enter model name as 'mode-bison-001'
    And User enter GCP Region as 'Palm Region'
    And User enters var name as 'Variable_Palm_Chat_Bison'
    And User select chat type as "text"
    And User enter Init Script as 'InitScript_Palm_Chat_Bison'
    And User select the keep conversation history as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "Palm Chat Bison" model
      | NAME                      |
      | MODEL_TYPE                |
      | GCP_REGION                |
      | MODEL                     |
      | VAR_NAME                  |
      | CHAT_TYPE                 |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'Palm Chat Bison Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName  | fieldValue               |
      | NAME       | Palm_Chat_Bison_Model    |
      | GCP_REGION | Palm Region              |
      | VAR_NAME   | Variable_Palm_Chat_Bison |
      | CHAT_TYPE  | text                     |
      | INIT_MODEL_ENGINE | import genai_client;${VAR_NAME} |
      | KEEP_CONVERSATION_HISTORY | false|
      |KEEP_INPUT_OUTPUT | false|
      |KEEP_INPUT_OUTPUT | false|


  @DeleteTestCatalog
  Scenario: Create Model of Eleuther GPTJ and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Eleuther GPTJ'
    And User enters Catalog name as 'Eleuther_GPTJ_Model'
    And User enter model name as 'Eleuther GPTJ'
    And User select the Type as 'Text Generation'
    And User enter the Endpoint as 'https://azureopenai.com/'
    And User enters var name as 'Variable_Eleuther_GPTJ'
    And User select chat type as "chat-completion"
    And User enter Init Script as 'InitScript_Eleuther_GPTJ'
    And User select the keep conversation history as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "Eleuther GPTJ" model
      | NAME                      |
      | MODEL                     |
      | MODEL_TYPE                |
      | ENDPOINT                  |
      | VAR_NAME                  |
      | CHAT_TYPE                 |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'Eleuther GPTJ Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName         | fieldValue               |
      | NAME              | Eleuther_GPTJ_Model      |
      | ENDPOINT          | https://azureopenai.com/ |
      | VAR_NAME          | Variable_Eleuther_GPTJ   |
      | INIT_MODEL_ENGINE | InitScript_Eleuther_GPTJ |
      | MODEL_TYPE        | TEXT_GENERATION          |
      | CHAT_TYPE         | chat-completion          |
      | KEEP_CONVERSATION_HISTORY | false|
      |KEEP_INPUT_OUTPUT | false|
      |KEEP_INPUT_OUTPUT | false|


  @DeleteTestCatalog
  Scenario: Create Model of Orca and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Orca'
    And User enters Catalog name as 'OrcaModel'
    And User enter model name as 'Orca Model'
    And User select the Type as 'EMBEDDED'
    And User enter the Endpoint as 'https://azureopenai.com/'
    And User enters var name as 'Variable_Orca'
    And User enter Init Script as 'InitScript_Orca'
    And User select the keep conversation history as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "Orca" model
      | NAME                      |
      | MODEL                     |
      | ENDPOINT                  |
      | VAR_NAME                  |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'OrcaModel'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName         | fieldValue               |
      | NAME              | OrcaModel                |
      | ENDPOINT          | https://azureopenai.com/ |
      | VAR_NAME          | Variable_Orca            |
      | INIT_MODEL_ENGINE | InitScript_Orca          |
      | MODEL_TYPE        | EMBEDDED                 |
      | KEEP_CONVERSATION_HISTORY | false|
      |KEEP_INPUT_OUTPUT | false|
      |KEEP_INPUT_OUTPUT | false|
      
