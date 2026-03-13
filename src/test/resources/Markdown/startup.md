# Startup BDD Steps Documentation

> **Last Updated:** February 26, 2026  
> **Project:** SemossWebQA E2E Tests  
> **Feature Path:** `src/test/resources/Features/startup/`

---

## Table of Contents

- [Overview](#overview)
- [Given Steps](#given-steps)
- [When Steps](#when-steps)
- [Then Steps](#then-steps)
- [Feature Files Summary](#feature-files-summary)

---

## Overview

This document contains **all BDD Cucumber steps** extracted exclusively from the startup-related feature files located under `src/test/resources/Features/startup/`. It covers two features:

1. **AI Core Application** — Native login flow
2. **Register New User** — User registration and subsequent login

---

## Given Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Given User logs out from the application` | AI Core Application, Register New User | Logs out the current user from the application |
| 2 | `Given User is on login page` | AI Core Application | Validates user is on the login page |

---

## When Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `When User enters nativeUsername and nativePassword` | AI Core Application | Enters native credentials (username and password) into login fields |
| 2 | `When User clicks on Login button` | AI Core Application, Register New User | Clicks the Login button to submit credentials |
| 3 | `When User clicks on Register Now button` | Register New User | Clicks the "Register Now" button on the login page |
| 4 | `When User fills below user creation form fields` | Register New User | Fills in the registration form using a data table of field name/value pairs |
| 5 | `When User clicks on Register button` | Register New User | Clicks the Register button to submit the registration form |
| 6 | `When User enters username as 'user<RANDOM_ID>' and password 'Pass@<RANDOM_ID>'` | Register New User | Enters the dynamically generated username and password on the login page |

---

## Then Steps

| # | Step Definition | Feature File(s) | Description |
|---|----------------|-----------------|-------------|
| 1 | `Then User can navigate to home page` | AI Core Application | Validates that the user has successfully navigated to the home page after login |
| 2 | `Then User can see 'Account registration successful. Log in below.' message on login page` | Register New User | Validates the success message is displayed after registration |
| 3 | `Then User should navigate to home page` | Register New User | Validates that the newly registered user has successfully navigated to the home page after login |

---

## Steps Count Summary

| Step Type | Count |
|-----------|-------|
| Given Steps | 2 |
| When Steps | 6 |
| Then Steps | 3 |
| **Total** | **11** |

---

## Feature Files Summary

| # | Feature File | Tags | Scenarios | Description |
|---|-------------|------|-----------|-------------|
| 1 | AI Core Application | *(none — commented out MS login)* | 1 Scenario | Native credential login flow |
| 2 | Register New User | `@Regression @Smoke` | 1 Scenario | Register a new user with dynamic fields and login with created credentials |

---

## Scenario Details

### Feature: AI Core Application

> **Note:** The first scenario (MS/SSO login) is commented out as it uses `AICoreTestManager` and a test user that is not available on localhost. Only the native login scenario is active.

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Login with native credentials | *(none)* | Logs out → navigates to login page → enters native username & password → clicks Login → validates home page |

#### Scenario Flow

```
Given User logs out from the application
  And User is on login page
 When User enters nativeUsername and nativePassword
  And User clicks on Login button
 Then User can navigate to home page
```

---

### Feature: Register New User

| # | Scenario | Tags | Description |
|---|---------|------|-------------|
| 1 | Register new user and login with created user | `@Regression @Smoke` | Logs out → clicks Register Now → fills registration form with dynamic data → clicks Register → validates success message → logs in with new credentials → validates home page |

#### Scenario Flow

```
Given User logs out from the application
 When User clicks on Register Now button
  And User fills below user creation form fields
      | FIELD_NAME       | FIELD_VALUE               |
      | First Name       | John                      |
      | Last Name        | Smith                     |
      | Username         | user<RANDOM_ID>           |
      | Email            | user<RANDOM_ID>@gmail.com |
      | Phone Number     | 12<RANDOM_ID>             |
      | Phone Extention  | ext.                      |
      | Country Code     | +91                       |
      | Password         | Pass@<RANDOM_ID>          |
      | Confirm Password | Pass@<RANDOM_ID>          |
  And User clicks on Register button
 Then User can see 'Account registration successful. Log in below.' message on login page
 When User enters username as 'user<RANDOM_ID>' and password 'Pass@<RANDOM_ID>'
  And User clicks on Login button
 Then User should navigate to home page
```

---

## Registration Form Fields

| # | Field Name | Field Value | Description |
|---|-----------|-------------|-------------|
| 1 | First Name | `John` | Static first name |
| 2 | Last Name | `Smith` | Static last name |
| 3 | Username | `user<RANDOM_ID>` | Dynamically generated username with random ID |
| 4 | Email | `user<RANDOM_ID>@gmail.com` | Dynamically generated email with random ID |
| 5 | Phone Number | `12<RANDOM_ID>` | Dynamically generated phone number with random ID prefix |
| 6 | Phone Extention | `ext.` | Static phone extension |
| 7 | Country Code | `+91` | Static country code (India) |
| 8 | Password | `Pass@<RANDOM_ID>` | Dynamically generated password with random ID |
| 9 | Confirm Password | `Pass@<RANDOM_ID>` | Must match Password field |

> **Note:** `<RANDOM_ID>` is a placeholder that gets replaced at runtime with a unique random identifier to ensure each test run creates a unique user.

---

## Tags Reference

| Tag | Feature File(s) | Description |
|-----|-----------------|-------------|
| `@Regression` | Register New User | Included in regression test suite |
| `@Smoke` | Register New User | Included in smoke test suite |

---

## Commented / Inactive Scenarios

### MS User Login (AI Core Application)

The following scenario is **commented out** in the feature file:

```gherkin
# Scenario: Login to the application
#   Given User is on application
#   When User enters username and password and click on SignIn button
#   Then User can navigate to home page
```

**Reason:** Uses `AICoreTestManager` and MS/SSO authentication which is not available on localhost environments.

#### Inactive Steps (Not Executed)

| # | Step Definition | Description |
|---|----------------|-------------|
| 1 | `Given User is on application` | Navigates to the application URL |
| 2 | `When User enters username and password and click on SignIn button` | Enters MS/SSO credentials and clicks Sign In |
| 3 | `Then User can navigate to home page` | Validates home page navigation (shared with active scenario) |

---