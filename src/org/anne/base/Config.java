package org.anne.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static Properties pfile = loadProperties("config.properties");
	public static String IMAGEPATH = pfile.getProperty("imagepath");
	public static String FILEPATH = pfile.getProperty("filepath");

	public static String getConfig(String key) {
		return pfile.getProperty(key);
	}

	private static Properties loadProperties(String fileName) {
		Properties properties = new Properties();
		InputStream in = Config.class.getClassLoader().getResourceAsStream(
				fileName);
		try {
			properties.load(in);
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
