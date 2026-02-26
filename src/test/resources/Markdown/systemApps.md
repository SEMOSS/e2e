# System Apps BDD Steps Documentation

> **Last Updated:** February 26, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/systemApps/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted exclusively from the system apps–related feature files located under `src/test/resources/Features/systemApps/`. It covers three features:

1. **Create Database and Insight to BI System App – CSV** — Database creation via CSV import and Insight creation
2. **Create Database and Insight to BI System App – Excel** — Database creation via Excel import and Insight creation
3. **Terminal** — Run Pixel and Python commands in the Terminal system app

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | BI CSV, BI Excel, Terminal | Validates user is on the Home page |
| 2 | `Given User can see database created success toast message as 'Success'` | BI CSV | Validates the database creation success toast message (also used as precondition for Insight scenario) |
| 3 | `Given User is on Terminal page` | Terminal | Validates user is on the Terminal page |

---

## When Steps

### Navigation & App Library

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | BI CSV, BI Excel, Terminal | Opens the main navigation menu |
| 2 | `When User clicks on Open App Library` | BI CSV, BI Excel, Terminal | Opens the App Library page |
| 3 | `When User clicks on Create New App button` | BI CSV, BI Excel | Clicks Create New App button in App Library |
| 4 | `When User clicks on Get Started button in "Drag and Drop"` | BI CSV, BI Excel | Clicks Get Started in the Drag and Drop app template |
| 5 | `When User enters app name as 'Test app'` | BI CSV, BI Excel | Enters the app name |
| 6 | `When User enters description as 'Created by automation script'` | BI CSV, BI Excel | Enters the app description |
| 7 | `When User enters tags 'Test1, Test2' and presses Enter` | BI CSV, BI Excel | Enters tags for the app |
| 8 | `When User clicks on Create button` | BI CSV, BI Excel | Clicks Create button to create the app |
| 9 | `When User clicks on System app` | BI CSV, BI Excel, Terminal | Clicks on the System app section in the App Library |
| 10 | `When User clicks on BI` | BI CSV, BI Excel | Clicks on the BI system app card |

### BI System App – Common

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 11 | `When User clicks on Welcome popup close option` | BI CSV, BI Excel | Closes the Welcome popup in the BI system app |
| 12 | `When User clicks on Catalog option` | BI CSV, BI Excel | Clicks on the Catalog option in BI |
| 13 | `When User clicks on Add Database button` | BI CSV, BI Excel | Clicks Add Database button in Catalog |
| 14 | `When User clicks on Import button` | BI CSV, BI Excel | Clicks the Import button to import data into the database |
| 15 | `When User clicks on Add option` | BI CSV, BI Excel | Clicks Add option to add data source for Insight |
| 16 | `When User clicks on Add All button from Available Columns section` | BI CSV, BI Excel | Clicks Add All to select all available columns |
| 17 | `When User clicks on Import button from Selected Columns section` | BI CSV, BI Excel | Clicks Import button from the Selected Columns section |
| 18 | `When User mouse hover on database frame and click on Visualize this data option` | BI CSV, BI Excel | Hovers on database frame and clicks Visualize this data |
| 19 | `When User clicks on Save button` | BI CSV, BI Excel | Clicks Save button to save the Insight |
| 20 | `When User enters 'Test Automation' as the insight name, selects the 'Test App' project, and clicks the Save button` | BI CSV, BI Excel | Enters Insight name, selects project, and saves |

### BI System App – CSV Specific

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `When User enter the database name as 'DB created from CSV'` | BI CSV | Enters the database name for CSV import |
| 22 | `When User uploads CSV file` | BI CSV | Uploads the CSV file |
| 23 | `When User clicks on Next button` | BI CSV | Clicks Next button after CSV upload |
| 24 | `When User search 'DB created from CSV' database and select` | BI CSV | Searches and selects the CSV database for Insight creation |

### BI System App – Excel Specific

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 25 | `When User select the Excel option` | BI Excel | Selects the Excel option for data import |
| 26 | `When User enter the database name as 'DB created from Excel'` | BI Excel | Enters the database name for Excel import |
| 27 | `When User upload Excel file and clicks on Next button` | BI Excel | Uploads the Excel file and clicks Next |
| 28 | `When User search 'DB created from Excel' database and select` | BI Excel | Searches and selects the Excel database for Insight creation |

### Terminal

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 29 | `When User clicks on Terminal card` | Terminal | Clicks on the Terminal system app card |
| 30 | `When User run pixel command 'Hello'` | Terminal | Runs a Pixel command in the Terminal |
| 31 | `When User change the language to 'Python'` | Terminal | Changes the Terminal language to Python |
| 32 | `When User run python command '1+1'` | Terminal | Runs a Python command in the Terminal |

---

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see database created success toast message as 'Success'` | BI CSV | Validates database creation success toast message |
| 2 | `Then User can see Insight created toast message as 'Successfully saved insight(s)'` | BI CSV, BI Excel | Validates Insight creation success toast message |
| 3 | `Then User sees 'Pixel' output 'Hello'` | Terminal | Validates Pixel command output |
| 4 | `Then User sees 'Python' output '2'` | Terminal | Validates Python command output |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 3 |
| When Steps | 32 |
| Then Steps | 4 |
| **Total** | **39** |

---

## Feature Files Summary

| # | Feature File | Tags | Scenarios | Status | Description |
|---|-------------|------|-----------|--------|-------------|
| 1 | Create Database and Insight to BI System App – CSV | `@Regression` | 1 Background + 1 Scenario | ✅ Active | CSV import → Database creation → Insight creation |
| 2 | Create Database and Insight to BI System App – Excel | `@Regression` | 1 Background + 1 Scenario | ✅ Active (partial toast validation commented) | Excel import → Database creation → Insight creation |
| 3 | Terminal | `@LoginWithAdmin @Regression @BLOCKED` | 1 Background + 2 Scenarios | 🚫 Blocked | Terminal not accessible in local environment |

---

## Scenario Details

### Feature: Create Database and Insight to BI System App – CSV

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Create an Insight from a database created by importing a CSV file | `@Regression` | Background creates app + imports CSV database → Scenario adds data source → selects all columns → imports → visualizes → saves Insight → validates toast |

#### Background Flow

```gherkin
Given  User is on Home page
 When User opens Main Menu
  And User clicks on Open App Library
  And User clicks on Create New App button
  And User clicks on Get Started button in "Drag and Drop"
  And User enters app name as 'Test app'
  And User enters description as 'Created by automation script'
  And User enters tags 'Test1, Test2' and presses Enter
  And User clicks on Create button
  And  User is on Home page
  And User opens Main Menu
  And User clicks on Open App Library
  And User clicks on System app
  And User clicks on BI
  And User clicks on Welcome popup close option
  And User clicks on Catalog option
  And User clicks on Add Database button
  And User enter the database name as 'DB created from CSV'
  And User uploads CSV file
  And User clicks on Next button
  And User clicks on Import button
 Then User can see database created success toast message as 'Success'
```

#### Scenario Flow

```gherkin
Given User can see database created success toast message as 'Success'
  And User clicks on Add option
  And User search 'DB created from CSV' database and select
  And User clicks on Add All button from Available Columns section
  And User clicks on Import button from Selected Columns section
  And User mouse hover on database frame and click on Visualize this data option
  And User clicks on Save button
  And User enters 'Test Automation' as the insight name, selects the 'Test App' project, and clicks the Save button
 Then User can see Insight created toast message as 'Successfully saved insight(s)'
```

---

### Feature: Create Database and Insight to BI System App – Excel

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Create an Insight from a database created by importing an Excel file | `@Regression` | Background creates app + imports Excel database → Scenario adds data source → selects all columns → imports → visualizes → saves Insight → validates toast |

#### Background Flow

```gherkin
Given  User is on Home page
 When User opens Main Menu
  And User clicks on Open App Library
  And User clicks on Create New App button
  And User clicks on Get Started button in "Drag and Drop"
  And User enters app name as 'Test app'
  And User enters description as 'Created by automation script'
  And User enters tags 'Test1, Test2' and presses Enter
  And User clicks on Create button
  And  User is on Home page
  And User opens Main Menu
  And User clicks on Open App Library
  And User clicks on System app
  And User clicks on BI
  And User clicks on Welcome popup close option
  And User clicks on Catalog option
  And User clicks on Add Database button
  And User select the Excel option
  And User enter the database name as 'DB created from Excel'
  And User upload Excel file and clicks on Next button
  And User clicks on Import button
```

> **Note:** The toast message validation `Then User can see database created success toast message as 'Success'` is commented out in the Background for the Excel feature.

#### Scenario Flow

```gherkin
  And User clicks on Add option
  And User search 'DB created from Excel' database and select
  And User clicks on Add All button from Available Columns section
  And User clicks on Import button from Selected Columns section
  And User mouse hover on database frame and click on Visualize this data option
  And User clicks on Save button
  And User enters 'Test Automation' as the insight name, selects the 'Test App' project, and clicks the Save button
 Then User can see Insight created toast message as 'Successfully saved insight(s)'
```

> **Note:** The `Given User can see database created success toast message as 'Success'` precondition is also commented out in the Scenario for Excel.

---

### Feature: Terminal

> **⚠️ BLOCKED:** Terminal system app is not accessible in the local environment. Both scenarios are tagged `@BLOCKED`.

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Run Pixel command in Terminal | `@LoginWithAdmin @Regression @BLOCKED` | Navigates to Terminal → runs Pixel command `Hello` → validates output `Hello` |
| 2 | Run Python command in Terminal | `@LoginWithAdmin @Regression @BLOCKED` | Navigates to Terminal → changes language to Python → runs `1+1` → validates output `2` |

#### Background Flow

```gherkin
Given User is on Home page
 When User opens Main Menu
  And User clicks on Open App Library
 When User clicks on System app
  And User clicks on Terminal card
```

#### Scenario 1: Run Pixel command in Terminal

```gherkin
Given User is on Terminal page
 When User run pixel command 'Hello'
 Then User sees 'Pixel' output 'Hello'
```

#### Scenario 2: Run Python command in Terminal

```gherkin
Given User is on Terminal page
 When User change the language to 'Python'
  And User run python command '1+1'
 Then User sees 'Python' output '2'
```

---

## BI System App – Database Creation Comparison

| # | Aspect | CSV | Excel |
|---|--------|-----|-------|
| 1 | Data Source Selection | Default (CSV) | `User select the Excel option` |
| 2 | Database Name | `DB created from CSV` | `DB created from Excel` |
| 3 | File Upload | `User uploads CSV file` | `User upload Excel file and clicks on Next button` |
| 4 | Next Button | Separate step: `User clicks on Next button` | Combined with upload step |
| 5 | Import Button | `User clicks on Import button` | `User clicks on Import button` |
| 6 | Success Toast Validation | ✅ Active | 🔇 Commented out |

---

## BI System App – Insight Creation Flow (Common)

Both CSV and Excel features share the same Insight creation flow:

| Step # | Step | Description |
|--------|------|-------------|
| 1 | `User clicks on Add option` | Opens the Add data source dialog |
| 2 | `User search '<DB_NAME>' database and select` | Searches and selects the created database |
| 3 | `User clicks on Add All button from Available Columns section` | Selects all available columns |
| 4 | `User clicks on Import button from Selected Columns section` | Imports selected columns |
| 5 | `User mouse hover on database frame and click on Visualize this data option` | Triggers visualization |
| 6 | `User clicks on Save button` | Opens save dialog |
| 7 | `User enters 'Test Automation' as the insight name, selects the 'Test App' project, and clicks the Save button` | Saves the Insight |
| 8 | `User can see Insight created toast message as 'Successfully saved insight(s)'` | Validates success |

---

## BI System App – App Creation Flow (Common Background)

Both CSV and Excel features share the same app creation prerequisite:

| Step # | Step | Description |
|--------|------|-------------|
| 1 | `User is on Home page` | Validates Home page |
| 2 | `User opens Main Menu` | Opens main menu |
| 3 | `User clicks on Open App Library` | Opens App Library |
| 4 | `User clicks on Create New App button` | Clicks Create New App |
| 5 | `User clicks on Get Started button in "Drag and Drop"` | Selects Drag and Drop template |
| 6 | `User enters app name as 'Test app'` | Enters app name |
| 7 | `User enters description as 'Created by automation script'` | Enters description |
| 8 | `User enters tags 'Test1, Test2' and presses Enter` | Enters tags |
| 9 | `User clicks on Create button` | Creates the app |

---

## Terminal – Command Reference

| # | Language | Command | Expected Output | Status |
|---|---------|---------|-----------------|--------|
| 1 | Pixel | `Hello` | `Hello` | 🚫 Blocked |
| 2 | Python | `1+1` | `2` | 🚫 Blocked |

---

## Tags Reference

| Tag | Feature File(s) | Description |
|-----|-----------------|-------------|
| `@Regression` | BI CSV, BI Excel, Terminal | Included in regression test suite |
| `@LoginWithAdmin` | Terminal | Requires admin login |
| `@BLOCKED` | Terminal | Blocked — Terminal not accessible in local environment |

---

## Commented / Inactive Steps

### BI Excel – Commented Toast Validation

The following steps are **commented out** in the Excel feature file:

```gherkin
# Background:
#   Then User can see database created success toast message as 'Success'

# Scenario:
#   Given User can see database created success toast message as 'Success'
```

**Reason:** Toast message validation was unstable or not applicable for the Excel import flow at the time of authoring.

---