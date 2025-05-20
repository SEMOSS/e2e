Feature: View add Storage page

  Scenario: Verify the available options on add storage page
    Given User clicks on Open Storage engine
    When User clicks on Add Storage button
    Then User should see Search bar to filter options
    And User should see the following options with valid icons on the page
      | LLM_GROUP    | LLM_DATABASE_OPTIONS                                                                                                                                |
      | Storage      | Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP |
      | File Uploads | ZIP                                                                                                                                                 |
