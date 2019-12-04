package com.Tab.Driver;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverManager
{
	

	private static ThreadLocal<EventFiringWebDriver> threadSafeDriver = new ThreadLocal<EventFiringWebDriver>();

	public static EventFiringWebDriver getDriver()
	{
		return threadSafeDriver.get();
	}

	public static void setWebDriver(EventFiringWebDriver driver)
	{
		threadSafeDriver.set(driver);
		
	}
}