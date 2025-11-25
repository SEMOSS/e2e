@DeleteTestCatalog 
Feature: Create app using NLP Query to Grid Template

  Background: Create Database and Model using ZIP file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'Diabetes'
    And User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    When User selects 'ZIP'
    And User uploads the file 'Model/ModelZIP.zip'
    And User click on Create 'Model' button
    And User clicks on Copy Catalog ID
    Then User can see the Catalog title as 'Llama3-70B-Instruct'

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Create app using NLP Query to Grid Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects 'NLP Query To Grid' from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User see the 'page-1'
    And User see the 'Natural Language Query to Grid' block
    And User views description as 'Ask your query on the diabetes dataset'
    When User clicks on Notebook
    And User select the 'nlp-query' from notebook
    And User select the 'Llama3-70B-Instruct' model for 'nlp-query-1'
  	And User click on Run All cell button  	And User clicks on 'page-1' page
  	And User clicks on Preview app button 
    When User enter the query for people "over" the age "50"
  	And User click on Fetch Data
  	Then Results should contain only people with age "above" "50"
  	When User enter the query for people "below" the age "50"
  	And User click on Fetch Data
  	Then Results should contain only people with age "below" "50"
  	And User close the Preview app window
  	And User clicks on the Save App icon
		
		
		
