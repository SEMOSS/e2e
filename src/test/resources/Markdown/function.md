# Function BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/function/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Function Types Available](#function-types-available)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the function-related feature files located under `src/test/resources/Features/function/`. It covers function creation (REST, AWS Image Text Extraction, AWS Polly, AWS Transcribe, AWS Comprehend, Azure Document Intelligence, Azure Speech To Text, Google Speech To Text, Google OCR), function form field validation for all types, ZIP upload creation, usage examples, function overview, access control, catalog filtering, discoverable functions, and form submission validation.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | All function features | Opens the main navigation menu |
| 2 | `Given User can see the Catalog title as '{string}'` | Add Function From Zip, View Function Details | Validates catalog title is displayed |

---

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
| 4 | `When User clicks on Add Function button` | View Add Function Page | Clicks Add Function button (alternate step) |
| 5 | `When User selects function '{string}'` | Validate All Function Types, Add Function | Selects function type (e.g., 'REST', 'AWS Polly') |

### Function Creation - REST Form Fields

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 6 | `When User enters Catalog name '{string}'` | Add Function | Enters function catalog name |
| 7 | `When User enters Url as '{string}'` | Add Function | Enters URL value |
| 8 | `When User selects HTTP method as '{string}'` | Add Function | Selects HTTP method (GET, HEAD, etc.) |
| 9 | `When User selects Post body message as '{string}'` | Add Function | Selects POST body message type (json, x-www-form-urlencoded) |
| 10 | `When User enters Headers as '{string}'` | Add Function | Enters HTTP headers |
| 11 | `When User enters Function parameters as '{string}'` | Add Function | Enters function parameters JSON |
| 12 | `When User enters Function required parameters as '{string}'` | Add Function | Enters function required parameters |
| 13 | `When User enters Function name as '{string}'` | Add Function | Enters function name (metadata) |
| 14 | `When User enters Function description as '{string}'` | Add Function | Enters function description (metadata) |

### Function Creation - Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 15 | `When User sees astrisk mark on the required fields '{string}'` | Add Function | Validates asterisk on required fields |
| 16 | `When User fills the function creation form with:` | Validate All Function Types | Fills function creation form with data table |

### Function Creation - Connect/Submit

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `When User sees Connect button` | Add Function | Validates Connect button is visible |
| 18 | `When User clicks on Connect button to create function` | Add Function | Clicks Connect button to create function |

### Function Creation - ZIP Upload

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `When User checks if 'Function' catalog created and Deletes the '{string}'` | Add Function From Zip, View Existing Functions, View Function Details | Checks if function catalog exists and deletes it |
| 20 | `When User clicks on file upload icon` | Add Function From Zip, View Existing Functions, View Function Details | Clicks file upload icon |
| 21 | `When User uploads the file '{string}'` | Validate All Function Types, Add Function From Zip, View Existing Functions, View Function Details | Uploads a file |
| 22 | `When User clicks on 'Upload' button to create catalog` | Add Function From Zip, View Existing Functions, View Function Details | Clicks Upload button to create catalog |
| 23 | `When User clicks on Copy Catalog ID` | Add Function, Add Function From Zip, View Existing Functions, View Function Details | Copies the catalog ID |

### Function Catalog Edit

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 24 | `When User clicks on Edit button` | View Existing Functions | Clicks Edit button |
| 25 | `When User add Tags '{string}' and presses Enter` | View Existing Functions | Adds tags |
| 26 | `When User enters the Domains as '{string}'` | View Existing Functions | Enters domain values |
| 27 | `When User selects '{string}' from the Data Classification dropdown` | View Existing Functions | Selects data classification |
| 28 | `When User selects '{string}' from the Data Restrictions dropdown` | View Existing Functions | Selects data restrictions |
| 29 | `When User clicks on Submit button` | View Existing Functions | Clicks Submit button |

### Function Usage Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 30 | `When User selects 'Usage' tab` | View Function Details | Clicks Usage tab |
| 31 | `When User can see '{string}' usage instructions section` | View Function Details | Validates usage instructions section visible |

### Function Access Control

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 32 | `When User clicks on Access Control Tab` | Add Function From Zip, View Existing Functions | Clicks Access Control tab |
| 33 | `When User clicks on Delete button` | Add Function From Zip | Clicks Delete button for function |
| 34 | `When User clicks Make '{string}' Discoverable button` | View Existing Functions | Clicks Make Function Discoverable button |

### Function Catalog Search & Selection

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 35 | `When User clicks on the function name '{string}' in the function catalog` | View Existing Functions | Clicks on function name in catalog |
| 36 | `When User clicks on Discoverable Functions button` | View Existing Functions | Clicks Discoverable Functions button |

### User Login/Logout

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 37 | `When User logs out from the application` | View Existing Functions | Logs out from the application |
| 38 | `When User login as '{string}'` | View Existing Functions | Logs in as specified user |

---

## Then Steps

### Function Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see function creation form with following sections with fields:` | Validate All Function Types | Validates function form sections and fields with data table |
| 2 | `Then User can see function creation form with following mandatory fields` | Validate All Function Types | Validates function mandatory fields with data table |
| 3 | `Then User can see 'Connect' button becomes enabled to create function` | Validate All Function Types | Validates Connect button becomes enabled |
| 4 | `Then User sees the Create function button is disabled` | Add Function | Validates Create function button is disabled |

### Function Creation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `Then User sees success toast message '{string}'` | Add Function, Add Function From Zip, View Existing Functions, View Function Details | Validates success toast message |
| 6 | `Then User can see the Catalog title as '{string}'` | Add Function From Zip, View Existing Functions, View Function Details | Validates catalog title displayed |

### Function Deletion Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 7 | `Then User sees deleted function success toast message '{string}'` | Add Function From Zip | Validates function deletion success toast |

### Function Usage Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 8 | `Then User can see 'How to use in Javascript' usage instructions section` | View Function Details | Validates Javascript usage instructions |
| 9 | `Then User can see 'How to use in Python' usage instructions section` | View Function Details | Validates Python usage instructions |
| 10 | `Then User can see 'How to use in Java' usage instructions section` | View Function Details | Validates Java usage instructions |

### Function Catalog Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 11 | `Then User sees the function name '{string}' in the function catalog` | View Existing Functions | Validates function name in catalog list |
| 12 | `Then User applies each filter and validate '{string}' catalog is visible on the '{string}' catalog page` | View Existing Functions | Validates filter functionality with data table |

### View Add Function Options Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 13 | `Then User should see Search bar to filter function options` | View Add Function Page | Validates search bar visible on add function page |
| 14 | `Then User should see the following '{string}' options with valid icons on the Connect to Function page` | View Add Function Page | Validates function options with groups using data table |

### User Login

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 15 | `Then User login as '{string}'` | View Existing Functions | Logs in as specified user |

---

## Function Types Available

### Connection Types - 3 Section Forms

| # | Function | General Fields | Credentials Fields | Settings Fields | Mandatory Fields |
|---|----------|---------------|-------------------|----------------|-----------------|
| 1 | AWS Image Text Extraction | Function Type, Catalog Name | Access Key, Secret Key | Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters |
| 2 | AWS Polly | Function Type, Catalog Name | Access Key, Secret Key | Region, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Access Key, Secret Key, Region, Function Name (metadata), Function Description (metadata), Function Required Parameters |
| 3 | AWS Transcribe | Function Type, Catalog Name | Access Key, Secret Key | Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters |
| 4 | AWS Comprehend | Function Type, Catalog Name | Access Key, Secret Key | Region, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Access Key, Secret Key, Region, Function Name (metadata), Function Description (metadata), Function Required Parameters |
| 5 | Azure Document Intelligence | Function Type, Catalog Name | API Key, URL | *(none)* | Function Type, Catalog Name, URL, API Key |
| 6 | Azure Speech To Text | Function Type, Catalog Name | Speech Key | Speech region, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Speech Key, Speech region, Function Name (metadata), Function Description (metadata), Function Required Parameters |
| 7 | REST | Function Type, Catalog Name | URL, Http Method | POST Message Body Type, Http Headers, Function Parameters, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, URL, Http Method, POST Message Body Type, Function Parameters, Function Name (metadata), Function Description (metadata), Function Required Parameters |

### Connection Types - Requiring File Upload

| # | Function | General Fields | Credentials Fields | Settings Fields | Mandatory Fields |
|---|----------|---------------|-------------------|----------------|-----------------|
| 8 | Google Speech To Text | Function Type, Catalog Name | Google Bucket Engine Id, Function Name (metadata), Function Description (metadata) | Upload Service Account File, Function Required Parameters | Function Type, Catalog Name, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters |
| 9 | Google OCR | Function Type, Catalog Name | Project Id | Processor Id, Region, Upload Service Account File, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Project Id, Processor Id, Region, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters |

### All Available Function Options

| Group | Function Options |
|-------|-----------------|
| Functions | AWS Image Text Extraction, AWS Polly, AWS Transcribe, AWS Comprehend, Azure Document Intelligence, Azure Speech To Text, Google Speech To Text, Google OCR, REST |

---

## Commented-Out Scenarios (Known Bugs / Not Yet Active)

The following scenarios are present in the feature files but are **commented out** due to known bugs or pending implementation:

| # | Scenario | Feature File | Reason |
|---|---------|-------------|--------|
| 1 | Validate Change access popup | Add Function From Zip | Commented out — no bug reference noted |
| 2 | Validate change access request | Add Function From Zip | Commented out — no bug reference noted |
| 3 | View overview details in "Overview" tab for selected Function | View Function Details | Bug: [SEMOSS/community#587](https://github.com/SEMOSS/community/issues/587) |

---

## Feature Files Summary

| # | Feature File | Scenarios | Description |
|---|-------------|-----------|-------------|
| 1 | Validate All Function Types | 2 Scenario Outlines (7 standard examples + 2 file upload examples) | Validate form sections, fields, mandatory fields, and Connect button enablement for all 9 function types; 2 requiring file upload (Google Speech To Text, Google OCR) |
| 2 | Add Function (REST) | 2 Scenario Outlines (1 successful creation + 1 missing fields validation) | Create REST function with all required fields; validate Create button disabled when URL is missing |
| 3 | Add Function From Zip | 1 Scenario Outline (Delete Function) + 2 commented-out scenarios | Create function from ZIP file, delete function; commented-out Change Access popup and request scenarios |
| 4 | View Add Function Page | 1 Scenario | Verify available function options with icons (9 options) |
| 5 | View Existing Functions on Catalog Page | 2 Scenarios | Create function from ZIP, edit tags/domains/classification/restrictions, validate My Functions filter, validate Discoverable Functions filter |
| 6 | View Function Details | 1 Scenario + 1 commented-out scenario | Validate Usage tab (Javascript, Python, Java); commented-out Overview tab scenario due to bug |

---

## Test Data Summary

### REST Function Test Data

| Field | Value |
|-------|-------|
| Catalog Name | `TestFunction{Timestamp}` |
| URL | `https://api.api-ninjas.com/v1/weather` |
| HTTP Method | `GET` |
| POST Body Message Type | `json` |
| Headers | `{"X-Api-Key": "myKey"}` |
| Function Parameters | `[{"parameterName":"lat","parameterType":"String","parameterDescription":"The lat of the location"},{"parameterName":"lon","parameterType":"String","parameterDescription":"lon of the location"}]` |
| Required Parameters | `["lat", "lon"]` |
| Function Name | `WeatherFunction` |
| Function Description | `a function to call weather based on lat and long` |

### ZIP Upload Test Data

| File | Catalog Name |
|------|-------------|
| `Function/weatherFunctionTest.zip` | `WeatherFunctionTest` |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 2 |
| When Steps | 38 |
| Then Steps | 15 |
| **Total** | **55** |

---