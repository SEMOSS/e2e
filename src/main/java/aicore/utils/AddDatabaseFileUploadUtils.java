package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AddDatabaseFileUploadUtils {

	private static final String TAB_SELECTION_TESTID = "tab-{tabName}";
	private static final String DATABASE_TYPE_SELECTION_TESTID = "database-form-option-DATABASE_TYPE-{dbType}";
	private static final String DATABASE_TYPE_DROPDOWN_XPATH = "//*[@data-testid='database-form-input-DATABASE_TYPE']";
	private static final String METAMODEL_TYPE_DROPDOWN_XPATH = "//*[@data-testid='database-form-input-METAMODEL_TYPE']";
	private static final String METAMODEL_TYPE_SELECTION_TESTID = "database-form-option-METAMODEL_TYPE-{metaModelType}";
	private static final String DATABASE_NAME_INPUT_XPATH = "//input[@data-testid='database-form-input-DATABASE_NAME']";
	private static final String COLUMNS_XPATH = "//*[(@data-testid='column-name-input-0-{columnIndex}')]";
	private static final String TOGGLE_BUTTON_TESTID = "toggle-button-0-{columnIndex}";
	private static final String EDIT_BUTTON_TESTID = "edit-button-0-{columnIndex}";
	private static final String FULL_SCREEN_BUTTON_TESTID = "engineMetadata-fullscreen-btn";
	private static final String TABLE_LIST_BUTTON_TESTID = "engineMetadata-tablelist-btn";
	private static final String REFRESH_BUTTON_TESTID = "engineMetadata-refresh-btn";
	private static final String SAVE_BUTTON_TESTID = "engineMetadata-save-btn";
	private static final String CANCEL_BUTTON_TESTID = "engineMetadata-cancel-btn";
	private static final String CREATE_RELATIONSHIP_BUTTON_TESTID = "engineMetadata-createrelationship-btn";
	private static final String ADD_RELATIONSHIP_BUTTON_TESTID = "add-connection";
	private static final String SAVE_RELATIONSHIP_BUTTON_TESTID = "save-connection";
	private static final String FULL_SCREEN_CLOSE_BUTTON_XPATH = "//button[@aria-label='Close']";
	private static final String CREATE_CONNECTION_DATATESTID = "add-connection";

	public static void selectTab(Page page, String tabName) {
		page.getByTestId(TAB_SELECTION_TESTID.replace("{tabName}", tabName)).click();
	}

	public static void selectFileType(Page page, String fileType) {
		page.getByText(fileType).click();
	}

	public static void selectDatabaseType(Page page, String dbType) {
		page.locator(DATABASE_TYPE_DROPDOWN_XPATH).click();
		page.getByTestId(DATABASE_TYPE_SELECTION_TESTID.replace("{dbType}", dbType)).click();
	}

	public static void selectMetamodelType(Page page, String metaModelType) {
		page.locator(METAMODEL_TYPE_DROPDOWN_XPATH).click();
		page.getByTestId(METAMODEL_TYPE_SELECTION_TESTID.replace("{metaModelType}", metaModelType)).click();
	}

	public static void enterDatabaseName(Page page, String dbName) {
		page.locator(DATABASE_NAME_INPUT_XPATH).fill(dbName);
	}

	public static void checkColumnsAreEditable(Page page) {
		int index = 0;
		while (true) {
			String xpath = COLUMNS_XPATH.replace("{columnIndex}", String.valueOf(index));
			Locator column = page.locator(xpath);
			if (column.count() == 0) {
				break;
			}
			// check column is visible
			if (!column.isVisible()) {
				throw new AssertionError("Column at index " + index + " is not visible");
			}
			// check toggle button visibility and enabled state
			String toggleTestId = TOGGLE_BUTTON_TESTID.replace("{columnIndex}", String.valueOf(index));
			Locator toggleButton = page.getByTestId(toggleTestId);
			if (toggleButton.count() == 0 || !toggleButton.isVisible() || !toggleButton.isEnabled()) {
				throw new AssertionError(
						"Toggle button for column " + index + " is missing, not visible or not enabled");
			}
			// check edit button visibility and enabled state
			String editTestId = EDIT_BUTTON_TESTID.replace("{columnIndex}", String.valueOf(index));
			Locator editButton = page.getByTestId(editTestId);
			if (editButton.count() == 0 || !editButton.isVisible() || !editButton.isEnabled()) {
				throw new AssertionError("Edit button for column " + index + " is missing, not visible or not enabled");
			}
			index++;
		}
	}

	public static boolean verifyTableName(Page page, String tableName) {
		String[] table = tableName.split("\\.");
		String fileName = table[0];
		String fileType = table[1];
		String tableIsVisiable = page.locator("//button[@role='combobox']//span").textContent();
		return tableIsVisiable.contains(fileName) && tableIsVisiable.contains(fileType);
	}

	public static void verifyFullScreenBtn(Page page) {
		page.getByTestId(FULL_SCREEN_BUTTON_TESTID).isVisible();
		page.getByTestId(FULL_SCREEN_BUTTON_TESTID).isEnabled();
		page.getByTestId(FULL_SCREEN_BUTTON_TESTID).click();
		if (page.locator(FULL_SCREEN_CLOSE_BUTTON_XPATH).first().isVisible()) {
			page.locator(FULL_SCREEN_CLOSE_BUTTON_XPATH).first().click();
		} else {
			throw new AssertionError("Full screen button is not enabled");
		}
	}

	public static void verifySelectTableBtn(Page page) {
		Locator selectTable = page.getByTestId(TABLE_LIST_BUTTON_TESTID);
		AICorePageUtils.waitFor(selectTable);
		page.getByTestId(TABLE_LIST_BUTTON_TESTID).isEnabled();
		page.getByTestId(TABLE_LIST_BUTTON_TESTID).click();
		if (page.getByRole(AriaRole.MENU).isVisible()) {
			page.locator("html").click();
		} else {
			throw new AssertionError("Select table Menu is not enabled");
		}
	}

	public static void verifyResetbtn(Page page) {
		Locator resetButton = page.getByTestId(REFRESH_BUTTON_TESTID);
		AICorePageUtils.waitFor(resetButton);
		resetButton.isEnabled();
		resetButton.click(new Locator.ClickOptions().setForce(true));
	}

	public static void verifyCreateRealtionshipBtn(Page page, String parentTable, String childTable) {
		page.getByTestId(CREATE_RELATIONSHIP_BUTTON_TESTID).click();
		page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Select or type parent table"))
				.isVisible();
		page.getByRole(AriaRole.COMBOBOX).filter(new Locator.FilterOptions().setHasText("Select or type parent table"))
				.click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(parentTable)).click();
		page.getByRole(AriaRole.COMBOBOX).filter(new Locator.FilterOptions().setHasText("Select or type child table"))
				.click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(childTable)).click();
	}

	public static void verifySaveBtn(Page page) {
		Locator saveButton = page.getByTestId(SAVE_BUTTON_TESTID);
		AICorePageUtils.waitFor(saveButton);
		saveButton.isEnabled();
	}

	public static void verifyCancelBtn(Page page) {
		page.getByTestId(CANCEL_BUTTON_TESTID).isVisible();
		page.getByTestId(CANCEL_BUTTON_TESTID).isEnabled();
	}

	public static void verifyAddBtnForCreateConnection(Page page) {
		Locator addButton = page.getByTestId(ADD_RELATIONSHIP_BUTTON_TESTID);
		AICorePageUtils.waitFor(addButton);
		addButton.hover();
		addButton.click();
	}

	public static void verifySaveBtnForCreateRelationship(Page page) {
		Locator createRelationshipButton = page.getByTestId(SAVE_RELATIONSHIP_BUTTON_TESTID);
		AICorePageUtils.waitFor(createRelationshipButton);
		createRelationshipButton.focus();
		createRelationshipButton.click(new Locator.ClickOptions().setForce(true));
	}
}