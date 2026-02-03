### The UI has changed and is currently in progress. Once it becomes stable, we will update the test cases accordingly.

#Feature: Playground Home
#
    #Background: Validate Playground Home page elements
        #Given User is on Home page
        #When User opens Main Menu
        #And User clicks on Open Model
        #And User clicks on Add Model
        #And User clicks on file upload icon
        #And User uploads the file 'Model/Llama3-70B-Instruct.zip'
        #And User clicks on 'Upload' button to create catalog
        #And User clicks on Copy Catalog ID
        #And User is on Home page
        #And User opens Main Menu
        #And User clicks on Open App Library
        #And User clicks on Create New App button
        #And User clicks on Get Started button in "Develop in code"
        #And User enters app name as 'PlaygroundMCP app'
        #And User enters description as 'Created by automation script'
        #And User enters tags 'MCP' and presses Enter
        #And User clicks on Create button
        #And User fetch the app name 
        #And User clicks on the file icon in the left panel
        #And User uploads the file 'PlaygroundMCP/mcp.zip'
        #And User selects the unzip checkbox
        #And User clicks on 'Upload' button to create catalog
        #And User clicks on the publish icon to publish the code app
        #And User is on Home page
        #And User clicks on Build button
        #And User clicks on Try it out hyperlink for Experiment in our Playground
#
        #@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp.
        #Scenario: Validate Playground Home page titles and buttons
        #Then User able to see the following Titles:
          #| Welcome |
          #| This is a demo environment for learning purposes only. Ready to start exploring and experimenting? |
        #And User is able to see the following Buttons:
          #| Attach Documents |
          #| Open Configuration Menu |
          #| Record the Model |
        #And User sees the Prompt textarea with placeholder 'What do you want to do today?'
        #And User verifies that the 'Prompt the Model' button is 'disabled'
        #When User enters prompt in the Prompt textarea 'What is SEMOSS?'
        #Then User verifies that the 'Prompt the Model' button is 'enabled'
        #And User clicks on the "Open Configuration Menu" button
        #And User sees the Configuration Menu is opened