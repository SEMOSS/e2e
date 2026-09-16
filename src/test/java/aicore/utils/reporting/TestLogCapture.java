package aicore.utils.reporting;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.LogManager;


public final class TestLogCapture {

	private static final String APPENDER_NAME = "TestLogCaptureAppender";
	private static volatile boolean registered = false;

	private static final ThreadLocal<StringBuilder> BUFFER = ThreadLocal.withInitial(StringBuilder::new);
	private static final ThreadLocal<Boolean> CAPTURING = ThreadLocal.withInitial(() -> false);

	private TestLogCapture() {
	}

	public static synchronized void ensureRegistered() {
		if (registered) {
			return;
		}
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		Layout<String> layout = PatternLayout.newBuilder().withPattern("%d{HH:mm:ss.SSS} %-5level %logger{1} - %msg%n")
				.withConfiguration(config).build();
		CapturingAppender appender = new CapturingAppender(APPENDER_NAME, null, layout);
		appender.start();
		config.addAppender(appender);
		config.getRootLogger().addAppender(appender, null, null);
		ctx.updateLoggers();
		registered = true;
	}

	public static void start() {
		ensureRegistered();
		BUFFER.get().setLength(0);
		CAPTURING.set(true);
	}

	public static String stop() {
		CAPTURING.set(false);
		String content = BUFFER.get().toString();
		BUFFER.get().setLength(0);
		return content;
	}

	private static final class CapturingAppender extends AbstractAppender {
		protected CapturingAppender(String name, Filter filter, Layout<String> layout) {
			super(name, filter, layout, true, null);
		}

		@Override
		public void append(LogEvent event) {
			if (Boolean.TRUE.equals(CAPTURING.get())) {
				BUFFER.get().append(new String(getLayout().toByteArray(event)));
			}
		}
	}
}
