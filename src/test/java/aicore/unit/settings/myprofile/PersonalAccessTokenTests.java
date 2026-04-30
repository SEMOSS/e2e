package aicore.unit.settings.myprofile;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AICoreAllureLabels;
import aicore.utils.AICorePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.CommonUtils;
import aicore.utils.settings.MyProfilePageUtils;
import aicore.utils.settings.PersonalAccessTokenUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

public class PersonalAccessTokenTests extends AbstractE2ETest {

	@BeforeAll
	public void login() throws IOException {
		login(page, UserType.NATIVE);
	}

	@BeforeEach
	public void setup() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		MyProfilePageUtils.clickOnMyProfileCard(page);
	}

	@Test
    @Epic(AICoreAllureLabels.SETTINGS_EPIC)
    @Feature(AICoreAllureLabels.MY_PROFILE_FEATURE)
    @DisplayName("Generate Personal Access Key")
    @Description("This test creates a personal access key. \nValidates examples and then deletes the access key.")
	void testGenerateKey() {
		// create key
		String timestamp = CommonUtils.getTimeStampName();
		String keyName = "New Key" + timestamp;
		String description = "New desc" + timestamp;

		PersonalAccessTokenUtils.clickNewKeyButton(page);
		PersonalAccessTokenUtils.enterKeyName(page, keyName);
		PersonalAccessTokenUtils.enterDescription(page, description);
		AICorePageUtils.saveScreenshotAtStep(page, "Capture screenshot of generating a personal access toke");
		PersonalAccessTokenUtils.clickGenerateButton(page);

		// Extract key info
		String accessKey = PersonalAccessTokenUtils.copyAccessKey(page);
		String secretKey = PersonalAccessTokenUtils.copySecretKey(page);

		String jsContent = PersonalAccessTokenUtils.getJavasScriptExample(page);
		int actualAccessCount = CommonUtils.countIdOccurances(jsContent, accessKey);
		int actualSecretCount = CommonUtils.countIdOccurances(jsContent, secretKey);
		Assertions.assertEquals(1, actualAccessCount, "Access Key mismatch in JS");
		Assertions.assertEquals(1, actualSecretCount, "Secret Key mismatch in JS");

		String pyConent = PersonalAccessTokenUtils.getPyExample(page);
		actualAccessCount = CommonUtils.countIdOccurances(pyConent, accessKey);
		actualSecretCount = CommonUtils.countIdOccurances(pyConent, secretKey);
		Assertions.assertEquals(1, actualAccessCount, "Access Key mismatch in PY");
		Assertions.assertEquals(1, actualSecretCount, "Secret Key mismatch in PY");

		PersonalAccessTokenUtils.clickOnCloseBtn(page);

		// see personal access tokens on table in page
		String actualKeyName = PersonalAccessTokenUtils.validateGeneratedKey(page, keyName);
		Assertions.assertEquals(actualKeyName, keyName);

		String actualDescription = PersonalAccessTokenUtils.validateDescriptionName(page, description);
		Assertions.assertEquals(actualDescription, description);

		PersonalAccessTokenUtils.clickOnDeleteIcon(page, keyName);
		// TODO validate delete toast message
//		MyProfilePageUtils.deleteKeyToastMessage(page);
	}

}
