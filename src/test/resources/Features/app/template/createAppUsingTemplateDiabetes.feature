@DeleteTestCatalog @DeleteCreatedTestApp
Feature: Create app using Template
Background: User create the Diabetes database using zip file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    And User selects database 'ZIP'
    And User uploads database file 'Database/diabetes.zip'
    And User clicks on Create Database button
    And User clicks on the database name 'Diabetes' in the database catalog
    And User clicks On Copy Catalog ID

@LoginWithAdmin
Scenario: Create app using create Diabetes Record Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Create Diabetes Record" from Template List
    And User enters app name as 'Diabetes app'
    And User enters description as 'Diabetes app created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User clicks on Notebook
    And User clicks on Query name as 'insert-diabetes-record'
    And User clicks on Run cell button of database cell
    Then User can see the output for database cell

@LoginWithAdmin
Scenario: validate the Updated Diabetes Record app Template with data filling
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Create Diabetes Record" from Template List
    And User enters app name as 'Diabetes app'
    And User enters description as 'Diabetes app created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User clicks on Preview app button
    And user add value "1234" in "ID" field
    And user add value "23" in "AGE" field
    And user add value "Male" in "GENDER" field
    And user add value "Delhi" in "LOCATION" field
    And User click on Add Record button
    Then User sees the success message "true"

@LoginWithAdmin
Scenario: Create app using create Diabetes Record Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Create Diabetes Record" from Template List
    And User enters app name as 'Diabetes app'
    And User enters description as 'Diabetes app created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User clicks on Preview app button
    And user add value "16767" in "ID" field
    And user add value "35" in "AGE" field
    And user add value "Male" in "GENDER" field
    And user add value "Pune" in "LOCATION" field
    And User click on Add Record button
    Then User sees the success message "true"
    When User close the Preview app window
    And User clicks on Notebook
    And User clicks on Query name as 'insert-diabetes-record'
    And User clicks on Run cell button of database cell
    Then User can see the output for database cell
    When User modify the Sql query "SELECT * from diabetes WHERE ID=16767 AND AGE=35 AND LOCATION='Pune' AND GENDER='Male'"
    Then User sees the output of the executed query where 'AGE' is '35'
    And User sees the output of the executed query where 'LOCATION' is 'Pune'
    And User sees the output of the executed query where 'GENDER' is 'Male'