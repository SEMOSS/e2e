# App BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/app/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the app-related feature files located under `src/test/resources/Features/app/`. It covers app creation (Drag and Drop & Templates), notebook operations, scripting, transformation, database operations within notebooks, app settings & permissions (Author/Editor/Read), templates (Ask LLM, Landing Page, Multi Page, NLP Query to Grid, Variable Guide, Visualize CSV, CRUD Diabetes Record, Custom Frame to Visualization), and preview functionality.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | All app features | User is on the home page |
| 2 | `Given User opens Main Menu` | All app features | Opens the main navigation menu |
| 3 | `Given User clicks on app Edit button` | Editor Permission | Clicks app Edit button |

---

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
| 8 | `When User uploads the file '{string}'` | Notebook DB Ops, Transformation, Templates | Uploads a file (e.g., 'Database/TestDatabase.zip', 'Model/ModelZIP.zip') |
| 9 | `When User clicks on '{string}' button to create catalog` | Notebook DB Ops, Transformation, Templates | Clicks Upload button to create catalog |
| 10 | `When User clicks on Copy Catalog ID` | Notebook DB Ops, Transformation, Templates | Copies the catalog ID |
| 11 | `When User clicks on MetaData tab` | Notebook DB Ops, Transformation | Clicks MetaData tab |
| 12 | `When User clicks on Refresh button` | Notebook DB Ops, Transformation | Clicks Refresh button on MetaData |
| 13 | `When User selects the '{string}' from the dropdown` | Notebook DB Ops, Transformation | Selects table from dropdown (e.g., 'DIABETES') |
| 14 | `When User clicks on apply database button` | Notebook DB Ops, Transformation | Clicks apply database button |

### Model Setup (Background)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 15 | `When User clicks on Add Model` | NLP Query Template | Clicks Add Model button |

### App Creation - Drag and Drop

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 16 | `When User clicks on Create New App button` | All app features | Clicks Create New App button |
| 17 | `When User clicks on Get Started button in "{string}"` | Notebook, App Settings, Notebook DB Ops, Transformation | Clicks Get Started in Drag and Drop |
| 18 | `When User enters app name as '{string}'` | All app features | Enters app name |
| 19 | `When User clicks on Create button` | All app features | Clicks Create button |
| 20 | `When User fetch the app name` | All app features | Fetches the created app name |

### App Creation - Templates

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `When User selects "{string}" from Template List` | All template features | Selects template (Ask LLM, Landing Page, Multi Page, etc.) |
| 22 | `When User enters description as '{string}'` | All template features | Enters app description |
| 23 | `When User enters tags '{string}' and presses Enter` | All template features | Enters tags and presses Enter |

### App Navigation & Search

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 24 | `When User searches '{string}' app in the app searchbox` | Notebook, App Settings, Templates | Searches app in searchbox |
| 25 | `When User clicks on '{string}' app from the My Apps` | Notebook, App Settings, Templates | Clicks on app from My Apps list |
| 26 | `When User clicks on app Edit button` | Notebook, App Settings, Templates | Clicks app Edit button |

### App Blocks & UI Builder

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 27 | `When User clicks on Blocks if it is not selected by default` | Notebook, Custom Frame, Multi Page, Variable Guide | Clicks on Blocks tab if not selected |
| 28 | `When User drags the '{string}' block and drops it on the '{string}'` | Custom Frame, Multi Page, Variable Guide | Drags and drops a block (e.g., 'Area Chart') |
| 29 | `When User clicks on the Save App icon` | Notebook DB Ops, Custom Frame, Multi Page, Variable Guide, Visualize CSV | Clicks Save App icon |
| 30 | `When User clicks on the title as '{string}'` | Visualize CSV | Clicks on a title element |
| 31 | `When User clicks on the Block Settings option` | Landing Page, Visualize CSV | Clicks Block Settings option |
| 32 | `When User change title '{string}' with '{string}'` | Visualize CSV | Changes block title |
| 33 | `When User filles the destination URL as "{string}"` | Landing Page | Fills destination URL in block settings |
| 34 | `When User clicks on Save button of the app` | Landing Page | Clicks Save button of the app |
| 35 | `When User clicks on hyperlink text "{string}"` | Landing Page | Clicks on hyperlink text |

### Notebook Operations

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 36 | `When User clicks on Notebook` | Notebook Ops, Notebook DB Ops, Scripting, Transformation, Templates | Clicks on Notebook tab |
| 37 | `When User clicks on Create new notebook` | Notebook Ops, Notebook DB Ops, Scripting, Transformation | Clicks Create new notebook |
| 38 | `When User enters New Query name as '{string}'` | Notebook Ops, Notebook DB Ops, Scripting, Transformation | Enters notebook query name |
| 39 | `When User clicks on query Submit button` | Notebook Ops, Notebook DB Ops, Scripting, Transformation | Clicks query Submit button |
| 40 | `When User duplicates the notebook named "{string}"` | Notebook Ops | Duplicates a notebook by name |
| 41 | `When User searches for notebook named "{string}"` | Notebook Ops | Searches for notebook by name |
| 42 | `When User clicks on Query name as '{string}'` | Create/Delete/Read/Update Diabetes, NLP Query | Clicks on a specific query name in notebook |
| 43 | `When User select the '{string}' from notebook` | NLP Query | Selects a notebook query |

### Notebook Cells & Execution

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 44 | `When User mouse hover below the existing cell` | Notebook DB Ops, Transformation | Mouse hovers below existing cell to show options |
| 45 | `When User selects '{string}' from the hidden options` | Notebook DB Ops, Transformation | Selects option from hidden cell menu (e.g., 'Import Data', 'Transformation') |
| 46 | `When User selects '{string}' from the data import options` | Notebook DB Ops | Selects data import type (e.g., 'Custom Import (SQL)', 'From Data Catalog') |
| 47 | `When User selects '{string}' from the Transformation options` | Transformation, Uppercase | Selects transformation type (e.g., 'Timestamp', 'Uppercase') |
| 48 | `When User deletes the previous cell` | Notebook DB Ops, Scripting | Deletes the previous cell |
| 49 | `When User selects '{string}' database from the dropdown` | Notebook DB Ops, Transformation, Templates | Selects database from dropdown in notebook |
| 50 | `When User writes the query '{string}'` | Notebook DB Ops | Writes SQL query in the cell |
| 51 | `When User clicks on Run cell button` | Notebook DB Ops, Transformation | Clicks Run cell button |
| 52 | `When User clicks on Run cell button of database cell` | Create/Delete/Read/Update Diabetes | Clicks Run cell button for database cell |
| 53 | `When User click on Run All cell button` | Transformation, NLP Query | Clicks Run All cell button |
| 54 | `When User clicks on Run this cell and below icon` | Scripting | Clicks Run this cell and below icon |
| 55 | `When User hovers and clicks on the cell` | Scripting | Hovers and clicks on cell |
| 56 | `When User enters code as '{string}'` | Scripting | Enters code in cell (e.g., 'Hello', '1+1') |
| 57 | `When User changes the language to '{string}'` | Scripting | Changes cell language (e.g., 'Pixel') |
| 58 | `When User selects type as '{string}'` | Notebook DB Ops | Selects cell type (e.g., 'Python') |
| 59 | `When User enter the data limit as '{string}'` | Notebook DB Ops | Enters data limit |
| 60 | `When User fetch the frame id` | Notebook DB Ops, Transformation | Fetches the frame ID |
| 61 | `When User modify the Sql query "{string}"` | Create/Delete/Update Diabetes | Modifies SQL query in cell |

### Notebook Data Import

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 62 | `When User selects '{string}' from the dropdown list` | Notebook DB Ops | Selects database from dropdown list in data import |
| 63 | `When User selects all columns from database` | Notebook DB Ops | Selects all columns for import |
| 64 | `When User clicks on data Import button` | Notebook DB Ops | Clicks data Import button |

### Notebook Transformation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 65 | `When User selects the frame from the selected frame dropdown` | Transformation, Uppercase | Selects frame from dropdown |
| 66 | `When User selects the column '{string}' for transformation` | Uppercase | Selects column for transformation |
| 67 | `When User enters column name as '{string}'` | Transformation | Enters new column name for transformation |
| 68 | `When User clicks on Include time checkbox` | Transformation | Clicks Include time checkbox |

### Notebook Model Selection

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 69 | `When User select the '{string}' model for '{string}'` | NLP Query | Selects model for a notebook query |

### App Preview

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 70 | `When User clicks on Preview app button` | Ask LLM, Create/Delete/Read/Update Diabetes, NLP Query | Clicks Preview app button |
| 71 | `When User clicks on Close Preview button` | Ask LLM | Clicks Close Preview button |
| 72 | `When User close the Preview app window` | Create/Delete/Update Diabetes, NLP Query | Closes Preview app window |
| 73 | `When User clicks on '{string}' page` | NLP Query | Clicks on a specific page |

### App Preview - Template Interactions

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 74 | `When user add value "{string}" in "{string}" field` | Create/Update Diabetes | Adds value in a field (e.g., ID, AGE, GENDER, LOCATION) |
| 75 | `When user selects "{string}" from "{string}" dropdown` | Delete Diabetes | Selects value from dropdown in preview |
| 76 | `When user selects "{string}" from "{string}" Read App dropdown` | Read Diabetes | Selects value from Read App dropdown |
| 77 | `When User click on '{string}' Record button` | Create/Delete/Update Diabetes | Clicks Add/Delete/Update Record button |
| 78 | `When User enter the query for people "{string}" the age "{string}"` | NLP Query | Enters NLP query for age filter |
| 79 | `When User click on Fetch Data` | NLP Query | Clicks Fetch Data button |

### App Settings

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 80 | `When User click on Settings` | Author/Editor/Read Permission | Clicks on Settings |
| 81 | `When User Click on Members setting option` | Author/Editor Permission | Clicks on Members setting option |
| 82 | `When User Click on General setting option` | Author/Editor Permission | Clicks on General setting option |
| 83 | `When User clicks on Add Member button` | Author/Editor/Read Permission | Clicks Add Member button |
| 84 | `When User adds one user and assigns them as '{string}'` | Author/Editor/Read Permission | Adds user with role (Editor/Read) |
| 85 | `When User Search '{string}' user from Access Control` | Author/Editor Permission | Searches user in Access Control |
| 86 | `When User deletes the '{string}' user` | Author/Editor Permission | Deletes user from Access Control |
| 87 | `When User turn OFF the Private option` | Author Permission | Turns OFF Private toggle |
| 88 | `When User turn ON the Private option` | Author Permission | Turns ON Private toggle |
| 89 | `When User turn OFF the Non Discoverable option` | Author Permission | Turns OFF Non Discoverable toggle |
| 90 | `When User turn ON the Non Discoverable option` | Author Permission | Turns ON Non Discoverable toggle |

### User Login/Logout

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 91 | `When User logs out from the application` | Editor/Read Permission | Logs out from the application |
| 92 | `When User login as '{string}'` | Editor/Read Permission | Logs in as specified user |

### Page Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 93 | `When User navigates to back page` | Landing Page, Multi Page | Navigates browser back |
| 94 | `When User click on the '{string}' hyperlink should point to '{string}'` | Multi Page | Clicks hyperlink and validates URL pattern |

### Variable Guide

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 95 | `When User selects "{string}" font style` | Variable Guide | Selects font style (Arial, Roboto, Times New Roman, Georgia) |
| 96 | `When User changes font size to "{string}"` | Variable Guide | Changes font size |
| 97 | `When User can click on the '{string}' block and see the Fonts Style and Size` | Variable Guide | Clicks on block and sees font options |

---

## Then Steps

### App Creation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see '{string}' with the text '{string}'` | Notebook Ops, Notebook DB Ops, Scripting, App Settings, Transformation | Validates page/text content (e.g., 'page-1' with welcome message) |
| 2 | `Then User sees the title '{string}'` | Ask LLM, Variable Guide | Validates app/block title |
| 3 | `Then User sees the title as '{string}'` | Visualize CSV | Validates title text |
| 4 | `Then User sees the sub title as '{string}'` | Visualize CSV | Validates subtitle text |
| 5 | `Then User sees title of the block as '{string}'` | Landing Page | Validates block title |
| 6 | `Then User can see the Catalog title as '{string}'` | Notebook DB Ops, Transformation, NLP Query | Validates catalog title |

### App Template Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 7 | `Then User sees description as '{string}'` | Ask LLM | Validates description text |
| 8 | `Then User views description as '{string}'` | Custom Frame, Landing Page, NLP Query, Variable Guide | Validates description text |
| 9 | `Then User views description for the block with title '{string}' as '{string}'` | Landing Page | Validates description for a specific block title |
| 10 | `Then User sees input field with With label '{string}'` | Ask LLM | Validates input field label |
| 11 | `Then User sees submit button` | Ask LLM | Validates submit button visible |
| 12 | `Then User sees the title '{string}' in Preview App` | Ask LLM | Validates title in Preview mode |
| 13 | `Then User sees description as '{string}' in Preview App` | Ask LLM | Validates description in Preview mode |
| 14 | `Then User sees input field with With label '{string}' in Preview App` | Ask LLM | Validates input field label in Preview mode |
| 15 | `Then User sees submit button in Preview App` | Ask LLM | Validates submit button in Preview mode |

### Landing Page Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 16 | `Then User sees the hyperlink with text "{string}" should point to the url "{string}"` | Landing Page | Validates hyperlink text and URL |
| 17 | `Then User clicks on the hyperlink with text "{string}" with title '{string}' should point to "{string}"` | Landing Page | Clicks hyperlink and validates URL for specific block |
| 18 | `Then User sees the URL as "{string}"` | Landing Page | Validates current URL |

### Multi Page Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `Then User see the '{string}'` | Custom Frame, Multi Page, NLP Query, Variable Guide | Validates page/block element visible |
| 20 | `Then User see the '{string}' block` | Custom Frame, Multi Page, Variable Guide | Validates block is visible |
| 21 | `Then User see the '{string}' hyperlink` | Multi Page | Validates hyperlink is visible |
| 22 | `Then User see the '{string}' title after clicking {string} hyperlink` | Multi Page | Validates title after clicking hyperlink |

### Variable Guide Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 23 | `Then User can see the '{string}' block` | Variable Guide | Validates block is visible |
| 24 | `Then The font style should be "{string}" and size should be "{string}"` | Variable Guide | Validates font style and size |
| 25 | `Then User can see the '{string}' block` | Variable Guide | Validates named block visible (Engine Variables, Data Structure Variables, Block Variables, Notebook Variables) |

### Notebook Operations Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 26 | `Then User see "{string}" notebook present in the notebook list` | Notebook Ops | Validates notebook present in list |
| 27 | `Then User deletes the notebook named "{string}"` | Notebook Ops | Deletes a notebook by name |
| 28 | `Then User Sees Python as the default language` | Scripting | Validates Python is default language |

### Notebook Output Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 29 | `Then User sees the output of the executed query where '{string}' is '{string}'` | Notebook DB Ops, Create/Update Diabetes | Validates query output column value |
| 30 | `Then User sees the output of the executed query with empty result` | Delete Diabetes | Validates query output is empty |
| 31 | `Then User can see Pixel output as '{string}'` | Scripting | Validates Pixel output |
| 32 | `Then User can see Python output as '{string}'` | Scripting | Validates Python output |
| 33 | `Then User can see header names as '{string}'` | Notebook DB Ops, Transformation, Uppercase | Validates header names in output |
| 34 | `Then User can see total '{string}' rows` | Notebook DB Ops | Validates total row count |
| 35 | `Then User can see the '{string}' column have unique values` | Notebook DB Ops | Validates column has unique values |
| 36 | `Then User can see name as frame id in JSON` | Notebook DB Ops | Validates frame id in JSON output |
| 37 | `Then User can see type as '{string}' for '{string}' in JSON` | Notebook DB Ops | Validates type in JSON output |
| 38 | `Then User can see '{string}' columns under the fields column` | Notebook DB Ops | Validates available columns for import |
| 39 | `Then User can see the output for database cell` | Create/Delete/Read/Update Diabetes | Validates database cell has output |

### Transformation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 40 | `Then User can see '{string}' column values as todays date along with current time` | Transformation | Validates timestamp with time |
| 41 | `Then User can see '{string}' column values as todays date along with '{string}' as timestamp` | Transformation | Validates timestamp without time |
| 42 | `Then User verifies the transformed data for '{string}' column is in uppercase format` | Uppercase | Validates uppercase transformation |

### Database Metadata Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 43 | `Then User sees the table in the metadata tab` | Notebook DB Ops, Transformation, Delete Diabetes | Validates table in metadata tab |

### NLP Query Template Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 44 | `Then Results should contain only people with age "{string}" "{string}"` | NLP Query | Validates NLP query results by age filter (above/below) |

### Template CRUD Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 45 | `Then User sees the success message "{string}"` | Create/Delete/Update Diabetes | Validates success message (e.g., "true") |
| 46 | `Then user sees the record with Unique ID "{string}"` | Read Diabetes | Validates record displayed by Unique ID |

### App Settings Validation - Author

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 47 | `Then '{string}' user can '{string}' Settings` | Author/Editor Permission | Validates user can view/not view Settings |
| 48 | `Then '{string}' user can '{string}' Members` | Author/Editor/Read Permission | Validates user can view/not view Members |
| 49 | `Then '{string}' user can '{string}' Apps` | Author/Editor/Read Permission | Validates user can view/not view Apps |
| 50 | `Then '{string}' user can '{string}' General` | Author/Editor/Read Permission | Validates user can view/not view General |
| 51 | `Then '{string}' user can see private toggle button as '{string}'` | Author/Editor Permission | Validates Private toggle button state |
| 52 | `Then '{string}' user can see Non-Discoverable toggle button as '{string}'` | Author/Editor Permission | Validates Non-Discoverable toggle state |
| 53 | `Then '{string}' user can '{string}' Delete catalog option` | Author/Editor Permission | Validates user can view Delete catalog option |
| 54 | `Then '{string}' user '{string}' Delete Catalog` | Author Permission | Validates user can/cannot Delete Catalog |
| 55 | `Then Editor user not able to Delete Catalog` | Editor Permission | Validates Editor cannot delete catalog |

### App Settings Validation - Read

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 56 | `Then '{string}' user Edit option should be '{string}'` | Editor/Read Permission | Validates Edit button state (Enable/Disable) |
| 57 | `Then '{string}' user can '{string}' Settings` | Read Permission | Validates Read user cannot view Settings |
| 58 | `Then '{string}' user can 'not see' private toggle button` | Read Permission | Validates Read user cannot see Private toggle |
| 59 | `Then '{string}' user can 'not see' Non-Discoverable toggle button` | Read Permission | Validates Read user cannot see Non-Discoverable toggle |

---

## Feature Files Summary

| # | Feature File | Scenarios | Description |
|---|-------------|-----------|-------------|
| 1 | Notebook Validate Database Operations | 2 | Import DB query (SQL), Import Data from catalog |
| 2 | Notebook Operations (CRUD) | 3 | Create, duplicate, delete, search notebooks |
| 3 | Notebook Uppercase Transformation | 1 | Validate Uppercase transformation function |
| 4 | Notebook Scripting | 2 | Pixel output, Python output in notebook |
| 5 | Validate Transformation (Timestamp) | 1 | Validate Timestamp transformation with/without time |
| 6 | App Setting - Author Permission | 5 | Author: view settings, members, general, private/non-discoverable toggle, delete app |
| 7 | App Setting - Editor Permission | 5 | Editor: view settings, members, general, add/delete members, delete model |
| 8 | App Setting - Read Permission | 1 | Read: edit disabled, no settings/members/general/delete access |
| 9 | Ask LLM Template | 1 | Create Ask LLM app, validate preview |
| 10 | Create Diabetes Record Template | 3 | Create app, validate data filling, validate with notebook |
| 11 | Custom Frame to Visualization Template | 1 | Create app, drag and drop blocks |
| 12 | Delete Diabetes Record Template | 2 | Create app, delete existing record |
| 13 | Landing Page Template | 1 | Create Landing Page, validate resources, hyperlinks, block settings |
| 14 | Multi Page Template | 1 | Create Multi Page app, validate navigation between pages |
| 15 | NLP Query to Grid Template | 1 | Create NLP app, run queries, validate age filters |
| 16 | Read Diabetes Record Template | 2 | Create app, read existing record |
| 17 | Update Diabetes Record Template | 2 | Create app, update existing record |
| 18 | Variable Guide Template | 1 | Create app, validate blocks, font style/size changes |
| 19 | Visualize CSV Template | 1 | Create app, edit block title |
| 20 | Available Models (for App) | – | Referenced in NLP Query background |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 3 |
| When Steps | 97 |
| Then Steps | 59 |
| **Total** | **159** |

---