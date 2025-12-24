@LoginWithAdmin @Regression
Feature: Validate all Storage types

  Scenario Outline: Validate '<STORAGE>' storage
    Given User opens Main Menu
    When User clicks on Open Storage
    And User clicks on Add Storage button
    And User selects '<STORAGE>' storage
    Then User can see '<STORAGE_TYPE>' storage creation form with following sections with fields:
      | SECTION_NAME | FIELDS      |
      | <S1_NAME>    | <S1_FIELDS> |
      | <S2_NAME>    | <S2_FIELDS> |
      | <S3_NAME>    | <S3_FIELDS> |
    And User can see '<STORAGE_TYPE>' storage creation form with following mandatory fields
      | <MANDATORY_FIELDS> |
    When User fills the '<STORAGE_TYPE>' storage creation form with:
      | <FORM_FIELDS> |
    Then User can see 'Connect' button becomes enabled to create storage

    Examples: 
      | STORAGE                      | STORAGE_TYPE | S1_NAME | S1_FIELDS    | S2_NAME     | S2_FIELDS                      | S3_NAME  | S3_FIELDS                          | MANDATORY_FIELDS                                                                 | FORM_FIELDS                                                                                                                          |
      | Amazon S3                    | S3           | General | Catalog Name | Credentials | Access Key, Secret Key         | Settings | Region, Bucket                     | Catalog Name, Region, Bucket                                                     | Catalog Name=Amazon-S3, Access Key=Test123, Secret Key=Test@123, Region=Asia, Bucket=s3                                              |
      | CEPH                         | CEPH         | General | Catalog Name | Credentials | Access Key, Secret Key         | Settings | Endpoint, Root Bucket Path         | Catalog Name, Access Key, Secret Key, Endpoint                                   | Catalog Name=CEPH, Access Key=Test123, Secret Key=Test@123, Endpoint=https://www.google.com, Root Bucket Path=/data                  |
      | Dropbox                      | S3           | General | Catalog Name | Credentials | S3 Access Key, S3 Secret Key   | Settings | S3 Endpoint, S3 Region             | Catalog Name, S3 Endpoint, S3 Region, S3 Access Key, S3 Secret Key               | Catalog Name=Dropbox, S3 Region=Asia, S3 Endpoint=https://www.google.com, S3 Access Key=Test123, S3 Secret Key=Test@123              |
      | Google Cloud                 | GCS          | General | Catalog Name | Credentials | Service Account File           | Settings | Region, Bucket                     | Catalog Name, Region, Service Account File                                       | Catalog Name=Google-Cloud, Region=Asia, Bucket=GCS, Service Account File=/data                                                       |
      | Local File System            | LOCAL        | General | Catalog Name |             |                                | Settings | Local Path Prefix                  | Catalog Name, Local Path Prefix                                                  | Catalog Name=Local-File-System, Local Path Prefix=/data                                                                              |
      | Microsoft Azure Blob Storage | Azure        | General | Catalog Name | Credentials | Primary Key, Connection String | Settings | Account Name, Generate Dynamic SAS | Catalog Name, Account Name, Generate Dynamic SAS, Primary Key, Connection String | Catalog Name=Microsoft-Azure-Blob, Account Name=Azure storage, Generate Dynamic SAS=True, Primary Key=Test123, Connection String=ABC |
