package aicore.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("aicore.unit.database")
public class UnitSuite {
}