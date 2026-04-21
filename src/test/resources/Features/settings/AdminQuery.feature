Feature: Admin query

## Add to AdminQUeryPageTests when this is resolved
 ##As per latest UI not able to see the Max Row count so commenting the below scenario 
  #@LoginWithAdmin @Regression
  #Scenario Outline: Validate '<DATABASE_NAME>' Admin query count
    #Given User created '<MODEL_COUNT>' models with the 'OpenAI' model 'GPT-5', catalog name 'Model', OpenAI key 'Test123'
    #And User created '<JOB_COUNT>' jobs with the job name 'Test Job', Pixel '1+1'
    #When User opens Main Menu
    #And User clicks on Open Settings
    #And User enables admin mode
    #And User clicks on 'Admin Query' Card
    #And User clicks on Database dropdown
    #And User selects '<DATABASE_NAME>' from the database dropdown
    #And User enters '<MAX_ROWS>' in the Max Rows to Collected textbox
    #And User enters '<QUERY>' in the query textbox
    #And User clicks on Run button
    #Then User can see success toast message as 'Successfully submitted query'
    #And User can see table with '<ROW_COUNT>' rows
    #And User Delete the created Model
#
    #Examples: 
      #| DATABASE_NAME         | QUERY                          | MAX_ROWS | ROW_COUNT | MODEL_COUNT | JOB_COUNT |
      #| Local Master Database | select * from ENGINECONCEPT    |        1 |         1 |           0 |         0 |
      #| security              | select * from ENGINEMETA       |        5 |         5 |           5 |         0 |
      #| scheduler             | select * from SMSS_JOB_RECIPES |       10 |        10 |           0 |        10 |
