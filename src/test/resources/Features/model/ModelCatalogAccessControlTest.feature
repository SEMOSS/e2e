@Regression @DeleteTestCatalog @LoginWithAdmin
Feature: Validate change access permissions

  Background: Create a model, make it discoverable, login with another user, request for access, and login back with author user
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Model
    And User clicks on Add Model
    And User selects 'OpenAI' type
    And User selects 'GPT-4.1'
    And User enters Catalog Name as 'Model'
    And User enters Open AI Key as 'Test@1234'
    And User clicks on Create Model button
    And User clicks on Copy Catalog ID
    Then User can see the Model title as 'Model'
    When User clicks on Access Control Tab
    And User clicks Make 'Model' Discoverable button
    And User logs out from the application
    And User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Model
    And User clicks on Discoverable Models button
    And User searches the 'Model' in the model catalog searchbox
    And User selects the 'Model' from the model catalog
    And User click on the Request Access button
    And User selects 'author' access
    And User types a comment as 'Access Request'
    And User clicks on Request button
    And User logs out from the application
    And User login as 'admin'
    And User opens Main Menu
    And User clicks on Open Model
    And User searches the 'Model' in the model catalog searchbox
    And User selects the 'Model' from the model catalog
    And User clicks on Access Control Tab
    Then User should see '1 pending request' on Pending Requests section

  Scenario: Accept the request and validate user added in the member list
    When User clicks on the pending request expand button
    And User clicks on 'Approve' option in the Actions column
    Then User can see a toast message as 'Successfully approved user permissions'
    When User refresh the page
    Then User should see the 'editor' user in the Members list with 'author' permission

  Scenario: Reject the request and validate user not added in the member list
    When User clicks on the pending request expand button
    And User clicks on 'Reject' option in the Actions column
    Then User can see a toast message as 'Successfully denied user permissions'
    When User refresh the page
    Then User should not see the 'editor' user in the Members list with 'author' permission

  Scenario: Change the requested access and Accept it
    When User clicks on the pending request expand button
    And User change the requested access role to 'read' role
    And User clicks on 'Approve' option in the Actions column
    Then User can see a toast message as 'Successfully approved user permissions'
    When User refresh the page
    Then User should see the 'editor' user in the Members list with 'read' permission
