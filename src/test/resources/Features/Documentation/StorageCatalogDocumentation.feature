Feature: Storage Catalog Documentation

  @LoginWithAdmin @SkipIfVersionMatch
  Scenario: Storage Catalog Documentation
    Given User captures documentation screenshot for 'Storage Catalog'
    When User opens Main Menu
    And User captures a 'button' and highlights the 'Storage'
    And User clicks on Open Storage engine
    And User captures a 'button' and highlights the 'Add Storage'
    When User clicks on Add Storage button
    And User captures a 'list item' and highlights the 'Amazon S3'
    And User selects 'Amazon S3' storage
    And User enter storage Catalog name as 'Test Storage'
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User captures a 'button' and highlights the 'Create storage'
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User can see the Storage title 'Test Storage'
    And User captures a 'button' and highlights the 'Export'
    And User captures a 'button' and highlights the 'Edit'
    And User clicks on 'Edit' button
    And User captures screenshot for "View Tabs"
    And User enters and selects 'CONFIDENTIAL' under 'Data classification' section
    And User captures a 'button' and highlights the 'Submit'
    And User clicks on 'Submit' button
    And User clicks on Settings Tab
    And User clicks on Make Discoverable button in storage settings
    And User logs out from the application
    Then User login as "editor"
    When User opens Main Menu
    And User clicks on Open Storage engine
    And User captures a 'button' and highlights the 'Discoverable Storages'
    And User clicks on 'Discoverable Storages' tab
    And User select the 'Test Storage' from the storage catalog
    And User captures a 'button' and highlights the 'Request Access'
    And User click on the Request Access button
    And User selects 'author' access
    And User captures screenshot for "Access Request"
    And User clicks on Request button
    And User logs out from the application
    Then User login as "admin"
    When User opens Main Menu
    And User clicks on Open Storage engine
    And User select the 'Test Storage' from the storage catalog
    And User clicks on Settings Tab for storage
    And User clicks on Delete button for storage
    Then User sees deleted storage success toast message 'Successfully deleted Storage'
    And User completes screenshot capture and triggers comparison for 'Storage Catalog'


