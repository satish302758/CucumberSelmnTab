package com.Tab.PageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class Page_HomePage extends BasePage
{ 
	 
        
        @FindBy(xpath= "//span[@cuba-id=\"application\"]")
        private WebElement lnl_Application;
        @FindBy(xpath= "//span[text()='Applications']")
        private WebElement lnk_Applications;
       
 
	@Override
	protected ExpectedCondition getPageLoadCondition()
	{
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(lnl_Application);
	}
	 
	public Page_Applications openApplications()
	{
 
      click(lnl_Application, "Application Link");
      click(lnk_Applications, "Applications Link"); 
      return (Page_Applications) base(Page_Applications.class);
	}
	
	 
}