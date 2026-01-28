package aicore.pages.app;

import com.microsoft.playwright.Page;

import aicore.utils.page.app.AppVariablePageUtils;

public class AppVariablePage {

	private Page page;

	public AppVariablePage(Page page) {
		this.page = page;
	}

	public void clickOnVariablesOption() {
		AppVariablePageUtils.clickOnVariableOption(page);
	}

	public void clickOnAddVariableButton() {
		AppVariablePageUtils.clickOnAddVariableButton(page);
	}

	public void enterVariableName(String variableName) {
		AppVariablePageUtils.enterVariableName(page, variableName);
	}

	public void selectVariableType(String variableType) {
		AppVariablePageUtils.selectVariableType(page, variableType);
	}

	public void clickVariableType() {
		AppVariablePageUtils.clickVariableType(page);
	}

	public void setVariableValue(String variableValue) {
		AppVariablePageUtils.setVariableValue(page, variableValue);
	}
	public void enterVariableValue(String variableValue) {
		AppVariablePageUtils.enterVariableValue(page, variableValue);
	}
	public void enterVariable(String variableValue) {
		AppVariablePageUtils.enterVariable(page, variableValue);
	}

	public void clickOnCreateVariableButton() {
		AppVariablePageUtils.clickOnCreateVariableButton(page);
	}

	public String getCatalogNameForVariable() {
		return AppVariablePageUtils.getCatalogNameForVariable(page);
	}

	public void validateSuccessToastMessage(String variableName) {
		AppVariablePageUtils.validateSuccessToastMessage(page, variableName);
	}

	public void verifyVariablePresentInList(String variableName, String variableType) {
		AppVariablePageUtils.verifyVariablePresentInList(page, variableName, variableType);
	}

	public void clickOnEditVariableOption() {
		AppVariablePageUtils.clickOnEditVariableOption(page);
	}

	public void clickOnVariableOpenMenu(String variableName) {
		AppVariablePageUtils.clickOnVariableOpenMenu(page, variableName);
	}

	public void clickOnSaveVariableButton() {
		AppVariablePageUtils.clickOnSaveVariableButton(page);
	}

}