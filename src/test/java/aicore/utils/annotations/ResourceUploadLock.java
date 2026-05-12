package aicore.utils.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ResourceUploadLocks.class)
public @interface ResourceUploadLock {
  String value();       // name of the resource, e.g., "FUNCTION_ZIP", "DB_ZIP", "VECTOR_ZIP"
  long timeoutMillis() default 120_000;  // optional timeout
}
