package moti.servlet3example.service;

import java.io.Reader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;




public class UserService implements Service {
    // Username and password store
    private Properties usersProps = new Properties();
        
    /**
     * Load $HOME/java-ee-example/servlet3example-users.properties if exists, else it will
     * use the one in classpath under "/zemian/servlet3example/service/servlet3example-users.properties".
     * 
     * @throws RuntimeException - if both file and resource name not found. 
     */
    @Override
    public void init() {        
        // Replaced anonymous inner class with lambda expression for Java SE 21 compatibility
        FileUtils.loadOptionalFile("servlet3example-users.properties", getClass().getPackage().getName(), 
            reader -> usersProps.load(reader));
    }
    
    @Override
    public void destroy() {
        // Do nothing.
    }
        
    public boolean validate(String username, String password) {
        String storedPassword = usersProps.getProperty(username);
        return storedPassword != null && storedPassword.equals(password);
    }
    
    public Set<String> getUsers() {
        Set<String> result = new HashSet<>();
        for (String username : usersProps.stringPropertyNames())
            result.add(username);
        return result;
    }
}