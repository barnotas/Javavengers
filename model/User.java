package model;

public class User {
    private String firstName;
    private String email;
    private boolean loggedIn;

    public User() {
        // Initialize the User object with default values
        this.firstName = "Guest";
        this.email = "";
    }

    public User(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}