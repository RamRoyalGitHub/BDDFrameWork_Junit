package com.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.dataprovider.ConfigFileReader;
import com.test.manager.FileReaderManager;

public class MicrosoftPwdPage extends MicrosoftLoginPage {
	ConfigFileReader configFileReader;

	@FindBy(css="#i0118")
	private WebElement txtPwd;
	
	@FindBy(css="#idSIButton9")
	private WebElement btnSignin;
	
	public MicrosoftPwdPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		configFileReader =new ConfigFileReader();
		
	}
	
	public MicrosoftPwdPage entertxtPwd() {
		setValue(txtPwd,configFileReader.getpassword());
	/*	setValue(txtPwd,FileReaderManager.getInstance().getConfigReader().getpassword());*/
		return this;
		
	}
	
	public MicrosoftPwdPage clickSigninbtnMtd() {
		jsClick(btnSignin);
		return this;
		
	}

}
