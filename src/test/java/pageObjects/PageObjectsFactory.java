package pageObjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import utils.GenericActions;

public class PageObjectsFactory implements IPageObject {

	private Map<String, Object> objectFactory = new HashMap<String, Object>();

	private WebDriver driver;
	private GenericActions genericActions;

	// Private constructor to prevent the class is instantiated outside this class
	private PageObjectsFactory() {
	}

	/**
	 * Creates a single instance of the specified page class. If the page class
	 * instance already exists it just return the previously created instance.
	 * 
	 * 
	 * @param c the page class to be instantiated
	 * @return an unique instance of the class
	 * @throws RuntimeException if an error occurs during the object creation
	 */
	@Override
	public <T> T getPage(Class<T> c) {
		synchronized (c) {
			String key = c.toString();
			Object instance = objectFactory.get(key);
			if (instance == null) {
				try {
					Constructor<T> constructor = c.getDeclaredConstructor(WebDriver.class, GenericActions.class);
					// Makes the constructor accessible if it is private.
					instance = constructor.newInstance(driver, genericActions);
					objectFactory.put(key, instance);
				} catch (IllegalAccessException | InstantiationException | InvocationTargetException
						| NoSuchMethodException e) {
					throw new RuntimeException("Exception while creating singleton instance for class : " + key, e);
				}
			}
			return c.cast(instance);
		}
	}

	public GenericActions getGenericActions() {
		return genericActions;
	}

	// This method sets our webdriver required in PageObjectManager Singleton
	public void setDriver(WebDriver driver) {
		if (this.driver == null) {
			this.driver = driver;
			genericActions = new GenericActions(driver);
		}
	}
}
