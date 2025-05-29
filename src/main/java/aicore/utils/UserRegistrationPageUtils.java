package aicore.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class UserRegistrationPageUtils {
	private static final Logger logger = LogManager.getLogger(UserRegistrationPageUtils.class);

	private static final String REGISTER_NOW_BUTTON_DATA_TEST_ID = "loginPage-button-registerPage";
	private static final String FIRST_NAME_FIELD_DATA_TEST_ID = "loginPage-textField-firstNameRegister";
	private static final String LAST_NAME_FIELD_DATA_TEST_ID = "loginPage-textField-lastNameRegister";
	private static final String USERNAME_FIELD_DATA_TEST_ID = "loginPage-textField-usernameRegister";
	private static final String EMAIL_FIELD_DATA_TEST_ID = "loginPage-textField-emailRegister";
	private static final String PHONE_NUMBER_FIELD_DATA_TEST_ID = "loginPage-textField-phone";
	private static final String PHONE_EXTENTION_FIELD_DATA_TEST_ID = "loginPage-textField-extension";
	private static final String COUNTRY_CODE_FIELD_DATA_TEST_ID = "loginPage-textField-countryCode";
	private static final String PASSWORD_FIELD_DATA_TEST_ID = "loginPage-textField-passwordRegister";
	private static final String CONFIRM_PASSWORD_FIELD_DATA_TEST_ID = "loginPage-textField-passwordConfirm";
	private static final String REGISTER_BUTTON_DATA_TEST_ID = "loginPage-button-register";

	public static void clickOnRegisterNowButton(Page page) {
		page.getByTestId(REGISTER_NOW_BUTTON_DATA_TEST_ID).click();
	}

	public static void fillUserRegistartionFormField(Page page, String fieldName, String fieldValue) {
		Locator fieldLocator = null;
		switch (fieldName) {
		case "First Name":
			fieldLocator = page.getByTestId(FIRST_NAME_FIELD_DATA_TEST_ID);
			break;
		case "Last Name":
			fieldLocator = page.getByTestId(LAST_NAME_FIELD_DATA_TEST_ID);
			break;
		case "Username":
			fieldLocator = page.getByTestId(USERNAME_FIELD_DATA_TEST_ID);
			break;
		case "Email":
			fieldLocator = page.getByTestId(EMAIL_FIELD_DATA_TEST_ID);
			break;
		case "Phone Number":
			fieldLocator = page.getByTestId(PHONE_NUMBER_FIELD_DATA_TEST_ID);
			break;
		case "Phone Extention":
			fieldLocator = page.getByTestId(PHONE_EXTENTION_FIELD_DATA_TEST_ID);
			break;
		case "Country Code":
			fieldLocator = page.getByTestId(COUNTRY_CODE_FIELD_DATA_TEST_ID);
			break;
		case "Password":
			fieldLocator = page.getByTestId(PASSWORD_FIELD_DATA_TEST_ID);
			break;
		case "Confirm Password":
			fieldLocator = page.getByTestId(CONFIRM_PASSWORD_FIELD_DATA_TEST_ID);
			break;
		default:
			logger.info("Invalid Field name" + fieldName);
		}
		fieldLocator.fill(fieldValue);
	}

	public static void clickOnRegisterButton(Page page) {
		page.getByTestId(REGISTER_BUTTON_DATA_TEST_ID).click();
	}

	public static String verifyRegitrationSuccessMessage(Page page, String messageText) {
		return page.getByText(messageText).textContent().trim();
	}
}
