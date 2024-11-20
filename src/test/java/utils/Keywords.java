package utils;

public enum Keywords {
	//Config Keywords
	REMOTEURL("http://localhost:4444/wd/hub"),
	SELENIUM_GRID_ENABLED("selenium.grid.enabled"),
	URL("application.url"),
	BROWSER("browser"),
	CHROME("chrome"),
	FIREFOX("firefox"),
	//Form Keywords
	EMAIL("email"),
	PASSWORD("password"),
	FEMALE("female"),
	NAME("Name"),
	LNAME("LastName"),
	DOB("DoB"),
	GENDER("Gender"),
	LADA("669"),
	MALE("male"),
	
	SUCCESS("SUCCESS"),
	FAILED("FAILED"),
	
	;
	
	private final String value;
	
	private Keywords(final String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
