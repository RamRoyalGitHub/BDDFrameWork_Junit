package com.test.manager;

import com.test.dataprovider.ConfigFileReader;

public class FileReaderManager {

	private static FileReaderManager filereaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;

	private FileReaderManager() {
		// TODO Auto-generated constructor stub
	}

	public static FileReaderManager getInstance() {
		return filereaderManager;

	}

	public ConfigFileReader getConfigReader() {
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;

	}

}
