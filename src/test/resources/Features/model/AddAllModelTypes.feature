Feature: Add all model types

  @DeleteTestCatalog
  Scenario: Create Model of GPT-3.5 and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'GPT-3.5'
  And User enters Catalog name as 'GPT3_Model'
  And User enters open AI Key as 'Test@1234'
  And User enters var name as 'Variable'
  Then User can enable Submit button after filling mandatory fields for "GPT 3.5" model
 # | fieldName                  | 
  | NAME                       | 
  | MODEL_TYPE                 | 
  | OPEN_AI_KEY                |    
  | VAR_NAME                   |
  | CHAT_TYPE                  |
  | INIT_MODEL_ENGINE          |
  | KEEP_CONVERSATION_HISTORY  |
  | KEEP_INPUT_OUTPUT          |
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User Can see the Model title as 'GPT3 Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS properties
  | fieldName   | fieldValue |
  | NAME        | GPT3_Model |
  | OPEN_AI_KEY | Test@1234  |
  | VAR_NAME    | Variable   |
  
  @DeleteTestCatalog
  Scenario: Create Model of GPT-4 and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'GPT-4'
  And User enters Catalog name as 'GPT4_Model'
  And User enters open AI Key as 'Test@1234'
  And User enters var name as 'GPT4_Variable'
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User Can see the Model title as 'GPT4 Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS properties
  | fieldName   | fieldValue    |
  | NAME        | GPT4_Model    |
  | OPEN_AI_KEY | Test@1234     |
  | VAR_NAME    | GPT4_Variable |
  
  @DeleteTestCatalog
  Scenario: Create Model of Text-Davinci and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'Text-Davinci'
  And User enters Catalog name as 'Text_Davinci_Model'
  And User enters open AI Key as 'Test@1234'
  And User enters var name as 'Variable_Davinci'
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User Can see the Model title as 'Text Davinci Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS properties
  | fieldName   | fieldValue         |
  | NAME        | Text_Davinci_Model |
  | OPEN_AI_KEY | Test@1234          |
  | VAR_NAME    | Variable_Davinci   |
  
  @DeleteTestCatalog
  Scenario: Create Model of DALL E and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'DALL E'
  And User enters Catalog name as 'DALL_E_Model'
  And User enters open AI Key as 'Test@1234'
  And User enters var name as 'Variable_DALLE'
  And User enter Init Script as 'InitScript_DALL E'
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User Can see the Model title as 'DALL E Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS properties
  | fieldName         | fieldValue        |
  | NAME              | DALL_E_Model      |
  | OPEN_AI_KEY       | Test@1234         |
  | VAR_NAME          | Variable_DALLE    |
  | INIT_MODEL_ENGINE | InitScript_DALL E |
  
  @DeleteTestCatalog
  Scenario: Create Model of Palm Chat Bison and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'Palm Chat Bison'
  And User enters Catalog name as 'Palm_Chat_Bison_Model'
  And User enter GCP Region as 'Palm Region'
  And User enters var name as 'Variable_Palm_Chat_Bison'
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
  
  @DeleteTestCatalog
  Scenario: Create Model of Eleuther GPTJ and validate the SMSS properties
  Given User opens Main Menu
  When User clicks on Open Model
  When User clicks on Add Model
  And User selects 'Eleuther GPTJ'
  And User enters Catalog name as 'Eleuther_GPTJ_Model'
  And User select the Type as 'Text Generation'
  And User enter the Endpoint as 'Endpoint Text'
  And User enters var name as 'Variable_Eleuther_GPTJ'
  And User enter Init Script as 'InitScript_Eleuther_GPTJ'
  And User clicks on Create Model button
  And User can see a toast message as 'Successfully added LLM to catalog'
  And User clicks On Copy Catalog ID
  Then User Can see the Model title as 'Eleuther GPTJ Model'
  And User clicks on SMSS
  Then User can see following fields in SMSS properties
  | fieldName         | fieldValue               |
  | NAME              | Eleuther_GPTJ_Model      |
  | ENDPOINT          | Endpoint Text            |
  | VAR_NAME          | Variable_Eleuther_GPTJ   |
  | INIT_MODEL_ENGINE | InitScript_Eleuther_GPTJ |
  | MODEL_TYPE        | TEXT_GENERATION          |
  @DeleteTestCatalog
  Scenario: Create Model of Orca and validate the SMSS properties
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Orca'
    And User enters Catalog name as 'OrcaModel'
    And User select the Type as 'EMBEDDED'
    And User enter the Endpoint as 'Endpoint Orca'
    And User enters var name as 'Variable_Orca'
    And User enter Init Script as 'InitScript_Orca'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'OrcaModel'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName         | fieldValue      |
      | NAME              | OrcaModel       |
      | ENDPOINT          | Endpoint Orca   |
      | VAR_NAME          | Variable_Orca   |
      | INIT_MODEL_ENGINE | InitScript_Orca |
      | MODEL_TYPE        | EMBEDDED        |

  @DeleteTestCatalog
  Scenario: Create Model of Azure Open Ai Model
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    And User selects 'Azure Open AI'
    And User enters Catalog name as 'Azure_OpenAI_Model'
    And User enters open AI Key as 'AzureTest@1234'
    And User enter the Deployment Name as 'Deployment_Name'
    And User enter the Endpoint as 'https://azureopenai.endpoint.com/'
    And User enters var name as 'Variable_Azure_OpenAI'
    And User clicks on Create Model button
    And User can see a toast message as 'Successfully added LLM to catalog'
    And User clicks On Copy Catalog ID
    Then User Can see the Model title as 'Azure OpenAI Model'
    And User clicks on SMSS
    Then User can see following fields in SMSS properties
      | fieldName        | fieldValue                        |
      | NAME             | Azure_OpenAI_Model                |
      | OPEN_AI_KEY			 | AzureTest@1234                    |
  # Need to check beacuse beployment field not display in SMSS field
   #  | DEPLOYMENT_NAME  | Deployment_Name                   |
      | ENDPOINT         | https://azureopenai.endpoint.com/ |
      | VAR_NAME         | Variable_Azure_OpenAI             |

      @DeleteTestCatalog
      Scenario: Create Model of Azure Open AI ADA Embedder
       Given User opens Main Menu
       When User clicks on Open Model
       When User clicks on Add Model
       And User selects 'Azure Open AI ADA Embedder'
       And User enters Catalog name as 'Azure_ADA_Embedder_Model'
      # tag name is not able to edit
       #And User enter the tag as 'Embedddings'
       And User enters open AI Key as 'AzureADA@1234'
       And User enter the Endpoint as 'https://www.google.com/'
       And User enter the Version as '3'
       And User enters var name as 'Variable_Azure_ADA_Embedder1'
       And User clicks on Create Model button 
       And User can see a toast message as 'Successfully added LLM to catalog'
       And User clicks On Copy Catalog ID
       Then User Can see the Model title as 'Azure ADA Embedder Model'
       And User clicks on SMSS   
       Then User can see following fields in SMSS properties
          | fieldName   | fieldValue                       |
          | NAME        | Azure_ADA_Embedder_Model         |
          | OPEN_AI_KEY | AzureADA@1234                    |
          | ENDPOINT    | https://www.google.com/					 |
         	| API_VERSION | 3                            		 |
          | VAR_NAME    | Variable_Azure_ADA_Embedder1     |
          