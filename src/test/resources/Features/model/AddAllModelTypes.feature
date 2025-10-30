@Regression
Feature: Add all model types

   @DeleteTestCatalog
   Scenario: Create Model of GPT-3.5 and validate the SMSS properties
     Given User opens Main Menu
     When User clicks on Open Model
     When User clicks on Add Model
     And User selects 'GPT-3.5'
     And User enters Catalog Name as 'GPT3_Model'
     And User select Type as 'Open AI'
     And User enters Open AI Key as 'Test@1234'
     And User enters Variable Name as 'Variable'
     And User select Chat Type as 'chat-completion'
     And User enter Init Script as 'InitScript_GPT3.5'
     And User select the Keep Conversation History as 'false'
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
     Then User can see the Model title as 'GPT3 Model'
     And User clicks on SMSS
     Then User can see following fields in SMSS Properties
       | fieldName                 | fieldValue                      |
       | NAME                      | GPT3_Model                      |
       | VAR_NAME                  | Variable                        |
       | CHAT_TYPE                 | chat-completion                 |
       | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
       | KEEP_CONVERSATION_HISTORY | false                           |
       | KEEP_INPUT_OUTPUT         | false                           | 		 
       | MAX_TOKENS 			   			 | two                             |
   	   | MAX_INPUT_TOKENS    	     | xyz                             |

   @DeleteTestCatalog
   Scenario: Create Model of GPT-4 and validate the SMSS properties
     Given User opens Main Menu
     When User clicks on Open Model
     When User clicks on Add Model
     And User selects 'GPT-4'
     And User enters Catalog Name as 'GPT4_Model'
     And User select Type as 'Open AI'
     And User enters Open AI Key as 'Test@1234'
     And User enters Variable Name as 'GPT4_Variable'
     And User select Chat Type as 'chat-completion'
     And User enter Init Script as 'InitScript_GPT4'
     And User select the Keep Conversation History as 'false'
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
     Then User can see the Model title as 'GPT4 Model'
     And User clicks on SMSS
     Then User can see following fields in SMSS Properties
       | fieldName                 | fieldValue                      |
       | NAME                      | GPT4_Model                      |
       | VAR_NAME                  | GPT4_Variable                   |
       | CHAT_TYPE                 | chat-completion                 |
       | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
       | KEEP_CONVERSATION_HISTORY | false                           |
       | KEEP_INPUT_OUTPUT         | false                           |
       | MAX_TOKENS 			  			 | two                             |
       | MAX_INPUT_TOKENS          | xyz                             |
    

   @DeleteTestCatalog
   Scenario: Create Model of Text-Davinci and validate the SMSS properties
     Given User opens Main Menu
     When User clicks on Open Model
     When User clicks on Add Model
     And User selects 'Text-Davinci'
     And User enters Catalog Name as 'Text_Davinci_Model'
     And User select Type as 'Open AI'
     And User enters Open AI Key as 'Test@1234'
     And User enters Variable Name as 'Variable_Davinci'
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
     Then User can see the Model title as 'Text Davinci Model'
     And User clicks on SMSS
     Then User can see following fields in SMSS Properties
       | fieldName         | fieldValue               |
       | NAME              | Text_Davinci_Model       |
       | VAR_NAME          | Variable_Davinci         |
       | INIT_MODEL_ENGINE | from genai_client import |
       | KEEP_INPUT_OUTPUT | false                    |
       | MAX_TOKENS 			 | two                      |
       | MAX_INPUT_TOKENS  | xyz                      |

   @DeleteTestCatalog
   Scenario: Create Model of DALL E and validate the SMSS properties
     Given User opens Main Menu
     When User clicks on Open Model
     When User clicks on Add Model
     And User selects 'DALL E'
     And User select Type as 'Open AI'
     And User enters Catalog Name as 'DALL_E_Model'
     And User enters Open AI Key as 'Test@1234'
     And User enters Variable Name as 'Variable_DALLE'
     And User enter Init Script as 'InitScript_DALL E'
     And User select the Keep Conversation History as 'false'
     And User select Record Questions and Responses as 'false'
     And User enter the Max Tokens as 'two'
     And User enter the Max Input Tokens 'xyz'
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
     Then User can see the Model title as 'DALL E Model'
     And User clicks on SMSS
     Then User can see following fields in SMSS Properties
       | fieldName                 | fieldValue        |
       | NAME                      | DALL_E_Model      |
       | VAR_NAME                  | Variable_DALLE    |
       | INIT_MODEL_ENGINE         | InitScript_DALL E |
       | KEEP_CONVERSATION_HISTORY | false             |
       | KEEP_INPUT_OUTPUT         | false             |
       | MAX_TOKENS							   | two               |
       | MAX_INPUT_TOKENS          | xyz               |


   @DeleteTestCatalog
   Scenario: Create Model of Palm Chat Bison and validate the SMSS properties
     Given User opens Main Menu
     When User clicks on Open Model
     When User clicks on Add Model
     And User selects 'Palm Chat Bison'
     And User enters Catalog Name as 'Palm_Chat_Bison_Model'
    # type not able to edit
     # And User select the Type as 'Vertex'
     And User enter model name as 'mode-bison-001'
     And User enter GCP Region as 'Palm Region'
     And User enters Variable Name as 'Variable_Palm_Chat_Bison'
     And User select Chat Type as "text"
     And User enter Init Script as 'InitScript_Palm_Chat_Bison'
     And User select the Keep Conversation History as 'false'
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
     Then User can see the Model title as 'Palm Chat Bison Model'
     And User clicks on SMSS
     Then User can see following fields in SMSS Properties
       | fieldName                 | fieldValue                      |
       | NAME                      | Palm_Chat_Bison_Model           |
       | GCP_REGION                | Palm Region                     |
       | VAR_NAME                  | Variable_Palm_Chat_Bison        |
       | CHAT_TYPE                 | text                            |
       | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
       | KEEP_CONVERSATION_HISTORY | false                           |
       | KEEP_INPUT_OUTPUT         | false                           |
       | MAX_TOKENS							   | two            							   |
       | MAX_INPUT_TOKENS          | xyz            							   |

   @DeleteTestCatalog
   Scenario: Create Model of Eleuther GPTJ and validate the SMSS properties
     Given User opens Main Menu
     When User clicks on Open Model
     When User clicks on Add Model
     And User selects 'Eleuther GPTJ'
     And User enters Catalog Name as 'Eleuther_GPTJ_Model'
     And User enter model name as 'Eleuther GPTJ'
     And User select the Type as 'Text Generation'
     And User enter the Endpoint as 'https://azureopenai.com/'
     And User enters Variable Name as 'Variable_Eleuther_GPTJ'
     And User select Chat Type as "chat-completion"
     And User enter Init Script as 'InitScript_Eleuther_GPTJ'
     And User select the Keep Conversation History as 'false'
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
     Then User can see the Model title as 'Eleuther GPTJ Model'
     And User clicks on SMSS
     Then User can see following fields in SMSS Properties
       | fieldName                 | fieldValue               |
       | NAME                      | Eleuther_GPTJ_Model      |
       | ENDPOINT                  | https://azureopenai.com/ |
       | VAR_NAME                  | Variable_Eleuther_GPTJ   |
       | INIT_MODEL_ENGINE         | InitScript_Eleuther_GPTJ |
       | MODEL_TYPE                | TEXT_GENERATION          |
       | CHAT_TYPE                 | chat-completion          |
       | KEEP_CONVERSATION_HISTORY | false                    |
       | KEEP_INPUT_OUTPUT         | false                    |
  		 | MAX_TOKENS 							 | two                      |
       | MAX_INPUT_TOKENS 				 | xyz                      |

   @DeleteTestCatalog
   Scenario: Create Model of Orca and validate the SMSS properties
     Given User opens Main Menu
     When User clicks on Open Model
     When User clicks on Add Model
     And User selects 'Orca'
     And User enters Catalog Name as 'OrcaModel'
     And User enter model name as 'Orca Model'
     And User select the Type as 'EMBEDDED'
     And User enter the Endpoint as 'https://azureopenai.com/'
     And User enters Variable Name as 'Variable_Orca'
     And User enter Init Script as 'InitScript_Orca'
     And User select the Keep Conversation History as 'false'
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
     Then User can see the Model title as 'OrcaModel'
     And User clicks on SMSS
     Then User can see following fields in SMSS Properties
       | fieldName                 | fieldValue               |
       | NAME                      | OrcaModel                |
       | ENDPOINT                  | https://azureopenai.com/ |
       | VAR_NAME                  | Variable_Orca            |
       | INIT_MODEL_ENGINE         | InitScript_Orca          |
       | MODEL_TYPE                | EMBEDDED                 |
       | KEEP_CONVERSATION_HISTORY | false                    |
       | KEEP_INPUT_OUTPUT         | false                    |
   		 | MAX_TOKENS								 | two                      |
       | MAX_INPUT_TOKENS					 | xyz                      |

@DeleteTestCatalog
Scenario: Create Model of Azure OpenAI and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'Azure Open AI'
  And User enters Catalog Name as 'Azure_OpenAI_Model'
  And User select Type as 'Open AI'
  And User enters Open AI Key as 'Test@1234'
  And User enter the Endpoint as 'https://azureopenai.com/'
  And User enter the Deployment Name as 'Deployment123'
  And User enters Variable Name as 'Variable_Azure_OpenAI'
  And User select Chat Type as 'chat-completion'
  And User enter Init Script as 'InitScript_Azure_OpenAI'
  And User select the Keep Conversation History as 'false'
  And User select Record Questions and Responses as 'false'
  And User enter the Max Tokens as 'two'
  And User enter the Max Input Tokens 'xyz'
  Then User can enable Submit button after filling mandatory fields for "Azure OpenAI" model
    | NAME                      |
    | MODEL_TYPE                |
    | OPEN_AI_KEY               |
    | ENDPOINT           			  |
    | MODEL                     |
    | VAR_NAME                  |
    | CHAT_TYPE                 |
    | INIT_MODEL_ENGINE         |
    | KEEP_CONVERSATION_HISTORY |
    | KEEP_INPUT_OUTPUT         |
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User can see the Model title as 'Azure OpenAI Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS Properties
    | fieldName                 | fieldValue                      |
    | NAME                      | Azure_OpenAI_Model              |
    | ENDPOINT                  | https://azureopenai.com/        |
#Not able to see the Deployment field in the SMSS Properties
   #| DEPLOYMENT                | Deployment123                   |
    | VAR_NAME                  | Variable_Azure_OpenAI           |
    | CHAT_TYPE                 | chat-completion                 |
    | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
    | KEEP_CONVERSATION_HISTORY | false                           |
    | KEEP_INPUT_OUTPUT         | false                           |
    | MAX_TOKENS                | two                             |
    | MAX_INPUT_TOKENS          | xyz                             |

   @DeleteTestCatalog
   Scenario: Create Model of Azure Open AI ADA Embedder and validate the SMSS properties
   Given User opens Main Menu
   When User clicks on Open Model
   When User clicks on Add Model
   And User selects 'Azure Open AI ADA Embedder'
   And User enters Catalog Name as 'Azure_OpenAI_ADA_Embedder_Model'
   #tag name is preselected and not able to edit
   #And User enter the tag as "Embeddings"
   And User select Type as 'Open AI'
   And User select the model as "text-embedding-ada-002"
   And User enters Open AI Key as 'Test@1234'
   And User enter the Endpoint as 'https://azureopenai.com/'
   And User enter the Version as '20'
   And User enters Variable Name as 'Variable_Azure_OpenAI_ADA_Embedder'
   And User enter Init Script as 'InitScript_Azure_OpenAI_ADA_Embedder'
   And User enter the Max Tokens as 'xyz'
   Then User can enable Submit button after filling mandatory fields for "Azure Open AI ADA Embedder" model
     | NAME              |
     | MODEL_TYPE        |
     | OPEN_AI_KEY       |
     | ENDPOINT          |
     | API_VERSION       |
     | VAR_NAME          |
     | INIT_MODEL_ENGINE |
     | MAX_TOKENS        |
   And User clicks on Create Model button
   And User can see a toast message as 'Successfully added LLM to catalog'
   And User clicks On Copy Catalog ID
   Then User can see the Model title as 'Azure OpenAI ADA Embedder Model'
   And User clicks on SMSS
   Then User can see following fields in SMSS Properties
     | fieldName         | fieldValue                        |
     | NAME              | Azure_OpenAI_ADA_Embedder_Model   |
     | ENDPOINT          | https://azureopenai.com/          |
     | API_VERSION       | 20                                |
     | VAR_NAME          | Variable_Azure_OpenAI_ADA_Embedder|
     | MODEL_TYPE        | OPEN_AI                           |
     | INIT_MODEL_ENGINE | from genai_client import Azure    |
     | MAX_TOKENS        | 4000                              |
    
    @DeleteTestCatalog
  Scenario: Create Model of Claude and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Claude'
    And User enters Catalog Name as 'Claude_Model'
    And User select Type as 'Bedrock'
    And User enter model name as "Model_Name_Claude"
    And User enter aws Region as "us-east-1"
    And User enter AWS Access key as "Xyz@123"
    And User enter AWS Secreate key as "543"
    And User enters Variable Name as 'Variable_Claude'
    And User select Chat Type as 'chat-completion'
    And User enter Init Script as 'InitScript_Claude'
    And User select the Keep Conversation History as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "Claude" model
      | NAME                      |
      | MODEL_TYPE                |
      | AWS_REGION                |
      | AWS_ACCESS_KEY            |
      |AWS_SECRET_KEY             |
      | VAR_NAME                  |
      | CHAT_TYPE                 |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
     And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User can see the Model title as 'Claude Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS Properties
      | fieldName                 | fieldValue                      |
      | NAME                      | Claude_Model                    |
      | MODEL_TYPE                | BEDROCK                         |
      | AWS_REGION                | us-east-1                       |
      | VAR_NAME                  | Variable_Claude                 |
      | CHAT_TYPE                 | chat-completion                 |
      | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
      | KEEP_CONVERSATION_HISTORY | false                           |
      | KEEP_INPUT_OUTPUT         | false                           |
      | MAX_TOKENS                | two                             |
      | MAX_INPUT_TOKENS          | xyz                             |

@DeleteTestCatalog
Scenario: Create Model of Palm Bison and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Palm Bison'
And User enters Catalog Name as 'Palm_Bison_Model'
#not able to edit the type as its preselected
#And User select Type as 'Vertex'
And User enter model name as 'mode-bison-001'
And User enter GCP Region as 'Palm Region'
And User enters Variable Name as 'Variable_Palm_Bison'
And User select Chat Type as "text"
And User enter Init Script as 'InitScript_Palm_Bison'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Palm Bison" model
  | NAME                      |
  | MODEL_TYPE                |
  | GCP_REGION                |
  | VAR_NAME                  |
  | CHAT_TYPE                 |
  | INIT_MODEL_ENGINE         |
  | KEEP_CONVERSATION_HISTORY |
  | KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Palm Bison Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
  | fieldName                 | fieldValue                      |
  | NAME                      | Palm_Bison_Model                |
  | GCP_REGION                | Palm Region                     |
  | VAR_NAME                  | Variable_Palm_Bison             |
  | CHAT_TYPE                 | text                            |
  | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
  | KEEP_CONVERSATION_HISTORY | false                           |
  | KEEP_INPUT_OUTPUT         | false                           |
  | MAX_TOKENS 								| two                             |
  | MAX_INPUT_TOKENS				  | xyz   												  |
  
  @DeleteTestCatalog
  Scenario: Create Model of Palm Code Bison and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Palm Code Bison'
    And User enters Catalog Name as 'Palm_Code_Bison_Model'
    #type not able to edit
    # And User select the Type as 'Vertex'
    And User enter model name as 'mode-bison-001'
    And User enter GCP Region as 'Palm Region'
    And User enters Variable Name as 'Variable_Palm_Code_Bison'
    And User select Chat Type as "text"
    And User enter Init Script as 'InitScript_Palm_Code_Bison'
    And User select the Keep Conversation History as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "Palm Code Bison" model
      | NAME                      |
      | MODEL_TYPE                |
      | GCP_REGION                |
      | VAR_NAME                  |
      | CHAT_TYPE                 |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User can see the Model title as 'Palm Code Bison Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS Properties
      | fieldName                 | fieldValue                      |
      | NAME                      | Palm_Code_Bison_Model           |
      | GCP_REGION                | Palm Region                     |
      | VAR_NAME                  | Variable_Palm_Code_Bison        |
      | CHAT_TYPE                 | text                					  |
      | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
      | KEEP_CONVERSATION_HISTORY | false                           |
      | KEEP_INPUT_OUTPUT         | false                           |
      | KEEP_INPUT_OUTPUT         | false                           |

 
  @DeleteTestCatalog
  Scenario: Create Model of Palm Chat Bison and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Palm Chat Bison'
    And User enters Catalog Name as 'Palm_Chat_Bison_Model'
    #type not able to edit
    # And User select the Type as 'Vertex'
    And User enter model name as 'mode-bison-001'
    And User enter GCP Region as 'Palm Region'
    And User enters Variable Name as 'Variable_Palm_Chat_Bison'
    And User select Chat Type as "text"
    And User enter Init Script as 'InitScript_Palm_Chat_Bison'
    And User select the Keep Conversation History as 'false'
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
    Then User can see the Model title as 'Palm Chat Bison Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS Properties
      | fieldName                 | fieldValue                      |
      | NAME                      | Palm_Chat_Bison_Model           |
      | GCP_REGION                | Palm Region                     |
      | VAR_NAME                  | Variable_Palm_Chat_Bison        |
      | CHAT_TYPE                 | text                            |
      | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
      | KEEP_CONVERSATION_HISTORY | false                           |
      | KEEP_INPUT_OUTPUT         | false                           |
      | MAX_TOKENS 								| two                             |
      | MAX_INPUT_TOKENS 					| xyz                             |

 @DeleteTestCatalog
 Scenario: Create Model of embed-qa-4 and validate the SMSS properties
 Given User opens Main Menu
 When User clicks on Open Model
 When User clicks on Add Model
 And User selects 'embed-qa-4'
  And User enters Catalog Name as 'Embed-qa-4_Model'
  And User select Type as 'Open AI'
  And User enters Open AI Key as 'Test@1234'
  And User enters Variable Name as 'Variable_embed'
  And User enter Init Script as 'InitScript_embed-qa-4'
  And User select the Keep Conversation History as 'false'
  And User select Record Questions and Responses as 'false'
  And User enter the Max Tokens as 'two'
  And User enter the Max Input Tokens 'xyz'
  Then User can enable Submit button after filling mandatory fields for "embed-qa-4" model
    | NAME                      |
    | MODEL_TYPE                |
    | VAR_NAME                  |
    | CHAT_TYPE                 |
    | INIT_MODEL_ENGINE         |
    | KEEP_CONVERSATION_HISTORY |
    | KEEP_INPUT_OUTPUT         |
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User can see the Model title as 'Embed-qa-4 Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS Properties
    | fieldName                 | fieldValue                      |
    | NAME                      | Embed-qa-4_Model                |
    | VAR_NAME                  | Variable_embed                  |
    | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
    | MODEL_TYPE                | OPEN_AI                         |
    | KEEP_CONVERSATION_HISTORY | false                           |
    | KEEP_INPUT_OUTPUT         | false                           |
    | MAX_TOKENS                | two                             |
    | MAX_INPUT_TOKENS          | xyz                             |

 @DeleteTestCatalog
 Scenario: Create Model of rerank-qa-mistral-4bs and validate the SMSS properties
   Given User opens Main Menu
   When User clicks on Open Model
   When User clicks on Add Model
   And User selects 'rerank-qa-mistral-4b'
   And User enters Catalog Name as 'Rerank-qa-mistral-4bs_Model'
   And User select Type as 'Open AI'
   And User enters Open AI Key as 'Test@1234'
   And User enters Variable Name as 'Variable_rerank'
   And User select Chat Type as 'chat-completion'
   And User enter Init Script as 'InitScript_rerank-qa-mistral-4bs'
   And User select the Keep Conversation History as 'false'
   And User select Record Questions and Responses as 'false'
   And User enter the Max Tokens as 'two'
   And User enter the Max Input Tokens 'xyz'
   Then User can enable Submit button after filling mandatory fields for "rerank-qa-mistral-4bs" model
     | NAME                      |
     | MODEL_TYPE                |
     | VAR_NAME                  |
     | CHAT_TYPE                 |
     | INIT_MODEL_ENGINE         |
     | KEEP_CONVERSATION_HISTORY |
     | KEEP_INPUT_OUTPUT         | 
   And User clicks on Create Model button
   And User can see a toast message as 'Successfully added LLM to catalog'
   And User clicks On Copy Catalog ID
   Then User can see the Model title as 'Rerank-qa-mistral-4bs Model'
   And User clicks on SMSS
   Then User can see following fields in SMSS Properties
     | fieldName                 | fieldValue                      |
     | NAME                      | Rerank-qa-mistral-4bs_Model     |
     | VAR_NAME                  | Variable_rerank                 |
     | CHAT_TYPE                 | chat-completion                 |
     | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
     | MODEL_TYPE                | OPEN_AI                         |
     | KEEP_CONVERSATION_HISTORY | false                           |
     | KEEP_INPUT_OUTPUT         | false                           |
     | MAX_TOKENS                | two                             |
     | MAX_INPUT_TOKENS          | xyz                             |

@DeleteTestCatalog
Scenario: Create Model of Bert model and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Bert'
And User enters Catalog Name as 'Bert_Model'
And User enter model name as 'Bert'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Bert'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Bert'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Bert" model
  | NAME                      |
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
Then User can see the Model title as 'Bert Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
  | fieldName                 | fieldValue               |
  | NAME                      | Bert_Model               |
  | ENDPOINT                  | https://azureopenai.com/ |
  | VAR_NAME                  | Variable_Bert            |
  | INIT_MODEL_ENGINE         | InitScript_Bert          |
  | MODEL_TYPE                | TEXT_GENERATION          |
  | CHAT_TYPE                 | chat-completion          |
  | KEEP_CONVERSATION_HISTORY | false                    |
  | KEEP_INPUT_OUTPUT         | false                    |
  | MAX_TOKENS                | two                      |
  | MAX_INPUT_TOKENS          | xyz                      |

@DeleteTestCatalog
Scenario: Create Model of Dolly and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Dolly'
And User enters Catalog Name as 'Dolly_Model'
And User enter model name as 'Dolly'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Dolly'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Dolly'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Dolly" model
  | NAME                      |
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
Then User can see the Model title as 'Dolly Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
  | fieldName                 | fieldValue               |
  | NAME                      | Dolly_Model              |
  | ENDPOINT                  | https://azureopenai.com/ |
  | VAR_NAME                  | Variable_Dolly           |
  | INIT_MODEL_ENGINE         | InitScript_Dolly         |
  | MODEL_TYPE                | TEXT_GENERATION          |
  | CHAT_TYPE                 | chat-completion          |
  | KEEP_CONVERSATION_HISTORY | false                    |
  | KEEP_INPUT_OUTPUT         | false                    |
  | MAX_TOKENS                | two                      |
  | MAX_INPUT_TOKENS          | xyz                      |
  
  @DeleteTestCatalog
Scenario: Create Model of Gemini and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Gemini'
And User enters Catalog Name as 'Gemini_Model'
#type is not able to edit
#And User select Type as 'Vertex'
And User select the model as "gemini-1.5-pro-002"
And User enter GCP Region as 'Gemini Region'
And User enters Variable Name as 'Variable_Gemini'
#chat type is not able to edit
#And User select Chat Type as "text"
And User uploads a file 'dummy-pdf.pdf'
And User enter Init Script as 'InitScript_Gemini'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Gemini" model
| NAME                      |
| MODEL_TYPE                |
| GCP_REGION                |
| VAR_NAME                  |
| INIT_MODEL_ENGINE         |
| KEEP_CONVERSATION_HISTORY |
| KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Gemini Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
| fieldName                 | fieldValue                      |
| NAME                      | Gemini_Model                    |
| GCP_REGION                | Gemini Region                   |
| VAR_NAME                  | Variable_Gemini                 |
| CHAT_TYPE                 | generative                      |
| INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME} |
| KEEP_CONVERSATION_HISTORY | false                           |
| KEEP_INPUT_OUTPUT         | false                           |
| MAX_TOKENS 								| two                             |
| MAX_INPUT_TOKENS 					| xyz    													|


 @DeleteTestCatalog
Scenario: Create Model of Falcon and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Falcon'
And User enters Catalog Name as 'Falcon_Model'
And User enter model name as 'Falcon'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Falcon'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Falcon'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Falcon" model
  | NAME                      |
  | MODEL                     |
  | ENDPOINT                  |
  | VAR_NAME                  |
  | CHAT_TYPE                 |
  | INIT_MODEL_ENGINE         |
  | KEEP_CONVERSATION_HISTORY |
  | KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Falcon Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
  | fieldName                 | fieldValue               |
  | NAME                      | Falcon_Model             |
  | ENDPOINT                  | https://azureopenai.com/ |
  | VAR_NAME                  | Variable_Falcon          |
  | INIT_MODEL_ENGINE         | InitScript_Falcon        |
  | MODEL_TYPE                | TEXT_GENERATION          |
  | CHAT_TYPE                 | chat-completion          |
  | KEEP_CONVERSATION_HISTORY | false                    |
  | KEEP_INPUT_OUTPUT         | false                    |
  | MAX_TOKENS                | two                      |
  | MAX_INPUT_TOKENS          | xyz                      |
  
  @DeleteTestCatalog
  Scenario: Create Model of Flan T5 Large and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'Flan T5 Large'
  And User enters Catalog Name as 'Flan_T5_Large_Model'
  And User enter model name as 'Flan T5 Large'
  And User select the Type as 'Text Generation'
  And User enter the Endpoint as 'https://azureopenai.com/'
  And User enters Variable Name as 'Variable_Flan_T5_Large'
  And User select Chat Type as "chat-completion"
  And User enter Init Script as 'InitScript_Flan_T5_Large'
  And User select the Keep Conversation History as 'false'
  And User select Record Questions and Responses as 'false'
  And User enter the Max Tokens as 'two'
  And User enter the Max Input Tokens 'xyz'
  Then User can enable Submit button after filling mandatory fields for "Flan T5 Large" model
    | NAME                      |
    | MODEL                     |
    | ENDPOINT                  |
    | VAR_NAME                  |
    | CHAT_TYPE                 |
    | INIT_MODEL_ENGINE         |
    | KEEP_CONVERSATION_HISTORY |
    | KEEP_INPUT_OUTPUT         |
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User can see the Model title as 'Flan T5 Large Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS Properties
    | fieldName                 | fieldValue                   |
    | NAME                      | Flan_T5_Large_Model          |
    | ENDPOINT                  | https://azureopenai.com/     |
    | VAR_NAME                  | Variable_Flan_T5_Large       |
    | INIT_MODEL_ENGINE         | InitScript_Flan_T5_Large     |
    | MODEL_TYPE                | TEXT_GENERATION              |
    | CHAT_TYPE                 | chat-completion              |
    | KEEP_CONVERSATION_HISTORY | false                        |
    | KEEP_INPUT_OUTPUT         | false                        |
    | MAX_TOKENS                | two                          |
    | MAX_INPUT_TOKENS          | xyz                          |
    

@DeleteTestCatalog
Scenario: Create Model of Flan T5 XXL and validate the SMSS 
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Flan T5 XXL'
And User enters Catalog Name as 'Flan_T5_XXL_Model'
And User enter model name as 'Flan T5 XXL'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Flan_T5_XXL'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Flan_T5_XXL'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Flan T5 XXL" model
  | NAME                      |
  | MODEL                     |
  | ENDPOINT                  |
  | VAR_NAME                  |
  | CHAT_TYPE                 |
  | INIT_MODEL_ENGINE         |
  | KEEP_CONVERSATION_HISTORY |
  | KEEP_INPUT_OUTPUT         | 
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Flan T5 XXL Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
  | fieldName                 | fieldValue                   |
  | NAME                      | Flan_T5_XXL_Model            |
  | ENDPOINT                  | https://azureopenai.com/     |
  | VAR_NAME                  | Variable_Flan_T5_XXL         |
  | INIT_MODEL_ENGINE         | InitScript_Flan_T5_XXL       |
  | MODEL_TYPE                | TEXT_GENERATION              |
  | CHAT_TYPE                 | chat-completion              |
  | KEEP_CONVERSATION_HISTORY | false                        |
  | KEEP_INPUT_OUTPUT         | false                        |
  | MAX_TOKENS                | two                          |
  | MAX_INPUT_TOKENS          | xyz                          |

  @DeleteTestCatalog
  Scenario: Create Model of Guanaco and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'Guanaco'
  And User enters Catalog Name as 'Guanaco_Model'
  And User enter model name as 'Guanaco'
  And User select the Type as 'Text Generation'
  And User enter the Endpoint as 'https://azureopenai.com/'
  And User enters Variable Name as 'Variable_Guanaco'
  And User select Chat Type as "chat-completion"
  And User enter Init Script as 'InitScript_Guanaco'
  And User select the Keep Conversation History as 'false'
  And User select Record Questions and Responses as 'false'
  And User enter the Max Tokens as 'two'
  And User enter the Max Input Tokens 'xyz'
  Then User can enable Submit button after filling mandatory fields for "Guanaco" model
    | NAME                      |
    | MODEL                     |
    | ENDPOINT                  |
    | VAR_NAME                  |
    | CHAT_TYPE                 |
    | INIT_MODEL_ENGINE         |
    | KEEP_CONVERSATION_HISTORY |
    | KEEP_INPUT_OUTPUT         |
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User can see the Model title as 'Guanaco Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS Properties
    | fieldName                 | fieldValue               |
    | NAME                      | Guanaco_Model            |
    | ENDPOINT                  | https://azureopenai.com/ |
    | VAR_NAME                  | Variable_Guanaco         |
    | INIT_MODEL_ENGINE         | InitScript_Guanaco       |
    | MODEL_TYPE                | TEXT_GENERATION          |
    | CHAT_TYPE                 | chat-completion          |
    | KEEP_CONVERSATION_HISTORY | false                    |
    | KEEP_INPUT_OUTPUT         | false                    |
    | MAX_TOKENS                | two                      |
    | MAX_INPUT_TOKENS          | xyz                      |

@DeleteTestCatalog
Scenario: Create Model of Llama2 7B and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model  
And User selects 'Llama2 7B'
And User enters Catalog Name as 'Llama2_7B_Model' 
And User enter model name as 'meta-llama/Llama-2-7b'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Llama2_7B'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Llama2_7B'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Llama2 7B" model
  | NAME                      |
  | MODEL                     |
  | ENDPOINT                  |
  | VAR_NAME                  |
  | CHAT_TYPE                 |
  | INIT_MODEL_ENGINE         |
  | KEEP_CONVERSATION_HISTORY |
  | KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Llama2 7B Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
  | fieldName                 | fieldValue               |
  | NAME                      | Llama2_7B_Model          |
  | ENDPOINT                  | https://azureopenai.com/ |
  | VAR_NAME                  | Variable_Llama2_7B       |
  | INIT_MODEL_ENGINE         | InitScript_Llama2_7B     |
  | MODEL_TYPE                | TEXT_GENERATION          |
  | CHAT_TYPE                 | chat-completion          |
  | KEEP_CONVERSATION_HISTORY | false                    |
  | KEEP_INPUT_OUTPUT         | false                    |
  | MAX_TOKENS                | two                      |
  | MAX_INPUT_TOKENS          | xyz                      |

  @DeleteTestCatalog
  Scenario: Create Model of Llama2 13B and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'Llama2 13B'
  And User enters Catalog Name as 'Llama2_13B_Model'
  And User enter model name as 'meta-llama/Llama-2-13b'
  And User select the Type as 'Text Generation'
  And User enter the Endpoint as 'https://azureopenai.com/'
  And User enters Variable Name as 'Variable_Llama2_13B'
  And User select Chat Type as "chat-completion"
  And User enter Init Script as 'InitScript_Llama2_13B'
  And User select the Keep Conversation History as 'false'
  And User select Record Questions and Responses as 'false'
  And User enter the Max Tokens as 'two'
  And User enter the Max Input Tokens 'xyz'
  Then User can enable Submit button after filling mandatory fields for "Llama2 13B" model
    | NAME                      |
    | MODEL                     |
    | ENDPOINT                  |
    | VAR_NAME                  |
    | CHAT_TYPE                 |
    | INIT_MODEL_ENGINE         |
    | KEEP_CONVERSATION_HISTORY |
    | KEEP_INPUT_OUTPUT         |
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User can see the Model title as 'Llama2 13B Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS Properties
    | fieldName                 | fieldValue               |
    | NAME                      | Llama2_13B_Model         |
    | ENDPOINT                  | https://azureopenai.com/ |
    | VAR_NAME                  | Variable_Llama2_13B      |
    | INIT_MODEL_ENGINE         | InitScript_Llama2_13B    |
    | MODEL_TYPE                | TEXT_GENERATION          |
    | CHAT_TYPE                 | chat-completion          |
    | KEEP_CONVERSATION_HISTORY | false                    |
    | KEEP_INPUT_OUTPUT         | false                    |
    | MAX_TOKENS                | two                      |
    | MAX_INPUT_TOKENS          | xyz                      |

   @DeleteTestCatalog
    Scenario: Create Model of Llama2 70B and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Llama2 70B'
    And User enters Catalog Name as 'Llama2_70B_Model'
    And User enter model name as 'meta-llama/Llama-2-70b'
    And User select the Type as 'Text Generation'
    And User enter the Endpoint as 'https://azureopenai.com/'
    And User enters Variable Name as 'Variable_Llama2_70B'
    And User select Chat Type as "chat-completion"
    And User enter Init Script as 'InitScript_Llama2_70B'
    And User select the Keep Conversation History as 'false'
    And User select Record Questions and Responses as 'false'
    And User enter the Max Tokens as 'two'
    And User enter the Max Input Tokens 'xyz'
    Then User can enable Submit button after filling mandatory fields for "Llama2 70B" model
      | NAME                      |
      | MODEL                     |
      | ENDPOINT                  |
      | VAR_NAME                  |
      | CHAT_TYPE                 |
      | INIT_MODEL_ENGINE         |
      | KEEP_CONVERSATION_HISTORY |
      | KEEP_INPUT_OUTPUT         |
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User can see the Model title as 'Llama2 70B Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS Properties
      | fieldName                 | fieldValue               |
      | NAME                      | Llama2_70B_Model         |
      | ENDPOINT                  | https://azureopenai.com/ |
      | VAR_NAME                  | Variable_Llama2_70B      |
      | INIT_MODEL_ENGINE         | InitScript_Llama2_70B    |
      | MODEL_TYPE                | TEXT_GENERATION          |
      | CHAT_TYPE                 | chat-completion          |
      | KEEP_CONVERSATION_HISTORY | false                    |
      | KEEP_INPUT_OUTPUT         | false                    |
      | MAX_TOKENS                | two                      |
      | MAX_INPUT_TOKENS          | xyz                      |

     @DeleteTestCatalog
      Scenario: Create Model of Mosaic ML and validate the SMSS properties
      Given User opens Main Menu
      When User clicks on Open Model
      When User clicks on Add Model
      And User selects 'Mosaic ML'
      And User enters Catalog Name as 'Mosaic_ML_Model'
      And User enter model name as 'Mosaic ML'
      And User select the Type as 'Text Generation'
      And User enter the Endpoint as 'https://azureopenai.com/'
      And User enters Variable Name as 'Variable_Mosaic_ML'
      And User select Chat Type as "chat-completion"
      And User enter Init Script as 'InitScript_Mosaic_ML'
      And User select the Keep Conversation History as 'false'
      And User select Record Questions and Responses as 'false'
      And User enter the Max Tokens as 'two'
      And User enter the Max Input Tokens 'xyz'
      Then User can enable Submit button after filling mandatory fields for "Mosaic ML" model
        | NAME                      |
        | MODEL                     |
        | ENDPOINT                  |
        | VAR_NAME                  |
        | CHAT_TYPE                 |
        | INIT_MODEL_ENGINE         |
        | KEEP_CONVERSATION_HISTORY |
        | KEEP_INPUT_OUTPUT         |
      And User clicks on Create Model button
      And User can see a toast message as 'Successfully added LLM to catalog'
      And User clicks On Copy Catalog ID
      Then User can see the Model title as 'Mosaic ML Model'
      And User clicks on SMSS
      Then User can see following fields in SMSS Properties
        | fieldName                 | fieldValue               |
        | NAME                      | Mosaic_ML_Model          |
        | ENDPOINT                  | https://azureopenai.com/ |
        | VAR_NAME                  | Variable_Mosaic_ML       |
        | INIT_MODEL_ENGINE         | InitScript_Mosaic_ML     |
        | MODEL_TYPE                | TEXT_GENERATION          |
        | CHAT_TYPE                 | chat-completion          |
        | KEEP_CONVERSATION_HISTORY | false                    |
        | KEEP_INPUT_OUTPUT         | false                    |
        | MAX_TOKENS                | two                      |
        | MAX_INPUT_TOKENS          | xyz                      |
        
        
@DeleteTestCatalog
Scenario: Create Model of Replit code model 3b and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Replit code model – 3b'
And User enters Catalog Name as 'Replit_code_model_3b_Model'
And User enter model name as 'Replit code model – 3b'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Replit_code_model_3b'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Replit_code_model_3b'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Replit code model – 3b" model
| NAME                      |
| MODEL                     |
| ENDPOINT                  |
| VAR_NAME                  |
| CHAT_TYPE                 |
| INIT_MODEL_ENGINE         |
| KEEP_CONVERSATION_HISTORY |
| KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Replit Code Model 3b Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
| fieldName                 | fieldValue                       |
| NAME                      | Replit_code_model_3b_Model       |
| ENDPOINT                  | https://azureopenai.com/         |
| VAR_NAME                  | Variable_Replit_code_model_3b    |
| INIT_MODEL_ENGINE         | InitScript_Replit_code_model_3b  |
| MODEL_TYPE                | TEXT_GENERATION                  |
| CHAT_TYPE                 | chat-completion                  |
| KEEP_CONVERSATION_HISTORY | false                            |
| KEEP_INPUT_OUTPUT         | false                            |
| MAX_TOKENS                | two                              |
| MAX_INPUT_TOKENS          | xyz                              |

@DeleteTestCatalog
Scenario: Create Model of StableBeluga2 and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'StableBeluga2'
And User enters Catalog Name as 'StableBeluga2_Model'
And User enter model name as 'StableBeluga2'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_StableBeluga2'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_StableBeluga2'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "StableBeluga2" model
| NAME                      |
| MODEL                     |
| ENDPOINT                  |
| VAR_NAME                  |
| CHAT_TYPE                 |
| INIT_MODEL_ENGINE         |
| KEEP_CONVERSATION_HISTORY |
| KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'StableBeluga2 Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
| fieldName                 | fieldValue                   |
| NAME                      | StableBeluga2_Model          |
| ENDPOINT                  | https://azureopenai.com/     |
| VAR_NAME                  | Variable_StableBeluga2       |
| INIT_MODEL_ENGINE         | InitScript_StableBeluga2     |
| MODEL_TYPE                | TEXT_GENERATION              |
| CHAT_TYPE                 | chat-completion              |
| KEEP_CONVERSATION_HISTORY | false                        |
| KEEP_INPUT_OUTPUT         | false                        |
| MAX_TOKENS                | two                          |
| MAX_INPUT_TOKENS          | xyz                          |

@DeleteTestCatalog
Scenario: Create Model of Vicuna and validate the SMSS properties    
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Vicuna'
And User enters Catalog Name as 'Vicuna_Model'
And User enter model name as 'Vicuna'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Vicuna'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Vicuna'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Vicuna" model
| NAME                      |
| MODEL                     |
| ENDPOINT                  |
| VAR_NAME                  |
| CHAT_TYPE                 |
| INIT_MODEL_ENGINE         |
| KEEP_CONVERSATION_HISTORY |
| KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Vicuna Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
| fieldName                 | fieldValue               |
| NAME                      | Vicuna_Model             |
| ENDPOINT                  | https://azureopenai.com/ |
| VAR_NAME                  | Variable_Vicuna          |
| INIT_MODEL_ENGINE         | InitScript_Vicuna        |
| MODEL_TYPE                | TEXT_GENERATION          |
| CHAT_TYPE                 | chat-completion          |
| KEEP_CONVERSATION_HISTORY | false                    |
| KEEP_INPUT_OUTPUT         | false                    |
| MAX_TOKENS                | two                      |
| MAX_INPUT_TOKENS          | xyz                      |

@DeleteTestCatalog
Scenario: Create Model of Wizard 13B and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Wizard 13B'
And User enters Catalog Name as 'Wizard_13B_Model'
And User enter model name as 'Wizard 13B'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Wizard_13B'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Wizard_13B'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Wizard 13B" model
| NAME                      |
| MODEL                     |
| ENDPOINT                  |
| VAR_NAME                  |
| CHAT_TYPE                 |
| INIT_MODEL_ENGINE         |
| KEEP_CONVERSATION_HISTORY |
| KEEP_INPUT_OUTPUT         |
And User clicks on Create Model button
And User can see a toast message as 'Successfully added LLM to catalog'
And User clicks On Copy Catalog ID
Then User can see the Model title as 'Wizard 13B Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
| fieldName                 | fieldValue               |
| NAME                      | Wizard_13B_Model         |
| ENDPOINT                  | https://azureopenai.com/ |
| VAR_NAME                  | Variable_Wizard_13B      |
| INIT_MODEL_ENGINE         | InitScript_Wizard_13B    |
| MODEL_TYPE                | TEXT_GENERATION          |
| CHAT_TYPE                 | chat-completion          |
| KEEP_CONVERSATION_HISTORY | false                    |
| KEEP_INPUT_OUTPUT         | false                    |
| MAX_TOKENS                | two                      |
| MAX_INPUT_TOKENS          | xyz                      |

@DeleteTestCatalog
Scenario: Create Model of Wizard Coder and validate the SMSS properties
Given User opens Main Menu
When User clicks on Open Model
When User clicks on Add Model
And User selects 'Wizard Coder'
And User enters Catalog Name as 'Wizard_Coder_Model'
And User enter model name as 'Wizard Coder'
And User select the Type as 'Text Generation'
And User enter the Endpoint as 'https://azureopenai.com/'
And User enters Variable Name as 'Variable_Wizard_Coder'
And User select Chat Type as "chat-completion"
And User enter Init Script as 'InitScript_Wizard_Coder'
And User select the Keep Conversation History as 'false'
And User select Record Questions and Responses as 'false'
And User enter the Max Tokens as 'two'
And User enter the Max Input Tokens 'xyz'
Then User can enable Submit button after filling mandatory fields for "Wizard Coder" model
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
Then User can see the Model title as 'Wizard Coder Model'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
| fieldName                 | fieldValue               |
| NAME                      | Wizard_Coder_Model       |
| ENDPOINT                  | https://azureopenai.com/ |
| VAR_NAME                  | Variable_Wizard_Coder    |
| INIT_MODEL_ENGINE         | InitScript_Wizard_Coder  |
| MODEL_TYPE                | TEXT_GENERATION          |
| CHAT_TYPE                 | chat-completion          |
| KEEP_CONVERSATION_HISTORY | false                    |
| KEEP_INPUT_OUTPUT         | false                    |
| MAX_TOKENS                | two                      |
| MAX_INPUT_TOKENS          | xyz                      |

@DeleteTestCatalog
Scenario: Create Model of ZIP and validate the SMSS properties
Given User opens Main Menu
And User clicks on Open Model
And User clicks on Add Model
When User selects 'ZIP'
And User uploads a file 'Model/ModelZIP.zip'
And User click on Create 'Model' button
Then User can see a toast message as 'ZIP uploaded successfully'
And User clicks On Copy Catalog ID
Then User can see the Catalog title as 'Llama3-70B-Instruct'
And User clicks on SMSS
Then User can see following fields in SMSS Properties
  | fieldName                 | fieldValue                                     |
  | MODEL_TYPE                | TEXT_GENERATION                                |
  | ENDPOINT                  | https://play.semoss.org/moose/llama3-70/v1     |
  | VAR_NAME                  | llama3Model                                    |
  | INIT_MODEL_ENGINE         | import genai_client;${VAR_NAME}                |
  | KEEP_CONVERSATION_HISTORY | true                                           |
  | KEEP_INPUT_OUTPUT         | true                                           |
  | MAX_TOKENS                | 128000                                         |
  | MAX_INPUT_TOKENS          | 127000                                         |





        