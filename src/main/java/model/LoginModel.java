package model;

/**
 * Represents a login model with username and PIN authentication.
 */
public class LoginModel {
    private String username;
    private String pin;
    private boolean isLoggedIn;

    /**
     * Constructs a new LoginModel with default values.
     */
    public LoginModel() {
        this.username = "";
        this.pin = "";
        this.isLoggedIn = false;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the PIN.
     *
     * @return the PIN
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the PIN.
     *
     * @param pin the PIN to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Sets the logged-in status of the user.
     *
     * @param isLoggedIn true if the user is logged in, false otherwise
     */
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * Authenticates the user based on the provided username and PIN.
     *
     * @return true if the authentication is successful, false otherwise
     */
    public boolean authenticate() {
        // Perform authentication logic here
        // For example, you can check the username and PIN against a database or a predefined set of credentials
        // Return true if the authentication is successful, false otherwise
        return username.equals("admin") && pin.equals("1234");
    }
}