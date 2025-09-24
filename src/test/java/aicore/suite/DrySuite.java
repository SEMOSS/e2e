package aicore.suite;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("Features")
@ConfigurationParameter(key = "cucumber.glue", value = "aicore.steps,aicore.hooks")
//@ConfigurationParameter(key = "cucumber.glue", value =  "aicore.hooks")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,html:target/cucumber-report.html")
@ConfigurationParameter(key = "dryRun", value = "true")
//@ConfigurationParameter(key = "cucumber.plugin", value = "html:target/cucumber-report.html")
public class DrySuite {
}
