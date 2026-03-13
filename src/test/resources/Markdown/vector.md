# Vector BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/vector/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Vector Database Types Available](#vector-database-types-available)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the vector-related feature files located under `src/test/resources/Features/vector/`. It covers vector database creation (FAISS, Pinecone, ZIP upload), vector form field validation for all connection types (Azure AI Search, Chroma, Elastic Search, FAISS, Milvus, Open Search, PGVector, Pinecone, Weaviate), SMSS property validation, usage examples, vector overview, Q&A tab functionality, access control, change access request, catalog filtering, bookmarking, and discoverable vectors.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | Add Vector DB, Verify All Vector DB, View Add Vector Options, Vector Q&A | User is on the home page |
| 2 | `Given User opens Main Menu` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Opens the main navigation menu |
| 3 | `Given User can see the Vector title as '{string}'` | Vector Overview, Vector Q&A | Validates vector title is displayed |
| 4 | `Given User can see the 'Q&A' tab is displayed` | Vector Q&A | Validates Q&A tab is displayed |
| 5 | `Given User clicks on the Q&A tab` | Vector Q&A | Clicks on Q&A tab |

---

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
| 4 | `When User clicks on Add Model` | Add Vector DB, Vector Overview, View Existing Vectors | Clicks Add Model button |
| 5 | `When User selects '{string}' type` | Add Vector DB, Vector Overview, View Existing Vectors | Selects model type (e.g., 'OpenAI') |
| 6 | `When User selects '{string}'` | Add Vector DB, Vector Overview, View Existing Vectors | Selects model (e.g., 'GPT 3.5 Turbo') |
| 7 | `When User enters Catalog Name as '{string}'` | Add Vector DB, Vector Overview, View Existing Vectors | Enters model catalog name |
| 8 | `When User enters Open AI Key as '{string}'` | Add Vector DB, Vector Overview, View Existing Vectors | Enters OpenAI API key |
| 9 | `When User clicks on Create Model button` | Add Vector DB, Vector Overview, View Existing Vectors | Clicks Create Model button |
| 10 | `When User clicks on Copy Catalog ID` | All vector features | Copies the catalog ID |
| 11 | `When User clicks on Edit button` | Add Vector DB, Vector Overview, View Existing Vectors | Clicks Edit button |
| 12 | `When User add Tags '{string}' and presses Enter` | Add Vector DB, Vector Overview, View Existing Vectors | Adds tags |
| 13 | `When User clicks on Submit button` | Add Vector DB, Vector Overview, View Existing Vectors | Clicks Submit button |

### Model Setup (ZIP Upload - Q&A Background)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 14 | `When User select the zip icon option to upload file for '{string}'` | Vector Q&A | Clicks zip upload option for model |
| 15 | `When User uploads the file '{string}'` | Vector Q&A, Verify All Vector DB | Uploads a file |
| 16 | `When User click on Upload button for '{string}'` | Vector Q&A | Clicks Upload button for model |
| 17 | `When User can see a toast message as '{string}'` | Vector Q&A | Validates toast message |

### Vector Creation - Connection Types

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 18 | `When User clicks on Add Vector button` | All vector creation features | Clicks Add Vector button |
| 19 | `When User selects '{string}' connection` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Selects vector connection type (e.g., 'FAISS') |
| 20 | `When User enters vector database Catalog name as '{string}'` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Enters vector database catalog name |
| 21 | `When User selects '{string}' from Embedder field` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Selects embedder model |
| 22 | `When User selects '{string}' from Chunking Strategy field` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Selects chunking strategy (Token, Page by page, Markdown) |
| 23 | `When User enters value of Content Length as '{string}'` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Enters content length value |
| 24 | `When User enters value of Content Overlap as '{string}'` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Enters content overlap value |
| 25 | `When User clicks on Create Vector button` | Add Vector DB, Vector Overview, Vector Q&A, View Existing Vectors | Clicks Create Vector button |

### Vector Creation - ZIP Upload

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 26 | `When User checks if 'Vetcor' catalog created and Deletes the '{string}'` | Verify All Vector DB | Checks if vector catalog exists and deletes it |
| 27 | `When User clicks on file upload icon` | Verify All Vector DB | Clicks file upload icon |
| 28 | `When User uploads the file '{string}'` | Verify All Vector DB | Uploads vector ZIP file |
| 29 | `When User clicks on '{string}' button to create catalog` | Verify All Vector DB | Clicks Upload button to create catalog |
| 30 | `When User sees success toast message '{string}'` | Verify All Vector DB | Validates success toast message |

### Vector Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 31 | `When User selects vector database '{string}' from connection types` | Verify All Vector DB | Selects vector database type from connections |

### Vector SMSS Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 32 | `When User clicks on SMSS` | Add Vector DB | Clicks SMSS tab |

### Vector Usage Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 33 | `When User clicks on Usage tab for Vector DB` | Add Vector DB | Clicks Usage tab for Vector DB |

### Vector Overview

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 34 | `When User searches the '{string}' in the Vector Catalog searchbox` | Vector Overview, View Existing Vectors | Searches vector in catalog searchbox |
| 35 | `When User clicks on the created Vector card name as '{string}'` | Vector Overview, Vector Q&A | Clicks on vector card by name |
| 36 | `When User sees and copies the vector id` | Vector Overview | Sees and copies vector ID |

### Vector Q&A Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 37 | `When User clicks on the Q&A tab` | Vector Q&A | Clicks on Q&A tab |
| 38 | `When User clicks on Select Model dropdown` | Vector Q&A | Clicks Select Model dropdown in Q&A |
| 39 | `When User selects a model '{string}'` | Vector Q&A | Selects model from dropdown |
| 40 | `When User adjusts "{string}" slider to '{string}'` | Vector Q&A | Adjusts slider value (Limit queried results / Set Temperature) |
| 41 | `When User enters a question "{string}" in the question input textbox` | Vector Q&A | Enters a question in the Q&A input |
| 42 | `When User clicks on Generate Answer button` | Vector Q&A | Clicks Generate Answer button |

### Vector Access Control & Settings

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 43 | `When '{string}' user clicks on Settings` | Add Vector DB, Vector Overview | User clicks on Settings |
| 44 | `When User clicks on Add Member button` | Add Vector DB, Vector Overview | Clicks Add Member button |
| 45 | `When User adds one user and assigns them as '{string}'` | Add Vector DB, Vector Overview | Adds user with role (Editor/Read) |
| 46 | `When User clicks on Access Control Tab` | View Existing Vectors | Clicks Access Control tab |
| 47 | `When User clicks Make '{string}' Discoverable button` | View Existing Vectors | Clicks Make Vector Discoverable button |

### Vector Change Access

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 48 | `When User selects the '{string}' from the Vector catalog` | Add Vector DB, View Existing Vectors | Selects vector from catalog |
| 49 | `When User click on the Change Access button` | Add Vector DB | Clicks Change Access button |
| 50 | `When User should see the "Change Access" popup with following options:` | Add Vector DB | Validates Change Access popup options (Author, Editor, Read-Only, Comment Box, Cancel Button, Request Button) |
| 51 | `When User click on cancel button` | Add Vector DB | Clicks Cancel button on Change Access popup |
| 52 | `When User selects '{string}' access` | Add Vector DB | Selects access level (e.g., 'author') |
| 53 | `When User types a comment as '{string}'` | Add Vector DB | Types a comment for access request |
| 54 | `When User clicks on Request button` | Add Vector DB | Clicks Request button |
| 55 | `When User should successfully request access and a toast message as '{string}'` | Add Vector DB | Validates access request success toast |

### User Login/Logout

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 56 | `When User logs out from the application` | Add Vector DB, Vector Overview, View Existing Vectors | Logs out from the application |
| 57 | `When User login as '{string}'` | Add Vector DB, Vector Overview, View Existing Vectors | Logs in as specified user |

### Vector Catalog Edit & Filter

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 58 | `When User enters the Domains as '{string}'` | View Existing Vectors | Enters domain values |
| 59 | `When User selects '{string}' from the Data Classification dropdown` | View Existing Vectors | Selects data classification |
| 60 | `When User selects '{string}' from the Data Restrictions dropdown` | View Existing Vectors | Selects data restrictions |
| 61 | `When User clicks on Discoverable Vectors button` | View Existing Vectors | Clicks Discoverable Vectors button |

---

## Then Steps

### Vector Creation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see vector database created success toast message as '{string}'` | Add Vector DB, Vector Overview, Vector Q&A | Validates vector creation success toast |
| 2 | `Then User can see the Vector title as '{string}'` | Add Vector DB, Vector Overview, Vector Q&A | Validates vector title displayed |
| 3 | `Then User can see the Catalog title as '{string}'` | Verify All Vector DB | Validates catalog title displayed |
| 4 | `Then User sees success toast message '{string}'` | Verify All Vector DB | Validates success toast message |

### Vector SMSS Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `Then User can see vector catalog name in 'NAME' field as '{string}' in SMSS properties` | Add Vector DB | Validates NAME field in SMSS |
| 6 | `Then User can see embedder engine name in 'EMBEDDER_ENGINE_NAME' field as '{string}' in SMSS properties` | Add Vector DB | Validates EMBEDDER_ENGINE_NAME in SMSS |
| 7 | `Then User can see content length in 'CONTENT_LENGTH' field as '{string}' in SMSS properties` | Add Vector DB | Validates CONTENT_LENGTH in SMSS |
| 8 | `Then User can see content overlap in 'CONTENT_OVERLAP' field as '{string}' in SMSS properties` | Add Vector DB | Validates CONTENT_OVERLAP in SMSS |
| 9 | `Then User can see chunking strategy in 'CHUNKING_STRATEGY' field as '{string}' in SMSS properties` | Add Vector DB | Validates CHUNKING_STRATEGY in SMSS |

### Vector Usage Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `Then User sees an example of "{string}" with example code for Vector DB` | Add Vector DB | Validates usage example (Javascript, Python, Langchain API, Java) |

### Vector Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 11 | `Then User can see vector Database form sections with fields:` | Verify All Vector DB | Validates vector form sections and fields with data table |
| 12 | `Then User can see vector database mandatory fields` | Verify All Vector DB | Validates vector mandatory fields with data table |

### Vector Overview Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 13 | `Then User sees and copies the vector id` | Vector Overview | Validates vector ID is visible and copyable |
| 14 | `Then User sees the copied success toast message '{string}'` | Vector Overview | Validates copy success toast (e.g., 'ID copied to clipboard') |
| 15 | `Then User sees Tags '{string}' that have been added to the Vector` | Vector Overview | Validates tags displayed on vector |
| 16 | `Then User sees the Change Access button` | Vector Overview | Validates Change Access button visible |

### Vector Q&A Tab Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `Then User can see the 'Adjust Configurations' panel should be visible` | Vector Q&A | Validates Adjust Configurations panel |
| 18 | `Then User can see the 'Select Model' dropdown should be present` | Vector Q&A | Validates Select Model dropdown |
| 19 | `Then User can see the 'Limit the queried results:' slider should be visible` | Vector Q&A | Validates Limit queried results slider |
| 20 | `Then User can see the 'Set Temperature:' slider should be visible` | Vector Q&A | Validates Set Temperature slider |
| 21 | `Then User hover on 'Limit the queried results:' option and see the "{string}"` | Vector Q&A | Validates tooltip for Limit queried results |
| 22 | `Then User hover on 'Set Temperature:' option and see the "{string}"` | Vector Q&A | Validates tooltip for Set Temperature |
| 23 | `Then User can see Q&A header should be displayed` | Vector Q&A | Validates Q&A header |
| 24 | `Then User sees question input textbox should be visible` | Vector Q&A | Validates question input textbox |
| 25 | `Then User should see the Generate Answer button in enable` | Vector Q&A | Validates Generate Answer button enabled |
| 26 | `Then User selects a model '{string}'` | Vector Q&A | Selects model from dropdown |
| 27 | `Then Selected model should be shown in dropdown '{string}'` | Vector Q&A | Validates selected model shown in dropdown |
| 28 | `Then The value should be updated as '{string}' for "{string}"` | Vector Q&A | Validates slider value updated |
| 29 | `Then User sees the answer generated for the question` | Vector Q&A | Validates answer is generated |

### Vector Access Control Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 30 | `Then User should see the "Change Access" popup with following options:` | Add Vector DB | Validates Change Access popup (Author, Editor, Read-Only, Comment Box, Cancel Button, Request Button) |
| 31 | `Then User should successfully request access and a toast message as '{string}'` | Add Vector DB | Validates access request success |
| 32 | `Then User login as '{string}'` | Add Vector DB, Vector Overview, View Existing Vectors | Logs in as specified user |

### Vector Catalog Filter Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 33 | `Then User applies each filter and validate '{string}' catalog is visible on the '{string}' catalog page` | View Existing Vectors | Validates filter functionality with data table |

### View Add Vector Options Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 34 | `Then User should see Search bar to filter vector options` | View Add Vector Options | Validates search bar visible on add vector page |
| 35 | `Then User should see the following '{string}' options with icons on the Connect to Vector page` | View Add Vector Options | Validates vector options with groups using data table |

---

## Vector Database Types Available

### Connection Types - 4 Section Forms

| # | Vector Database | General Fields | Settings Fields | Credentials Fields | Advanced Settings Fields | Mandatory Fields |
|---|----------------|---------------|----------------|-------------------|------------------------|-----------------|
| 1 | Azure AI Search | Catalog Name, Embedder | Chunking Strategy, Content Length, Content Overlap, Index Name, Embedding Dimension Size | Host Name, API Key, API Version | Method Name, Space Type, Index Engine, EF Construction, M Value, Record Questions and Responses, Distance Method, Retain Extracted Text | Catalog Name, Host Name, Method Name, Space Type, Index Engine, EF Construction, M Value, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Index Name, Embedding Dimension Size, API Key, API Version |
| 2 | Chroma | Catalog Name, Description, Tags | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses | Host Name, API Key, Collection Name | Distance Method | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Collection Name |
| 3 | Elastic Search | Catalog Name, Description, Tags | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses, Index Name | Host Name, API Key, API Key ID, Username, Password | Distance Method, Method Name, Space Type, Index Engine, EF Construction, M Value, Embedding Dimension Size | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Method Name, Space Type, Index Engine, EF Construction, M Value, Embedding Dimension Size |
| 4 | Milvus | Catalog Name, Description, Tags | Embedder, Chunking Strategy, Content Length, Content Overlap, Embedding Dimension Size | Host Name, API Key, Collection, Database | Distance Method, Index Type, EF Construction, M Value, Record Questions and Responses | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Collection, Database, API Key |
| 5 | Open Search | Catalog Name, Description, Tags | Embedder, Chunking Strategy, Content Length, Content Overlap, Embedding Dimension Size, Record Questions and Responses | Host Name, Index Name, Username, Password | Distance Method, Index Engine, EF Construction, M Value, Method Name | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Username, Password, Index Name, Index Engine, EF Construction, M Value, Method Name |
| 6 | PGVector | Catalog Name, Description, Tags | Embedder, Host Name, Port, Database, PGVector Table Name, Schema, Chunking Strategy, Content Length, Content Overlap, Additional Parameters, Record Questions and Responses | JDBC Url, Username, Password | Distance Method, Fetch Size, Connection Timeout, Pool Min Size, Pool Max Size | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Database, PGVector Table Name |
| 7 | Weaviate | Catalog Name, Description, Tags | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses | Host Name, API Key, Weaviate Classname, Autocut default | Distance Method | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, API Key, Weaviate Classname, Autocut default |

### Connection Types - 3 Section Forms

| # | Vector Database | General Fields | Settings Fields | Credentials / Advanced Fields | Mandatory Fields |
|---|----------------|---------------|----------------|------------------------------|-----------------|
| 8 | FAISS | Catalog Name, Description, Tags | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses | Advanced Settings: Distance Method | Catalog Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap |
| 9 | Pinecone | Catalog Name, Description, Tags | Embedder, Chunking Strategy, Content Length, Content Overlap, Record Questions and Responses | Credentials: Host Name, API Key, Namespace | Catalog Name, Host Name, Record Questions and Responses, Embedder, Chunking Strategy, Content Length, Content Overlap, Namespace, API Key |

### Available Vector Options

| Group | Vector Options |
|-------|---------------|
| Connections | Azure AI Search, Chroma, Elastic Search, FAISS, Milvus, Open Search, PGVector, Pinecone, Weaviate |

### FAISS Chunking Strategies Tested

| # | Chunking Strategy | Content Length | Content Overlap | Notes |
|---|------------------|---------------|----------------|-------|
| 1 | Token | 510 | 17 | Content Length editable |
| 2 | Page by page | 512 | 19 | Content Length defaults to 512 (field not present) |
| 3 | Markdown | 512 | 15 | Content Length defaults to 512 (field not present) |

---

## Feature Files Summary

| # | Feature File | Scenarios | Description |
|---|-------------|-----------|-------------|
| 1 | Add Vector Database | 4 Scenarios (1 Outline with 3 examples + 1 usage + 1 change access popup outline + 1 change access request outline) | Create FAISS vector DBs with Token/Page by page/Markdown chunking, validate SMSS properties, usage examples, change access popup, change access request |
| 2 | Vector Overview | 1 Scenario | Create FAISS vector, edit tags, validate overview as Editor (vector ID, tags, Change Access button) |
| 3 | Vector Q&A Tab | 4 Scenarios | Validate Q&A tab UI elements, Select Model dropdown, slider adjustments, generate answer functionality |
| 4 | Verify All Vector Database | 3 Scenarios (1 ZIP creation + 1 Outline with 8 examples for 4-section forms + 1 Outline with 2 examples for 3-section forms) | Create vector from ZIP, validate form fields for all 9 connection types |
| 5 | View Add Vector Options | 1 Scenario | Verify available vector connection options with icons |
| 6 | View Existing Vectors on Catalog Page | 2 Scenarios | Create FAISS vector, edit tags/domains/classification/restrictions, validate My Vectors filter, validate Discoverable Vectors filter |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 5 |
| When Steps | 61 |
| Then Steps | 35 |
| **Total** | **101** |

---