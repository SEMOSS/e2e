# Home BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/home/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the home-related feature files located under `src/test/resources/Features/home/`. It covers the Build page validation (titles, buttons, Browse Templates), System Apps verification (BI, Terminal), and the global home search functionality for all catalog types (Apps, Model, Function, Vector, Database, Storage) with filter/unfilter and "Search All" scenarios.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | Validate Build Page, Search App and Catalogs | Validates user is on the Home page |
| 2 | `Given User opens Main Menu` | Validate Build Page, Search App and Catalogs | Opens the main navigation menu |

---

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
| 4 | `When User clicks on Open App Library` | Validate Build Page, Search App and Catalogs | Opens the App Library from Main Menu |
| 5 | `When User click on System Apps` | Validate Build Page | Clicks System Apps section in App Library |

### App Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 6 | `When User clicks on Create New App button` | Search App and Catalogs | Clicks Create New App button |
| 7 | `When User clicks on Get Started button in "Drag and Drop"` | Search App and Catalogs | Clicks Get Started in Drag and Drop section |
| 8 | `When User enters '{string}' as app name` | Search App and Catalogs | Enters app name |
| 9 | `When User enters description as '{string}'` | Search App and Catalogs | Enters app description |
| 10 | `When User enters tags '{string}' and presses Enter` | Search App and Catalogs | Enters tags and presses Enter |
| 11 | `When User clicks on Create button` | Search App and Catalogs | Clicks Create button to create app |
| 12 | `When User fetch the app name` | Search App and Catalogs | Fetches the created app name |

### Model Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 13 | `When User clicks on Open Model` | Search App and Catalogs | Opens the Model catalog page |
| 14 | `When User clicks on Add Model` | Search App and Catalogs | Clicks Add Model button |
| 15 | `When User selects '{string}' type` | Search App and Catalogs | Selects model type (e.g., 'OpenAI') |
| 16 | `When User selects '{string}'` | Search App and Catalogs | Selects model variant (e.g., 'GPT 3.5 Turbo') |
| 17 | `When User enters '{string}' as Catalog name` | Search App and Catalogs | Enters catalog name |
| 18 | `When User enters Open AI Key as '{string}'` | Search App and Catalogs | Enters OpenAI API key |
| 19 | `When User clicks on Create Model button` | Search App and Catalogs | Clicks Create Model button |
| 20 | `When User clicks on Copy Catalog ID` | Search App and Catalogs | Copies the catalog ID |

### Catalog Edit (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `When User clicks on Edit button` | Search App and Catalogs | Clicks Edit button |
| 22 | `When User add Tags '{string}' and presses Enter` | Search App and Catalogs | Adds tags and presses Enter |
| 23 | `When User clicks on Submit button` | Search App and Catalogs | Clicks Submit button |

### Vector Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 24 | `When User clicks on Open Vector` | Search App and Catalogs | Opens the Vector catalog page |
| 25 | `When User clicks on Add Vector button` | Search App and Catalogs | Clicks Add Vector button |
| 26 | `When User selects '{string}' connection` | Search App and Catalogs | Selects vector connection type (e.g., 'FAISS') |
| 27 | `When User selects '{string}' from Embedder field` | Search App and Catalogs | Selects embedder model |
| 28 | `When User selects '{string}' from Chunking Strategy field` | Search App and Catalogs | Selects chunking strategy (e.g., 'Token') |
| 29 | `When User enters value of Content Length as '{string}'` | Search App and Catalogs | Enters content length value |
| 30 | `When User enters value of Content Overlap as '{string}'` | Search App and Catalogs | Enters content overlap value |
| 31 | `When User clicks on Create Vector button` | Search App and Catalogs | Clicks Create Vector button |

### Function Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 32 | `When User clicks on Open Function` | Search App and Catalogs | Opens the Function catalog page |
| 33 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | Search App and Catalogs | Checks if catalog exists and deletes it |
| 34 | `When User clicks on Add Function` | Search App and Catalogs | Clicks Add Function button |
| 35 | `When User clicks on file upload icon` | Search App and Catalogs | Clicks file upload icon |
| 36 | `When User uploads the file '{string}'` | Search App and Catalogs | Uploads a file |
| 37 | `When User clicks on 'Upload' button to create catalog` | Search App and Catalogs | Clicks Upload button |

### Database Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 38 | `When User clicks on Open Database` | Search App and Catalogs | Opens the Database catalog page |
| 39 | `When User clicks on Add Database` | Search App and Catalogs | Clicks Add Database button |

### Storage Creation (for Search)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 40 | `When User clicks on Open Storage` | Search App and Catalogs | Opens the Storage catalog page |
| 41 | `When User clicks on Add Storage button` | Search App and Catalogs | Clicks Add Storage button |
| 42 | `When User selects '{string}' storage` | Search App and Catalogs | Selects storage type (e.g., 'Amazon S3') |
| 43 | `When User enters Region as '{string}'` | Search App and Catalogs | Enters region value |
| 44 | `When User enters Bucket as '{string}'` | Search App and Catalogs | Enters bucket value |
| 45 | `When User enters Access Key as '{string}'` | Search App and Catalogs | Enters access key |
| 46 | `When User enters Secret Key as '{string}'` | Search App and Catalogs | Enters secret key |
| 47 | `When User clicks on Connect button to create storage` | Search App and Catalogs | Clicks Connect button to create storage |

### Home Search

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 48 | `When User search the '{string}' in the home search box` | Search App and Catalogs | Searches for a catalog/app in the home search box |
| 49 | `When User clicks on the '{string}' option to filter the results` | Search App and Catalogs | Clicks filter option (Apps, Model, Function, Vector, Database, Storage) |

---

## Then Steps

### Build Page Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User able to see the following Titles:` | Validate Build Page | Validates all titles on the Build page using data table |
| 2 | `Then User able to see the following Buttons:` | Validate Build Page | Validates all buttons on the Build page using data table (drag, code, agent) |
| 3 | `Then User able to see the "Browse Templates" button` | Validate Build Page | Validates Browse Templates button is visible |

### System Apps Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 4 | `Then User can see '{string}' app in the System Apps section` | Validate Build Page | Validates app is visible in System Apps (e.g., 'BI', 'Terminal') |

### Home Search Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `Then User can see '{string}' in the '{string}' filter results` | Search App and Catalogs | Validates catalog/app is visible in filtered search results |
| 6 | `Then User clicks on the '{string}' option to unfilter the results` | Search App and Catalogs | Clicks filter option again to remove filter |
| 7 | `Then User close the search popup` | Search App and Catalogs | Closes the search popup |

---

## Build Page Content

### Titles Displayed on Build Page

| # | Title |
|---|-------|
| 1 | Empower your ideas with SEMOSS |
| 2 | Experiment in our Playground™ |
| 3 | Simplify tasks with AI Conductor |
| 4 | Get started with our tools |
| 5 | Drag and drop blocks |
| 6 | Develop in code |
| 7 | Construct an agent |
| 8 | Try these fan favorites |
| 9 | BI |
| 10 | Terminal |

### Buttons Displayed on Build Page

| # | Button |
|---|--------|
| 1 | drag |
| 2 | code |
| 3 | agent |
| 4 | Browse Templates |

### System Apps

| # | App Name |
|---|----------|
| 1 | BI |
| 2 | Terminal |

---

## Home Search - Supported Catalog Types

| # | Catalog Type | Search Term (Test Data) | Filter Option |
|---|-------------|------------------------|---------------|
| 1 | Apps | `App` | Apps |
| 2 | Model | `Model` | Model |
| 3 | Function | `weatherFunctionTest` | Function |
| 4 | Vector | `Vector` | Vector |
| 5 | Database | `TestDatabase` | Database |
| 6 | Storage | `Storage` | Storage |
| 7 | All (combined) | Various | All |

---

## Test Data Summary

### App Creation (for Search)

| Field | Value |
|-------|-------|
| App Name | `App` |
| Description | `Created by automation script` |
| Tags | `Test1, Test2` |
| Method | Drag and Drop |

### Model Creation (for Search)

| Field | Value |
|-------|-------|
| Type | `OpenAI` |
| Variant | `GPT 3.5 Turbo` |
| Catalog Name | `Model` |
| API Key | `Test@1234` |

### Vector Creation (for Search)

| Field | Value |
|-------|-------|
| Connection | `FAISS` |
| Catalog Name | `Vector` |
| Embedder | `Model` |
| Chunking Strategy | `Token` |
| Content Length | `512` |
| Content Overlap | `20` |

### Function Creation (for Search)

| File | Catalog Name |
|------|-------------|
| `Function/weatherFunctionTest.zip` | `WeatherFunctionTest` |

### Database Creation (for Search)

| File | Catalog Name |
|------|-------------|
| `Database/TestDatabase.zip` | `TestDatabase` |

### Storage Creation (for Search)

| Field | Value |
|-------|-------|
| Type | `Amazon S3` |
| Catalog Name | `Storage` |
| Region | `India` |
| Bucket | `BucketTest` |
| Access Key | `Test123` |
| Secret Key | `Test123` |

### Search All - Scenario Outline Examples

| Catalog Name | Option |
|-------------|--------|
| `App` | All |
| `Model` | All |
| `WeatherFunctionTest` | All |
| `Vector` | All |
| `TestDatabase` | All |
| `Storage` | All |

---

## Feature Files Summary

| # | Feature File | Tags | Scenarios | Description |
|---|-------------|------|-----------|-------------|
| 1 | Validate the Build Page | `@LoginWithAdmin @Regression` | 2 Scenarios | Validate Build page titles and buttons; Verify BI and Terminal System Apps |
| 2 | Search App and Catalogs | `@LoginWithAdmin @Regression` (varies per scenario) | 9 Scenarios (6 individual searches + 1 Scenario Outline with 6 examples + 1 cleanup + 1 App search) | Search App, Model, Function, Vector, Database, Storage from home search box with filter/unfilter; Search All combined; Delete created resources |

---

## Scenario Details

### Feature: Validate the Build Page

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate Build page title and buttons | `@LoginWithAdmin @Regression` | Navigates to Build page → validates 10 titles, 3 buttons (drag, code, agent), and Browse Templates button |
| 2 | Verify BI and Terminal apps are displayed under System apps | `@LoginWithAdmin @Regression` | Opens App Library → System Apps → validates BI and Terminal apps are visible |

### Feature: Search App and Catalogs

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Search App | `@LoginWithAdmin @Regression` | Creates Drag-and-Drop app → searches from home → filters by Apps → validates result |
| 2 | Search Model | `@DeleteTestCatalog @Regression` | Creates OpenAI GPT 3.5 Turbo model → searches from home → filters by Model → validates result |
| 3 | Search Function | `@Regression` | Creates function from ZIP upload → searches from home → filters by Function → validates result |
| 4 | Search Vector | `@Regression` | Creates model + FAISS vector → searches from home → filters by Vector → validates result |
| 5 | Search Database | `@Regression` | Creates database from ZIP upload → searches from home → filters by Database → validates result |
| 6 | Search Storage | `@Regression` | Creates Amazon S3 storage → searches from home → filters by Storage → validates result |
| 7 | Search All (Scenario Outline, 6 examples) | `@Regression` | Searches for each previously created catalog → validates visible in All filter results |
| 8 | Delete created resources | `@DeleteTestCatalog @DeleteCreatedTestApp @Regression` | Cleanup scenario to delete all test resources |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 2 |
| When Steps | 49 |
| Then Steps | 7 |
| **Total** | **58** |

---