Feature: Add TSV Database

  Background: Create Database using TSV file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    And User clicks on the 'file uploads' tab from options
    And User selects 'TSV' file type

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using TSV - AsFlatTable Metamodel
    When User enters 'DB created from TSV' as Database Name
    And User selects "h2" as database type 
    And User selects "asFlatTable" as Metamodel type
    And User uploads the file 'Database/Employee.tsv'
    And User clicks on Next button
    And User sees the colunm names with edit button and delete button 
    And User clicks on Import button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from TSV'
    And User clicks on Copy Catalog ID

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using TSV - AsSuggestedMetaModel Metamodel
    When User enters 'DB created from TSV' as Database Name
    And User selects "h2" as database type 
    And User selects "asSuggestedMetaModel" as Metamodel type
    And User uploads the file 'Database/Employee.tsv'
    And User clicks on Next button
    And User clicks on save button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from TSV'
    And User clicks on Copy Catalog ID

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using TSV - fromScratch Metamodel
    When User enters 'DB created from TSV' as Database Name
    And User selects "h2" as database type 
    And User selects "fromScratch" as Metamodel type
    And User uploads the file 'Database/Employee.tsv'
    And User clicks on Next button
    And User sees the colunm names with edit button and delete button 
    And User clicks on Import button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from TSV'
    And User clicks on Copy Catalog ID

@LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
 Scenario: Add Database using multiple TSV files - fromScratch Metamodel
    When User enters 'DB created from TSV' as Database Name
    And User selects "h2" as database type 
    And User selects "fromScratch" as Metamodel type
    And User uploads the file 'Database/Employee.tsv'
    And User uploads the file 'Database/Employee2.tsv'
    And User clicks on Next button
    And User sees the colunm names with edit button and delete button 
    And User clicks on Import button
    And User sees success toast message 'Successfully created database'
    And User can see the Catalog title as 'DB created from TSV'
    And User clicks on Copy Catalog ID