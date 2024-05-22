package model;

public class Settings {
    private User user;

    public Settings() {
        // Initialize the Settings object with a default User
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}