package com.test.runner;

import java.io.*;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;
import com.test.dataprovider.ConfigFileReader;
import com.test.manager.FileReaderManager;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "src/main/resources/MultipleWindow.feature"
			,glue={"com.test.step"},
			dryRun= true,
			plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
			/*tags= {"@SmokeTest,@RegressionTest"},*/
			monochrome=true
		    
		
			//tags= {"@LoginTest"}
			)
	public class JunitRunnerClass {
	@AfterClass
	public static void writeExtentReport() {
		ConfigFileReader configFileREader = new ConfigFileReader();
		Reporter.loadXMLConfig(new File( configFileREader.getReportConfigPath()));
			/*Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));*/
		Reporter.setSystemInfo("User Name", System.getProperty("User Name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("Time Zone"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("Time Zone"));
		Reporter.setSystemInfo("Machine","Windows 10" + "64 bit");
		Reporter.setSystemInfo("webdriver","3.0.0");
		Reporter.setSystemInfo("java version","1.8.0_121");
	}

	}


