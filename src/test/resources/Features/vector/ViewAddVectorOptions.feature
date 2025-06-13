Feature: View add Vector options on the page

  Scenario: Verify the available options on add vector page
    Given User clicks on Open Vector
    When User clicks on Add Vector button
    Then User should see Search bar to filter vector options
    And User should see the following vector options with icons on the page
      | GROUP        | VECTOR_OPTIONS                                                                   |
      | Connections  | Chroma, Elastic Search, FAISS, Milvus, Open Search, PGVector, Pinecone, Weaviate |
      | File Uploads | ZIP                                                                              |
