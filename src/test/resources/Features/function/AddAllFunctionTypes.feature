@LoginWithAdmin @Regression
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

    Examples: 
      | FUNCTION                    | S1_NAME | S1_FIELDS                   | S2_NAME     | S2_FIELDS              | S3_NAME  | S3_FIELDS                                                                                                                                          | MANDATORY_FIELDS                                                                                                                                                                    | FORM_FIELDS                                                                                                                                                                                                                                                                                            |
      | AWS Image Text Extraction   | General | Function Type, Catalog Name | Credentials | Access Key, Secret Key | Settings | Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters                               | Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters           | Function Type=AWS REKOGNITION, Catalog Name=AWS-Image-Text-Extraction, Access Key=Test123, Secret Key=Test@123, Region=Asia, S3 Bucket Engine Id=s3, Function Name (metadata)=Text-Extraction, Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"]  |
      | AWS Polly                   | General | Function Type, Catalog Name | Credentials | Access Key, Secret Key | Settings | Region, Function Name (metadata), Function Description (metadata), Function Required Parameters                                                    | Function Type, Catalog Name, Access Key, Secret Key, Region, Function Name (metadata), Function Description (metadata), Function Required Parameters                                | Catalog Name=AWS-Polly, Access Key=Test123, Secret Key=Test@123, Region=Asia, Function Name (metadata)=AWS Polly, Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"]                                                                               |
      | AWS Transcribe              | General | Function Type, Catalog Name | Credentials | Access Key, Secret Key | Settings | Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters                               | Function Type, Catalog Name, Access Key, Secret Key, Region, S3 Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters           | Catalog Name=AWS-Transcribe , Access Key=Test123, Secret Key=Test@123, Region=Asia, S3 Bucket Engine Id=s3, Function Name (metadata)=AWS Transcribe , Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"]                                           |
      | AWS Comprehend              | General | Function Type, Catalog Name | Credentials | Access Key, Secret Key | Settings | Region, Function Name (metadata), Function Description (metadata), Function Required Parameters                                                    | Function Type, Catalog Name, Access Key, Secret Key, Region, Function Name (metadata), Function Description (metadata), Function Required Parameters                                | Catalog Name=AWS-Comprehend , Access Key=Test123, Secret Key=Test@123, Region=Asia, Function Name (metadata)=AWS Comprehend , Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"]                                                                   |
      | Azure Document Intelligence | General | Function Type, Catalog Name | Credentials | API Key, URL           |          |                                                                                                                                                    | Function Type, Catalog Name, URL, API Key                                                                                                                                           | Catalog Name=Azure-Document-Intelligence, URL=https://www.google.com, API Key=Test123                                                                                                                                                                                                                  |
      | Azure Speech To Text        | General | Function Type, Catalog Name | Credentials | Speech Key             | Settings | Speech region, Function Name (metadata), Function Description (metadata), Function Required Parameters                                             | Function Type, Catalog Name, Speech Key, Speech region, Function Name (metadata), Function Description (metadata), Function Required Parameters                                     | Catalog Name=Azure-Speech-To-Text, Speech Key=Test@123, Speech region=Asia, Function Name (metadata)=Speech-To-Text, Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"]                                                                            |
      | REST                        | General | Function Type, Catalog Name | Credentials | URL, Http Method       | Settings | POST Message Body Type, Http Headers, Function Parameters, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, URL, Http Method, POST Message Body Type, Function Parameters, Function Name (metadata), Function Description (metadata), Function Required Parameters | Catalog Name=Rest, URL=https://www.google.com, Http Method=HEAD, POST Message Body Type=x-www-form-urlencoded, Http Headers=Host, Function Parameters=myFunctio, Function Name (metadata)=Rest, Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"] |

  Scenario Outline: Validate '<FUNCTION>' function- requiring file upload
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
    And User uploads the file 'Function/weatherFunctionTest.zip'
    Then User can see 'Connect' button becomes enabled to create function

    Examples: 
      | FUNCTION              | S1_NAME | S1_FIELDS                   | S2_NAME     | S2_FIELDS                                                                          | S3_NAME  | S3_FIELDS                                                                                                                                                           | MANDATORY_FIELDS                                                                                                                                                                | FORM_FIELDS                                                                                                                                                                                                                                          |
      | Google Speech To Text | General | Function Type, Catalog Name | Credentials | Google Bucket Engine Id, Function Name (metadata), Function Description (metadata) | Settings | Upload Service Account File, Function Required Parameters                                                                                                           | Function Type, Catalog Name, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters                                   | Catalog Name=AWS-Image-Text-Extraction, Function Name (metadata)=Text-Extraction, Google Bucket Engine Id=G10, Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"]                                |
      | Google OCR            | General | Function Type, Catalog Name | Credentials | Project Id                                                                         | Settings | Processor Id, Region, Upload Service Account File, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters | Function Type, Catalog Name, Project Id, Processor Id, Region, Google Bucket Engine Id, Function Name (metadata), Function Description (metadata), Function Required Parameters | Catalog Name=Google-OCR, Project Id=01, Processor Id=1.1, Region=Asia, Function Name (metadata)=Text-Extraction, Google Bucket Engine Id=G10, Function Description (metadata)=Testing, Function Required Parameters=["isFilePresentInS3","filePath"] |
