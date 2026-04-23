Feature: Database page Filters

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: Validate the filter on database
    Given User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'TestDatabase'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/TestDatabase.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    When User clicks on Open Database
    And User checks if 'Database' catalog created and Deletes the 'diabetes'
    And User clicks on Add Database
    And User clicks on file upload icon
    And User uploads the file 'Database/diabetes.zip'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User opens Main Menu
    When User clicks on Open Database
    And User clicks on the 'Ascending' Filter button
    Then User can see the 'Database' are sorted in ascending order
    When User clicks on the 'Descending' Filter button
    Then User can see the 'Database' are sorted in descending order
    And User opens Main Menu
    When User clicks on Open Database
    When User selects 'Date Created' from the Sort By dropdown
    And User clicks on the 'Ascending' Filter button
    Then User can see the 'Database' are sorted by date created in 'ascending' order
    When User clicks on the 'Descending' Filter button
    Then User can see the 'Database' are sorted by date created in 'descending' order