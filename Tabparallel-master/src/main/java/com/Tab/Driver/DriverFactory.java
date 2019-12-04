package com.Tab.Driver;

public class DriverFactory
{
	private static String chromeDriverExePath;
	private static String firefoxBinaryPath;
	private static String geckoDriverExePath;
	private static String gridPath;
	private static String ieDriverExePath;
	private static boolean isRemoteDriver;
	public static String getChromeDriverExePath()
	{
		return chromeDriverExePath;
	}
	public static String getFirefoxBinaryPath()
	{
		return firefoxBinaryPath;
	}
	public static String getGeckoDriverExePath()
	{
		return geckoDriverExePath;
	}
	public static String getGridPath()
	{
		return gridPath;
	}
	public static String getIeDriverExePath()
	{
		return ieDriverExePath;
	}
	public static boolean getIsRemoteRun()
	{
		return isRemoteDriver;
	}
	public static void setChromeDriverExePath(String chromeDriverExePath)
	{
		DriverFactory.chromeDriverExePath = chromeDriverExePath;
	}
	public static void setFirefoxBinaryPath(String firefoxBinaryPath)
	{
		DriverFactory.firefoxBinaryPath = firefoxBinaryPath;
	}
	public static void setGeckoDriverExePath(String geckoDriverExePath)
	{
		DriverFactory.geckoDriverExePath = geckoDriverExePath;
	}
	public static void setGridPath(String gridPath)
	{
		DriverFactory.gridPath = gridPath;
	}
	public static void setIeDriverExePath(String ieDriverExePath)
	{
		DriverFactory.ieDriverExePath = ieDriverExePath;
	}
	public static void setIsRemoteRun(boolean isRemote)
	{
		DriverFactory.isRemoteDriver = isRemote;
	}
}