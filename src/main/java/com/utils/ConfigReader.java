package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	/**
	 * This function loads the properties file
	 */
	private Properties prop;
	
	public Properties initialize_prop() {
		prop = new Properties();
		try {
			FileInputStream inFile = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}

}
