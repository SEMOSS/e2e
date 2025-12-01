Feature: Variable Guide App

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Create Variable Guide App using Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects 'Variables Guide' from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User see the 'page-1'
    And User see the 'Variables Example' block
    And User views description as 'This is an app used to help you understand the usage of our variables within our drag and drop app  builder'
    And User can see the 'Engine Variables' block
    And User can click on the 'Engine Variables' block and see the Fonts Style and Size
    When User selects "Arial" font style
    And User changes font size to "48"
    Then The font style should be "Arial" and size should be "48"
    And User can see the 'Data Structure Variables' block
    And User can click on the 'Data Structure Variables' block and see the Fonts Style and Size
    When User selects "Roboto" font style
    And User changes font size to "38"
    Then The font style should be "Roboto" and size should be "38"
    And User can see the 'Block Variables' block
    And User can click on the 'Block Variables' block and see the Fonts Style and Size
    When User selects "Times New Roman" font style
    And User changes font size to "28"
    Then The font style should be "Times New Roman" and size should be "28"
    And User can see the 'Notebook Variables' block
    And User can click on the 'Notebook Variables' block and see the Fonts Style and Size
    When User selects "Georgia" font style
    And User changes font size to "18"
    Then The font style should be "Georgia" and size should be "18"
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Area Chart' block and drops it on the 'Variables Example'
    And User clicks on the Save App icon
