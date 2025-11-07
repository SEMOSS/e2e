package aicore.suite;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@ExcludeTags("ApplicationBugFailure")
@SelectClasspathResource("Features")
@ConfigurationParameter(key = "cucumber.glue", value = "aicore.steps,aicore.hooks")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@Regression,@Documentation")
// @ConfigurationParameter(key = "cucumber.filter.tags", value = "@Documentation")
// @ConfigurationParameter(key = "cucumber.filter.tags", value = "@Smoke")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,html:target/cucumber-report.html")
public class FullSuite {
}
