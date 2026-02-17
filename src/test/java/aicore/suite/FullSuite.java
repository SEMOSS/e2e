package aicore.suite;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@ExcludeTags("ApplicationBugFailure")
@SelectClasspathResource("Features/model")
@ConfigurationParameter(key = "cucumber.glue", value = "aicore.steps,aicore.hooks")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@Regression or @Documentation")
//@ConfigurationParameter(key = "cucumber.filter.tags", value = "@Documentation")
// @ConfigurationParameter(key = "cucumber.filter.tags", value = "@Smoke")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,html:target/cucumber-report.html")
public class FullSuite {
}
