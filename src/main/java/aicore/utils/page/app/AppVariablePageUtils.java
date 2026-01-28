package aicore.utils.page.app;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AppVariablePageUtils {
	private static final String VariableOption = "//div[contains(@class,'flexlayout__border_button')][@title='Variables']";
	private static final String AddVariableButton = "//h6[text()='Variables']/parent::div/child::div//button//*[@data-testid='AddIcon']";
	private static final String VariableNameInput = "//p[text()='Variable Name']//following-sibling::div//div//input[@placeholder='Name']";
	private static final String VariableTypeDropdown = "//p[text()='Type']//following-sibling::div[1]";
	private static final String VariableTypeDropdownList = "//ul[@role='listbox']//li[@data-value='{variableType}']";
	private static final String VariableValueInput = "//p[text()='Value']//following-sibling::div//div//div";
	private static final String VariableText = "//*[contains(@class, 'view-lines')]/*[contains(@class, 'view-line')]/span/span";
	private static final String VariableValueInputBox = "//p[text()='Value']//following-sibling::div//div//input";
	private static final String VariableValueInputDropdownList = "//ul[@role='listbox']//li//span[text()='{value}']";
	private static final String CreateVariableButton = "//span[text()='Add']";
	private static final String ToastMessage = "//*[contains(@class, 'MuiAlert-message')]";
	private static final String CatalogVariable = "//div[@id='home__content']//h4";
	private static final String VariableListItem = "//button//div//p[text()='{variableName}']";
	private static final String SAVEVARIABLE = "//span[text()='Save']";
	private static final String OpenMenuOption = "//p[text()='{variableName}']/../../../../..//following-sibling::div//div//button[@title=\"Open Menu\"]";
	private static final String EditVariableOption = "//li[@value=\"Edit\"]";

	public static void clickOnVariableOption(Page page) {
		Locator variables = page.locator(VariableOption);
		variables.isVisible();
		variables.click();
	}

	public static void clickOnAddVariableButton(Page page) {
		boolean addVariableButtonIsVisible = page.locator(AddVariableButton).isVisible();
		if (addVariableButtonIsVisible) {
			page.locator(AddVariableButton).click();
		} else {
			throw new RuntimeException("Add Variable button is not visible");
		}
	}

	public static void enterVariableName(Page page, String variableName) {
		boolean variableNameInputIsVisible = page.locator(VariableNameInput).isVisible();
		if (variableNameInputIsVisible) {
			page.locator(VariableNameInput).fill(variableName);
		} else {
			throw new RuntimeException("Variable Name input is not visible");
		}
	}

	public static void clickVariableType(Page page) {
		Locator variableTypeButton = page.locator(VariableTypeDropdown);
		if (variableTypeButton.isVisible()) {
			variableTypeButton.click();
		} else {
			throw new RuntimeException("Variable Type dropdown is not visible");
		}
	}
	public static void selectVariableType(Page page, String variableType) {
		Locator variableTypeButton = page.locator(VariableTypeDropdown);
		if (variableTypeButton.isVisible()) {
			variableTypeButton.click();
			Locator variableTypeDropdownListOption = page
					.locator(VariableTypeDropdownList.replace("{variableType}", variableType));
			boolean variableTypeDropdownIsVisible = variableTypeDropdownListOption.isVisible();

			if (variableTypeDropdownIsVisible) {
				variableTypeDropdownListOption.click();
			} else {
				throw new RuntimeException("Variable Type option " + variableType + " is not visible");
			}
		} else {
			throw new RuntimeException("Variable Type dropdown is not visible");
		}
		System.out.println("Selected Variable Type: " + variableType);
	}

	public static void setVariableValue(Page page, String variableValue) {
		Locator VariableValue = page.locator(VariableValueInput);
		if (VariableValue.isVisible()) {
			VariableValue.click();
			Locator variableValueDropdownListOption = page
					.locator(VariableValueInputDropdownList.replace("{value}", variableValue));
			boolean variableValueDropdownIsVisible = variableValueDropdownListOption.isVisible();

			if (variableValueDropdownIsVisible) {
				variableValueDropdownListOption.click();
			} else {
				throw new RuntimeException("Variable Value option " + variableValue + " is not visible");
			}
		} else {
			throw new RuntimeException("Variable Value dropdown is not visible");
		}
	}

	public static void enterVariable(Page page, String variableValue) {
		Locator VariableTextArea = page.locator(VariableText);
		VariableTextArea.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
		if (VariableTextArea.isVisible()) {
			VariableTextArea.click();
			String textContent = VariableTextArea.textContent();
			VariableTextArea.press("ArrowDown");

			// Press backspace until the text content is empty
			while (textContent != null && !textContent.isEmpty()) {
				VariableTextArea.press("Backspace");
				// Small delay to let the DOM update
				page.waitForTimeout(50);
				textContent = VariableTextArea.textContent();
			}
			page.keyboard().type(variableValue, new Keyboard.TypeOptions().setDelay(200));
		} else {
			throw new RuntimeException("Variable Text Area is not visible");
		}
	}

	public static void enterVariableValue(Page page, String variableValue) {
		Locator VariableValue = page.locator(VariableValueInputBox);
		if (VariableValue.isVisible()) {
			VariableValue.fill(variableValue);
		} else {
			throw new RuntimeException("Variable Value dropdown is not visible");
		}
	}

	public static void clickOnCreateVariableButton(Page page) {
		boolean createVariableButtonIsVisible = page.locator(CreateVariableButton).isVisible();
		if (createVariableButtonIsVisible) {
			page.locator(CreateVariableButton).click();
		} else {
			throw new RuntimeException("Create Variable button is not visible");
		}
	}

	public static String getCatalogNameForVariable(Page page) {
		String catalogVariableName = page.locator(CatalogVariable).textContent();
		if (catalogVariableName == null) {
			throw new RuntimeException("Catalog Variable Name is not visible");
		}
		return catalogVariableName;
	}

	public static void validateSuccessToastMessage(Page page, String variableName) {
		Locator SuccessAlertMessageWithName = page.locator(ToastMessage);
		boolean isVisible = SuccessAlertMessageWithName.isVisible();
		if (!isVisible) {
			throw new AssertionError("Toast message for variable '" + variableName + "' was not visible.");
		}
		String toastMessage = SuccessAlertMessageWithName.textContent();
		String expectedToastMessage = "Successfully added " + variableName + ", remember to save your app.";
		if (toastMessage == null || !toastMessage.equals(expectedToastMessage)) {
			throw new AssertionError("Toast message for variable '" + variableName + "' was not matched.");
		}
	}

	public static void verifyVariablePresentInList(Page page, String variableName, String variableType) {
		page.locator(VariableListItem.replace("{variableName}", variableName)).isVisible();
//		boolean variableTypeIsVisible = page.locator(VariableListItem.replace("{variableName}", variableType))
//				.isVisible();
//		if (!variableNameIsVisible || !variableTypeIsVisible) {
//			throw new RuntimeException("Variable '" + variableName + "' with type '" + variableType
//					+ "' is not visible in the variable list");
//		}
	}

	public static void clickOnEditVariableOption(Page page) {
		page.locator(EditVariableOption).click();
	}

	public static void clickOnVariableOpenMenu(Page page, String variableName) {
		Locator variableItem = page.locator(OpenMenuOption.replace("{variableName}", variableName));
		variableItem.isVisible();
		variableItem.click();
	}

	public static void clickOnSaveVariableButton(Page page) {
		page.locator(SAVEVARIABLE).isVisible();
		page.locator(SAVEVARIABLE).click();
	}	

}
