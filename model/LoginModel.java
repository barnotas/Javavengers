package model;

public class LoginModel {
    private String username;
    private String pin;
    private boolean isLoggedIn;

    public LoginModel() {
        this.username = "";
        this.pin = "";
        this.isLoggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean authenticate() {
        // Perform authentication logic here
        // For example, you can check the username and PIN against a database or a predefined set of credentials
        // Return true if the authentication is successful, false otherwise
        return username.equals("admin") && pin.equals("1234");
    }
}