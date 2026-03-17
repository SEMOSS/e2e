Feature: Playground Settings -Attachment Option

  Scenario: Verify file upload functionality for multiple file formats
    Given User is on Home page
    When User clicks on Build button
    And User clicks on Try it out hyperlink for Experiment in our Playground
    And User click on Open Settings option button
    And User can see 'Attach Document' option
    When User uploads and verifies the files in Attach Document option on playground home page
      | fileName                                      |
      | PlaygroundMCP/Attachments/Employee.xlsx       |
      | PlaygroundMCP/Attachments/Image.png           |
      | PlaygroundMCP/Attachments/dummy.pdf           |
      | PlaygroundMCP/Attachments/cucumberreport.html |
      | PlaygroundMCP/Attachments/mcp 1.zip           |
      | PlaygroundMCP/Attachments/Document.docs       |
      | PlaygroundMCP/Attachments/Test.txt            |
