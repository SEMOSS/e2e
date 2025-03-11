# AI Core Playwright Setup

## AI Core
Make sure you have AI Core locally installed!

## Run Codegen
Use codegen to help write tests.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen http://localhost:9090/semoss-ui/packages/client/dist/#/"
```

# Testing Tickets - Contributor's Guide

If you have recently implemented a new functionality or page that will require e2e testing, you can help the testing team by either emailing them an outline of the relevant Features and Scenarios or creating the Epic and Task tickets yourself here in the e2e repo using the guidelines below. 

## Constructs & GitHub Setup

At a high level, each **page** in the AI Core platform is referred to a **Feature** and represented as an **Epic** ticket.

Within each page, each piece of **functionality** is referred to as a **Scenario** and is represented as a **Task** ticket. These Scenario Task tickets are the **children/sub-issues** of their associated Epic ticket. 

Each scenario ticket then contains one test formatted usning the **Given, When, Then** template:

`Given [the preconditions(s) or current state], 
When [event or user action],
Then [expected outcome]`

For example, the Epic ticket for the **Member Settings page** feature contains sub-issues for **User List** and **Add Native Account**. The User List scenario then tests the functionality that **given** a user is logged in as an admin, **when** they go to the Member Settings page, **then** they can properly use the pagination to view all users in the table. 

Feel free to view the current Issues for more detailed examples!

## Outline Example https://github.com/SEMOSS/e2e/issues/41

### [EPIC ISSUE] Feature: Member Settings page
#### [TASK SUB-ISSUE] Scenario 1: User List
- **Given:** a user is logged in as admin
- **When:** they go to the Member Settings page
- **Then:** they can properly use the pagination 

#### [TASK SUB-ISSUE] Scenario 2: Add Native Account
- **Given:** ...
- **When:** ...
- **Then:** ...

