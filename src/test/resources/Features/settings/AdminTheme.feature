@Regression
Feature: Admin Theme Settings

  Background: 
    Given User opens Main Menu
    And User clicks on Open Settings
    When User enables admin mode
    And User clicks on 'Admin Theme' Card

  Scenario: Verify Admin Theme page is loaded successfully
    Given User should see page title "Admin Theme"
    Then User should see breadcrumb "Admin Theme"
    And User should see subtitle "Update theming for the instance"

  Scenario: Verify Admin Theme page elements are displayed
    Given User should see page title "Admin Theme"
    Then User should see Select Theme dropdown
    And User should see "Name" input field
    And User should see "JSON" editor section
    And User should see "Save" button
    And User should see "Activate" button
    And User should see "Delete" button
    And User should see "Create Theme" button
    And User should see "Privacy Center" link
    And User should see "Admin On" option

  Scenario: Create the Admin Theme
    Given User should see page title "Admin Theme"
    When User clicks on "Create Theme" button
    And User enters "Test Theme" name in the input field
    Then User click on "Create" button in the Create Theme dialog
    And User should see the Toast message "Theme created successfully"

  Scenario: Delete the Admin Theme
    Given User should see page title "Admin Theme"
    When User clicks on "Create Theme" button
    And User enters "Test Theme" name in the input field
    And User click on "Create" button in the Create Theme dialog
    And User should see the Toast message "Theme created successfully"
    When User clicks on "Delete" button
    Then User should see the Toast message "Theme deleted successfully"
