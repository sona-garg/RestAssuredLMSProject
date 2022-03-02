package com.lmsApi.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class Read_Propertyfile {
	public Properties loadProperties() {
		try {
			Properties prop = new Properties();
			String configPath= "C:\\Users\\manoj\\eclipse-workspace\\RestAssuredLMS_Project\\src\\test\\resources\\config\\config.properties";
			FileInputStream input = new FileInputStream(configPath);
			prop.load(input);
			return prop;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
