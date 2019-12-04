package com.Tab.Driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.Tab.Utilities.BROWSER;

public class OptionsManager
{
	public static ChromeOptions getChromeOptions()
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		// options.addArguments("--incognito");
		return options;
	}
	// Get Firefox Options
	public static FirefoxOptions getFirefoxOptions()
	{
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		// Accept Untrusted Certificates
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		options.setBinary(DriverFactory.getFirefoxBinaryPath());
		options.setCapability(FirefoxDriver.PROFILE, profile);
		return options;
	}
	public static InternetExplorerOptions getInternetExplorer11Options() {
		InternetExplorerOptions options= new InternetExplorerOptions();
		 
		return options;
	}
	public static ChromeOptions getRemoteChromeOptions()
	{
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
		options.setCapability(CapabilityType.BROWSER_NAME, BROWSER.CHROME);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		options.addArguments("disable-infobars");
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		// options.addArguments("--incognito");
		return options;
	}
	// Get Firefox Options
	public static FirefoxOptions getRemoteFirefoxOptions()
	{
		FirefoxOptions options = new FirefoxOptions();
		// options.setBinary(DriverFactory.getFirefoxBinaryPath());
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
		options.setCapability(CapabilityType.BROWSER_NAME, "FireFox");
		options.addArguments("disable-infobars");
		return options;
	}
	public static InternetExplorerOptions getRemoteInternetExplorer11Options() {
		InternetExplorerOptions options= new InternetExplorerOptions();
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
		options.setCapability("ie.forceCreateProcessApi", true);
		options.setCapability("ie.browserCommandLineSwitches", "-private");
		options.setCapability(CapabilityType.BROWSER_NAME, "IE11");
		return options;
	}
}
