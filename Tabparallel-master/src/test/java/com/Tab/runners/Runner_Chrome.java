package com.Tab.runners;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Tab.ReportingListeners.ExtentReportConfiguration;
import com.Tab.ReportingListeners.ExtentTestManager;
import com.Tab.ReportingListeners.SummaryReport;
import com.Tab.Utilities.BROWSER;
import com.Tab.Utilities.ConfigFactory;
import com.Tab.Utilities.Fillio;
import com.Tab.steps.InitializeDriver;
import com.aventstack.extentreports.Status;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
features ={ "src/test/resources/features" },
glue ={ "com.Tab.steps", "com.Tab.runners" } ,
plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json"},
tags= {"@web"}

		
		)
public class Runner_Chrome extends InitializeDriver
{
	private TestNGCucumberRunner testNGCucumberRunner;
	String userDir = System.getProperty("user.dir");
	static String browserName = null;
	@BeforeClass(alwaysRun = true)
	public void setUpClass(ITestContext context)
	{
		try
		{
			Fillio.getTestDataInMap(null, null, null);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("@BeforeClass");
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		browserName=context.getCurrentXmlTest().getParameter("browser");
	}
	 
	@Before
	public synchronized void before(Scenario scenario) throws Exception
	{
		
		try
		{
			Thread.sleep(1000);
			BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
			openBrowser(browser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String scenarioName = scenario.getName().toString();
		ExtentReportConfiguration.setReporter(scenarioName);
		ExtentTestManager.setTest("Scenario :", getFeatureFileNameFromScenarioId(scenario)+" "+ scenarioName, ExtentReportConfiguration.getReporter(),scenario);
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenarioName);
	}
	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable  
	{
		System.out.println("@Test");
		testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
	}
	@DataProvider(parallel = true)
	public Object[][] scenarios()
	{
		return testNGCucumberRunner.provideScenarios();
	}
	@After
	public  synchronized void after(Scenario scenario) throws IOException
	{
		if (scenario.isFailed())
		{
			ExtentTestManager.logScenarioAsFail(scenario.getName());
			if (ConfigFactory.getIsExtentSummaryReport())
			{
			SummaryReport.logFailTest(scenario);
			}
		} else
		{
			ExtentTestManager.logScenarioAsPass(scenario.getName());
			if (ConfigFactory.getIsExtentSummaryReport())
			{
			SummaryReport.logPassTest(scenario);
			}
		}
		ExtentReportConfiguration.getReporter().flush();
		quit();
		 
	}
	@AfterClass(alwaysRun = true)
	public void tearDownClass()
	{
		System.out.println("@AfterClass");
		testNGCucumberRunner.finish();
	}
	@AfterSuite
	public void mailReport()
	{
		System.out.println("After suite");
		SummaryReport.flushSummary();
	}
	private String getFeatureFileNameFromScenarioId(Scenario scenario) {
	    String featureName = "";
	    String[] rawFeatureName = scenario.getId().replace("/", " ").replace(".", " ").split(" ");
	    featureName =  rawFeatureName[rawFeatureName.length-2];
	    return featureName;
	}
}