#Feature: View Existing Storage
  #I want to use this feature file for all scenarios related to View Existing Storage
  #
  #Background: View My Storages
    #Given User clicks on Open Storage Engine
    #And User has added storages in the system
    #
    #Scenario: View my storages under 'My Storages' tab
    #When User clicks on 'My Storages' tab
    #Then User should see the Storage title as 'Amazon S3 Test Storage'
#	    And User should see the 'No description available' in the description
#
    #Scenario: Apply 'Tag' Filter to my storage
    #When User clicks on 'My Storages' tab
    #And User clicks on 'Amazon S3 Test Storage'
    #And User clicks on 'Edit' button
    #And User enters 'Test Tag' under 'Tag' and press enter
    #And User clicks on 'Submit' button 
    #Then User should see 'Test Tag' under 'Tag' in the Overview tab
 #
    #Scenario: Apply 'Domain' Filter to my storage
    #When User clicks on 'My Storages' tab
    #And User clicks on 'Amazon S3 Test Storage'
    #When User clicks on 'Edit' button
    #And User enters 'Test Domain' under 'Domain' section and press enter
    #And User clicks on 'Submit' button 
    #Then User should see 'Test Domain' under 'Domain' in the Overview tab
 #
#	  Scenario: Apply 'Data Classification' Filter to my storage
    #When User clicks on 'My Storages' tab
    #And User clicks on 'Amazon S3 Test Storage'
    #When User clicks on 'Edit' button
    #And User enters and selects 'CONFIDENTIAL' under 'Data classification' section
    #And User clicks on 'Submit' button 
    #Then User should see 'CONFIDENTIAL' under 'Data classification' in the Overview tab
 #
    #Scenario: Apply 'Data Restrictions' Filter to discoverable storage
    #When User clicks on 'My Storages' tab
    #And User clicks on 'Amazon S3 Test Storage'
    #And User clicks on 'Edit' button
    #And User enters and selects 'CONFIDENTIAL ALLOWED' under 'Data restrictions' section
    #And User clicks on 'Submit' button 
    #Then User should see 'CONFIDENTIAL ALLOWED' under 'Data restrictions' in the Overview tab
 #
    #Scenario: Filter my storage by 'Tag'
    #When User clicks on Open Storage Engine
    #And User expands 'Filter By' section
    #And User selects 'Test Tag' under 'Tag' section
    #Then User should see the Storage title as 'Amazon S3 Test Storage'
  #
    #Scenario: Filter my storage by 'Domain'
    #When User unselects 'Test Tag' under 'Tag' section
    #And User selects 'Test Domain' under 'Domain' section
    #Then User should see the Storage title as 'Amazon S3 Test Storage'
   #
    #Scenario: Filter my storage by 'Data Classification'
    #When User unselects 'Test Domain' under 'Domain' section
    #And User selects 'CONFIDENTIAL' under 'Data Classification' section
    #Then User should see the Storage title as 'Amazon S3 Test Storage'
    #
    #Scenario: Filter my storage by 'Data Restrictions'
    #When User unselects 'CONFIDENTIAL' under 'Data Classification' section
    #And User selects 'CONFIDENTIAL ALLOWED' under 'Data Restrictions' section
    #Then User should see the Storage title as 'Amazon S3 Test Storage'
    #
    #
       