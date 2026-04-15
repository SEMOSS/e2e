# Guardrail BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/guardrail/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Guardrail Types Available](#guardrail-types-available)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the guardrail-related feature files located under `src/test/resources/Features/guardrail/`. It covers guardrail creation (form-based and ZIP upload), guardrail catalog editing (details, description, tags, domains, data classification, data restrictions), overview validation, and guardrail export functionality.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | Add Guardrail, Validate Guardrail | Opens the main navigation menu |
| 2 | `Given User can see the Guardrail Catalog title as '{string}'` | Validate Guardrail | Validates guardrail catalog title is displayed |

---

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | Add Guardrail, Validate Guardrail | Opens the main navigation menu |
| 2 | `When User clicks on Guardrail` | Add Guardrail, Validate Guardrail | Opens the Guardrail catalog page |

### Guardrail Creation - Form

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `When User clicks on Add Guardrail button` | Add Guardrail, Validate Guardrail | Clicks Add Guardrail button |
| 4 | `When User selects '{string}'` | Add Guardrail, Validate Guardrail | Selects guardrail type (e.g., 'Gliner') |
| 5 | `When User enters guardrail Catalog Name as '{string}'` | Add Guardrail, Validate Guardrail | Enters guardrail catalog name |
| 6 | `When User enters NER Labels as '{string}' and presses Enter` | Add Guardrail, Validate Guardrail | Enters NER labels and presses Enter |
| 7 | `When User enters Default Threshold as '{string}'` | Add Guardrail, Validate Guardrail | Enters default threshold value |
| 8 | `When User clicks on 'Connect' button` | Add Guardrail, Validate Guardrail | Clicks Connect button to create guardrail |
| 9 | `When User clicks on Copy Catalog ID` | Add Guardrail, Validate Guardrail | Copies the catalog ID |

### Guardrail Creation - ZIP Upload

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `When User clicks on file upload icon` | Add Guardrail | Clicks file upload icon |
| 11 | `When User uploads the file '{string}'` | Add Guardrail | Uploads a file (e.g., 'Guardrail/Gliner.zip') |
| 12 | `When User clicks on 'Upload' button to create catalog` | Add Guardrail | Clicks Upload button to create catalog |

### Guardrail Catalog Edit

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 13 | `When User clicks on Edit button` | Validate Guardrail | Clicks Edit button |
| 14 | `When User enters the details as '{string}'` | Validate Guardrail | Enters details in the overview section |
| 15 | `When User enters the description as '{string}'` | Validate Guardrail | Enters description for the guardrail |
| 16 | `When User add Tags '{string}' and presses Enter` | Validate Guardrail | Adds tags and presses Enter |
| 17 | `When User enters the Domains as '{string}'` | Validate Guardrail | Enters domain values |
| 18 | `When User selects '{string}' from the Data Classification dropdown` | Validate Guardrail | Selects data classification values |
| 19 | `When User selects '{string}' from the Data Restrictions dropdown` | Validate Guardrail | Selects data restrictions values |
| 20 | `When User clicks on Submit button` | Validate Guardrail | Clicks Submit button |

### Guardrail Export

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `When User clicks on Export button` | Validate Guardrail | Clicks Export button to download guardrail |

---

## Then Steps

### Guardrail Creation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see a toast message as '{string}'` | Add Guardrail, Validate Guardrail | Validates success toast message (e.g., 'Successfully added new guardrail to catalog', 'Successfully Created Guardrail Database') |
| 2 | `Then User can see the Guardrail Catalog title as '{string}'` | Add Guardrail, Validate Guardrail | Validates guardrail catalog title displayed |

### Guardrail Edit Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `Then User can see a edit success toast message as '{string}'` | Validate Guardrail | Validates edit success toast message ('Successfully set the new metadata values for the engine') |
| 4 | `Then User should see description as '{string}' on the page` | Validate Guardrail | Validates description is visible on the page |
| 5 | `Then User should see '{string}' in the overview Details section` | Validate Guardrail | Validates details in overview Details section |
| 6 | `Then User should see '{string}' in the overview Tag section` | Validate Guardrail | Validates tags in overview Tag section |
| 7 | `Then User should see '{string}' in the overview Domain section` | Validate Guardrail | Validates domains in overview Domain section |
| 8 | `Then User should see '{string}' in the overview Data classification section` | Validate Guardrail | Validates data classification in overview section |
| 9 | `Then User should see '{string}' in the overview Data restrictions section` | Validate Guardrail | Validates data restrictions in overview section |

### Guardrail Export Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 10 | `Then User sees export success toast message as '{string}'` | Validate Guardrail | Validates export success toast ('Guardrail engine downloaded successfully') |
| 11 | `Then User sees catalog zip file downloaded` | Validate Guardrail | Validates ZIP file is downloaded |
| 12 | `Then User sees downloaded zip file name contains '{string}'` | Validate Guardrail | Validates downloaded ZIP file name contains catalog name |

---

## Guardrail Types Available

### Connection Types

| # | Guardrail Type | Form Fields | Description |
|---|---------------|------------|-------------|
| 1 | Gliner | Catalog Name, NER Labels, Default Threshold | NER-based guardrail with configurable labels and threshold |

### Guardrail Creation Methods

| # | Method | Description |
|---|--------|-------------|
| 1 | Form-based | Select guardrail type → fill form fields → click Connect |
| 2 | ZIP Upload | Click upload icon → upload ZIP file → click Upload |

### Available Upload Files

| # | File Path | Description |
|---|----------|-------------|
| 1 | `Guardrail/Gliner.zip` | Pre-configured Gliner guardrail ZIP |

---

## Test Data Summary

### Gliner Guardrail - Creation

| Field | Value |
|-------|-------|
| Catalog Name | `Gliner guardrail` |
| NER Labels | `label` |
| Default Threshold | `1` |

### Gliner Guardrail - Edit

| Field | Value |
|-------|-------|
| Details | `Gliner guardrail` |
| Description | `Test Gliner guardrail catalog` |
| Tags | `embeddings, Test1, Test2, Test3` |
| Domains | `SAP, AI, Finance` |
| Data Classification | `IP, PHI, PII, PUBLIC` |
| Data Restrictions | `IP ALLOWED, PHI ALLOWED, FOUO ALLOWED` |

---

## Feature Files Summary

| # | Feature File | Tags | Scenarios | Description |
|---|-------------|------|-----------|-------------|
| 1 | Add Guardrail Catalog | `@LoginWithAdmin @Regression @DeleteTestCatalog` | 2 Scenarios | Create Gliner guardrail via form; Create guardrail by uploading ZIP file |
| 2 | Validate Guardrail Catalog | `@LoginWithAdmin @Regression @DeleteTestCatalog` | 2 Scenarios (1 Scenario Outline with 1 example + 1 Export scenario) | Edit guardrail catalog (details, description, tags, domains, classification, restrictions) and validate overview sections; Export guardrail and validate downloaded ZIP file |

---

## Scenario Details

### Feature: Add Guardrail Catalog

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Create Guardrail catalog by filling the form - Gliner | `@LoginWithAdmin @Regression @DeleteTestCatalog` | Navigates to Guardrail → Add Guardrail → selects Gliner → fills Catalog Name, NER Labels, Default Threshold → clicks Connect → validates success toast and catalog title |
| 2 | Create Guardrail catalog by uploading ZIP file | `@LoginWithAdmin @Regression @DeleteTestCatalog` | Navigates to Guardrail → Add Guardrail → clicks upload icon → uploads `Guardrail/Gliner.zip` → clicks Upload → validates success toast |

### Feature: Validate Guardrail Catalog

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Edit guardrail catalog (Scenario Outline) | `@LoginWithAdmin @Regression @DeleteTestCatalog` | Background creates Gliner guardrail → Edit button → enters details, description, tags, domains, data classification, data restrictions → Submit → validates edit success toast and all overview sections |
| 2 | Export created guardrail catalog | `@LoginWithAdmin @Regression @DeleteTestCatalog` | Background creates Gliner guardrail → clicks Export → validates export toast, ZIP download, and file name |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 2 |
| When Steps | 21 |
| Then Steps | 12 |
| **Total** | **35** |

---