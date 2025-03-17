Feature: Validate Available Models and Their Groupings

  Scenario: Verify the available models and their specific groupings
    Given User navigates to Open Model
    When User clicks on Add Model
    Then User can view the following models grouping
      | LLM_GROUP         | LLM_MODELS                                                                                                                                                                                           |
      | OpenAI            | GPT-3.5, GPT-4, Text-Davinci, DALL E                                                                                                                                                                 |
      | Azure             | Azure Open AI                                                                                                                                                                                        |
      | AWS Bedrock       | Claude                                                                                                                                                                                               |
      | Google GCP        | Palm Bison, Palm Chat Bison, Palm Code Bison                                                                                                                                                         |
      | NVIDIA NIM Models | embed-qa-4, rerank-qa-mistral-4b                                                                                                                                                                     |
      | Locally Hosted    | Bert, Dolly, Eleuther GPTJ, Falcon, Flan T5 Large, Flan T5 XXL, Guanaco, Llama2 7B, Llama2 13B, Llama2 70B, Mosaic ML, NeMo, Replit code model – 3b, StableBeluga2, Vicuna, Wizard 13B, Wizard Coder |
      | Embedded          | NeMo, Orca, AWS TITAN TEXT EMBEDDINGS, Stablity AI, Replit Code Model                                                                                                                                |
      | File Uploads      | ZIP                                                                                                                                                                                                  |
