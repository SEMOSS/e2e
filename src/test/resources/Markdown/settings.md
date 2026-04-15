# Settings BDD Steps Documentation

> **Last Updated:** February 26, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/settings/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted exclusively from the settings-related feature files located under `src/test/resources/Features/settings/`. It covers Admin Query, Admin Settings tiles, Job Management (add, edit, delete, run, pause, resume), Privacy Center popup validation, Settings My Profile (access tokens), User/Member Management, Catalog User Permissions (App, Model, Database, Function, Storage, Vector), Vector Settings search, and Configuration updates.

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User opens Main Menu` | Admin Query, Admin Settings, Job Management, Privacy Center, My Profile, User Permissions, User Management, Vector Settings, Settings Jobs | Opens the main navigation menu |
| 2 | `Given User clicks on Open Settings` | Admin Settings | Opens the Settings page from Main Menu (Background) |
| 3 | `Given User enables admin mode` | Admin Settings | Enables admin mode toggle on Settings page (Background) |
| 4 | `Given User created '{int}' models with the '{string}' model '{string}', catalog name '{string}', OpenAI key '{string}'` | Admin Query | Creates specified number of models as prerequisite |
| 5 | `Given User created '{int}' jobs with the job name '{string}', Pixel '{string}'` | Admin Query | Creates specified number of jobs as prerequisite |
| 6 | `Given User searches '{string}' in Search box` | Admin Settings | Searches text in the settings page search box |
| 7 | `Given User clicks on Open Model` | Vector Settings | Opens the Model catalog page (Background) |
| 8 | `Given User clicks on Open Vector` | Vector Settings | Opens the Vector catalog page |
| 9 | `Given User can see the Catalog title as '{string}'` | User Permissions (referenced via Background steps from other features) | Validates catalog title is displayed |

---

## When Steps

### Navigation & Settings

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User opens Main Menu` | Multiple | Opens the main navigation menu |
| 2 | `When User clicks on Open Settings` | Multiple | Opens the Settings page from Main Menu |
| 3 | `When User enables admin mode` | Admin Query, Admin Settings, Settings Jobs, User Management, Vector Settings | Enables admin mode toggle on Settings page |
| 4 | `When User enable admin mode` | User Management, Vector Settings | Enables admin mode (alternate phrasing) |

### Admin Query

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `When User clicks on '{string}' Card` | Admin Query, User Management | Clicks on a settings card (e.g., 'Admin Query', 'Member Settings', 'Configuration', 'Vector Settings') |
| 6 | `When User clicks on Database dropdown` | Admin Query | Clicks Database dropdown in Admin Query |
| 7 | `When User selects '{string}' from the database dropdown` | Admin Query | Selects a database from dropdown (e.g., 'LocalMasterDatabase', 'security', 'scheduler', 'themes') |
| 8 | `When User enters '{string}' in the query textbox` | Admin Query | Enters SQL query in the textbox |
| 9 | `When User enters '{string}' in the Max Rows to Collected textbox` | Admin Query | Enters max rows value |
| 10 | `When User clicks on Run button` | Admin Query | Clicks Run button to execute query |

### Job Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 11 | `When User clicks on Jobs` | Job Management, Settings Jobs | Clicks on Jobs card/tile |
| 12 | `When User clicks on Add Job button` | Job Management | Clicks Add Job button |
| 13 | `When User fills '{string}' in Name field` | Job Management | Fills job name field |
| 14 | `When User fills '{string}' in Pixel field` | Job Management | Fills pixel/recipe field |
| 15 | `When User clicks Add button` | Job Management | Clicks Add button to create job |
| 16 | `When User clicks on Edit Icon for added '{string}'` | Job Management | Clicks Edit icon for a specific job |
| 17 | `When User edit Tags as {int}` | Job Management | Edits tags value for a job |
| 18 | `When User edit Pixels as '{string}'` | Job Management | Edits Pixel value for a job |
| 19 | `When User clicks Save button` | Job Management | Clicks Save button after editing job |
| 20 | `When User clicks on Delete Icon for added '{string}'` | Job Management | Clicks Delete icon for a specific job |
| 21 | `When User selects the checkbox next to '{string}'` | Job Management | Selects checkbox next to a job |
| 22 | `When User clicks the checkbox of "{string}"` | Job Management | Clicks checkbox of a job (alternate phrasing) |
| 23 | `When User clicks the green Pause button` | Job Management | Clicks the green Pause button |
| 24 | `When User clicks the Resume button` | Job Management | Clicks the Resume button |
| 25 | `When User expands History table` | Settings Jobs | Expands the History table section |

### Privacy Center

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 26 | `When User clicks on Privacy Center button` | Privacy Center | Clicks Privacy Center button |
| 27 | `When User clicks on close icon` | Privacy Center | Clicks close icon on Privacy popup |
| 28 | `When User clicks on 'Close' button` | Privacy Center | Clicks Close button on Privacy popup |

### My Profile

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 29 | `When User clicks on My Profile` | My Profile, User Management | Clicks My Profile tile/link |
| 30 | `When User clicks on New Key button` | My Profile | Clicks New Key button in Personal Access Tokens |
| 31 | `When User copies the '{string}' using copy icon and validate its alphanumeric` | My Profile | Copies Access Key or Secret Key and validates format |
| 32 | `When User clicks on Close button` | My Profile | Clicks Close button on key generation dialog |

### User / Member Management

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 33 | `When User clicks on Add Member button` | User Permissions, User Management | Clicks Add Member button |
| 34 | `When User adds one user and assigns them as '{string}'` | User Permissions | Adds a user with specified role (Editor, Read) |
| 35 | `When User adds {int} members and can see toast message as '{string}' for all added members` | User Management | Adds specified number of members |
| 36 | `When User adds {int} members with name "{string}", userId "{string}", password "{string}", and email domain "{string}" and can see toast message as '{string}' for all added members` | User Management | Adds members with specific details |
| 37 | `When User clicks on Edit icon` | User Management | Clicks Edit icon for a member |
| 38 | `When User clicks on Model dropdown` | User Management | Clicks Model dropdown in member edit |
| 39 | `When User selects '{string}' value in Model dropdown` | User Management | Selects value in Model dropdown (e.g., 'Token') |
| 40 | `When User fills '{string}' value in Max Tokens field` | User Management | Fills Max Tokens value |
| 41 | `When User clicks on Frequency dropdown` | User Management | Clicks Frequency dropdown |
| 42 | `When User selects '{string}' value in Frequency dropdown` | User Management | Selects frequency value (e.g., 'Weekly') |
| 43 | `When User clicks on save button` | User Management | Clicks save button after editing member |
| 44 | `When User searches for the created user` | User Management | Searches for previously created user |
| 45 | `When User clicks on Delete Selected button {int} times` | User Management | Clicks Delete Selected button specified times |
| 46 | `When User logs out from the application` | User Permissions, User Management | Logs out current user |
| 47 | `When User login as '{string}'` | User Permissions, User Management | Logs in as specified user role |
| 48 | `When User logs in with the last generated userId and password` | User Management | Logs in with dynamically generated credentials |

### Configuration

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 49 | `When User clicks on '{string}' value` | User Management | Clicks on a configuration key value (e.g., 'access_keys_allowed') |
| 50 | `When User change value of the key to '{string}'` | User Management | Changes configuration key value |
| 51 | `When User clicks on Save button of the configuration` | User Management | Saves configuration changes |
| 52 | `When User clicks on Authentication dropdown` | User Management | Clicks Authentication dropdown in Configuration |
| 53 | `When User search '{string}' and select` | User Management | Searches and selects an authentication type (e.g., 'Native') |

### User Permissions (Catalog-Level)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 54 | `When User opens '{string}'` | User Permissions | Opens a catalog type page (Vector, Model, Database, Function, Storage) |
| 55 | `When User checks if '{string}' catalog created and Deletes the '{string}'` | User Permissions | Checks and deletes existing catalog |
| 56 | `When User clicks on Add '{string}' button` | User Permissions | Clicks Add button for specific catalog type |
| 57 | `When User selects the 'ZIP' option to upload file` | User Permissions | Selects ZIP upload option |
| 58 | `When User uploads the file '{string}'` | User Permissions | Uploads a file |
| 59 | `When User clicks on Create '{string}' button to create catalog` | User Permissions | Clicks Create button for specific catalog type |
| 60 | `When User clicks on Copy Catalog ID` | User Permissions, Vector Settings | Copies catalog ID |
| 61 | `When User clicks on file upload icon` | User Permissions | Clicks file upload icon |
| 62 | `When User clicks on 'Upload' button to create catalog` | User Permissions | Clicks Upload button |
| 63 | `When User selects the '{string}' card` | User Permissions | Selects a settings card (e.g., 'Vector Settings', 'Model Settings', 'App Settings') |
| 64 | `When User can search '{string}' in search box` | User Permissions | Searches for catalog in settings card |
| 65 | `When User clicks on the '{string}'` | User Permissions | Clicks on a specific catalog name |
| 66 | `When User search for '{string}' user in members search box` | User Permissions | Searches for user in members search box |
| 67 | `When '{string}' user changes '{string}' role to '{string}'` | User Permissions | User changes another user's role |
| 68 | `When '{string}' user deletes '{string}' role user from members list` | User Permissions | User deletes another user from members |

### App Creation (for User Permissions)

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 69 | `When User clicks on Open App Library` | User Permissions | Opens App Library |
| 70 | `When User clicks on Create New App button` | User Permissions | Clicks Create New App button |
| 71 | `When User clicks on Get Started button in '{string}'` | User Permissions | Clicks Get Started (e.g., 'Drag and Drop') |
| 72 | `When User enters '{string}' as app name` | User Permissions | Enters app name |
| 73 | `When User enters description as '{string}'` | User Permissions | Enters app description |
| 74 | `When User enters tags '{string}' and presses Enter` | User Permissions | Enters tags |
| 75 | `When User clicks on Create button` | User Permissions | Clicks Create button |
| 76 | `When User fetch the app name` | User Permissions | Fetches the app name |

### Vector Settings

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 77 | `When User clicks on Add Model` | Vector Settings | Clicks Add Model button |
| 78 | `When User selects '{string}' type` | Vector Settings | Selects model type (e.g., 'OpenAI') |
| 79 | `When User selects '{string}'` | Vector Settings | Selects model variant (e.g., 'GPT 3.5 Turbo') |
| 80 | `When User enters Catalog Name as '{string}'` | Vector Settings | Enters catalog name for model |
| 81 | `When User enters Open AI Key as '{string}'` | Vector Settings | Enters OpenAI API key |
| 82 | `When User clicks on Create Model button` | Vector Settings | Clicks Create Model button |
| 83 | `When User clicks on Edit button` | Vector Settings | Clicks Edit button |
| 84 | `When User add Tags '{string}' and presses Enter` | Vector Settings | Adds tags |
| 85 | `When User clicks on Submit button` | Vector Settings | Clicks Submit button |
| 86 | `When User clicks on Add Vector button` | Vector Settings | Clicks Add Vector button |
| 87 | `When User selects '{string}' connection` | Vector Settings | Selects vector connection (e.g., 'FAISS') |
| 88 | `When User enters vector database Catalog name as '{string}'` | Vector Settings | Enters vector catalog name |
| 89 | `When User selects '{string}' from Embedder field` | Vector Settings | Selects embedder model |
| 90 | `When User selects '{string}' from Chunking Strategy field` | Vector Settings | Selects chunking strategy |
| 91 | `When User enters value of Content Length as '{string}'` | Vector Settings | Enters content length |
| 92 | `When User enters value of Content Overlap as '{string}'` | Vector Settings | Enters content overlap |
| 93 | `When User clicks on Create Vector button` | Vector Settings | Clicks Create Vector button |
| 94 | `When User searches the '{string}' in the Vector Catalog searchbox` | Vector Settings | Searches in Vector catalog searchbox |
| 95 | `When User selects the '{string}' from the Vector catalog` | Vector Settings | Selects vector from catalog |

---

## Then Steps

### Admin Query Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can see table with '{int}' columns:'{string}'` | Admin Query | Validates table column count and names |
| 2 | `Then User can see success toast message as '{string}'` | Admin Query | Validates success toast (e.g., 'Successfully submitted query') |
| 3 | `Then User can see table with '{int}' rows` | Admin Query | Validates table row count |
| 4 | `Then User Delete the created Model` | Admin Query | Cleanup — deletes created models |

### Admin Settings Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 5 | `Then User can view the following settings tile` | Admin Settings | Validates all settings tiles are visible (data table) |
| 6 | `Then User can view the '{string}' tile` | Admin Settings | Validates a specific tile is visible after search |

### Job Management Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 7 | `Then User can see '{string}' in the list` | Job Management | Validates job is visible in the list |
| 8 | `Then User can see '{string}' value as Tag for edited '{string}'` | Job Management | Validates tag value after edit |
| 9 | `Then User sees delete job toast message as '{string}'` | Job Management | Validates delete success toast ('Successfully deleted all selected jobs') |
| 10 | `Then '{string}' will start running and Pause button will be enabled` | Job Management | Validates job is running and Pause button enabled |
| 11 | `Then the "{string}" should stop running` | Job Management | Validates job has stopped running |
| 12 | `Then the checkbox of "{string}" should become unselected` | Job Management | Validates checkbox is unselected |
| 13 | `Then the green Pause button should revert to its default state` | Job Management | Validates Pause button reverts to default |
| 14 | `Then the Resume button should revert to its default state` | Job Management | Validates Resume button reverts to default |

### Settings Jobs Page Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 15 | `Then User sees Jobs page title as '{string}'` | Settings Jobs | Validates Jobs page title ('Jobs') |
| 16 | `Then User sees Jobs page subtitle as '{string}'` | Settings Jobs | Validates Jobs page subtitle |
| 17 | `Then User sees below status tiles on Jobs page` | Settings Jobs | Validates status tiles (Active Jobs, Inactive Jobs, Failed Jobs) |
| 18 | `Then User sees below tabs on Jobs page` | Settings Jobs | Validates tabs (All, Active, Inactive) |
| 19 | `Then User sees below buttons on Jobs page` | Settings Jobs | Validates buttons (Pause, Resume, Add) |
| 20 | `Then User sees below buttons are disabled on Jobs page` | Settings Jobs | Validates disabled buttons (Pause, Resume) |
| 21 | `Then User sees Search box on Jobs page` | Settings Jobs | Validates search box presence |
| 22 | `Then User sees Jobs table with below columns` | Settings Jobs | Validates Jobs table columns |
| 23 | `Then User sees '{string}' message when there are no jobs present` | Settings Jobs | Validates 'No jobs found' message |
| 24 | `Then User sees History table is by default collapsed` | Settings Jobs | Validates History table is collapsed by default |
| 25 | `Then User sees History table with below columns` | Settings Jobs | Validates History table columns |
| 26 | `Then User sees search box on History table` | Settings Jobs | Validates search box on History table |
| 27 | `Then User sees '{string}' message when there is no job history present` | Settings Jobs | Validates 'No job history, please try again.' message |

### Privacy Center Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 28 | `Then Use can see Privacy popup with following elements` | Privacy Center | Validates Privacy popup elements (data table with Popup, Popup name, Close icon, Close button) |
| 29 | `Then Privacy popup should close` | Privacy Center | Validates Privacy popup is closed |

### My Profile Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 30 | `Then User can see '{string}' link in the top right` | My Profile | Validates 'Privacy Center' link is visible |
| 31 | `Then User can see '{string}' section on profile page` | My Profile, User Management | Validates sections on profile page (Edit profile information, Javascript SDK, Python SDK, Personal Access Tokens) |
| 32 | `Then User fills Name as '{string}' in Name field` | My Profile | Fills Name field for access token |
| 33 | `Then User fills Description as '{string}' in Description field` | My Profile | Fills Description field for access token |
| 34 | `Then User clicks on Generate button` | My Profile | Clicks Generate button for access token |
| 35 | `Then User copies contents using copy icon from example section and validate count of Access Key and Secret Key occurences in sections:` | My Profile | Validates Access Key and Secret Key counts in example sections (data table) |
| 36 | `Then User clicks on Close button` | My Profile | Clicks Close button |
| 37 | `Then User clicks on delete icon of the generated '{string}'` | My Profile | Clicks delete icon for a generated key |
| 38 | `Then User can sees the Toastmessage as "{string}"` | My Profile | Validates toast message (e.g., 'Successfully deleted key') |
| 39 | `Then User can see generated key name as '{string}'` | My Profile | Validates generated key name |
| 40 | `Then User can see generated key description as '{string}'` | My Profile | Validates generated key description |

### User / Member Management Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 41 | `Then User sees the Add User button` | User Management | Validates Add User button is visible |
| 42 | `Then User sees the search button` | User Management | Validates search button is visible |
| 43 | `Then User sees the new model limit value as '{string}' on Member Settings page` | User Management | Validates model limit value after edit |
| 44 | `Then User can see a toast message after updating values of '{string}' as "{string}"` | User Management | Validates configuration update toast message |

### User Management - Profile Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 45 | `Then User can see that the displayed User ID matches the generated userId` | User Management | Validates User ID matches on profile page |
| 46 | `Then User can see that the displayed Name matches the generated name` | User Management | Validates Name matches on profile page |
| 47 | `Then User can see that the displayed Email matches the generated email` | User Management | Validates Email matches on profile page |

### User Permissions Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 48 | `Then User should see users with following permissions` | User Permissions | Validates users with expected permissions (data table with USER_TYPE, ROLE) |
| 49 | `Then user can see permission date along with user added time` | User Permissions | Validates permission date/time for each role |
| 50 | `Then User should see role changed to '{string}' in members list` | User Permissions | Validates role change in members list |
| 51 | `Then User should see '{string}' role user is removed from members list` | User Permissions | Validates user removal from members list |
| 52 | `Then '{string}' user cannot change the '{string}' user role and sees '{string}' user in members list` | User Permissions | Validates role change restriction |
| 53 | `Then '{string}' user cannot delete the '{string}' user and sees '{string}' user in members list` | User Permissions | Validates delete restriction |
| 54 | `Then User can see '{string}' user '{string}' icon is disabled` | User Permissions | Validates user action icon is disabled (delete, edit) |

### Vector Settings Validation

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 55 | `Then User can see a toast message as '{string}'` | Vector Settings | Validates toast message (e.g., 'Successfully added LLM to catalog') |
| 56 | `Then User can see vector database created success toast message as '{string}'` | Vector Settings | Validates vector creation success toast |
| 57 | `Then User can see the Vector title as '{string}'` | Vector Settings | Validates Vector title |
| 58 | `Then User sees title as '{string}'` | Vector Settings | Validates settings page title (e.g., 'Vector Settings') |
| 59 | `Then User sees the vector cards` | Vector Settings | Validates vector cards are visible |
| 60 | `Then User sees the search bar` | Vector Settings | Validates search bar is visible |
| 61 | `Then User searches for the vector '{string}'` | Vector Settings | Searches for a specific vector |
| 62 | `Then User sees the '{string}' in the searched vector list` | Vector Settings | Validates vector is visible in search results |

---

## Admin Settings Tiles

| # | Settings Tile |
|---|---------------|
| 1 | App Settings |
| 2 | Database Settings |
| 3 | Function Settings |
| 4 | Model Settings |
| 5 | Storage Settings |
| 6 | Vector Settings |
| 7 | Jobs |
| 8 | Member Settings |
| 9 | Team Permissions |
| 10 | Configuration |
| 11 | Admin Query |
| 12 | My Profile |

---

## Admin Query - Supported Databases

| # | Database Name | Example Query | Columns |
|---|--------------|---------------|---------|
| 1 | LocalMasterDatabase | `select * from ENGINECONCEPT` | ENGINE, PARENTSEMOSSNAME, SEMOSSNAME, PARENTPHYSICALNAME, PARENTPHYSICALNAMEID, PHYSICALNAME, PHYSICALNAMEID, PARENTLOCALCONCEPTID, LOCALCONCEPTID, IGNORE_DATA, PK, ORIGINAL_TYPE, PROPERTY_TYPE, ADDITIONAL_TYPE (14) |
| 2 | security | `select * from ENGINEMETA` | ENGINEID, METAKEY, METAVALUE, METAORDER (4) |
| 3 | scheduler | `select * from SMSS_JOB_RECIPES` | USER_ID, JOB_ID, JOB_NAME, JOB_GROUP, CRON_EXPRESSION, CRON_TIMEZONE, PIXEL_RECIPE, PIXEL_RECIPE_PARAMETERS, JOB_CATEGORY, TRIGGER_ON_LOAD, UI_STATE (11) |
| 4 | themes | `select * from ADMIN_THEME` | ID, THEME_NAME, THEME_MAP, IS_ACTIVE (4) |

---

## Admin Query - Row Count Test Data

| # | Database Name | Query | Max Rows | Expected Row Count | Model Count | Job Count |
|---|--------------|-------|----------|--------------------|-------------|-----------|
| 1 | LocalMasterDatabase | `select * from ENGINECONCEPT` | 1 | 1 | 0 | 0 |
| 2 | security | `select * from ENGINEMETA` | 5 | 5 | 5 | 0 |
| 3 | scheduler | `select * from SMSS_JOB_RECIPES` | 10 | 10 | 0 | 10 |

---

## Jobs Page Elements

### Status Tiles

| # | Tile |
|---|------|
| 1 | Active Jobs |
| 2 | Inactive Jobs |
| 3 | Failed Jobs |

### Tabs

| # | Tab |
|---|-----|
| 1 | All |
| 2 | Active |
| 3 | Inactive |

### Buttons

| # | Button | Default State |
|---|--------|---------------|
| 1 | Pause | Disabled |
| 2 | Resume | Disabled |
| 3 | Add | Enabled |

### Jobs Table Columns

| # | Column |
|---|--------|
| 1 | Name |
| 2 | Frequency |
| 3 | Time Zone |
| 4 | Tags |
| 5 | Last Run |
| 6 | Status |
| 7 | Modified By |
| 8 | Actions |

### History Table Columns

| # | Column |
|---|--------|
| 1 | Name |
| 2 | Run Date |
| 3 | Time |
| 4 | Status |

---

## Privacy Center Popup Elements

| # | Element | Expected Name |
|---|---------|---------------|
| 1 | Popup | (visible) |
| 2 | Popup name | Privacy Preference Center |
| 3 | Close icon | (visible) |
| 4 | Close button | (visible) |

---

## My Profile Sections

| # | Section |
|---|---------|
| 1 | Privacy Center (link, top right) |
| 2 | Edit profile information |
| 3 | Javascript SDK |
| 4 | Python SDK |
| 5 | Personal Access Tokens |

### Access Token Example Sections Validation

| Section | Access Key Count | Secret Key Count |
|---------|-----------------|-----------------|
| Javascript Example | 1 | 1 |
| Python Example | 1 | 1 |

---

## User Permissions - Supported Catalog Types

### ZIP Upload Method (Scenario Outline 1)

| # | Catalog | File Name | Settings Card | Catalog Name |
|---|---------|-----------|---------------|-------------|
| 1 | Vector | `VectorDatabase/TestVector.zip` | Vector Settings | TestVector |

### File Upload Method (Scenario Outline 2)

| # | Catalog | File Name | Settings Card | Catalog Name |
|---|---------|-----------|---------------|-------------|
| 1 | Model | `Model/Llama3-70B-Instruct.zip` | Model Settings | Llama3-70B-Instruct |
| 2 | Database | `Database/TestDatabase.zip` | Database Settings | TestDatabase |
| 3 | Function | `Function/weatherFunctionTest.zip` | Function Settings | WeatherFunctionTest |
| 4 | Storage | `Storage/Localminio.zip` | Storage Settings | localminio |

### App (Standalone Scenario)

| # | Catalog | Method | Settings Card | Catalog Name |
|---|---------|--------|---------------|-------------|
| 1 | Apps | Drag and Drop | App Settings | Test App permissions |

### User Permission Roles Tested

| # | User Type | Role |
|---|-----------|------|
| 1 | Author | Author |
| 2 | Editor | Editor |
| 3 | Read | Read-Only |

### Permission Flow Per Catalog

| Step | Actor | Action | Target | Expected Result |
|------|-------|--------|--------|-----------------|
| 1 | Author | Adds Editor and Read members | Catalog | Users visible with correct roles |
| 2 | Editor | Changes Read-Only role to Editor | Read user | Role changed to Editor |
| 3 | Editor | Deletes Editor role user | Read user (now Editor) | User removed from members |
| 4 | Editor | Attempts to change Author role | Author user | Cannot change — Author still visible |
| 5 | Editor | Attempts to delete Author | Author user | Cannot delete — Author still visible |
| 6 | Editor | Adds new Read member | New user | Member added |
| 7 | Read | Views Author icons | Author user | Delete and edit icons disabled |
| 8 | Read | Views Editor icons | Editor user | Delete and edit icons disabled |
| 9 | Author | Changes Editor role to Read-Only | Editor user | Role changed to Read-Only |
| 10 | Author | Deletes Read-Only role user | Editor user (now Read-Only) | User removed from members |

---

## User Management - Test Data

### Add New Native User

| Field | Value |
|-------|-------|
| Member Count | 11 |
| Toast Message | `Successfully added user` |
| Delete Count | 2 |

### Edit Native User - Model Limit

| Field | Value |
|-------|-------|
| Member Count | 1 |
| Model Dropdown | Token |
| Max Tokens | 2 |
| Frequency | Weekly |
| Expected Limit Value | 2 |

### Add Native User with Unique Details - Profile Validation

| Field | Value |
|-------|-------|
| Name | TestUser |
| UserId | TestUserId |
| Password | Test@123 |
| Email Domain | testautomation.com |
| Toast Message | `Successfully added user` |

---

## Configuration Settings - Test Data

| # | Auth Type | Key | Value | Expected Toast |
|---|-----------|-----|-------|----------------|
| 1 | Adfs (default) | `access_keys_allowed` | `true` | Successfully modified adfs properties |
| 2 | Native | `access_keys_allowed` | `true` | Successfully modified native properties |

---

## Vector Settings - Test Data

### Model Creation (Background)

| Field | Value |
|-------|-------|
| Type | `OpenAI` |
| Variant | `GPT 3.5 Turbo` |
| Catalog Name | `Catalog` |
| API Key | `Test@1234` |
| Tags | `embeddings` |
| Expected Toast | `Successfully added LLM to catalog` |

### Vector Creation Examples

| # | Connection | Catalog Name | Model Name | Chunking Strategy | Content Length | Content Overlap |
|---|-----------|-------------|-----------|------------------|---------------|----------------|
| 1 | FAISS | FAISS Vector DB01 | Catalog | Token | 510 | 17 |
| 2 | FAISS | FAISS Vector DB02 | Catalog | Page by page | 512 | 19 |
| 3 | FAISS | FAISS Vector DB03 | Catalog | Markdown | 512 | 15 |

### Search Vector Validation

| # | Catalog Name | Validated In |
|---|-------------|--------------|
| 1 | FAISS Vector DB01 | Vector Settings card + Vector Catalog |
| 2 | FAISS Vector DB02 | Vector Settings card + Vector Catalog |
| 3 | FAISS Vector DB03 | Vector Settings card + Vector Catalog |

---

## Feature Files Summary

| # | Feature File | Tags | Scenarios | Description |
|---|-------------|------|-----------|-------------|
| 1 | Admin Query | `@LoginWithAdmin @Regression` | 2 Scenario Outlines (4 + 3 examples = 7 runs) | Validate Admin Query for databases; Validate query row count with Max Rows |
| 2 | Admin Settings | `@LoginWithAdmin @Regression` | 2 Scenarios | Check admin access to 12 settings tiles; Search settings page |
| 3 | Job Management | `@LoginWithAdmin @Regression` | 5 Scenarios | Edit Job, Delete Job, Run Job, Pause a Running Job, Resume a Paused Job |
| 4 | Settings Jobs | `@Regression` | 1 Scenario | Validate all elements on Jobs page (titles, tiles, tabs, buttons, tables, History) |
| 5 | Privacy Center Popup Validation | `@Regression` | 1 Scenario | Validate Privacy Center popup elements, close icon, Close button |
| 6 | Settings My Profile | `@Regression` | 4 Scenarios | My Profile sections; Generate access key; Delete access key; Personal Access Token validation |
| 7 | Validate Catalog User Permissions | `@LoginWithAuthor @DeleteTestCatalog @Regression` | 3 Scenario Outlines (1 + 4 examples + 1 Apps scenario = 6 runs) | Validate user permissions for Vector (ZIP), Model/Database/Function/Storage (file upload), Apps (Drag and Drop) |
| 8 | User Management | `@LoginWithAdmin @Regression` | 5 Scenarios | Add Native User; Edit Model Limit; Update Configuration (Adfs); Update Configuration (Native); Add user and validate profile |
| 9 | Search Vector Settings | `@LoginWithAdmin @DeleteTestCatalog @Regression @Smoke` | 2 Scenario Outlines (3 + 3 examples = 6 runs) | Create vectors; Search vectors in Vector Settings |

---

## Scenario Details

### Feature: Admin Query

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate '<DATABASE_NAME>' Admin query (Outline, 4 examples) | `@LoginWithAdmin @Regression` | Enables admin mode → Admin Query card → selects database → enters query → Run → validates columns count/names and success toast |
| 2 | Validate '<DATABASE_NAME>' Admin query count (Outline, 3 examples) | `@LoginWithAdmin @Regression` | Creates prerequisite models/jobs → Admin Query → selects database → enters Max Rows → enters query → Run → validates row count → cleanup |

### Feature: Admin Settings

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Check admin have access to each setting | `@LoginWithAdmin @Regression` | Background enables admin mode → validates all 12 settings tiles visible |
| 2 | Search on Settings Page | `@LoginWithAdmin @Regression` | Background enables admin mode → searches 'mod' → validates 'Model Settings' tile visible |

### Feature: Job Management

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Edit Job | `@LoginWithAdmin @Regression` | Background adds 'Test Job' → edits Tags to 2 and Pixels to '2+3' → saves → validates tag '2' → deletes → validates toast |
| 2 | Delete Job | `@LoginWithAdmin @Regression` | Background adds 'Test Job' → deletes → validates toast |
| 3 | Run Job | `@LoginWithAdmin @Regression` | Background adds 'Test Job' → selects checkbox → validates running and Pause enabled → deletes → validates toast |
| 4 | Pause a Running Job | `@LoginWithAdmin @Regression` | Background adds job → selects checkbox → clicks Pause → validates stopped, checkbox unselected, Pause reverted → deletes |
| 5 | Resume a Paused Job | `@LoginWithAdmin @Regression` | Background adds job → selects → Pause → stop verified → selects again → Resume → validates running, checkbox unselected, Resume reverted → deletes |

### Feature: Settings Jobs

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate elements present on jobs page | `@Regression` | Opens Settings → admin mode → Jobs → validates title, subtitle, status tiles, tabs, buttons (disabled state), search box, Jobs table columns, 'No jobs found' message → History table collapsed → expands → validates History columns, search box, 'No job history' message |

### Feature: Privacy Center Popup Validation

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Validate Privacy Center popup and its elements | `@Regression` | Opens Settings → clicks Privacy Center → validates popup elements (Popup, name, close icon, Close button) → closes via icon → validates closed → reopens → closes via Close button → validates closed |

### Feature: Settings My Profile

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Settings - My Profile Page | `@Regression` | Opens Settings → My Profile → validates Privacy Center link, Edit profile, Javascript SDK, Python SDK, Personal Access Tokens sections |
| 2 | Generate a new access key and verify details | `@Regression` | Opens My Profile → New Key → fills name 'New Key' / description 'New Description' → generates → copies Access Key (validates alphanumeric) → copies Secret Key (validates alphanumeric) → validates example sections (1 Access Key + 1 Secret Key each in Javascript/Python) → closes |
| 3 | Generated key - Delete | `@Regression` | Opens My Profile → New Key → fills name/description → generates → closes → deletes 'New Key' → validates toast 'Successfully deleted key' |
| 4 | Settings - Personal Access Token | `@Regression` | Opens My Profile → New Key → fills name/description → generates → closes → validates Personal Access Tokens section → validates key name 'New Key' → validates key description 'New Description' |

### Feature: Validate Catalog User Permissions

| # | Scenario | Tags | Examples | Description |
|---|---------|------|---------|-------------|
| 1 | Validate user access permissions (ZIP option) | `@LoginWithAuthor @DeleteTestCatalog @Regression` | Vector | Creates Vector via ZIP → adds Editor/Read → validates permissions → Editor changes/deletes roles → Read validates disabled icons → Author changes/deletes roles |
| 2 | Validate user access permissions (file upload) | `@LoginWithAuthor @DeleteTestCatalog @Regression` | Model, Database, Function, Storage | Creates catalog via file upload → same permission flow as above |
| 3 | Validate user access permissions of Apps | `@LoginWithAuthor @DeleteCreatedTestApp @Regression` | N/A | Creates App via Drag and Drop → same permission flow via App Settings card |

### Feature: User Management

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Add New Native User | `@LoginWithAdmin @Regression` | Opens Member Settings → adds 11 members → validates toast → searches → deletes 2 |
| 2 | Edit Native User - Change Model Limit Restriction | `@LoginWithAdmin @Regression` | Opens Member Settings → adds 1 member → clicks Edit → selects Token model → fills Max Tokens 2 → selects Weekly frequency → saves → validates new limit '2' → searches → deletes 1 |
| 3 | Update Configuration - access_keys_allowed - true - Adfs | `@LoginWithAdmin @Regression` | Opens Configuration card → clicks 'access_keys_allowed' → changes to 'true' → saves → validates toast 'Successfully modified adfs properties' |
| 4 | Update Configuration - access_keys_allowed - true - Native | `@LoginWithAdmin @Regression` | Opens Configuration card → clicks Authentication dropdown → searches/selects 'Native' → clicks 'access_keys_allowed' → changes to 'true' → saves → validates toast 'Successfully modified native properties' |
| 5 | Add New Native User - Validate profile info | `@LoginWithAdmin @Regression` | Opens Member Settings → adds 1 user with specific name/userId/password/email → logs out → logs in as new user → My Profile → validates User ID, Name, Email match → logs out → logs in as Admin → searches → deletes 1 |

### Feature: Search Vector Settings

| # | Scenario | Tags | Examples | Description |
|---|---------|------|---------|-------------|
| 1 | Create vector (Outline, 3 examples) | `@LoginWithAdmin @DeleteTestCatalog @Regression @Smoke` | FAISS Vector DB01/02/03 | Background creates model → creates FAISS vectors with Token/Page by page/Markdown chunking → validates success toast and Vector title |
| 2 | Validate Search Functionality (Outline, 3 examples) | `@DeleteTestCatalog @Regression @Smoke` | FAISS Vector DB01/02/03 | Creates vector → opens Settings → admin mode → Vector Settings card → validates title 'Vector Settings', vector cards, search bar → searches vector → validates visible → navigates to Vector Catalog → searches and selects vector |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 9 |
| When Steps | 95 |
| Then Steps | 62 |
| **Total** | **166** |

---