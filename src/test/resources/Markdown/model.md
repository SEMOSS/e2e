# Model BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/model/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Model Types Available](#model-types-available)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the model-related feature files located under `src/test/resources/Features/model/`. It covers model creation, configuration, SMSS validation, chat functionality, permissions (Author/Editor/Read), catalog management, settings, and filtering.

---

## Given Steps

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 1 | `Given User opens Main Menu` | All model features | Opens the main navigation menu |
| 2 | `Given User is on Home page` | Model Settings | User is on the home page |
| 3 | `Given User can see the Model title as '{string}'` | Add Model, Model Permissions, Model Chat | Validates the model title is displayed |
| 4 | `Given User can see the Model title as 'Model'` | Add Model | Validates model title as 'Model' |
| 5 | `Given User can see the Model title as 'Catalog'` | Editor/Read Permission | Validates model title as 'Catalog' |

---

## When Steps

### Navigation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 1 | `When User opens Main Menu` | All model features | Opens the main navigation menu |
| 2 | `When User clicks on Open Model` | All model features | Opens the model catalog page |
| 3 | `When User clicks on Add Model` | All model features | Clicks the Add Model button |

### Model Creation - Form Based

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 4 | `When User clicks on the '{string}' tab` | Add All Model Types | Clicks on model group tab (e.g., OpenAI, Google Vertex AI, AWS Bedrock) |
| 5 | `When User selects '{string}'` | Add All Model Types, Add Model | Selects specific model (e.g., GPT 3.5 Turbo, GPT-5, Claude 3 Opus) |
| 6 | `When User selects 'OpenAI' type` | Add Model, Model Permissions | Selects OpenAI as model type |
| 7 | `When User enters Catalog Name as '{string}'` | All model features | Enters model catalog name |
| 8 | `When User enters Open AI Key as '{string}'` | Add Model, Model Permissions | Enters OpenAI API key |
| 9 | `When User clicks on Create Model button` | Add Model, Model Permissions | Clicks Create Model button |
| 10 | `When User fills the model creation form with:` | Add All Model Types | Fills model creation form with data table |
| 11 | `When User clicks on model 'Create Model' button` | Add All Model Types | Clicks Create Model button on form |

### Model Creation - File Upload

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 12 | `When User clicks on file upload icon` | Model Chat | Clicks the file upload icon |
| 13 | `When User uploads the file '{string}'` | Model Chat | Uploads a model file (e.g., 'Model/Llama_model.zip') |
| 14 | `When User clicks on '{string}' button to create catalog` | Model Chat | Clicks Upload button to create catalog |
| 15 | `When User get the CatalogName for variable` | Model Chat | Gets the catalog name for variable reference |

### Model Catalog Actions

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 16 | `When User clicks on Copy Catalog ID` | All model features | Copies the model catalog ID |
| 17 | `When User copies the model catalog ID below the title using copy icon` | Add Model | Copies the model catalog ID from below title |
| 18 | `When User searches the '{string}' in the model catalog searchbox` | Add Model, Model Catalog, Permissions | Searches model in catalog searchbox |
| 19 | `When User selects the '{string}' from the model catalog` | Add Model, Permissions | Selects model from catalog list |
| 20 | `When User should see the '{string}' on the model catalog page` | Add Model, Model Catalog | Validates model visible on catalog page |

### Model Edit & Metadata

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 21 | `When User clicks on Edit button` | Add Model, Model Catalog | Clicks the edit button |
| 22 | `When User enters the details as '{string}'` | Add Model | Enters model details text |
| 23 | `When User enters the description as '{string}'` | Add Model | Enters model description |
| 24 | `When User add Tags '{string}' and presses Enter` | Add Model, Model Catalog | Adds tags to the model |
| 25 | `When User enters the Domains as '{string}'` | Add Model, Model Catalog | Enters domain values |
| 26 | `When User selects '{string}' from the Data Classification dropdown` | Add Model, Model Catalog | Selects data classification |
| 27 | `When User selects '{string}' from the Data Restrictions dropdown` | Add Model, Model Catalog | Selects data restrictions |
| 28 | `When User clicks on Submit button` | Add Model, Model Catalog, Permissions | Clicks submit to save changes |

### Model SMSS

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 29 | `When User clicks on SMSS` | Add Model, Add All Model Types | Opens SMSS properties tab |
| 30 | `When User clicks on Edit SMSS button` | Add Model | Clicks Edit SMSS button |
| 31 | `When User can edit the value of '{string}' field as '{string}'` | Add Model | Edits a specific SMSS field value |
| 32 | `When User clicks on Update SMSS button` | Add Model | Clicks Update SMSS button to save |
| 33 | `When User refresh the page` | Add Model | Refreshes the page |

### Model Usage Tab

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 34 | `When User clicks on Usage tab` | Add Model | Clicks on Usage tab |
| 35 | `When User copies code contents and validate model catalog Id occurences in sections:` | Add Model | Validates model ID in usage code sections with data table |

### Model Chat

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 36 | `When User clicks on Chat button` | Model Chat | Clicks the Chat button |
| 37 | `When User click on send button to submit the query` | Model Chat | Clicks send button to submit chat query |
| 38 | `When User click on clear all button` | Model Chat | Clicks clear all button to clear chat |

### Model Settings

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 39 | `When User clicks on Model Setting tab` | Model Settings | Clicks on Model Setting tab |

### Model Permissions & Access Control

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 40 | `When '{string}' user clicks on Settings` | Author Permission | Specified user clicks on Settings |
| 41 | `When '{string}' user clicks on Access Control` | Editor/Read Permission | Specified user clicks on Access Control |
| 42 | `When User clicks on Add Member button` | All Permission features | Clicks Add Member button |
| 43 | `When User adds one user and assigns them as '{string}'` | All Permission features | Adds a user with specified role (Author/Editor/Read) |
| 44 | `When User Search the '{string}' user from Access Control` | Author Permission | Searches user in Access Control |
| 45 | `When User Search '{string}' user from Access Control` | Editor Permission | Searches user in Access Control |
| 46 | `When User deletes the '{string}' user` | Author/Editor Permission | Deletes a user from Access Control |

### User Login/Logout

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 47 | `When User logs out from the application` | Editor/Read Permission | Logs out from the application |
| 48 | `When User login as '{string}'` | Editor/Read Permission | Logs in as specified user |

---

## Then Steps

### Model Creation Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 1 | `Then User can see the Model title as '{string}'` | All model features | Validates model title displayed |
| 2 | `Then User can see a toast message as 'Successfully added LLM to catalog'` | Add Model | Validates LLM creation success toast |
| 3 | `Then User can see a toast message as 'Model uploaded successfully!'` | Model Chat | Validates model file upload success toast |
| 4 | `Then User can see 'Create Model' button becomes enabled` | Add All Model Types | Validates Create Model button is enabled |

### Model Form Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 5 | `Then User can see following form sections with fields:` | Add All Model Types | Validates model form sections and fields with data table |
| 6 | `Then User can see following fields are mandatory fields` | Add All Model Types | Validates mandatory fields with data table |
| 7 | `Then User can view the following model options under their group` | Available Models | Validates model groups and options with data table |

### Model SMSS Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 8 | `Then User can see following fields in SMSS Properties` | Add All Model Types | Validates SMSS fields with data table |
| 9 | `Then User can see name in 'NAME' field as '{string}' in SMSS properties` | Add Model | Validates NAME field in SMSS |
| 10 | `Then User can see var name in 'VAR_NAME' field as '{string}' in SMSS properties` | Add Model | Validates VAR_NAME field in SMSS |
| 11 | `Then User can see updated value in '{string}' field as '{string}'` | Add Model | Validates updated SMSS field value |

### Model Edit Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 12 | `Then User can see a edit success toast message as 'Successfully set the new metadata values for the engine'` | Add Model | Validates edit success toast |
| 13 | `Then User should see '{string}' on the page` | Add Model | Validates text/tag visible on page |
| 14 | `Then User should see description as '{string}' on the page` | Add Model | Validates description displayed |
| 15 | `Then User should see '{string}' in the overview Tag section` | Add Model | Validates tags in overview |
| 16 | `Then User should see '{string}' in the overview Domain section` | Add Model | Validates domains in overview |
| 17 | `Then User should see '{string}' in the overview Data classification section` | Add Model | Validates data classification in overview |
| 18 | `Then User should see '{string}' in the overview Data restrictions section` | Add Model | Validates data restrictions in overview |
| 19 | `Then User should see '{string}' in the overview Details section` | Add Model | Validates details in overview |

### Model Catalog Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 20 | `Then User should see the '{string}' on the model catalog page` | Add Model, Model Catalog | Validates model visible on catalog page |
| 21 | `Then User applies each filter and validate '{string}' catalog is visible on the '{string}' catalog page` | Model Catalog | Validates filter functionality with data table |

### Model Chat Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 22 | `Then User should see the Chat section for Model with title 'Chat with the Model'` | Model Chat | Validates Chat section title |
| 23 | `Then User should see the Model ID and Model Name displayed in Model information section` | Model Chat | Validates Model ID and Name in info section |
| 24 | `Then User should see the Temperature value displayed as '{string}' in Model information section by default` | Model Chat | Validates default Temperature value |
| 25 | `Then User should see the Max Tokens value displayed as '{string}' in Model information section by default` | Model Chat | Validates default Max Tokens value |
| 26 | `Then User should see the input textbox with placeholder as 'Ask a question...'` | Model Chat | Validates chat input placeholder |
| 27 | `Then User should see the send button get active on entering text '{string}' in the input textbox` | Model Chat | Validates send button becomes active |
| 28 | `Then User should see the loader indicating that the response is being generated for the query` | Model Chat | Validates loading indicator |
| 29 | `Then User should see the response generated for the query in the chat window` | Model Chat | Validates response in chat window |
| 30 | `Then User should see the chat window is cleared of previous conversation` | Model Chat | Validates chat window is cleared |

### Model Settings Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 31 | `Then User can see '{string}' section` | Model Settings | Validates section is visible (Private, Non Discoverable, Delete Model, Pending Requests, Permissions) |
| 32 | `Then User can see text message in '{string}' section as '{string}'` | Model Settings | Validates text message in section |
| 33 | `Then User can see toggle button in '{string}' section` | Model Settings | Validates toggle button in section |
| 34 | `Then User can see Delete button in 'Delete Model' section` | Model Settings | Validates Delete button in Delete Model section |
| 35 | `Then User can see 'Pending Requests' section contains 'pending request' or 'pending requests' text with count` | Model Settings | Validates pending requests text and count |
| 36 | `Then User can see 'Search Members' textbox` | Model Settings | Validates Search Members textbox |
| 37 | `Then User can see 'Add Members' button` | Model Settings | Validates Add Members button |
| 38 | `Then User can see 'Rows per page' selection dropdown` | Model Settings | Validates Rows per page dropdown |

### Model Permission Validation - Author

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 39 | `Then '{string}' user can '{string}' Overview` | Author/Editor/Read Permission | Validates user can View/Not View Overview |
| 40 | `Then '{string}' user can '{string}' Usage` | Author/Editor/Read Permission | Validates user can View/Not View Usage |
| 41 | `Then '{string}' user can '{string}' SMSS Details` | Author/Editor/Read Permission | Validates user can View/Not View SMSS Details |
| 42 | `Then '{string}' user can '{string}' Edit SMSS` | Author/Editor/Read Permission | Validates user can View/Not View Edit SMSS |
| 43 | `Then '{string}' user can '{string}' Access Control` | Author/Editor/Read Permission | Validates user can View/Not View Access Control |
| 44 | `Then '{string}' user '{string}' see Member Setting` | Author/Editor/Read Permission | Validates user can/cannot see Member Setting |
| 45 | `Then '{string}' user '{string}' Delete Catalog` | Author Permission | Validates user can/cannot Delete Catalog |

### User Login Validation

| # | Step Definition | Feature File | Description |
|---|----------------|--------------|-------------|
| 46 | `Then User login as '{string}'` | Editor/Read Permission | Logs in as specified user |

---

## Model Types Available

### OpenAI

| # | Model | Form Fields |
|---|-------|-------------|
| 1 | Other OpenAI models | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 2 | GPT-5 | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 3 | GPT-5.1 | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 4 | GPT-5 Mini | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 5 | GPT-5 nano | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 6 | GPT 4 | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 7 | GPT 3.5 Turbo | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 8 | GPT-4o | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 9 | DALL E 3 | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 10 | DALL E 2 | Catalog Name, Model, Chat Type, Open AI Key, Max Tokens, Context Window |
| 11 | text-embedding-3-large | Catalog Name, Model, Tag, OpenAI API Key, Max Tokens, Max Input Tokens |
| 12 | text-embedding-3-small | Catalog Name, Model, Tag, OpenAI API Key, Max Tokens, Max Input Tokens |

### Google Vertex AI

| # | Model | Form Fields |
|---|-------|-------------|
| 1 | Other Google Vertex AI models | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 2 | Gemini 2.5 Pro | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 3 | Gemini Pro | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 4 | Gemini Ultra | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 5 | Gemma 2b | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 6 | Llama 2-7b | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 7 | Llama 2-70b | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 8 | PaLM 2 Bison | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 9 | PaLM 2 Bison (32k) | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 10 | Code Generation Bison | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |
| 11 | Mistral | Catalog Name, Model, Project, GCP Region, Service Account Credentials, Max Tokens, Context Window |

### AWS Bedrock

| # | Model | Form Fields |
|---|-------|-------------|
| 1 | Other AWS Bedrock models | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 2 | Claude 3 Opus | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 3 | Claude 3 Sonnet | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 4 | Claude 3 Haiku | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 5 | Claude 2.0 | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 6 | Jurassic-2 Ultra | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 7 | Jurassic-2 Mid | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 8 | Titan Text G1 Express | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |
| 9 | Titan Embeddings (text) | Catalog Name, Model ID, AWS Access Key ID, AWS Secret Access Key, Region, Max Completion Tokens, Context Window |

### NVIDIA NIM

| # | Model | Form Fields |
|---|-------|-------------|
| 1 | Other NVIDIA NIM models | Catalog Name, Model Name, Completion Type, OPEN AI Key, Max Completion Tokens, Max Input Tokens, Context Window |
| 2 | EMBED QA 4 | Catalog Name, Model Name, Completion Type, OPEN AI Key, Max Completion Tokens, Max Input Tokens, Context Window |
| 3 | Rerank QA Mistral 4B | Catalog Name, Model Name, Completion Type, OPEN AI Key, Max Completion Tokens, Max Input Tokens, Context Window |

### OpenAI-Compatible (Commented Out)

| # | Model |
|---|-------|
| 1 | Other OpenAI-Compatible models |
| 2 | Bert |
| 3 | Dolly |
| 4 | Eleuther GPTJ |
| 5 | Falcon |
| 6 | Flan T5 Large |
| 7 | Flan T5 XXL |
| 8 | Guanaco |
| 9 | Llama2 7B |
| 10 | Llama2 13B |
| 11 | Llama2 70B |
| 12 | Mosaic ML |
| 13 | Replit code model – 3b |
| 14 | StableBeluga2 |
| 15 | Vicuna |
| 16 | Wizard 13B |
| 17 | Wizard Coder |

### Embedded (Commented Out)

| # | Model |
|---|-------|
| 1 | Other Embedded models |
| 2 | Orca |

### Azure OpenAI (Commented Out)

| # | Model |
|---|-------|
| 1 | Other Azure OpenAI models |
| 2 | Azure Open AI |
| 3 | Azure Open AI ADA Embedding |

---

## Feature Files Summary

| # | Feature File | Scenarios | Description |
|---|-------------|-----------|-------------|
| 1 | Add All Model Types | 1 Scenario Outline (30+ examples) | Create all model types and validate SMSS properties |
| 2 | Add Model | 6 Scenarios | Create GPT 3.5 Turbo, validate SMSS, edit SMSS, add tags, view catalog, edit details, validate usage |
| 3 | Model Chat | 1 Scenario | Validate chat section, send query, view response, clear chat |
| 4 | Model Settings | 1 Scenario | Validate settings page sections (Private, Non Discoverable, Delete, Pending Requests, Permissions) |
| 5 | Model Permission - Author | 10 Scenarios | Author can view overview, usage, SMSS, edit SMSS, access control, add/delete members, delete model |
| 6 | Model Permission - Editor | 8 Scenarios | Editor can view overview, usage, access control, add/delete read members; cannot view SMSS |
| 7 | Model Permission - Read | 6 Scenarios | Read can view overview, usage; cannot view SMSS, access control, member settings |
| 8 | Available Models | 1 Scenario | Verify all model groups and model options |
| 9 | View Model Catalog | 1 Scenario | View and validate filter functionality on model catalog |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 5 |
| When Steps | 48 |
| Then Steps | 46 |
| **Total** | **99** |

---