@LoginWithAdmin @Regression @DeleteCreatedTestApp
Feature: MCP Usage Tab from Setting option

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
    And User click on Settings

  Scenario: Verify the all section are display in MCP Tab along with their code and copy option
    When User clicks on 'MCP Usage' tab
    Then User can see the 'Available Tools' section on setting page
    And User can see the 'VS Code (MCP Integration)' section on setting page
    And User clicks on copy button for 'VS Code (MCP Integration)' section
    And User sees success toast message 'Successfully copied to clipboard'
    And User can see the 'Claude Desktop (MCP Server Connection)' section on setting page
    And User clicks on copy button for 'Claude Desktop (MCP Server Connection)' section
    And User sees success toast message 'Successfully copied to clipboard'
    And User can see the 'Claude with custom backend and MCP (Best for AI Tooling)' section on setting page
    And User clicks on copy button for 'Claude with custom backend and MCP (Best for AI Tooling)' section
    And User sees success toast message 'Successfully copied to clipboard'
    And User can see the 'OpenAI Codex / CLI Tools (MCP Connection)' section on setting page
    And User clicks on copy button for 'OpenAI Codex / CLI Tools (MCP Connection)' section
    And User sees success toast message 'Successfully copied to clipboard'
    And User can see the 'Terminal Command (npx mcp-remote)' section on setting page
    And User clicks on copy button for 'Terminal Command (npx mcp-remote)' section
    And User sees success toast message 'Successfully copied to clipboard'
    And User can see the 'cURL Command (Manual MCP JSON-RPC Request)' section on setting page
    And User clicks on copy button for 'cURL Command (Manual MCP JSON-RPC Request)' section
    And User sees success toast message 'Successfully copied to clipboard'
    And User can see the 'JavaScript (Node.js — fetch / axios)' section on setting page
    And User clicks on copy button for 'JavaScript (Node.js — fetch / axios)' section
    And User sees success toast message 'Successfully copied to clipboard'
    And User can see the 'Python (requests)' section on setting page
    And User clicks on copy button for 'Python (requests)' section
    And User sees success toast message 'Successfully copied to clipboard'
