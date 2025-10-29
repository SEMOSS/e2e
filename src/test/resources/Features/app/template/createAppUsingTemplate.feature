Feature: Create app using Template
 
@LoginWithAdmin @Regression
Scenario: Create app using Template
    Given User is on Home page
    When User opens Main Menu
    And User clicks on Open App Library
    And User clicks on Create New App button
    And User selects "Ask LLM" from Template List
    And User enters app name as 'Test app'
    And User enters description as 'Created by automation script'
    And User enters tags 'Test1, Test2' and presses Enter
    And User clicks on Create button
    Then User sees the title 'Ask LLM'
    And User sees description as 'Ask an LLM a question'
    And User sees input field with With label 'Question'
    And User sees submit button
    When User clicks on Preview app button
    Then User sees the title 'Ask LLM' in Preview App
    And User sees description as 'Ask an LLM a question' in Preview App
    And User sees input field with With label 'Question' in Preview App
    And User sees submit button in Preview App
    When User clicks on Close Preview button
    And User opens Main Menu
    And User clicks on Open App Library
    And User searches 'Test app' app in the app searchbox
    And User clicks on 'Test app' app from the My Apps
    
  @LoginWithAdmin @DeleteCreatedTestApp @Regression
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
    Then User sees title of the block as 'Landing Page Title'
    And User views description as 'Drag and drop your content below to start populating your page.  Add images, text, and links to customize your landing page and make it your own.  Whether you are setting up a portfolio, a business page, or a personal blog, this is the first step to creating something unique and engaging.  Make your vision come to life!'
    And User sees the hyperlink with text "Explore" should point to the url "SemossWeb/packages/client/dist/"
    And User navigates to back page
    Then User sees title of the block as 'Resources'
    And User views description as 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique. Duis cursus, mi quis viverra ornare, eros dolor interdum nulla, ut commodo diam libero vitae erat. Aenean faucibus nibh et justo cursus id rutrum lorem imperdiet. Nunc ut sem vitae risus tristique posuere.'
    Then User sees title of the block as 'Resource 1'
    And User views description for the block with title 'Resource 1' as 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.'
    And User clicks on the hyperlink with text "Navigate"  with title 'Resource 1' should point to "SemossWeb/packages/client/dist/"
    And User navigates to back page
    Then User sees title of the block as 'Resource 2'
    And User views description for the block with title 'Resource 2' as 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.'
    And User clicks on the hyperlink with text "Navigate"  with title 'Resource 2' should point to "SemossWeb/packages/client/dist/"
    And User navigates to back page
    Then User sees title of the block as 'Resource 3'
    And User views description for the block with title 'Resource 3' as 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.'
    And User clicks on the hyperlink with text "Navigate"  with title 'Resource 3' should point to "SemossWeb/packages/client/dist/"
    And User navigates to back page
    Then User sees title of the block as 'Resource 4'
    And User views description for the block with title 'Resource 4' as 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.'
    And User clicks on the hyperlink with text "Navigate"  with title 'Resource 4' should point to "SemossWeb/packages/client/dist/"
    And User navigates to back page
    Then User sees title of the block as 'Resource 5'
    And User views description for the block with title 'Resource 5' as 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.'
    And User clicks on the hyperlink with text "Navigate"  with title 'Resource 5' should point to "SemossWeb/packages/client/dist/"
    And User navigates to back page
    Then User sees title of the block as 'Resource 6'
    And User views description for the block with title 'Resource 6' as 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique.'
    And User clicks on the hyperlink with text "Navigate"  with title 'Resource 6' should point to "SemossWeb/packages/client/dist/"
    And User navigates to back page
    And User clicks on hyperlink text "Explore"
    And User clicks on the Block Settings option
    And User filles the destination URL as "https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login"
    And User clicks on Save button of the app
    And User clicks on the Block Settings option
    And User clicks on hyperlink text "Explore"
    And User sees the URL as "https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login"
 