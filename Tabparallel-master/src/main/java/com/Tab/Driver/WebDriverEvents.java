package com.Tab.Driver;

import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebDriverEvents implements WebDriverEventListener
{

	private Logger log = EventLog.getLogInstance();

	public void beforeAlertAccept(WebDriver driver)
	{
		log.info("Before Alert Accept");
	}

	public void afterAlertAccept(WebDriver driver)
	{
		log.info("After Alert Accept");
	}

	public void afterAlertDismiss(WebDriver driver)
	{
		log.info("After Alert Dismiss");
	}

	public void beforeAlertDismiss(WebDriver driver)
	{
		log.info("Before Alert Accept");
	}

	public void beforeNavigateTo(String url, WebDriver driver)
	{
		try
		{
			log.info("Before Navigate to :" + url);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void afterNavigateTo(String url, WebDriver driver)
	{

		log.info("After Navigate to :" + url + "in Browser: ");
	}

	public void beforeNavigateBack(WebDriver driver)
	{
		log.info("Before Navigate Back");
	}

	public void afterNavigateBack(WebDriver driver)
	{
		log.info("After Navigate Back");
	}

	public void beforeNavigateForward(WebDriver driver)
	{
		log.info("Before Navigate Forward");
	}

	public void afterNavigateForward(WebDriver driver)
	{
		log.info("After Navigate Forward");
	}

	public void beforeNavigateRefresh(WebDriver driver)
	{
		log.info("Before Navigate Refresh");
	}

	public void afterNavigateRefresh(WebDriver driver)
	{
		log.info("Before Navigate Refresh");
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver)
	{
		log.info("Before FindBy");
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver)
	{
		log.info("Before FindBy " + by.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver)
	{
		log.info("Before Click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver)
	{
		log.info("After Click on: " + element.toString());
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend)
	{
		log.info("Before Change value of: " + element.toString() + " value to Send: " + keysToSend[0].toString());
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend)
	{
		log.info("After Change value of: " + element.toString() + " value to Send: " + keysToSend[0].toString());
	}

	public void beforeScript(String script, WebDriver driver)
	{
		log.info("Before Executing Script: " + script);
	}

	public void afterScript(String script, WebDriver driver)
	{
		log.info("After Executing Script: " + script);
	}

	public void beforeSwitchToWindow(String windowName, WebDriver driver)
	{
		log.info("Before Switch to Window: " + windowName);
	}

	public void afterSwitchToWindow(String windowName, WebDriver driver)
	{
		log.info("After Switch to Window: " + windowName);
	}

	public void onException(Throwable throwable, WebDriver driver)
	{
		log.info("Exception Encountered: " + throwable.getMessage());
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> target)
	{
		
	}

	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text)
	{
		// TODO Auto-generated method stub
		
	}
}