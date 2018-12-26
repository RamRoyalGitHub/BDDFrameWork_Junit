package com.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.base.BasePage;

public class FBLoginPage extends BasePage {

	@FindBy(css = "#email")
	private WebElement txtEmailOrPhone;

	@FindBy(css = "#pass")
	private WebElement txtPassword;

	@FindBy(css = "#u_0_2")
	private WebElement btnLogin;


	


	public FBLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}



	public FBLoginPage navigateToFb() {
		try {
			driver.navigate().to("www.facebook.com");
		} catch (Exception e) {
			System.out.println("Failed to navigate to fb login page");
			e.printStackTrace();
		}
		return this;
	}
	
	public FBLoginPage login() {
		waitForPageToLoad();
		try {
	
			setValue(txtEmailOrPhone, "venkatarmn76");
			setValue(txtPassword, "77991");
			click(btnLogin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return this;
	}
	
}
