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
        // Using var for local variable type inference (Java 10+)
        // This is an optional change for Java 21 upgrade
        var config = new Config("config.properties");
        this.config = config;
        
        // Using var for local variable type inference (Java 10+)
        var userService = new UserService();
        this.userService = userService;
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