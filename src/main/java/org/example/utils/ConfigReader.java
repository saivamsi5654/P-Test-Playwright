package org.example.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

@Slf4j
@UtilityClass
public class ConfigReader {
    private static final Properties configProperties = new Properties();

    static {
        loadProperties("config/config-{0}.properties",configProperties);
    }

    public static String getProperty(String key) {
        return getProperty(key, null);
    }
    public static String getProperty(String key, String defaultValue) {
        return getConfigProperty(key, null);
    }

    public static String getConfigProperty(String key) {
        return getConfigProperty(key, null);
    }

    public String getConfigProperty(String key, String defaultValue) {
        return configProperties.getProperty(key, defaultValue);
    }

    public static void loadProperties(String filePath, Properties properties) {
        Properties systemProperties = System.getProperties();
        String env = systemProperties.getProperty("env","int");
        filePath = MessageFormat.format(filePath,env);
        log.info("Env for config file {}",env);
        try(InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(filePath)) {
            if(inputStream == null) {
                throw new RuntimeException("Unable to find " + filePath);
            }
            properties.load(inputStream);
            properties.putAll(systemProperties);
        } catch(Exception e){
            log.error("Unable to read properties file.");
        }
        log.debug("All properties {}", properties);
    }
}
