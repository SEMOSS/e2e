Feature: View existing Storages on storage Catalog Page

  Background: Add Amazon S3 Storage
    Given User opens Main Menu
    When User clicks on Open Storage
    And User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters storage Catalog name as 'Amazon S3 Storage'
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User clicks on Create Storage button
    And User clicks On Copy Catalog ID
    And User clicks on Edit button
    And User add tags 'embeddings, Test1' and presses Enter
    And User enters the Domains as 'SAP, AI'
    And User selects 'PUBLIC, RESTRICTED' from the Data Classification dropdown
    And User selects 'FOUO ALLOWED, INTERNAL ALLOWED' from the Data Restrictions dropdown
    And User clicks on Submit button
    
  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: view and validate filter functionality - My Storages
    Given User opens Main Menu
    When User clicks on Open Storage
    And User searches the 'Amazon S3 Storage' in the storage Catalog searchbox
    Then User applies each filter and validate 'Amazon S3 Storage' catalog is visible on the 'storage' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE      |
      | Tag                 | embeddings, Test1 |
      | Domain              | SAP, AI           |
      | Data Classification | PUBLIC            |
      | Data Restrictions   | FOUO ALLOWED      |
    #When User clicks on bookmark button of 'Amazon S3 Storage' catalog
    #Then User sees the catalog name 'Amazon S3 Storage' in the Bookmarked section
    #When User clicks on bookmark button to unbookmark 'Amazon S3 Storage' catalog

  @DeleteTestCatalog @Regression
  Scenario: view and validate filter functionality - Discoverable Storages
    Given User opens Main Menu
    When User clicks on Open Storage
    And User searches the 'Amazon S3 Storage' in the storage Catalog searchbox
    And User selects the 'Amazon S3 Storage' from the storage catalog
    And User clicks on Access Control Tab
    And User clicks Make 'Storage' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Storage
    And User searches the 'Amazon S3 Storage' in the storage Catalog searchbox
    And User clicks on Discoverable Storages button
    Then User applies each filter and validate 'Amazon S3 Storage' catalog is visible on the 'storage' catalog page
      | FILTER_CATEGORY     | FILTER_VALUE     |
      | Data Classification | RESTRICTED       |
      | Data Restrictions   | INTERNAL ALLOWED |
    And User logs out from the application
    And User login as 'Admin'
