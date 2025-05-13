package aicore.pages;

import com.microsoft.playwright.Page;

public class VectorSearchAndFilterPage {
	
	private Page page;
	private String timestamp;
	
	private static final String VECTOR_XPATH="//a[@data-testid='Vector-icon']";
	private static final String CLICK_SEARCH_XPATH="//input[@placeholder='Search']";
	private static final String SEARCHED_VECTOR_XPATH="//div[@class='css-1icp1k8']//p[text()='{vectorName}']";
	private static final String BOOKMAR_XPATH="//button[@title='Bookmark {bookmark}']";
	private static final String UNBOOKMAR_XPATH="//h6[text()='Bookmarked']/following-sibling::div//button[@title='Unbookmark {bookmarked}']";
	private static final String SEARCHBY_XPATH="//input[@placeholder='Search by...']";
	private static final String EDIT_XPATH="//span[text()='{editButton}']";
	private static final String FILTER_ADD_XPATH="//label[text()='{filter}']/following-sibling::div//input";
	private static final String SUBMIT_BUTTON_XPATH="//button//span[text()='{submit}']";
	private static final String FILTER_XPATH="//li//div//h6[text()='{filterName}']/ancestor::li/following-sibling::div//p[text()='{filter}']";
	private static final String ADD_VECTOR_XPATH="//button[@aria-label='Navigate to import Vector']";
	private static final String CONNECTION_XPATH="//div//p[text()='{connection}']";
	private static final String INPUT_XPATH="//div//label[text()='{inputName}']/following-sibling::div//input";
	private static final String EMBEDDER_DROPDOWN_XPATH = "(//div[contains(@class ,'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input')])[1]";
	private static final String CHUNKING_DROPDOWN_XPATH = "(//div[contains(@class,'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input')])[2]";
	private static final String RECORD_DROPDOWN_XPATH="(//div[contains(@class,'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input')])[3]";
	private static final String SELECT_XPATH="//div//ul//li[text()='{selectName}']";
	private static final String CREATE_VECTOR_XPATH="//div//button//span[text()='Create vector']";
	private static final String DIV_XPATH="//div[@class='css-155d4xt']";

	
	public VectorSearchAndFilterPage(Page page, String timestamp) {
		this.page = page;
		this.timestamp = timestamp;
	}
	
	public void clickOnOpenVector() {
		page.locator(VECTOR_XPATH).click();
	}

	
	public void vectorsTab(String myv) {
		page.getByText(myv).click();
	}

	public void clickVector(String vector) {
	   page.locator(SEARCHED_VECTOR_XPATH.replace("{vectorName}", vector)).click();
	}
	
	public void clickEdit(String edit) {
		page.locator(EDIT_XPATH.replace("{editButton}", edit)).click();
	}
	
	public void enterFilters(String input,String filter) {
		if(input!="") {
		String a[]=input.split(",");
		int n=a.length;
		while(n>0) {
		page.locator(FILTER_ADD_XPATH.replace("{filter}", filter)).click();
		page.locator(FILTER_ADD_XPATH.replace("{filter}", filter)).fill(a[n-1]);
		page.locator(FILTER_ADD_XPATH.replace("{filter}", filter)).press("Enter");
		n--;
		}
		}
	}
	
	
	public void enterFilters1(String input, String filter) {
		if(input!="") {
		String a[]=input.split(",");
		int n=a.length;
		while(n>0) {
			page.locator(FILTER_ADD_XPATH.replace("{filter}", filter)).click();
			page.locator(FILTER_ADD_XPATH.replace("{filter}", filter)).fill(a[n-1]);
			page.getByText(a[n-1]).click();
			n--;
			}
		}
		
	}

	
	public void clickSubmit(String submit) {
		page.locator(SUBMIT_BUTTON_XPATH.replace("{submit}", submit)).click();
	}


	

	public void clickSearch() {
		page.locator(CLICK_SEARCH_XPATH).click();
	}

	public void searchVector(String vectorName) {
		page.locator(CLICK_SEARCH_XPATH).fill(vectorName);
		
	}
	
	public boolean verifyVectorIsDisplayedOnCatalogPage(String vectorName) {
		String vectorNameWithTimestamp = SEARCHED_VECTOR_XPATH.replace("{vectorName}", vectorName);
		boolean isModelVisible = page.isVisible(vectorNameWithTimestamp);
		return isModelVisible;
	}

	public void selectFilterBy() {
		page.locator(SEARCHBY_XPATH).click();
		
	}

	public void selectFilterBy1(String input, String filterName) {
		page.locator(SEARCHBY_XPATH).fill(input);
		page.locator(FILTER_XPATH.replace("{filterName}", filterName).replace("{filter}", input)).click();
		page.locator(SEARCHBY_XPATH).fill("");
	}


	
	
	public void bookmarkVector(String bookmark) {
		page.locator(BOOKMAR_XPATH.replace("{bookmark}", bookmark)).click();
		
	}

	public boolean isBookmarked(String bookmarked) {
		return page.locator(UNBOOKMAR_XPATH.replace("{bookmarked}", bookmarked)).isVisible();
		
		
	}

	public void discoverableTab(String myd) {
		page.getByText(myd).click();
		
	}


	public void addVector() {
		page.locator(ADD_VECTOR_XPATH).click();
	}

	public void selectConnection(String connection) {
		page.locator(CONNECTION_XPATH.replace("{connection}", connection)).click();
	}

	public void inputName(String iName, String name) {
		page.locator(INPUT_XPATH.replace("{inputName}", iName)).click();
		page.locator(INPUT_XPATH.replace("{inputName}", iName)).fill(name);
	    page.locator(DIV_XPATH).click();
	}

	public void selectEmbedder(String name) {
		page.locator(EMBEDDER_DROPDOWN_XPATH).click();
		page.locator(SELECT_XPATH.replace("{selectName}", name)).click();
		
	}
	
	public void selectChunking(String name) {
		page.locator(CHUNKING_DROPDOWN_XPATH).click();
		page.locator(SELECT_XPATH.replace("{selectName}", name)).click();
	}

	public void selectRecord(String name) {
		page.locator(RECORD_DROPDOWN_XPATH).click();
		page.locator(SELECT_XPATH.replace("{selectName}", name)).click();
	}

	public void createVector() {
		page.locator(CREATE_VECTOR_XPATH).click();
	}

	


	

}
