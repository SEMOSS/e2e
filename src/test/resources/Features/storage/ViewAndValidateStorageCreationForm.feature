Feature: Validate storage creation form

  @LoginWithAdmin @Regression
  Scenario Outline: View and fill '<FUNCTION_NAME>' storage creation form
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Storage
    When User clicks on Add Storage button
    And User selects '<FUNCTION_NAME>' storage
    And User can see '<FIELD_NAMES>' fields on the form
    And User sees astrisk mark on the '<REQUIRED_FIELDS>' fields of storage creation form
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       | India             |
      | Bucket       | BucketTest        |
      | Access key   | Test123           |
      | Secret key   | Test123           |

    Examples: 
      | FUNCTION_NAME | FIELD_NAMES                                          | REQUIRED_FIELDS              |
      | Amazon S3     | Catalog Name, Region, Bucket, Access key, Secret key | Catalog Name, Region, Bucket |

  @DeleteTestCatalog @Regression
  Scenario: Submit and validate 'Amazon S3' storage creation form
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open Storage
    When User clicks on Add Storage button
    And User selects 'Amazon S3' storage
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE |
      | Catalog Name |             |
      | Region       | India       |
      | Bucket       | BucketTest  |
    Then User sees the Connect button is disabled
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       |                   |
      | Bucket       | BucketTest        |
    Then User sees the Connect button is disabled
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       | India             |
      | Bucket       |                   |
    Then User sees the Connect button is disabled
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       | India             |
      | Bucket       | BucketTest        |
    Then User can see 'Connect' button becomes enabled to create storage
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       | India             |
      | Bucket       | BucketTest        |
      | Access key   | Test123           |
      | Secret key   | Test123           |
    Then User clicks on Connect button to create storage
    And User can see create storage success toast message as 'Successfully added new storage to catalog'
    And User clicks on Copy Catalog ID
    And User can see the Storage title as 'Amazon S3 Storage'
