package aicore.unit.settings.admin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.settings.SettingsPageUtils;

public class AdminSettingsPageTests extends AbstractE2ETest {
	@BeforeAll
	public static void setup() throws IOException {
		login(page, UserType.NATIVE);
	}

	@BeforeEach
	public void openMainMenu() throws IOException {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenSettings(page);
		SettingsPageUtils.clickOnAdminButton(page);
	}
	
    @ParameterizedTest(name = "Verify tile is visible: {0}")
    @ValueSource(strings = {
        "App Settings",
        "Database Settings",
        "Function Settings",
        "Model Settings",
        "Storage Settings",
        "Vector Settings",
        "Jobs",
        "Member Settings",
        "Team Permissions",
        "Configuration",
        "Admin Query",
        "My Profile"
    })
	void testSettingOptions(String tileName) {
		boolean isTileVisible = SettingsPageUtils.checkCardVisible(page, tileName);
		assertTrue(isTileVisible, "Tile not visible: " + tileName);
	}
    
    
    @Test
    void testSearch() {
		SettingsModelPageUtils.clickOnSearchBox(page, "mod");
		String tileName = "Model Settings";
		boolean isTileVisible = SettingsPageUtils.checkCardVisible(page, tileName);
		assertTrue(isTileVisible, "Tile not visible: " + tileName);
    }
}
