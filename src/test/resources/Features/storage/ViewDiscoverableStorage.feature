############Scenario: View discoverable storages under 'Discoverable Storages' tab
#### for this to work we need to:
##### create a storage as an author
##### go to access control/settings
##### click on make discoverable
#### login as editor
#### go to discoverable storages
#### user should see the storage the author created

#Feature: View Existing Discoverable Storage
  #I want to use this feature file for all scenarios related to View Existing Storage
  #
  #Background: Add Amazon S3 Storage
    #Given User clicks on Open Storage engine
    #When User clicks on Add Storage button
    #And User selects 'Amazon S3' storage
    #And User enters storage Catalog name as 'Amazon S3 Test Storage'
    #And User enters Region as 'India'
    #And User enters Bucket as 'BucketTest'
    #And User enters Access Key as 'Test123'
    #And User enters Secret Key
    #And User clicks on Create Storage button
    #Then User can see create storage success toast message as 'Successfully added to catalog storage'
    #And User can see the Storage title as 'Amazon S3 Test Storage'
   #
    #@LoginWithAuthor
    #Scenario: View discoverable storages under 'Discoverable Storages' tab
    #When User clicks on 'Discoverable Storages' tab
    #Then User should see the Storage title as 'Amazon S3 Test Storage'
      #And User should see the 'No description available' in the description
#		
    #Scenario: Filter discoverable storage by 'Data Classification'
    #When User clicks on 'Discoverable Storages' tab
    #And User selects 'CONFIDENTIAL' under 'Data Classification' section
    #Then User should see the Storage title as 'Amazon S3 Test Storage'
   #
    #Scenario: Filter discoverable storage by 'Data Restrictions'
    #When User unselects 'CONFIDENTIAL' under 'Data Classification' section
    #And User selects 'CONFIDENTIAL ALLOWED' under 'Data Restrictions' section
    #Then User should see the Storage title as 'Amazon S3 Test Storage'