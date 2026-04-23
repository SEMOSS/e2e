Feature: Storage Page Filters

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: Validate the Storage filter on app
    Given User created '1' storage with the 'Amazon S3' storage, catalog name 'Ascending Amazon S3 Storage', Region 'India', Bucket 'BucketTest'
    When User created '1' storage with the 'Amazon S3' storage, catalog name 'Descending Amazon S3 Storage', Region 'India', Bucket 'BucketTest'
    And User opens Main Menu
    And User clicks on Open Storage
    And User clicks on the 'Ascending' Filter button
    Then User can see the "Storage" are sorted in ascending order
    When User clicks on the 'Descending' Filter button
    Then User can see the "Storage" are sorted in descending order
    And User opens Main Menu
    And User clicks on Open Storage
    When User selects 'Date Created' from the Sort By dropdown
    And User clicks on the 'Ascending' Filter button
    Then User can see the 'Storage' are sorted by date created in 'ascending' order
    When User clicks on the 'Descending' Filter button
    Then User can see the 'Storage' are sorted by date created in 'descending' order
