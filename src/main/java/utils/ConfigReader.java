package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }
        return props.getProperty(key);
    }
}
