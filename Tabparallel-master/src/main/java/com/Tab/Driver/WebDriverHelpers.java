package com.Tab.Driver;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.Tab.ReportingListeners.ExtentTestManager;

public class WebDriverHelpers
{
	private Alert alert = null;
	private WebDriver driver = DriverManager.getDriver();
	public String getBrowserName()
	{
		 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    return cap.getBrowserName().toLowerCase();
	}
	private void highlightElement(WebElement element)
	{
		
			DriverManager.getDriver().executeScript("arguments[0].style.border='3px solid green';", element);
		
	}
	private void removeHighlightElement(WebElement element)
	{
		try
		{
			DriverManager.getDriver().executeScript("arguments[0].style.border='';", element);
		}
		catch (Exception e)
		{
			// ignoring as during loading elements may not be available in DOM
		}
	}
	public void ExecuteScript(String script, WebElement element)
	{
		DriverManager.getDriver().executeScript(script, element);
	}
	public void type(WebElement element, String value, String elementName)
	{
		try
		{
			highlightElement(element);
			ExecuteScript("arguments[0].value='';", element);
			element.sendKeys(value);
			//Doing Replace all as SOmtimes Keys be Sent Along in with test Data
			ExtentTestManager.logPassStep("Send Value : " + value.replaceAll("[^A-Za-z0-9]", "") + " on " + elementName);
			removeHighlightElement(element);
		}
		catch (Exception e)
		{
			ExtentTestManager.logFailStep("Send Keys on : " + elementName + " failed with ", e);
		}
	}
	public void select(WebElement element, String value, String elementName)
	{
		try
		{
			highlightElement(element);
			Select select = new Select(element);
			select.selectByValue(value);
			ExtentTestManager.logPassStep("Selecting on : " + elementName);
			removeHighlightElement(element);
		}
		catch (Exception e)
		{
			ExtentTestManager.logFailStep("Selecting on : " + elementName + " failed with ", e);
		}
	}
	public void click(WebElement element, String elementName)
	{
		try
		{
			highlightElement(element);
			element.click();
			ExtentTestManager.logPassStep("Clicking on : " + elementName);
			removeHighlightElement(element);
		}
		catch (Exception e)
		{
			if (e.toString().contains("Unable to connect to the remote server"))
			{
				element.click();
			} else
			{
				ExtentTestManager.logFailStep("Clicking on : " + elementName + " failed with: ", e);
			}
		}
	}
	public void wNclick(WebElement element, String elementName)
	{
		// Wait for Displayed and Click
		try
		{
			WaitUntilDisplayed(element, null);
			highlightElement(element);
			element.click();
			ExtentTestManager.logPassStep("Clicking on : " + elementName);
			removeHighlightElement(element);
		}
		catch (Exception e)
		{
			if (e.toString().contains("Unable to connect to the remote server"))
			{
				element.click();
			} else
			{
				ExtentTestManager.logFailStep("Clicking on : " + elementName + " failed with: ", e);
			}
		}
	}
	public void clickCheckBox(WebElement element, String elementName)
	{
		try
		{
			highlightElement(element);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			ExtentTestManager.logPassStep("Clicking by JS on : " + elementName);
			removeHighlightElement(element);
		}
		catch (Exception e)
		{
			ExtentTestManager.logFailStep("Clicking on : " + elementName + " failed with: ", e);
		}
	}
	public void quitBrowser() throws InterruptedException, IOException
	{
		if (driver != null)
		{
			driver.quit();
			// While closing Firefox, we get Container Plugin Error.. so to handle that we
			// kill from task manager
			Thread.sleep(3000); // The container plugin appears after 2 3 seconds of closing firefox browser
			Runtime.getRuntime().exec("taskkill /f /im WerFault.exe");
		}
	}
	public void DoAction(String command, WebElement element, String elementName)
	{
		Actions action = new Actions(driver);
		try
		// try block
		{
			switch (command)
			{
			case "Reset":
				element.clear();
				ExtentTestManager.logPassStep("Clearing on : " + elementName);
				break;
			case "MoveToElement":
				// Move to element
				action.moveToElement(element).build().perform();
				ExtentTestManager.logPassStep("Moving to Element on : " + elementName);
				break;
			case "DoubleClick":
				new Actions(driver).doubleClick(element).build().perform();
				ExtentTestManager.logPassStep("Double Click on : " + elementName);
				break;
			case "MoveToElementNClick":
				// Move to element
				action.moveToElement(element).perform();
				// Click element
				element.click();
				ExtentTestManager.logPassStep("Move and Click on : " + elementName);
				break;
			case "Submit":
				element.submit();
				ExtentTestManager.logPassStep("Submit on : " + elementName);
				break;
			}
		}
		catch (Exception e)
		{
			ExtentTestManager.logFailStep(command + " Failed on : " + elementName + "  with ", e);
		}
	}
	public void DoAlertsaction(String command)
	{
		Actions actions = new Actions(driver);
		try
		// try block
		{
			switch (command)
			{
			case "AcceptAlert":
				// Accept and switch to Alert
				alert = driver.switchTo().alert();
				alert.accept();
				ExtentTestManager.logPassStep("Alert Accepeted");
				break;
			case "CancelAlert":
				// Accept and switch to Alert
				alert = driver.switchTo().alert();
				alert.dismiss();
				ExtentTestManager.logPassStep("Cancel Alert");
				break;
			case "Refresh":
				// with cross browser implementation, sometimes we are getting "Timed out
				// waiting for page to load" after the operation is performed hence try
				// try block catch block
				try
				// try block
				{
					// Refresh driver
					driver.navigate().refresh();
					ExtentTestManager.logPassStep("Refresh : ");
				}
				catch (Exception e)
				{
					// perform key action
					actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
					ExtentTestManager.logPassStep("Refresh : ");
				}
				break;
			case "PressEscape":
				// Send keys
				actions.sendKeys(Keys.ESCAPE).build().perform();
				break;
			}
		}
		catch (Exception e)
		{
			ExtentTestManager.logFailStep(command + " Failed with ", e);
		}
	}
	public boolean CheckElementState(String command, WebElement element)
	{
		boolean objState = false;
		try
		// try block
		{
			switch (command)
			{
			case "IsEnabled":
				// Set objState
				objState = element.isEnabled();
				break;
			case "IsDisplayed":
				// Set objState
				objState = element.isDisplayed();
				break;
			case "IsSelected":
				// Set objState
				objState = element.isSelected();
				break;
			}
			// return object or value
			return objState;
		}
		catch (WebDriverException exp)
		{
			// return object or value
			return false;
		}
	}
	public String GetObjectProperties(String command, WebElement element)
	{
		String objState = null;
		try
		// try block
		{
			// Highlight(webElement);
			switch (command)
			{
			case "getText":
				// Set objState
				objState = element.getText();
				break;
			case "getValue":
			case "getSelectedValue":
				// Set objState
				objState = element.getAttribute("value");
				break;
			case "href":
				objState = element.getAttribute("href");
				break;
			}
			// return object or value
			return objState;
		}
		catch (ElementNotVisibleException exp)
		{
			// return object or value
			return null;
		}
		catch (WebDriverException exp)
		{
			// return object or value
			return null;
		}
	}
	public String DoAccessors(String command)
	{
		String actualValue = null;
		try
		// try block
		{
			switch (command)
			{
			case "GetTitle":
				// Set value
				actualValue = driver.getTitle();
				break;
			case "GetURL":
				// Set value
				actualValue = driver.getCurrentUrl();
				break;
			}
			// return object or value
			return actualValue;
		}
		catch (ElementNotVisibleException exp)
		{
			// return object or value
			return null;
		}
	}
	public boolean IsAlertPresent()
	{
		try
		// try block
		{
			if (null == alert)
			{
				alert = driver.switchTo().alert();
			}
			// return object or value
			return true;
		}
		catch (NoAlertPresentException e)
		{
			// return object or value
			return false;
		}
	}
	public WebElement WaitForElement(WebElement element)
	{
		try
		{
			int iteration = 0;
			boolean flag = false;
			do
			{
				if (element != null)
				{
					flag = element.isDisplayed();
					iteration++;
					Thread.sleep(1000);
				}
			} while (!flag && iteration < 60);
			return element;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	public void SwitchToDefaultContent()
	{
		driver.switchTo().defaultContent();
	}
	public void SwitchToFramebyName(String frameName)
	{
		driver.switchTo().frame(frameName);
	}
	public void Switchtoframebyindex(int index)
	{
		driver.switchTo().frame(index);
	}
	public void SwitchtoframebyWebElement(WebElement element)
	{
		try
		{
			driver.switchTo().frame(element);
		}
		catch (Exception e)
		{
		}
	}
	public void dragAndDrop(WebElement From, WebElement To)
	{
		Actions builder = new Actions(driver);
		builder.dragAndDrop(From, To);
	}
	public int Randomintvalue(int Min, int Max)
	{
		return Integer.parseInt(RandomStringUtils.random(10));
	}
	public int GetRandomNumberBetween(int min, int max)
	{
		Random rand = new Random();
		int randomNumber = rand.nextInt(max - min) + min;
		return randomNumber;
	}
	public void WaitForInivisibility(WebElement element, String Description) throws InterruptedException
	{
		Thread.sleep(3000);
		boolean state = true;
		int loop = 60;
		do
		{
			try
			{
				state = element.isDisplayed();
				if (state == false)
				{
					ExtentTestManager.logPassStep("Wait For Invsibility " + Description);
					break;
				}
				loop--;
				Thread.sleep(1000);
			}
			catch (Exception e)
			{
				ExtentTestManager.logFailStep("wait for Invisibility failed " + Description, e);
				break;
			}
		} while (state && loop > 0);
	}
	public static void WaitUntilEnabled(WebElement element, String StepDescription) throws InterruptedException
	{
		int timeout = 40;
		do
		{
			Thread.sleep(250);
			try
			{
				if (element.isEnabled())
				{
					ExtentTestManager.logPassStep("Wait For Enabled " + StepDescription);
					break;
				}
				timeout--;
			}
			catch (StaleElementReferenceException e)
			{
				timeout--;
			}
			catch (Exception e)
			{
				ExtentTestManager.logFailStep("wait for Enabled failed " + StepDescription, e);
				break;
			}
		} while (timeout > 0);
	}
	public static void WaitUntilDisplayed(WebElement element, String StepDescription)
	{
		int timeout = 40;
		do
		{
			try
			{
				Thread.sleep(250);
				if (element.isDisplayed())
				{
					break;
				}
				timeout--;
			}
			catch (NoSuchElementException e)
			{
				timeout--;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		} while (timeout > 0);
	}
}