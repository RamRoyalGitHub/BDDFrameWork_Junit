package com.test.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.test.base.BasePage;
import com.test.dataprovider.ConfigFileReader;

public class DropDownTest extends BasePage{
	private ConfigFileReader configfilereader=new ConfigFileReader();
	
	public DropDownTest(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(css=".continents")
	private WebElement dropdown;
	
	public void dropdownmtd() {
		Select sel=new Select(dropdown);	
		List<WebElement> list=sel.getOptions();
		for (int i = 0; i < list.size(); i++) {
			String value=sel.getOptions().get(i).getText();
			System.out.println(value);
			sel.selectByIndex(i);
			
		}
		
	}
	
	public DropDownTest navigatetoUrl() {
		driver.get(configfilereader.getmuldopdownUrl());
		return null;
		
	}

}
