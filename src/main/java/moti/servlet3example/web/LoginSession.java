package moti.servlet3example.web;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class LoginSession implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String LOGIN_SESSION_KEY = LoginSession.class.getSimpleName();
    private String id = UUID.randomUUID().toString();
    // Note: In a real-world application, consider using java.time.Instant instead of Date
    // which was introduced in Java 8 and is preferred for new code
    private Date dateCreated = new Date();
    private String username;
    
    public LoginSession(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public String getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "SessionData(id=" + id + ", username=" + username + ", dateCreated=" + dateCreated + ")";
    }
}