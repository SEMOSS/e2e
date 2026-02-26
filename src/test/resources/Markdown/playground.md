# Playground BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/playground/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted exclusively from the playground-related feature files located under `src/test/resources/Features/playground/`. It covers Playground Home Chat (prompt/response, history), Playground Configuration tab (Model add/search, App MCP tools, Knowledge/Vector, Max Token, Temperature, Instructions), and Playground Workspace management (create, switch, edit, search, delete).

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | Chat, Configuration Model, Configuration Knowledge, Workspace | Validates user is on the Home page |
| 2 | `Given User opens Main Menu` | Configuration MCP App, Configuration Knowledge | Opens the main navigation menu |
| 3 | `Given User sees the Prompt textarea with placeholder '{string}'` | Chat | Validates Prompt textarea placeholder text (e.g., '/ to open menu') |

---

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | Chat, Configuration Model, Configuration MCP App, Configuration Knowledge, Workspace | Opens the main navigation menu |
| 2 | `When User is on Home page` | Chat, Workspace | Navigates to Home page |
| 3 | `When User clicks on Build button` | Chat, Configuration Model, Configuration MCP App, Configuration Knowledge, Workspace | Clicks Build button on Home page |
| 4 | `When User clicks on Try it out hyperlink for Experiment in our Playground` | Chat, Configuration Model, Configuration MCP App, Configuration Knowledge, Workspace | Clicks Try it out hyperlink to open Playground |

### Model Creation (Background/Prerequisite)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `When User clicks on Open Model` | Chat, Configuration Model, Configuration Knowledge, Workspace | Opens Model catalog page |
| 6 | `When User clicks on Add Model` | Chat, Configuration Model, Configuration Knowledge, Workspace | Clicks Add Model button |
| 7 | `When User clicks on file upload icon` | Chat, Workspace | Clicks file upload icon |
| 8 | `When User uploads the file '{string}'` | Chat, Workspace | Uploads a file (e.g., 'Model/Llama3-70B-Instruct.zip') |
| 9 | `When User clicks on 'Upload' button to create catalog` | Chat, Workspace | Clicks Upload button to create catalog |
| 10 | `When User clicks on Edit button` | Chat, Configuration Knowledge, Workspace | Clicks Edit button on catalog |
| 11 | `When User add Tags '{string}' and presses Enter` | Chat, Configuration Knowledge, Workspace | Adds tags (e.g., 'text-generation', 'embeddings') |
| 12 | `When User clicks on Submit button` | Chat, Configuration Knowledge, Workspace | Clicks Submit button |
| 13 | `When User clicks on Copy Catalog ID` | Chat, Configuration Model, Configuration Knowledge, Workspace | Copies Catalog ID |
| 14 | `When User add "{int}" models with details "{string}" "{string}" "{string}" "{string}" "{string}"` | Configuration Model | Adds multiple models with specified details (type, variant, name, key, tag) |
| 15 | `When User selects '{string}' type` | Configuration Knowledge | Selects model type (e.g., 'OpenAI') |
| 16 | `When User selects '{string}'` | Configuration Knowledge | Selects model variant (e.g., 'GPT 3.5 Turbo') |
| 17 | `When User enters Catalog Name as '{string}'` | Configuration Knowledge | Enters catalog name for model |
| 18 | `When User enters Open AI Key as '{string}'` | Configuration Knowledge | Enters OpenAI API key |
| 19 | `When User clicks on Create Model button` | Configuration Knowledge | Clicks Create Model button |

### App Creation (Background/Prerequisite for MCP)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 20 | `When User clicks on Open App Library` | Configuration MCP App | Opens App Library |
| 21 | `When User clicks on Create New App button` | Configuration MCP App | Clicks Create New App button |
| 22 | `When User clicks on Get Started button in "{string}"` | Configuration MCP App | Clicks Get Started (e.g., 'Drag and Drop') |
| 23 | `When User add "{int}" app with details "{string}" "{string}" "{string}" "{string}"` | Configuration MCP App | Adds multiple apps with specified details (method, name, description, tag) |
| 24 | `When User fetch the app name` | Configuration MCP App | Fetches the created app name |

### Vector Creation (Background/Prerequisite for Knowledge)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 25 | `When User clicks on Open Vector` | Configuration Knowledge | Opens Vector catalog page |
| 26 | `When User clicks on Add Vector button` | Configuration Knowledge | Clicks Add Vector button |
| 27 | `When User add "{int}" vectors with details "{string}" "{string}" "{string}" "{string}"` | Configuration Knowledge | Adds multiple vectors with specified details (connection, name, tag, embedder) |

### Playground Chat

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 28 | `When User enters prompt in the Prompt textarea '{string}'` | Chat | Enters prompt text (e.g., 'tell me a joke') |
| 29 | `When User clicks on the "Ask the AI" button` | Chat | Clicks Ask the AI button to submit prompt |
| 30 | `When User waits for the response from the model` | Chat | Waits for model response |
| 31 | `When User clicks on sidebar toggle button` | Chat | Clicks sidebar toggle button to show/hide history |

### Playground Configuration - Model

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 32 | `When User clicks on the "Open settings" button` | Configuration Model, Configuration MCP App, Configuration Knowledge | Clicks Open settings button to open configuration panel |
| 33 | `When User clicks on the Model dropdown` | Configuration Model | Clicks Model dropdown in configuration |
| 34 | `When User searches the '{string}' configuration tab in the model catalog searchbox` | Configuration Model | Searches for model in configuration searchbox |
| 35 | `When User selects the '{string}' from the model catalog dropdown` | Configuration Model | Selects model from dropdown |

### Playground Configuration - App MCP (Toolbox)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 36 | `When User clicks on the Toolbox dropdown` | Configuration MCP App | Clicks Toolbox dropdown |
| 37 | `When User saves the added Toolbox list` | Configuration MCP App | Saves the added Toolbox tools list |
| 38 | `When User deletes the added '{string}' from Toolbox section` | Configuration MCP App | Deletes a tool from Toolbox section |
| 39 | `When User search for '{string}' in the Toolbox available tools & selects it` | Configuration MCP App | Searches and selects a tool in Toolbox |

### Playground Configuration - Knowledge

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 40 | `When User clicks on the Knowledge dropdown` | Configuration Knowledge | Clicks Knowledge dropdown |
| 41 | `When User saves the added Knowledge list` | Configuration Knowledge | Saves the added Knowledge list |
| 42 | `When User deletes the added '{string}' from Knowledge section` | Configuration Knowledge | Deletes a knowledge item from Knowledge section |
| 43 | `When User searches the '{string}' configuration tab in the Knowledge catalog searchbox` | Configuration Knowledge | Searches for knowledge in searchbox |

### Playground Workspace

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 44 | `When User sees the Prompt textarea with placeholder '{string}'` | Workspace | Validates Prompt textarea placeholder |
| 45 | `When User click on the workspace button` | Workspace | Clicks workspace button |
| 46 | `When User clicks on Create New Workspace button` | Workspace | Clicks Create New Workspace button |
| 47 | `When User enters workspace name as '{string}'` | Workspace | Enters workspace name (e.g., 'PlaygroundWorkspace1') |
| 48 | `When User enters workspace description as '{string}'` | Workspace | Enters workspace description |
| 49 | `When User clicks on Create button to create workspace` | Workspace | Clicks Create button to create workspace |
| 50 | `When User selects the '{string}' from the workspace list` | Workspace | Selects workspace from the list |
| 51 | `When User selects the '{string}' as new chat from the workspace list` | Workspace | Selects workspace as new chat |
| 52 | `When User clicks on Edit Workspace button` | Workspace | Clicks Edit Workspace button |
| 53 | `When User updates workspace name to '{string}'` | Workspace | Updates workspace name |
| 54 | `When User updates workspace description to '{string}'` | Workspace | Updates workspace description |
| 55 | `When User clicks on Save button to save workspace changes` | Workspace | Clicks Save to save workspace edits |
| 56 | `When User enters '{string}' in the workspace search box` | Workspace | Enters text in workspace search box |
| 57 | `When User clicks on Delete Workspace button` | Workspace | Clicks Delete Workspace button |

---

## Then Steps

### Playground Chat Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User verifies that the '{string}' button is '{string}'` | Chat | Validates button state (e.g., 'Ask the AI' is 'disabled' or 'enabled') |
| 2 | `Then User verifies that the response from the model is displayed as Prompt` | Chat | Validates model response is displayed |

### Playground Configuration - Model Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `Then User verify the model catalog dropdown is present with default model with '{string}' name` | Configuration Model | Validates default model in dropdown |
| 4 | `Then User verify "{string}" model should be checked in the model catalog dropdown` | Configuration Model | Validates specific model is checked/selected in dropdown |
| 5 | `Then User should see the '{string}' in the model catalog dropdown` | Configuration Model | Validates model is visible in dropdown after search |

### Playground Configuration - App MCP (Toolbox) Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 6 | `Then User should see and select the '{string}' in the Toolbox available tools` | Configuration MCP App | Validates and selects tool in Toolbox dropdown |
| 7 | `Then User verify added '{string}' is updated in selected list` | Configuration MCP App | Validates tool is updated in selected list |
| 8 | `Then User verify the added '{string}' is displayed in Toolbox section` | Configuration MCP App | Validates tool is displayed in Toolbox section after save |
| 9 | `Then User verify the '{string}' is removed from Toolbox section` | Configuration MCP App | Validates tool is removed from Toolbox section |

### Playground Configuration - Knowledge Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `Then User Verify the Instructions section is displayed` | Configuration Knowledge | Validates Instructions section is visible |
| 11 | `Then User Verify the Max Token section is displayed with default value & stepper control` | Configuration Knowledge | Validates Max Token section with default value and controls |
| 12 | `Then User Verify the Temperature section is displayed with default value '{string}'` | Configuration Knowledge | Validates Temperature section with default value (e.g., '.30') |
| 13 | `Then User should see and select the '{string}' in the Knowledge available tools` | Configuration Knowledge | Validates and selects knowledge in dropdown |
| 14 | `Then User verify "{string}" knowledge should be checked in the knowledge catalog dropdown` | Configuration Knowledge | Validates knowledge is checked in dropdown |
| 15 | `Then User verify the added '{string}' is displayed in Knowledge section` | Configuration Knowledge | Validates knowledge is displayed after save |
| 16 | `Then User verify the '{string}' is removed from Knowledge section` | Configuration Knowledge | Validates knowledge is removed from section |

### Playground Workspace Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `Then User verifies that '{string}' workspace is created with description '{string}'` | Workspace | Validates workspace is created with correct description |
| 18 | `Then User verifies that '{string}' workspace is selected in the workspace list` | Workspace | Validates workspace is selected in the list |
| 19 | `Then User verifies that '{string}' workspace is selected in the workspace "{string}" menu` | Workspace | Validates workspace is selected in Open settings menu |
| 20 | `Then User verifies that '{string}' workspace is updated with new description '{string}'` | Workspace | Validates workspace is updated with new description |
| 21 | `Then User verifies that '{string}' workspace is displayed in the search results` | Workspace | Validates workspace appears in search results |
| 22 | `Then User verifies that workspace is deleted and not present in the workspace list` | Workspace | Validates workspace is deleted and no longer in list |

---

## Playground Configuration Sections

### Model Configuration

| # | Element | Description |
|---|---------|-------------|
| 1 | Model dropdown | Dropdown to select/search model catalogs |
| 2 | Default model | First model created is set as default |
| 3 | Model search | Search within model catalog dropdown |

### App MCP Configuration (Toolbox)

| # | Element | Description |
|---|---------|-------------|
| 1 | Toolbox dropdown | Dropdown to select/search MCP app tools |
| 2 | Selected list | Shows selected tools |
| 3 | Toolbox section | Displays saved tools |
| 4 | Delete tool | Remove a tool from Toolbox section |
| 5 | Search tool | Search for a specific tool in available tools |

### Knowledge Configuration (Vector)

| # | Element | Description |
|---|---------|-------------|
| 1 | Knowledge dropdown | Dropdown to select/search vector knowledge bases |
| 2 | Knowledge section | Displays saved knowledge items |
| 3 | Delete knowledge | Remove a knowledge item from section |
| 4 | Knowledge search | Search within knowledge catalog dropdown |

### Other Configuration Sections

| # | Section | Default Value | Description |
|---|---------|---------------|-------------|
| 1 | Instructions | (empty) | Text area for system instructions |
| 2 | Max Token | Default value with stepper control | Controls maximum token output |
| 3 | Temperature | `.30` | Controls randomness of model output |

---

## Chat Elements

| # | Element | Description |
|---|---------|-------------|
| 1 | Prompt textarea | Placeholder: `/ to open menu` |
| 2 | Ask the AI button | Disabled when empty, enabled when prompt entered |
| 3 | Model response | Displayed as Prompt after submission |
| 4 | Sidebar toggle button | Shows/hides chat history sidebar |

---

## Workspace Management

| # | Action | Description |
|---|--------|-------------|
| 1 | Create Workspace | Enter name + description → Create |
| 2 | Switch Workspace | Select workspace from list or as new chat |
| 3 | Edit Workspace | Update name + description → Save |
| 4 | Search Workspace | Enter text in workspace search box |
| 5 | Delete Workspace | Select workspace → Delete |

---

## Test Data Summary

### Model Creation (Chat & Workspace Background)

| Field | Value |
|-------|-------|
| Upload File | `Model/Llama3-70B-Instruct.zip` |
| Tags | `text-generation` |

### Model Creation (Configuration Model Background)

| Field | Value |
|-------|-------|
| Count | 2 models |
| Type | `OpenAI` |
| Variant | `GPT 3.5 Turbo` |
| Catalog Name | `Model` (Model1, Model2) |
| API Key | `Test@1234` |
| Tags | `text-generation` |

### App Creation (Configuration MCP App Background)

| Field | Value |
|-------|-------|
| Count | 2 apps |
| Method | `Drag and Drop` |
| App Name | `PlaygroundMCP app` (PlaygroundMCP app0, PlaygroundMCP app1) |
| Description | `Created for Playground` |
| Tags | `MCP` |

### Vector/Knowledge Creation (Configuration Knowledge Background)

| Field | Value |
|-------|-------|
| Model Type | `OpenAI` |
| Model Variant | `GPT 3.5 Turbo` |
| Model Catalog Name | `ModelCatalog` |
| Model API Key | `Test@1234` |
| Model Tags | `embeddings` |
| Vector Count | 2 vectors |
| Vector Connection | `FAISS` |
| Vector Name | `FAISSVector` (FAISSVector10, FAISSVector11) |
| Vector Tags | `MCP` |
| Vector Embedder | `ModelCatalog` |

### Workspace Test Data

| Field | Value |
|-------|-------|
| Workspace Name | `PlaygroundWorkspace1` |
| Workspace Description | `Workspace created by automation script` |
| Updated Name | `PlaygroundWorkspaceUpdated` |
| Updated Description | `Workspace updated by automation script` |

---

## Feature Files Summary

| # | Feature File | Tags | Scenarios | Description |
|---|-------------|------|-----------|-------------|
| 1 | Playground Home Chat | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | 1 Scenario | Validate chat prompt/response, Ask the AI button states, sidebar toggle |
| 2 | Playground Home Configuration - Model | `@LoginWithAdmin @Regression @DeleteTestCatalog` | 1 Scenario | Validate Model dropdown, default model, search model, select model |
| 3 | Playground Home Configuration - App MCP | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | 1 Scenario | Validate Toolbox dropdown, add/search/delete MCP app tools |
| 4 | Playground Home Configuration - Knowledge, Max Token, Temperature, Instructions | `@LoginWithAdmin @Regression @DeleteTestCatalog` | 1 Scenario | Validate Knowledge dropdown, add/search/delete knowledge, Instructions, Max Token, Temperature sections |
| 5 | Playground Home Workspace | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | 5 Scenarios | Add workspace, switch workspace, edit workspace, search workspace, delete workspace |

---

## Scenario Details

### Feature: Playground Home Chat

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate Playground chat validations - add/remove history prompt | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | Background uploads model with text-generation tag → navigates to Playground → validates Prompt placeholder → validates Ask the AI disabled → enters prompt → validates enabled → clicks Ask the AI → waits for response → validates response displayed → clicks sidebar toggle |

### Feature: Playground Home Configuration - Model

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate Playground Configuration tab for Model - add/search Model | `@LoginWithAdmin @Regression @DeleteTestCatalog` | Background creates 2 models → navigates to Playground → Open settings → validates default model in dropdown → clicks Model dropdown → validates default checked → searches Model2 → validates visible → selects Model2 → validates Model2 checked |

### Feature: Playground Home Configuration - App MCP

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate Playground Configuration tab for App - add/search MCP APP | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | Background creates 2 apps → navigates to Playground → Open settings → Toolbox dropdown → selects PlaygroundMCP app1 → validates in selected list → saves → validates displayed → deletes → validates removed → searches PlaygroundMCP app0 → selects → saves → validates displayed |

### Feature: Playground Home Configuration - Knowledge, Max Token, Temperature, Instructions

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate Playground Configuration tab for knowledge - add/search/delete knowledge, Max token, Temperature and Instructions section | `@LoginWithAdmin @Regression @DeleteTestCatalog` | Background creates model + 2 vectors → navigates to Playground → Open settings → validates Instructions section → validates Max Token with default value & stepper → validates Temperature default '.30' → Knowledge dropdown → selects FAISSVector11 → validates checked → saves → validates displayed → deletes → validates removed → searches FAISSVector11 |

### Feature: Playground Home Workspace

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate Playground workspace - add workspace | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | Background uploads model → navigates to Playground → clicks workspace → Create New Workspace → enters name/description → creates → validates created → selects → validates selected |
| 2 | Validate Playground workspace - switch workspace | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | Navigates to Playground → clicks workspace → selects PlaygroundWorkspace1 as new chat → validates selected in Open settings menu |
| 3 | Validate Playground workspace - edit workspace | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | Navigates to Playground → clicks workspace → selects PlaygroundWorkspace1 → Edit Workspace → updates name/description → saves → validates updated → selects → validates selected |
| 4 | Validate Playground workspace - search workspace | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | Navigates to Playground → clicks workspace → enters search text → validates workspace displayed in search results |
| 5 | Validate Playground workspace - delete workspace | `@LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp` | Navigates to Playground → clicks workspace → selects PlaygroundWorkspace1 → Delete Workspace → validates workspace is deleted and not in list |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 3 |
| When Steps | 57 |
| Then Steps | 22 |
| **Total** | **82** |

---