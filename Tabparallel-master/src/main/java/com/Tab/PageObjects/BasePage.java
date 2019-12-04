package com.Tab.PageObjects;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Tab.Driver.DriverManager;
import com.Tab.Driver.WebDriverHelpers;
public abstract class BasePage < T > extends WebDriverHelpers
{
	protected WebDriver driver;
	private final long PAGE_LOAD_TIMEOUT = 30;
	//private final int AJAX_ELEMENTLOAD_TIMEOUT = 30;
	public BasePage()
	{
		this.driver = DriverManager.getDriver();
	}
	public T base(Class < T > objectClass)
	{
		T page = null;
		try
		{
			 
			//AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENTLOAD_TIMEOUT);
			page = PageFactory.initElements(driver, objectClass);
			//PageFactory.initElements(ajaxElemFactory, page);
			
			ExpectedCondition<?> pageLoadCondition =  ((BasePage<?>) page).getPageLoadCondition();
			
			waitForPageToLoad(pageLoadCondition);
			
		}
		catch (NoSuchElementException e)
		{
			 
			throw new IllegalStateException(String.format("This is not the %s page", objectClass.getSimpleName()));
		}
		return page;
	}
	 
	private void waitForPageToLoad(ExpectedCondition<?> pageLoadCondition)
	{
		WebDriverWait wait = new WebDriverWait(driver, PAGE_LOAD_TIMEOUT);
		wait.until(pageLoadCondition);
	}
	protected abstract ExpectedCondition<?> getPageLoadCondition();
}