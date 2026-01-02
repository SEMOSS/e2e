package aicore.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import aicore.hooks.SetupHooks;
import aicore.pages.VerifyVectorDatabasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class VerifyAllVectorDatabaseSteps {
private VerifyVectorDatabasePage verifyVectorDatabasePage;

    public VerifyAllVectorDatabaseSteps() {
         verifyVectorDatabasePage = new VerifyVectorDatabasePage((SetupHooks.getPage()));
    }

    @And("User selects vector database {string} from connection types")
    public void user_Selects_Vector_Database_From_Connection_Types(String database) { 
       verifyVectorDatabasePage.selectVectorDatabaseFromConnectionTypes(database);
    }

    @Then("User can see vector Database form sections with fields:")
	public void user_can_see_form_sections_with_fields(DataTable DBTable) {
		List<Map<String, String>> rows = DBTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			String sectionName = row.get("SECTION_NAME");
			String[] fields = row.get("FIELDS").split(", ");
			for (String field : fields) {
				boolean isFieldVisible = verifyVectorDatabasePage.verifyFieldUnderSection(sectionName, field);
				Assertions.assertTrue(isFieldVisible, field + " is not visible under " + sectionName + " section");
			}
		}
	}

    @Then("User can see vector database mandatory fields")
	public void user_can_see_database_mandatory_fields(DataTable DBTable) {
		String singleCell = DBTable.cells().get(0).get(0);
		String[] fields = singleCell.split(", ");
		for (String field : fields) {
			boolean isFieldMandatory = verifyVectorDatabasePage.isVectorDatabaseFieldMandatory(field);
			Assertions.assertTrue(isFieldMandatory, field + " is not mandatory field");
		}
	}

}
