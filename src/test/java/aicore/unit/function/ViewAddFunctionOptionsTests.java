package aicore.unit.function;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.AddFunctionPageUtils;
import aicore.utils.FunctionTestUtils;
import aicore.utils.PWPage;

public class ViewAddFunctionOptionsTests extends AbstractPlaywrightTestBase {
	
	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}	
	@AfterEach
	void goHome(@PWPage Page page) {
		logout(page);
	}

	private static Stream<Arguments> provideOptionsToAddOnFunctionPage() {
	    return Stream.of(
	    		Arguments.of("AWS Image Text Extraction, AWS Polly, AWS Transcribe, AWS Comprehend, Azure Document Intelligence, Azure Speech To Text, Google Speech To Text, Google OCR, REST")
	    		);
	}
	
	@ParameterizedTest
	@MethodSource("provideOptionsToAddOnFunctionPage")
	void testVerifyOptionsOnAddFunctionPage(String functionOptions, @PWPage Page page) {		
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenFunction(page); 
		AddFunctionPageUtils.clickOnAddFunctionButton(page);
		FunctionTestUtils.validateSearchBar(page);
		FunctionTestUtils.verifyOptionsWithIconsOnConnectToFunctionPage(page, functionOptions);
	}
}
