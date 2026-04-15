@LoginWithAdmin @Regression @DeleteTestCatalog
Feature: Vector Q&A Tab

  Background: Add Model and Vector database
    Given User opens Main Menu
    When User clicks on Open Model
    ##And User checks if 'Model' catalog created and Deletes the 'TextEmbeddings'
    And User clicks on Add Model
    And User select the zip icon option to upload file for 'model'
    And User uploads the file 'Model/Llama_model.zip'
    And User click on Upload button for 'model'
    And User can see a toast message as 'Model uploaded successfully!'
    And User clicks on Copy Catalog ID
    And User clicks on Edit button
    And User add Tags 'embeddings' and presses Enter
    And User clicks on Submit button
    And User opens Main Menu
    And User clicks on Open Vector
    And User clicks on Add Vector button
    And User selects 'FAISS' connection
    And User enters vector database Catalog name as 'FAISS Vector DB00'
    And User selects 'Llama3-70B-Instruct' from Embedder field
    And User selects 'Token' from Chunking Strategy field
    And User enters value of Content Length as '510'
    And User enters value of Content Overlap as '17'
    And User clicks on Create Vector button
    And User clicks on Copy Catalog ID
    Then User can see vector database created success toast message as 'Successfully added vector database to catalog'

  Scenario: Validate the Vector - Q&A tab
    Given User can see the Vector title as 'FAISS Vector DB00'
    And User can see the 'Q&A' tab is displayed
    When User clicks on the Q&A tab
    Then User can see the 'Adjust Configurations' panel should be visible
    And User can see the 'Select Model' dropdown should be present
    And User can see the 'Limit the queried results:' slider should be visible
    And User can see the 'Set Temperature:' slider should be visible
    And User hover on 'Limit the queried results:' option and see the "	This will change the amount of chunks pulled from a vector database. Pulling too many chunks can potentially cause your engine's token limit to be exceeded!"
    And User hover on 'Set Temperature:' option and see the "This changes the randomness of the LLM's output. The higher the temperature the more creative and imaginative your answer will be."
    And User can see Q&A header should be displayed
    And User sees question input textbox should be visible
    And User should see the Generate Answer button in enable

  Scenario: Verify Select Model dropdown functionality
    Given User can see the Vector title as 'FAISS Vector DB00'
    And User clicks on the Q&A tab
    When User clicks on Select Model dropdown
    Then User selects a model 'Llama3-70B-Instruct'
    Then Selected model should be shown in dropdown 'Llama3-70B-Instruct'

  Scenario: Verify sliders are adjustable
    Given User can see the Vector title as 'FAISS Vector DB00'
    And User clicks on the Q&A tab
    When User adjusts "Limit the queried results:" slider to '8'
    Then The value should be updated as '8' for "Limit the queried results:"
    When User adjusts "Set Temperature:" slider to '0.6'
    Then The value should be updated as '0.6' for "Set Temperature:"

  Scenario: Verify answer is displayed when user asks a question
    Given User can see the Vector title as 'FAISS Vector DB00'
    And User clicks on the Q&A tab
    And User clicks on Select Model dropdown
    And User selects a model 'Llama3-70B-Instruct'
    When User enters a question "What is vector database?" in the question input textbox
    And User clicks on Generate Answer button
    Then User sees the answer generated for the question
