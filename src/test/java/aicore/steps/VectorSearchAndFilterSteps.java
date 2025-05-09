//package aicore.steps;
//
//import static org.junit.Assert.assertTrue;
//
//import aicore.hooks.SetupHooks;
//import aicore.pages.VectorSearchAndFilterPage;
//import aicore.utils.CommonUtils;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class VectorSearchAndFilterSteps {
//
//	private VectorSearchAndFilterPage openVectorPage;
//	protected static String timestamp;
//
//	public VectorSearchAndFilterSteps() {
//		timestamp = CommonUtils.getTimeStampName();
//		this.openVectorPage = new VectorSearchAndFilterPage(SetupHooks.getPage(), timestamp);
//	}
//
//	@Given("User navigates to Open Vector Page")
//	public void user_navigates_to_open_vector() {
//		openVectorPage.clickOnOpenVector();
//	}
//
//	@When("User clciks on {string}")
//	public void user_clicks_on_the_vector(String vector) {
//		openVectorPage.clickVector(vector);
//	}
//
//	@And("User clicks on {string} button")
//	public void user_clicks_on_edit_button(String edit) {
//		openVectorPage.clickEdit(edit);
//	}
//
//	@And("User enters the filters {string} in {string} box and clicks enter")
//	public void enters_in_filter_box(String input, String filter) {
//		openVectorPage.enterFilters(input, filter);
//	}
//
//	@And("User enters the filters {string} in {string} box")
//	public void enters_in_filter_box1(String input, String filter) {
//		openVectorPage.enterFilters1(input, filter);
//	}
//
//	@Then("User clicks on the {string} button")
//	public void click_on_submit_button(String submit) {
//		openVectorPage.clickSubmit(submit);
//	}
//
//	@When("User clicks on Search box")
//	public void click_search_box() {
//		openVectorPage.clickSearch();
//	}
//
//	@And("User enters the search value as {string} and presses Enter")
//	public void user_searches_in_the_vector_catalog_search_box(String VectorName) {
//		openVectorPage.searchVector(VectorName);
//	}
//
//	@Then("User should see the {string} vector on the Vector Catalog page")
//	public void user_should_see_the_on_the_vector_catalog_page(String vectorName) {
//		boolean isVectorDisplayed = openVectorPage.verifyVectorIsDisplayedOnCatalogPage(vectorName);
//		assertTrue(isVectorDisplayed);
//	}
//
//	@When("User clicks on search by under Filter By Section")
//	public void select_under_filter_by() {
//		openVectorPage.selectFilterBy();
//	}
//
//	@And("User enters {string} in the search box and clicks on it under {string}")
//	public void select_under_filter_by1(String input, String filter) {
//		openVectorPage.selectFilterBy1(input, filter);
//	}
//
//	@Then("User should see {string} vector on the Vectort Catalog page under My Vectors tab")
//	public void user_should_see_the_on_the_vector_catalog_page1(String vectorName) {
//		boolean isVectorDisplayed = openVectorPage.verifyVectorIsDisplayedOnCatalogPage(vectorName);
//		assertTrue(isVectorDisplayed);
//	}
//
//	@When("User clicks on Bookmark  icon of {string} then the vector is bookmarked")
//	public void user_clicks_on_bookmark_icon(String bookmark) {
//		openVectorPage.bookmarkVector(bookmark);
//	}
//
//}
