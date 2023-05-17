package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
 	Properties properties;
	String propertyFilePath = "./src/test/java/config/config.properties";
	
	public ReadConfig() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at "+propertyFilePath);
		}
	}
	
	public  String getAndroidPhone() {
		String androidPhone = properties.getProperty("androidPhone");
		
		if (androidPhone != null) {
			return androidPhone;
		} else {
			throw new RuntimeException("androidPhone not specified in the Configuration.properties");
		}
	}
	
	public  String getAndroidTablet() {
		String androidTablet = properties.getProperty("androidTablet");
		
		if (androidTablet != null) {
			return androidTablet;
		} else {
			throw new RuntimeException("androidTablet not specified in the Configuration.properties");
		}
	}
	
	public  String getIOSPhone() {
		String iosPhone = properties.getProperty("iosPhone");
		
		if (iosPhone != null) {
			return iosPhone;
		} else {
			throw new RuntimeException("iosPhone not specified in the Configuration.properties");
		}
	}
	
	public  String getIOSTablet() {
		String iosTablet = properties.getProperty("iosTablet");
		
		if (iosTablet != null) {
			return iosTablet;
		} else {
			throw new RuntimeException("iosTablet not specified in the Configuration.properties");
		}
	}
	
	
}
