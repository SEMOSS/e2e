@DeleteCreatedTestApp
Feature: Notebook Scripting

  Background: Create Drag and Drop App
    Given User is on Home page
    When User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name 

  @LoginWithAdmin @Regression
  Scenario: Pixel Output in Notebook
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test query'
    And User clicks on query Submit button
    Then User Sees Python as the default language
    When User changes the language to 'Pixel'
    And User hovers and clicks on the cell
    And User deletes the previous cell
    And User enters code as 'Hello'
    And User clicks on Run this cell and below icon
    Then User can see Pixel output as 'Hello'

  @LoginWithAdmin @Regression
  Scenario: Python Output in Notebook
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    And User clicks on app Edit button
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'
    When User clicks on Notebook
    And User clicks on Create new notebook
    And User enters New Query name as 'Test query'
    And User clicks on query Submit button
    Then User Sees Python as the default language
    And User hovers and clicks on the cell
    And User deletes the previous cell
    And User enters code as '1+1'
    And User clicks on Run this cell and below icon
    Then User can see Python output as '2'
