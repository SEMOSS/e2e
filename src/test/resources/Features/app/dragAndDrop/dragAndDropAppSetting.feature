@LoginWithAdmin @Regression @DeleteCreatedTestApps
Feature: Drag and Drop App setting Page

  Background: Create Drag and Drop app and navigate to Setting Page
    Given User opens Main Menu
    And User is on Home page
    And User clicks on Open App Library
    When User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'

  Scenario: Setting page - View Title, Member, Apps, General option
    Given User click on Settings
    Then 'Admin' user can 'view' Settings
    And User see the 'Settings' as title of the 'Settings' option
    And 'Admin' user can 'view' Members
    And 'Admin' user can 'view' Apps
    And 'Admin' user can 'view' General

  #Scenario: Setting page - validate the Member option for drag and drop app
    #Given User click on Settings
    #When User clicks on 'Members' option under Settings
    #Then User see the 'Members' page open on right side panel
    #And 'Admin' user can 'view' Export Icon
    #And User can see the 'Pending Requests' section on setting page
    #And User clicks on 'General' option under Settings
    #And User make the 'Apps' as 'Discoverable'
    #And User can see a toast message as 'Successfully made Model discoverable'
    #And User logs out from the application
    #Then User login as "editor"
    #And User opens Main Menu
    #And User clicks on Open App Library
    #And User click on 'Discoverable Models' tab
    #And User searches the 'Drag and Drop' in the model catalog searchbox
    #And User selects the 'Drag and Drop' from the model catalog
    #Given User can see the Model title as 'Drag and Drop'
    #And User click on the Request Access button
    #And User selects 'read-only' access
    #And User clicks on Request button
    #And User logs out from the application
    #Then User login as "Admin"
    #And User opens Main Menu
    #And User clicks on Open App Library
    #And User searches the 'Drag and Drop' in the model catalog searchbox
    #And User selects the 'Drag and Drop' from the model catalog
    #And User clicks on Access Control
    #And User click on Pending Request
    #And User 'Accept' request
    #And User can see the 'Permissions' section on setting page
    #And User clicks on Add Member button
    #And User adds one user and assigns them as 'Editor'
    #And User Search 'Editor' user from Access Control
    #And User deletes the 'Editor' user
    #And User clicks on Add Member button
    #And User adds one user and assigns them as 'Read'
    #And User Search 'Read' user from Access Control
    #And User deletes the 'Read' user
