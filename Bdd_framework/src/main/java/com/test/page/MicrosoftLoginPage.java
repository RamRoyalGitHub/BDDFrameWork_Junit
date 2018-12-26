package com.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.base.BasePage;
import com.test.dataprovider.ConfigFileReader;
import com.test.manager.FileReaderManager;

public class MicrosoftLoginPage extends BasePage {

	private ConfigFileReader configFileReader= new ConfigFileReader();
	
	@FindBy(css = "#i0116")
	private WebElement txtLoginID;

	@FindBy(css = "#idSIButton9")
	private WebElement btnLoginNext;

	public MicrosoftLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		/*configFileReader=new ConfigFileReader();*/

	}

	public MicrosoftLoginPage navigateToMS() {

		try {
			driver.navigate().to(configFileReader.geturl());
			/*driver.navigate().to(FileReaderManager.getInstance().getConfigReader().geturl());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	public MicrosoftLoginPage loginIDMtd() {

		try {
			setValue(txtLoginID, configFileReader.getusername());
		/*	setValue(txtLoginID, FileReaderManager.getInstance().getConfigReader().getusername());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;

	}

	public Class<MicrosoftPwdPage>  btnNextMtd() {

		try {
			click(btnLoginNext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MicrosoftPwdPage.class;

	}
}
