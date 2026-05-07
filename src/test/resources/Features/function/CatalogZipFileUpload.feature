Feature: Validate zip catalog upload functionality in Files Section for all catalog types

  @LoginWithAuthor @DeleteTestCatalog @Regression
  Scenario Outline: Validate zip catalog upload functionality in Files Section for '<CATALOG>' catalog
    Given User opens Main Menu
    When User opens '<CATALOG>'
    And User checks if '<CATALOG>' catalog created and Deletes the '<CATALOG_NAME>'
    And User clicks on Add '<CATALOG>' button
    And User clicks on file upload icon
    And User uploads the file '<FILE_NAME>'
    And User clicks on 'Upload' button to create catalog
    And User clicks on Copy Catalog ID
    And User clicks on File Tab
    And User clicks on Create at Icon on File Tab
    And User select Action as 'Upload Files'
    And User uploads the file 'ModelZIP.zip'
    And User clicks on 'Upload' button to create code app
    Then User can see the 'ModelZIP.zip' folder in the Files section
    When User clicks on Create at Icon on File Tab
    And User select Action as 'New Directory'
    And User enter the folder name as 'TestFolder'
    And User click on Create button
    Then User can see the 'TestFolder' folder in the Files section
    And User clicks on the 'TestFolder' folder in the Files section
    And User clicks on Create at Icon on File Tab
    And User select Action as 'New Directory'
    When User enter the folder name as 'SubFolder'
    And User click on Create button
    Then User can see the 'SubFolder' folder under 'TestFolder' in the Files section
    When User clicks on Create at Icon on File Tab
    And User select Action as 'New File'
    And User enter the file name as 'SubFile'
    And User click on Create button
    Then User can see the 'SubFile' file under 'TestFolder' in the Files section
    When User clicks on Create at Icon on File Tab
    And User select Action as 'New File'
    And User enter the file name as 'TestFile'
    And User click on Create button
    Then User can see the 'TestFile' file in the Files section
    And User click on the created 'TestFile' file
    And User Edit File with some content as 'dummydata'

    Examples:
      | CATALOG   | FILE_NAME                        | CATALOG_NAME        |
      | Model     | Model/Llama3-70B-Instruct.zip    | Llama3-70B-Instruct |
      | Database  | Database/TestDatabase.zip        | TestDatabase        |
      | Function  | Function/weatherFunctionTest.zip | WeatherFunctionTest |
      | Storage   | Storage/Localminio.zip           | localminio          |
      | Vector    | VectorDatabase/TestVector.zip    | TestVector          |
      | Guardrail | Guardrail/Gliner.zip             | Gliner              |
