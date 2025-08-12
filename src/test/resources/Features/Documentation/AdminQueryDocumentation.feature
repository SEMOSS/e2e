Feature: Admin query

  @LoginWithAdmin @SkipIfVersionMatch
  Scenario Outline: Validate '<DATABASE_NAME>' Admin query
    Given User captures documentation screenshot for 'Admin query'
    Given User opens Main Menu
    And User clicks on Open Settings
    When User enables admin mode
    And User captures a 'button' and highlights the 'Admin On'
    And User captures a 'button' and highlights the 'Admin Query'
    And User clicks on 'Admin Query' Card
    And User clicks on Database dropdown
    And User captures a 'List Item' and highlights the '<DATABASE_NAME>'
    And User selects '<DATABASE_NAME>' from the database dropdown
    And User enters '<QUERY>' in the query textbox
    And User captures a 'button' and highlights the 'Run'
    And User clicks on Run button
    Then User can see table with '<COLUMN_COUNT>' columns:'<COLUMN_NAMES>'
    And User captures screenshot for "<DATABASE_NAME>Query Results"

      Examples: 
      | DATABASE_NAME       | QUERY                          | COLUMN_COUNT | COLUMN_NAMES                                                                                                                                                                                                       |
      | LocalMasterDatabase | select * from ENGINECONCEPT    |           14 | ENGINE, PARENTSEMOSSNAME, SEMOSSNAME, PARENTPHYSICALNAME, PARENTPHYSICALNAMEID, PHYSICALNAME, PHYSICALNAMEID, PARENTLOCALCONCEPTID, LOCALCONCEPTID, IGNORE_DATA, PK, ORIGINAL_TYPE, PROPERTY_TYPE, ADDITIONAL_TYPE |
      | security            | select * from ENGINEMETA       |            4 | ENGINEID, METAKEY, METAVALUE, METAORDER                                                                                                                                                                            |
      | scheduler           | select * from SMSS_JOB_RECIPES |           11 | USER_ID, JOB_ID, JOB_NAME, JOB_GROUP, CRON_EXPRESSION, CRON_TIMEZONE, PIXEL_RECIPE, PIXEL_RECIPE_PARAMETERS, JOB_CATEGORY, TRIGGER_ON_LOAD, UI_STATE                                                               |
      | themes              | select * from ADMIN_THEME      |            4 | ID, THEME_NAME, THEME_MAP, IS_ACTIVE                                                                                                                                                                               |
