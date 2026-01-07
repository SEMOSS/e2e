package aicore.framework;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ResourcePool {
    private static List<Resource> RESOURCES = List.of();
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    private static final ThreadLocal<Resource> CURRENT = new ThreadLocal<>();

    private ResourcePool() {}

    public static void init(List<Resource> resources) {
        if (resources == null || resources.isEmpty()) {
            throw new IllegalArgumentException("resources cannot be null or empty");
        }
        RESOURCES = List.copyOf(resources);
    }

    public static Resource get() {
        Resource r = CURRENT.get();
        if (r == null) {
            int index = Math.floorMod(COUNT.getAndIncrement(), RESOURCES.size());
            r = RESOURCES.get(index);
            CURRENT.set(r);
        }
        return r;
    }

    /**
     * Return an unmodifiable view of the resources managed by the pool.
     */
    public static List<Resource> getAllResources() {
        return RESOURCES;
    }

    /**
     * Close contexts, browsers and Playwright instances for all resources.
     * Safe to call multiple times.
     */
    public static void closeAllResources() {
        for (Resource r : RESOURCES) {
            if (r == null) continue;
            try {
                r.closeContext();
            } catch (Exception ignore) {}
        }
        for (Resource r : RESOURCES) {
            if (r == null) continue;
            try {
                r.closeBrowserAndPlaywright();
            } catch (Exception ignore) {}
        }
    }
}
