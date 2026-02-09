# Feature: Playground Home model MCP to verify configuration tab

#   Background: Playground Configuration tab for Model - add model
#     Given User is on Home page
#     When User opens Main Menu
#     And User clicks on Open Model
#     And User clicks on Add Model
#     And User add "2" models with details "OpenAI" "GPT 3.5 Turbo" "Model" "Test@1234" "text-generation"
#     And User clicks on Copy Catalog ID

#   @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp
#   Scenario: Validate Playground Configuration tab for Model - add/search Model
#     Given User is on Home page
#     When User clicks on Build button
#     And User clicks on Try it out hyperlink for Experiment in our Playground
#     And User clicks on the "Open settings" button
#     And User clicks on the MCP dropdown
#     Then User should see and select the 'PlaygroundMCP app' in the MCP availale tools
#     And User verify added 'PlaygroundMCP app' is updated in selected list
#     When User saves the added MCP list
#     Then User verify the added 'PlaygroundMCP app' is displayed in MCP section
#     When User deletes the added 'PlaygroundMCP app' from MCP section
#     Then User verify the 'PlaygroundMCP app' is removed from MCP sections
#     When User clicks on the MCP dropdown
#     And User search for 'PlaygroundMCP app' in the MCP available tools & selects it
#     Then User verify added 'PlaygroundMCP app' is updated in selected list
#     When User saves the added MCP list
#     Then User verify the added 'PlaygroundMCP app' is displayed in MCP section
