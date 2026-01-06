Feature: Documentation for Notebook

  @LoginWithAdmin @SkipIfVersionMatch @Documentation @DeleteCreatedTestApp
  Scenario: Capture a screenshot of the notebook query entry for documentation
    Given User captures documentation screenshot for 'Navigating/Create New App'
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User clicks on Get Started button in "Drag and Drop"
    And User enters app name as 'Test App'
    And User clicks on Create button
    And User fetch the app name for drag and drop app
    And User closes the Block Settings button
    And User clicks on Notebook
    And User deletes the notebook named 'mcp_driver'
    And User clicks on Create new notebook
    And User enters New Query name as "prompt output"
    And User clicks on query Submit button
    And User clicks on Create new notebook
    And User enters New Query name as "adding nums"
    And User clicks on query Submit button
    And User clicks on 'page-1' page
    And User highlight the 'page-1' page
    And User clicks on 'prompt output' page
    And User enters code as "from ai_server import ModelEngine\nmodel = ModelEngine(engine_id =\n\"4acbe913-df40-4ac0-b23a-da5ad91b172\")\n\n# Generation\n5 question = \"{prompt}\"\n6 output = model.ask(question = question, param_dict="
    And User clicks on the Block Settings option
    And User clicks on the Notebook 'prompt output'
    And User captures screenshot for app screens "NB1"
    And User mouse hover below the existing cell
    And User selects 'Import Data' from the hidden options
    And User captures screenshot for app screens "NB2"
    And User completes screenshot capture and triggers comparison for 'Notebook'
