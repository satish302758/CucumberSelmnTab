package com.Tab.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Page_Applications extends BasePage
{
	/// For Applications Pages
	@FindBy(xpath = "//div[@cuba-id=\"createBtn\"]")
	private WebElement btn_Create;
	@FindBy(xpath = "//div[text()='Application Product Selection']")
	private WebElement lbl_ApplicationProductSelection;
	@FindBy(xpath = "//div[@cuba-id=\"productLineLookup\"]//child::input")
	private WebElement lkp_ProductLine;
	@FindBy(xpath = "//div[@cuba-id=\"productLineLookup\"]//child::input")
	private List<WebElement> lkp_ProductLineList;
	@FindBy(xpath = "//div[@cuba-id=\"productTypeLookup\"]//child::input")
	private WebElement lkp_ProductType;
	@FindBy(xpath = "//div[@cuba-id=\"customerTypeLookup\"]//child::input")
	private WebElement lkp_CustomerType;
	@FindBy(xpath = "//div[@cuba-id=\"customerTypeLookup\"]//child::input")
	private WebElement chk_SelectProduct;
	@FindBy(xpath = "//span[text()='OK']//parent::span//parent::div")
	private WebElement btn_OK;
	@Override
	protected ExpectedCondition getPageLoadCondition()
	{
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(btn_Create);
	}
	private WebElement SelectProduct(String productName)
	{
		return this.driver.findElement(By.xpath("//label[text()='" + productName + "']//preceding-sibling::input"));
	}
	public void createDepositProduct(String productLine, String productType, String customerType, String productName)
	{
		
		click(btn_Create, "Create_Btn");
		WaitUntilDisplayed(lbl_ApplicationProductSelection, "Application Product Selection");
		type(lkp_ProductLine, productLine + Keys.ENTER+Keys.TAB, "Product Line");
		type(lkp_ProductType, productType +  Keys.ENTER+Keys.TAB, "Product Type");
		type(lkp_CustomerType, customerType +  Keys.ENTER+Keys.TAB, "Customer Type");
		clickCheckBox(SelectProduct(productName), "Product Name");
		click(btn_OK, "OK button");
	}
}
