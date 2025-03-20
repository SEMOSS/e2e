package aicore.steps;

import java.io.IOException;


import aicore.base.AICoreTestManager;
import aicore.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MemberSettingSteps {

	private LoginPage loginpage;
	private HomePage homePage;
	private SettingPage settingpage;
	

	public MemberSettingSteps() {
//		Page page = new AICoreTestBase().page; 
		this.loginpage = new LoginPage(AICoreTestManager.getPage());
		this.homePage = new HomePage(AICoreTestManager.getPage());
		this.settingpage = new SettingPage(AICoreTestManager.getPage());
	}

	@Given("I am logged as Admin user in AI CORE application")
	public void user_is_on_application_with_admin() throws IOException {
		
		 homePage.checkOnMemberSetting();
	}
	 @When("I go to the settings")
	 public void user_clicks_on_setting_button() throws IOException {
		 homePage.clickOnMemberSetting();

		}
	   @And("I enable admin mode")
	   public void user_is_on_seting_page() throws IOException {
		  if( settingpage.checkAdminButton()){
		   settingpage.callAdminButton();
		  }else {			
			  System.out.println("Admin button is not visible");	
		  }}

		  
		  
	    @And("I select Member Settings")
	    public void user_select_member_setting_tile () throws IOException {
			if( settingpage.checkMemberSettingPageTile()){				
				settingpage.callMemberSettingPageTile();
			}else {
			System.out.println("Admin mode is not ON Or Member Setting card is not visible");	
			}

		}
	    
	    @And("I should see Admin on")
	    public void user_can_see_Admin_on() throws IOException {

			   settingpage.checkAdminOnButton();

		}
	    @Then("I should see the Add User button")
	    public void user_can_see_Add_use_button() throws IOException {

			  settingpage.checkAddMemberButton();

		}
	    @And("I should see a count of users")
	    public void user_can_see_users_count() throws IOException {
	    	settingpage.checkCountOfUsers();
		}
}