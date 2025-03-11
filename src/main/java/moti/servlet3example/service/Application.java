
package moti.servlet3example.service;

public class Application implements Service {
    public static final String SERVLET_CONTEXT_KEY = Application.class.getName();
    
    private static Application INSTANCE = new Application();
    
    private Config config;
    private UserService userService;
    
    private Application() {
    }
    
    public static Application getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void init() {
        config = new Config("config.properties");
        
        userService = new UserService();
        userService.init();
    }
    
    @Override
    public void destroy() {   
        userService.destroy();     
    }

    public UserService getUserService() {
        return userService;
    }

    public Config getConfig() {
        return config;
    }
}
