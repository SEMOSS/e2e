@LoginWithAdmin @Regression
Feature: Drag and Drop App setting Page

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User opens Main Menu
    And User is on Home page
    And User clicks on Open App Library
    When User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    And User clicks on Block Settings option

  @DeleteCreatedTestApp
  Scenario: Setting page - Access Control Tab - validate the Member option for drag and drop app
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
  Scenario: Settings page - Setting Tab validate the Apps option for drag and drop app
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

  Scenario: Setting page - Access Control Tab - validate the General option for drag and drop app
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
    And User sees the following sections and on clicking the copy button, "Successfully copied to clipboard" toast message appears:
      | SECTION NAME                                             |
      | VS Code (MCP Integration)                                |
      | Claude Desktop (MCP Server Connection)                   |
      | Claude with custom backend and MCP (Best for AI Tooling) |
      | OpenAI Codex / CLI Tools (MCP Connection)                |
      | Terminal Command (npx mcp-remote)                        |
      | cURL Command (Manual MCP JSON-RPC Request)               |
      | JavaScript (Node.js — fetch / axios)                     |
      | Python (requests)                                        |
