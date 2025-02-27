Feature: Connect to Storage
  I want to use this feature file for all scenarios related to the Add Storage

  Background: Login to the application
    Given User is on application
    When User enters username and password and click on SignIn button
    Then User can navigate to home page

  Scenario: Add Amazon S3 Storage
    Given User clicks on Open Storage engine
    When User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    And User enters Catlog name as 'Amazon S3 Storage'
    And User enters Region as 'India'
    And User enters Access Key as 'Test123'
    And User enters Secret Key
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User Can see the Storage title as 'Amazon S3 Storage'
    And User clicks on SMSS
    And User can see storage name in 'NAME' field as 'Amazon S3 Storage' in SMSS properties
    And User can see storage region in 'S3_REGION' field as 'India' in SMSS properties
    And User can see storage access key in 'S3_ACCESS_KEY' field as 'Test123' in SMSS properties