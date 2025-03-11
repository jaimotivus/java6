package moti.servlet3example.service;

import java.io.Reader;
import java.util.Properties;
import java.util.Set;

/**
 * Load and provide config properties for other services. It first check for user directory
 * for a config file, if not found then load a classpath resource as default.
 * 
 * @author zedeng
 *
 */
public class Config {
    private Properties config = new Properties();

    public Config(String fileName) {
        // Replaced anonymous inner class with lambda expression for Java SE 21 compatibility
        FileUtils.loadOptionalFile(fileName, getClass().getPackage().getName(), reader -> config.load(reader));
    }
    
    public Set<String> getKeys() {
        return config.stringPropertyNames();
    }

    public String getValue(String key) {
        return config.getProperty(key);
    }

    public String getValue(String key, String def) {
        String result = config.getProperty(key);
        if (result == null)
                result = def;
        return result;
    }
}