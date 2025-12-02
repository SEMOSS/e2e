Feature: Admin query

  @LoginWithAdmin @Regression
  Scenario Outline: Validate '<DATABASE_NAME>' Admin query
    Given User opens Main Menu
    And User clicks on Open Settings
    When User enables admin mode
    And User clicks on 'Admin Query' Card
    And User clicks on Database dropdown
    And User selects '<DATABASE_NAME>' from the database dropdown
    And User enters '<QUERY>' in the query textbox
    And User clicks on Run button
    Then User can see table with '<COLUMN_COUNT>' columns:'<COLUMN_NAMES>'
    And User can see success toast message as 'Successfully submitted query'

    Examples: 
      | DATABASE_NAME       | QUERY                          | COLUMN_COUNT | COLUMN_NAMES                                                                                                                                                                                                       |
      | LocalMasterDatabase | select * from ENGINECONCEPT    |           14 | ENGINE, PARENTSEMOSSNAME, SEMOSSNAME, PARENTPHYSICALNAME, PARENTPHYSICALNAMEID, PHYSICALNAME, PHYSICALNAMEID, PARENTLOCALCONCEPTID, LOCALCONCEPTID, IGNORE_DATA, PK, ORIGINAL_TYPE, PROPERTY_TYPE, ADDITIONAL_TYPE |
      | security            | select * from ENGINEMETA       |            4 | ENGINEID, METAKEY, METAVALUE, METAORDER                                                                                                                                                                            |
      | scheduler           | select * from SMSS_JOB_RECIPES |           11 | USER_ID, JOB_ID, JOB_NAME, JOB_GROUP, CRON_EXPRESSION, CRON_TIMEZONE, PIXEL_RECIPE, PIXEL_RECIPE_PARAMETERS, JOB_CATEGORY, TRIGGER_ON_LOAD, UI_STATE                                                               |
      | themes              | select * from ADMIN_THEME      |            4 | ID, THEME_NAME, THEME_MAP, IS_ACTIVE                                                                                                                                                                               |
     #| UserTrackingDatabase | select * from INSIGHT_OPENS    |            4 | INSIGHTID, USERID, OPENED_ON, ORIGIN                                                                                                                                                                               |
 
  @LoginWithAdmin @Regression
  Scenario Outline: Validate '<DATABASE_NAME>' Admin query count
    Given User created '<MODEL_COUNT>' models with the 'OpenAI' model 'GPT 3.5 Turbo', catalog name 'Model', OpenAI key 'Test123'
    And User created '<JOB_COUNT>' jobs with the job name 'Test Job', Pixel '1+1'
    When User opens Main Menu
    And User clicks on Open Settings
    And User enables admin mode
    And User clicks on 'Admin Query' Card
    And User clicks on Database dropdown
    And User selects '<DATABASE_NAME>' from the database dropdown
    And User enters '<MAX_ROWS>' in the Max Rows to Collected textbox
    And User enters '<QUERY>' in the query textbox
    And User clicks on Run button
    Then User can see success toast message as 'Successfully submitted query'
    And User can see table with '<ROW_COUNT>' rows
    And User Delete the created Model

    Examples: 
      | DATABASE_NAME       | QUERY                          | MAX_ROWS | ROW_COUNT | MODEL_COUNT | JOB_COUNT |
      | LocalMasterDatabase | select * from ENGINECONCEPT    |        1 |         1 |           0 |         0 |
      | security            | select * from ENGINEMETA       |        5 |         5 |           5 |         0 |
      | scheduler           | select * from SMSS_JOB_RECIPES |       10 |        10 |           0 |        10 |
