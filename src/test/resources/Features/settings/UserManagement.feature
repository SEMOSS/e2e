Feature: User Management

  #@LoginWithAdmin
  #Scenario: Add New Native User
    #Given User navigates to Open Setting page
    #When User enable admin mode
    #And User clicks on 'Member Settings' Card
    #Then User sees the Add User button
    #And User adds 30 members and can see toast message as 'Successfully added user' for all added members
    #And User sees the search button
    #And User searches for the created user
    #And User clicks on Delete Selected button 1 times
#
  #@LoginWithAdmin
  #Scenario: Edit Native User - Change Model Limit Restriction
    #Given User navigates to Open Setting page
    #When User enable admin mode
    #And User clicks on 'Member Settings' Card
    #Then User sees the Add User button
    #And User adds 1 members and can see toast message as 'Successfully added user' for all added members
    #And User clicks on Edit icon
    #And User clicks on Model dropdown
    #And User selects 'Token' value in Model dropdown
    #And User fills '2' value in Max Tokens field
    #And User clicks on Frequency dropdown
    #And User selects 'Weekly' value in Frequency dropdown
    #And User clicks on save button
    #Then User sees the new model limit value as '2' on Member Settings page
    #And User sees the search button
    #And User searches for the created user
    #And User clicks on Delete Selected button 1 times

   @LoginWithAdmin
  Scenario: Update Configuration Settings - access_keys_allowed - true - Adfs
    Given User navigates to Open Setting page
    When User enable admin mode
    And User clicks on 'Configuration' Card
    And User clicks on 'access_keys_allowed' value 
    And User change value of the key to 'true'
    And User clicks on Save button of the configuration
    And User can see a toast message after updating values of 'Adfs' as "Succesfully modified adfs properties"
    
    @LoginWithAdmin
  Scenario: Update Configuration Settings - access_keys_allowed - true - native
    Given User navigates to Open Setting page
    When User enable admin mode
    And User clicks on 'Configuration' Card
    And User clicks on Authentication dropdown
    And User search 'Native' and select  
    And User clicks on 'access_keys_allowed' value 
    And User change value of the key to 'true'
    And User clicks on Save button of the configuration
    And User can see a toast message after updating values of 'Native' as "Succesfully modified native properties"