package aicore.utils.extensions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.*;

import aicore.utils.annotations.ResourceUploadLock;

import org.junit.jupiter.api.extension.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MultiResourceUploadLockExtension implements BeforeEachCallback, InvocationInterceptor, AfterEachCallback {
	private static final Logger logger = LogManager.getLogger(MultiResourceUploadLockExtension.class);

  // Map resourceName → ReentrantLock
  private static final ConcurrentMap<String, ReentrantLock> LOCKS = new ConcurrentHashMap<>();
  
  private static final String LOCKS_STORE_KEY = "LOCK_TOKENS";

  private record LockToken(String resource, boolean acquired) {}
  
  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    Method method = context.getRequiredTestMethod();
    ResourceUploadLock[] anns = method.getAnnotationsByType(ResourceUploadLock.class);
    if (anns.length == 0) return;

    List<LockToken> tokens = new ArrayList<>();
    for (ResourceUploadLock ann : anns) {
      String res = ann.value();
      long to = ann.timeoutMillis();
      ReentrantLock lock = LOCKS.computeIfAbsent(res, r -> new ReentrantLock());
      boolean ok = lock.tryLock(to, TimeUnit.MILLISECONDS);
      if (!ok) {
        // on failure, release previously acquired locks
        for (LockToken t : tokens) {
          ReentrantLock l = LOCKS.get(t.resource);
          if (t.acquired && l.isHeldByCurrentThread()) { 
        	  l.unlock();
              logger.info("Resource unlocked after timeout for {}. Context: {}",res, context.getDisplayName());
          }
        }
        throw new IllegalStateException(
          "Timeout acquiring lock '" + res + "' for " + context.getDisplayName());
      }
      logger.info("Resource locked for {}. Context: {}",res, context.getDisplayName());
      tokens.add(new LockToken(res, true));
    }
    getStore(context).put(LOCKS_STORE_KEY, tokens);
  }

  /// implementation for single resource lock
//  @Override
//  public void beforeEach(ExtensionContext context) throws Exception {
//	  ResourceUploadLock ann = findLockAnnotation(context);
//    if (ann == null) return;
//
//    String resource = ann.value();
//    long timeout = ann.timeoutMillis();
//    // get-or-create lock
//    ReentrantLock lock = LOCKS.computeIfAbsent(resource, r -> new ReentrantLock());
//    boolean acquired = lock.tryLock(timeout, TimeUnit.MILLISECONDS);
//    if (!acquired) {
//      throw new IllegalStateException(
//        "Timeout acquiring lock for resource '" + resource
//        + "' in " + context.getDisplayName());
//    }
//    logger.info("Resource locked for {}. Context: {}",resource, context.getDisplayName());
//    // store which resource we locked
//    getStore(context).put("LOCK_TOKEN", new LockToken(resource, true));
//  }

  @Override
  public void interceptTestMethod(Invocation<Void> invocation,
                                  ReflectiveInvocationContext<Method> invocationContext,
                                  ExtensionContext context) throws Throwable {
    // regardless of lock, proceed to run @BeforeEach, test, @AfterEach
    invocation.proceed();
  }
  
  @Override
  public void afterEach(ExtensionContext context) {
    @SuppressWarnings("unchecked")
    List<LockToken> tokens = getStore(context).remove(LOCKS_STORE_KEY, List.class);
    if (tokens != null) {
      // release in reverse order (optional)
      Collections.reverse(tokens);
      for (LockToken t : tokens) {
        ReentrantLock lock = LOCKS.get(t.resource);
        if (t.acquired && lock.isHeldByCurrentThread()) {
          lock.unlock();
          logger.info("Resource unlocked for {}. Context: {}",t.resource, context.getDisplayName());
        }
      }
    }
  }

//  @Override
//  public void afterEach(ExtensionContext context) {
//    LockToken token = getStore(context).remove("LOCK_TOKEN", LockToken.class);
//    if (token != null && token.acquired) {
//      ReentrantLock lock = LOCKS.get(token.resource);
//      if (lock != null && lock.isHeldByCurrentThread()) {
//        lock.unlock();
//        logger.info("Resource unlocked locked for {}. Context: {}",token.resource, context.getDisplayName());
//      }
//    }
//  }

  private ExtensionContext.Store getStore(ExtensionContext ctx) {
    // namespace per test run
    return ctx.getStore(ExtensionContext.Namespace.create(MultiResourceUploadLockExtension.class, ctx.getUniqueId()));
  }
}
