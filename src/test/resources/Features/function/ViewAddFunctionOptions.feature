Feature: View add function page

  Scenario: Verify the available options on add function page
    Given User navigates to Open Function
    When User clicks on Add Function button
    Then User should see Search bar to filter options
    And User should see the following options on the page
      | LLM_GROUP    | LLM_DATABASE_OPTIONS                                                                                                                                             |
      | Function     | AWS Image Text Extraction, AWS Polly, AWS Transcribe, AWS Comprehend, Azure Document Intelligence, Azure Speech To Text, Google Speech To Text, Google OCR, REST |
      | File Uploads | ZIP                                                                                                                                                              |
   
    And User should see the valid (non-broken) icon appear before each database option
      | AWS Image Text Extraction   |
      | AWS Polly                   |
      | AWS Transcribe              |
      | AWS Comprehend              |
      | Google Speech To Text       |
      | Azure Speech To Text        |
      | Google OCR                  |
      | ZIP                         |
      | Azure Document Intelligence |
      | REST                        |