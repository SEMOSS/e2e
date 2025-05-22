Feature: View add Storage page

  Scenario: Verify the available options on add storage page
    Given User clicks on Open Storage engine
    When User clicks on Add Storage button
    Then User should see Search bar to filter storage options
    And User should see the following storage options with valid icons on the page
      | GROUP        | STORAGE_OPTIONS                                                                                                                                                        |
      | Storage      | Amazon S3, CEPH, Dreamhost, Dropbox, Google Cloud, Google Drive, Local File System, Microsoft Azure Blob Storage, Microsoft OneDrive, MinIO, Network File System, SFTP |
      | File Uploads | ZIP                                                                                                                                                                    |
