package com.epam.framework.config;

import com.epam.framework.exceptions.ConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();
    private static volatile boolean loaded = false;

    private ConfigReader() {
    }

    public static synchronized void loadProperties() {
        if (loaded) {
            return;
        }

        try (InputStream input =
                     ConfigReader.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new ConfigurationException(
                        "config.properties not found on classpath"
                );
            }

            properties.load(input);
            loaded = true;

        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to load config.properties", e
            );
        }
    }

    public static String getProperty(String key) {
        if (!loaded) {
            loadProperties();
        }

        String systemOverride = System.getProperty(key);
        if (systemOverride != null) {
            return systemOverride;
        }

        return properties.getProperty(key);
    }
}