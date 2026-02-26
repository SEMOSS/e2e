package aicore.base;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Page;

/**
 * Main method to setup test users by reading the config file
 */
public class TestUsersSetup {
	private static final Logger logger = LogManager.getLogger(TestUsersSetup.class);

	public static void main(String[] args) throws IOException {
		GenericSetupUtils.initialize();
		Page page = GenericSetupUtils.setupPlaywright();

		logger.info("Creating users");
		GenericSetupUtils.createUsers(page);
	}

}
