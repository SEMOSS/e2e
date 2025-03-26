package e2e;

public class E2ETests {
//
//	private static final Logger logger = LogManager.getLogger(E2ETests.class);
//
//	private static boolean pingSuccessful = true;
//
//	// Shared between all tests in this class.
//	protected static Playwright playwright;
//	protected static Browser browser;
//
//	// New instance for each test method.
//	protected BrowserContext context;
//	protected Page page;
//
//	// URL prefix
//	private static String urlPrefix = null;
//	private static String api = null;
//
//	// setup specific
//	private static boolean initialize = true;
//	private static boolean headless = false;
//	private static double slowmo = 0.0;
//	private static double timeout = 5000.0;
//	private static LaunchOptions lo = null;
//
//	// for debugging. don't have logging yet :/
//	private static String apiStringEndpoint = null;
//
//	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//	private static String folderDateTime = LocalDateTime.now().format(dtf);
//
//	//@BeforeAll
//	static void launchBrowser() throws Exception {
//		logger.info("Before all start");
//		if (initialize) {
//			logger.info("--------------- INTIALIZING ---------------");
//			logger.info("Log check");
//			logger.info("INFO");
//			logger.debug("DEBUG");
//			logger.warn("WARN");
//			logger.error("ERROR");
//			logger.fatal("FATAL");
//			logger.info("Log check end");
//			
//			loadTestProps();
//			lo = new LaunchOptions();
//			lo.setHeadless(headless);
//			lo.setSlowMo(slowmo);
//
//			pingSuccessful = pingServer();
//
//			Path p = Paths.get("videos");
//			logger.info("Videos will be saved to: {}", p.toString());
//			if (Files.isDirectory(p)) {
//				logger.info("Cleaning directory: {}", p.toString());
//				FileUtils.cleanDirectory(p.toFile());
//			}
//			
//			Path trace = Paths.get("traces");
//			logger.info("Traces will be saved to: {}", trace);
//			if (Files.isDirectory(trace)) {
//				logger.info("Cleaning directory: {}", trace.toString());
//				FileUtils.cleanDirectory(trace.toFile());
//			}
//
//			initialize = false;
//			logger.info("------------------ FINISHED INTIALIZING --------------------");
//		}
//
//		if (!pingSuccessful) {
//			//fail("Cannot run tests since we cannot reach server at " + apiStringEndpoint);
//		}
//
//		playwright = Playwright.create();
//
//		browser = playwright.chromium().launch(lo);
//		logger.info("Before all end");
//	}
//
//	private static boolean pingServer() throws InterruptedException {
//		int i = 0;
//		boolean successful = false;
//		apiStringEndpoint = getApi("/api/config");
//		logger.info("attempting to ping: {}", apiStringEndpoint);
//		while (i < 10) {
//			try {
//				HttpURLConnection conn = (HttpURLConnection) new URL(apiStringEndpoint).openConnection();
//				conn.setRequestMethod("GET");
//				conn.setConnectTimeout(1000);
//				int code = conn.getResponseCode();
//				if (code == 200) {
//					logger.info("Successful ping");
//					successful = true;
//					break;
//				}
//			} catch (Exception e) {
//				logger.error("Could not ping api", e);
//			}
//			logger.info("Sleeping for 500ms then pinging again.");
//
//			i++;
//			Thread.sleep(500);
//		}
//		return successful;
//	}
//
//	//@AfterAll
//	static void closeBrowser() {
//		logger.info("AFTER ALL start");
//		playwright.close();
//		logger.info("AFTER ALL end\n");
//	}
//
//	//@BeforeEach
//	void createContextAndPage() {
//		logger.info("Start Test for: {}", ti);
//		logger.info("Before each start");
//		String className = ti.getTestClass().get().getSimpleName();
//		Path path = Paths.get("videos", folderDateTime, className);
//		logger.info("Video saved at: {}", path.toString());
//		
//		// context options
//		NewContextOptions co = new Browser.NewContextOptions();
//		co.setRecordVideoDir(path);
//		co.setRecordVideoSize(1920, 1080);
//		co.setViewportSize(1920, 1080);
//		co.setStorageStatePath(Paths.get("state.json"));
//		
//		context = browser.newContext(co);
//		
//		// tracing
//		StartOptions so = new Tracing.StartOptions();
//		so.setScreenshots(true);
//		so.setSnapshots(true);
//		so.setSources(true);
//		context.tracing().start(so);
//
//		context.setDefaultTimeout(timeout);
//		page = context.newPage();
//
//		// request handling
//		page.onRequest(HttpLogger::logRequest);
//		
//		// response handling
//		page.onResponse(HttpLogger::logResponse);
//
//		if (!Utility.getRegistered()) {
//			logger.info("Not registered, starting registration.");
//			//nativeRegister();
//		}
//		
//		logger.info("Before each end");
//	}
//
//	//@AfterEach
//	void closeContext(TestInfo ti) throws IOException {
//		logger.info("After each start");
//		// save video with easy to understand name
//		String methodName = ti.getDisplayName().substring(0, ti.getDisplayName().length() - 2);
//		String videoName = methodName + ".webm";
//		String className = ti.getTestClass().get().getSimpleName();
//		Path path = Paths.get("videos", folderDateTime, className, videoName);
//		Path p = page.video().path();
//		
//		StopOptions so = new Tracing.StopOptions();
//		String traceName = methodName + ".zip";
//		so.setPath(Paths.get("traces", className, traceName));
//		context.tracing().stop(so);
//		context.close();
//		Files.move(p, path);
//		logger.info("Moved video to: {}", path.toString());
//		
//		
//		logger.info("After each end");
//		logger.info("End Test for: {}", ti);
//		logger.info("---------------------------------------------------\n\n");
//	}
//
//	static Properties loadTestProps() throws IOException {
//		Properties props = new Properties();
//		logger.info("loading test props from file");
//
//		try (InputStream is = Files.newInputStream(Paths.get("test.props"))) {
//			props.load(is);
//			urlPrefix = props.get("URL_PREFIX").toString();
//			headless = Boolean.valueOf(props.get("HEADLESS").toString());
//			slowmo = Double.valueOf(props.get("SLOWMO").toString());
//			timeout = Double.valueOf(props.get("TIMEOUT").toString());
//			api = String.valueOf(props.get("API")).toString();
//			logger.info("Props: {}", props);
//		}
//
//		return props;
//	}
//
//	public static String getUrlPrefix() {
//		return urlPrefix;
//	}
//	
//	public static String getApiPrefix() {
//		return api;
//	}
//
//	protected String getUrl(String ending) {
//		return urlPrefix + ending;
//	}
//
//	protected static String getApi(String ending) {
//		return api + ending;
//	}
//
//	private void nativeRegister() {
//		page.navigate(getApi("/setAdmin/"));
//		logger.info("Page is: {}", page.url());
//		assertEquals(getApi("/setAdmin/"), page.url());
//		page.locator("#user-id").click();
//		page.locator("#user-id").fill("user1");
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
//		logger.info("After submitting admin: {}", page.url());
//
//		page.navigate(getUrl("/packages/client/dist/#/login"));
//		page.waitForURL(getUrl("/packages/client/dist/#/login"));
//		page.getByText("Log in below").click();
//		assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("Log in below");
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register Now")).click();
//		page.getByText("Register below").click();
//		assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("Register below");
//
//		List<Locator> inputs = page.locator("input[type='text']").all();
//		List<Locator> visible = new ArrayList<>();
//		for (Locator l : inputs) {
//			if (l.isVisible()) {
//				visible.add(l);
//			}
//		}
//
//		visible.get(0).click();
//		visible.get(0).fill("user");
//
//		visible.get(1).click();
//		visible.get(1).fill("one");
//
//		visible.get(2).click();
//		visible.get(2).fill("user1");
//
//		visible.get(3).click();
//		visible.get(3).fill("user1@deloitte.com");
//
//		List<Locator> passwords = page.locator("input[type='password']").all();
//		List<Locator> visiblePasswords = new ArrayList<>();
//		for (Locator l : passwords) {
//			if (l.isVisible()) {
//				visiblePasswords.add(l);
//			}
//		}
//		visiblePasswords.get(0).click();
//		visiblePasswords.get(0).fill("TestTest8*");
//
//		visiblePasswords.get(1).click();
//		visiblePasswords.get(1).fill("TestTest8*");
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register Account")).click();
//
//		page.waitForLoadState(LoadState.LOAD);
//		page.getByRole(AriaRole.ALERT).click();
//		assertThat(page.getByRole(AriaRole.ALERT)).containsText("Account registration successful. Log in below.");
//		logger.info("Account registration Done");
////		doLogin();
////		doLogout();
//	}
//
//	private void doLogin() {
//		page.navigate(getUrl("/packages/client/dist/#/login"));
//		page.getByLabel("Username").click();
//		page.getByLabel("Username").fill("user1");
//		page.getByLabel("Username").press("Tab");
//		page.locator("input[type=\"password\"]").fill("TestTest8*");
//		Response response = page.waitForResponse(getApi("/api/auth/login"), () -> page
//				.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login with native")).click());
//		assertEquals(200, response.status());
//		page.waitForLoadState(LoadState.NETWORKIDLE);
//		page.waitForLoadState(LoadState.LOAD);
//		page.navigate(getUrl("/packages/client/dist/#"));
//		page.waitForLoadState(LoadState.NETWORKIDLE);
//		page.waitForLoadState(LoadState.LOAD);
//		page.waitForURL(getUrl("/packages/client/dist/#"));
//	}
//
//	private void doLogout() {
//		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^SEMOSS$")))
//				.getByRole(AriaRole.BUTTON).click();
//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
//
//		page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome!")).click();
//		assertEquals(getUrl("/packages/client/dist/#/login"), page.url());
//	}

}
