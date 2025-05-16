Feature: View Storage
  I want to use this feature file for all scenarios related to View Existing Storage
  
  Background: Add Amazon S3 Storage
    Given User clicks on Open Storage engine
    When User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters storage Catalog name as 'Amazon S3 Test Storage'
    And User enters Region as 'India'
    And User enters Bucket as 'BucketTest'
    And User enters Access Key as 'Test123'
    And User enters Secret Key as 'Test123'
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User can see the Storage title as 'Amazon S3 Test Storage'
    
    Scenario: View my storages under 'My Storages' tab
    Given User clicks on Open Storage engine
    When User clicks on 'My Storages' tab
    Then User should see the Storage title as 'Amazon S3 Test Storage'
	  And User should see the 'No description available' in the description

    Scenario: Apply 'Tag' Filter to my storage
    Given User clicks on Open Storage engine
    When User clicks on 'My Storages' tab
    And User clicks on 'Amazon S3 Test Storage'
    And User clicks on 'Edit' button
    And User enters 'Test Tag' under 'Tag' and press enter
    And User clicks on 'Submit' button 
    Then User should see 'Test Tag' under 'Tag' in the Overview tab
    ### Scenario filter by the test tag on the main page
    Given User clicks on Open Storage Engine
    And User expands 'Filter By' section
    And User selects 'Test Tag' under 'Tag' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'
 
    Scenario: Apply 'Domain' Filter to my storage
    Given User clicks on Open Storage engine
    When User clicks on 'My Storages' tab
    And User clicks on 'Amazon S3 Test Storage'
    When User clicks on 'Edit' button
    And User enters 'Test Domain' under 'Domain' section and press enter
    And User clicks on 'Submit' button 
    Then User should see 'Test Domain' under 'Domain' in the Overview tab
    #### filter by domain on the main page
    Given User clicks on Open Storage Engine
    And User selects 'Test Domain' under 'Domain' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'
 
	  Scenario: Apply 'Data Classification' Filter to my storage
	  Given User clicks on Open Storage engine    
    When User clicks on 'My Storages' tab
    And User clicks on 'Amazon S3 Test Storage'
    When User clicks on 'Edit' button
    And User enters and selects 'CONFIDENTIAL' under 'Data classification' section
    And User clicks on 'Submit' button 
    Then User should see 'CONFIDENTIAL' under 'Data classification' in the Overview tab
    ### filter by data classification on the main page
    Given User clicks on Open Storage Engine 
    And User selects 'CONFIDENTIAL' under 'Data Classification' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'
 
    Scenario: Apply 'Data Restrictions' Filter to discoverable storage
    Given User clicks on Open Storage engine    
    When User clicks on 'My Storages' tab
    And User clicks on 'Amazon S3 Test Storage'
    And User clicks on 'Edit' button
    And User enters and selects 'CONFIDENTIAL ALLOWED' under 'Data restrictions' section
    And User clicks on 'Submit' button 
    Then User should see 'CONFIDENTIAL ALLOWED' under 'Data restrictions' in the Overview tab
    ###Scenario: Filter my storage by 'Data Restrictions' on the main page
    Given User clicks on Open Storage Engine 
    And User selects 'CONFIDENTIAL ALLOWED' under 'Data Restrictions' section
    Then User should see the Storage title as 'Amazon S3 Test Storage'
    
    
       