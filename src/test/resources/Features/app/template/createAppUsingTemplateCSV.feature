Feature:Create app using Template 

Background: Create app using Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Visualize CSV" from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User sees the title as 'Visualize data from upload'
    And User sees the sub title as 'This app allows you to upload a CSV file shows data from it in our dynamic data grid'
    And User clicks on the Save App icon

@LoginWithAdmin @Regression
Scenario: Create app using Template Visualize CSV
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    Then User sees the title as 'Visualize data from upload'
    And User clicks on app Edit button
    And User clicks on the title as 'Visualize data from upload'
    And User clicks on the Block Settings option
    And User change title 'Visualize data from upload' with 'Visualize data from upload - Edited'
    And User clicks on the Save App icon
    Then User sees the title as 'Visualize data from upload - Edited'
