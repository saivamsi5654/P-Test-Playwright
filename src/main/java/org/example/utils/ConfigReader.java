package org.example.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

@Slf4j
@UtilityClass
public class ConfigReader {
    private static final Properties CONFIG_PROPERTIES = new Properties();

    static {
        loadProperties("configs/config-{0}.properties", CONFIG_PROPERTIES);
    }

    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    public static String getProperty(String key, String defaultValue) {
        return CONFIG_PROPERTIES.getProperty(key, defaultValue);
    }

    public static String getConfigProperty(String key) {
        return getConfigProperty(key, null);
    }

    public static String getConfigProperty(String key, String defaultValue) {
        return CONFIG_PROPERTIES.getProperty(key, defaultValue);
    }

    public static void loadProperties(String filePath, Properties properties) {
        String env = System.getProperty("env", "int");
        String resolvedPath = MessageFormat.format(filePath, env);
        log.info("Loading configuration for environment '{}' from '{}'", env, resolvedPath);

        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream(resolvedPath)) {
            if (inputStream == null) {
                throw new IllegalStateException("Unable to find configuration resource: " + resolvedPath);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read configuration resource: " + resolvedPath, e);
        }

        // JVM system properties override values from the configuration file.
        System.getProperties().forEach((key, value) -> {
            if (key instanceof String && value instanceof String) {
                properties.setProperty((String) key, (String) value);
            }
        });
    }
}
