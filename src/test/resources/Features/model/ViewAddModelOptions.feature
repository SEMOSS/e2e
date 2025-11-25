@Regression
Feature: Available Models and Their Groupings

  Scenario: Verify the available models and their specific groupings
    Given User opens Main Menu
    When User clicks on Open Model
    When User clicks on Add Model
    Then User can view the following model options under their group
      | GROUP             | MODEL_OPTIONS                                                                                                                                                                                                                  |
      | OpenAI            | Other OpenAI models, GPT-5, GPT-5.1, GPT-5 Mini, GPT-5 nano, GPT 4, GPT 3.5 Turbo, GPT-4o, DALL E 3, DALL E 2, text-embedding-3-large, text-embedding-3-small, gpt-audio, GPT Image 1                                          |
      | Google Vertex AI  | Other Google Vertex AI models, Gemini 2.5 Pro, Gemini Pro, Gemini Ultra, Gemma 2b, Llama 2-7b, Llama 2-70b, PaLM 2 Bison, PaLM 2 Bison (32k), Code Generation Bison, Mistral                                                   |
      | Azure OpenAI      | Other Azure OpenAI models, Azure Open AI, Azure Open AI ADA Embedding                                                                                                                                                          |
      | AWS Bedrock       | Other AWS Bedrock models, Claude 3 Opus, Claude 3 Sonnet, Claude 3 Haiku, Claude 2.0, Jurassic-2 Ultra, Jurassic-2 Ultra, Titan Text G1 Express, Titan Text Lite, Titan Embeddings (text)                                      |
      | NVIDIA NIM        | Other NVIDIA NIM models, EMBED QA 4, Rerank QA Mistral 4B                                                                                                                                                                      |
      | OpenAI-Compatible | Other OpenAI-Compatible models, Bert, Dolly, Eleuther GPTJ, Falcon, Flan T5 Large, Flan T5 XXL, Guanaco, Llama2 7B, Llama2 13B, Llama2 13B, Mosaic ML, Replit code model – 3b, StableBeluga2, Vicuna, Wizard 13B, Wizard Coder |
      | Embedded          | Other Embedded models, NeMo, Orca, AWS TITAN TEXT EMBEDDINGS, Stablity AI, Replit Code Model                                                                                                                                   |
