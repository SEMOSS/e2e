@LoginWithAdmin @Regression
Feature: Validate the Build Page

  Scenario: Validate Build page title and buttons
    Given User is on Home page
    When User clicks on Build button
    Then User able to see the following Titles:
      | Empower your ideas with SEMOSS   |
      | Experiment in our Playground™    |
      | Simplify tasks with AI Conductor |
      | Get started with our tools       |
      | Drag and drop blocks             |
      | Develop in code                  |
      | Construct an agent               |
      | Try these fan favorites          |
      | BI                               |
      | Terminal                         |
    And User able to see the following Buttons:
      | drag  |
      | code  |
      | agent |
    And User able to see the "Browse Templates" button
