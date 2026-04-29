package aicore.unit.home;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import aicore.pages.home.HomePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.LoginPageUtils;
import aicore.utils.TestTags;
import aicore.utils.UserRegistrationPageUtils;

@Tag(TestTags.BROKEN)
//TODO register button is not available
public class UserRegistrationTests extends AbstractE2ETest {
	
	@Test
	void testRegisterUser() {
		String timestamp = CommonUtils.getTimeStampName();
		UserRegistrationPageUtils.clickOnRegisterNowButton(page);
		
		String firstNameFiled = "First Name";
		String firstNameValue = "John";
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, firstNameFiled, firstNameValue);
		
		String lastNameField = "Last Name";
		String lastNameValue = "Smith";
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, lastNameField, lastNameValue);
		
		String userNameField = "Username";
		String userNameValue = "user" + timestamp;
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, userNameField, userNameValue);
		
		String emailField = "Username";
		String emailValue = "user" + timestamp+"@gmail.com";
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, emailField, emailValue);
		
		String phoneNumberField = "Phone Number";
		String phoneNumValue = "123" + timestamp;
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, phoneNumberField, phoneNumValue);
		
		String phoneNumberExtField = "Phone Extention";
		String phoneNumExtValue = "123";
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, phoneNumberExtField, phoneNumExtValue);
		
		String countryCodeField = "Country Code";
		String countryCodeValue = "+91";
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, countryCodeField, countryCodeValue);
		
		String passwordField = "Password";
		String passwordValue = "Pass@"+timestamp;
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, passwordField, passwordValue);
		
		String confirmPasswordField = "Confirm Password";
		UserRegistrationPageUtils.fillUserRegistartionFormField(page, confirmPasswordField, passwordValue);
		
		UserRegistrationPageUtils.clickOnRegisterButton(page);
		
		String expectedMessage = "Account registration successful. Log in below.";
		String actualMessage =  UserRegistrationPageUtils.verifyRegitrationSuccessMessage(page, expectedMessage);
		Assertions.assertEquals(expectedMessage, actualMessage, "Registration message is incorrect on login page");
		
		LoginPageUtils.enterUsernameAndPassword(page, userNameValue, passwordValue);
		LoginPageUtils.clickLoginButton(page);
		
		HomePageUtils.navigateToHomePage(page);
		
		//TODO cleanup
		// login as admin
		// delete user created above

	}


	
}
