package com.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    public static Properties prop;
    private static final String FILE_PATH = System.getProperty("user.dir") + File.separator + "Config.properties";

    // Static block to load the file once when the class is first accessed
    static {
    	 prop = new Properties();
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
        	System.out.println(FILE_PATH);
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("Could not load Config.properties file at: " + FILE_PATH);
        }
    }

    public static String getProperty(String key) {
        // 1. Check System Properties first (Jenkins/Maven -D parameters)
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isEmpty()) {
            return systemValue;
        }

        // 2. Fallback to the Config.properties file
        return prop.getProperty(key);
    }
}