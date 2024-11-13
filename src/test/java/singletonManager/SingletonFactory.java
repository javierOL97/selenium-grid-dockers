package singletonManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SingletonFactory {

	private static Map<String, Object> objectFactory = new HashMap<String, Object>();

	// Private constructor to prevent the class is instantiated outside this class
	private SingletonFactory() {
	}

	/**
	 * Creates a single instance of the specified class. If the class instance
	 * already exists it just return the previously created instance.
	 * 
	 * 
	 * @param c the class to be instantiated
	 * @return an unique instance of the class
	 * @throws RuntimeException if an error occurs during the object creation
	 */
	public static <T> T getSingletonInstance(Class<T> c) {
		synchronized (c) {
			String key = c.toString();
			Object instance = objectFactory.get(key);
			if (instance == null) {
				try {
					// Get the constructor from the class
					Constructor<T> constructor = c.getDeclaredConstructor();
					// Makes the constructor accessible if it is private.
					constructor.setAccessible(true);

					instance = constructor.newInstance();
					objectFactory.put(key, instance);
				} catch (IllegalAccessException | InstantiationException | InvocationTargetException
						| NoSuchMethodException e) {
					throw new RuntimeException("Exception while creating singleton instance for class : " + key, e);
				}
			}
			return c.cast(instance);
		}
	}

	public static void cleanObjectFactory() {
		objectFactory.clear();
	}

}
