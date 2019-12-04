package com.Tab.ReportingListeners;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.Tab.Driver.DriverManager;
import com.Tab.Utilities.ConfigFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import cucumber.api.Scenario;

public class ExtentTestManager
{
	private static ThreadLocal<ExtentTest> threadSafeTest = new ThreadLocal<ExtentTest>();
	 
	public static synchronized ExtentTest getTest()
	{
		return threadSafeTest.get();
	}
	public static synchronized ExtentTest setTest(String testName, String scenarioName, ExtentReports report,Scenario scenario)
	{ 
		Collection<String> tagsList= scenario.getSourceTagNames();
		
		System.out.println(tagsList);
		ExtentTest test = report.createTest(testName, scenarioName);
		threadSafeTest.set(test);
		return test;
		
	}
	public static void logInfo(String message) throws IOException
	{
		Markup m = MarkupHelper.createLabel(message, ExtentColor.BLUE);
		threadSafeTest.get().log(Status.INFO, m);
	
	}
	public static void logScenarioAsPass(String scenarioName) throws IOException
	{
		Markup m = MarkupHelper.createLabel(scenarioName + " Passed", ExtentColor.GREEN);
		threadSafeTest.get().log(Status.INFO, m);
	}
	public static void logScenarioAsFail(String scenarioName)
	{
		try
		{
			
			String bas64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
			threadSafeTest.get().log(Status.INFO, "Test case failed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(bas64).build());
			Markup m = MarkupHelper.createLabel(scenarioName + " Failed", ExtentColor.RED);
			threadSafeTest.get().log(Status.FAIL, m);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public synchronized static void logPassStep(String details) throws IOException
	{
		try
		{
			if(ConfigFactory.getIsScreenShot())
			{
			String bas64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
			threadSafeTest.get().log(Status.PASS, details,
					MediaEntityBuilder.createScreenCaptureFromBase64String(bas64).build());
			}
			else
			{
				threadSafeTest.get().log(Status.PASS, details);	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public synchronized static void logFailStep(String details, Exception exp)
	{
		try
		{
			if(ConfigFactory.getIsScreenShot())
			{
			String bas64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
			threadSafeTest.get().log(Status.FAIL, details + exp.getClass().getCanonicalName(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(bas64).build());
			}
			else
			{
				threadSafeTest.get().log(Status.FAIL, details + exp.getClass().getCanonicalName());	
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}