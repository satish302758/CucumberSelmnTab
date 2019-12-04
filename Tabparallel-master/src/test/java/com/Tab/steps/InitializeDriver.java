package com.Tab.steps;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.Tab.Driver.DriverFactory;
import com.Tab.Driver.DriverManager;
import com.Tab.Driver.OptionsManager;
import com.Tab.Driver.WebDriverEvents;
import com.Tab.Utilities.BROWSER;
import com.Tab.Utilities.ConfigFactory;
import com.Tab.Utilities.GridStart;

public class InitializeDriver
{
	private WebDriver driver;
	private EventFiringWebDriver eventdriver;
	private String defaultUserName;
	private String defaultPassword;
	protected void openBrowser(BROWSER browser) throws Exception
	{
		if (DriverFactory.getIsRemoteRun())
		{
			try
			{
				System.out.println("Launching : " + browser);
				switch (browser)
				{
				case FIREFOX:
					driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()),
							OptionsManager.getRemoteFirefoxOptions());
					break;
				case CHROME:
					driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()),
							OptionsManager.getRemoteChromeOptions());
					break;
				case IE:
					driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()),
							OptionsManager.getRemoteInternetExplorer11Options());
					break;
				default:
					throw new Exception("Browser Not Defined");
				}
			}
			catch (MalformedURLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			System.out.println("Launching : " + browser);
			switch (browser)
			{
			case CHROME:
				System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
				driver = new ChromeDriver(OptionsManager.getChromeOptions());
				break;
			case FIREFOX:
				// to write low level warning logs to temp
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
						System.getProperty("java.io.tmpdir") + "geckodriverlogs.txt");
				System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
				driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
				break;
			case IE:
				System.setProperty("webdriver.ie.driver", DriverFactory.getIeDriverExePath());
				driver = new InternetExplorerDriver(OptionsManager.getInternetExplorer11Options());
			}
		}
		eventdriver = new EventFiringWebDriver(driver);
		WebDriverEvents event = new WebDriverEvents();
		if (ConfigFactory.getIsSeleniumEventlog())
		{
			eventdriver.register(event);
		}
		DriverManager.setWebDriver(eventdriver);
		System.out.println("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
	}
	protected void quit()
	{
		DriverManager.getDriver().quit();
		System.out.println("Test Execution Completed !!!");
	}
}