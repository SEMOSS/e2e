package aicore.unit.function;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.FunctionTestUtils;

public class ViewAddFunctionOptionsTests extends AbstractE2ETest {

	private static Stream<Arguments> provideOptionsToAddOnFunctionPage() {
	    return Stream.of(
	    		Arguments.of("AWS Image Text Extraction, AWS Polly, AWS Transcribe, AWS Comprehend, Azure Document Intelligence, Azure Speech To Text, Google Speech To Text, Google OCR, REST")
	    		);
	}
	
	/// TODO: THIS TEST IF FAILING HERE AND IN THE CUCUMBER FEATURE FILE. NEEDS TO BE FIXED AND THE 
	/// CUCUMBER FILE DELETED
	@ParameterizedTest
	@MethodSource("provideOptionsToAddOnFunctionPage")
	void testVerifyOptionsOnAddFunctionPage(String functionOptions) {
		login(page, UserType.NATIVE); //TODO find a way to only login once at the beginning of the set of tests
		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		FunctionTestUtils.validateSearchBar(page);
		// TODO this step needs to be fixed, also failing in feature file
		FunctionTestUtils.verifyOptionsWithIconsOnConnectToFunctionPage(page, functionOptions);
	}
}
