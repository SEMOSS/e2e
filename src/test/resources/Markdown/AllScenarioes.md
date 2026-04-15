# All Cucumber BDD Steps Documentation


> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/`

---

## Table of Contents

- [Startup](#startup)
- [Home](#home)
- [Playground](#playground)
- [App](#app)
- [Model](#model)
- [Vector](#vector)
- [Database](#database)
- [Function](#function)
- [Storage](#storage)
- [Guardrail](#guardrail)
- [Settings](#settings)
- [System Apps](#system-apps)

---

---

# Startup

> **Feature Path:** `src/test/resources/Features/startup/`  
> **Step Files:** `aicore.steps` (Login/Registration steps)

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User logs out from the application` | AI Core Application, Register New User | Logs out the current user from the application |
| 2 | `Given User is on login page` | AI Core Application | Validates user is on the login page |

## When Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User enters username as '{string}'` | AI Core Application, Register New User | Enters username in login field |
| 2 | `When User enters password as '{string}'` | AI Core Application, Register New User | Enters password in login field |
| 3 | `When User clicks on Login button` | AI Core Application, Register New User | Clicks the Login button |
| 4 | `When User clicks on Register button` | Register New User | Clicks the Register button |
| 5 | `When User enters name as '{string}'` | Register New User | Enters user name during registration |
| 6 | `When User enters email as '{string}'` | Register New User | Enters email during registration |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can navigate to home page` | AI Core Application | Validates successful navigation to home page after login |
| 2 | `Then User can see 'Account registration successful. Log in below.' message on login page` | Register New User | Validates registration success message |
| 3 | `Then User should navigate to home page` | Register New User | Validates newly registered user navigates to home page after login |

---

# Home

> **Feature Path:** `src/test/resources/Features/home/`  
> **Step Files:** `HomePageSteps.java`

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | Validate Build Page, Search App and Catalogs | Validates user is on the Home page |
| 2 | `Given User opens Main Menu` | Validate Build Page, Search App and Catalogs | Opens the main navigation menu |

## When Steps

### Navigation - Home & Main Menu

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | Validate Build Page, Search App and Catalogs | Opens the main navigation menu |
| 2 | `When User clicks on Home` | Search App and Catalogs | Navigates to the Home page |

### Build Page

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `When User clicks on Build button` | Validate Build Page | Clicks the Build button on Home page |

### App Library & System Apps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `When User clicks on Open App Library` | Validate Build Page, Search App and Catalogs | Opens the App Library page |
| 5 | `When User click on System Apps` | Validate Build Page | Clicks System Apps filter |

### App Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 6 | `When User clicks on Create New App button` | Search App and Catalogs | Clicks Create New App button |
| 7 | `When User clicks on Get Started button in "Drag and Drop"` | Search App and Catalogs | Clicks Get Started in Drag and Drop section |
| 8 | `When User enters '{string}' as app name` | Search App and Catalogs | Enters app name |
| 9 | `When User enters description as '{string}'` | Search App and Catalogs | Enters app description |
| 10 | `When User enters tags '{string}' and presses Enter` | Search App and Catalogs | Enters tags and presses Enter |
| 11 | `When User clicks on Create button` | Search App and Catalogs | Clicks Create button to create app |

### Model Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 12 | `When User clicks on Open Model` | Search App and Catalogs | Opens the Model catalog page |
| 13 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | Search App and Catalogs | Checks if catalog exists and deletes it |
| 14 | `When User clicks on Add Model` | Search App and Catalogs | Clicks Add Model button |
| 15 | `When User selects '{string}' model type` | Search App and Catalogs | Selects model type (e.g., 'OpenAI') |
| 16 | `When User selects '{string}' model variant` | Search App and Catalogs | Selects model variant (e.g., 'GPT 3.5 Turbo') |
| 17 | `When User enters Model name '{string}'` | Search App and Catalogs | Enters model catalog name |
| 18 | `When User enters API key as '{string}'` | Search App and Catalogs | Enters API key |
| 19 | `When User clicks on Create Model button` | Search App and Catalogs | Clicks Create Model button |
| 20 | `When User clicks on Copy Catalog ID` | Search App and Catalogs | Copies the catalog ID |

### Catalog Edit (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `When User fetch the app name` | Search App and Catalogs | Fetches the created app name |

### Vector Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 22 | `When User clicks on Open Vector` | Search App and Catalogs | Opens the Vector catalog page |
| 23 | `When User clicks on Add Vector Database` | Search App and Catalogs | Clicks Add Vector Database button |
| 24 | `When User selects '{string}' vector connection` | Search App and Catalogs | Selects vector connection type |
| 25 | `When User enters Vector name '{string}'` | Search App and Catalogs | Enters vector catalog name |
| 26 | `When User selects '{string}' embedder` | Search App and Catalogs | Selects embedder model |
| 27 | `When User selects '{string}' chunking strategy` | Search App and Catalogs | Selects chunking strategy |
| 28 | `When User enters content length '{string}'` | Search App and Catalogs | Enters content length |
| 29 | `When User enters content overlap '{string}'` | Search App and Catalogs | Enters content overlap |
| 30 | `When User clicks on Create Vector button` | Search App and Catalogs | Clicks Create Vector button |
| 31 | `When User clicks on file upload icon` | Search App and Catalogs | Clicks file upload icon |

### Function Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 32 | `When User clicks on Open Function` | Search App and Catalogs | Opens the Function catalog page |
| 33 | `When User clicks on Add Function` | Search App and Catalogs | Clicks Add Function button |
| 34 | `When User uploads the file '{string}'` | Search App and Catalogs | Uploads a file |
| 35 | `When User clicks on 'Upload' button to create catalog` | Search App and Catalogs | Clicks Upload button |

### Database Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 36 | `When User clicks on Open Database` | Search App and Catalogs | Opens the Database catalog page |
| 37 | `When User clicks on Add Database` | Search App and Catalogs | Clicks Add Database button |

### Storage Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 38 | `When User clicks on Open Storage` | Search App and Catalogs | Opens the Storage catalog page |
| 39 | `When User clicks on Add Storage` | Search App and Catalogs | Clicks Add Storage button |

### Home Search

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 40 | `When User enters '{string}' in the home search box` | Search App and Catalogs | Enters text in home search box |
| 41 | `When User selects '{string}' filter` | Search App and Catalogs | Selects catalog filter type |
| 42 | `When User clicks on '{string}' from search results` | Search App and Catalogs | Clicks item from search results |
| 43 | `When User clicks on Search All` | Search App and Catalogs | Clicks Search All button |
| 44 | `When User unselects '{string}' filter` | Search App and Catalogs | Unselects catalog filter type |
| 45 | `When User clicks on Open App Library` | Search App and Catalogs | Opens App Library |
| 46 | `When User clicks on 'My Apps' tab` | Search App and Catalogs | Clicks My Apps tab |
| 47 | `When User searches '{string}' app in the app searchbox` | Search App and Catalogs | Searches for an app |
| 48 | `When User clicks on '{string}' app from the My Apps` | Search App and Catalogs | Clicks app from My Apps |
| 49 | `When User clicks on app Delete button` | Search App and Catalogs | Clicks Delete app button |

## Then Steps

### Build Page Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User able to see the following Titles:` | Validate Build Page | Validates all titles on the Build page using data table |
| 2 | `Then User able to see the following Buttons:` | Validate Build Page | Validates all buttons on the Build page using data table |
| 3 | `Then User able to see the "Browse Templates" button` | Validate Build Page | Validates Browse Templates button is visible |

### System Apps Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `Then User can see '{string}' app in the System Apps section` | Validate Build Page | Validates app is visible in System Apps |

### Search Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `Then User can see '{string}' in the search results` | Search App and Catalogs | Validates item appears in search results |
| 6 | `Then User can see the catalog details page` | Search App and Catalogs | Validates catalog details page is displayed |
| 7 | `Then User can see '{string}' app on the page` | Search App and Catalogs | Validates app is visible on page |

---

# Playground

> **Feature Path:** `src/test/resources/Features/playground/`  
> **Step Files:** `PlaygroundSteps.java`

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | Chat, Configuration Model, Configuration Knowledge, Workspace | Validates user is on the Home page |
| 2 | `Given User opens Main Menu` | Configuration MCP App, Configuration Knowledge | Opens the main navigation menu |
| 3 | `Given User sees the Prompt textarea with placeholder '{string}'` | Chat | Validates Prompt textarea placeholder text |

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | Chat, Configuration Model, Configuration MCP App, Configuration Knowledge, Workspace | Opens the main navigation menu |
| 2 | `When User is on Home page` | Chat, Workspace | Navigates to Home page |
| 3 | `When User clicks on Build button` | Chat, Configuration Model, Configuration MCP App, Configuration Knowledge, Workspace | Clicks Build button on Home page |
| 4 | `When User clicks on Try it out hyperlink for Experiment in our Playground` | Chat, Configuration Model, Configuration MCP App, Configuration Knowledge, Workspace | Clicks Try it out hyperlink to open Playground |

### Model Setup

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `When User clicks on Open Model` | Chat, Configuration Model, Configuration Knowledge | Opens the Model catalog page |
| 6 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | Chat, Configuration Model, Configuration MCP App, Configuration Knowledge | Checks if catalog exists and deletes it |
| 7 | `When User clicks on Add Model` | Chat, Configuration Model, Configuration Knowledge | Clicks Add Model button |
| 8 | `When User clicks on file upload icon` | Chat, Configuration Model, Configuration Knowledge | Clicks file upload icon |
| 9 | `When User uploads the file '{string}'` | Chat, Configuration Model, Configuration Knowledge | Uploads a file |
| 10 | `When User clicks on 'Upload' button to create catalog` | Chat, Configuration Model, Configuration Knowledge | Clicks Upload button |
| 11 | `When User clicks on Copy Catalog ID` | Chat, Configuration Model, Configuration Knowledge | Copies the catalog ID |

### MCP App Setup

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 12 | `When User clicks on Open App Library` | Configuration MCP App | Opens the App Library page |
| 13 | `When User clicks on Create New App button` | Configuration MCP App | Clicks Create New App button |
| 14 | `When User clicks on Get Started button in "{string}"` | Configuration MCP App | Clicks Get Started in code section |
| 15 | `When User enters app name as '{string}'` | Configuration MCP App | Enters app name |
| 16 | `When User enters description as '{string}'` | Configuration MCP App | Enters app description |
| 17 | `When User enters tags '{string}' and presses Enter` | Configuration MCP App | Enters tags |
| 18 | `When User clicks on Create button` | Configuration MCP App | Clicks Create button |
| 19 | `When User fetch the app name` | Configuration MCP App | Fetches created app name |
| 20 | `When User clicks on the file icon in the left panel` | Configuration MCP App | Clicks file icon in left panel |
| 21 | `When User selects the unzip checkbox` | Configuration MCP App | Selects unzip checkbox |
| 22 | `When User clicks on the publish icon to publish the code app` | Configuration MCP App | Publishes code app |

### Chat Operations

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 23 | `When User enters '{string}' in the Prompt textarea` | Chat | Enters text in prompt textarea |
| 24 | `When User clicks on Send button` | Chat | Clicks send button |
| 25 | `When User clicks on Ask the AI button` | Chat | Clicks Ask the AI button |
| 26 | `When User clicks on sidebar toggle` | Chat | Toggles sidebar |

### Configuration - Model

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 27 | `When User clicks on Configuration tab` | Configuration Model, Configuration MCP App, Configuration Knowledge | Opens Configuration tab |
| 28 | `When User clicks on Model dropdown` | Configuration Model | Clicks Model dropdown |
| 29 | `When User searches '{string}' in model search` | Configuration Model | Searches for model |
| 30 | `When User selects '{string}' model from dropdown` | Configuration Model | Selects model from dropdown |

### Configuration - MCP App Tools

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 31 | `When User clicks on Toolbox dropdown` | Configuration MCP App | Opens Toolbox dropdown |
| 32 | `When User searches '{string}' in toolbox search` | Configuration MCP App | Searches toolbox |
| 33 | `When User selects '{string}' tool from dropdown` | Configuration MCP App | Selects tool from dropdown |
| 34 | `When User clicks on delete icon for '{string}' tool` | Configuration MCP App | Deletes tool |

### Configuration - Knowledge

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 35 | `When User clicks on Knowledge dropdown` | Configuration Knowledge | Opens Knowledge dropdown |
| 36 | `When User searches '{string}' in knowledge search` | Configuration Knowledge | Searches knowledge |
| 37 | `When User selects '{string}' knowledge from dropdown` | Configuration Knowledge | Selects knowledge |
| 38 | `When User clicks on delete icon for '{string}' knowledge` | Configuration Knowledge | Deletes knowledge |

### Configuration - Max Token, Temperature, Instructions

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 39 | `When User clicks on Max Token section` | Configuration Knowledge | Opens Max Token section |
| 40 | `When User enters '{string}' in Max Token input` | Configuration Knowledge | Enters max token value |
| 41 | `When User clicks on Temperature section` | Configuration Knowledge | Opens Temperature section |
| 42 | `When User enters '{string}' in Temperature input` | Configuration Knowledge | Enters temperature value |
| 43 | `When User clicks on Instructions section` | Configuration Knowledge | Opens Instructions section |
| 44 | `When User enters '{string}' in Instructions textarea` | Configuration Knowledge | Enters instructions |

### Workspace Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 45 | `When User clicks on Workspace tab` | Workspace | Opens Workspace tab |
| 46 | `When User clicks on Create Workspace button` | Workspace | Creates new workspace |
| 47 | `When User enters workspace name as '{string}'` | Workspace | Enters workspace name |
| 48 | `When User clicks on Save Workspace button` | Workspace | Saves workspace |
| 49 | `When User clicks on '{string}' workspace` | Workspace | Switches to workspace |
| 50 | `When User clicks on edit icon for '{string}' workspace` | Workspace | Edits workspace |
| 51 | `When User enters new workspace name as '{string}'` | Workspace | Enters new workspace name |
| 52 | `When User clicks on Update Workspace button` | Workspace | Updates workspace name |
| 53 | `When User searches '{string}' in workspace search` | Workspace | Searches workspace |
| 54 | `When User clicks on delete icon for '{string}' workspace` | Workspace | Deletes workspace |
| 55 | `When User clicks on Confirm Delete button` | Workspace | Confirms workspace deletion |
| 56 | `When User clicks on Open Vector` | Configuration Knowledge | Opens Vector catalog |
| 57 | `When User clicks on Add Vector Database` | Configuration Knowledge | Clicks Add Vector Database |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see the response in the chat` | Chat | Validates response is displayed |
| 2 | `Then User can see Ask the AI button is enabled` | Chat | Validates Ask the AI button is enabled |
| 3 | `Then User can see Ask the AI button is disabled` | Chat | Validates Ask the AI button is disabled |
| 4 | `Then User can see sidebar is collapsed` | Chat | Validates sidebar is collapsed |
| 5 | `Then User can see sidebar is expanded` | Chat | Validates sidebar is expanded |
| 6 | `Then User can see '{string}' model selected` | Configuration Model | Validates selected model |
| 7 | `Then User can see default model is selected` | Configuration Model | Validates default model selection |
| 8 | `Then User can see '{string}' tool added` | Configuration MCP App | Validates tool is added |
| 9 | `Then User can see '{string}' tool is removed` | Configuration MCP App | Validates tool is removed |
| 10 | `Then User can see '{string}' knowledge added` | Configuration Knowledge | Validates knowledge is added |
| 11 | `Then User can see '{string}' knowledge is removed` | Configuration Knowledge | Validates knowledge is removed |
| 12 | `Then User can see Max Token value as '{string}'` | Configuration Knowledge | Validates max token value |
| 13 | `Then User can see Temperature value as '{string}'` | Configuration Knowledge | Validates temperature value |
| 14 | `Then User can see Instructions text as '{string}'` | Configuration Knowledge | Validates instructions text |
| 15 | `Then User can see workspace '{string}' is created` | Workspace | Validates workspace creation |
| 16 | `Then User can see workspace '{string}' is active` | Workspace | Validates active workspace |
| 17 | `Then User can see workspace name updated to '{string}'` | Workspace | Validates workspace name update |
| 18 | `Then User can see workspace '{string}' in search results` | Workspace | Validates workspace in search |
| 19 | `Then User can see workspace '{string}' is deleted` | Workspace | Validates workspace deletion |
| 20 | `Then User sees success toast message '{string}'` | Configuration MCP App | Validates success toast message |
| 21 | `Then User can see the Playground page` | Chat | Validates Playground page is loaded |
| 22 | `Then User can see chat history` | Chat | Validates chat history is displayed |

---

# App

> **Feature Path:** `src/test/resources/Features/app/`  
> **Step Files:** `CreateCodeAppSteps.java`, `DragAndDropAppLayersSteps.java`, and other app step files

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | All app features | User is on the home page |
| 2 | `Given User opens Main Menu` | All app features | Opens the main navigation menu |
| 3 | `Given User clicks on app Edit button` | Editor Permission | Clicks app Edit button |

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All app features | Opens the main navigation menu |
| 2 | `When User clicks on Open App Library` | All app features | Opens the App Library page |
| 3 | `When User clicks on Open Database` | Notebook DB Ops, Transformation, Templates | Opens the Database catalog |
| 4 | `When User clicks on Open Model` | NLP Query Template | Opens the Model catalog |

### Database Setup (Background)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `When User checks if 'Database' catalog created and Deletes the '{string}'` | Notebook DB Ops, Transformation, Templates | Checks if DB catalog exists and deletes it |
| 6 | `When User clicks on Add Database` | Notebook DB Ops, Transformation, Templates | Clicks Add Database button |
| 7 | `When User clicks on file upload icon` | Notebook DB Ops, Transformation, Templates | Clicks file upload icon |
| 8 | `When User uploads the file '{string}'` | Notebook DB Ops, Transformation, Templates | Uploads a file |
| 9 | `When User clicks on '{string}' button to create catalog` | Notebook DB Ops, Transformation, Templates | Clicks Upload button to create catalog |

### Model Setup (Background)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `When User clicks on Add Model` | NLP Query Template | Clicks Add Model button |

### App Creation - Drag and Drop

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 11 | `When User clicks on Create New App button` | All app features | Clicks Create New App button |
| 12 | `When User clicks on Get Started button in "{string}"` | Notebook, App Settings, Notebook DB Ops, Transformation | Clicks Get Started in Drag and Drop |
| 13 | `When User enters app name as '{string}'` | All app features | Enters app name |
| 14 | `When User clicks on Create button` | All app features | Clicks Create button |
| 15 | `When User fetch the app name` | All app features | Fetches the created app name |
| 16 | `When User enters description as '{string}'` | Most app features | Enters description |
| 17 | `When User enters tags '{string}' and presses Enter` | Most app features | Enters tags |

### App Creation - Templates

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 18 | `When User selects "{string}" from Template List` | Landing Page, Multi Page, Ask LLM, CRUD Templates, NLP Query, Variable Guide, Visualize CSV, Custom Frame | Selects template from list |

### App Navigation & Search

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `When User searches '{string}' app in the app searchbox` | App Settings, Blocks | Searches for app |
| 20 | `When User clicks on '{string}' app from the My Apps` | App Settings, Blocks | Clicks app from My Apps |
| 21 | `When User clicks on app Edit button` | Blocks | Clicks Edit button |

### App Blocks & UI Builder

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 22 | `When User clicks on Blocks if it is not selected by default` | Blocks, Data Grid | Selects Blocks option |
| 23 | `When User drags the '{string}' block and drops it on the page` | Blocks | Drags block to page |
| 24 | `When User clicks on the '{string}' block to select it` | Blocks | Selects a block |
| 25 | `When User clicks on the Block Settings option` | Blocks, App Settings | Opens Block Settings |
| 26 | `When User enters '{string}' as the destination` | Blocks | Enters destination URL |
| 27 | `When User enters '{string}' text as '{string}'` | Blocks | Enters text for block |
| 28 | `When User selects the Appearance tab` | Blocks | Selects Appearance tab |
| 29 | `When User selects the '{string}' styles` | Blocks | Selects text styles |
| 30 | `When User selects '{string}' from the font list` | Blocks | Selects font |
| 31 | `When User selects '{string}' as the HEX color value` | Blocks | Sets HEX color |
| 32 | `When User selects '{string}' as the text alignment` | Blocks | Sets text alignment |
| 33 | `When User clicks on the Save App icon` | Blocks | Saves app |
| 34 | `When User change title '{string}' with '{string}'` | Visualize CSV | Changes block title |
| 35 | `When User filles the destination URL as "{string}"` | Landing Page | Fills destination URL |
| 36 | `When User clicks on Save button of the app` | Landing Page | Clicks Save button |
| 37 | `When User clicks on hyperlink text "{string}"` | Landing Page | Clicks hyperlink text |
| 38 | `When User closes the Block Settings button` | Notebook | Closes Block Settings |

### Notebook Operations

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 39 | `When User clicks on Notebook` | Notebook DB Ops, Transformation, Scripting | Opens Notebook |
| 40 | `When User clicks on Create new notebook` | Notebook DB Ops, Transformation, Scripting | Creates new notebook |
| 41 | `When User enters New Query name as '{string}'` | Notebook DB Ops, Transformation, Scripting | Enters notebook query name |
| 42 | `When User clicks on query Submit button` | Notebook DB Ops, Transformation, Scripting | Submits query name |
| 43 | `When User deletes the notebook named '{string}'` | Notebook Operations | Deletes notebook |
| 44 | `When User duplicates the notebook named '{string}'` | Notebook Operations | Duplicates notebook |
| 45 | `When User searches notebooks for '{string}'` | Notebook Operations | Searches notebooks |

### Notebook Cells & Execution

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 46 | `When User mouse hover below the existing cell` | Notebook DB Ops, Transformation | Hovers below existing cell |
| 47 | `When User selects '{string}' from the hidden options` | Notebook DB Ops, Transformation | Selects from hidden options |
| 48 | `When User selects '{string}' from the data import options` | Notebook DB Ops | Selects data import option |
| 49 | `When User selects '{string}' from the dropdown list` | Notebook DB Ops | Selects from dropdown list |
| 50 | `When User selects all columns from database` | Notebook DB Ops | Selects all columns |
| 51 | `When User clicks on data Import button` | Notebook DB Ops | Clicks data Import button |
| 52 | `When User enters pixel code '{string}'` | Scripting | Enters pixel code |
| 53 | `When User enters python code '{string}'` | Scripting | Enters python code |
| 54 | `When User clicks on Run button` | Scripting, Notebook DB Ops | Clicks Run button |

### Notebook Data Import

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 55 | `When User enters SQL query '{string}'` | Notebook DB Ops | Enters SQL query |
| 56 | `When User clicks on apply database button` | Notebook DB Ops | Applies database |

### Notebook Transformation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 57 | `When User selects '{string}' transformation` | Transformation | Selects transformation type |
| 58 | `When User selects '{string}' column` | Transformation | Selects column for transformation |
| 59 | `When User clicks on Apply Transformation button` | Transformation | Applies transformation |

### Notebook Model Selection

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 60 | `When User selects '{string}' model for notebook` | NLP Query | Selects model for notebook |

### App Preview

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 61 | `When User clicks on Preview app button` | Ask LLM, CRUD Diabetes, NLP Query | Clicks Preview app button |
| 62 | `When User clicks on Close Preview button` | Ask LLM | Clicks Close Preview button |
| 63 | `When User close the Preview app window` | CRUD Diabetes, NLP Query | Closes Preview app window |
| 64 | `When User clicks on '{string}' page` | NLP Query | Clicks on a specific page |

### App Preview - Template Interactions

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 65 | `When User see the '{string}' block` | Multi Page | Validates block is visible |
| 66 | `When User see the '{string}' hyperlink` | Multi Page | Validates hyperlink is visible |
| 67 | `When User click on the '{string}' hyperlink should point to '{string}'` | Multi Page | Clicks hyperlink and validates URL |
| 68 | `When User see the '{string}' title after clicking resources hyperlink` | Multi Page | Validates title after navigation |

### App Settings

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 69 | `When User click on Settings` | App Settings | Opens Settings |
| 70 | `When User clicks on '{string}' Card` | App Settings | Clicks settings card |

### User Login/Logout

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 71 | `When User logs in as '{string}'` | App Settings (Author/Editor/Read) | Logs in as specified user |

### Page Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 72 | `When User navigates to back page` | Landing Page | Navigates browser back |
| 73 | `When User clicks on the hyperlink with text "{string}" with title '{string}' should point to "{string}"` | Landing Page | Clicks hyperlink and validates URL |

### Variable Guide

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 74 | `When User clicks on '{string}' variable guide block` | Variable Guide | Clicks variable guide block |

## Then Steps

### App Creation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see '{string}' with the text '{string}'` | All Drag and Drop features | Validates page with welcome text |
| 2 | `Then User can see '{string}' app on the page` | App Landing Page | Validates app is visible |
| 3 | `Then User can see the following details on the app card` | App Landing Page | Validates app card details using data table |

### App Block Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `Then User can see 'Hello world' on the page` | Blocks | Validates block text on page |
| 5 | `Then User should see the '{string}' text as '{string}'` | Blocks | Validates block text content |
| 6 | `Then User sees title of the block as '{string}'` | Landing Page | Validates block title |

### App Template Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 7 | `Then User sees description as '{string}'` | Ask LLM | Validates description text |
| 8 | `Then User views description as '{string}'` | Custom Frame, Landing Page, NLP Query, Variable Guide | Validates description text |
| 9 | `Then User views description for the block with title '{string}' as '{string}'` | Landing Page | Validates description for specific block title |
| 10 | `Then User sees input field with With label '{string}'` | Ask LLM | Validates input field label |
| 11 | `Then User sees submit button` | Ask LLM | Validates submit button visible |
| 12 | `Then User sees the title '{string}' in Preview App` | Ask LLM | Validates title in Preview mode |

### Notebook Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 13 | `Then User sees the table in the metadata tab` | Notebook DB Ops | Validates table in metadata tab |
| 14 | `Then User sees the output cell with data` | Scripting | Validates output cell |
| 15 | `Then User sees the notebook '{string}' is created` | Notebook Operations | Validates notebook creation |
| 16 | `Then User sees the notebook '{string}' is duplicated` | Notebook Operations | Validates notebook duplication |
| 17 | `Then User sees the notebook '{string}' is deleted` | Notebook Operations | Validates notebook deletion |

### Transformation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 18 | `Then User sees the transformation applied` | Transformation | Validates transformation applied |

### App Settings Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `Then '{string}' user can '{string}' Settings` | App Settings | Validates user permission for Settings |
| 20 | `Then User see the '{string}' as title of the '{string}' option` | App Settings | Validates settings option title |
| 21 | `Then '{string}' user can '{string}' Members` | App Settings | Validates user permission for Members |
| 22 | `Then User sees success toast message '{string}'` | Various | Validates success toast message |

---

# Model

> **Feature Path:** `src/test/resources/Features/model/`  
> **Step Files:** Model-related step files

## Given Steps

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 1 | `Given User opens Main Menu` | All model features | Opens the main navigation menu |
| 2 | `Given User is on Home page` | Model Settings | User is on the home page |
| 3 | `Given User can see the Model title as '{string}'` | Add Model, Model Permissions, Model Chat | Validates the model title is displayed |
| 4 | `Given User can see the Model title as 'Model'` | Add Model | Validates model title as 'Model' |
| 5 | `Given User can see the Model title as 'Catalog'` | Editor/Read Permission | Validates model title as 'Catalog' |

## When Steps

### Navigation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 1 | `When User opens Main Menu` | All model features | Opens the main navigation menu |
| 2 | `When User clicks on Open Model` | All model features | Opens the model catalog page |
| 3 | `When User clicks on Add Model` | All model features | Clicks the Add Model button |

### Model Creation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 4 | `When User selects '{string}' model type` | Add All Model Types, Add Model | Selects model type |
| 5 | `When User selects '{string}' model variant` | Add All Model Types, Add Model | Selects model variant |
| 6 | `When User enters Model name '{string}'` | Add All Model Types, Add Model | Enters model catalog name |
| 7 | `When User enters API key as '{string}'` | Add All Model Types, Add Model | Enters API key |
| 8 | `When User clicks on Create Model button` | Add All Model Types, Add Model | Clicks Create Model button |
| 9 | `When User clicks on file upload icon` | Add Model | Clicks file upload icon |
| 10 | `When User uploads the file '{string}'` | Add Model | Uploads model file |
| 11 | `When User clicks on '{string}' button to create catalog` | Add Model | Clicks Upload button |

### SMSS Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 12 | `When User clicks on SMSS tab` | Add Model, Model Permissions | Clicks SMSS tab |
| 13 | `When User validates SMSS properties` | Add Model | Validates SMSS properties |
| 14 | `When User edits SMSS property '{string}' to '{string}'` | Add Model, Model Permissions | Edits SMSS property |

### Catalog Management

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 15 | `When User clicks on Copy Catalog ID` | Add Model | Copies catalog ID |
| 16 | `When User adds tag '{string}'` | Add Model | Adds tag to model |
| 17 | `When User clicks on Overview tab` | Add Model, Model Permissions | Clicks Overview tab |
| 18 | `When User clicks on Usage tab` | Add Model | Clicks Usage tab |
| 19 | `When User clicks on Access Control Tab` | Model Permissions | Clicks Access Control tab |

### Chat

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 20 | `When User clicks on Chat tab` | Model Chat | Clicks Chat tab |
| 21 | `When User enters '{string}' in chat input` | Model Chat | Enters chat message |
| 22 | `When User clicks on Send Chat button` | Model Chat | Sends chat message |
| 23 | `When User clicks on Clear Chat button` | Model Chat | Clears chat history |

### Model Settings

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 24 | `When User clicks on Model Settings` | Model Settings | Opens Model Settings |
| 25 | `When User clicks on Private toggle` | Model Settings, Model Permissions | Toggles private setting |
| 26 | `When User clicks on Non Discoverable toggle` | Model Settings | Toggles non-discoverable setting |
| 27 | `When User clicks on Delete button` | Model Settings, Model Permissions | Clicks delete button |

### Model Permissions

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 28 | `When User clicks on Add Member button` | Model Permissions | Clicks Add Member button |
| 29 | `When User enters member '{string}'` | Model Permissions | Enters member name |
| 30 | `When User selects permission '{string}'` | Model Permissions | Selects permission level |
| 31 | `When User clicks on Save Member button` | Model Permissions | Saves member |
| 32 | `When User clicks on Delete Member button for '{string}'` | Model Permissions | Deletes member |

### Model Filtering

| # | Step Definition | Feature File | Description |
|---|----------------|-----------------|-------------|
| 33 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | All model features | Checks and deletes catalog |
| 34 | `When User clicks on My Models filter` | Model Permissions | Clicks My Models filter |
| 35 | `When User clicks on Discoverable Models filter` | Model Permissions | Clicks Discoverable filter |
| 36 | `When User searches '{string}' in model search` | Model Permissions | Searches for model |
| 37 | `When User clicks on '{string}' model from catalog` | Model Permissions | Clicks model from catalog |
| 38 | `When User logs in as '{string}'` | Model Permissions | Logs in as specified user |
| 39 | `When User clicks on Make '{string}' Discoverable button` | Model Permissions | Makes model discoverable |
| 40 | `When User edits model details` | Add Model | Edits model details |
| 41 | `When User enters Model Description as '{string}'` | Add Model | Enters model description |
| 42 | `When User clicks on Edit Details Save button` | Add Model | Saves edited details |

### Model Form Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 43 | `When User can see '{string}' sections in the form` | Add All Model Types | Validates form sections |
| 44 | `When User can see '{string}' fields in section '{string}'` | Add All Model Types | Validates fields in section |
| 45 | `When User can see '{string}' mandatory fields` | Add All Model Types | Validates mandatory fields |
| 46 | `When User can see Connect button is '{string}'` | Add All Model Types | Validates Connect button state |
| 47 | `When User clicks on Bookmark icon` | Add Model | Clicks Bookmark icon |
| 48 | `When User clicks on Unbookmark icon` | Add Model | Clicks Unbookmark icon |

## Then Steps

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 1 | `Then User can see the model '{string}' in the catalog` | Add Model | Validates model in catalog |
| 2 | `Then User can see SMSS property '{string}' with value '{string}'` | Add Model | Validates SMSS property |
| 3 | `Then User can see success toast message as '{string}'` | Add Model | Validates success toast |
| 4 | `Then User can see the model overview` | Add Model, Model Permissions | Validates model overview |
| 5 | `Then User can see Usage examples` | Add Model | Validates usage examples |
| 6 | `Then User can see tag '{string}' on the model` | Add Model | Validates tag |
| 7 | `Then User can see chat response` | Model Chat | Validates chat response |
| 8 | `Then User can see chat is cleared` | Model Chat | Validates chat cleared |
| 9 | `Then User can see Private toggle is '{string}'` | Model Settings | Validates toggle state |
| 10 | `Then User can see Non Discoverable toggle is '{string}'` | Model Settings | Validates toggle state |
| 11 | `Then User can see Delete button` | Model Settings | Validates delete button visible |
| 12 | `Then User can see Pending Requests section` | Model Settings | Validates section visible |
| 13 | `Then User can see Permissions section` | Model Settings | Validates section visible |
| 14 | `Then User can see member '{string}' with permission '{string}'` | Model Permissions | Validates member permission |
| 15 | `Then User can see member '{string}' is removed` | Model Permissions | Validates member removed |
| 16 | `Then User can see Access Control tab` | Model Permissions | Validates tab visible |
| 17 | `Then User can see SMSS tab` | Model Permissions | Validates tab visible |
| 18 | `Then User cannot see SMSS tab` | Model Permissions (Editor) | Validates tab not visible |
| 19 | `Then User can see the model is deleted` | Model Permissions | Validates model deleted |
| 20 | `Then User can see the model '{string}' in My Models` | Model Permissions | Validates model in My Models |
| 21 | `Then User can see the model '{string}' in Discoverable Models` | Model Permissions | Validates model is discoverable |
| 22 | `Then User can see the model title as '{string}'` | Model Permissions | Validates model title |
| 23 | `Then User can see catalog ID` | Add Model | Validates catalog ID visible |
| 24 | `Then User can see Model Description as '{string}'` | Add Model | Validates model description |
| 25 | `Then User can see the model is bookmarked` | Add Model | Validates bookmark |
| 26 | `Then User can see the model is unbookmarked` | Add Model | Validates unbookmark |
| 27-46 | *(Additional model validation steps for form fields, SMSS properties, permissions)* | Various | Various validation steps |

---

# Vector

> **Feature Path:** `src/test/resources/Features/vector/`  
> **Step Files:** `VectorDatabaseSteps.java`, `VectorSettingsSteps.java`

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | All vector features | Opens the main navigation menu |
| 2 | `Given User can see the Vector title as '{string}'` | Add Vector DB, Vector Overview | Validates vector title is displayed |

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All vector features | Opens the main navigation menu |
| 2 | `When User clicks on Open Vector` | All vector features | Opens the Vector catalog page |
| 3 | `When User clicks on Open Model` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Opens the Model catalog page |

### Model Setup (Background)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `When User clicks on Add Model` | Add Vector DB | Clicks Add Model button |
| 5 | `When User clicks on file upload icon` | Add Vector DB, Verify All Vector Types | Clicks file upload icon |
| 6 | `When User uploads the file '{string}'` | Add Vector DB, Verify All Vector Types | Uploads a file |
| 7 | `When User clicks on '{string}' button to create catalog` | Add Vector DB | Clicks Upload button |
| 8 | `When User clicks on Copy Catalog ID` | Add Vector DB | Copies catalog ID |

### Vector Creation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 9 | `When User clicks on Add Vector Database` | Add Vector DB, Verify All Vector Types | Clicks Add Vector Database button |
| 10 | `When User selects '{string}' vector connection` | Add Vector DB, Verify All Vector Types | Selects vector connection type |
| 11 | `When User enters Vector name '{string}'` | Add Vector DB | Enters vector catalog name |
| 12 | `When User selects '{string}' embedder` | Add Vector DB | Selects embedder model |
| 13 | `When User selects '{string}' chunking strategy` | Add Vector DB | Selects chunking strategy |
| 14 | `When User enters content length '{string}'` | Add Vector DB | Enters content length |
| 15 | `When User enters content overlap '{string}'` | Add Vector DB | Enters content overlap |
| 16 | `When User clicks on Create Vector button` | Add Vector DB | Clicks Create Vector button |
| 17 | `When User uploads document '{string}'` | Add Vector DB | Uploads document to vector |

### Vector Validation & Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 18 | `When User clicks on SMSS tab` | Add Vector DB | Clicks SMSS tab |
| 19 | `When User clicks on Overview tab` | Vector Overview | Clicks Overview tab |
| 20 | `When User clicks on Usage tab` | Add Vector DB | Clicks Usage tab |
| 21 | `When User adds tag '{string}'` | Vector Overview | Adds tag |
| 22 | `When User clicks on Access Control Tab` | Vector Overview, View Existing Vectors | Clicks Access Control tab |

### Q&A Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 23 | `When User clicks on Q&A tab` | Vector Q&A | Clicks Q&A tab |
| 24 | `When User clicks on Select Model dropdown` | Vector Q&A | Opens model dropdown |
| 25 | `When User selects '{string}' model` | Vector Q&A | Selects model |
| 26 | `When User enters question '{string}'` | Vector Q&A | Enters question |
| 27 | `When User clicks on Generate Answer button` | Vector Q&A | Generates answer |
| 28 | `When User adjusts slider to '{string}'` | Vector Q&A | Adjusts slider |

### Vector Filtering & Catalog

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 29 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | All vector features | Checks and deletes catalog |
| 30 | `When User clicks on My Vectors filter` | View Existing Vectors | Clicks My Vectors filter |
| 31 | `When User clicks on Discoverable Vectors filter` | View Existing Vectors | Clicks Discoverable filter |
| 32 | `When User searches '{string}' in vector search` | View Existing Vectors | Searches for vector |
| 33 | `When User clicks on '{string}' vector from catalog` | View Existing Vectors | Clicks vector |
| 34 | `When User clicks on Make '{string}' Discoverable button` | View Existing Vectors | Makes vector discoverable |
| 35 | `When User edits tags as '{string}'` | View Existing Vectors | Edits tags |

### Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 36 | `When User can see '{string}' sections in the form` | Verify All Vector Types | Validates form sections |
| 37 | `When User can see '{string}' fields in section '{string}'` | Verify All Vector Types | Validates fields |
| 38 | `When User can see '{string}' mandatory fields` | Verify All Vector Types | Validates mandatory fields |
| 39 | `When User can see Connect button is '{string}'` | Verify All Vector Types | Validates button state |
| 40 | `When User clicks on Bookmark icon` | View Existing Vectors | Clicks Bookmark |
| 41 | `When User clicks on Unbookmark icon` | View Existing Vectors | Clicks Unbookmark |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see the vector '{string}' in the catalog` | Add Vector DB | Validates vector in catalog |
| 2 | `Then User can see SMSS property '{string}' with value '{string}'` | Add Vector DB | Validates SMSS property |
| 3 | `Then User can see success toast message as '{string}'` | Add Vector DB | Validates success toast |
| 4 | `Then User can see Usage examples` | Add Vector DB | Validates usage examples |
| 5 | `Then User can see the vector overview` | Vector Overview | Validates overview |
| 6 | `Then User can see vector ID` | Vector Overview | Validates vector ID |
| 7 | `Then User can see tag '{string}' on the vector` | Vector Overview | Validates tag |
| 8 | `Then User can see Change Access button` | Vector Overview | Validates button |
| 9 | `Then User can see Q&A tab elements` | Vector Q&A | Validates Q&A elements |
| 10 | `Then User can see Select Model dropdown` | Vector Q&A | Validates dropdown |
| 11 | `Then User can see the generated answer` | Vector Q&A | Validates answer |
| 12 | `Then User can see slider value as '{string}'` | Vector Q&A | Validates slider |
| 13 | `Then User can see the vector '{string}' in My Vectors` | View Existing Vectors | Validates in My Vectors |
| 14 | `Then User can see the vector '{string}' in Discoverable Vectors` | View Existing Vectors | Validates in Discoverable |
| 15 | `Then User can see the vector is bookmarked` | View Existing Vectors | Validates bookmark |
| 16 | `Then User can see the vector is unbookmarked` | View Existing Vectors | Validates unbookmark |

---

# Database

> **Feature Path:** `src/test/resources/Features/database/`  
> **Step Files:** Database-related step files

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | All database features | Opens the main navigation menu |
| 2 | `Given User is on Home page` | All database features | User is on the home page |
| 3 | `Given User can see the Database title as '{string}'` | Add Database | Validates database title |
| 4 | `Given User can see database created success toast message as '{string}'` | Add CSV/TSV/Excel/H2/SQLite Database | Validates creation toast |
| 5 | `Given User clicks on Open Database` | All database features | Opens Database catalog |

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All database features | Opens main menu |
| 2 | `When User clicks on Open Database` | All database features | Opens Database catalog |
| 3 | `When User clicks on Add Database` | All database features | Clicks Add Database button |

### Database Creation - File Upload

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `When User clicks on file upload icon` | Add CSV/TSV/Excel/ZIP Database | Clicks file upload icon |
| 5 | `When User uploads the file '{string}'` | Add CSV/TSV/Excel/ZIP Database | Uploads file |
| 6 | `When User clicks on '{string}' button to create catalog` | Add CSV/TSV/Excel/ZIP Database | Clicks Upload button |
| 7 | `When User enters Database name '{string}'` | Add CSV/TSV/Excel/H2/SQLite Database | Enters database name |
| 8 | `When User selects '{string}' metamodel` | Add CSV/TSV/Excel Database | Selects metamodel |
| 9 | `When User clicks on Create Database button` | Add CSV/TSV/Excel/H2/SQLite Database | Clicks Create button |

### Database Creation - Connection

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `When User selects '{string}' database type` | Add H2/SQLite, Verify All Types | Selects database type |
| 11 | `When User enters hostname as '{string}'` | Add H2/SQLite | Enters hostname |
| 12 | `When User enters port as '{string}'` | Add H2/SQLite | Enters port |
| 13 | `When User enters database as '{string}'` | Add H2/SQLite | Enters database name |
| 14 | `When User enters username as '{string}'` | Add H2/SQLite | Enters username |
| 15 | `When User enters password as '{string}'` | Add H2/SQLite | Enters password |

### Metadata Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 16 | `When User clicks on Metadata tab` | Add ZIP Database | Clicks Metadata tab |
| 17 | `When User clicks on Save Metadata button` | Add ZIP Database | Saves metadata |
| 18 | `When User enters metadata description as '{string}'` | Add ZIP Database | Enters metadata description |

### SMSS & Overview

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `When User clicks on SMSS tab` | Add ZIP Database | Clicks SMSS tab |
| 20 | `When User clicks on Overview tab` | Add ZIP Database | Clicks Overview tab |
| 21 | `When User clicks on Usage tab` | Add ZIP Database | Clicks Usage tab |
| 22 | `When User adds tag '{string}'` | Add ZIP Database | Adds tag |

### Query Functionality

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 23 | `When User clicks on Query tab` | Validate Query | Clicks Query tab |
| 24 | `When User enters query '{string}'` | Validate Query | Enters query |
| 25 | `When User clicks on Run Query button` | Validate Query | Runs query |
| 26 | `When User clicks on Reset button` | Validate Query | Resets query |
| 27 | `When User clicks on Collapse button` | Validate Query | Collapses results |
| 28 | `When User clicks on Expand button` | Validate Query | Expands results |
| 29 | `When User searches '{string}' in columns` | Validate Query | Searches columns |
| 30 | `When User clicks on Refresh Structure button` | Validate Query | Refreshes structure |

### Database Filtering & Catalog

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 31 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | All database features | Checks and deletes catalog |
| 32 | `When User clicks on My Databases filter` | View Existing Databases | Clicks My Databases filter |
| 33 | `When User searches '{string}' in database search` | View Existing Databases | Searches database |
| 34 | `When User clicks on '{string}' database from catalog` | View Existing Databases | Clicks database |
| 35 | `When User edits tags as '{string}'` | View Existing Databases | Edits tags |
| 36 | `When User edits domains as '{string}'` | View Existing Databases | Edits domains |
| 37 | `When User edits data classification as '{string}'` | View Existing Databases | Edits classification |
| 38 | `When User edits data restrictions as '{string}'` | View Existing Databases | Edits restrictions |
| 39 | `When User clicks on Bookmark icon` | View Existing Databases | Clicks Bookmark |
| 40 | `When User clicks on Unbookmark icon` | View Existing Databases | Clicks Unbookmark |

### Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 41 | `When User can see '{string}' sections in the form` | Verify All Types | Validates form sections |
| 42 | `When User can see '{string}' fields in section '{string}'` | Verify All Types | Validates fields |
| 43 | `When User can see '{string}' mandatory fields` | Verify All Types | Validates mandatory fields |
| 44 | `When User can see Connect button is '{string}'` | Verify All Types | Validates button state |

### Database Relationship

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 45 | `When User creates relationship between tables` | Add CSV Database | Creates relationship |
| 46 | `When User selects multiple files` | Add CSV Database | Selects multiple files |

### View Options

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 47 | `When User searches '{string}' in database types search` | View Add Database Options | Searches database types |
| 48 | `When User can see '{string}' database options with icons` | View Add Database Options | Validates options with icons |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see the database '{string}' in the catalog` | Add Database | Validates database in catalog |
| 2 | `Then User can see success toast message as '{string}'` | Add Database | Validates success toast |
| 3 | `Then User can see SMSS property '{string}' with value '{string}'` | Add ZIP Database | Validates SMSS property |
| 4 | `Then User can see Usage examples` | Add ZIP Database | Validates usage examples |
| 5 | `Then User can see the database overview` | Add ZIP Database | Validates overview |
| 6 | `Then User can see tag '{string}' on the database` | Add ZIP Database | Validates tag |
| 7 | `Then User can see metadata description as '{string}'` | Add ZIP Database | Validates metadata |
| 8 | `Then User can see query results` | Validate Query | Validates query results |
| 9 | `Then User can see query results are reset` | Validate Query | Validates reset |
| 10 | `Then User can see results are collapsed` | Validate Query | Validates collapse |
| 11 | `Then User can see results are expanded` | Validate Query | Validates expand |
| 12 | `Then User can see '{string}' columns in results` | Validate Query | Validates columns |
| 13 | `Then User can see column '{string}' in search results` | Validate Query | Validates column search |
| 14 | `Then User can see structure is refreshed` | Validate Query | Validates refresh |
| 15 | `Then User can see the database '{string}' in My Databases` | View Existing Databases | Validates filter |
| 16 | `Then User can see the database is bookmarked` | View Existing Databases | Validates bookmark |
| 17 | `Then User can see the database is unbookmarked` | View Existing Databases | Validates unbookmark |
| 18 | `Then User can see database created success toast message as '{string}'` | Add CSV/TSV/Excel Database | Validates creation toast |

---

# Function

> **Feature Path:** `src/test/resources/Features/function/`  
> **Step Files:** `AddFunctionSteps.java`, `ViewFunctionSteps.java`

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | All function features | Opens the main navigation menu |
| 2 | `Given User can see the Catalog title as '{string}'` | Add Function From Zip, View Function Details | Validates catalog title is displayed |

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All function features | Opens the main navigation menu |
| 2 | `When User clicks on Open Function` | All function features | Opens the Function catalog page |

### Function Creation - General

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `When User clicks on Add Function` | Validate All Function Types, Add Function, Add Function From Zip, View Existing Functions | Clicks Add Function button |
| 4 | `When User clicks on Add Function button` | View Add Function Page | Clicks Add Function button (alternate) |
| 5 | `When User selects function '{string}'` | Validate All Function Types, Add Function | Selects function type |

### Function Creation - REST

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 6 | `When User enters Catalog name '{string}'` | Add Function | Enters catalog name |
| 7 | `When User enters Url as '{string}'` | Add Function | Enters function URL |
| 8 | `When User selects HTTP method as '{string}'` | Add Function | Selects HTTP method |
| 9 | `When User selects Post body message as '{string}'` | Add Function | Selects post body |
| 10 | `When User enters Function parameters as '{string}'` | Add Function | Enters function parameters |
| 11 | `When User enters Function required parameters as '{string}'` | Add Function | Enters required parameters |
| 12 | `When User enters Function description as '{string}'` | Add Function | Enters function description |
| 13 | `When User clicks on Create Function button` | Add Function | Clicks Create Function |

### Function Creation - ZIP Upload

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 14 | `When User clicks on file upload icon` | Add Function From Zip, View Existing Functions | Clicks file upload icon |
| 15 | `When User uploads the file '{string}'` | Add Function From Zip, View Existing Functions | Uploads file |
| 16 | `When User clicks on '{string}' button to create catalog` | Add Function From Zip | Clicks Upload button |

### Function Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `When User clicks on SMSS tab` | Add Function | Clicks SMSS tab |
| 18 | `When User clicks on Overview tab` | View Function Details | Clicks Overview tab |
| 19 | `When User clicks on Usage tab` | Add Function | Clicks Usage tab |
| 20 | `When User adds tag '{string}'` | Add Function | Adds tag |
| 21 | `When User clicks on Copy Catalog ID` | Add Function | Copies catalog ID |

### Function Filtering & Catalog

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 22 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | All function features | Checks and deletes catalog |
| 23 | `When User clicks on My Functions filter` | View Existing Functions | Clicks My Functions filter |
| 24 | `When User clicks on Discoverable Functions filter` | View Existing Functions | Clicks Discoverable filter |
| 25 | `When User searches '{string}' in function search` | View Existing Functions | Searches function |
| 26 | `When User clicks on '{string}' function from catalog` | View Existing Functions | Clicks function |
| 27 | `When User clicks on Make '{string}' Discoverable button` | View Existing Functions | Makes function discoverable |
| 28 | `When User edits tags as '{string}'` | View Existing Functions | Edits tags |
| 29 | `When User edits domains as '{string}'` | View Existing Functions | Edits domains |
| 30 | `When User edits data classification as '{string}'` | View Existing Functions | Edits classification |
| 31 | `When User edits data restrictions as '{string}'` | View Existing Functions | Edits restrictions |

### Function Usage Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 32 | `When User selects 'Usage' tab` | View Function Details | Clicks Usage tab |
| 33 | `When User can see '{string}' usage instructions section` | View Function Details | Validates usage instructions |

### Function Access Control

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 34 | `When User clicks on Access Control Tab` | Add Function From Zip, View Existing Functions | Clicks Access Control tab |
| 35 | `When User clicks on Delete button` | Add Function From Zip | Clicks Delete button |

### Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 36 | `When User can see '{string}' sections in the form` | Validate All Function Types | Validates form sections |
| 37 | `When User can see '{string}' fields in section '{string}'` | Validate All Function Types | Validates fields |
| 38 | `When User can see '{string}' mandatory fields` | Validate All Function Types | Validates mandatory fields |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see the function '{string}' in the catalog` | Add Function, Add Function From Zip | Validates function in catalog |
| 2 | `Then User can see success toast message as '{string}'` | Add Function | Validates success toast |
| 3 | `Then User can see SMSS property '{string}' with value '{string}'` | Add Function | Validates SMSS property |
| 4 | `Then User can see Usage examples` | Add Function | Validates usage examples |
| 5 | `Then User can see tag '{string}' on the function` | Add Function | Validates tag |
| 6 | `Then User can see catalog ID` | Add Function | Validates catalog ID |
| 7 | `Then User can see the function '{string}' in My Functions` | View Existing Functions | Validates in My Functions |
| 8 | `Then User can see the function '{string}' in Discoverable Functions` | View Existing Functions | Validates in Discoverable |
| 9 | `Then User can see '{string}' Markup with Function overview in Overview tab at the bottom of the page.` | View Function Details | Validates markup overview |
| 10 | `Then User sees '{string}' button` | View Function Details | Validates button visible |
| 11 | `Then User selects '{string}' tab` | View Function Details | Clicks tab |
| 12 | `Then User can see '{string}' usage instructions section` | View Function Details | Validates usage instructions |
| 13 | `Then User can see the function is deleted` | Add Function From Zip | Validates deletion |
| 14 | `Then User can see '{string}' function options with icons` | View Add Function Page | Validates function options |
| 15 | `Then User can see Create button is '{string}'` | Add Function | Validates button state |

---

# Storage

> **Feature Path:** `src/test/resources/Features/storage/`  
> **Step Files:** Storage-related step files

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | All storage features | Opens main menu |
| 2 | `Given User is on Home page` | All storage features | User is on home page |
| 3 | `Given User can see the Storage title as '{string}'` | Add Storage | Validates storage title |

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All storage features | Opens the main navigation menu |
| 2 | `When User clicks on Open Storage` | All storage features | Opens the Storage catalog page |

### Storage Creation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `When User clicks on Add Storage` | All storage features | Clicks Add Storage button |
| 4 | `When User selects '{string}' storage type` | All storage features | Selects storage type |
| 5 | `When User enters Storage name '{string}'` | Add Storage | Enters storage name |
| 6 | `When User enters Access Key as '{string}'` | Add Storage (Amazon S3) | Enters access key |
| 7 | `When User enters Secret Key as '{string}'` | Add Storage (Amazon S3) | Enters secret key |
| 8 | `When User enters Bucket Name as '{string}'` | Add Storage (Amazon S3) | Enters bucket name |
| 9 | `When User enters Region as '{string}'` | Add Storage (Amazon S3) | Enters region |
| 10 | `When User clicks on Create Storage button` | Add Storage | Clicks Create Storage |
| 11 | `When User enters Local Path as '{string}'` | Add Local File System Storage | Enters local path |

### Storage Validation & Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 12 | `When User clicks on SMSS tab` | Add Storage (Amazon S3) | Clicks SMSS tab |
| 13 | `When User clicks on Overview tab` | Add Storage (Amazon S3) | Clicks Overview tab |
| 14 | `When User clicks on Usage tab` | Add Storage (Amazon S3) | Clicks Usage tab |
| 15 | `When User adds tag '{string}'` | Add Storage (Amazon S3) | Adds tag |
| 16 | `When User clicks on Access Control Tab` | Add Storage (Amazon S3) | Clicks Access Control tab |

### Storage Filtering & Catalog

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | All storage features | Checks and deletes catalog |
| 18 | `When User clicks on My Storages filter` | View Existing Storages | Clicks My Storages filter |
| 19 | `When User clicks on Discoverable Storages filter` | View Existing Storages | Clicks Discoverable filter |
| 20 | `When User searches '{string}' in storage search` | View Existing Storages | Searches storage |
| 21 | `When User clicks on '{string}' storage from catalog` | View Existing Storages | Clicks storage |
| 22 | `When User clicks on Make '{string}' Discoverable button` | View Existing Storages | Makes discoverable |
| 23 | `When User edits tags as '{string}'` | View Existing Storages | Edits tags |
| 24 | `When User clicks on Bookmark icon` | View Existing Storages | Clicks bookmark |
| 25 | `When User clicks on Unbookmark icon` | View Existing Storages | Clicks unbookmark |
| 26 | `When User clicks on Delete button` | Add Local File System, Add Storage (Amazon S3) | Clicks Delete |

### Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 27 | `When User can see '{string}' sections in the form` | Validate All Storage Types | Validates form sections |
| 28 | `When User can see '{string}' fields in section '{string}'` | Validate All Storage Types | Validates fields |
| 29 | `When User can see '{string}' mandatory fields` | Validate All Storage Types | Validates mandatory fields |
| 30 | `When User can see Connect button is '{string}'` | Validate All Storage Types | Validates button state |

### View Options

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 31 | `When User can see '{string}' storage options with icons` | View Add Storage Page | Validates storage options |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see the storage '{string}' in the catalog` | Add Storage | Validates storage in catalog |
| 2 | `Then User can see success toast message as '{string}'` | Add Storage | Validates success toast |
| 3 | `Then User can see SMSS property '{string}' with value '{string}'` | Add Storage (Amazon S3) | Validates SMSS property |
| 4 | `Then User can see Usage examples` | Add Storage (Amazon S3) | Validates usage examples |
| 5 | `Then User can see the storage overview` | Add Storage (Amazon S3) | Validates overview |
| 6 | `Then User can see storage ID` | Add Storage (Amazon S3) | Validates storage ID |
| 7 | `Then User can see tag '{string}' on the storage` | Add Storage (Amazon S3) | Validates tag |
| 8 | `Then User can see Change Access button` | Add Storage (Amazon S3) | Validates button |
| 9 | `Then User can see the storage '{string}' in My Storages` | View Existing Storages | Validates filter |
| 10 | `Then User can see the storage '{string}' in Discoverable Storages` | View Existing Storages | Validates filter |
| 11 | `Then User can see the storage is bookmarked` | View Existing Storages | Validates bookmark |
| 12 | `Then User can see the storage is unbookmarked` | View Existing Storages | Validates unbookmark |
| 13 | `Then User can see the storage is deleted` | Add Local File System, Add Storage (Amazon S3) | Validates deletion |

---

# Guardrail

> **Feature Path:** `src/test/resources/Features/guardrail/`  
> **Step Files:** `AddGuardrailSteps.java`

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | Add Guardrail, Validate Guardrail | Opens the main navigation menu |
| 2 | `Given User can see the Guardrail Catalog title as '{string}'` | Validate Guardrail | Validates guardrail catalog title |

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | Add Guardrail, Validate Guardrail | Opens the main navigation menu |
| 2 | `When User clicks on Guardrail` | Add Guardrail, Validate Guardrail | Opens the Guardrail catalog page |

### Guardrail Creation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `When User clicks on Add Guardrail` | Add Guardrail | Clicks Add Guardrail button |
| 4 | `When User enters Guardrail name '{string}'` | Add Guardrail | Enters guardrail name |
| 5 | `When User selects Guardrail type '{string}'` | Add Guardrail | Selects guardrail type |
| 6 | `When User enters Guardrail description '{string}'` | Add Guardrail | Enters description |
| 7 | `When User clicks on Create Guardrail button` | Add Guardrail | Clicks Create |
| 8 | `When User clicks on file upload icon` | Add Guardrail | Clicks file upload icon |
| 9 | `When User uploads the file '{string}'` | Add Guardrail | Uploads file |
| 10 | `When User clicks on '{string}' button to create catalog` | Add Guardrail | Clicks Upload button |

### Guardrail Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 11 | `When User clicks on Overview tab` | Validate Guardrail | Clicks Overview tab |
| 12 | `When User edits guardrail details` | Validate Guardrail | Edits details |
| 13 | `When User edits description as '{string}'` | Validate Guardrail | Edits description |
| 14 | `When User edits tags as '{string}'` | Validate Guardrail | Edits tags |
| 15 | `When User edits domains as '{string}'` | Validate Guardrail | Edits domains |
| 16 | `When User edits data classification as '{string}'` | Validate Guardrail | Edits classification |
| 17 | `When User edits data restrictions as '{string}'` | Validate Guardrail | Edits restrictions |
| 18 | `When User clicks on Export button` | Validate Guardrail | Exports guardrail |

### Guardrail Filtering

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | Add Guardrail, Validate Guardrail | Checks and deletes catalog |
| 20 | `When User searches '{string}' in guardrail search` | Validate Guardrail | Searches guardrail |
| 21 | `When User clicks on '{string}' guardrail from catalog` | Validate Guardrail | Clicks guardrail |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see the guardrail '{string}' in the catalog` | Add Guardrail | Validates in catalog |
| 2 | `Then User can see success toast message as '{string}'` | Add Guardrail | Validates success toast |
| 3 | `Then User can see the guardrail overview` | Validate Guardrail | Validates overview |
| 4 | `Then User can see guardrail description as '{string}'` | Validate Guardrail | Validates description |
| 5 | `Then User can see tag '{string}' on the guardrail` | Validate Guardrail | Validates tag |
| 6 | `Then User can see domain '{string}' on the guardrail` | Validate Guardrail | Validates domain |
| 7 | `Then User can see data classification '{string}' on the guardrail` | Validate Guardrail | Validates classification |
| 8 | `Then User can see data restrictions '{string}' on the guardrail` | Validate Guardrail | Validates restrictions |
| 9 | `Then User can see the guardrail is exported` | Validate Guardrail | Validates export |
| 10 | `Then User can see Guardrail Catalog title as '{string}'` | Validate Guardrail | Validates title |
| 11 | `Then User can see guardrail name '{string}'` | Validate Guardrail | Validates name |
| 12 | `Then User can see guardrail type '{string}'` | Validate Guardrail | Validates type |

---

# Settings

> **Feature Path:** `src/test/resources/Features/settings/`  
> **Step Files:** `VectorSettingsSteps.java`, and other settings step files

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | Admin Query, Admin Settings, Job Management, Privacy Center, My Profile, User Permissions, User Management, Vector Settings, Settings Jobs | Opens the main navigation menu |
| 2 | `Given User clicks on Open Settings` | Admin Settings | Opens the Settings page from Main Menu |
| 3 | `Given User enables admin mode` | Admin Settings | Enables admin mode toggle |
| 4 | `Given User created '{int}' models with the '{string}' model '{string}', catalog name '{string}', OpenAI key '{string}'` | Admin Query | Creates models as prerequisite |
| 5 | `Given User created '{int}' jobs with the job name '{string}', Pixel '{string}'` | Admin Query | Creates jobs as prerequisite |
| 6 | `Given User is on Home page` | My Profile, User Permissions | User is on home page |
| 7 | `Given User clicks on app Edit button` | User Permissions | Clicks app Edit button |
| 8 | `Given User can see the Model title as '{string}'` | User Permissions | Validates model title |
| 9 | `Given User can see the Vector title as '{string}'` | User Permissions | Validates vector title |

## When Steps

### Navigation & Settings Access

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All settings features | Opens main menu |
| 2 | `When User clicks on Open Settings` | All settings features | Opens Settings |
| 3 | `When User enables admin mode` | Admin Query, Admin Settings, Job Management | Enables admin mode |

### Admin Settings

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `When User can see '{string}' settings tiles` | Admin Settings | Validates settings tiles |
| 5 | `When User searches '{string}' in settings search` | Admin Settings | Searches settings |

### Admin Query

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 6 | `When User clicks on '{string}' Card` | Admin Query, User Management | Clicks settings card |
| 7 | `When User clicks on Database dropdown` | Admin Query | Clicks Database dropdown |
| 8 | `When User selects '{string}' from the database dropdown` | Admin Query | Selects database |
| 9 | `When User enters '{string}' in the query textbox` | Admin Query | Enters SQL query |
| 10 | `When User enters '{string}' in the Max Rows to Collected textbox` | Admin Query | Enters max rows |
| 11 | `When User clicks on Run button` | Admin Query | Clicks Run |

### Job Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 12 | `When User clicks on Jobs tab` | Job Management, Settings Jobs | Clicks Jobs tab |
| 13 | `When User clicks on Add Job button` | Job Management | Clicks Add Job |
| 14 | `When User enters job name as '{string}'` | Job Management | Enters job name |
| 15 | `When User enters Pixel as '{string}'` | Job Management | Enters Pixel |
| 16 | `When User selects frequency '{string}'` | Job Management | Selects frequency |
| 17 | `When User clicks on Save Job button` | Job Management | Saves job |
| 18 | `When User clicks on Edit button for job '{string}'` | Job Management | Edits job |
| 19 | `When User clicks on Delete button for job '{string}'` | Job Management | Deletes job |
| 20 | `When User clicks on Run button for job '{string}'` | Job Management | Runs job |
| 21 | `When User clicks on Pause button for job '{string}'` | Job Management | Pauses job |
| 22 | `When User clicks on Resume button for job '{string}'` | Job Management | Resumes job |

### Settings Jobs Page

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 23 | `When User can see '{string}' titles on Jobs page` | Settings Jobs | Validates titles |
| 24 | `When User can see '{string}' tiles on Jobs page` | Settings Jobs | Validates tiles |
| 25 | `When User can see '{string}' tabs on Jobs page` | Settings Jobs | Validates tabs |
| 26 | `When User clicks on History tab` | Settings Jobs | Clicks History tab |

### Privacy Center

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 27 | `When User clicks on Privacy Center link` | Privacy Center | Clicks Privacy Center |
| 28 | `When User can see Privacy Center popup elements` | Privacy Center | Validates popup elements |
| 29 | `When User clicks on Close icon` | Privacy Center | Clicks Close icon |
| 30 | `When User clicks on Close button` | Privacy Center | Clicks Close button |

### My Profile

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 31 | `When User clicks on profile icon` | My Profile | Clicks profile icon |
| 32 | `When User clicks on My Profile` | My Profile | Opens My Profile |
| 33 | `When User clicks on Generate Access Token` | My Profile | Opens access token generation |
| 34 | `When User enters token name as '{string}'` | My Profile | Enters token name |
| 35 | `When User enters token description as '{string}'` | My Profile | Enters token description |
| 36 | `When User clicks on Generate button` | My Profile | Generates token |
| 37 | `When User clicks on Delete token button` | My Profile | Deletes token |

### User Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 38 | `When User clicks on 'Member Settings' Card` | User Management | Opens Member Settings |
| 39 | `When User clicks on Add User button` | User Management | Clicks Add User |
| 40 | `When User enters user name as '{string}'` | User Management | Enters user name |
| 41 | `When User enters user email as '{string}'` | User Management | Enters email |
| 42 | `When User enters user password as '{string}'` | User Management | Enters password |
| 43 | `When User enters user type as '{string}'` | User Management | Enters user type |
| 44 | `When User clicks on Save User button` | User Management | Saves user |
| 45 | `When User selects user '{string}' from table` | User Management | Selects user |
| 46 | `When User edits model limit as '{string}'` | User Management | Edits model limit |
| 47 | `When User clicks on 'Configuration' Card` | User Management | Opens Configuration |
| 48 | `When User selects configuration type '{string}'` | User Management | Selects config type |
| 49 | `When User enters configuration value '{string}'` | User Management | Enters config value |
| 50 | `When User clicks on Update Configuration button` | User Management | Updates configuration |

### User Permissions (Catalog)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 51 | `When User clicks on Open Vector` | User Permissions | Opens Vector catalog |
| 52 | `When User clicks on Open Model` | User Permissions | Opens Model catalog |
| 53 | `When User clicks on Open Database` | User Permissions | Opens Database catalog |
| 54 | `When User clicks on Open Function` | User Permissions | Opens Function catalog |
| 55 | `When User clicks on Open Storage` | User Permissions | Opens Storage catalog |
| 56 | `When User clicks on Open App Library` | User Permissions | Opens App Library |
| 57 | `When User clicks on Add Vector Database` | User Permissions | Clicks Add Vector |
| 58 | `When User clicks on Add Model` | User Permissions | Clicks Add Model |
| 59 | `When User clicks on Add Database` | User Permissions | Clicks Add Database |
| 60 | `When User clicks on Add Function` | User Permissions | Clicks Add Function |
| 61 | `When User clicks on Add Storage` | User Permissions | Clicks Add Storage |
| 62 | `When User clicks on file upload icon` | User Permissions | Clicks file upload icon |
| 63 | `When User uploads the file '{string}'` | User Permissions | Uploads file |
| 64 | `When User clicks on '{string}' button to create catalog` | User Permissions | Clicks Upload |
| 65 | `When User clicks on Create New App button` | User Permissions | Clicks Create New App |
| 66 | `When User clicks on Get Started button in "{string}"` | User Permissions | Clicks Get Started |
| 67 | `When User enters app name as '{string}'` | User Permissions | Enters app name |
| 68 | `When User clicks on Create button` | User Permissions | Clicks Create |
| 69 | `When User fetch the app name` | User Permissions | Fetches app name |
| 70 | `When User clicks on Access Control Tab` | User Permissions | Clicks Access Control |
| 71 | `When User clicks on Add Member button` | User Permissions | Clicks Add Member |
| 72 | `When User enters member '{string}'` | User Permissions | Enters member |
| 73 | `When User selects permission '{string}'` | User Permissions | Selects permission |
| 74 | `When User clicks on Save Member button` | User Permissions | Saves member |
| 75 | `When User logs in as '{string}'` | User Permissions | Logs in as user |

### App Creation (for User Permissions)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 76 | `When User fetch the app name` | User Permissions | Fetches the app name |

### Vector Settings

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 77 | `When User clicks on 'Vector Settings' Card` | Vector Settings | Opens Vector Settings |
| 78 | `When User searches '{string}' in vector settings search` | Vector Settings | Searches vector settings |
| 79 | `When User selects '{string}' vector from settings` | Vector Settings | Selects vector |

## Then Steps

### Admin Query Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see table with '{int}' columns:'{string}'` | Admin Query | Validates table columns |
| 2 | `Then User can see success toast message as '{string}'` | Admin Query | Validates success toast |
| 3 | `Then User can see table with '{int}' rows` | Admin Query | Validates table rows |
| 4 | `Then User Delete the created Model` | Admin Query | Cleanup — deletes models |

### Admin Settings Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `Then User can see admin mode is enabled` | Admin Settings | Validates admin mode |
| 6 | `Then User can see '{string}' settings tiles` | Admin Settings | Validates tiles count |
| 7 | `Then User can see '{string}' in settings search results` | Admin Settings | Validates search results |

### Job Management Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 8 | `Then User can see job '{string}' in job list` | Job Management | Validates job in list |
| 9 | `Then User can see job '{string}' is updated` | Job Management | Validates job update |
| 10 | `Then User can see job '{string}' is deleted` | Job Management | Validates job deletion |
| 11 | `Then User can see job '{string}' is running` | Job Management | Validates job running |
| 12 | `Then User can see job '{string}' is paused` | Job Management | Validates job paused |
| 13 | `Then User can see job '{string}' is resumed` | Job Management | Validates job resumed |

### Settings Jobs Page Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 14 | `Then User can see Jobs page elements` | Settings Jobs | Validates page elements |
| 15 | `Then User can see History tab content` | Settings Jobs | Validates History tab |

### Privacy Center Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 16 | `Then User can see Privacy Center popup is closed` | Privacy Center | Validates popup closed |

### My Profile Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `Then User can see '{string}' link in the top right` | My Profile | Validates Privacy Center link |
| 18 | `Then User can see '{string}' section on profile page` | My Profile, User Management | Validates profile sections |
| 19 | `Then User fills Name as '{string}' in Name field` | My Profile | Fills Name for token |
| 20 | `Then User fills Description as '{string}' in Description field` | My Profile | Fills Description for token |
| 21 | `Then User clicks on Generate button` | My Profile | Clicks Generate |
| 22 | `Then User can see the token is generated` | My Profile | Validates token generation |
| 23 | `Then User can see the token is deleted` | My Profile | Validates token deletion |

### User Management Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 24 | `Then User can see user '{string}' in user list` | User Management | Validates user in list |
| 25 | `Then User can see model limit is '{string}'` | User Management | Validates model limit |
| 26 | `Then User can see configuration is updated` | User Management | Validates config update |

### User Permissions Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 27 | `Then User can see member '{string}' with permission '{string}'` | User Permissions | Validates member permission |
| 28 | `Then '{string}' user can '{string}' Settings` | User Permissions | Validates user access |
| 29 | `Then User sees success toast message '{string}'` | User Permissions | Validates toast |

### Vector Settings Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 30 | `Then User can see vector '{string}' in vector settings` | Vector Settings | Validates vector in settings |

---

# System Apps

> **Feature Path:** `src/test/resources/Features/systemApps/`  
> **Step Files:** System Apps related step files

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | BI CSV, BI Excel, Terminal | Validates user is on the Home page |
| 2 | `Given User can see database created success toast message as 'Success'` | BI CSV | Validates database creation success toast |
| 3 | `Given User is on Terminal page` | Terminal | Validates user is on Terminal page |

## When Steps

### Navigation & App Library

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | BI CSV, BI Excel, Terminal | Opens the main navigation menu |
| 2 | `When User clicks on Open App Library` | BI CSV, BI Excel, Terminal | Opens the App Library page |
| 3 | `When User clicks on Create New App button` | BI CSV, BI Excel | Clicks Create New App button |
| 4 | `When User clicks on Get Started button in "Drag and Drop"` | BI CSV, BI Excel | Clicks Get Started in Drag and Drop |
| 5 | `When User enters app name as 'Test app'` | BI CSV, BI Excel | Enters app name |
| 6 | `When User enters description as 'Created by automation script'` | BI CSV, BI Excel | Enters description |
| 7 | `When User enters tags '{string}' and presses Enter` | BI CSV, BI Excel | Enters tags |
| 8 | `When User clicks on Create button` | BI CSV, BI Excel | Clicks Create |
| 9 | `When User fetch the app name` | BI CSV, BI Excel | Fetches app name |

### Database Creation (BI)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `When User clicks on Notebook` | BI CSV, BI Excel | Opens Notebook |
| 11 | `When User clicks on Create new notebook` | BI CSV, BI Excel | Creates notebook |
| 12 | `When User enters New Query name as '{string}'` | BI CSV, BI Excel | Enters query name |
| 13 | `When User clicks on query Submit button` | BI CSV, BI Excel | Submits query |
| 14 | `When User mouse hover below the existing cell` | BI CSV, BI Excel | Hovers below cell |
| 15 | `When User selects '{string}' from the hidden options` | BI CSV, BI Excel | Selects hidden option |
| 16 | `When User selects '{string}' from the data import options` | BI CSV, BI Excel | Selects import option |
| 17 | `When User clicks on file upload icon` | BI CSV, BI Excel | Clicks file upload |
| 18 | `When User uploads the file '{string}'` | BI CSV, BI Excel | Uploads file |
| 19 | `When User enters Database name '{string}'` | BI CSV, BI Excel | Enters DB name |
| 20 | `When User clicks on Create Database button` | BI CSV, BI Excel | Creates database |

### Insight Creation (BI)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `When User clicks on Blocks if it is not selected by default` | BI CSV, BI Excel | Selects Blocks |
| 22 | `When User drags the '{string}' block and drops it on the page` | BI CSV, BI Excel | Drags block |
| 23 | `When User clicks on the '{string}' block to select it` | BI CSV, BI Excel | Selects block |
| 24 | `When User clicks on Block Settings option` | BI CSV, BI Excel | Opens Block Settings |

### Terminal

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 25 | `When User click on System Apps` | Terminal | Clicks System Apps |
| 26 | `When User clicks on 'Terminal' app` | Terminal | Opens Terminal app |
| 27 | `When User enters Pixel command '{string}'` | Terminal | Enters Pixel command |
| 28 | `When User clicks on Run Pixel button` | Terminal | Runs Pixel |
| 29 | `When User enters Python command '{string}'` | Terminal | Enters Python command |
| 30 | `When User clicks on Run Python button` | Terminal | Runs Python |

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see '{string}' with the text '{string}'` | BI CSV, BI Excel | Validates page text |
| 2 | `Then User can see database created success toast message as '{string}'` | BI CSV, BI Excel | Validates creation toast |
| 3 | `Then User can see the insight is created` | BI CSV, BI Excel | Validates insight creation |
| 4 | `Then User can see Pixel output as '{string}'` | Terminal | Validates Pixel output |
| 5 | `Then User can see Python output as '{string}'` | Terminal | Validates Python output |
| 6 | `Then User can see 'Terminal' app in the System Apps section` | Terminal | Validates Terminal visible |





