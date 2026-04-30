package aicore.unit.function;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.FunctionTestUtils;

public class ViewAddFunctionOptionsTests extends AbstractE2ETest {
	
	@BeforeAll
	void setup() {
		login(page, UserType.NATIVE);
	}

	private Stream<Arguments> provideOptionsToAddOnFunctionPage() {
	    return Stream.of(
	    		Arguments.of("AWS Image Text Extraction, AWS Polly, AWS Transcribe, AWS Comprehend, Azure Document Intelligence, Azure Speech To Text, Google Speech To Text, Google OCR, REST")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideOptionsToAddOnFunctionPage")
	void testVerifyOptionsOnAddFunctionPage(String functionOptions) {		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		FunctionTestUtils.validateSearchBar(page);
		FunctionTestUtils.verifyOptionsWithIconsOnConnectToFunctionPage(page, functionOptions);
	}
}
