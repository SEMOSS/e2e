@DeleteCreatedTestApp @Regression
Feature: Code app files

  Background: Create the code app
    Given User is on Home page
    And User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Develop in code"
    And User enters app name as 'Code app'
    And User enters description as 'Created by automation script'
    And User enters tags 'MCP' and presses Enter
    And User clicks on Create button
    And User fetch the app name

  Scenario: Upload a file and publish file in the code app
    Given User clicks on the file icon in the left panel
    And User clicks on Create at Icon on File Tab
    And User select Action as 'Upload Files'
    When User uploads the file 'Playground/mcp.zip'
    Then User clicks on 'Upload' button to create code app
    And User can see the 'mcp.zip' folder in the Files section
    And User clicks on the publish icon to publish the code app
    And User sees success toast message 'Successfully compiled and published'

  Scenario: Create a Directory in the files section for code app
    Given User clicks on the file icon in the left panel
    And User clicks on Create at Icon on File Tab
    And User select Action as 'New Directory'
    When User enter the folder name as 'TestFolder'
    And User click on Create button
    Then User can see the 'TestFolder' folder in the Files section

  @ApplicationBugFailure
  Scenario: Create a file in the files section for code app
    Given User clicks on the file icon in the left panel
    And User clicks on Create at Icon on File Tab
    And User select Action as 'New File'
    When User enter the file name as 'TestFile'
    And User click on Create button
    Then User can see the 'TestFile' file in the Files section
    And User click on the created 'TestFile' file
    And User Edit File with some content as 'dummydata'
    And User Save the file

  Scenario: Create file and folder under the created Directory in the files section for code app
    Given User clicks on the file icon in the left panel
    And User clicks on Create at Icon on File Tab
    And User select Action as 'New Directory'
    When User enter the folder name as 'TestFolder'
    And User click on Create button
    Then User can see the 'TestFolder' folder in the Files section
    And User clicks on the 'TestFolder' folder in the Files section
    And User clicks on Create at Icon on File Tab
    And User select Action as 'New Directory'
    When User enter the folder name as 'SubFolder'
    And User click on Create button
    Then User can see the 'SubFolder' folder under 'TestFolder' in the Files section
    And User clicks on Create at Icon on File Tab
    And User select Action as 'New File'
    When User enter the file name as 'SubFile'
    And User click on Create button
    And User can see the 'SubFile' file under 'TestFolder' in the Files section

  Scenario: Refresh Files option is enabled and clickable
    Given User clicks on the file icon in the left panel
    When The Refresh Files option should be visible
    And The Refresh Files option should be clickable

  Scenario: Publish code app and verify access using shared URL
    Given User clicks on the file icon in the left panel
    And User clicks on Create at Icon on File Tab
    And User select Action as 'Upload Files'
    When User uploads the file 'Playground/mcp.zip'
    Then User clicks on 'Upload' button to create code app
    And User can see the 'mcp.zip' folder in the Files section
    And User clicks on the publish icon to publish the code app
    And User sees success toast message 'Successfully compiled and published'
    And User click on Share App link
    And User click on Copy button for Url
    And User sees success toast message 'Successfully copied to clipboard'
    And User open the new tab
    And User paste the URl on new tab
    And User able to see the 'Code app' title on the new tab page
    And User move to main page

  @ApplicationBugFailure
  Scenario: Edit the uploaded file in code app and verify changes in shared url link
    Given User clicks on the file icon in the left panel
    And User clicks on Create at Icon on File Tab
    And User select Action as 'Upload Files'
    When User uploads the file 'Playground/mcp.zip'
    And User clicks on 'Upload' button to create code app
    And User can see the 'mcp.zip' folder in the Files section
    And User clicks on three dot icon of 'mcp.zip' file
    And User select the 'Unzip' file option
    And User can see the 'py' folder in the Files section
    And User clicks on the 'portals' folder in the Files section
    And User click on the created 'index.html' file
    And User edit file for change title as 'Get New Stock Updated'
    And User Save the file
    And User clicks on the publish icon to publish the code app
    Then User sees success toast message 'Successfully compiled and published'
    And User click on Share App link
    And User click on Copy button for Url
    And User sees success toast message 'Successfully copied to clipboard'
    And User open the new tab
    And User paste the URl on new tab
    And User able to see the 'Get New Stock Updated' title on the new tab page
    And User move to main page

@ApplicationBugFailure
  Scenario: Edit the uploaded file in code app and verify changes in code app preview
     Given User clicks on the file icon in the left panel
    And User clicks on Create at Icon on File Tab
    And User select Action as 'Upload Files'
    When User uploads the file 'Playground/mcp.zip'
    And User clicks on 'Upload' button to create code app
    And User can see the 'mcp.zip' folder in the Files section
    And User clicks on three dot icon of 'mcp.zip' file
    And User select the 'Unzip' file option
    And User can see the 'py' folder in the Files section
    And User clicks on the 'portals' folder in the Files section
    And User click on the created 'index.html' file
    And User edit file for change title as 'Get New Stock Updated'
    And User Save the file
    And User clicks on the publish icon to publish the code app
    And User sees success toast message 'Successfully published'
    And User click on 'Code app' from breadcrumb link
    And User able to see the 'Get New Stock Updated' title on the page
