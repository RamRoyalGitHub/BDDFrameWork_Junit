package com.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.base.BasePage;
import com.test.dataprovider.ConfigFileReader;

public class MultipleWindow extends BasePage {
	private final Logger log = LoggerFactory.getLogger(MultipleWindow.class);
	ConfigFileReader configFileReader;

	@FindBy(css = "#button1")
	private WebElement btnNewWindow;

	@FindBy(xpath = "//a[text()=\"Enroll Your Self\"]")
	private WebElement linkToenroll;

	@FindBy(css = ".your-name")
	private WebElement txtName;

	public MultipleWindow(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public MultipleWindow clickbtnNewWindow() {
		try {
			click(btnNewWindow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage() + e);

		}
		return this;

	}

	public MultipleWindow clickolllink() {
		try {
			click(linkToenroll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;

	}

	public MultipleWindow enterName() {
		try {
			setValue(txtName, configFileReader.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;

	}

}
