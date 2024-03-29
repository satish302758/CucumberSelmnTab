package com.Tab.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Tab.Driver.DriverManager;

public class Page_LoginPage extends BasePage
{

	Page_LoginPage log;
	@FindBy(xpath = "//input[@cuba-id=\"loginField\"]")
	WebElement txt_UserName;
	@FindBy(xpath = "//input[@cuba-id=\"passwordField\"]")
	WebElement txt_Password;
	@FindBy(xpath = "//div[@cuba-id=\"loginButton\"]")
	WebElement btn_Login;

	public Page_HomePage loginToApplication()
	{
		 
		try
		{
			
			
			DriverManager.getDriver().navigate().to("https://google.com");
			/*DriverManager.getDriver().navigate().to("https://onboardingint.tabbank.com/app/#!");
			// base(Page_LoginPage.class);
			PageFactory.initElements(driver, this);
			type(txt_UserName, "admin", "UserName");
			type(txt_Password, "abc123", "Password");
			click(btn_Login, "Login button");*/
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return (Page_HomePage) base(Page_HomePage.class);
	}
	
	
	public Page_HomePage loginToApplicationtwo() {
		
		try
		{
			
			
			DriverManager.getDriver().navigate().to("https://gotomeeting.com");
			/*DriverManager.getDriver().navigate().to("https://onboardingint.tabbank.com/app/#!");
			// base(Page_LoginPage.class);
			PageFactory.initElements(driver, this);
			type(txt_UserName, "admin", "UserName");
			type(txt_Password, "abc123", "Password");
			click(btn_Login, "Login button");*/
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return (Page_HomePage) base(Page_HomePage.class);
	}
	
/*	public Page_HomePage loginToApplicationtwo(String user, String Password)
	{
		try
		{
			DriverManager.getDriver().navigate().to("https://onboardingint.tabbank.com/app/#!");

			// base(Page_LoginPage.class);
			PageFactory.initElements(driver, this);
			type(txt_UserName, "admin", "UserName");
			type(txt_Password, "abc123", "Password");
			click(btn_Login, "Login button");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return (Page_HomePage) base(Page_HomePage.class);
	}*/
	@Override
	protected ExpectedCondition getPageLoadCondition()
	{
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(txt_UserName);
	}

}
