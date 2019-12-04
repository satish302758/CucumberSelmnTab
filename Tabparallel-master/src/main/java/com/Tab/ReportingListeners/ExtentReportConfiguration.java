package com.Tab.ReportingListeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import com.Tab.Utilities.ConfigFactory;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportConfiguration
{
	public static String createDirectory()
	{
		Date d = new Date();
		String reportDirectory = null;
		try
		{
			reportDirectory = System.getProperty("user.dir") + "\\reports\\TestRun "	+ d.toString().replace(":", "_").replace(" ", "_");
			Files.createDirectories(Paths.get(reportDirectory));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportDirectory;
	}
	private static ThreadLocal<ExtentReports> report = new ThreadLocal<ExtentReports>();
	public static synchronized void setReporter(String scenarioName)
	{
		int i = 1;
		Date d = new Date();
		String reportDirectory = ConfigFactory.getReportDirectory();
		String fileName = scenarioName + " " + d.toString().replace(":", "_").replace(" ", "_") + d.getTime() + ".html";
		ExtentReports extent = new ExtentReports();
		String completeFilePath = reportDirectory + "\\" + fileName;
		File file = new File(completeFilePath);
		while (file.exists())
		{
			completeFilePath = completeFilePath + "_" + i;
			i++;
		}
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(completeFilePath);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setReportName(fileName);
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", System.getProperty("user.name"));
		extent.setSystemInfo("Organization", "TAB Bank");
		extent.setSystemInfo("Build no", "");
		extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
		
		report.set(extent);
	}
	public static synchronized ExtentReports getReporter()
	{
		return report.get();
	}
	
}