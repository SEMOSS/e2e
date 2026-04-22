@Regression @DeleteTestCatalog @LoginWithAdmin
Feature: Validate change access permissions

  Background: Create a Model - GPT-4.1
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
    And User clicks on Access Control Tab
    And User clicks Make 'Model' Discoverable button
    And User logs out from the application
    Then User login as 'editor'
    And User opens Main Menu
    And User clicks on Open Model
    And User clicks on Discoverable Models button
    And User searches the 'Model' in the model catalog searchbox
    And User selects the 'Model' from the model catalog
    And User click on the Request Access button

  Scenario: Accept request
    When User selects 'author' access
    And User types a comment as 'Access Request'
    And User clicks on Request button
    And User logs out from the application
    Then User login as 'admin'
    And User opens Main Menu
    And User clicks on Open Model
    And User searches the 'Model' in the model catalog searchbox
    And User selects the 'Model' from the model catalog
    And User clicks on Access Control Tab
    Then User should see '1 pending request' on Pending Requests section
    When User clicks on the pending request expand button
    When User clicks on 'Approve' option in the Actions column
    Then User can see a toast message as 'Successfully approved user permissions'
    When User refresh the page
    Then User should see the 'editor' user in the Members list with 'author' permission
