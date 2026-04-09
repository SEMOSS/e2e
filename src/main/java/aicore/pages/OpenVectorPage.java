package aicore.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import aicore.framework.ConfigUtils;
import aicore.utils.AICorePageUtils;
import aicore.utils.CommonUtils;

public class OpenVectorPage extends AbstractAddCatalogPageBase {

	private static final String ADD_VECTOR_BUTTON_XPATH = "//button[text()='Add ']";
	private static final String VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH = "//div[contains(@class,'MuiAlert-message css-')]";

	private static final String ACCESS_CONTROL_XPATH = "//button[text()='Access Control']";
	private static final String DELETE_BUTTON_XPATH = "//span[text()='Delete']";
	private static final String CONFIRMATION_POPUP_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]";
	private static final String CONFIRMATION_POPUP_DELETE_BUTTON_XPATH = "//div[contains(@class,'MuiDialog-paperWidthSm')]//div//button[contains(@class,'MuiButton-containedSizeMedium')]";
	private static final String VECTOR_CARD_XPATH = "//p[text()='{catalogName}']";
	private static final String DELETE_TOAST_MESSAGE_XPATH = "//div[text()='Successfully deleted Vector']";
	private static final String VECTOR_ID = "//*[@data-testid=\"ContentCopyOutlinedIcon\"]/../..";
	private static final String COPY_VECTOR_ID = "engineHeader-copy-Vector-id-btn";
	private static final String COPIED_TOAST_DATA_XPATH = "//div[text()='ID copied to clipboard']";
	private static final String VECTOR_DESCRIPTION_XPATH = "//*[@id='home__content']//div//h6[contains(@class,'MuiTypography-subtitle1')]";
	private static final String VECTOR_TAGS_XPATH = "//div[contains(@class,'flex flex-1 flex-col')]//span";
	private static final String UPDATED_BY_XPATH = "//*[@id='home__content']//p[contains(text(),'Updated by ')]";
	private static final String UPDATED_AT_XPATH = "//*[@id='home__content']//p[contains(text(),'at ')]";
	private static final String DISCOVERABLE_VECTORS_XPATH = "//button[normalize-space()='Discoverable Vectors']";
	private static final String TOASTER_MESSAGE_XPATH = "//div[text()='{toasterMessage}']";
	private static final String Q_A_TAB_XPATH = "//button[text()='Q&A']";
	private static final String ADJUST_CONFIGURATIONS_PANEL_XPATH = "//span[text()='{panelName}']/parent::div[contains(@class,'flex items-center')]";

	private static final String CLICK_ON_QA_TAB_DATATESTID = "engineLayout-Q&A-tab";

	private static final String SLIDER_BAR_XPATH = "//p[text()='{sliderName}']/following::span[@role='slider']";

	public OpenVectorPage(Page page) {
		this.page = page;
	}

	public void clickOnAddVectorButton() {
		page.click(ADD_VECTOR_BUTTON_XPATH);
	}

	public String verifyVectorCreatedToastMessage() {
		String toastMessage = page.textContent(VECTOR_CREATED_SUCCESS_TOAST_MESSAGE_XPATH).trim();
		return toastMessage;
	}

	public void verifyVectorTitle(String vectorTitle) {
		Locator vector = page.getByTestId("Title");
		vector.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		if (!vector.isVisible()) {
			throw new AssertionError("Vector title '" + vectorTitle + "' is not visible on the page.");
		}
	}

	public void clickOnAccessControl() {
		page.locator(ACCESS_CONTROL_XPATH).isVisible();
		page.locator(ACCESS_CONTROL_XPATH).click();
	}

	public void clickOnDeleteButton() {
		page.locator(DELETE_BUTTON_XPATH).isVisible();
		page.locator(DELETE_BUTTON_XPATH).click();
	}

	public void confirmationPopUp() {
		page.locator(CONFIRMATION_POPUP_XPATH)
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).isVisible();
		page.locator(CONFIRMATION_POPUP_DELETE_BUTTON_XPATH).click();
	}

	public void addedVectorCard(String catalogName) {
		page.locator(VECTOR_CARD_XPATH.replace("{catalogName}", catalogName)).isVisible();
		page.locator(VECTOR_CARD_XPATH.replace("{catalogName}", catalogName)).click();
	}

	public boolean verifyToastMessage(String expectedToastMessage) {
		Locator alert = page.locator(TOASTER_MESSAGE_XPATH.replace("{toasterMessage}", expectedToastMessage));
		String actualToastMessage = page
				.locator(TOASTER_MESSAGE_XPATH.replace("{toasterMessage}", expectedToastMessage)).textContent().trim();
		return actualToastMessage.equals(expectedToastMessage);
		// return AICorePageUtils.verifySuccessToastMessage(page, alert);
	}

	public void copyVectorId() {
		page.getByTestId(COPY_VECTOR_ID).isVisible();
		page.getByTestId(COPY_VECTOR_ID).click();
	}

	public String copiedSuccessToastMessage() {
		page.locator(COPIED_TOAST_DATA_XPATH);
		String toastMessage = page.locator(COPIED_TOAST_DATA_XPATH).textContent();
		return toastMessage;
	}

	public void verifyVectorDescription() {
		page.locator(VECTOR_DESCRIPTION_XPATH).isVisible();
		String vectorDescription = page.locator(VECTOR_DESCRIPTION_XPATH).textContent().trim();
		if (vectorDescription == null || vectorDescription.isEmpty()) {
			throw new AssertionError("Vector description is not available or empty.");
		}
	}

	public void verifyChangeAccessButton() {
		if (!page.getByText("Change Access").isVisible()) {
			throw new AssertionError("Change Access button is not visible on the Vector page.");
		}
	}

	public void verifyCurrentUrlContainsVectorId() {
		page.getByTestId(COPY_VECTOR_ID).isVisible();
		String vectorIdText = page.getByTestId(COPY_VECTOR_ID).textContent().trim();
		String currentUrl = page.url();
		if (!currentUrl.contains(vectorIdText)) {
			throw new AssertionError("Vector ID is not present or wrong in Vector catalog: " + vectorIdText);
		}
	}

	public void verifyVectorTags(String tagName) {
		page.locator(VECTOR_TAGS_XPATH.replace("{tagName}", tagName)).first().isVisible();
		if (!page.locator((VECTOR_TAGS_XPATH.replace("{tagName}", tagName))).first().isVisible()) {
			throw new AssertionError("Tag " + tagName + " is not visible on the Vector page.");
		}
	}

	public void verifyUpdatedBy(String role) {
		String username = ConfigUtils.getValue(role.toUpperCase() + "_USERNAME");
		page.locator(UPDATED_BY_XPATH).isVisible();
		String updatedByText = page.locator(UPDATED_BY_XPATH).textContent().trim();
		if (!updatedByText.contains(username)) {
			throw new AssertionError("Updated By does not contain the expected username: " + username);
		}
	}

	public void verifyUpdatedAt() {
		page.locator(UPDATED_AT_XPATH).isVisible();
		String updatedAtText = page.locator(UPDATED_AT_XPATH).textContent().trim();
		String[] updatedDate = CommonUtils.splitStringBySpace(updatedAtText, 1);
		String currentDate = CommonUtils.getTodayDateFormatted();
		if (!updatedDate[1].equals(currentDate)) {
			throw new AssertionError("Updated At does not match the current date: " + currentDate);
		}
	}

	public void clickOnDiscoverableVectorsButton() {
		page.locator(DISCOVERABLE_VECTORS_XPATH).click();
	}

	public void clickOnQnAButton() {
		page.getByTestId(CLICK_ON_QA_TAB_DATATESTID).click();
	}

	public boolean verifyQandATabIsDisplayed() {
		return page.locator(Q_A_TAB_XPATH).isVisible();
	}

	public boolean verifyPanelIsVisible(String panelName) {
		return page.locator(ADJUST_CONFIGURATIONS_PANEL_XPATH.replace("{panelName}", panelName)).isVisible();
	}

	public void clickOnQandATab() {
		page.getByTestId(CLICK_ON_QA_TAB_DATATESTID).click();
	}

	public void selectModelFromDropdown(String modelName) {
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(modelName)).click();
	}

	public void adjustSlider(String sliderName, String target) {
		double targetValue = Double.parseDouble(target);
		Locator slider = page.locator(SLIDER_BAR_XPATH.replace("{sliderName}", sliderName)).first();
		slider.focus();
		double current = Double.parseDouble(slider.getAttribute("aria-valuenow"));
		while (current != targetValue) {
			if (current < targetValue) {
				slider.press("ArrowRight");
			} else {
				slider.press("ArrowLeft");
			}
			current = Double.parseDouble(slider.getAttribute("aria-valuenow"));
		}
	}

	public String getCurrentSliderValue(String value, String sliderName) {
		Locator slidervalue = page.locator(SLIDER_BAR_XPATH.replace("{sliderName}", sliderName)).first();
		AICorePageUtils.waitFor(slidervalue);
		return slidervalue.getAttribute("aria-valuenow");
	}
}