Feature: Playground Home App MCP to verify configuration tab

  Background: Playground Configuration tab for Model - add model
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User add "2" app with details "Drag and Drop" "PlaygroundMCP app" "Created for Playground" "MCP"
    And User fetch the app name

  @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp
  Scenario: Validate Playground Configuration tab for App - add/search MCP APP
    Given User is on Home page
    When User clicks on Build button
    And User clicks on Try it out hyperlink for Experiment in our Playground
    And User clicks on the "Open settings" button
    And User clicks on the Toolbox dropdown
    Then User should see and select the 'PlaygroundMCP app1' in the Toolbox available tools
    And User verify added 'PlaygroundMCP app' is updated in selected list
    When User saves the added Toolbox list
    Then User verify the added 'PlaygroundMCP app1' is displayed in Toolbox section
    When User deletes the added 'PlaygroundMCP app1' from Toolbox section
    Then User verify the 'PlaygroundMCP app1' is removed from Toolbox section
    When User clicks on the Toolbox dropdown
    And User search for 'PlaygroundMCP app0' in the Toolbox available tools & selects it
    Then User verify added 'PlaygroundMCP app0' is updated in selected list
    When User saves the added Toolbox list
    Then User verify the added 'PlaygroundMCP app0' is displayed in Toolbox section
