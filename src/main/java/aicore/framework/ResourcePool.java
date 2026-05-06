package aicore.framework;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ResourcePool {

	private static List<Resource> RESOURCES = List.of();

	private static final AtomicInteger COUNT = new AtomicInteger(0);

	private static volatile boolean initialized = false;

	private static ThreadLocal<Resource> CURRENT = ThreadLocal.withInitial(() -> {
		int index = Math.floorMod(COUNT.getAndIncrement(), RESOURCES.size());
		return RESOURCES.get(index);
	});

	private ResourcePool() {
	}

	public static void init(List<Resource> resources) {
		if (resources == null || resources.isEmpty()) {
			throw new IllegalArgumentException("resources cannot be null or empty");
		}

		RESOURCES = List.copyOf(resources);
		initialized = true;
	}

	public static Resource get() {
		if (!initialized) {
			synchronized (ResourcePool.class) {
				if (!initialized) {
					List<Resource> defaultResources = List.of(new Resource("http://localhost:9090/", 0));
					RESOURCES = List.copyOf(defaultResources);
					initialized = true;
				}
			}
		}
		return CURRENT.get();
	}

}
