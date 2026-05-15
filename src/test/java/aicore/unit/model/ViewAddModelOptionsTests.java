package aicore.unit.model;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.microsoft.playwright.Page;

import aicore.pages.home.MainMenuUtils;
import aicore.pages.model.SettingsModelPageUtils;
import aicore.utils.AbstractPlaywrightTestBase;
import aicore.utils.annotations.PWPage;
import aicore.utils.page.model.ModelPageUtils;

public class ViewAddModelOptionsTests extends AbstractPlaywrightTestBase {

	@BeforeEach
	void setup(@PWPage Page page) {
		loginNativeAdmin(page);
	}

	@AfterEach
	void tearDown(@PWPage Page page) {
		logout(page);
	}

	private static Stream<Arguments> provideModelGroupOptions() {
		return Stream.of(
				Arguments.of(
						"All Providers",
						"Claude Haiku 4.5, Claude Opus 4.6, Claude Sonnet 4.6, Other Anthropic Model, Claude Opus 4.6 (Bedrock), Claude Sonnet 4.6 (Bedrock), Claude Sonnet 4.5 (Bedrock), Claude Haiku 4.5 (Bedrock), Claude Sonnet 4 (Bedrock), Amazon Nova 2 Multimodal Embeddings, Amazon Nova 2 Lite, Amazon Nova 2 Sonic, Amazon Nova Canvas, Amazon Nova Lite, Amazon Nova Micro, Amazon Nova Premier, Amazon Nova Pro, Amazon Nova Reel, Amazon Nova Reel v1.1, Amazon Nova Sonic, Amazon Rerank 1.0, Amazon Titan Embeddings G1 - Text, Amazon Titan Image Generator G1 v2, Amazon Titan Multimodal Embeddings G1, Amazon Titan Text Embeddings V2, Amazon Titan Text Embeddings v2, Amazon Titan embedding model variant for text embedding workloads on Bedrock., Amazon Titan Image Generator G1 v2, Amazon Titan Multimodal Embeddings G1, Amazon Titan Text Embeddings V2, Amazon Titan Text Embeddings v2, Amazon Titan Text Large, Amazon Titan Text/Image Embeddings, Other AWS Bedrock Model, Other AWS Bedrock Embedding Model, Azure Open AI, Azure OpenAI Embeddings, Claude (Azure), Other Azure OpenAI Model, Other Azure OpenAI Embedding Model, Sonar, Sonar Pro, Sonar Reasoning Pro, Sonar Deep Research, Other Perplexity Model, Falcon, Flan T5 Large, Flan T5 XXL, Llama3 405B, Llama3 70B, Llama3 8B, Mosaic ML, Replit code model – 3b, Other Self Hosted Model, Other Self Hosted Embedding Model, NeMo, Orca, Replit Code Model, Stablity AI, Other Embedded Model"),
				Arguments.of(
						"Anthropic",
						"Claude Haiku 4.5, Claude Opus 4.6, Claude Sonnet 4.6, Other Anthropic Model"),
				Arguments.of(
						"AWS Bedrock",
						"Claude Opus 4.6 (Bedrock), Claude Sonnet 4.6 (Bedrock), Claude Sonnet 4.5 (Bedrock), Claude Haiku 4.5 (Bedrock), Claude Sonnet 4 (Bedrock), Amazon Nova 2 Multimodal Embeddings, Amazon Nova 2 Lite, Amazon Nova 2 Sonic, Amazon Nova Canvas, Amazon Nova Lite, Amazon Nova Micro, Amazon Nova Premier, Amazon Nova Pro, Amazon Nova Reel, Amazon Nova Reel v1.1, Amazon Nova Sonic, Amazon Rerank 1.0, Amazon Titan Embeddings G1 - Text, Amazon Titan Image Generator G1 v2, Amazon Titan Multimodal Embeddings G1, Amazon Titan Text Embeddings V2, Amazon Titan Text Embeddings v2, Amazon Titan embedding model variant for text embedding workloads on Bedrock., Amazon Titan Image Generator G1 v2, Amazon Titan Multimodal Embeddings G1, Amazon Titan Text Embeddings V2, Amazon Titan Text Embeddings v2, Amazon Titan Text Large, Amazon Titan Text/Image Embeddings, Other AWS Bedrock Model, Other AWS Bedrock Embedding Model"),
				Arguments.of(
						"Azure OpenAI",
						"Azure Open AI, Azure OpenAI Embeddings, Claude (Azure), Other Azure OpenAI Model, Other Azure OpenAI Embedding Model"),
				Arguments.of(
						"Google Gemini",
						"Claude Haiku 4.5 (Vertex), Claude Opus 4.6 (Vertex), Claude Sonnet 4.6 (Vertex), Gemini 3.1 Pro Preview, Gemini 3 Flash Preview, Gemini 3.1 Flash Lite Preview, Gemini 3 Pro Image Preview, Gemini 3.1 Flash Image Preview, Gemini 2.5 Pro, Gemini 2.5 Flash, Gemini 2.5 Flash Lite, Gemini 2.0 Flash 001, Gemini 2.5 Flash Image, Other Google Gemini Model"),
				Arguments.of(
						"NVIDIA NIM",
						"EMBED QA 4, Rerank QA Mistral 4B, Other NVIDIA NIM Model"),
				Arguments.of(
						"OpenAI",
						"chatgpt-image-latest, GPT Image 1.5, GPT Image 1, gpt-image-1-mini, GPT-4.1, GPT-5, GPT-5 Mini, GPT-5 nano, GPT-5.1, GPT-5.4, GPT-5.4 Mini, GPT-5.4 Nano, GPT-5.4 Pro, gpt-audio, text-embedding-3-large, text-embedding-3-small, Other OpenAI Model, Other OpenAI Embedding Model"),
				Arguments.of(
						"Perplexity",
						"Sonar, Sonar Pro, Sonar Reasoning Pro, Sonar Deep Research, Other Perplexity Model"),
				Arguments.of(
						"Self Hosted",
						"Falcon, Flan T5 Large, Flan T5 XXL, Llama3 405B, Llama3 70B, Llama3 8B, Mosaic ML, Replit code model – 3b, Other Self Hosted Model, Other Self Hosted Embedding Model"),
				Arguments.of(
						"Embedded",
						"NeMo, Orca, Replit Code Model, Stablity AI, Other Embedded Model"));
	}

	@ParameterizedTest(name = "[{index}] {0}")
	@MethodSource("provideModelGroupOptions")
	void testVerifyModelOptionsUnderGroup(String group, String expectedModelOptions, @PWPage Page page) {
		MainMenuUtils.openMainMenu(page);
		MainMenuUtils.clickOnOpenModel(page);
		ModelPageUtils.clickAddModelButton(page);
		SettingsModelPageUtils.clickOnOptionsGroupTab(page, group);

		String[] expectedModels = expectedModelOptions.split(", ");
		for (String modelOption : expectedModels) {
			boolean isVisible = SettingsModelPageUtils.verifyModelOptionIsVisible(page, modelOption);
			Assertions.assertTrue(isVisible, modelOption + " model option is not visible under " + group);
		}
	}
}
