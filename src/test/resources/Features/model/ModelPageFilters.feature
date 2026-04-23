Feature: Model Page Filters

  @LoginWithAdmin @DeleteTestCatalog @Regression
  Scenario: Validate the Model filter on app
    Given User created '1' models with the 'OpenAI' model 'GPT-5', catalog name 'Ascending Model', OpenAI key 'Test123'
    When User created '1' models with the 'OpenAI' model 'GPT-5', catalog name 'Descending Model', OpenAI key 'Test123'
    And User opens Main Menu
    And User clicks on Open Model
    And User clicks on the 'Ascending' Filter button
    Then User can see the "Model" are sorted in ascending order
    When User clicks on the 'Descending' Filter button
    Then User can see the "Model" are sorted in descending order
    And User opens Main Menu
    And User clicks on Open Model
    When User selects 'Date Created' from the Sort By dropdown
    And User clicks on the 'Ascending' Filter button
    Then User can see the "Model" are sorted by date created in 'ascending' order
    When User clicks on the 'Descending' Filter button
    Then User can see the "Model" are sorted by date created in 'descending' order
