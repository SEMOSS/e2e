Feature: Create app using Template

  #@LoginWithAdmin
  #Scenario: Create app using Template
    #Given User is on Home page
    #When User opens Main Menu
    #And User clicks on Open App Library
    #And User clicks on Create New App button
    #And User selects "Ask LLM" from Template List
    #And User enters app name as 'Test app'
    #And User enters description as 'Created by automation script'
    #And User enters tags 'Test1, Test2' and presses Enter
    # And User clicks on Create button
    # Then User sees the title 'Ask LLM'
    #And User sees description as 'Ask an LLM a question'
    #And User sees input field with With label 'Question'
    #And User sees submit button
    #When User clicks on Preview app button
    #Then User sees the title 'Ask LLM' in Preview App
    #And User sees description as 'Ask an LLM a question' in Preview App
    #And User sees input field with With label 'Question' in Preview App
    #And User sees submit button in Preview App
    #When User clicks on Close Preview button
    #And User is on Home page
    #And User opens Main Menu
    #And User clicks on Open App Library
    #And User searches 'Test app' app in the app searchbox
    #And User clicks on 'Test app' app from the My Apps

  Scenario: Create app using Template - Landing page
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Landing Page" from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User sees the title as 'Landing Page Title'
    And User views description as 'Drag and drop your content below to start populating your page.  Add images, text, and links to customize your landing page and make it your own.  Whether you are setting up a portfolio, a business page, or a personal blog, this is the first step to creating something unique and engaging.  Make your vision come to life!'
    And User sees the hyperlink with text "Explore" should point to "http://localhost:5173/SemossWeb/packages/client/dist/"
    # And User sees submit button
    # When User clicks on Preview app button
    # Then User sees the title 'Ask LLM' in Preview App
    # And User sees description as 'Ask an LLM a question' in Preview App
    # And User sees input field with With label 'Question' in Preview App
    # And User sees submit button in Preview App
    # When User clicks on Close Preview button
    # And User is on Home page
    # And User opens Main Menu
    # And User clicks on Open App Library
    # And User searches 'Test app' app in the app searchbox
    # And User clicks on 'Test app' app from the My Apps
    
