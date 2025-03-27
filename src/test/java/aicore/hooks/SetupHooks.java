package aicore.hooks;

import aicore.base.GenericSetupUtils;
import aicore.base.RunInfo;
import aicore.utils.ConfigUtils;
import aicore.utils.UrlUtils;
import com.microsoft.playwright.*;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SetupHooks {

    private static final Logger logger = LogManager.getLogger(SetupHooks.class);

    private static Page page;
    private static BrowserContext context;
    private static Browser browser;
    private static Playwright playwright;

    private static int step = 0;

    @BeforeAll
    public static void beforeAll() throws IOException {
        //logger.info("BEFORE ALL: {}", scenario.getName());
        GenericSetupUtils.initialize();

        playwright = Playwright.create();
        browser = playwright.chromium().launch(GenericSetupUtils.getLaunchOptions());
    }

    @Before
    public void before(Scenario scenario) {
        logger.info("BEFORE: {}", scenario.getName());
        step = 0;

        Browser.NewContextOptions newContextOptions = GenericSetupUtils.getContextOptions();
        context = browser.newContext(newContextOptions);

        Tracing.StartOptions startOptions = GenericSetupUtils.getStartOptions();
        context.tracing().start(startOptions);

        page = context.newPage();
        page.setDefaultTimeout(Double.parseDouble(ConfigUtils.getValue("timeout")));

        GenericSetupUtils.setupLoggers(page);

        if (GenericSetupUtils.useDocker() && RunInfo.isNeedToCreateUser()) {
            GenericSetupUtils.createUsers(page);
        }

        logger.info("BEFORE - logging in and starting test: {}", scenario.getName());
        String sourceTagName = scenario.getSourceTagNames().stream().findFirst().orElse("");

        // Use switch to handle different sourceTagNames
        switch (sourceTagName) {
            case "@MSUser":
            	page.navigate("https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login");
            	page.waitForTimeout(1000);
            	page.locator("//div[@class='MuiStack-root css-bcmwpg']//button").click();
            	Page page1 = page.waitForPopup(() -> {
					page.locator("//span[(text()='Deloitte Login')]").click();
				});
            	
            	page1.fill("//input[@type='email']", ConfigUtils.getValue("ms_username"));
            	page1.click("#idSIButton9");
            	page1.fill("//input[@type='password']", ConfigUtils.getValue("ms_password"));
            	page1.click("//input[@data-report-event='Signin_Submit']");
                break;

            case "@NativeAdmin":
                // Native User Flow
                String localAdminUser = ConfigUtils.getValue("admin_username");
                String localAdminPassword = ConfigUtils.getValue("admin_password");
                GenericSetupUtils.login(page, localAdminUser, localAdminPassword);
                break;

            case "@NativeAuthor":
            	 String nativeUser = ConfigUtils.getValue("native_username");
                 String nativePassword = ConfigUtils.getValue("native_password");
                 GenericSetupUtils.login(page, nativeUser, nativePassword);
                break;

            default:
            	 String adminUser = ConfigUtils.getValue("native_username");
               String adminPassword = ConfigUtils.getValue("native_password");
               GenericSetupUtils.login(page, adminUser, adminPassword);
                break;
        }
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        logger.info("BEFORE STEP: {}, {}", scenario.getName(), step++);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        logger.info("STEP {}", scenario.isFailed() ? "FAILED" : "PASSED");
    }

    @After
    public void after(Scenario scenario) throws IOException {
        page.navigate(UrlUtils.getUrl("#/"));
        GenericSetupUtils.logout(page);
        logger.info("AFTER: {}", scenario.getName());
        String scenarioName = scenario.getName();

        if (GenericSetupUtils.useTrace()) {
            Tracing.StopOptions so = new Tracing.StopOptions();
            String filename = scenarioName + ".zip";
            Path dir = Paths.get("traces", "features");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Path traceFile = Paths.get("traces", "features", filename);
            so.setPath(traceFile);
            page.context().tracing().stop(so);
        }

        context.close();

        if (GenericSetupUtils.useVideo()) {
            Path og = page.video().path();
            Path newPath = Paths.get("videos", "features", scenarioName + ".webm");
            Path newDir = Paths.get("videos", "features");
            if (!Files.exists(newDir)) {
                Files.createDirectories(newDir);
            }
            Files.move(og, newPath);
        }

        page.close();
    }

    @AfterAll
    public static void afterAll() {
        logger.info("AFTER ALL");
        playwright.close();
    }


    public static Page getPage() {
        return page;
    }
}
