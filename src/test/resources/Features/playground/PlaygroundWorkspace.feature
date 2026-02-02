Feature: Playground Home Workspace

  Background: Validate Playground workspace - creation/deletion/editing/searching/switching workspace
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User clicks on file upload icon
    And User uploads the file 'Model/Llama3-70B-Instruct.zip'
    And User clicks on 'Upload' button to create catalog
    When User clicks on Edit button
    And User add Tags 'text-generation' and presses Enter
    And User clicks on Submit button
    And User clicks on Copy Catalog ID
    And User is on Home page

  @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp.
  Scenario: Validate Playground workspace validatinos - add workspace
    Given User is on Home page
    When User clicks on Build button
    And User clicks on Try it out hyperlink for Experiment in our Playground
    And User sees the Prompt textarea with placeholder '/ to add capability'
    And User click on the workspace button
    And User clicks on Create New Workspace button
    And User enters workspace name as 'PlaygroundWorkspace1'
    And User enters workspace description as 'Workspace created by automation script'
    And User clicks on Create button to create workspace
    Then User verifies that 'PlaygroundWorkspace1' workspace is created with description 'Workspace created by automation script'
    When User click on the workspace button
    When User selects the 'PlaygroundWorkspace1' from the workspace list
    Then User verifies that 'PlaygroundWorkspace1' workspace is selected in the workspace list

  # @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp.
  # Scenario: Validate Playground workspace validatinos - edit workspace
  #   Given User is on Home page
  #   When User clicks on Try it out hyperlink for Experiment in our Playground
  #   And User sees the Prompt textarea with placeholder '/ to add capability'
  #   And User click on the workspace button
  #   And User selects the 'PlaygroundWorkspace1' from the workspace list
  #   And User clicks on Edit Workspace button
  #   And User updates workspace name to 'PlaygroundWorkspaceUpdated'
  #   And User updates workspace description to 'Workspace updated by automation script'
  #   And User clicks on Save button to save workspace changes
  #   Then User verifies that 'PlaygroundWorkspaceUpdated' workspace is updated with new description 'Workspace updated by automation script'
  #   When User selects the 'PlaygroundWorkspaceUpdated' from the workspace list
  #   Then User verifies that 'PlaygroundWorkspaceUpdated' workspace is selected in the workspace list

  @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp.
  Scenario: Validate Playground workspace validatinos - search workspace
    Given User is on Home page
    When User clicks on Try it out hyperlink for Experiment in our Playground
    And User sees the Prompt textarea with placeholder '/ to add capability'
    And User click on the workspace button
    And User enters 'PlaygroundWorkspace1' in the workspace search box
    Then User verifies that 'PlaygroundWorkspace1' workspace is displayed in the search results

  @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp.
  Scenario: Validate Playground workspace validatinos - switch workspace
    Given User is on Home page
    When User clicks on Try it out hyperlink for Experiment in our Playground
    And User sees the Prompt textarea with placeholder '/ to add capability'
    And User click on the workspace button
    And User selects the 'PlaygroundWorkspace1' as new chat from the workspace list
    Then User verifies that 'PlaygroundWorkspace1' workspace is selected in the workspace chat

  @LoginWithAdmin @Regression @DeleteTestCatalog @DeleteCreatedTestApp.
  Scenario: Validate Playground workspace validatinos - delete workspace
    Given User is on Home page
    When User clicks on Try it out hyperlink for Experiment in our Playground
    And User sees the Prompt textarea with placeholder '/ to add capability'
    And User click on the workspace button
    And User selects the 'PlaygroundWorkspace1' from the workspace list
    And User clicks on Delete Workspace button
    Then User verifies that workspace is deleted and not present in the workspace list
