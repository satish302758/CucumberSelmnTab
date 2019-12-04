package com.Tab.Utilities;

import com.Tab.Driver.DriverFactory;

public class ConfigFactory
{
	private static String configPropertyFilePath;
	private static String reportDirectory;
	private static boolean isExtentSummaryReport;
	private static boolean isSeleniumEventlog;
	private static boolean isEmailReport;
	private static boolean isScreenShot;
	public static String getReportDirectory()
	{
		return reportDirectory;
	}
	public static void setReportDirectory(String reportDirectory)
	{
		ConfigFactory.reportDirectory = reportDirectory;
	}
	public static void setConfigPropertyFilePath(String configPropertyFilePath)
	{
		ConfigFactory.configPropertyFilePath = configPropertyFilePath;
	}
	public static String getConfigPropertyFilePath()
	{
		return configPropertyFilePath;
	}
	public static void setIsExtentSummaryReport(String isExtentSummaryReport)
	{
		
		ConfigFactory.isExtentSummaryReport = Boolean.parseBoolean(isExtentSummaryReport);
	}
	public static boolean getIsExtentSummaryReport()
	{
		return isExtentSummaryReport;
	}
	public static void setIsSeleniumEventlog(String isSeleniumEventlog)
	{
		ConfigFactory.isSeleniumEventlog = Boolean.parseBoolean(isSeleniumEventlog);
	}
	public static boolean getIsSeleniumEventlog( )
	{
		return ConfigFactory.isSeleniumEventlog;
	}
	public static void setIsEmailReport(String isEmailReport)
	{
		ConfigFactory.isEmailReport = Boolean.parseBoolean(isEmailReport);
	}
	public static boolean getIsEmailReport( )
	{
		return ConfigFactory.isEmailReport;
	}
	public static void setIsScreenShot(String isScreenShot)
	{
		ConfigFactory.isScreenShot = Boolean.parseBoolean(isScreenShot);
	}
	public static boolean getIsScreenShot( )
	{
		return ConfigFactory.isScreenShot;
	}
	
}
