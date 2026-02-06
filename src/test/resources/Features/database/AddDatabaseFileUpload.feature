Feature: Add TSV Database

  Background: Create Database using TSV file
    Given User opens Main Menu
    And User clicks on Open Database
    When User clicks on Add Database
    And User clicks on the 'file uploads' tab from options

  @LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
  Scenario Outline: Add Database using '<File_Type>' - '<Metamodel_Type>' Metamodel
    When User selects '<File_Type>' file type
    And User enters '<Database_Name>' as Database Name
    And User selects '<Database_Type>' as database type
    And User selects '<Metamodel_Type>' as Metamodel type
    And User uploads the file '<File_Path>'
    #And User clicks on Next button
    And User click on Connect button
    And User sees the colunm names with edit button and delete button
    And User clicks on Import button
    #And User sees success toast message '<Toast_Message>'
    And User can see the Catalog title as '<Database_Name>'
    And User clicks on Copy Catalog ID

    Examples: 
      | File_Type | Database_Name         | Metamodel_Type | Database_Type | File_Path              | Toast_Message                 |
      | TSV       | DB created from TSV   | asFlatTable    | h2            | Database/Employee.tsv  | Successfully created database |
      | TSV       | DB created from TSV   | fromScratch    | h2            | Database/Employee.tsv  | Successfully created database |
      | CSV       | DB created from CSV   | asFlatTable    | h2            | Database/diabetes.csv  | Successfully created database |
      | CSV       | DB created from CSV   | fromScratch    | h2            | Database/diabetes.csv  | Successfully created database |
      | Excel     | DB created from Excel | asFlatTable    | h2            | Database/Database.xlsx | Successfully Created Database |

  @LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
  Scenario Outline: Add Database using '<File_Type>' - '<Metamodel_Type>' Metamodel
    When User selects '<File_Type>' file type
    And User enters '<Database_Name>' as Database Name
    And User selects '<Database_Type>' as database type
    And User selects '<Metamodel_Type>' as Metamodel type
    And User uploads the file '<File_Path>'
    And User click on Connect button
    #And User clicks on save button
    And User clicks on Save button for database
    #And User sees success toast message '<Toast_Message>'
    And User can see the Catalog title as '<Database_Name>'
    And User clicks on Copy Catalog ID

    Examples: 
      | File_Type | Database_Name       | Metamodel_Type       | Database_Type | File_Path             | Toast_Message                 |
      | TSV       | DB created from TSV | asSuggestedMetaModel | h2            | Database/Employee.tsv | Successfully created database |
      | CSV       | DB created from CSV | asSuggestedMetaModel | h2            | Database/diabetes.csv | Successfully created database |

  @LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
  Scenario Outline: Add Database using multiple '<File_Type>' files - '<Metamodel_Type>' Metamodel
    When User selects '<File_Type>' file type
    And User enters '<Database_Name>' as Database Name
    And User selects '<Database_Type>' as database type
    And User selects '<Metamodel_Type>' as Metamodel type
    And User uploads the file '<File_Path1>'
    And User uploads the file '<File_Path2>'
    And User click on Connect button
    And User sees the colunm names with edit button and delete button
    And User clicks on Import button
    #And User sees success toast message '<Toast_Message>'
    And User can see the Catalog title as '<Database_Name>'
    And User clicks on Copy Catalog ID

    Examples: 
      | File_Type | Database_Name       | Metamodel_Type | Database_Type | File_Path1            | File_Path2             | Toast_Message                 |
      | TSV       | DB created from TSV | fromScratch    | h2            | Database/Employee.tsv | Database/Employee2.tsv | Successfully created database |
      | CSV       | DB created from CSV | fromScratch    | h2            | Database/diabetes.csv | Database/diabetes2.csv | Successfully created database |

  @LoginWithAdmin @Regression @DeleteTestCatalog @Smoke
  Scenario Outline: Add Database using '<File_Type>' - '<Metamodel_Type>' Metamodel
    When User selects '<File_Type>' file type
    And User enters '<Database_Name>' as Database Name
    And User selects '<Database_Type>' as database type
    And User selects '<Metamodel_Type>' as Metamodel type
    And User uploads the file '<File_Path>'
    And User click on Connect button
    And User verifies the '<Table_Name>' table name is displayed
    And User verifies the full screen button is enabled
    And User clicks on the select table button and verifies the table is selected
    And User reset button is enabled
    And User clicks on create relationship button and creates relationship between '<Parent_Table>' and '<Child_Table>' tables
    And User verifies save button is enabled
    And User verifies cancel button is enabled
    And User clicks on Save button for database
    #And User sees success toast message '<Toast_Message>'
    And User can see the Catalog title as '<Database_Name>'
    And User clicks on Copy Catalog ID

    Examples: 
      | File_Type | Database_Name       | Metamodel_Type       | Database_Type | File_Path             | Toast_Message                 | Table_Name   | Parent_Table | Child_Table |
      | TSV       | DB created from TSV | asSuggestedMetaModel | h2            | Database/Employee.tsv | Successfully created database | Employee.tsv | JOB ID       | EMPLOYEE ID |
