package com.test.page;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	WebDriver driver;
	TakesScreenshot srsh=((TakesScreenshot)driver);
	File srcFile=srsh.getScreenshotAs(OutputType.FILE);
	File dest= new File("//screen.jpg
}
