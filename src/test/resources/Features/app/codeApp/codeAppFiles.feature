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
    And User fetch the app name for drag and drop app

  Scenario: Upload a file to code app
    Given User clicks on the file icon in the left panel
    When User uploads the file 'PlaygroundMCP/mcp.zip'
    And User selects the unzip checkbox
    Then User clicks on 'Upload' button to create catalog
    And User can see the 'py' folder in the Files section
    And User can see the 'mcp' folder in the Files section
    And User clicks on the publish icon to publish the code app
    And User sees success toast message 'Successfully published'

  Scenario: Create a folder in the files section for code app
    Given The Files section should be open by default
    And User clicks on Create New Folder icon
    When User enter the folder name as 'TestFolder'
    And User click on Create button
    Then User can see the 'TestFolder' folder in the Files section

  Scenario: Create a file in the files section for code app
    Given The Files section should be open by default
    And User clicks on Create New File icon
    When User enter the file name as 'TestFile'
    And User click on Create button
    Then User can see the 'TestFile.txt' file in the Files section
    And User click on the Created 'TestFile.txt' file
    And User Edit File with some content as 'dummydata'
    And User Save the file

  Scenario: Create file and folder under the created folder in the files section for code app
    Given The Files section should be open by default
    And User clicks on Create New Folder icon
    When User enter the folder name as 'TestFolder'
    And User click on Create button
    Then User can see the 'TestFolder' folder in the Files section
    And User clicks on the 'TestFolder' folder in the Files section
    And User clicks on Create New Folder icon
    When User enter the folder name as 'SubFolder'
    And User click on Create button
    Then User can see the 'SubFolder' folder under 'TestFolder' in the Files section
    And User clicks on the 'TestFolder' folder in the Files section
    And User clicks on Create New File icon
    And User enter the file name as 'SubFile'
    And User click on Create button
    And User can see the 'SubFile' file under 'TestFolder' in the Files section

  Scenario: Refresh Files option is enabled and clickable
    Given The Files section should be open by default
    When The Refresh Files option should be visible
    And The Refresh Files option should be clickable

  Scenario: Check the Recompile Refactor option is enabled and clickable
    Given The Files section should be open by default
    When The Recompile Refactor option should be visible
    Then User click on the  Recompile Refactor option
    And User sees success toast message 'Successfully recompiled reactors. Remember to publish changes.'

  Scenario: Publish code app and verify access using shared URL
    Given User clicks on the file icon in the left panel
    When User uploads the file 'PlaygroundMCP/mcp.zip'
    And User selects the unzip checkbox
    Then User clicks on 'Upload' button to create catalog
    And User can see the 'py' folder in the Files section
    And User can see the 'mcp' folder in the Files section
    And User clicks on the publish icon to publish the code app
    And User sees success toast message 'Successfully published'
    And User click on Share App link
    And User click on Copy button for Url
    And User sees success toast message 'Successfully copied to clipboard'
    And User open the New Tab
    And User paste the URl on new tab
    And User able to see the 'Get Stock' on the new tab page
    And User move to main page
