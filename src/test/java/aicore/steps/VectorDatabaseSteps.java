package aicore.steps;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.EmbedDocumentPage;
import aicore.pages.OpenVectorPage;
import aicore.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VectorDatabaseSteps {
	private EmbedDocumentPage embedDocumentPage;
	private OpenVectorPage vectorPage;

	public VectorDatabaseSteps() {
		embedDocumentPage = new EmbedDocumentPage(SetupHooks.getPage());
		vectorPage = new OpenVectorPage(SetupHooks.getPage(), AddModelSteps.timestamp);
	}

	@When("User clicks on files")
	public void user_clicks_on_files() {
		embedDocumentPage.clickOnFilesButton();
	}

	@Then("User clicks on Embed New Document")
	public void user_clicks_on_embed_new_document() {
		embedDocumentPage.clickOnAddEmbedDocument();
	}

	@Then("User clicks on Embed button")
	public void user_clicks_on_embed_button() {
		embedDocumentPage.clickOnEmbedDocument();
	}

	@Then("User sees file embeded success toast message {string}")
	public void user_sees_file_embeded_success_toast_message(String toastMessage) {
		String expectedMessage = embedDocumentPage.verifyToastMessage();
		String actualMessage = toastMessage;
		Assertions.assertEquals(actualMessage, expectedMessage, "Embedding of document is not done successfully");
	}

	@Then("User sees file named {string} in the file list")
	public void user_sees_file_named_in_the_file_list(String fileName) {
		String actualvalue = embedDocumentPage.fileNameinList();
		String expectedValue = fileName;
		if (fileName.contains("/")) {
			String[] ActualFileName = fileName.split("/");
			int fileNameIndex = ActualFileName.length - 1;
			Assertions.assertEquals(ActualFileName[fileNameIndex], actualvalue,
					"Name of file is not visible in the list");
		} else {
			Assertions.assertEquals(actualvalue, expectedValue, "Name of file is not visible in the list");
		}
	}

	@Then("User sees date of upload in the file list")
	public void user_sees_date_of_upload_in_the_file_list() {
		String actualDate = embedDocumentPage.dateUploaded();
		String expectedDate = CommonUtils.getTodayDateFormatted();
		Assertions.assertEquals(actualDate, expectedDate, "Upload date is not present or either not same");
	}

	@Then("User sees file size {string} in the file list")
	public void user_sees_file_size_in_the_file_list(String filesize) {
		String actualSize = embedDocumentPage.fileSize();
		String expectedSize = filesize;
		Assertions.assertEquals(actualSize, expectedSize, "File size is not seen in the list");
	}

	@Then("User sees delete icon in the file list")
	public void user_sees_delete_icon_in_the_file_list() {
		embedDocumentPage.deleteButton();
	}

	@Then("User copies the vector id")
	public void user_copies_the_vector_id() {
		vectorPage.copyVectorId();
	}

	@Then("User sees the copied success toast message {string}")
	public void user_sees_the_copied_success_toast_message(String expectedToastMessage) {
		String actualToastMessage = vectorPage.copiedSuccessToastMessage();
		Assertions.assertEquals(actualToastMessage, expectedToastMessage, "Vector Id is not copied successfully");
	}

	@Then("User clicks on Q&A button")
	public void user_clicks_on_qa_button() {
		vectorPage.clickOnQnAButton();
	}

	@And("User clicks on Documents tab")
	public void user_clicks_on_documents_tab() {
		embedDocumentPage.clickOnDocumentsTab();
	}
}
