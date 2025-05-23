Feature: Add Storage
  I want to use this feature file for all scenarios related to the Add Storage

  Background: Add Amazon S3 Storage
    Given User clicks on Open Storage engine
    When User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters storage Catalog name as 'Amazon S3 Storage'
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User can see the Storage title as 'Amazon S3 Storage'

  Scenario: Validate SMSS properties of storage
    Given User can see the Storage title as 'Amazon S3 Storage'
    And User clicks on SMSS
    Then User can see storage name in 'NAME' field as 'Amazon S3 Storage' in SMSS properties
    And User can see storage region in 'S3_REGION' field as 'India' in SMSS properties
    And User can see storage bucket in 'S3_BUCKET' field as 'BucketTest' in SMSS properties
    And User can see storage access key in 'S3_ACCESS_KEY' field as 'Test123' in SMSS properties
    And User clicks on Open Storage engine

  Scenario: Validate Change access popup
    Given User can see the Storage title as 'Amazon S3 Storage'
    And 'Author' user clicks on Settings of Storage
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as "Editor"
    And User clicks on Open Storage engine
    And User searches the 'Amazon S3 Storage' in the storage Catalog searchbox
    And User selects the 'Amazon S3 Storage' from the storage catalog
    And User click on the Change Access button
    And User should see the "Change Access" popup with following options:
      | Author         |
      | Editor         |
      | Read-Only      |
      | Comment Box    |
      | Cancel Button  |
      | Request Button |
    And User click on cancel button
    And User logs out from the application
    Then User login as "Author"

  Scenario: Validate change access request
    Given User can see the Storage title as 'Amazon S3 Storage'
    And 'Author' user clicks on Settings of Storage
    And User clicks on Add Member button
    And User adds one user and assigns them as 'Editor'
    And User logs out from the application
    Then User login as "Editor"
    And User clicks on Open Storage engine
    And User searches the 'Amazon S3 Storage' in the storage Catalog searchbox
    And User selects the 'Amazon S3 Storage' from the storage catalog
    And User click on the Change Access button
