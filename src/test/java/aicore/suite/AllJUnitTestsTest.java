package aicore.suite;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("junit-jupiter")
@SelectPackages("aicore.unit")
public class AllJUnitTestsTest {
    public static final String RUN_COMMAND = "mvn clean verify -Dtest=AllJUnitTestsTest";
}
