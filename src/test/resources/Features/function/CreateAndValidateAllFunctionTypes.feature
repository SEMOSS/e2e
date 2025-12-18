@LoginWithAdmin @Regression @DeleteTestCatalog
Feature: Validate all function types

  Scenario Outline: Validate '<FUNCTION>' function
    Given User opens Main Menu
    When User clicks on Open Function
    And User clicks on Add Function
    And User selects function '<FUNCTION>'
    Then User can see function creation form with following sections with fields:
      | SECTION_NAME | FIELDS      |
      | <S1_NAME>    | <S1_FIELDS> |
      | <S2_NAME>    | <S2_FIELDS> |
      | <S3_NAME>    | <S3_FIELDS> |
    And User can see function creation form with following mandatory fields
      | <MANDATORY_FIELDS> |
    When User fills the function creation form with:
      | <FORM_FIELDS> |
    Then User can see 'Connect' button becomes enabled to create function
    When User clicks on 'Connect' button to create function

    Examples: 
      | FUNCTION                  | S1_NAME | S1_FIELDS                   | S2_NAME     | S2_FIELDS              | S3_NAME  | S3_FIELDS                                                                                                            | MANDATORY_FIELDS                                                                                                                                                          | FORM_FIELDS                                                                                                                                                                                                                                                                                           |
      | AWS Image Text Extraction | General | Function Type, Catalog Name | Credentials | Access Key, Secret Key | Settings | Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type=AWS REKOGNITION, Catalog Name=AWS-Image-Text-Extraction, Access Key=Test123, Secret Key=Test@123, Region=Asia, S3 Bucket Engine Id=s3, Function Name (metadata)=Text-Extraction, Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"] |
