package com.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



import com.test.base.BasePage;
import com.test.dataprovider.ConfigFileReader;

public class DropDownRegister extends BasePage {
	
	private ConfigFileReader configFileReader=new ConfigFileReader();
	
	public DropDownRegister(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//select[@name='country']")
	private WebElement dropDown;
	
	@FindBy(name="submit")
	private WebElement btnSubmit;
	
	

	public DropDownRegister selectValueinnDropDown() {
		Select select=new Select(dropDown);
		try {
			select.selectByVisibleText("ALGERIA");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return this;
	}

	public DropDownRegister naviagteToReister() {
		try {
			driver.navigate().to(configFileReader.getDropDownUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return this;
	}
	
	public DropDownRegister clickSubmit() {
		try {
			click(btnSubmit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return this;
	}
	
	
	
}
