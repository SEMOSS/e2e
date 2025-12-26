Feature: Add Excel Database

  Background: Create Database using Excel file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    And User clicks on the 'file uploads' tab from options
    And User selects 'Excel' file type

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using Excel - AsFlatTable Metamodel
    When User enters 'DB created from Excel' as Database Name
    And User selects "h2" as database type 
    And User selects "asFlatTable" as Metamodel type
    And User uploads the file 'Database/Database.xlsx'
    And User clicks on Next button
    And User sees the colunm names with edit button and delete button 
    And User clicks on Import button
    And User sees success toast message 'Successfully Created Database'
    And User can see the Catalog title as 'DB created from Excel'
    And User clicks on Copy Catalog ID
