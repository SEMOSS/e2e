Feature: Create Multi Page App using Template

  @LoginWithAdmin @DeleteCreatedTestApp @Regression
  Scenario: Create Multi Page App using Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects 'Multi Page' from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name 
    And User see the 'page-1'
    And User see the 'Landing Page' block
    And User see the 'Go to resources' hyperlink
    And User see the 'Go to About' hyperlink
    And User click on the 'Go to resources' hyperlink should point to 'SemossWeb/packages/client/dist/#/app/.*/view/resources'
    And User see the 'Resources' title after clicking resources hyperlink
    And User navigates to back page
    And User click on the 'Go to About' hyperlink should point to 'SemossWeb/packages/client/dist/#/app/.*/view/about'
    And User see the 'About' title after clicking about hyperlink
    And User navigates to back page
    And User clicks on Blocks if it is not selected by default
    And User drags the 'Area Chart' block and drops it on the 'landing page'
    And User clicks on the Save App icon
