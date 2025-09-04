package aicore.suite;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("Features/function/AddFunction.feature")
@ConfigurationParameter(key = "cucumber.glue", value =  "aicore.steps,aicore.hooks")
//@ConfigurationParameter(key = "cucumber.glue", value =  "aicore.hooks")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,html:target/cucumber-report.html")
//@ConfigurationParameter(key = "cucumber.plugin", value = "html:target/cucumber-report.html")
public class FullSuite {
}
