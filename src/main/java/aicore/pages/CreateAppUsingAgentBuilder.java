package aicore.pages;

import com.microsoft.playwright.Page;

import aicore.utils.AgentBuilderAppsUtils;
import aicore.utils.CommonUtils;
import aicore.utils.page.app.AppPageUtils;

public class CreateAppUsingAgentBuilder {
	private Page page;
	private String timestamp;

	public CreateAppUsingAgentBuilder(Page page) {
		this.page = page;
		this.timestamp = CommonUtils.getTimeStampName();
	}

	public void clickOnCreateNewAppButton() {
		AppPageUtils.clickOnCreateNewAppButton(page);
	}

	public void enterName(String appName) {
		AgentBuilderAppsUtils.enterName(page, appName, timestamp);
	}

	public void clickOnLLMModels(String modelTitle) {
		AgentBuilderAppsUtils.clickOnSelectLLM(page, modelTitle);
	}

	public void fillPrompt(String promptContext) {
		AgentBuilderAppsUtils.fillPrompt(page, promptContext);
	}

	public void setInput(String setInput) {
		AgentBuilderAppsUtils.setInput(page, setInput);
	}

	public void selectInputType(String inputType) {
		AgentBuilderAppsUtils.selectInputType(page, inputType);
	}

	public void clickOnPreviewButton() {
		AgentBuilderAppsUtils.clickOnPreviewButton(page);
	}

	public void clickOnCreateAppButton() {
		AgentBuilderAppsUtils.clickOnCreateAppButton(page);
	}
	public void setInputInPrompt(String input) {
		AgentBuilderAppsUtils.setInputInPrompt(page, input);
	}
	public void selectInputTypeForInput(String inputType, String setInput) {
		AgentBuilderAppsUtils.selectInputTypeForInput(page, inputType, setInput);
	}
}
