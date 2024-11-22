package singletonManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SingletonFactory {

	//We create a ThreadLocal that contains a HashMap for each thread when we run our tests in paralel
	private static ThreadLocal<Map<String, Object>> objectFactory =  ThreadLocal.withInitial(HashMap::new);

	// Private constructor to prevent the class is instantiated outside this class
	private SingletonFactory() {
	}

	
	public static <T> T getSingletonInstance(Class<T> c) {
		String key = c.toString();
		Object instance = objectFactory.get().get(key);
		if (instance == null) {
			try {
				// Get the constructor from the class
				Constructor<T> constructor = c.getDeclaredConstructor();
				// Makes the constructor accessible if it is private.
				constructor.setAccessible(true);

				instance = constructor.newInstance();
				objectFactory.get().put(key, instance);
			} catch (IllegalAccessException | InstantiationException | InvocationTargetException
					| NoSuchMethodException e) {
				throw new RuntimeException("Exception while creating singleton instance for class : " + key, e);
			}
		}
		return c.cast(instance);
	}

	public static void cleanObjectFactory() {
		objectFactory.get().clear();
	}

}
