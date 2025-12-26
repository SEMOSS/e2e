Feature: Add CSV Database

  Background: Create Database using CSV file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    And User clicks on the 'file uploads' tab from options
    And User selects 'CSV' file type

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using CSV - AsFlatTable Metamodel
    When User enters 'DB created from CSV' as Database Name
    And User selects "h2" as database type 
    And User selects "asFlatTable" as Metamodel type
    And User uploads the file 'Database/diabetes.csv'
    And User clicks on Next button
    And User sees the colunm names with edit button and delete button
    And User clicks on Import button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from CSV'
    And User clicks on Copy Catalog ID

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using CSV - AsSuggestedMetaModel Metamodel
    When User enters 'DB created from CSV' as Database Name
    And User selects "h2" as database type 
    And User selects "asSuggestedMetaModel" as Metamodel type
    And User uploads the file 'Database/diabetes.csv'
    And User clicks on Next button
    And User clicks on save button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from CSV'
    And User clicks on Copy Catalog ID

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using CSV - fromScratch Metamodel
    When User enters 'DB created from CSV' as Database Name
    And User selects "h2" as database type 
    And User selects "fromScratch" as Metamodel type
    And User uploads the file 'Database/diabetes.csv'
    And User clicks on Next button
    And User sees the colunm names with edit button and delete button 
    And User clicks on Import button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from CSV'
    And User clicks on Copy Catalog ID

  @LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
  Scenario: Add Database using CSV - fromScratch Metamodel
    When User enters 'DB created from CSV' as Database Name
    And User selects "h2" as database type 
    And User selects "fromScratch" as Metamodel type
    And User uploads the file 'Database/diabetes.csv'
    And User uploads the file 'Database/diabetes2.csv'
    And User clicks on Next button
    And User sees the colunm names with edit button and delete button 
    And User clicks on Import button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from CSV'
    And User clicks on Copy Catalog ID