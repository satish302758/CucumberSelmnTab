package com.Tab.steps;

import com.Tab.PageObjects.Page_LoginPage;

import java.util.List;
import java.util.Map;

import com.Tab.PageObjects.Page_Applications;
import com.Tab.PageObjects.Page_HomePage;

 
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class StepDefinition_OnBoarding 
{
	Page_LoginPage login = new Page_LoginPage();
	Page_HomePage home;
	Page_Applications applications;
	/*
	 * @Given("^launch browser '(.*?)'$") public void launsch_browser(String
	 * browserName) throws Throwable { openBrowser(browserName);
	 * ExtentTestManager.logInfo("Browser Opened : " + browserName);
	 * 
	 * }
	 * 
	 */
	/*
	 * @Given("^launch browser '(.*?)'$") public void launch_browser(String
	 * browserName) throws Exception { openBrowser(browserName);
	 * ExtentTestManager.logInfo("Browser Opened : " + browserName); }
	 */
////Background Step
	
	
	@Given("launch browser")
	public void launch_browser( ) {

       login.loginToApplication();
  
	}
	@Given("launch browsert")
	public void launch_browsert( ) {

		 login.loginToApplicationtwo();
	}
	 
	@When("^user navigates to the URL ([^\\\"]*)$")
	public void user_navigates_to_the_URL(String string)
	{

		//home =  login.loginToApplication(string, "Password.1");
	}
	@Then("user click on login")
	public void user_click_on_login( DataTable dataTable) {
	   //List<Map<String,String>> data= dataTable.asMaps(String.class, String.class);
	  //applications= home.openApplications(); 
	  //applications.createDepositProduct(data.get(0).get("ProductLine"), data.get(0).get("ProductType"),data.get(0).get("CustomerType"), data.get(0).get("ProductName"));
	
	}
	  
	
	
	
	
	
	
}