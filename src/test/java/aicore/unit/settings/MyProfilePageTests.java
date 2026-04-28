package aicore.unit.settings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddCatalogPageBaseUtils;
import aicore.utils.CommonUtils;
import aicore.utils.settings.MyProfilePageUtils;

public class MyProfilePageTests extends AbstractE2ETest {

	@BeforeAll
	public static void login() throws IOException {
		login(page, UserType.NATIVE);
	}
	
	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		MyProfilePageUtils.clickOnMyProfileCard(page);
	}
	
	@ParameterizedTest(name = "Check {0}: Expecting {1} Access Keys and {2} Secret Keys")
	@CsvSource({
	    "Javascript Example, 1, 1",
	    "Python Example,     1, 1"
	})
	void testGenerateKey(String sectionName, int expectedAccessCount, int expectedSecretCount) {
	    // 1. Setup (This runs for EVERY row in @CsvSource)
	    String timestamp = CommonUtils.getTimeStampName();
	    String keyName = "New Key";
	    String description = "New desc";

	    MyProfilePageUtils.clickNewKeyButton(page);
	    MyProfilePageUtils.enterKeyName(page, keyName, timestamp);
	    MyProfilePageUtils.enterDescription(page, description, timestamp);
	    MyProfilePageUtils.clickGenerateButton(page);
	    
	    // 2. Extract keys
	    String accessKey = MyProfilePageUtils.copyAccessKey(page, "Access Key");
	    String secretKey = MyProfilePageUtils.copyAccessKey(page, "Secret Key");

	    // 3. Validation Logic
	    String sectionContents = MyProfilePageUtils.extractExampleSectionContent(page, sectionName);
	    
	    int actualAccessCount = CommonUtils.countIdOccurances(sectionContents, accessKey);
	    int actualSecretCount = CommonUtils.countIdOccurances(sectionContents, secretKey);

	    Assertions.assertEquals(expectedAccessCount, actualAccessCount, 
	        "Access Key mismatch in: " + sectionName);
	    Assertions.assertEquals(expectedSecretCount, actualSecretCount, 
	        "Secret Key mismatch in: " + sectionName);
		AddCatalogPageBaseUtils.clickOnClose(page);

	    
	    // see personal access tokens on page
		boolean isVisible = MyProfilePageUtils.isSectionVisible(page, sectionName);
		assertTrue(isVisible, "Expected section not found: " + sectionName);

		String actualKeyName = MyProfilePageUtils.validateGeneratedKey(page, keyName, timestamp);
		String expKeyTitle = keyName + timestamp;
		Assertions.assertEquals(actualKeyName, expKeyTitle);
		
		String actualDescription =  MyProfilePageUtils.validateDescriptionName(page, description, timestamp);
		String expDescription = description + timestamp;
		Assertions.assertEquals(actualDescription, expDescription);
	    
		MyProfilePageUtils.clickOnDeleteIcon(page, keyName, timestamp);
		MyProfilePageUtils.deleteKeyToastMessage(page);


	}

	@ParameterizedTest
	@ValueSource(strings = { "Edit profile information", "Javascript SDK", "Python SDK", "Personal Access Tokens" })
	void testSectionLoad(String sectionName) {
		boolean isVisible = MyProfilePageUtils.isSectionVisible(page, sectionName);
		assertTrue(isVisible, "Expected section not found: " + sectionName);		

	}
	

}
