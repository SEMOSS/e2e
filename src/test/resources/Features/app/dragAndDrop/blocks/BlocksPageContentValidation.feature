@regression @DeleteCreatedTestApp
Feature: Block page content

  Background: Create Drag and Drop app and navigate to Blocks option
    Given User opens Main Menu
    When User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    Then User can see 'page-1' with the text 'Welcome to the UI Builder! Drag and drop blocks to use in your app.'

  Scenario: Validate the blocks page content
    Given User clicks on Blocks if it is not selected by default
    When User clicks on Block Settings option
    Then User see the 'Add Blocks' as title of the 'Blocks' option
    And User sees the search bar
    And User sees the 'System Blocks' section
    And User sees the 'Community Blocks' section
    And User sees the following options under 'System Blocks' section:
      | Section | Options                                                                            |
      | Layout  | Accordion, Container, Flip Card, Form, Iterator, Modal, Popover, Sidebar-Menu, Tab |
