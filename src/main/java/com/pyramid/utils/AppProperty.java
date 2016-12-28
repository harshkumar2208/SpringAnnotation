package com.pyramid.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperty {
	
	private static AppProperty instance = new AppProperty();
	private Properties properties;
	
	private AppProperty()
	{
		InputStream is = this.getClass().getResourceAsStream("Configuration.properties");
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static AppProperty getInstance()
	{
		return instance;
	}
	
	public String getProperties(String key)
	{
		return properties.getProperty(key);
	}

}
