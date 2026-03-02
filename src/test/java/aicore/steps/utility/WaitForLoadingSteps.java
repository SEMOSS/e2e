package aicore.steps.utility;

import aicore.hooks.SetupHooks;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.cucumber.java.en.And;

public class WaitForLoadingSteps {

    private Page page;

    public WaitForLoadingSteps() {
        this.page = SetupHooks.getPage();
    }

    @And("User waits for loading")
    public void userWaitsLoading() {
        page.waitForLoadState(LoadState.LOAD);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForTimeout(1000);
    }

}
