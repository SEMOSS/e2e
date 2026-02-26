# Storage BDD Steps Documentation

> **Last Updated:** February 25, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/storage/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Storage Types Available](#storage-types-available)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted from the storage-related feature files located under `src/test/resources/Features/storage/`. It covers storage creation (Amazon S3, CEPH, Dropbox, Google Cloud, Local File System, Microsoft Azure Blob Storage, MinIO, Network File System, SFTP), storage form field validation for all connection types, SMSS property validation, usage examples, storage overview, access control, change access request, catalog filtering, bookmarking, discoverable storages, and form submission validation.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User is on Home page` | Add Storage, Add Local FS, Validate All Storage Types, View Add Storage, Validate Storage Form | User is on the home page |
| 2 | `Given User opens Main Menu` | Add Storage, Validate All Storage Types, View Existing Storages | Opens the main navigation menu |
| 3 | `Given User can see the Storage title as '{string}'` | Add Storage | Validates storage title is displayed |

---

## When Steps

### Navigation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | All storage features | Opens the main navigation menu |
| 2 | `When User clicks on Open Storage` | All storage features | Opens the Storage catalog page |

### Storage Creation - General

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `When User clicks on Add Storage button` | All storage features | Clicks Add Storage button |
| 4 | `When User selects '{string}' storage` | All storage features | Selects storage type (e.g., 'Amazon S3', 'Local File System') |
| 5 | `When User enters storage Catalog name as '{string}'` | Add Storage, Add Local FS, View Existing Storages | Enters storage catalog name |
| 6 | `When User clicks on Connect button to create storage` | Add Storage, Add Local FS, Validate Storage Form, View Existing Storages | Clicks Connect button to create storage |
| 7 | `When User clicks on Copy Catalog ID` | Add Storage, Add Local FS, Validate Storage Form, View Existing Storages | Copies the catalog ID |

### Storage Creation - Amazon S3

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 8 | `When User enters Region as '{string}'` | Add Storage, View Existing Storages | Enters region value |
| 9 | `When User enters Bucket as '{string}'` | Add Storage, View Existing Storages | Enters bucket value |
| 10 | `When User enters Access Key as '{string}'` | Add Storage, View Existing Storages | Enters access key |
| 11 | `When User enters Secret Key as '{string}'` | Add Storage, View Existing Storages | Enters secret key |

### Storage Creation - Local File System

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 12 | `When User enters Path Prefix as '{string}'` | Add Local FS | Enters path prefix for local storage |

### Storage Form Validation - All Types

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 13 | `When User fills the '{string}' storage creation form with:` | Validate All Storage Types | Fills storage creation form with data table |

### Storage Form Validation - Specific Fields

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 14 | `When User can see '{string}' fields on the form` | Validate Storage Form | Validates field names visible on form |
| 15 | `When User sees astrisk mark on the '{string}' fields of storage creation form` | Validate Storage Form | Validates asterisk on required fields |
| 16 | `When User enters value in below fields` | Validate Storage Form | Enters values in form fields using data table |

### Storage SMSS Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 17 | `When User clicks on SMSS` | Add Storage | Clicks SMSS tab |

### Storage Usage Tab

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 18 | `When User clicks on Usage tab for storage` | Add Storage | Clicks Usage tab for storage |

### Storage Overview

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `When User can see 'copy Storage ID' Storage ID along with copy icon` | Add Storage | Validates Storage ID with copy icon is visible |
| 20 | `When User clicks on copy icon of Storage ID` | Add Storage | Clicks copy icon for Storage ID |
| 21 | `When User can see toast message as '{string}'` | Add Storage | Validates toast message (e.g., 'ID copied to clipboard') |
| 22 | `When User clicks on Edit button` | Add Storage, View Existing Storages | Clicks Edit button |
| 23 | `When User add Tags '{string}' and presses Enter` | Add Storage, View Existing Storages | Adds tags |
| 24 | `When User clicks on Submit button` | Add Storage, View Existing Storages | Clicks Submit button |
| 25 | `When User enters the Domains as '{string}'` | View Existing Storages | Enters domain values |
| 26 | `When User selects '{string}' from the Data Classification dropdown` | View Existing Storages | Selects data classification |
| 27 | `When User selects '{string}' from the Data Restrictions dropdown` | View Existing Storages | Selects data restrictions |

### Storage Access Control

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 28 | `When User clicks on Access Control Tab` | Add Storage, Add Local FS, View Existing Storages | Clicks Access Control tab |
| 29 | `When User clicks on Add Member button` | Add Storage | Clicks Add Member button |
| 30 | `When User adds one user and assigns them as '{string}'` | Add Storage | Adds user with role (Editor/Read) |
| 31 | `When User clicks on Delete button for storage` | Add Local FS | Clicks Delete button for storage |
| 32 | `When User clicks Make '{string}' Discoverable button` | View Existing Storages | Clicks Make Storage Discoverable button |

### Storage Change Access

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 33 | `When User click on the Change Access button` | Add Storage | Clicks Change Access button |
| 34 | `When User should see the "Change Access" popup with following options:` | Add Storage | Validates Change Access popup options (Author, Editor, Read-Only, Comment Box, Cancel Button, Request Button) |
| 35 | `When User click on cancel button` | Add Storage | Clicks Cancel button on Change Access popup |
| 36 | `When User selects '{string}' access` | Add Storage | Selects access level (e.g., 'author') |
| 37 | `When User types a comment as '{string}'` | Add Storage | Types a comment for access request |
| 38 | `When User clicks on Request button` | Add Storage | Clicks Request button |

### Storage Catalog Search & Selection

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 39 | `When User searches '{string}' storage in the storage searchbox` | Add Local FS | Searches storage in searchbox |
| 40 | `When User clicks on created storage '{string}'` | Add Local FS | Clicks on created storage |
| 41 | `When User searches the '{string}' in the storage Catalog searchbox` | Add Storage, View Existing Storages | Searches storage in catalog searchbox |
| 42 | `When User selects the '{string}' from the storage catalog` | Add Storage, View Existing Storages | Selects storage from catalog |

### Storage Catalog Filter & Bookmark

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 43 | `When User clicks on bookmark button of '{string}' catalog` | View Existing Storages | Clicks bookmark button for catalog |
| 44 | `When User clicks on bookmark button to unbookmark '{string}' catalog` | View Existing Storages | Clicks bookmark button to unbookmark |
| 45 | `When User clicks on Discoverable Storages button` | View Existing Storages | Clicks Discoverable Storages button |

### User Login/Logout

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 46 | `When User logs out from the application` | Add Storage, View Existing Storages | Logs out from the application |
| 47 | `When User login as '{string}'` | Add Storage, View Existing Storages | Logs in as specified user |

---

## Then Steps

### Storage Creation Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see create storage success toast message as '{string}'` | Add Storage, Add Local FS, Validate Storage Form | Validates storage creation success toast |
| 2 | `Then User can see the Storage title as '{string}'` | Add Storage, Add Local FS, Validate Storage Form | Validates storage title displayed |

### Storage Form Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 3 | `Then User can see '{string}' storage creation form with following sections with fields:` | Validate All Storage Types | Validates storage form sections and fields with data table |
| 4 | `Then User can see '{string}' storage creation form with following mandatory fields` | Validate All Storage Types | Validates storage mandatory fields with data table |
| 5 | `Then User can see 'Connect' button becomes enabled to create storage` | Validate All Storage Types, Validate Storage Form | Validates Connect button becomes enabled |
| 6 | `Then User sees the Connect button is disabled` | Validate Storage Form | Validates Connect button is disabled |

### Storage SMSS Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 7 | `Then User can see storage name in 'NAME' field as '{string}' in SMSS properties` | Add Storage | Validates NAME field in SMSS |
| 8 | `Then User can see storage region in 'S3_REGION' field as '{string}' in SMSS properties` | Add Storage | Validates S3_REGION in SMSS |
| 9 | `Then User can see storage bucket in 'S3_BUCKET' field as '{string}' in SMSS properties` | Add Storage | Validates S3_BUCKET in SMSS |
| 10 | `Then User can see storage access key in 'S3_ACCESS_KEY' field as '{string}' in SMSS properties` | Add Storage | Validates S3_ACCESS_KEY in SMSS |

### Storage Usage Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 11 | `Then User sees an example of "{string}" with example code for storage` | Add Storage | Validates usage example (Javascript, Python, Langchain API, Java) |

### Storage Overview Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 12 | `Then User can see a edit success toast message as '{string}'` | Add Storage | Validates edit success toast |
| 13 | `Then User should see '{string}' on the page` | Add Storage | Validates text/tag visible on page |
| 14 | `Then User sees Change Access button` | Add Storage | Validates Change Access button visible for Read user |

### Storage Access Control Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 15 | `Then User sees deleted storage success toast message '{string}'` | Add Local FS | Validates storage deletion success toast |
| 16 | `Then User should see the "Change Access" popup with following options:` | Add Storage | Validates Change Access popup (Author, Editor, Read-Only, Comment Box, Cancel Button, Request Button) |
| 17 | `Then User should successfully request access and a toast message as '{string}'` | Add Storage | Validates access request success toast |
| 18 | `Then User login as '{string}'` | Add Storage, View Existing Storages | Logs in as specified user |

### Storage Catalog Filter Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 19 | `Then User applies each filter and validate '{string}' catalog is visible on the '{string}' catalog page` | View Existing Storages | Validates filter functionality with data table |
| 20 | `Then User sees the catalog name '{string}' in the Bookmarked section` | View Existing Storages | Validates catalog in Bookmarked section |

### View Add Storage Options Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 21 | `Then User should see Search bar to filter storage options` | View Add Storage | Validates search bar visible on add storage page |
| 22 | `Then User should see the following '{string}' options with valid icons on the Connect to Storage page` | View Add Storage | Validates storage options with groups using data table |

---

## Storage Types Available

### Connection Types

| # | Storage | Storage Type | General Fields | Credentials Fields | Settings Fields | Mandatory Fields |
|---|---------|-------------|---------------|-------------------|----------------|-----------------|
| 1 | Amazon S3 | S3 | Catalog Name | Access Key, Secret Key | Region, Bucket | Catalog Name, Region, Bucket |
| 2 | CEPH | CEPH | Catalog Name | Access Key, Secret Key | Endpoint, Root Bucket Path | Catalog Name, Access Key, Secret Key, Endpoint |
| 3 | Dropbox | S3 | Catalog Name | S3 Access Key, S3 Secret Key | S3 Endpoint, S3 Region | Catalog Name, S3 Endpoint, S3 Region, S3 Access Key, S3 Secret Key |
| 4 | Google Cloud | GCS | Catalog Name | Service Account File | Region, Bucket | Catalog Name, Region, Service Account File |
| 5 | Local File System | LOCAL | Catalog Name | *(none)* | Local Path Prefix | Catalog Name, Local Path Prefix |
| 6 | Microsoft Azure Blob Storage | Azure | Catalog Name | Primary Key, Connection String | Account Name, Generate Dynamic SAS | Catalog Name, Account Name, Generate Dynamic SAS, Primary Key, Connection String |
| 7 | MinIO | MINIO | Catalog Name | Access Key, Secret Key | Region, Endpoint, Root Bucket Path | Catalog Name, Region, Endpoint, Access Key, Secret Key |
| 8 | Network File System | NFS | Catalog Name | Username, Password | Network Domain, Network Path Prefix | Catalog Name, Network Domain, Username, Password |
| 9 | SFTP | SFTP | Catalog Name | Username, Password | Host, Port | Catalog Name, Host, Port, Username |

### All Available Storage Options

| Group | Storage Options |
|-------|----------------|
| Storage | Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP |

> **Note:** Dreamhost, Google Drive, and Microsoft OneDrive appear in the available options list but do not have form validation scenarios in the current feature files.

---

## Feature Files Summary

| # | Feature File | Scenarios | Description |
|---|-------------|-----------|-------------|
| 1 | Validate All Storage Types | 1 Scenario Outline (9 examples) | Validate form sections, fields, mandatory fields, and Connect button enablement for all 9 storage types (Amazon S3, CEPH, Dropbox, Google Cloud, Local File System, Microsoft Azure Blob, MinIO, NFS, SFTP) |
| 2 | Add Local File System Storage | 1 Scenario | Create Local File System storage and delete it |
| 3 | Add Storage (Amazon S3) | 5 Scenarios | Create Amazon S3 storage; validate usage examples, SMSS properties, overview (ID/tags/access control), change access popup, change access request |
| 4 | View Add Storage Page | 1 Scenario | Verify available storage options with icons (12 options) |
| 5 | Validate Storage Creation Form | 2 Scenarios (1 Outline with 1 example + 1 form submission validation) | Validate Amazon S3 form fields/asterisks, validate Connect button disabled for missing required fields, validate successful creation |
| 6 | View Existing Storages on Catalog Page | 2 Scenarios | Create Amazon S3 storage, edit tags/domains/classification/restrictions, validate My Storages filter with bookmark/unbookmark, validate Discoverable Storages filter |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 3 |
| When Steps | 47 |
| Then Steps | 22 |
| **Total** | **72** |

---