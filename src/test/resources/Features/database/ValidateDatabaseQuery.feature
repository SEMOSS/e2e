Feature: Validate Database Query Functionality

  Background: Validate database query response
    Given User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    When User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User can see the Catalog title as 'TestDatabase'
    And User clicks on Query tab

  #@LoginWithAdmin @Regression @DeleteTestCatalog
  #Scenario: Validate database query response
    #When User enters the query 'SELECT AGE, BMI from DIABETES'
    #And User clicks on 'Run Query' button
    #Then User sees 'AGE, BMI' columns in the query response table
#
  #@LoginWithAdmin @Regression @DeleteTestCatalog
  #Scenario: Validate database query response
    #When User enters the query 'SELECT AGE, BMI from DIABETES'
    #And User clicks on 'Reset' button
    #Then User can see query field is empty

  @LoginWithAdmin @Regression @DeleteTestCatalog
  Scenario: Validate collapse data columns
    And User clicks on 'Collapse All' button
    Then User can see all data columns are collapsed
