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
      | FUNCTION_NAME | FIELD_NAMES                                                                 | REQUIRED_FIELDS              |
      | Amazon S3     | Catalog Name, Region, Bucket, Access key, Secret key, Create Storage button | Catalog Name, Region, Bucket |

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
    When User sees the Create Storage button disabled
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       |                   |
      | Bucket       | BucketTest        |
    When User sees the Create Storage button disabled
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       | India             |
      | Bucket       |                   |
    When User sees the Create Storage button disabled
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       | India             |
      | Bucket       | BucketTest        |
    When User sees the Create Storage button is enabled
    When User enters value in below fields
      | FIELD_NAME   | FIELD_VALUE       |
      | Catalog Name | Amazon S3 Storage |
      | Region       | India             |
      | Bucket       | BucketTest        |
      | Access key   | Test123           |
      | Secret key   | Test123           |
    And User clicks on Create Storage button
    Then User can see create storage success toast message as 'Successfully added to catalog storage'
    And User clicks On Copy Catalog ID
    And User can see the Storage title as 'Amazon S3 Storage'
