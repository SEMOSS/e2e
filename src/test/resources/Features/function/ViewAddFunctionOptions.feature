@Regression
Feature: View add function page

  Scenario: Verify the available options on add function page
    Given User opens Main Menu
    When User clicks on Open Function
    When User clicks on Add Function button
    Then User should see Search bar to filter function options
    And User should see the following function options with valid icons on the page
      | GROUP     | FUNCTION_OPTIONS                                                                                                                                                 |
      | Functions | AWS Image Text Extraction, AWS Polly, AWS Transcribe, AWS Comprehend, Azure Document Intelligence, Azure Speech To Text, Google Speech To Text, Google OCR, REST |
