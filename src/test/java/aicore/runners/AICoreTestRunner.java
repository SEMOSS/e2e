package aicore.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features", glue = { "aicore.steps" }, plugin = { "pretty",
		"html:target/cucumber-report.html" }, monochrome = true, dryRun = true)

public class AICoreTestRunner {

}
