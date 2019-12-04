package com.Tab.ReportingListeners;

import java.lang.reflect.Array;
import java.util.Collection;

import com.Tab.Utilities.ConfigFactory;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import cucumber.api.Scenario;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

public class SummaryReport
{
	private static ExtentReports report;
	public static void setupSummaryReporter()
	{
		report = new ExtentReports();
		String reportDirectory = ConfigFactory.getReportDirectory();
		String fileName = "summary.html";
		String completeFilePath = reportDirectory + "\\" + fileName;
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(completeFilePath);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setReportName(fileName);
		report.setSystemInfo("Automation Tester", System.getProperty("user.name"));
		report.setSystemInfo("Organization", "TAB Bank");
		report.attachReporter(htmlReporter);
	report.setAnalysisStrategy(AnalysisStrategy.TEST);  
	}
	public static void logFailTest(Scenario scenario)
	{
		ExtentTest test =report.createTest(scenario.getName()).log(Status.FAIL,
				MarkupHelper.createLabel("Scenario " + scenario.getName() + " Failed", ExtentColor.RED));
		 String featureName = "";
		    String rawFeatureName = scenario.getId().split(";")[0].replace("-"," ");
		    featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
	 Object[] tags= scenario.getSourceTagNames().toArray();
		for(int i=0;i<scenario.getSourceTagNames().size();i++)
		{
			test.assignCategory(tags[i].toString());
		}
	}
	public static void logPassTest(Scenario scenario)
	{
		ExtentTest test =	report.createTest(scenario.getName()).log(Status.PASS,
				MarkupHelper.createLabel("Scenario " + scenario.getName() + " Passed", ExtentColor.GREEN));
		
		 String featureName = "";
		    String[] rawFeatureName = scenario.getId().replace("/", " ").replace(".", " ").split(" ");
		    featureName =  rawFeatureName[rawFeatureName.length-2];
		    test.assignAuthor(featureName);
		 Object[] tags= scenario.getSourceTagNames().toArray();
			for(int i=0;i<scenario.getSourceTagNames().size();i++)
			{
				test.assignCategory(tags[i].toString());
			}
	}
	public static void flushSummary()
	{
		report.flush();
	}
}
