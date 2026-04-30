package aicore.unit.home;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;

public class MainMenuTests extends AbstractE2ETest {

	@BeforeAll
	public void setup() throws IOException {
		login(page, UserType.NATIVE);
	}
	
	@BeforeEach
	public void openMainMenu() throws IOException {
		MainMenuUtils.openMainMenu(page);
	}
	
	@Test
	public void testAppLibrary() {
		MainMenuUtils.clickOnOpenAppLibrary(page);
		// TODO validate 
	}
	
	@Test
	public void testModel() {
		MainMenuUtils.clickOnOpenModel(page);
		// TODO validate 
	}
	
	@Test
	public void testDatabaseLibrary() {
		MainMenuUtils.clickOnOpenDatabase(page);
		// TODO validate 
	}
	
	@Test
	public void testVectorLibrary() {
		MainMenuUtils.clickOnOpenVector(page);
		// TODO validate 
	}
	
	@Test
	public void testFunction() {
		MainMenuUtils.clickOnOpenFunction(page);
		// TODO validate 
	}
	
	@Test
	public void testStorage() {
		MainMenuUtils.clickOnOpenStorage(page);
		// TODO validate 
	}

	@Test
	public void testGuardrail() {
		MainMenuUtils.clickOnGuardrail(page);
		// TODO validate 
	}
	
	@Test
	public void testSettings() {
		MainMenuUtils.clickOnOpenSettings(page);
		// TODO validate 
	}

}
