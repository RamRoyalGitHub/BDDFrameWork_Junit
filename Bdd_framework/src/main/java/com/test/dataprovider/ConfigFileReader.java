package com.test.dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ConfigFileReader {

	private Properties properties;
	private final String proprtyFilePath = "configs//test.properties";

	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(proprtyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + proprtyFilePath);
		}
	}

	public String geturl() {

		String msurl = properties.getProperty("msurl");
		if (msurl != null) {
			return msurl;
		} else {
			throw new RuntimeException("msurl not specified in the test.properties file.");

		}

	}

	public String getusername() {
		String userid = properties.getProperty("userid");
		if (userid != null) {
			return userid;

		} else {
			throw new RuntimeException("userid not specified in the test.properties file.");

		}
	}

	public String getpassword() {
		String password = properties.getProperty("password");
		if (password != null) {
			return password;

		} else {
			throw new RuntimeException("password not specified in the test.properties file.");

		}
	}

	public String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if (reportConfigPath != null) {
			return reportConfigPath;

		} else {
			throw new RuntimeException("reportConfigPath not specified in the test.properties file.");
		}

	}

	public String getName() {
		String name = properties.getProperty("name");
		if (name != null) {
			return name;

		} else {
			throw new RuntimeException("name not specified in the test.properties file.");
		}
	}

	public String getDropDownUrl() {
		String dropdownurl = properties.getProperty("dropdownurl");
		if (dropdownurl != null) {
			return dropdownurl;

		} else {
			throw new RuntimeException("dropDownurl not specified in the test.properties file.");

		}
	}

	public String getdataDriverDemoUrl() {
		String dataDriverDemoUrl = properties.getProperty("dataDriverDemoUrl");
		if (dataDriverDemoUrl != null) {
			return dataDriverDemoUrl;

		} else {
			throw new RuntimeException("dataDriverDemoUrl not specified in the test.properties file.");

		}
	}

	public String getmuldopdownUrl() {
		String muldropdownUrl = properties.getProperty("muldropdownUrl");
		if (muldropdownUrl != null) {
			return muldropdownUrl;

		} else {
			throw new RuntimeException("dropdownUrl not specified in the test.properties file.");

		}
	}

}
