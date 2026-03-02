Feature: Add Local File System Storage

  Background: Add Local File System Storage
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Storage
    And User clicks on Add Storage button
    And User selects 'Local File System' storage
    And User enters storage Catalog name as 'Local File System Storage'
    And User enters Path Prefix as 'local_storage'
    And User clicks on Connect button to create storage
    Then User can see create storage success toast message as 'Successfully added new storage to catalog'
    And User clicks on Copy Catalog ID
    And User can see the Storage title as 'Local File System Storage'

  @LoginWithAdmin @Regression
  Scenario: Delete Local File System Storage
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Storage
    And User searches 'Local File System Storage' storage in the storage searchbox
    And User clicks on created storage 'Local File System Storage'
    And User clicks on Access Control Tab
    And User clicks on Delete button for storage
    Then User sees deleted storage success toast message 'Successfully deleted Storage'
