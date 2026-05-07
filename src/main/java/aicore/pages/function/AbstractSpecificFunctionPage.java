package aicore.pages.function;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import aicore.pages.engine.AbstractBaseEnginePage;
import aicore.utils.TestResourceTrackerHelper;

public abstract class AbstractSpecificFunctionPage extends AbstractBaseEnginePage{

	protected static final String ENGINE_SETTINGS_ACCESS_CONTROL_TAB_TST_ID = "engineLayout-Access Control-tab";
	
	protected String functionName;
	
	public AbstractSpecificFunctionPage(Page page, String functionName) {
		super(page);
		this.functionName = functionName;
	}

	@Override
	public String getEngineType() {
		return TestResourceTrackerHelper.CATALOG_TYPE_FUNCTION;
	}

	@Override
	public String getEngineName() {
		return this.functionName;
	}
	
	@Override
    public void clickOnOpensettings() {
		selectCatalog(); /// for functions, selecting the function card opens 
					/// the settings page for that specific function 
	}
	
	@Override
    public void clickOnAccessControl() {
		Locator btn = page.getByTestId(ENGINE_SETTINGS_ACCESS_CONTROL_TAB_TST_ID);
		waitAndClick(btn);
	}
}
