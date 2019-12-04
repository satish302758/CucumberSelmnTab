package com.Tab.TestNGListeners;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Tab.Driver.DriverFactory;
import com.Tab.Driver.EventLog;
import com.Tab.ReportingListeners.ExtentReportConfiguration;
import com.Tab.ReportingListeners.Featurereport;
import com.Tab.ReportingListeners.SummaryReport;
import com.Tab.Utilities.ConfigFactory;
import com.Tab.Utilities.EmailReporter;
import com.Tab.Utilities.GridStart;

public class SetupListener implements ITestListener
{
	private FileInputStream fis;
	private Properties config = new Properties();
	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public synchronized void onStart(ITestContext context)
	{
		System.out.print("In on Start");
		displayInfoMessage("Tests Execution Started ");
		///////// LOG4J
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties"
				+ File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		log.info("config loaded");
		//// Setup Event Logger
		EventLog.setupLogger();
		///////////////
		ConfigFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//config.properties");
		try
		{
			fis = new FileInputStream(ConfigFactory.getConfigPropertyFilePath());
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		try
		{
			config.load(fis);
			log.info("config properties file loaded");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		try
		{
			DriverFactory.setGridPath("http://" + InetAddress.getLocalHost().getHostAddress() + ":2323/wd/hub");
			log.info("Grid path loaded");
		}
		catch (UnknownHostException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DriverFactory.setIsRemoteRun(Boolean.parseBoolean(config.getProperty("isRemoteRun")));
		DriverFactory.setFirefoxBinaryPath(config.getProperty("firefoxBinaryPath"));
		log.info("FireFox Binary Loaded loaded");
		DriverFactory.setChromeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
		log.info("Chrome Driver file loaded");
		DriverFactory.setGeckoDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		log.info("FireFox Driver file loaded");
		DriverFactory.setIeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
		log.info("Internet Explorer Driver file loaded");
		if (DriverFactory.getIsRemoteRun())
		{
			try
			{
				GridStart.startGrid();
			}
			catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Create Report Directory
		ConfigFactory.setReportDirectory(ExtentReportConfiguration.createDirectory());
		ConfigFactory.setIsExtentSummaryReport(config.getProperty("isExtentSummaryReport"));
		ConfigFactory.setIsScreenShot(config.getProperty("isScreenShot"));
		if (ConfigFactory.getIsExtentSummaryReport())
		{
			SummaryReport.setupSummaryReporter();
		}
		ConfigFactory.setIsEmailReport(config.getProperty("isEmailReport"));
	}
	@Override
	public synchronized void onFinish(ITestContext context)
	{
		System.out.print("on finsish");
		log.info("Summary TESTNG Report Mailed");
		if (ConfigFactory.getIsExtentSummaryReport())
		{
			 
		}
		displayInfoMessage("Feature Execution Finished");
		Featurereport.GenerateSummary();
		if (ConfigFactory.getIsEmailReport())
		{
			EmailReporter.mailTestNGReport();
		}
	}
	@Override
	public synchronized void onTestStart(ITestResult result)
	{
	}
	@Override
	public synchronized void onTestSuccess(ITestResult result)
	{
	}
	@Override
	public synchronized void onTestFailure(ITestResult result)
	{
	}
	@Override
	public synchronized void onTestSkipped(ITestResult result)
	{
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
	}
	private void displayInfoMessage(String message)
	{
		SystemTray sys = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().getImage("images/tray.gif");
		TrayIcon tray = new TrayIcon(image, "hool");
		tray.setImageAutoSize(true);
		try
		{
			sys.add(tray);
		}
		catch (AWTException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tray.displayMessage(message, null, MessageType.INFO);
	}
}