package com.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.base.BasePage;
import com.test.dataprovider.ConfigFileReader;

public class DataDrivenWithoutExample extends BasePage {
	
	ConfigFileReader configFileReader=new ConfigFileReader();

	
	public DataDrivenWithoutExample(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}


	@FindBy(css="#account")
	private WebElement linkmyAccoynt;
	
	@FindBy(css="#log")
	private WebElement txtUserName;
	
	
	@FindBy(css="#pwd")
	private WebElement txtPassword;
	
	public DataDrivenWithoutExample navigatoDemo() {
		
		try {
			navigateTo(configFileReader.getdataDriverDemoUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return this;
	}
	public DataDrivenWithoutExample navigatoLoginPage() {
		try {
			click(linkmyAccoynt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public DataDrivenWithoutExample enterLoginCredentials(String UserName,String Password) {
		try {
			setValue(txtUserName, UserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			setValue(txtPassword, Password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
}
