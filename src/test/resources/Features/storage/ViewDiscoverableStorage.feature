Feature: View Existing Discoverable Storage

  Background: Add Amazon S3 Storage
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Storage engine
    And User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters storage Catalog name as 'Amazon S3 Test Storage'
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User can see the Storage title as 'Amazon S3 Test Storage'

  @LoginWithAdmin
  Scenario: View discoverable storages under 'Discoverable Storages' tab
    And User clicks on Settings Tab
    And User clicks on Make Discoverable button in storage settings
    And User logs out from the application
    Then User login as "editor"
    When User opens Main Menu
    And User clicks on Open Storage engine
    And User clicks on 'Discoverable Storages' tab
    And User searches the 'Amazon S3 Test Storage' in the storage Catalog searchbox
    Then User should see the Storage title as 'Amazon S3 Test Storage'
    When User logs out from the application
    And User login as "Admin"

  @LoginWithAdmin
  Scenario: Filter discoverable storage by 'Data Classification'
    When User clicks on 'Edit' button
    And User enters and selects 'CONFIDENTIAL' under 'Data classification' section
    And User clicks on 'Submit' button
    And User clicks on Settings Tab
    And User clicks on Make Discoverable button in storage settings
    And User logs out from the application
    Then User login as "editor"
    And User opens Main Menu
    And User clicks on Open Storage engine
    And User clicks on 'Discoverable Storages' tab
    And User searches the 'Amazon S3 Test Storage' in the storage Catalog searchbox
    And User selects 'CONFIDENTIAL' under 'Data Classification' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'
    When User logs out from the application
    And User login as "Admin"

  @LoginWithAdmin
  Scenario: Filter discoverable storage by 'Data Restrictions'
    When User clicks on 'Edit' button
    And User enters and selects 'CONFIDENTIAL ALLOWED' under 'Data restrictions' section
    And User clicks on 'Submit' button
    And User clicks on Settings Tab
    And User clicks on Make Discoverable button in storage settings
    And User logs out from the application
    Then User login as "editor"
    And User opens Main Menu
    And User clicks on Open Storage engine
    And User clicks on 'Discoverable Storages' tab
    And User searches the 'Amazon S3 Test Storage' in the storage Catalog searchbox
    And User selects 'CONFIDENTIAL ALLOWED' under 'Data Restrictions' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'