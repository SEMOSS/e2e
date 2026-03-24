@Regression @LoginWithAdmin
Feature: Code App - Setting page

  Background: Create the code app
    Given User is on Home page
    And User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Develop in code"
    And User enters app name as 'Code app'
    And User enters description as 'Created by automation script'
    And User enters tags 'MCP' and presses Enter
    And User clicks on Create button
    Then User fetch the app name

  @DeleteCreatedTestApp @ApplicationBugFailure
  Scenario: Setting page - Access Control Tab - validate the Member option for code app
    Given User click on Settings
    When User clicks on Access Control Tab
    Then User can see the 'Access Settings' section on setting page
    And User can see the 'Pending Requests' section on setting page
    When User clicks on Add Member button
    Then User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user
    When User clicks on Add Member button
    Then User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteCreatedTestApp
  Scenario: Settings page - Setting Tab - validate the Apps option for code app
    Given User click on Settings
    When User clicks on 'Settings' tab for Apps
    Then User can see the 'Portals' section on setting page
    And User can 'enable' the portal
    When User click on Publish Portal button
    Then User sees success toast message 'Successfully published'
    And User can see the 'Reactors' section on setting page
    When User click on 'Compile Changes on This Instance' button on setting page
    Then User sees success toast message 'Successfully compiled'
    When User click on 'Deploy and Persist Changes' button on setting page
    Then User sees success toast message 'Successfully compiled and deployed'
    And User can see the 'Update Project' section on setting page
    And User uploads the file 'dummy-pdf.pdf'
    And User click on 'Update' button on setting page

  Scenario: Setting page - Access Control Tab - validate the General option for code app
    Given User click on Settings
    When User clicks on Access Control Tab
    Then User turn OFF the Private option
    And 'Admin' user can see toaster message is 'Successfully made .* public'
    When User turn ON the Private option
    Then 'Admin' user can see toaster message is 'Successfully made .* private'
    When User turn OFF the Non Discoverable option
    Then 'Admin' user can see toaster message is 'Successfully made .* discoverable'
    When User turn ON the Non Discoverable option
    Then 'Admin' user can see toaster message is 'Successfully made .* undiscoverable'
    When User can see the 'Delete Project' section on General setting page
    Then 'Admin' user 'can' Delete Catalog

  @DeleteCreatedTestApp
  Scenario: Verify the all section are display in MCP Tab along with their code and copy option for drag and drop app
    Given User click on Settings
    When User clicks on 'MCP Usage' tab
    Then User can see the 'Available Tools' section on setting page
    And User verifies section visibility, copy action, and toast message
      | Section Name                                             | Toast Message                    |
      | VS Code (MCP Integration)                                | Successfully copied to clipboard |
      | Claude Desktop (MCP Server Connection)                   | Successfully copied to clipboard |
      | Claude with custom backend and MCP (Best for AI Tooling) | Successfully copied to clipboard |
      | OpenAI Codex / CLI Tools (MCP Connection)                | Successfully copied to clipboard |
      | Terminal Command (npx mcp-remote)                        | Successfully copied to clipboard |
      | cURL Command (Manual MCP JSON-RPC Request)               | Successfully copied to clipboard |
      | JavaScript (Node.js — fetch / axios)                     | Successfully copied to clipboard |
      | Python (requests)                                        | Successfully copied to clipboard |
