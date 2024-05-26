package model;
/**
 * LoginModel represents a user login model containing username and pin number.
 * This class encapsulates the login credentials of a user.
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */
public class LoginModel {
    private String username;
    private String pin;
    private boolean isLoggedIn;

    /**
     * Constructs LoginModel class.
     */
    public LoginModel() {
        this.username = "";
        this.pin = "";
        this.isLoggedIn = false;
    }
    
    /**
     * Returns username.
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Returns pin number.
     * @return
     */
    public String getPin() {
        return pin;
    }
    /**
     * Sets pin number.
     * @param pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Returns true to approve login.
     * @return
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Sets true to approve login.
     * @param isLoggedIn
     */
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    /**
     * Returns if the authentication is approved.
     * @return
     */
    public boolean authenticate() {
        // Perform authentication logic here
        // For example, you can check the username and PIN against a database or a predefined set of credentials
        // Return true if the authentication is successful, false otherwise
        return username.equals("admin") && pin.equals("1234");
    }
}