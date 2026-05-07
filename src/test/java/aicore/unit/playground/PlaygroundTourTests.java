package aicore.unit.playground;

import java.awt.print.PageFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import aicore.pages.home.HomePageUtils;
import aicore.utils.AbstractE2ETest;
import aicore.utils.PlaygroundPageUtils;

public class PlaygroundTourTests extends AbstractE2ETest {

    @Test
    void testPlaygroundTour() {
		//post login, navigate to playground app and verify the tour steps using next button
        login(page, UserType.NATIVE);
        HomePageUtils.navigateToHomePage(page);
//		HomePageUtils.clickOnBuildButton(page);
		PlaygroundPageUtils.clickOnPlaygroundAppButton(page);
		int totalSteps = PlaygroundPageUtils.getTotalSteps(page);
		Assertions.assertTrue(totalSteps > 0, "Total steps in the tour should be greater than 0.");
		for (int i = 1; i < totalSteps; i++) {
			page.waitForTimeout(2000); // Wait for 1 second before clicking the next button
			PlaygroundPageUtils.nextTourButton(page);
		}
		//navigate to playground app and verify the tour steps using skip button
		HomePageUtils.navigateToHomePage(page);
//		HomePageUtils.clickOnBuildButton(page);
		PlaygroundPageUtils.clickOnPlaygroundAppButton(page);
		page.waitForTimeout(2000); // Wait for 2 seconds before opening the sidebar and launching the tour
		PlaygroundPageUtils.openSideBar(page);
		PlaygroundPageUtils.launchTour(page);
		int totalStep = PlaygroundPageUtils.getTotalSteps(page);
		Assertions.assertTrue(totalStep > 0, "Total steps in the tour should be greater than 0.");
		PlaygroundPageUtils.skipTourButton(page);
    }

}