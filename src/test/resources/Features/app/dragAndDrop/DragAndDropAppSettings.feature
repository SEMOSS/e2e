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
    And User clicks on Access Control Tab
    And User can see the 'Access Settings' section on setting page
    And User can see the 'Pending Requests' section on setting page
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User Search 'Editor' user from Access Control
    And User deletes the 'Editor' user
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Read'
    And User Search 'Read' user from Access Control
    And User deletes the 'Read' user

  @DeleteCreatedTestApp
  Scenario: Settings page - Setting Tab validate the Apps option for drag and drop app
    Given User click on Settings
    And User clicks on 'Settings' tab for Apps
    And User can see the 'Portals' section on setting page
    And User can 'enable' the portal
    And User click on Publish Portal button
    And User sees success toast message 'Successfully published'
    And User can see the 'Reactors' section on setting page
    And User click on 'Compile Changes on This Instance' button on setting page
    And User sees success toast message 'Successfully compiled'
    And User click on 'Deploy and Persist Changes' button on setting page
    And User sees success toast message 'Successfully compiled and deployed'
    And User can see the 'Update Project' section on setting page
    And User uploads the file 'dummy-pdf.pdf'
    And User click on 'Update' button on setting page

  Scenario: Setting page - Access Control Tab - validate the General option for drag and drop app
    Given User click on Settings
    And User clicks on Access Control Tab
    And User turn OFF the Private option
    And 'Admin' user can see toaster message is 'Successfully made .* public'
    And User turn ON the Private option
    And 'Admin' user can see toaster message is 'Successfully made .* private'
    And User turn OFF the Non Discoverable option
    And 'Admin' user can see toaster message is 'Successfully made .* discoverable'
    And User turn ON the Non Discoverable option
    And 'Admin' user can see toaster message is 'Successfully made .* undiscoverable'
    And User can see the 'Delete Project' section on General setting page
    And 'Admin' user 'can' Delete Catalog
    
    Scenario: Verify the all section are display in MCP Tab along with their code and copy option for drag and drop app
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
    
